package com.ivansiabro.bootcounter.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.ivansiabro.bootcounter.data.repository.BootRepository
import com.ivansiabro.bootcounter.notification.BootNotificationManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@HiltWorker
class BootWorker @AssistedInject constructor(
    private val repo : BootRepository,
    @Assisted appContext : Context,
    @Assisted params : WorkerParameters
) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val bootEvents = repo.getEvents().first()
        val message = when {
            bootEvents.isEmpty() -> "No boots detected"
            bootEvents.size == 1 -> "The boot was detected = ${formatDate(bootEvents.first().timestamp)}"
            else -> {
                val lastEvent = bootEvents.first()
                val secondLastEvent = bootEvents[1]
                val timeDelta = calculateTimeDelta(lastEvent.timestamp, secondLastEvent.timestamp)
                "Last boots time delta = $timeDelta"
            }
        }
        val notificationManager = BootNotificationManager(applicationContext)
        notificationManager.showNotification("Boot Event", message)
        return Result.success()
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    private fun calculateTimeDelta(lastTimestamp: Long, secondLastTimestamp: Long): String {
        val deltaMillis = lastTimestamp - secondLastTimestamp
        val deltaMinutes = deltaMillis / (1000 * 60)
        return "$deltaMinutes minutes"
    }
}
package com.ivansiabro.bootcounter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.CallSuper
import com.ivansiabro.bootcounter.data.repository.BootRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var bootRepository: BootRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val pendingResult = goAsync()
            @OptIn(DelicateCoroutinesApi::class) // Must run globally; there's no teardown callback.
            GlobalScope.launch {
                try {
                    bootRepository.saveEvent(System.currentTimeMillis())
                } finally {
                    pendingResult.finish()
                }
            }
            Log.d("BootReceiver", "Intent.ACTION_BOOT_COMPLETED, bootRepository.toString(): $bootRepository")
        }
    }
}
package com.ivansiabro.bootcounter.notification

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class BootNotificationManager(private val context: Context) {

    companion object {
        private const val CHANNEL_ID = "BOOT_APP_CHANNEL_ID"
        private const val CHANNEL_NAME = "CHANNEL_NAME"
        private const val CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION"
        private const val NOTIFICATION_ID = 0
    }

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)  // Dismisses the notification when tapped
            .setDefaults(Notification.DEFAULT_ALL)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //we don't have permission to show notification
                return
            }
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    fun cancelNotification() {
        with(NotificationManagerCompat.from(context)) {
            cancel(NOTIFICATION_ID)
        }
    }
}
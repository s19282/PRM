package pl.edu.pja.travelerapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.GeofencingEvent

class Notifier : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        println("back!!!!!")
        val event = intent?.let {
            GeofencingEvent.fromIntent(it)
        }
        val descriptionActivity = Intent(context, ShowPictureActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("id",event?.triggeringGeofences?.first()?.requestId.toString().toLong())
        }
        val descriptionPendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            5,
            descriptionActivity,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationId: Int = if(intent!=null && intent.hasExtra("requestCode")) {
            intent.getIntExtra("requestCode",-1)
        }
        else {
            -2
        }

        context?.let {
            val notification = NotificationCompat.Builder(it, "pl.edu.pja.travelerapp.Geofence")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("You've been to this place before")
                .addAction(
                    R.drawable.ic_launcher_foreground,
                    "See picture!",
                    descriptionPendingIntent
                )
                .build()
            it.getSystemService(NotificationManager::class.java)
                ?.notify(notificationId, notification)
        }
    }
}
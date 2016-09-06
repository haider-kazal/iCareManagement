package info.kazal.app.icaremanagement;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by mrFarhad on 9/29/15.
 */
public class AlarmReceiverDiet extends BroadcastReceiver {

    private int id_diet;

    Intent intent;

    public Intent getIntent() {
        return intent;
    }


    @Override
    public void onReceive(Context context, Intent intent) {


        PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MyDietChart.class), 0);
        //Intent i = getIntent();
        id_diet = intent.getIntExtra("id_diet", -1);

        // Builds a notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Diet Alarm")
                        .setTicker("see your diet chart")
                        .setContentText(" see your diet chart");

        // Defines the Intent to fire when the notification is clicked
        mBuilder.setContentIntent(notificIntent);

        // Set the default notification option
        // DEFAULT_SOUND : Make sound
        // DEFAULT_VIBRATE : Vibrate
        // DEFAULT_LIGHTS : Use the default light notification
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        // Auto cancels the notification when clicked on in the task bar
        mBuilder.setAutoCancel(true);

        // Gets a NotificationManager which is used to notify the user of the background event
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Post the notification
        mNotificationManager.notify(id_diet, mBuilder.build());

    }

}

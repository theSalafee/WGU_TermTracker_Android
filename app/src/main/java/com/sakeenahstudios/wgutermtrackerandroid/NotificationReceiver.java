package com.sakeenahstudios.wgutermtrackerandroid;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {
    public static final String courseAlarmFile = "courseAlarms";
    public static final String termAlarmFile = "termAlarms";
    public static final String assessmentAlarmFile = "assessmentAlarms";
    public static final String alarmFile = "alarmFile";
    public static final String nextAlarmField = "nextAlarmID";
    private static final String CHANNEL_ID = "WGUC196";
    public  static int notificationId;

    @Override
    public void onReceive (Context context, Intent intent) {
//        Toast.makeText(context, intent.getStringExtra("Channel ID = " + CHANNEL_ID), Toast.LENGTH_LONG).show();
        Toast.makeText(context, "I got this far", Toast.LENGTH_LONG).show();
        createNotificationChannel(context, CHANNEL_ID );
        String msg = intent.getStringExtra("key");
        //Inputs
        String destination = intent.getStringExtra("destination");
        if (destination == null || destination.isEmpty()) {
            destination = "";
        }
        int id = intent.getIntExtra("id", 0);
//        String alarmTitle = intent.getStringExtra("title");
//        String alarmText = intent.getStringExtra("text");
//        int nextAlarmId = intent.getIntExtra("nextAlarmId", getAndIncrementNextAlarmId(context));

        // Notification Builder
        Notification builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_lightbulb_outline_black_24dp)
                .setContentTitle(msg)
                .setContentText(msg).build();

        NotificationManager mm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mm.notify(notificationId++, builder);

//        //Target intent to launch
//        Intent resultIntent;
//        Uri uri;
//        SharedPreferences sharedPreferences;
//        switch (destination) {
//
//            case "course":
//                resultIntent = new Intent(context, CourseEditorActivity.class);
//                //resultIntent.putExtra(EXTRA_COURSEID, id);
//                resultIntent.putExtra(EXTRA_COURSEID, id);
//                break;
//
//            case "term":
//                resultIntent = new Intent(context, TermEditorActivity.class);
//                resultIntent.putExtra(EXTRA_TERMID, id);
//                break;
//
//            case "assessment":
//                resultIntent = new Intent(context, AssessmentEditorActivity.class);
//                resultIntent.putExtra(EXTRA_ASSESSMENTID, id);
//                break;
//
//            default:
//                resultIntent = new Intent(context, MainActivity.class);
//                break;
//        }

        //Task navigation for target intent.
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(resultPendingIntent).setAutoCancel(true);
//
//        //Do the notification
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(nextAlarmId, builder.build());
    }


    public static boolean _scheduleAlarm (Context context, int id, long time, String title, String text, String destinationString, String alarmFile) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        int nextAlarmId = getNextAlarmId(context);
        Intent intentAlarm = new Intent(context, NotificationReceiver.class);
        intentAlarm.putExtra("id", id);
        intentAlarm.putExtra("title", title);
        intentAlarm.putExtra("text", text);
        intentAlarm.putExtra("destination", destinationString);
        intentAlarm.putExtra("nextAlarmId", nextAlarmId);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, nextAlarmId, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT)); //FLAG_UPDATE_CURRENT));

        SharedPreferences sp = context.getSharedPreferences(alarmFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Integer.toString(id), nextAlarmId);
        editor.commit();

        incrementNextAlarmId(context);
        return true;
    }

    public static boolean scheduleCourseAlarm (Context context, int id, long time, String title, String text) {
        return _scheduleAlarm(context, id, time, title, text, "course", courseAlarmFile);
    }

    public static boolean scheduleAssessmentAlarm (Context context, int id, long time, String title, String text) {
        return _scheduleAlarm(context, id, time, title, text, "assessment", assessmentAlarmFile);
    }

    public static boolean scheduleTermAlarm (Context context, int id, long time, String title, String text) {
        return _scheduleAlarm(context, id, time, title, text, "term", termAlarmFile);
    }

    private static int getNextAlarmId (Context context) {
        SharedPreferences alarmPrefs;
        alarmPrefs = context.getSharedPreferences(alarmFile, Context.MODE_PRIVATE);
        int nextAlarmId = alarmPrefs.getInt(nextAlarmField, 1);
        return nextAlarmId;
    }

    private static void incrementNextAlarmId (Context context) {
        SharedPreferences alarmPrefs;
        alarmPrefs = context.getSharedPreferences(alarmFile, Context.MODE_PRIVATE);
        int nextAlarmId = alarmPrefs.getInt(nextAlarmField, 1);
        SharedPreferences.Editor alarmEditor = alarmPrefs.edit();
        alarmEditor.putInt(nextAlarmField, nextAlarmId + 1);
        alarmEditor.commit();
    }

    private static int getAndIncrementNextAlarmId (Context context) {
        int nextAlarmId = getNextAlarmId(context);
        incrementNextAlarmId(context);
        return nextAlarmId;
    }

    private void createNotificationChannel(Context context, String CHANNEL_ID) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Customize message for start and end dates
    // Same receiver class but custom messages for the assessments as well
    // !!! instead of making a new numAlerts use courseEditorActivity.numAlerts
    // same numAlerts for all of them start by 0 but increments number

    // talk about my troubles with the alerts and why and what I had to do to fix.....
    // what would I do differnt for fragments on phones vs. tablet.
    // fragments allow you to place fragments differently based on screen
}

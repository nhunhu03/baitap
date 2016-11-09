package com.example.sms_scheduler;



import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Broad_cast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Intent i=new Intent(arg0,My_Service.class);
		PendingIntent	  pendingIntent = PendingIntent.getService(arg0, 0, i, 0);
		final Calendar time = Calendar.getInstance();
	//	time.set(Calendar.MINUTE, 0);
		//time.set(Calendar.SECOND, 0);
		//time.set(Calendar.MILLISECOND, 0);
		AlarmManager alarmManager = (AlarmManager)arg0.getSystemService(Context.ALARM_SERVICE);
		//alarmManager.set(AlarmManager.RTC, 1000,pendingIntent );
		alarmManager.setRepeating(AlarmManager.RTC, time.getTime().getTime()
                , 1000, pendingIntent);
	//	arg0.startService(i);
		
		
	}

}

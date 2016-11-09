package com.example.sms_scheduler;

import android.os.Bundle;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.view.Menu;

public class Notification extends Activity {

    public Notification(int icLauncher, String string, long currentTimeMillis) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
      // nm.cancel(1000);
      // nm.cancelAll();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_notification, menu);
        return true;
    }

	public void setLatestEventInfo(Add_sms add_sms, String string,
			String string2, PendingIntent i) {
		// TODO Auto-generated method stub
		
	}
}

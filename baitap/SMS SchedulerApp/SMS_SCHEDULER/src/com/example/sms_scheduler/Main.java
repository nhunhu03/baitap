package com.example.sms_scheduler;







import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint({ "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast", "ShowToast" })
public class Main extends Activity {
ImageView m1,m2,m3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m1=(ImageView)findViewById(R.id.m1);
        m2=(ImageView)findViewById(R.id.m2);
        m3=(ImageView)findViewById(R.id.m3);
    	Intent i=new Intent(this,My_Service.class);
		PendingIntent	  pendingIntent = PendingIntent.getService(this, 0, i, 0);
		final Calendar time = Calendar.getInstance();
	//	time.set(Calendar.MINUTE, 0);
		//time.set(Calendar.SECOND, 0);
		//time.set(Calendar.MILLISECOND, 0);
		AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
		//alarmManager.set(AlarmManager.RTC, 1000,pendingIntent );
		alarmManager.setRepeating(AlarmManager.RTC, time.getTime().getTime()
                , 60000, pendingIntent);
 
     // startService(i);
       
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
   
   public void onclick(View v)
    {
    	
    	switch(v.getId())
    	{
    	case R.id.m1:
    		Intent i=new Intent(this,Add_sms.class);
    		startActivity(i);
    		
    	Toast.makeText(getApplicationContext(), "Add SMS..", Toast.LENGTH_LONG).show();
    	break;
    	case R.id.m2:
    		Intent j=new Intent(this,Sett_sms.class);
    		startActivity(j);
    		Toast.makeText(getApplicationContext(), "Scheduled SMS list..", Toast.LENGTH_LONG).show();
    		break;
    	case R.id.m3:
    		//Toast.makeText(getApplicationContext(), "Setting App..", 1000).show();
    		Intent jx=new Intent(this,List.class);
    		startActivity(jx);
    		Toast.makeText(getApplicationContext(), "Scheduled SMS list..", Toast.LENGTH_LONG).show();
    		break;
    	
    	}  		
    }
   public boolean onOptionItemSelect(MenuItem men)
   {
	
   	 switch(men.getItemId())
   	 {
   	 case R.id.item1:
       Intent ii=new Intent(this,Contact_backup.class);
    	this.startActivity(ii);
		return true;
   	 
   	 }
	return true;
	 
	
   }
}

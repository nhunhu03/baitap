package com.example.sms_scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.widget.Toast;

public class My_Service extends Service  {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
		
	}
	public void onStart(Intent i,int id)
	{
		Toast.makeText(getApplicationContext(), "Service Start", 1000).show();
		
	}
	public void fetno()
	{
		try
		{
		SQLiteDatabase sqldb;
		sqldb=openOrCreateDatabase("sms", SQLiteDatabase.CREATE_IF_NECESSARY, null);
	String columns[]={"nm","msg","dt","tm","dtm"};
		
		Cursor c=sqldb.query("schedul",columns, null, null, null, null, null);
		c.moveToFirst();
		Date cdate=new Date();
		
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		cdate=dformat.parse(cdate.toString() );
		while(c.moveToNext())
		{
			if(cdate.getTime()==Long.parseLong(c.getString(4)))
			{
				Toast.makeText(this, "found",10000).show();
			}
			
		}
		}
		catch(Exception e)
		{
			Toast.makeText(this, e.toString(), 10000).show();
		}
		
	}

}

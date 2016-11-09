package com.example.sms_scheduler;





import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Sett_sms extends Activity implements OnLongClickListener {
EditText t1,t2,t3,t4;
ImageView bt;
SQLiteDatabase databas;
Intent i;
//Button btnCalendar, btnTimePicker;

	private int mYear, mMonth, mDay, mHour, mMinute;
 
String qry="create table if not exists schedul(nm text,msg text,dt text,tm text,dtm integer)";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sett_sms);
        databas=openOrCreateDatabase("sms", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        databas.execSQL(qry);
        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.t2);
        t3=(EditText)findViewById(R.id.t3);
        t4=(EditText)findViewById(R.id.t4);
       
       bt=(ImageView)findViewById(R.id.save);
      t2.setOnLongClickListener(new View.OnLongClickListener() {
		
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.t2)
			{
				//temp_intent();
				return true;
			}
			
			Date d1=new Date();
			
			return true;
		}
	});
      
      
        }
        
      
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sett_sms, menu);
        return true;
    }
   
    public void lg()
    {
    	Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    	startActivityForResult(intent, 1000);
    }
    public void temp_intent()
    {
    	Intent t=new Intent(this,Mesage_tempt.class);
    	startActivity(t);
    }
	public boolean onLongClick(View arg0) {
		//lg();
		String dateTimeKey = "com.example.app.datetime";
		return true;
	}
	public void ondate(View v)
	{
		final Dialog dialog = new Dialog(this);
		  //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		    dialog.setContentView(R.layout.dialog_view2);
		   // final EditText t3 = (EditText)dialog.findViewById(R.id.t3);
		    final     DatePicker dp1=(DatePicker)dialog.findViewById(R.id.datePicker1);
		   
		 t3.setOnClickListener(new View.OnClickListener() {
		        
		        public void onClick(View v) {
		            // TODO Auto-generated method stub
		        //Button b1=(Button)findViewById(R.id.button1);
		   
		      t3.setText(""+dp1.getYear());
		        
		           dialog.dismiss();
		        }
		    });
		    dialog.show();
	}
	
	 public void ondateset(View v)
	 {
		 final Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);

         // Launch Date Picker Dialog
         DatePickerDialog dpd = new DatePickerDialog(this,
                 new DatePickerDialog.OnDateSetListener() {

                     public void onDateSet(DatePicker view, int year,
                             int monthOfYear, int dayOfMonth) {
                         // Display Selected date in textbox
                         t3.setText(dayOfMonth + "-"
                                 + (monthOfYear + 1) + "-" + year);

                     }
                 }, mYear, mMonth, mDay);
         dpd.show();
         
	} 
	 public void onsettime(View v)
	 {
		 final Calendar c = Calendar.getInstance();
         mHour = c.get(Calendar.HOUR_OF_DAY);
         mMinute = c.get(Calendar.MINUTE);

         // Launch Time Picker Dialog
         TimePickerDialog tpd = new TimePickerDialog( this,
                 new TimePickerDialog.OnTimeSetListener() {
  
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						 
						String am_pm="";
						t4.setText(""+hourOfDay);
						if(hourOfDay>=12)
							am_pm="PM";
						//t4.setText(hourOfDay + ":" + minute+" "+am_pm);
						else 
							am_pm="AM";
							
						t4.setText(hourOfDay + ":" + minute+" "+am_pm);
						
					}

						
                 }, mHour, mMinute, true);
        
         tpd.show();
	 }
	
	public void clr()
	{
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
	}
	public void onclick(View v)
	{
		
	
		if(t1.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Empty No", 1000).show();
		}
		else if(t2.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Empty..", 1000).show();
		}
		else if(t3.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Empty..", 1000).show();
		}
		else if(t4.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Empty..", 1000).show();
		}
		else
		{
		try
		{
			String smsdate=t3.getText().toString()+" "+  t4.getText().toString().substring(0, 5);
			
			SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date1=dformat.parse(smsdate);
		
			
			
			databas.execSQL("insert into schedul values('"+t1.getText().toString()+"','"+t2.getText().toString()+"','"+t3.getText().toString()+"','"+t4.getText().toString()+"',"+date1.getTime()+")");
	    	Toast.makeText(getApplicationContext(), "save", 1000).show();
            clr();
           
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.toString(), 1000).show();
		}
		}
			
		//DATA STORE IN SHARD PREFENCES FILE
		 /*SharedPreferences sp=getSharedPreferences("data", Activity.MODE_PRIVATE);
			edt.putString("mobile", t1.getText().toString());
		edt.putString("msg", t2.getText().toString());
		edt.putString("date", t3.getText().toString());
		edt.putString("time", t4.getText().toString());
		edt.commit();
		Toast.makeText(getApplicationContext(), "Setting Successfully", 1000).show(); */
		
	}
}
package com.example.sms_scheduler;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add_sms extends Activity implements  OnLongClickListener,OnClickListener {
 private static final int NOTIFY_ME_ID = 0;
EditText no,msg;
 ImageView cat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sms);
        msg=(EditText)findViewById(R.id.msg);
        no=(EditText)findViewById(R.id.no);
      // no.setOnLongClickListener((OnLongClickListener) this);
      //  **************************************************************************
      
        
        
        
        
        
        
      //  ************************************************************************
       msg.setOnLongClickListener(this);
       Intent b=getIntent();
       String str=b.getStringExtra("templet");
       msg.setText(str);
    }

    public void onreset(View v)
	{
		msg.setText("");
		no.setText("");
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_sms, menu);
        return true;
        
    }
public void lg()
{
	
}
public void temp_intent()
{
	Intent t=new Intent(this,Mesage_tempt.class);
	startActivity(t);
}

	public boolean onLongClick(View arg0) {
		
		switch(arg0.getId())
		{
		case R.id.no:
		
			
			break;
		case R.id.msg:
			temp_intent();
			break;
		}
		return true;
	} 
	public void oncat(View v)
	{
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		   intent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
			startActivityForResult(intent, 1000);
	}
	@SuppressWarnings("deprecation")
	public void onclick(View v)
	{
		if(no.getText().toString().equals(""))	
			
		{
			Toast.makeText(getApplicationContext(), "Number Not Exist", 5000).show();
		}
		else if(msg.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Empty Message Not Send", 5000).show();
		}
		else
		{
		SmsManager sm=SmsManager.getDefault();
		Intent noti= new Intent(this,Notification.class);
		noti.putExtra("Mobile NO",no.getText().toString());
		PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, noti, 0);
		
	sm.sendTextMessage(no.getText().toString(), null, msg.getText().toString(), null, pendingIntent);
    Toast.makeText(getApplicationContext(), "Send SMS", 1000).show();
	NotificationManager  nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	NotificationCompat.Builder nc=new NotificationCompat.Builder(getApplicationContext());
	nc.setTicker("my msg");
	nc.setSmallIcon(R.drawable.ic_launcher);
	nc.setContentTitle("new notification");
	nc.setContentText(no.getText().toString());
	Intent i=new Intent(this,Notification.class);
	PendingIntent pi = PendingIntent.getActivity(this,0,i,0);
	nc.setContentIntent(pi);
   android.app.Notification n=nc.build();
   n.number+=2;
   nm.notify(1000,n);
		}
	/**Notification n=nc.build();
	n.number+=2;

	nm.notify(1000, n); **/
	}
	public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
		// TODO Auto-generated method stub
		 
		 String str=(String) parent.getItemAtPosition(pos);
		 Toast.makeText(getApplicationContext(), str, 1000).show();
		 
		
	}
	
	protected void onActivityResult(int requestCode,int resultCode, Intent data)
	{
		if (requestCode == 1000) {
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	            // Get the URI that points to the selected contact
	            Uri contactUri = data.getData();
	            // We only need the NUMBER column, because there will be only one row in the result
	            String[] projection = {Phone.NUMBER};

	            // Perform the query on the contact to get the NUMBER column
	            // We don't need a selection or sort order (there's only one result for the given URI)
	            // CAUTION: The query() method should be called from a separate thread to avoid blocking
	            // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
	            // Consider using CursorLoader to perform the query.
	            Cursor cursor = getContentResolver()
	                    .query(contactUri, projection, null, null, null);
	            cursor.moveToFirst();

	            // Retrieve the phone number from the NUMBER column
	            int column = cursor.getColumnIndex(Phone.NUMBER);
	            String number = cursor.getString(column);
	            Toast.makeText(getApplicationContext(), number, 5000).show();
                no.setText(number.toString());
	            // Do something with the phone number...
	        }
		
		}
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	}
	

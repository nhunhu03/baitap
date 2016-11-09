package com.example.contactharvester;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	String  Name="";
	String Number="";
	private EditText editText1;
	private EditText foldername;
	private Button button1;
	private Button button2;
	Spinner filetype;
	TextView tv;
	String all ="";
	private EditText toEmail;
	private EditText subject;
	private EditText emailBody;
	private int seekR;
	SeekBar redSeekBar;
	LinearLayout mScreen;
	public void datasaver(String a , String filename , String Filetype ,String foldername) {
	    // write on SD card file data in the text box
	    try {
	        File newFolder = new File(Environment.getExternalStorageDirectory(), foldername);
	        if (!newFolder.exists()) {
	            newFolder.mkdir();
	        }
	        try {
	            File file = new File(newFolder, filename +"."+ Filetype);
	            file.createNewFile();
	            FileOutputStream fos;
	            fos = new FileOutputStream(file);
	            byte[] data = a.getBytes();
	            fos.write(data);
	            fos.flush();
	            fos.close();
	        } catch (Exception ex) {
	            System.out.println("ex: " + ex);
	            Toast.makeText(this, "ex: " + ex, Toast.LENGTH_LONG).show();
	        }
	    } catch (Exception e) {
	        System.out.println("e: " + e);
	        Toast.makeText(this, "e: " + e, Toast.LENGTH_LONG).show();
	    }
	   
	}
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tv = (TextView) findViewById(R.id.textView1);
        filetype  = (Spinner) findViewById(R.id.spinner1);
    	button1 = (Button)findViewById( R.id.button1 );
    	button2 = (Button)findViewById( R.id.sendEmail );
    	foldername = (EditText)findViewById(R.id.foldername);
    	toEmail = (EditText)findViewById( R.id.toEmail );
		subject = (EditText)findViewById( R.id.subject );
		emailBody = (EditText)findViewById( R.id.emailBody );
		redSeekBar = (SeekBar) findViewById(R.id.mySeekingBar_R);
		redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
		mScreen = (LinearLayout) findViewById(R.id.tab1);
		button1.setOnClickListener( this );
    	button2.setOnClickListener( this );
    	 List<String> list = new ArrayList<String>();
         list.add("txt");
         list.add("csv");
         list.add("sql");
         
         ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
         (this, android.R.layout.simple_spinner_item,list);
          
         dataAdapter.setDropDownViewResource
         (android.R.layout.simple_spinner_dropdown_item);
          
    	filetype.setAdapter(dataAdapter);
    	
        
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Contact List");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Export Contacts");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Export Contacts");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Share");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Share");
        host.addTab(spec);
        
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
        Name=Name+"\n"+phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        Number=Number+"\n"+phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
      }
        
        String [] namer = Name.split("\n");
        String [] phoneno = Number.split("\n");
        
        String [] alls = new HashSet<String>(Arrays.asList(namer)).toArray(new String[0]);
        for (int i = 1; i < namer.length; i++) {
			
        	  all = all+"Name : "+namer[i]+"\n"+"Number : "+phoneno[i]+"\n\n";
        }
        
        //Delete Duplicates
        int numDuplicates = namer.length - alls.length;	
        
        tv.setText("Total Duplicates Found : "+numDuplicates+"\n\nContacts : \n\n"+all);
       emailBody.setText(all);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		tv = (TextView) findViewById(R.id.textView1);
		editText1 = (EditText)findViewById(R.id.editText1);
		filetype  = (Spinner) findViewById(R.id.spinner1);
		foldername = (EditText)findViewById(R.id.foldername);
		switch (arg0.getId()) {
		case R.id.button1:
			try {
				datasaver(tv.getText().toString(),  editText1.getText().toString() , String.valueOf(filetype.getSelectedItem()) , foldername.getText().toString());
				Toast.makeText(this,  "Saved Contacts In "+foldername.getText().toString()+" Folder >> "+editText1.getText().toString()+"."+String.valueOf(filetype.getSelectedItem()), Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "Error ! \n"+e.toString(), Toast.LENGTH_LONG).show();
				
			}
			break;

		case R.id.sendEmail:
		      Intent emailIntent = new Intent(Intent.ACTION_SEND);
		      
		      emailIntent.setData(Uri.parse("mailto:"));
		      emailIntent.setType("text/plain");
		      emailIntent.putExtra(Intent.EXTRA_EMAIL, toEmail.getText().toString());
		      emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
		      emailIntent.putExtra(Intent.EXTRA_TEXT, "[By Contact Harvester ] \n"+emailBody.getText().toString()+"\n\nDDevelopers\nHAT(Inc)");
		      
		      try {
		         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		         finish();
		        
		      }
		      catch (android.content.ActivityNotFoundException ex) {
		         Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
		      }
			break;
		}
	}
    
	private SeekBar.OnSeekBarChangeListener seekBarChangeListener
	= new SeekBar.OnSeekBarChangeListener()
	{

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
	  boolean fromUser) {
	// TODO Auto-generated method stub
	 updateBackground();
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	// TODO Auto-generated method stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	// TODO Auto-generated method stub
	}
	};

	private void updateBackground()
	{
		Random rn = new Random();
	 seekR = redSeekBar.getProgress();
	 mScreen.setBackgroundColor(
	  0xff000000
	  + seekR * 0x1000+rn.nextInt()
	
	  );
	}
}

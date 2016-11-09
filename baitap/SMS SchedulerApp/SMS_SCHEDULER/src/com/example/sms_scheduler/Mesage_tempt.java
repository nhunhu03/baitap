package com.example.sms_scheduler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Mesage_tempt extends Activity  {
  ListView l;
  Intent i;
  ArrayAdapter<Object> aa;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesage_tempt);
       l=(ListView)findViewById(R.id.lst);
      i =new Intent(this,Add_sms.class);
       load();
       l.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View v, int posn,
				long id) {
		
		}
	});
    }

public void load()
{
	
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mesage_tempt, menu);
        return true;
    }

	

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	
}

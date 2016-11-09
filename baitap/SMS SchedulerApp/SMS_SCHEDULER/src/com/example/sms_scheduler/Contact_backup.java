package com.example.sms_scheduler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Contact_backup extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_backup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_contact_backup, menu);
        return true;
        
    }
}

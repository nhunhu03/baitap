package com.example.sms_scheduler;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
public class List extends Activity {
//ImageView b;
SQLiteDatabase db;

//String qry="create table if not exists schedul(nm text,msg text,dt text,tm text)";
//String t1="ram",t2="raj",t3="rohan",t4="mohan";    
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
      //  b=(ImageView)findViewById(R.id.b);
        
    // #######################################################################  
        db=openOrCreateDatabase("sms", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        
 TableLayout tl=(TableLayout)findViewById(R.id.tbl); 
		
		
		try
		{
			
	//	t3.setText("");
		String columns[]={"nm","msg","dt","tm","dtm"};
		
		Cursor c=db.query("schedul",columns, null, null, null, null, null);
		c.moveToFirst();
		do
		{
		//	t3.append(c.getString(0)+" "+c.getString(1)+"\n");
			TableRow tr=new TableRow(this);
			//tr.setBackgroundColor(Color.BLACK);
			
			TextView tv1=new TextView(this);
			TextView tv2=new TextView(this);
			TextView tv3=new TextView(this);
			TextView tv4=new TextView(this);
			tv1.setText("        "+c.getString(0));
			tv2.setText("        "+c.getString(1));
			tv3.setText("        "+c.getString(2));
			tv4.setText("        "+c.getString(3));
			tr.addView(tv1);
			tr.addView(tv2);
			tr.addView(tv3);
			tr.addView(tv4);
			tl.addView(tr);
			
		}
		while(c.moveToNext());
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.toString(), 1000).show();
		}
        
        
        
        
        
      /* db.execSQL(qry);
        databas.execSQL("insert into schedul  values('"+t1+"','"+t2+"','"+t3+"','"+t4+"')");
    	Toast.makeText(getApplicationContext(), "save", 1000).show(); */
      
    }
public void getdata()
{
	//TextView tv = (TextView) findViewById(R.id.textView3);
	//EditTextet2 et = (EditText) findViewById(R.id.editText1);
	TextView  tv=new TextView(this);
	//EditText et=new EditText(this);
	

	//String name = et.getText().toString();
	String columns[]={"no","msg","dt","tm"};
	Cursor c=db.query("SELECT *FROM schedul",columns, null, null, null, null, null); 

	c.moveToNext();

	tv.setText(c.getString(c.getColumnIndex("dt"))); 

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list, menu);
        return true;
    }
    public void onclick(View v)
    {
//TableLayout tl=(TableLayout)findViewById(R.id.tb1); 
		TableLayout tl=new TableLayout(this);
		
		try
		{
			
	//	t3.setText("");
		String columns[]={"no","msg"};
		
		Cursor c=db.query("emp",columns, null, null, null, null, null);
		c.moveToFirst();
		do
		{
		//	t3.append(c.getString(0)+" "+c.getString(1)+"\n");
			TableRow tr=new TableRow(this);
			TextView tv1=new TextView(this);
			TextView tv2=new TextView(this);
			tv1.setText(c.getString(0));
			tv2.setText(c.getString(1));
			tr.addView(tv1);
			tr.addView(tv2);
			tl.addView(tr);
			
		}
		while(c.moveToNext());
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.toString(), 1000).show();
		}
     
    }
}

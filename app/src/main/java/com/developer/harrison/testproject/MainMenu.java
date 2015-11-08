package com.developer.harrison.testproject;

//import android.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainMenu extends Activity{
	
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.fancy_menu);
	  
	  

      Button next = (Button) findViewById(R.id.start);
      next.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
              Intent myIntent = new Intent(view.getContext(), InitialSetup.class);
              startActivityForResult(myIntent, 0);
          }
      });
		        Button btnExit = (Button) findViewById(R.id.exit);
		        btnExit.setOnClickListener(new View.OnClickListener() 
		{
		            public void onClick(View arg0) 
		    {
		                try 
		{
		                quit(); 
		                } 
		catch (Exception e) 
		{
		                }
		            }
		        });
		    }
		    
		    public void quit() 
		    {
		        int pid = android.os.Process.myPid();
		        android.os.Process.killProcess(pid);
		        System.exit(0);
		    }


}

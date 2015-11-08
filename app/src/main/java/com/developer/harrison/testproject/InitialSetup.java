package com.developer.harrison.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;


public class InitialSetup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_setup);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_initial_setup, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void playListener(View v) {
        Intent intent = new Intent(InitialSetup.this, MainActivity.class);

        Spinner spinner = (Spinner) findViewById(R.id.play_type);
        Switch sw = (Switch) findViewById(R.id.comp_first);
        Button button = (Button) findViewById(R.id.play_button);

        int spinnerSelection = spinner.getSelectedItemPosition();
        if (spinnerSelection == 0)
            intent.putExtra("gamesToPlay", 0+"");
        else if (spinnerSelection == 1)
            intent.putExtra("gamesToPlay", 2+"");
        else if (spinnerSelection == 2)
            intent.putExtra("gamesToPlay", 3+"");
        else
            intent.putExtra("gamesToPlay", 1+"");

        if (sw.isChecked() == true)
            intent.putExtra("compFirst", 1+"");
        else
            intent.putExtra("compFirst", 0+"");

        startActivity(intent);
    }

}

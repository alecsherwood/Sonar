package com.frontsidesmith.apps.smartmap;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Vibrator;


public class SmartMap extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_map);

        //Start Google Maps API



        //Retrieve current Location of user.
            //Continuously!


        //Retrieve current direction of user.
            //Continuously!

        //determine if they are in front of a street.


        //First alert.
            //Subtle Vibration Alert
                Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE); // Setup vibrator.
                v.vibrate(500);
        //Second Alert
                v.vibrate(500);
                v.vibrate(1000);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smart_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

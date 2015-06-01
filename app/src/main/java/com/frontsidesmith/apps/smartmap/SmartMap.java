package com.frontsidesmith.apps.smartmap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,
                1, mLocationListener);

        //Retrieve current direction of user.
            //Continuously!

        //determine if they are in front of a street.


            //Subtle Vibration Alert
                Vibrator v; // Setup vibrator.
                 v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500); // First alert
        /* Second Alert */
                v.vibrate(500); //Second alert
                v.vibrate(1000);


    }


    //Location Listener
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
                //Leverage Google Maps.
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };



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

package com.frontsidesmith.apps.smartmap;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SmartMap extends ActionBarActivity {

    private Button pushToTalk;
    private Button pushForLocation;

    private TextView speechToText;
    private TextToSpeech tts;

    private String locationName = null;
    private String fullAddress = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_map);

        //Start Google Maps API




        //Retrieve current Location of user.
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //Location Listener
        final LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {

                LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                String provider = locationManager.getBestProvider(new Criteria(), true);

                Location locations = locationManager.getLastKnownLocation(provider);
                if(null!=locations){
                    double longitude = locations.getLongitude();
                    double latitude = locations.getLatitude();
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
                        if(null!=listAddresses&&listAddresses.size()>0){
                            String _Location = listAddresses.get(0).getAddressLine(0);
                            locationName = _Location;
                            fullAddress = locationName + listAddresses.get(0).getAddressLine(1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
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

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, mLocationListener);





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



        //Speech Recognition
            pushToTalk = (Button) findViewById(R.id.push_to_talk); //Grabs button from XML

            pushToTalk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-us");

                    try{
                        startActivityForResult(intent, 1);
                    }
                    catch(ActivityNotFoundException e){
                        //Device not supported
                        Toast toast = Toast.makeText(getApplicationContext(), "This Device is does not support Speech to Text", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });


        //Push for location
            pushForLocation = (Button) findViewById(R.id.location_button);

            pushForLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(locationName != null){
                        //Text To Speech
                        tts = new TextToSpeech(SmartMap.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int status) {
                                if(status != TextToSpeech.ERROR){
                                    tts.setLanguage(Locale.US);
                                    if(locationName != null){
                                        speakText(locationName);
                                    }
                                }

                            }
                        });

                    }
                }
            });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1: {
                if(data != null) {
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    speechToText = (TextView) findViewById(R.id.text_to_speech_text_view);

                    final String capturedText = text.get(0);

                    speechToText.setText(capturedText);

                     //Text To Speech
                        tts = new TextToSpeech(SmartMap.this, new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int status) {
                                if(status != TextToSpeech.ERROR){
                                    tts.setLanguage(Locale.US);
                                        speakText(capturedText);
                                }

                            }
                        });
                }
            }
        }
    }

    public void speakText(String text){
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }





   @Override
    protected void onPause() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }

       super.onPause();

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

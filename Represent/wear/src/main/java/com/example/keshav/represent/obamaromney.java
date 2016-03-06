package com.example.keshav.represent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class obamaromney extends Activity {
    public String zipCodeEntry;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.election_main);
// ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        Log.d("obamaromney", "setting up shake detector");
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Log.d("shake_test", "onShake called");
                TextView obamaStat = (TextView) findViewById(R.id.textView5);
                TextView romneyStat = (TextView) findViewById(R.id.textView6);
                int romneynum = (int) (Math.random() * 100);
                int obamanum = 100 - romneynum;
                obamaStat.setText(Integer.toString(obamanum) + "%");
                romneyStat.setText(Integer.toString(romneynum) + "%");
                TextView citystat = (TextView) findViewById(R.id.textView7);
                citystat.setText("Calhoun County, Alabama");
            }
        });

        Intent wintent = getIntent();
        Bundle extras = wintent.getExtras();
        Log.d("obamaromney", "extras: " + extras);
        if (extras != null) {
            zipCodeEntry = extras.getString("ZIP_CODE");
            Log.d("obamaromney", "zipCodeEntry: " + zipCodeEntry);
            TextView obamaStat = (TextView) findViewById(R.id.textView5);
            TextView romneyStat = (TextView) findViewById(R.id.textView6);
            Integer romneyNewStat = Integer.parseInt(zipCodeEntry) % 100;
            Integer obamaNewStat = 100 - romneyNewStat;
            Log.d("obamaromney", "romney: " + romneyNewStat);
            Log.d("obamaromney", "obama: " + obamaNewStat);
            obamaStat.setText(Integer.toString(obamaNewStat) + "%");
            romneyStat.setText(Integer.toString(romneyNewStat) + "%");
        }
    }
    public void onResume() {
        super.onResume();
        Log.d("obamaromney", "onPause");
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        Log.d("obamaromney", "onResume");
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
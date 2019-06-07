package com.example.cs541_helloworld_android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    public static ImageView nameimg;

    String[] namepics = {"english","hindi","arabic","brazilian","chinese","dutch","french","italian"};



    private float acelVal;
    private float acelLast;
    private float Shake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);

        sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);


        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        Shake = 0.00f;


        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnernames,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);









    }


    Random x = new Random();


    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {



            float x = event.values[0];
            float y = event.values[0];
            float z = event.values[0];

            acelLast = acelVal;
            acelVal = (float) Math.sqrt((double)(x*x + y*y + z*z));
            float delta = acelVal - acelLast;
            Shake = Shake * 0.9f + delta;


            if (Shake > 25)
            {
                changeimg();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };



    public void OnClick(View view)
    {
        changeimg();
    }


    public void changeimg()
    {
        int n = x.nextInt(7);
        nameimg = findViewById(R.id.imageView2);

        int id = getResources().getIdentifier("com.example.cs541_helloworld_android:drawable/" + namepics[n], null, null);


        nameimg.setImageResource(id);

        String soundname = namepics[n];

        int resID=getResources().getIdentifier(soundname, "raw",    this.getPackageName());

        MediaPlayer sound = MediaPlayer.create(getApplicationContext(),resID);


        sound.start();




    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String language = parent.getItemAtPosition(position).toString();


        nameimg = findViewById(R.id.imageView2);

        int languageid = getResources().getIdentifier("com.example.cs541_helloworld_android:drawable/" + language, null, null);


        nameimg.setImageResource(languageid);



        int resID=getResources().getIdentifier(language, "raw",    this.getPackageName());

        MediaPlayer sound = MediaPlayer.create(getApplicationContext(),resID);


        sound.start();



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

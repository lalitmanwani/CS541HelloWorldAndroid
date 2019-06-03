package com.example.cs541_helloworld_android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity{

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




    }







}

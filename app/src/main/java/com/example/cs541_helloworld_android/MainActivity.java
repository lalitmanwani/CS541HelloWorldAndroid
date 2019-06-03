package com.example.cs541_helloworld_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static ImageView nameimg;

    String[] namepics = {"english","hindi","arabic","brazilian","chinese","dutch","french","italian"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button changebutton = findViewById(R.id.button2);
        changebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                int n = x.nextInt(7);
                nameimg = findViewById(R.id.imageView2);

               int id = getResources().getIdentifier("com.example.cs541_helloworld_android:drawable/" + namepics[n], null, null);


                nameimg.setImageResource(id);


                System.out.println((n));

            }
        });
    }


    Random x = new Random();



    public void changeimg()
    {




    }







}

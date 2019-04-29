package com.x15515673.smartplant;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;


/**
 * Author : Jordan May x15515673
 * IoT Application Development Project
 *
 * This class is the main activity that handles the views in which the user wants to go to.
 */
public class MainActivity extends AppCompatActivity
{

    // Setting our variables up
    CardView pump_card;
    CardView sensors_card;
    CardView lcd_card;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Custom action bar
        ActionBar actionbar = getSupportActionBar();
        getSupportActionBar().setDisplayOptions
                (actionbar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_custom);


        pump_card = findViewById(R.id.water_pump_card);
        sensors_card = findViewById(R.id.sensors_card);
        lcd_card = findViewById(R.id.lcd_card);

        // Attaching listeners to all our cards
        pump_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                Toast.makeText(getApplicationContext(), "Pump", Toast.LENGTH_LONG).show();

                // Start Pump Activity
                Intent pump = new Intent(getApplicationContext(), WaterPumpActivity.class);
                startActivity(pump);
            }
        });
        sensors_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Sensors", Toast.LENGTH_SHORT).show();

                // Start Sensor Activity
                Intent pump = new Intent(getApplicationContext(), SensorActivity.class);
                startActivity(pump);
            }
        });

        lcd_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "LCD", Toast.LENGTH_SHORT).show();

                // Start LCD Activity
                Intent pump = new Intent(getApplicationContext(), LCDActivity.class);
                startActivity(pump);
            }
        });

    }
}

package com.x15515673.smartplant;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import org.aviran.cookiebar2.CookieBar;


/**
 * x15515673 - Jordan May
 */
public class MainActivity extends AppCompatActivity {

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

        // On Create show a welcome message
        CookieBar.build(MainActivity.this)
                .setTitle("Welcome Back")
                .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                .show();

        // Set our resources for the cards
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

            }
        });

        sensors_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Sensors", Toast.LENGTH_SHORT).show();
            }
        });

        lcd_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "LCD", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

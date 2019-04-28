package com.x15515673.smartplant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SensorActivity extends AppCompatActivity {

    // Variables
    // Toggles
    private ToggleButton temperatureToggle;
    private ToggleButton humidityToggle;
    private ToggleButton soilToggle;
    private ToggleButton lightToggle;

    // Sensor information textviews
    private TextView temperatureValue;
    private TextView humidityValue;
    private TextView soilValue;
    private TextView lightValue;

    private TextView temperatureState;
    private TextView humidityState;
    private TextView soilState;
    private TextView lightState;

    // Limiting rate
    private EditText rateLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sensors");
        setContentView(R.layout.activity_sensor);

        // Set our text views for our values
        temperatureValue = findViewById(R.id.temperatureValues);
        humidityValue = findViewById(R.id.humidityValues);
        soilValue = findViewById(R.id.soilValues);
        lightValue = findViewById(R.id.lightValues);

        // Set our state
        temperatureState = findViewById(R.id.temperatureStateVal);
        humidityState = findViewById(R.id.humidityStateVal);
        soilState = findViewById(R.id.soilStateValues);
        lightState = findViewById(R.id.lightStateVal);

        // Set positions that were saved



        // Set our toggles
        temperatureToggle = findViewById(R.id.temperatureToggle);
        humidityToggle = findViewById(R.id.humidityToggle);
        soilToggle = findViewById(R.id.soilToggle);
        lightToggle = findViewById(R.id.lightToggle);

        // Set our listeners
        temperatureToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperatureToggle.isChecked()){
                    // if checked
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    temperatureState.setText("ON");
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    temperatureState.setText("OFF");
                }
            }
        });
        humidityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (humidityToggle.isChecked()){
                    // if checked
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    humidityState.setText("ON");
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    humidityState.setText("OFF");
                }
            }
        });
        soilToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soilToggle.isChecked()){
                    // if checked
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    soilState.setText("ON");
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    soilState.setText("OFF");
                }
            }
        });
        lightToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lightToggle.isChecked()){
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    lightState.setText("ON");
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    lightState.setText("OFF");
                }
            }
        });

    }
}

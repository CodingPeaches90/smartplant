package com.x15515673.smartplant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SensorActivity extends AppCompatActivity {

    // Variables
    // Toggles
    private ToggleButton temperatureToggle;
    private ToggleButton humidityToggle;
    private ToggleButton soilToggle;

    // Sensor information textviews
    private TextView temperatureValue;
    private TextView humidityValue;
    private TextView soilValue;

    private TextView temperatureState;
    private TextView humidityState;
    private TextView soilState;

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

        // Set our state
        temperatureState = findViewById(R.id.temperatureStateVal);
        humidityState = findViewById(R.id.humidityStateVal);
        soilState = findViewById(R.id.soilStateVal);

        // Set our toggles
        temperatureToggle = findViewById(R.id.temperatureToggle);
        humidityToggle = findViewById(R.id.humidityToggle);
        soilToggle = findViewById(R.id.soilToggle);

    }
}

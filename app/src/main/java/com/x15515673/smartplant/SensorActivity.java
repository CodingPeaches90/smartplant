package com.x15515673.smartplant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Map;

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

    // Firebase
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sensors");
        setContentView(R.layout.activity_sensor);

        // Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

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

        // We need our rate limit from the input field
        rateLimit = findViewById(R.id.rateLimit);


        // Set our toggles
        temperatureToggle = findViewById(R.id.temperatureToggle);
        humidityToggle = findViewById(R.id.humidityToggle);
        soilToggle = findViewById(R.id.soilToggle);
        lightToggle = findViewById(R.id.lightToggle);

        // Set positions that were saved in firebase
        // set the temperature
        databaseReference.child("Temperature").child("Information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                String state = String.valueOf(map.get("state"));
                String previous_value = String.valueOf(map.get("message"));

                Toast.makeText(SensorActivity.this, "ddd"+state, Toast.LENGTH_SHORT).show();

                if (state.equals("ON")){
                    temperatureToggle.setChecked(true);
                    temperatureState.setText("ON");
                }else{
                    temperatureToggle.setChecked(false);
                    temperatureState.setText("OFF");
                }
                temperatureValue.setText(previous_value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // set the HUMIDITY
        databaseReference.child("Humidity").child("Information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                String state = String.valueOf(map.get("state"));
                String previous_value = String.valueOf(map.get("message"));

                Toast.makeText(SensorActivity.this, "ddd"+state, Toast.LENGTH_SHORT).show();

                if (state.equals("ON")){
                    humidityToggle.setChecked(true);
                    humidityState.setText("ON");
                }else{
                    humidityToggle.setChecked(false);
                    humidityState.setText("OFF");
                }
                humidityValue.setText(previous_value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // set the light
        databaseReference.child("Light").child("Information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                String state = String.valueOf(map.get("state"));
                String previous_value = String.valueOf(map.get("message"));

                Toast.makeText(SensorActivity.this, "ddd"+state, Toast.LENGTH_SHORT).show();

                if (state.equals("ON")){
                    lightToggle.setChecked(true);
                    lightState.setText("ON");
                }else{
                    lightToggle.setChecked(false);
                    lightState.setText("OFF");
                }
                lightValue.setText(previous_value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // set the soil
        databaseReference.child("Soil").child("Information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                String state = String.valueOf(map.get("state"));
                String previous_value = String.valueOf(map.get("message"));

                Toast.makeText(SensorActivity.this, "ddd"+state, Toast.LENGTH_SHORT).show();

                if (state.equals("ON")){
                    soilToggle.setChecked(true);
                    soilState.setText("ON");
                }else{
                    soilToggle.setChecked(false);
                    soilState.setText("OFF");
                }
                soilValue.setText(previous_value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Set our listeners
        temperatureToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long date = new Date().getTime();

                // We need our rate limit
                String ratelimit_sensor = rateLimit.getText().toString();

                // Check if something is in ratelimit
                if (!checkerLimit(ratelimit_sensor)){
                    // if rate returns false, we manuall set our edit text
                    String temp = "3";
                    rateLimit.setText(temp);
                }

                if (temperatureToggle.isChecked()){
                    // if checked
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    temperatureState.setText("ON");

                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();

                    databaseReference.child("Temperature").child("Information").child("state").setValue("ON").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    temperatureState.setText("OFF");
                    // Firebase
                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();

                    databaseReference.child("Temperature").child("Information").child("state").setValue("OFF").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        humidityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long date = new Date().getTime();
                String ratelimit_sensor = rateLimit.getText().toString();

                // Check if something is in ratelimit
                if (!checkerLimit(ratelimit_sensor)){
                    // if rate returns false, we manuall set our edit text
                    String temp = "2";
                    rateLimit.setText(temp);

                }


                if (humidityToggle.isChecked()){
                    // if checked
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    humidityState.setText("ON");
                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();
                    // Toggle in Firebase

                    databaseReference.child("Humidity").child("Information").child("state").setValue("ON").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    humidityState.setText("OFF");
                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();

                    // Firebase
                    databaseReference.child("Humidity").child("Information").child("state").setValue("OFF").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        soilToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long date = new Date().getTime();
                String ratelimit_sensor = rateLimit.getText().toString();

                // Check if something is in ratelimit
                if (!checkerLimit(ratelimit_sensor)){
                    // if rate returns false, we manuall set our edit text
                    String temp = "3";
                    rateLimit.setText(temp);

                }


                if (soilToggle.isChecked()){
                    // if checked
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    soilState.setText("ON");
                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();

                    // Soil Firebase

                    databaseReference.child("Soil").child("Information").child("state").setValue("ON").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    // Check the text view
                    soilState.setText("OFF");
                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();

                    // Firebase
                    databaseReference.child("Soil").child("Information").child("state").setValue("OFF").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        lightToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long date = new Date().getTime();
                String ratelimit_sensor = rateLimit.getText().toString();

                // Check if something is in ratelimit
                if (!checkerLimit(ratelimit_sensor)){
                    // if rate returns false, we manuall set our edit text
                    String temp = "3";
                    rateLimit.setText(temp);

                }

                if (lightToggle.isChecked()){
                    Toast.makeText(SensorActivity.this, "ON", Toast.LENGTH_SHORT).show();
                    lightState.setText("ON");
                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();

                    // Firebase

                    databaseReference.child("Light").child("Information").child("state").setValue("ON").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(SensorActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                    lightState.setText("OFF");
                    // Post to Firebase
                    final String limit = rateLimit.getText().toString();

                    // Firebase
                    databaseReference.child("Light").child("Information").child("state").setValue("OFF").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if (task.isSuccessful())
                            {
                                // Tell the user of this
                                Toast.makeText(SensorActivity.this, "Pump is on and will turn off in " + limit + 's', Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    public boolean checkerLimit(String limit)
    {
        try {
            int limit_int = Integer.parseInt(limit);
            if (limit_int > 0) {
                return true;

            } else {
                Toast.makeText(this, "The limit is too small, defaulting to 3", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        catch (NumberFormatException exception){
            Toast.makeText(this, "Dont put any strings in the limit field", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

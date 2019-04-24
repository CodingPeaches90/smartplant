package com.x15515673.smartplant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.ornach.nobobutton.NoboButton;
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

public class WaterPumpActivity extends AppCompatActivity {

    // Declare Variables

    NoboButton waterBtn;
    ToggleSwitch seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_name_pump);
        setContentView(R.layout.activity_water_pump);

        // Set final position array
        final int[] possec = new int[1];

        // Assign buttons to resources
        waterBtn = findViewById(R.id.waterbutton);
        seconds = findViewById(R.id.matchParentWidthToggleSwitch);

        // Set Default to 0 for toggle switch
        seconds.setCheckedPosition(0);

        // Toggle for seconds listener
        seconds.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] operators = getResources().getStringArray(R.array.times);
                Toast.makeText(WaterPumpActivity.this, ""+ position, Toast.LENGTH_SHORT).show();
                possec[0] = position;
            }
        });
        // Button Listener
        waterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WaterPumpActivity.this, "Pressed with seconds " + possec[0], Toast.LENGTH_SHORT).show();
            }
        });
    }
}

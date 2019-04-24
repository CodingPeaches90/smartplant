package com.x15515673.smartplant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.llollox.androidtoggleswitch.widgets.MultipleToggleSwitch;
import com.ornach.nobobutton.NoboButton;

import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

import java.util.List;

import static com.llollox.androidtoggleswitch.widgets.ToggleSwitch.*;

public class WaterPumpActivity extends AppCompatActivity {

    NoboButton waterBtn;
    ToggleSwitch seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_name_pump);
        setContentView(R.layout.activity_water_pump);

        final int[] possec = new int[1];

        waterBtn = findViewById(R.id.waterbutton);

        seconds = findViewById(R.id.matchParentWidthToggleSwitch);

        // toggle

        seconds.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] operators = getResources().getStringArray(R.array.times);
                Toast.makeText(WaterPumpActivity.this, ""+ position, Toast.LENGTH_SHORT).show();
                possec[0] = position;
            }
        });

        waterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WaterPumpActivity.this, "Pressed with seconds " + possec[0], Toast.LENGTH_SHORT).show();
            }
        });
    }
}

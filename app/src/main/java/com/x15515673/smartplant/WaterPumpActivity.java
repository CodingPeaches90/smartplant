package com.x15515673.smartplant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ornach.nobobutton.NoboButton;
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;

import java.util.Date;
import java.util.Map;

/**
 * Author : Jordan May x15515673
 *
 * This class contains the code for the Water Pump activity in which it triggers the Pump Attached
 * to the Pi.
 */
public class WaterPumpActivity extends AppCompatActivity {

    // Declare Variables
    private DatabaseReference databaseReference;
    NoboButton waterBtn;
    ToggleSwitch seconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activity_name_pump);
        setContentView(R.layout.activity_water_pump);

        // Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Set final position array
        final int[] possec = new int[1];

        // Assign buttons to resources
        waterBtn = findViewById(R.id.waterbutton);
        seconds = findViewById(R.id.matchParentWidthToggleSwitch);

        // Set Default to 0 for toggle switch
        seconds.setCheckedPosition(0);

        // Set the text just watered
        final RelativeTimeTextView vRel = findViewById(R.id.last_watered);

        databaseReference.child("Pump").child("Information").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                long val = (long) map.get("date");

                vRel.setReferenceTime(val);
            }
            // If there is a failure to read (Could be permissions however very extreme exception)
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                // Just show a toast
                Toast.makeText(getApplicationContext(), "No permission to read from Firebase DB, contact the app owner.", Toast.LENGTH_SHORT).show();
            }
        });


        // Toggle for seconds listener
        seconds.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                String[] operators = getResources().getStringArray(R.array.times);
                Toast.makeText(WaterPumpActivity.this, ""+ operators[position], Toast.LENGTH_SHORT).show();
                possec[0] = position;
            }
        });
        // Button Listener, send to firebase here
        waterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String[] operators = getResources().getStringArray(R.array.times);
                // we set the current position to the array of size one (This is a work around)
                int position = possec[0];
                int whichRate = Integer.parseInt(operators[position]);
                // Set rate to 0 for now
                String tempRate = "0";
                // multiple if's to check and assign the rate
                if (whichRate == 2)
                {
                    tempRate = "2";
                    Toast.makeText(WaterPumpActivity.this, ""+tempRate, Toast.LENGTH_SHORT).show();

                }else if (whichRate == 4)
                {
                    tempRate = "4";
                    Toast.makeText(WaterPumpActivity.this, ""+tempRate, Toast.LENGTH_SHORT).show();


                }else if (whichRate == 6)
                {
                    tempRate = "6";
                    Toast.makeText(WaterPumpActivity.this, ""+tempRate, Toast.LENGTH_SHORT).show();
                }

                long date = new Date().getTime();
                Payload payload = new Payload("on", tempRate, "non", date);
                // when the user clicks this, grab the seconds the user wants and post to firebase as a object type Payload
                final String finalTempRate = tempRate;
                databaseReference.child("Pump").child("Information").setValue(payload).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Tell the user of this
                            Toast.makeText(WaterPumpActivity.this, "Pump is on and will turn off in " + finalTempRate + 's', Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

package com.x15515673.smartplant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class LCDActivity extends AppCompatActivity {

    private Button button_last_watered;
    private Button button_current_temperature;
    private DatabaseReference databaseReference;
    private Payload payload;
    // static messages we want to send to firebase
    static String message1 = "last watered";
    static String message2 = "temperature";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("LCD Functions");
        setContentView(R.layout.activity_lcd);

        button_last_watered = findViewById(R.id.button);
        button_current_temperature = findViewById(R.id.button2);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        // When the user presses this button we wannt to set the message node on the LCD child
        button_last_watered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("LCD").child("Information").child("message").setValue(message1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Tell the user of this
                            Toast.makeText(LCDActivity.this, "Displaying last watered!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        button_current_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("LCD").child("Information").child("message").setValue(message2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Tell the user of this
                            Toast.makeText(LCDActivity.this, "Displaying last current temperature!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

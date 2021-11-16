package com.example.librarybookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CampusOverviewActivity extends AppCompatActivity
{
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://libarybookingapp-default-rtdb.europe-west1.firebasedatabase.app/");
    final DatabaseReference databaseRef = database.getReference("Campus/Magee");
    final DatabaseReference maxPCSlotsRef = databaseRef.child("maxPCSlots");
    final DatabaseReference bookedPcSlotsRef = databaseRef.child("bookedPCSlots");

    TextView txtMaxPcSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_overview);

        txtMaxPcSlots = (TextView) findViewById(R.id.txtMaxPCSlots);

        maxPCSlotsRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                int availablePCSlots;
                //Integer.valueOf(String.valueOf(dataSnapshot.getValue()));
                //availablePCSlots = Integer.valueOf(snapshot.getValue(String.class));

                String test2="";

                test2 = snapshot.getValue().toString();

                //String dpOutput = snapshot.getValue(String.class);
                //txtMaxPcSlots.setText(test2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }



}
package com.example.librarybookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CampusOverviewActivity extends AppCompatActivity
{
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://libarybookingapp-default-rtdb.europe-west1.firebasedatabase.app/");
    final DatabaseReference refCampus = database.getReference("Campus");
    TextView txtCampusName, txtPCSlots, txtNoPCSlots;
    ImageView imgOverviewBg;
    int maxPCSlots=0, maxNoPCSlots=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_overview);

        String campusID = getIntent().getStringExtra("CampusID");

        txtCampusName = findViewById(R.id.txtCampusName);
        txtPCSlots = findViewById(R.id.txtPCSlots);
        txtNoPCSlots = findViewById(R.id.txtNoPCSlots);
        imgOverviewBg = findViewById(R.id.imgOverviewBg);

        refCampus.child(campusID).child("CampusName").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                    String value = String.valueOf(task.getResult().getValue());
                    txtCampusName.setText(value + " library");

                    switch(value)
                    {
                        case "Magee":
                            imgOverviewBg.setImageResource(R.drawable.mageelibrary);
                            break;
                        case "Belfast":
                            imgOverviewBg.setImageResource(R.drawable.belfastlibrary);
                            break;
                        case "Coleraine":
                            imgOverviewBg.setImageResource(R.drawable.colerainelibrary);
                            break;
                        case "Jordonstown":
                            imgOverviewBg.setImageResource(R.drawable.jordonstownlibrary);
                    }
                }
            }
        });

        refCampus.child(campusID).child("MaxPCSlots").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                    maxPCSlots = Integer.valueOf(String.valueOf(task.getResult().getValue()));
            }
        });

        refCampus.child(campusID).child("BookedPCSlots").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                    int value = Integer.valueOf(String.valueOf(task.getResult().getValue()));
                    txtPCSlots.setText(String.valueOf(maxPCSlots- value) + " remaining out of " + String.valueOf(maxPCSlots));

                    if((maxPCSlots - value) < 40)
                        txtPCSlots.setTextColor(Color.parseColor("#DD5454"));
                    else
                        txtPCSlots.setTextColor(Color.parseColor("#006726"));
                }
            }
        });

        refCampus.child(campusID).child("MaxNoPCSlots").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                    maxNoPCSlots = Integer.valueOf(String.valueOf(task.getResult().getValue()));
            }
        });

        refCampus.child(campusID).child("BookedNoPCSlots").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                    int value = Integer.valueOf(String.valueOf(task.getResult().getValue()));
                    txtNoPCSlots.setText(String.valueOf(maxNoPCSlots - value) + " remaining out of " + String.valueOf(maxNoPCSlots));

                    if((maxNoPCSlots - value) < 40)
                        txtNoPCSlots.setTextColor(Color.parseColor("#DD5454"));
                    else
                        txtNoPCSlots.setTextColor(Color.parseColor("#006726"));
                }
            }
        });
    }

    public void overviewBookClicked(View view)
    {
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }
}
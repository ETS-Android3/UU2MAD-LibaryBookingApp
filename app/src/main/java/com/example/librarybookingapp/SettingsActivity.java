package com.example.librarybookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://libarybookingapp-default-rtdb.europe-west1.firebasedatabase.app/");
    final DatabaseReference refUser = database.getReference("User");
    TextView txtSetUsername, txtSetName;
    CheckBox chbSetSkip;
    String userID = "UID1";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        txtSetName = findViewById(R.id.txtSetName);
        Spinner spinSetCampus = (Spinner) findViewById(R.id.spnSetCampus);
        chbSetSkip = findViewById(R.id.chbSetSkip);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.campusArray, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinSetCampus.setAdapter(adapter);

        spinSetCampus.setOnItemSelectedListener(this);

        refUser.child(userID).child("Username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                   String value = String.valueOf(task.getResult().getValue());
                   txtSetUsername = findViewById(R.id.txtResetPassUsername);
                   txtSetUsername.setText("Username: " + value);
                }
            }
        });

        refUser.child(userID).child("Forename").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                    String value = String.valueOf(task.getResult().getValue());
                    txtSetName.setText("Name: " + value);
                }
            }
        });

        refUser.child(userID).child("Surname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                    String value = String.valueOf(task.getResult().getValue());
                    txtSetName.setText(txtSetName.getText() + " " + value);
                }
            }
        });

        refUser.child(userID).child("SkipSelection").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                    String value = String.valueOf(task.getResult().getValue());

                    if(value=="true")
                        chbSetSkip.setChecked(true);
                    else
                        chbSetSkip.setChecked(false);
                }
            }
        });
    }

    public void setSkipClick(View view)
    {
        chbSetSkip = findViewById(R.id.chbSetSkip);

        if(chbSetSkip.isChecked())
            refUser.child(userID).child("SkipSelection").setValue("true");
        else
            refUser.child(userID).child("SkipSelection").setValue("false");
    }

    public void setResetPassClicked(View view)
    {
        Intent intent = new Intent(this, ResetPassActivity.class);
        startActivity(intent);
    }

    public void setLogOutClicked(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l)
    {
        int item = parent.getSelectedItemPosition();

        switch (item)
        {
            case 1:
                refUser.child(userID).child("CampusID").setValue("CID1");
                break;
            case 2:
                refUser.child(userID).child("CampusID").setValue("CID2");
                break;
            case 3:
                refUser.child(userID).child("CampusID").setValue("CID3");
                break;
            case 4:
                refUser.child(userID).child("CampusID").setValue("CID4");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}



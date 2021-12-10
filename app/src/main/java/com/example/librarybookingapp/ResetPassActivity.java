package com.example.librarybookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPassActivity extends AppCompatActivity
{
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://libarybookingapp-default-rtdb.europe-west1.firebasedatabase.app/");
    final DatabaseReference refUser = database.getReference("User");
    final DatabaseReference refCampus = database.getReference("Campus");
    TextView txtResetPassUsername;
    EditText eTxtResetPass, eTxtResetNewPass, eTxtResetConfNewPass;
    String currPass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        eTxtResetPass = findViewById(R.id.eTxtResetPass);
        eTxtResetNewPass = findViewById(R.id.eTxtResetNewPass);
        eTxtResetConfNewPass = findViewById(R.id.eTxtResetConfNewPass);

        String userID = "UID1";

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

                    txtResetPassUsername = findViewById(R.id.txtResetPassUsername);
                    txtResetPassUsername.setText("Username: " + value);
                }
            }
        });

    }


    public void resetPassResetClicked(View view)
    {
        String userID = "UID1";

        refUser.child(userID).child("Password").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                {
                    currPass = String.valueOf(task.getResult().getValue());
                }
            }
        });



        if(eTxtResetPass.getText().toString().equals(currPass)) {

            if (eTxtResetNewPass.getText().toString().equals(eTxtResetConfNewPass.getText().toString()))
            {
                if (eTxtResetPass.getText().toString().length()>=8 && eTxtResetPass.getText().toString().length()<=40)
                {
                    refUser.child(userID).child("Password").setValue(eTxtResetNewPass.getText().toString());

                    Intent intent = new Intent(this, SettingsActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Passwords must be between 8 and 40 characters long", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(this, "Current Password incorrect", Toast.LENGTH_SHORT).show();




        currPass="";



    }


}
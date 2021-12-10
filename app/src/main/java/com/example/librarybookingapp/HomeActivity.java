package com.example.librarybookingapp;

import static android.content.ContentValues.TAG;
import static java.lang.Boolean.parseBoolean;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity
{
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://libarybookingapp-default-rtdb.europe-west1.firebasedatabase.app/");
    final DatabaseReference refDatabase = database.getReference("User");
    TextView txtHomeWel;
    CheckBox homeConf;
    boolean skipSelection = false;

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtHomeWel = findViewById(R.id.txtHomeWel);

        String userID ="UID1";

        refDatabase.child(userID).child("Forename").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
               {
                    String value = String.valueOf(task.getResult().getValue());

                    txtHomeWel.setText("Welcome " + value);
                }
            }
        });

        refDatabase.child(userID).child("Surname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
               {
                    String value = String.valueOf(task.getResult().getValue());

                    txtHomeWel.setText(txtHomeWel.getText() + " " + value);
                }
            }
        });

        refDatabase.child(userID).child("skipSelection").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
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
                        skipSelection = true;
                    else
                        skipSelection = false;
                }
            }
        });

        refDatabase.child(userID).child("skipSelection").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
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
                        skipSelection = true;
                    else
                        skipSelection = false;
                }
            }
        });






    }

    public void homeInfoClicked(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ulster.ac.uk/library"));
        startActivity(browserIntent);
    }

    public void homeCovidClicked(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nhs.uk/conditions/coronavirus-covid-19/"));
        startActivity(browserIntent);
    }

    public void homeBookClicked(View view)
    {
        homeConf = findViewById(R.id.chbHomeConf);

        if(homeConf.isChecked())
        {
            Intent intent;
            if(skipSelection==true)
                intent = new Intent(this, CampusOverviewActivity.class);
            else
                intent = new Intent(this, CampusSelectActivity.class);

            startActivity(intent);
        }
    }

    public void homeSettingsClicked(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
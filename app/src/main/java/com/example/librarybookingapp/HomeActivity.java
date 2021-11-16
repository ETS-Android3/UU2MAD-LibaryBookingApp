package com.example.librarybookingapp;

import static android.content.ContentValues.TAG;

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
    final DatabaseReference refDatabase = database.getReference("User/1");
    final DatabaseReference refForename = refDatabase.child("forename");
    final DatabaseReference refSurname = refDatabase.child("surname");
    final DatabaseReference refSkipSelection = refDatabase.child("SkipSelection");

    //String skipSelection;



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

        //String fullName;

        TextView txtHomeWel;

        txtHomeWel = findViewById(R.id.txtHomeWel);

        //txtHomeWel.setText("Welcome " + fullName);

        refForename.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);

                // after getting the value we are setting
                // our value to our text view in below line.
                txtHomeWel.setText("Welcome " + value);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(HomeActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

            refSurname.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    String value = snapshot.getValue(String.class);

                    txtHomeWel.append(" " +value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {
                    Toast.makeText(HomeActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                }
            });



        refSkipSelection.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String skipSelection = snapshot.getValue(String.class);


                txtHomeWel.append(" " + skipSelection);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(HomeActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
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
        CheckBox homeConf;

        homeConf = findViewById(R.id.chbHomeConf);











    }



    public void homeSettingsClicked(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

}
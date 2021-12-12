package com.example.librarybookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CampusSelectActivity extends AppCompatActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_select);
    }

    public void selectMageeClicked(View view)
    {
        String campusID="CID1";
        Intent intent = new Intent(this, CampusOverviewActivity.class);
        intent.putExtra("CampusID", campusID);
        startActivity(intent);
    }

    public void selectBelfastClicked(View view)
    {
        String campusID="CID2";
        Intent intent = new Intent(this, CampusOverviewActivity.class);
        intent.putExtra("CampusID", campusID);
        startActivity(intent);
    }

    public void selectColeraineClicked(View view)
    {
        String campusID="CID3";
        Intent intent = new Intent(this, CampusOverviewActivity.class);
        intent.putExtra("CampusID", campusID);
        startActivity(intent);
    }

    public void selectJordonstownClicked(View view)
    {
        String campusID="CID4";
        Intent intent = new Intent(this, CampusOverviewActivity.class);
        intent.putExtra("CampusID", campusID);
        startActivity(intent);
    }
}
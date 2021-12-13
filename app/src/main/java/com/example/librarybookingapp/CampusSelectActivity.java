package com.example.librarybookingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
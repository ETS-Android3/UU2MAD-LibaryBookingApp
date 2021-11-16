package com.example.librarybookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class CampusSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_select);
    }

    public void selectMageeClicked(View view)
    {
        Intent intent = new Intent(this, CampusOverviewActivity.class);
        startActivity(intent);

    }
}
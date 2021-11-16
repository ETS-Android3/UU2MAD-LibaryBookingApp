package com.example.librarybookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
{
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

        if (homeConf.isChecked()==true)
        {
            Intent intent = new Intent(this, CampusSelectActivity.class);
            startActivity(intent);
        }

    }

    public void homeSettingsClicked(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

}
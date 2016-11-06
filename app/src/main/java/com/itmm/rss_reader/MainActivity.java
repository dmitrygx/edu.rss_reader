package com.itmm.rss_reader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ButtonHandler.ButtonPressed {

    ButtonHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickHandler = new ButtonHandler(this, this);
    }

    @Override
    public void buttonPolitics() {
        Intent myIntent = new Intent(MainActivity.this, PoliticsActivity.class);

        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public void buttonBusiness() {
        Intent myIntent = new Intent(MainActivity.this, BusinessActivity.class);

        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public void buttonSport() {
        Intent myIntent = new Intent(MainActivity.this, SportActivity.class);

        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public void buttonMusic() {
        Intent myIntent = new Intent(MainActivity.this, MusicActivity.class);

        MainActivity.this.startActivity(myIntent);
    }

    @Override
    public void buttonMSDN() {
        Intent myIntent = new Intent(MainActivity.this, MSDNActivity.class);

        MainActivity.this.startActivity(myIntent);
    }
}


package com.example.vijay.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void updatesandup(View vi){
        Intent intent=new Intent(this,DataWrites.class);
        startActivity(intent);
    }
    public void registration(View vi){
        Intent intent= new Intent(this,AdminRegAnnocement.class);
        startActivity(intent);
    }
    public void uploadjnf(View view){
        Intent intent= new Intent(this,CameraActivity.class);
        startActivity(intent);
    }
}

package com.example.vijay.finalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AdminRegAnnocement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg_annocement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void Submition(View view){

        EditText COMPANY=(EditText) findViewById(R.id.extractEditText8);
        String Company=COMPANY.getText().toString();
        DatePicker OpenDATE=(DatePicker) findViewById(R.id.datePicker);

        int Day=OpenDATE.getDayOfMonth();
        int Month=OpenDATE.getMonth();
        int Year=OpenDATE.getYear();
        DatePicker CloseDATE=(DatePicker) findViewById(R.id.datePicker2);
        int CDay=CloseDATE.getDayOfMonth();
        int CMonth=CloseDATE.getMonth();
        int CYear=CloseDATE.getYear();
        dbinsert db=new dbinsert(getBaseContext());
        db.RegistrationOfCompany(Company,Day,Month,Year,CDay,CMonth,CYear);
    }

}

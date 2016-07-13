package com.example.vijay.finalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void submit(View vi){
        boolean cancel = false;
        View focusView = null;
        EditText ID=(EditText) findViewById(R.id.extractEditText);
        EditText NAME=(EditText) findViewById(R.id.extractEditText7);
        EditText Tenth=(EditText) findViewById(R.id.extractEditText2);
        EditText Puc=(EditText) findViewById(R.id.extractEditText5);
        EditText Engg=(EditText) findViewById(R.id.extractEditText6);
        String Name=NAME.getText().toString();
        String IDNumber=ID.getText().toString();
        String TENTH=Tenth.getText().toString();
        String PUC=Puc.getText().toString();
        String ENGG=Engg.getText().toString();
        if(TextUtils.isEmpty(Name)){
            NAME.setError("Enter Ur Name Here");
            cancel=true;
            focusView=NAME;
        }
        if(TextUtils.isEmpty(IDNumber)){
            ID.setError("This is Field Is Required");
            cancel=true;
            focusView=ID;
        }
        if(TextUtils.isEmpty(TENTH)){
            Tenth.setError("This is Field Is Required");
            cancel=true;
            focusView=Tenth;
        }
        if(TextUtils.isEmpty(PUC)){
            Puc.setError("This is Field Is Required");
            cancel=true;
            focusView=Puc;
        }
        if(TextUtils.isEmpty(ENGG)){
            Engg.setError("This is Field Is Required");
            cancel=true;
            focusView=Engg;
        }
        if(cancel){
            focusView.requestFocus();
        }
        else {
            Toast.makeText(getBaseContext(), IDNumber + " "+Name+" " + TENTH + " " + PUC + " " + ENGG, Toast.LENGTH_LONG).show();
            dbinsert db=new dbinsert(getBaseContext());
            db.StudentReg(IDNumber,Name,TENTH,PUC,ENGG);
        }
    }
}

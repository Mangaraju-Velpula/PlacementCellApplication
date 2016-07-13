package com.example.vijay.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class DataWrites extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String tag;
    Date date=new Date();
    String tags[]={"Updates","UpComming Drives"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_writes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(getBaseContext(),""+date,Toast.LENGTH_LONG).show();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(DataWrites.this,android.R.layout.simple_spinner_item,tags);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tag=tags[position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void logout(View vi){
        Intent intent=new Intent(getBaseContext(),MainActivity.class);
        startActivity(intent);
    }
    public void submit(View vi){
        boolean cancel = false;
        View focusView = null;
        EditText Title= (EditText) findViewById(R.id.extractEditText3);
        EditText Description = (EditText) findViewById(R.id.extractEditText4);

        String Notice_Description=Description.getText().toString();
        String Notice_Title=Title.getText().toString();

        if(TextUtils.isEmpty(Notice_Title)){
            Title.setError("This is Field Is Required");
            cancel=true;
            focusView=Title;
        }
        if (TextUtils.isEmpty(Notice_Description)) {
            Description.setError("This Field Is Required");
            cancel=true;
            focusView=Description;
        }
        if(cancel){
            focusView.requestFocus();
        }
        else{

                dbinsert db= new dbinsert(getBaseContext());
                Date today=new Date();
                String Day = today.toString();
                db.insertion(Notice_Title,Day,Notice_Description,tag);
                /*PhoneDatabase phoneDatabase= new PhoneDatabase(this);
                phoneDatabase.Open();
                String Day = date.toString();
                phoneDatabase.InsertDataUpdates(Notice_Title,Day,Notice_Description,tag);
                phoneDatabase.Close();
                Toast.makeText(getBaseContext(),"Title is "+Notice_Title+"\n"+"Description "+Notice_Description+"\n"+"Tag "+tag,Toast.LENGTH_LONG).show();
*/

        }
    }
}

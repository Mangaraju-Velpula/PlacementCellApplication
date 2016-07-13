package com.example.vijay.finalproject;

/**
 * Created by vijay on 4/6/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class dbinsert {

    public AmazonDynamoDBClient client = null;
    private String tableName ="kings";
    public Context context;
    public boolean flag=true;
    public int bit=0;
    public dbinsert(Context c) {
        context=c;
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("Access Key", "Secret Key");
        client = new AmazonDynamoDBClient(awsCreds);
        client.setRegion(Region.getRegion(Regions.US_WEST_1));
    }
    private class createTable extends AsyncTask<String,String,String>{
        protected String doInBackground(String... params) {
            logMessage("List all items"+params[0]);
            ScanRequest scanRequest = new ScanRequest().withTableName(params[0]);
            ScanResult result = client.scan(scanRequest);
            for (Map<String, AttributeValue> item : result.getItems()) {
                if(!params[0].equals("companies")) {
                    printItem(item);
                }
                else
                {
                    regItem(item);
                }
            }
            return null;
        }
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
    }
    public void call(Context c,String Name){
        tableName=Name;
        new createTable().execute(tableName);
    }

    public  void logMessage(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + " ==> " + msg);
    }

    private void regItem(Map<String, AttributeValue> attributeList) {
        String itemString = new String();
        String[] Arr=new String[7];
        int i=0;
        for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
            if (!itemString.equals(""))
                itemString += ", ";
            String attributeName = item.getKey();
            AttributeValue value = item.getValue();
            Arr[i++]=value.getS();
            itemString += attributeName
                    + ""
                    + (value.getS() == null ? "" : "=\"" + value.getS() + "\"");


        }

        logMessage(itemString);
        PhoneDatabase phoneDatabase=new PhoneDatabase(context);
        phoneDatabase.Open();

        // logMessage("Latest Date  "+Arr[2]+"  "+phoneDatabase.tableEntriesCount(Arr[3]));
        logMessage("logMessage" + Arr[0] + "," + Arr[1] + "," + Arr[2] + "," + Arr[3] + "," + Arr[4] + "," + Arr[5] + "," + Arr[6]);
        phoneDatabase.InsertRegDeatils(Arr[0], Arr[1], Arr[2], Arr[3], Arr[4], Arr[5], Arr[6]);
    }

    private void printItem(Map<String, AttributeValue> attributeList) {
        String itemString = new String();
        String[] Arr=new String[4];
        int i=0;
        for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
            if (!itemString.equals(""))
                itemString += ", ";
            String attributeName = item.getKey();
            AttributeValue value = item.getValue();
            Arr[i++]=value.getS();
            itemString += attributeName
                    + ""
                    + (value.getS() == null ? "" : "=\"" + value.getS() + "\"");


        }

        logMessage(itemString);
        PhoneDatabase phoneDatabase=new PhoneDatabase(context);
        phoneDatabase.Open();
        logMessage(Arr[0] + "," + Arr[1] + "," + Arr[2] + "," + Arr[3]);
        phoneDatabase.InsertDataUpdates(Arr[1], Arr[2], Arr[0], Arr[3]);

    }

    public void insertion(String Notice_title, String Day, String Notice_description, String tag){

        new InsertDataIntoAmazon().execute(Notice_title, Day, Notice_description, tag);
    }
    public void RegistrationOfCompany(String Company,int Day,int Month,int Year,int CDay,int CMonth,int CYear){
        Toast.makeText(context,"Open Date "+Day+" "+Month+" "+Year,Toast.LENGTH_LONG).show();
        Toast.makeText(context,"Closed Date "+CDay+" "+CMonth+" "+CYear,Toast.LENGTH_LONG).show();
        String DDay=Day+"";
        String DMonth=Month+"";
        String DYear=Year+"";
        String CCDay=CDay+"";
        String CCMonth=CMonth+"";
        String CCYear=CYear+"";
        new InsertRegistrationAnnocment().execute(Company,DDay,DMonth,DYear,CCDay,CCMonth,CCYear);
    }
    private class InsertRegistrationAnnocment extends AsyncTask<String,String,String>{

        protected String doInBackground(String... params) {
            Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();

            // Add bikes.
            item.put("companyname", new AttributeValue().withS(params[0]));
            // Size, followed by some title.
            item.put("postedday", new AttributeValue().withS(params[1]));
            item.put("postedmonth", new AttributeValue().withS(params[2]));
            item.put("postedyear", new AttributeValue().withS(params[3]));
            item.put("endedday", new AttributeValue().withS(params[4]));
            item.put("endedmonth", new AttributeValue().withS(params[5]));
            item.put("endedyear", new AttributeValue().withS(params[6]));
           tableName="companies";

            PutItemRequest itemRequest = new PutItemRequest().withTableName(
                    tableName).withItem(item);
            client.putItem(itemRequest);
            item.clear();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(context,"Updated",Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }


    }


    public void StudentReg(String IDNumber,String Name, String tenth,String puc, String engg){

        new StudentRegClass().execute(IDNumber,Name,tenth,puc,engg);
    }
    private class StudentRegClass extends AsyncTask<String,String,String>{

        protected String doInBackground(String... params) {
            //logMessage("oth index " + params[0] + " 1th index " + params[1] + " 2nd Index " + params[2] + " 3rd Index " + params[3] + " 4rd Index " + params[4] + " 5rd Index " + params[5]+" 6rd Index " + params[6]);
            Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();

            // Add bikes.
            item.put("idnumber", new AttributeValue().withS(params[0]));
            // Size, followed by some title.
            item.put("companyname", new AttributeValue().withS("Infosys"));
            item.put("graduation", new AttributeValue().withS(params[4]));
            item.put("puc", new AttributeValue().withS(params[3]));
            item.put("ssc", new AttributeValue().withS(params[2]));
            item.put("studentname", new AttributeValue().withS(params[1]));
            tableName="registrations";

            PutItemRequest itemRequest = new PutItemRequest().withTableName(
                    tableName).withItem(item);
            client.putItem(itemRequest);
            item.clear();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(context,"Updated",Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }


    }


























    private class InsertDataIntoAmazon extends AsyncTask<String,String,String>{
        protected String doInBackground(String... params) {
            logMessage("oth index " + params[0] + " 1th index " + params[1] + " 2nd Index" + params[2] + " 3rd Index " + params[3]);
            Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();

            // Add bikes.
            item.put("title", new AttributeValue().withS(params[0]));
            // Size, followed by some title.
            item.put("body", new AttributeValue().withS(params[2]));
            item.put("postedon", new AttributeValue().withS(params[1]));
            item.put("tag", new AttributeValue().withS(params[3].toLowerCase()));
            if (params[3].toLowerCase().equals("updates")) {
                tableName="updates";
            }
            else {tableName="upcommings";

            }

            PutItemRequest itemRequest = new PutItemRequest().withTableName(
                    tableName).withItem(item);
            client.putItem(itemRequest);
            item.clear();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(context,"Updated",Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
        }
    }


}


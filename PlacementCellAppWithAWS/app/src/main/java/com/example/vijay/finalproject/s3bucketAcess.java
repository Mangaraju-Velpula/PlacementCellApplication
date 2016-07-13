package com.example.vijay.finalproject;

/**
 * Created by Dileep Dora on 4/23/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class s3bucketAcess {

    AmazonS3 client;
    public Context context;
    public static String Arr[]= new String[100];
    TextView textView;
    public s3bucketAcess(Context con){
        context=con;
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJTII7XMBBG7CXCDA", "9blTMHZjsrgN2UjhMuWJ6rbMsZlEjxAsHPhdnvqf");
         client = new AmazonS3Client(awsCreds);
    }

    public void s3calling(){
        String[] Array= new String[100];
        new getS3BucketData().execute();
        Log.v("Log", "" + Arr[1]);

    }





    private class getS3BucketData extends AsyncTask<String, String, String[]> {
        @Override
        protected String[] doInBackground(String... params) {

            ListObjectsRequest listObjectsRequest=new ListObjectsRequest().withBucketName("resultsjnf").withPrefix("jnfs");
            ObjectListing objectListing;
            int i=0;
            SharedPreferences pref = context.getSharedPreferences("MyPref",1);
            SharedPreferences.Editor editor = pref.edit();
            do{



                objectListing = client.listObjects(listObjectsRequest);
                for(S3ObjectSummary objectSummary : objectListing.getObjectSummaries()){
                    System.out.println(" - " + objectSummary.getKey() + "  " +
                            "(size = " + objectSummary.getSize() +
                            ")");
                    editor.putString(i + "", objectSummary.getKey());
                    i++;
                }
                listObjectsRequest.setMarker(objectListing.getNextMarker());
            }while(objectListing.isTruncated());
            return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
           // Toast.makeText(context,"Data"+Arr[0],Toast.LENGTH_LONG).show();

            super.onPostExecute(strings);
        }
    }
}

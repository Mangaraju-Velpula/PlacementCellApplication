package com.example.vijay.finalproject;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


class SingleRow{
    String date;
    String title;
    String description;
    int img;
    SingleRow(String date,String title,String description,int img){
        this.date=date;
        this.title=title;
        this.description=description;
        this.img=img;
    }
}

public class ListViewBaseAdapter extends BaseAdapter {

    ArrayList<SingleRow> list;
    Context context;
    int fragment;
    ListViewBaseAdapter(Context c,int frag){
        context=c;
        fragment=frag;
        list = new ArrayList<SingleRow>();
        Resources res=c.getResources();
        String Message="";
        int img;
        if(frag==1) {
            PhoneDatabase phoneDatabase = new PhoneDatabase(context);
            phoneDatabase.Open();
            img=R.drawable.download;
            Cursor cursor = phoneDatabase.getDataNotice();
            cursor.moveToLast();
            for (int i = cursor.getCount(); i > 0; i--) {

                list.add(new SingleRow(cursor.getString(1), cursor.getString(3), cursor.getString(0), img));
                cursor.moveToPrevious();

            }
        }
        else {
            PhoneDatabase phoneDatabase = new PhoneDatabase(context);
            phoneDatabase.Open();
            img = R.drawable.images;
            Cursor cursor = phoneDatabase.getRegDetails();
            if (cursor != null) {
                cursor.moveToLast();
                //Toast.makeText(context, cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5) + " " + cursor.getString(6), Toast.LENGTH_LONG).show();
                for (int i = cursor.getCount(); i > 0; i--) {

                    String StartDate = cursor.getString(5) + "/"+cursor.getString(3) +"/"+ cursor.getString(0);
                    String LastDate = cursor.getString(6) +"/"+ cursor.getString(1) +"/"+ cursor.getString(2);
                    list.add(new SingleRow(StartDate, LastDate, cursor.getString(4), img));
                    cursor.moveToPrevious();
                }
            }
        }
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.single_row, parent, false);
            TextView title = (TextView) row.findViewById(R.id.textView3);
            TextView descrition = (TextView) row.findViewById(R.id.textView2);
            ImageView image = (ImageView) row.findViewById(R.id.imageView);
            TextView date = (TextView) row.findViewById(R.id.textView);
            SingleRow temp = list.get(position);
            title.setText(temp.title);
            descrition.setText(temp.description);
            image.setImageResource(temp.img);
            date.setText(temp.date);
            return row;

    }
}

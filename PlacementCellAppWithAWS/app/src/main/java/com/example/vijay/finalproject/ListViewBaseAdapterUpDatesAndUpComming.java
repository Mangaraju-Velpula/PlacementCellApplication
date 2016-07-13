package com.example.vijay.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vijay on 4/9/2016.
 */
class SingleRowUpAndUpCom{
    String date;
    String title;
    String description;
    SingleRowUpAndUpCom(String date, String title, String description){
        this.date=date;
        this.title=title;
        this.description=description;
    }
}
public class ListViewBaseAdapterUpDatesAndUpComming extends BaseAdapter {

    ArrayList<SingleRowUpAndUpCom> list;
    Context context;
    ListViewBaseAdapterUpDatesAndUpComming(Context c,int frag){
        context=c;
        list= new ArrayList<SingleRowUpAndUpCom>();
        PhoneDatabase phoneDatabase=new PhoneDatabase(context);
        phoneDatabase.Open();

        Cursor cursor= phoneDatabase.getDataUpdates();
        cursor.moveToLast();
        if(frag==2){
            cursor=phoneDatabase.getDataUpComming();
            cursor.moveToLast();
        }
        for (int i = cursor.getCount(); i > 0; i--) {

            list.add(new SingleRowUpAndUpCom(cursor.getString(1), cursor.getString(0), cursor.getString(2)));
            cursor.moveToPrevious();

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
        View row = inflater.inflate(R.layout.single_row_updates_upcomming, parent, false);
        TextView title = (TextView) row.findViewById(R.id.textView10);
        TextView descrition = (TextView) row.findViewById(R.id.textView11);
        TextView date = (TextView) row.findViewById(R.id.textView9);
        SingleRowUpAndUpCom temp = list.get(position);
        title.setText(temp.title);
        descrition.setText(temp.description);
        date.setText(temp.date);
        return row;
    }
}

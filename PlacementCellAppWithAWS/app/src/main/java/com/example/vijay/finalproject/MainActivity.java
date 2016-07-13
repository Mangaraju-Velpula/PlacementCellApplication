package com.example.vijay.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vijay.finalproject.tabs.SlidingTabLayout;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SlidingTabLayout mytabs;
    public static Context context;
    static int GlobalInteger=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mytabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mytabs.setViewPager(mViewPager);
        context=getBaseContext();

    }
    public void http(View view){
        Toast.makeText(this,"Click on",Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.admin_login) {

            Intent intent= new Intent(this,AdminLoginActivity.class);
            startActivity(intent);
        }
        if(id == R.id.sync){
            dbinsert db=new dbinsert(getBaseContext());
            db.call(getBaseContext(),"updates");
            db.call(getBaseContext(),"upcommings");
            db.call(getBaseContext(),"companies");

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String tabs[];

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs=getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabs[position];
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        ListView list;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            String Message="";
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                rootView = inflater.inflate(R.layout.notices, container, false);
                list = (ListView) rootView.findViewById(R.id.listView);
                list.setAdapter(new ListViewBaseAdapter(context,1));
                list.invalidate();



            }
           else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                rootView = inflater.inflate(R.layout.updates, container,false);
                list = (ListView) rootView.findViewById(R.id.listView);
                list.setAdapter(new ListViewBaseAdapterUpDatesAndUpComming(context,1));
                list.invalidate();

            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==3){
                rootView = inflater.inflate(R.layout.upcomming, container,false);
                list = (ListView) rootView.findViewById(R.id.listView);
                list.setAdapter(new ListViewBaseAdapterUpDatesAndUpComming(context,2));
                list.invalidate();

            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==4){
                rootView= inflater.inflate(R.layout.registration,container,false);
                list = (ListView) rootView.findViewById(R.id.listView);
                list.setAdapter(new ListViewBaseAdapter(context,2));
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(context,RegistrationForm.class);
                        startActivity(intent);
                    }
                });
                list.invalidate();
            }
            else if(getArguments().getInt(ARG_SECTION_NUMBER)==5){
                rootView= inflater.inflate(R.layout.jnfs,container,false);
                list = (ListView) rootView.findViewById(R.id.listView);
                if(GlobalInteger==0) {
                    s3bucketAcess s3bucket = new s3bucketAcess(context);
                    GlobalInteger++;
                    s3bucket.s3calling();
                }
                int i=0;
              //  Log.v("Array",""+Array);
                SharedPreferences pref = context.getSharedPreferences("MyPref",1);
                SharedPreferences.Editor editor = pref.edit();
                int size=pref.getAll().size();
                if(size!=0) {
                    final String Array[] = new String[size - 1];
                    String pavan="";
                    for(i=1;i<size;i++){
                        String str="";
                        str=pref.getString(i+"",null);
                        Array[i-1]=str;
                        pavan+=str+"\n";
                        //Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
//                    Log.v("asd",str);
                        //textView.setText("hello "+str);
                    }
                    ArrayAdapter<String> adapter= new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,android.R.id.text1,Array);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(context,"Selected is "+Array[position],Toast.LENGTH_LONG).show();
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-1.amazonaws.com/resultsjnf/"+Array[position]));
                            startActivity(browserIntent);
                        }
                    });
                }

            }
            else {
                rootView= inflater.inflate(R.layout.results,container,false);
                list = (ListView) rootView.findViewById(R.id.listView);
                if(GlobalInteger==1) {
                    s3buketAccessforresults s3bucket = new s3buketAccessforresults(context);
                    GlobalInteger++;
                    s3bucket.s3resultscalling();
                }
                int i=0;
                //  Log.v("Array",""+Array);
                SharedPreferences pref = context.getSharedPreferences("MyResults", 1);
                SharedPreferences.Editor editor = pref.edit();
                int size=pref.getAll().size();
                if(size!=0) {
                    final String Array[] = new String[size - 1];
                    String pavan = "";
                    for (i = 1; i < size; i++) {
                        String str = "";
                        str = pref.getString(i + "", null);
                        Array[i - 1] = str;
                        pavan += str + "\n";
                        //Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
//                    Log.v("asd",str);
                        //textView.setText("hello "+str);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, Array);
                    list.setAdapter(adapter);
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(context, "Selected is " + Array[position], Toast.LENGTH_LONG).show();
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://s3-us-west-1.amazonaws.com/resultsjnf/" + Array[position]));
                            startActivity(browserIntent);
                        }
                    });
                }
                //textView.setText("Results");
            }
            return rootView;
        }
    }
}

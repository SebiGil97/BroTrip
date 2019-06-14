package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class MainActivity extends Activity implements View.OnClickListener {
public static final String TAG = "BroTrip";
    //TripDataAdapter mAdapter = null;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = null;
        b = (Button) findViewById(R.id.activity_main_button_new_trip);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_main_button_test_list);
        b.setOnClickListener(this);

     //   ListView lv = (ListView) findViewById(R.id.activity_main_listView_trips);
     //   mAdapter = new TripDataAdapter(this);
     //   lv.setAdapter(mAdapter);
     //   tripData = new TripData[10];


     //   List<TripData> trip_list = new LinkedList<TripData>();


     //   final ArrayAdapter<TripData> arrayAdapter = new ArrayAdapter<TripData>
     //           (this, android.R.layout.simple_list_item_1, trip_list);

    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){
            case R.id.activity_main_button_new_trip : {
            Intent i = new Intent(this, NewTrip.class);
            startActivity(i);
            } break;
            case R.id.activity_main_button_test_list : {
             //   TripData trip = new TripData("title " + i,"car " + i);
             //   i++;
             //   trip_list.add(trip);
             //   mAdapter.notifyDataSetChanged();
                Intent i = new Intent(this, ActiveTrip.class);
                startActivity(i);

            } break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }
}

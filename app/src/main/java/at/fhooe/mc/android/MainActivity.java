package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "BroTrip";
    public int mNumberOfTrips = 0;
    TripDataAdapter adapter;



    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = null;
        b = (Button) findViewById(R.id.activity_main_button_new_trip);
        b.setOnClickListener(this);


        //---------- Dynamic List ----------
        final ListView lv = (ListView) findViewById(R.id.activity_main_listView_trips);

        adapter = new TripDataAdapter(this); // which Context and how to use the selfmade adapter

        lv.setAdapter(adapter);

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id) {
                Trip trip = (Trip) lv.getItemAtPosition(_position);
                Toast.makeText(MainActivity.this, "clicked " + trip.getTripTitle(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, ActivityActiveTrip.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){

            case R.id.activity_main_button_new_trip : {
                Intent i = new Intent(this, ActivityNewTrip.class);
                //startActivity(i);
                startActivityForResult(i, 1);
            } break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }// switch

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Trip newTrip = (Trip)data.getExtras().getSerializable("newTripResult");
                Toast.makeText(this, "added " + newTrip.getTripTitle(), Toast.LENGTH_SHORT).show();
                adapter.add(newTrip);
                adapter.notifyDataSetChanged();
            }

        }
    }//onActivityResult


}


package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static at.fhooe.mc.android.ActivityNewTrip.KEY_CAR;
import static at.fhooe.mc.android.ActivityNewTrip.KEY_TITLE;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG          = "BroTrip";
    private static final String SP_KEY      = "ActivityNewTrip_NewTrip";

    public Trip[] mTripList = new Trip[]{};




    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = null;
        b = (Button) findViewById(R.id.activity_main_button_new_trip);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_main_button_test_list);
        b.setOnClickListener(this);
        Button Load = (Button) findViewById(R.id.activity_main_button_load_test);
        b.setOnClickListener(this);


        //---------- Dynamic List ----------
        ListView lv = (ListView) findViewById(R.id.activity_main_listView_trips);

        final List<Trip> TripArrayList = new ArrayList<Trip>(Arrays.asList(mTripList));

        final TripDataAdapter adapter = new TripDataAdapter(this); // which Context and how to use the selfmade adapter


        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, TripArrayList);

        lv.setAdapter(adapter);

       final SharedPreferences sp = getSharedPreferences(SP_KEY, MODE_PRIVATE);
        Load.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { // soll automatisch funktionieren und nur einmal

                String title = sp.getString(KEY_TITLE, "undefined"); // holt sich String title
                String car = sp.getString(KEY_CAR, "undefined"); // holt sich String car
                Trip trip = new Trip(title, car);


                adapter.add(trip);
                Toast.makeText(MainActivity.this, "load " + trip.getTripTitle() + " & " + trip.getCar(), Toast.LENGTH_SHORT).show();

                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onClick(View _v) {


        switch(_v.getId()){

            case R.id.activity_main_button_new_trip : {
                Intent i = new Intent(this, ActivityNewTrip.class);
                startActivity(i);
            } break;

            case R.id.activity_main_button_test_list : {
                Intent i = new Intent(this, ActivityActiveTrip.class);
                startActivity(i);
            } break;

            default : Log.e(TAG, "unexpected ID encountered");
        }
    }
}

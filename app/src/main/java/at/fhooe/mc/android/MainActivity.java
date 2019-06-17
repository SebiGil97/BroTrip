package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "BroTrip";
    public int mNumberOfTrips = 0;
    public String[] mTripList = new String[]{};




    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = null;
        b = (Button) findViewById(R.id.activity_main_button_new_trip);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_main_button_test_list);
        b.setOnClickListener(this);


        //---------- Dynamic List ----------
        ListView lv = (ListView) findViewById(R.id.activity_main_listView_trips);

        final List<String> TripArrayList = new ArrayList<String>(Arrays.asList(mTripList));

        //TripDataAdapter adapter = new TripDataAdapter(this); // which Context and how to use the selfmade adapter

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, TripArrayList);

        lv.setAdapter(adapter);

        //TEST DATABASE
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
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

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "BroTrip";
    public int mNumberOfTrips = 0;
    public List<Trip> TripList;
    TripDataAdapter adapter;

    //Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("myTrips");


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = null;
        b = (Button) findViewById(R.id.activity_main_button_new_trip);
        b.setOnClickListener(this);

        TripList = new LinkedList<Trip>();

        //---------- Dynamic List ----------
        final ListView lv = (ListView) findViewById(R.id.activity_main_listView_trips);

        adapter = new TripDataAdapter(this); // which Context and how to use the selfmade adapter

        lv.setAdapter(adapter);

        for(int i = 0;i < TripList.size();i++){
            adapter.add(TripList.get(0));
        }

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id) {
                Trip trip = (Trip) lv.getItemAtPosition(_position);
                Toast.makeText(MainActivity.this, "clicked " + trip.getTripTitle(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, ActivityActiveTrip.class);
                i.putExtra("chosenTrip", trip);
                startActivity(i);
            }
        });

        //Restore Trip List from Firebase
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                TripList = dataSnapshot.getValue(new GenericTypeIndicator<List<Trip>>() {});
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){

            case R.id.activity_main_button_new_trip : {
                Intent i = new Intent(this, ActivityNewTrip.class);
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
                //TripList.add(newTrip);
                adapter.add(newTrip);
                adapter.notifyDataSetChanged();
            }

        }
    }//onActivityResult

    //Firebase
    @Override
    protected void onStop() {
        super.onStop();
        //Save TripList to Firebase
       // myRef.setValue(TripList); //vorübergehend auskommentiert
    }

}


package at.fhooe.mc.android.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;
import at.fhooe.mc.android.ListAdapter.TripDataAdapter;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "BroTrip";
    public List<Trip> tripList;
    TripDataAdapter adapter;

    // Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("myTrips");
    ValueEventListener downloadListener;
    //hallo


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton ib = null;
        ib = (ImageButton) findViewById(R.id.activity_main_imageButton_new_trip);
        ib.setOnClickListener(this);



        tripList = new LinkedList<Trip>();

        //myRef.addListenerForSingleValueEvent(new ValueEventListener() {
        downloadListener =new ValueEventListener() { //addListenerForSingleValueEvent

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

               List<Trip> tripListRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Trip>>() {});
                if(tripListRestore!=null){
                    tripList=tripListRestore;
                    for(int i = 0;i < tripList.size();i++){      //
                        adapter.add(tripList.get(i));  //
                        Log.i(TAG, "onScreen");
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };

        myRef.addListenerForSingleValueEvent(downloadListener);


        //---------- Dynamic List ----------
        final ListView lv = (ListView) findViewById(R.id.activity_main_listView_trips);

        adapter = new TripDataAdapter(this); // which Context and how to use the selfmade adapter

        lv.setAdapter(adapter);

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _parent, View _view, int _position, long _id) {
                Trip trip = (Trip) lv.getItemAtPosition(_position);
                Toast.makeText(MainActivity.this, "clicked " + trip.getmTripTitle(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, ActivityActiveTrip.class);
                i.putExtra("chosenTrip", trip);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){

            case R.id.activity_main_imageButton_new_trip : {
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
                Toast.makeText(this, "added " + newTrip.getmTripTitle(), Toast.LENGTH_SHORT).show();
                tripList.add(newTrip);
                myRef.setValue(tripList);   //Firebase
                adapter.add(newTrip);
                adapter.notifyDataSetChanged();
            }

        }
    }//onActivityResult

}


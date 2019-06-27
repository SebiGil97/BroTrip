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
    boolean deleteON = false;

    // Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("myTrips");
    DatabaseReference myRefPur;
    DatabaseReference myRefRef;
    ValueEventListener downloadListener;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ib = null;
        ib = (ImageButton) findViewById(R.id.activity_main_imageButton_new_trip);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_main_imageButton_delete);
        ib.setOnClickListener(this);

        tripList = new LinkedList<Trip>();

        //--------- get tripList from firebase ---------
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
                Toast.makeText(MainActivity.this, "Trip " + trip.getmTripTitle(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, ActivityActiveTrip.class);
                i.putExtra("chosenTrip", trip);
                startActivity(i);
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int _pos, long id) {
                View v = findViewById(R.id.activity_main_imageButton_delete);
                v.setVisibility(View.VISIBLE);  //makes Delete Button Visible

                //make checkbox visible
                for(int i=0; i<tripList.size(); i++){
                    tripList.get(i).setDelete(true);
                }
                deleteON=true;
                adapter.notifyDataSetChanged();
                return true;
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
            case R.id.activity_main_imageButton_delete:
                Log.i(TAG,"delete pressed");

                //removes checked items from list
                for(int i=0; i<tripList.size();i++){
                    if(tripList.get(i).isReadyDelete()){

                        //--------- remove refuelList and purchaseList from firebase ---------
                        Toast.makeText(this, tripList.get(i).getmTripTitle(),Toast.LENGTH_SHORT).show();

                        myRefRef = database.getReference(tripList.get(i).getmTripTitle() + "Refuel");
                        myRefRef.removeValue();
                        myRefPur = database.getReference(tripList.get(i).getmTripTitle() + "Purchase");
                        myRefPur.removeValue();

                        //---------- remove car ----------
                        adapter.remove(tripList.get(i));
                        tripList.remove(i);
                        i--;
                    }
                }
                adapter.notifyDataSetChanged();
                closeDeleteMode();
                myRef.setValue(tripList);
                break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }// switch

    @Override
    public void onBackPressed() {

        //remove delete Buttons
        if(deleteON){
            closeDeleteMode();
        }else{
            super.onBackPressed();
        }

        Log.i(TAG,"back");
    }

    public void closeDeleteMode(){
        for(int i=0; i<tripList.size();i++){
            tripList.get(i).setDelete(false);
            tripList.get(i).setReadyDelete(false);
        }
        deleteON=false;
        View v = findViewById(R.id.activity_main_imageButton_delete);
        v.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Trip newTrip = (Trip)data.getExtras().getSerializable("newTripResult");
                tripList.add(newTrip);
                myRef.setValue(tripList);   //saves to Firebase
                adapter.add(newTrip);
                adapter.notifyDataSetChanged();
            }

        }
    }//onActivityResult

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Destroy");
        myRef.setValue(tripList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(deleteON){
            closeDeleteMode();
        }
        //remove delete Buttons

    }
}


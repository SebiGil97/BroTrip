package at.fhooe.mc.android.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.widget.Toast;
import at.fhooe.mc.android.*;
import at.fhooe.mc.android.ListAdapter.PersonAdapter;
import at.fhooe.mc.android.R;
import com.google.firebase.database.*;

import static at.fhooe.mc.android.Activities.MainActivity.TAG;

public class ActivityPersons extends Activity {
    Trip currentTripFirebase;
    Trip currentTrip;
    List<Trip> tripList;
    List<Person> persons;
    List<Refuel> refuelList;
    List<Purchase> purchaseList;

    //firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefTrip;
    ValueEventListener tripListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        currentTrip = (Trip) getIntent().getExtras().getSerializable("persons");
      /*  refuelList = (List<Refuel>) getIntent().getExtras().getSerializable("refuelList");
        purchaseList = (List<Purchase>) getIntent().getExtras().getSerializable("purchaseList");

        for(int i=0;i<currentTrip.getmPersons().size();i++){
            currentTrip.getmPersons().get(i).setmExpenditures(0);
            currentTrip.getmPersons().get(i).setmExpPurchase(0);
            currentTrip.getmPersons().get(i).setmExpRefuel(0);
            currentTrip.getmPersons().get(i).setmNumberRefuel(0);
            currentTrip.getmPersons().get(i).setmNumberPurchase(0);

            for(int y=0;y<refuelList.size();y++){
                if(refuelList.get(y).getmPayer().equals(currentTrip.getmPersons().get(i).getmName())){
                    currentTrip.getmPersons().get(i).addRefuel(refuelList.get(y));
                }
            }
            for(int y=0;y<purchaseList.size();y++){
                if(purchaseList.get(y).getmPayer().equals(currentTrip.getmPersons().get(i).getmName())){
                    currentTrip.getmPersons().get(i).addPurchase(purchaseList.get(y));
                }
            }

        } */
/*
        tripList = new LinkedList<Trip>();
        myRefTrip = database.getReference("myTrips");

        //firebase Trip
        tripListener = new ValueEventListener() { //addListenerForSingleValueEvent
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Trip> tripListRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Trip>>() {});
                if(tripListRestore!=null){
                    tripList=tripListRestore;
                }

                for(int i = 0;i < tripList.size();i++){
                    if(tripList.get(i).getTripTitle().equals(currentTrip.getTripTitle())){
                        currentTripFirebase = tripList.get(i);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        };
        myRefTrip.addListenerForSingleValueEvent(tripListener);
*/
        ListView lv = (ListView) findViewById(R.id.activity_persons_listView);

        PersonAdapter adapter = new PersonAdapter(this);

        persons = currentTrip.getmPersons();

        for(int i = 0;i < persons.size();i++) {
            adapter.add(persons.get(i));
        }

        lv.setAdapter(adapter);
    }
}

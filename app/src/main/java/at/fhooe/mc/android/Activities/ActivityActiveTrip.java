package at.fhooe.mc.android.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import at.fhooe.mc.android.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ActivityActiveTrip extends Activity implements View.OnClickListener {
    private static final String TAG = "BroTripActiveTrip";

    Trip currentTripFirebase;
    Trip currentTrip;
    List<Purchase> purchaseList;
    List<Refuel> refuelList;
    List<Trip> tripList;
    float maxMileage;

    //firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefTrip;
    ValueEventListener tripListener;
    DatabaseReference myRefPurchase;
    ValueEventListener purchaseListener;
    DatabaseReference myRefRefuel;
    ValueEventListener refuelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_trip);


        ImageButton ib = null;
        ib = (ImageButton) findViewById(R.id.activity_active_trip_imageButton_shopping);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_active_trip_imageButton_refuel);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_active_trip_imageButton_info);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_active_trip_imageButton_map);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_active_trip_imageButton_persons);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_active_trip_imageButton_settings);
        ib.setOnClickListener(this);

        tripList = new LinkedList<Trip>();
        purchaseList=new LinkedList<Purchase>();
        refuelList=new LinkedList<Refuel>();

        currentTrip = (Trip) getIntent().getExtras().getSerializable("chosenTrip");
        myRefTrip = database.getReference("myTrips");
        myRefPurchase = database.getReference(currentTrip.getmTripTitle()+"Purchase");
        myRefRefuel = database.getReference(currentTrip.getmTripTitle()+"Refuel");

        //---------- set title --------
        TextView title = (TextView) findViewById(R.id.activity_active_trip_textView_title);
        title.setText(currentTrip.getTripTitle());
        title.setTextColor(Color.WHITE);

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
                //Toast.makeText(ActivityActiveTrip.this, "currentTripFirebase: " + currentTripFirebase.getTripTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
        myRefTrip.addListenerForSingleValueEvent(tripListener);

        //firebase Purchase
        purchaseListener =new ValueEventListener() { //addListenerForSingleValueEvent
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Purchase> purchaseListRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Purchase>>() {});
                if(purchaseListRestore!=null){
                    purchaseList=purchaseListRestore;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
        myRefPurchase.addListenerForSingleValueEvent(purchaseListener);

        //firebase Refuel
        refuelListener =new ValueEventListener() { //addListenerForSingleValueEvent
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Refuel> refuelListRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Refuel>>() {});
                if(refuelListRestore!=null){
                    refuelList=refuelListRestore;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
        myRefRefuel.addListenerForSingleValueEvent(refuelListener);

        if(refuelList.size() == 0){
            maxMileage = currentTrip.getmMileage();
        }else{
            maxMileage = refuelList.get(refuelList.size()-1).getmDrivenKilometers();
        }

    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()) {
            case R.id.activity_active_trip_imageButton_shopping: {
                Log.i(TAG, "activity_active_trip_shopping pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityPurchase.class);
                i.putExtra("purchasePerson", (Serializable)currentTripFirebase.getmPersons());
                startActivityForResult(i, 2);
            }
            break;
            case R.id.activity_active_trip_imageButton_refuel: {
                Log.i(TAG, "activity_active_trip_refuel pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityRefuel.class);
                i.putExtra("purchasePerson", (Serializable)currentTripFirebase.getmPersons());
                i.putExtra("maxMileage", (Serializable)maxMileage);
                startActivityForResult(i, 1);
            }
            break;
            case R.id.activity_active_trip_imageButton_map: {
                Log.i(TAG, "activity_active_trip_map pressed!");
                Toast.makeText(this, "map pressed", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.activity_active_trip_imageButton_info: {
                Log.i(TAG, "activity_active_trip_statistics pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityInfo.class);
                i.putExtra("infoTrip", (Serializable)currentTripFirebase);
                startActivity(i);
            }
            break;
            case R.id.activity_active_trip_imageButton_persons: {
                Log.i(TAG, "activity_active_trip_person pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityPersons.class);
                i.putExtra("persons", (Serializable)currentTripFirebase);
                startActivity(i);
            }
            break;
            case R.id.activity_active_trip_imageButton_settings: {
                Log.i(TAG, "activity_active_trip_settings pressed!");
                Toast.makeText(this, "settings pressed", Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                Log.e(TAG, "unexpected ID encountered");
        }// switch
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Refuel newRefuel = (Refuel) data.getExtras().getSerializable("newRefuel");
                Toast.makeText(this, "added new Refuel", Toast.LENGTH_SHORT).show();
                refuelList.add(newRefuel);
                myRefRefuel.setValue(refuelList); //save to firebase

                maxMileage = newRefuel.getmDrivenKilometers();

                List<Person> p = currentTripFirebase.getmPersons();
                for(int i = 0;i < p.size();i++){
                    if(p.get(i).getmName().equals(newRefuel.getmPayer())){
                        p.get(i).addRefuel(newRefuel);
                    }
                }
                currentTripFirebase.addRefuel(newRefuel);
                myRefTrip.setValue(tripList);
            }
        }

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                Purchase newPurchase = (Purchase) data.getExtras().getSerializable("newPurchase");
                Toast.makeText(this, "added new Purchase at " + newPurchase.getmNameShop(), Toast.LENGTH_SHORT).show();
                purchaseList.add(newPurchase);
                myRefPurchase.setValue(purchaseList); //save to firebase

                List<Person> p = currentTripFirebase.getmPersons();
                for(int i = 0;i < p.size();i++){
                    if(p.get(i).getmName().equals(newPurchase.getmPayer())){
                        p.get(i).addPurchase(newPurchase);
                    }
                }
                currentTripFirebase.addPurchase(newPurchase);
                myRefTrip.setValue(tripList);
            }
        }


    }//onActivityResult

    /*
    @Override
    protected void onResume() {
        super.onResume();

        LinkedList<Refuel> deletedRefuels = (LinkedList<Refuel>) getIntent().getExtras().getSerializable("deletedRefuels");
        if(deletedRefuels != null && deletedRefuels.size() > 0){
            for(int i = 0;i < deletedRefuels.size();i++){
                for(int j = 0;j < currentTripFirebase.getmPersons().size();j++){
                    if(deletedRefuels.get(i).getmPayer() == currentTripFirebase.getmPersons().get(j).getmName()){
                        currentTripFirebase.getmPersons().get(j).deleteRefuel(deletedRefuels.get(i));
                    }
                }
            }
        }
        myRefTrip.setValue(tripList);
    }
*/

}

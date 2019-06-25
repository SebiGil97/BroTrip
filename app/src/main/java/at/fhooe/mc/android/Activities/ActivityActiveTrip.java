package at.fhooe.mc.android.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import at.fhooe.mc.android.Purchase;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Refuel;
import at.fhooe.mc.android.Trip;

public class ActivityActiveTrip extends Activity implements View.OnClickListener {
    private static final String TAG = "BroTripActiveTrip";

    Trip currentTrip;
    List<Purchase> purchaseList;
    List<Refuel> refuelList;

    //firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefPurchase;
    ValueEventListener purchaseListener;
    DatabaseReference myRefRefuel;
    ValueEventListener refuelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_trip);

        Button b = null;
        b = (Button) findViewById(R.id.activity_active_trip_map);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_persons);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_refuel);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_shopping);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_info);
        b.setOnClickListener(this);

        purchaseList=new LinkedList<Purchase>();
        refuelList=new LinkedList<Refuel>();

        currentTrip = (Trip) getIntent().getExtras().getSerializable("chosenTrip");
        myRefPurchase = database.getReference(currentTrip.getmTripTitle()+"Purchase");
        myRefRefuel = database.getReference(currentTrip.getmTripTitle()+"Refuel");

        TextView title = (TextView) findViewById(R.id.activity_active_trip_textView_title);
        title.setText(currentTrip.getTripTitle());

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
    }


    @Override
    public void onClick(View _v) {
        switch(_v.getId()) {
            case R.id.activity_active_trip_shopping: {
                Log.i(TAG, "activity_active_trip_shopping pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityPurchase.class);
                i.putExtra("purchasePerson", (Serializable)currentTrip.getmPersons());
                startActivityForResult(i, 2);
            }
            break;
            case R.id.activity_active_trip_refuel: {
                Log.i(TAG, "activity_active_trip_refuel pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityRefuel.class);
                i.putExtra("purchasePerson", (Serializable)currentTrip.getmPersons());
                startActivityForResult(i, 1);
            }
            break;
            case R.id.activity_active_trip_map: {
                Log.i(TAG, "activity_active_trip_map pressed!");
                Toast.makeText(this, "map pressed", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.activity_active_trip_info: {
                Log.i(TAG, "activity_active_trip_statistics pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityInfo.class);
                i.putExtra("infoTrip", (Serializable)currentTrip);
                startActivity(i);
            }
            break;
            case R.id.activity_active_trip_persons: {
                Log.i(TAG, "activity_active_trip_person pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityPersons.class);
                i.putExtra("persons", (Serializable)currentTrip);
                startActivity(i);
            }
            break;
            case R.id.activity_active_trip_settings: {
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
            }
        }

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                Purchase newPurchase = (Purchase) data.getExtras().getSerializable("newPurchase");
                Toast.makeText(this, "added new Purchase at " + newPurchase.getmNameShop(), Toast.LENGTH_SHORT).show();
                //currentTrip.addPurchase(newPurchase); //obsolete?
                purchaseList.add(newPurchase);
                myRefPurchase.setValue(purchaseList); //save to firebase
            }
        }


    }//onActivityResult
}
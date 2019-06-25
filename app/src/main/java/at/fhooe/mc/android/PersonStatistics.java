package at.fhooe.mc.android;

import android.util.Log;
import com.google.firebase.database.*;

import java.util.List;

import static at.fhooe.mc.android.Activities.MainActivity.TAG;

public class PersonStatistics {
    private int mNumberRefuels;
    private int mNumberPurchases;
    private double mTotalCosts;

    public PersonStatistics(){
        mNumberRefuels = 0;
        mNumberPurchases = 0;
        mTotalCosts = 0;
    }

    /*
    public PersonStatistics getPersonStatistics(){
        PersonStatistics pStat = new PersonStatistics();

        List<Purchase> purchaseList;
        List<Refuel> refuelList;

        //firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefPurchase;
        ValueEventListener purchaseListener;
        DatabaseReference myRefRefuel;
        ValueEventListener refuelListener;

        myRefPurchase = database.getReference(currentTrip.getmTripTitle()+"Purchase");
        myRefRefuel = database.getReference(currentTrip.getmTripTitle()+"Refuel");

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

        return pStat;
    }
    */
}

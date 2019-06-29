package at.fhooe.mc.android.Activities;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.Toast;
import at.fhooe.mc.android.*;
import at.fhooe.mc.android.Fragments.FragmentConsumption;
import at.fhooe.mc.android.Fragments.FragmentInfoBasic;
import at.fhooe.mc.android.Fragments.FragmentInfoPurchase;
import at.fhooe.mc.android.Fragments.FragmentInfoRefuel;
import at.fhooe.mc.android.R;
import com.google.firebase.database.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import static at.fhooe.mc.android.Activities.MainActivity.TAG;

public class ActivityInfo extends Activity implements View.OnClickListener {

    private static final String TAG = "ActivityInfo";
    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    private static final String VALUE_KEY = "ActivityInfoTripTitle";

    Trip currentTrip;
    public List<Purchase> purchases;
    public List<Refuel> refuels;


    // Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefPurchase;
    ValueEventListener purchaseListener;
    DatabaseReference myRefRefuel;
    ValueEventListener refuelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_info);

        //---------- Buttons ----------
        ImageButton ib = null;
        ib = (ImageButton) findViewById(R.id.activity_info_imageButton_purchases_info);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_info_imageButton_refuel_info);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_info_imageButton_consumption_info);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.activity_info_imageButton_basic_info);
        ib.setOnClickListener(this);

        //---------- get currentTrip from intent ----------
        currentTrip = (Trip) getIntent().getExtras().getSerializable("infoTrip");

        //---------- get purchaseList from firebase ----------
        myRefPurchase = database.getReference(currentTrip.getmTripTitle()+"Purchase");
        purchaseListener = new ValueEventListener() { //addListenerForSingleValueEvent

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Purchase> purchasesRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Purchase>>() {});
                if(purchasesRestore!=null){
                    purchases=purchasesRestore;
                }else{
                    purchases=new LinkedList<Purchase>();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };

        myRefPurchase.addValueEventListener(purchaseListener);


        //---------- get refuelList from firebase ----------
        myRefRefuel = database.getReference(currentTrip.getmTripTitle()+"Refuel");
        refuelListener = new ValueEventListener() { //addListenerForSingleValueEvent

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Refuel> refuelRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Refuel>>() {});
                if(refuelRestore!=null){
                    refuels = refuelRestore;
                }else{
                    refuels=new LinkedList<Refuel>();
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }

        };
        myRefRefuel.addValueEventListener(refuelListener);

        getIntent().putExtra("CurrentTrip", currentTrip);

        final FragmentManager fMgr = getFragmentManager();
        FragmentTransaction fT = fMgr.beginTransaction();
        fT.replace(R.id.fragment_container, new FragmentInfoBasic());
        fT.commit();
    }

    @Override
    public void onClick(View _v) {
        final FragmentManager fMgr = getFragmentManager();
        SharedPreferences sp = getSharedPreferences(SP_KEY, MODE_PRIVATE);

        switch(_v.getId()){

            case R.id.activity_info_imageButton_basic_info : {
                getIntent().putExtra("CurrentTrip", currentTrip);

                FragmentTransaction fT = fMgr.beginTransaction();
                fT.replace(R.id.fragment_container, new FragmentInfoBasic());
                fT.commit();
            } break;
            case R.id.activity_info_imageButton_purchases_info : {
                getIntent().putExtra("currentTrip", (Serializable) currentTrip);

                FragmentTransaction fT = fMgr.beginTransaction();
                fT.replace(R.id.fragment_container, new FragmentInfoPurchase());
                fT.commit();
            } break;
            case R.id.activity_info_imageButton_refuel_info : {

                getIntent().putExtra("currentTrip", (Serializable) currentTrip);

                FragmentTransaction fT = fMgr.beginTransaction();
                fT.replace(R.id.fragment_container, new FragmentInfoRefuel());
                fT.commit();
            } break;
            case R.id.activity_info_imageButton_consumption_info : {
                getIntent().putExtra("RefuelList", (Serializable) refuels);
                getIntent().putExtra("CurrentTrip", currentTrip);

                FragmentTransaction fT = fMgr.beginTransaction();
                fT.replace(R.id.fragment_container, new FragmentConsumption());
                fT.commit();
            } break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }// switch

    public void onBackPressed() {
        SharedPreferences sp = getSharedPreferences(SP_KEY, MODE_PRIVATE);
        Boolean deleteON = false;
        List<Fragment> fragmentList = getFragmentManager().getFragments();
        if (fragmentList != null) {

            for(Fragment fragment : fragmentList){

                if(fragment instanceof OnBackPressedListener){
                    ((OnBackPressedListener)fragment).onBackPressed();
                    deleteON = sp.getBoolean("FragmentDeleteBoolean", true); // holt sich String

                    if(deleteON == false){
                        super.onBackPressed();
                    }
                }

            }
        }
    }

}

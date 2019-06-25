package at.fhooe.mc.android.Activities;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import at.fhooe.mc.android.Fragments.FragmentInfoBasic;
import at.fhooe.mc.android.Fragments.FragmentInfoPurchase;
import at.fhooe.mc.android.Fragments.FragmentInfoRefuel;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;

public class ActivityInfo extends Activity implements View.OnClickListener {

    private static final String TAG = "ActivityInfo";
    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    private static final String VALUE_KEY = "ActivityInfoTripTitle";

    Trip currentTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_info);


        Button b = null;
        b = findViewById(R.id.activity_info_button_basic_info);
        b.setOnClickListener(this);
        b = findViewById(R.id.activity_info_button_purchases_info);
        b.setOnClickListener(this);
        b = findViewById(R.id.activity_info_button_refuel_info);
        b.setOnClickListener(this);

        currentTrip = (Trip) getIntent().getExtras().getSerializable("infoTrip");

        getIntent().putExtra("InfoBasicTrip", currentTrip);

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

            case R.id.activity_info_button_basic_info : {
                getIntent().putExtra("InfoBasicTrip", currentTrip);

                FragmentTransaction fT = fMgr.beginTransaction();
                fT.replace(R.id.fragment_container, new FragmentInfoBasic());
                fT.commit();
            } break;
            case R.id.activity_info_button_purchases_info : {
                SharedPreferences.Editor edit = sp.edit(); // erzeugt Editor
                edit.putString(VALUE_KEY, currentTrip.getTripTitle());
                edit.commit();

                FragmentTransaction fT = fMgr.beginTransaction();
                fT.replace(R.id.fragment_container, new FragmentInfoPurchase());
                fT.commit();
                //fT.addToBackStack(null);
            } break;
            case R.id.activity_info_button_refuel_info : {
                SharedPreferences.Editor edit = sp.edit(); // erzeugt Editor
                edit.putString(VALUE_KEY, currentTrip.getTripTitle());
                edit.commit();

                FragmentTransaction fT = fMgr.beginTransaction();
                fT.replace(R.id.fragment_container, new FragmentInfoRefuel());
                fT.commit();
            } break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }// switch
}

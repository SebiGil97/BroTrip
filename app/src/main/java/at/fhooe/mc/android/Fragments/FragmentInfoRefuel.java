package at.fhooe.mc.android.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import at.fhooe.mc.android.*;
import at.fhooe.mc.android.ListAdapter.InfoPurchaseAdapter;
import at.fhooe.mc.android.ListAdapter.InfoRefuelAdapter;
import at.fhooe.mc.android.R;
import com.google.firebase.database.*;

import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static at.fhooe.mc.android.Activities.MainActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoRefuel extends Fragment implements OnBackPressedListener {

    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    private static final String VALUE_KEY = "FragmentDeleteBoolean";

    Trip currentTrip;
    Trip currentTripFirebase;
    private List<Refuel> refuels;
    private List<Trip> tripList;
    InfoRefuelAdapter adapter;
    boolean deleteON;


    //firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefTrip;
    ValueEventListener tripListener;
    DatabaseReference myRefRefuel;

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        Intent i = getActivity().getIntent();
        currentTrip = (Trip) i.getSerializableExtra("currentTrip");

        //Intalize List
        tripList = new LinkedList<Trip>();
        refuels = new LinkedList<Refuel>();

        //firebase Trip
        myRefTrip = database.getReference("myTrips");
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

        //------------Firebase-------------------------------
        //Referenze for firebase
        myRefRefuel = database.getReference(currentTrip.getmTripTitle() + "Refuel");
        //load RefuelList from Database
        myRefRefuel.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<Refuel> refuelListRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Refuel>>() {});
                if(refuelListRestore!=null){
                    refuels=refuelListRestore;

                    for(int i=0;i<refuels.size();i++){
                        adapter.add(refuels.get(i));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = _inflater.inflate(R.layout.fragment_info_refuel, _container, false);

        final LinkedList<Refuel> deletedRefuels = new LinkedList(); // to update person statistsics

        ImageButton ib = null;
        ib = (ImageButton) view.findViewById(R.id.fragment_info_refuel_imageButton_delete);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"delete pressed!");



                //removes checked items from list
                for(int i=0; i<refuels.size();i++){
                    if(refuels.get(i).isReadyDelete()){
                        deletedRefuels.add(refuels.get(i));
                        adapter.remove(refuels.get(i));
                        refuels.remove(i);
                        i--;
                    }
                }
                adapter.notifyDataSetChanged();
                closeDeleteMode();
                myRefRefuel.setValue(refuels);
            }
        });
/*
        if(deletedRefuels != null && deletedRefuels.size() > 0){
            for(int i = 0;i < deletedRefuels.size();i++){
                for(int j = 0;j < currentTripFirebase.getmPersons().size();j++){
                    if(deletedRefuels.get(i).getmPayer().equals(currentTripFirebase.getmPersons().get(j))){
                        currentTripFirebase.getmPersons().get(j).deleteRefuel(deletedRefuels.get(i));
                    }
                }
            }
        }
        myRefTrip.setValue(tripList);

        deletedRefuels.clear();
*/
                //---------- Dynamic List ----------
        ListView lv = (ListView) view.findViewById(R.id.fragment_info_refuel_listView);
        adapter = new InfoRefuelAdapter(getActivity());


        if (refuels != null) {
            for (int index = 0; index < refuels.size(); index++) {      //
                adapter.add(refuels.get(index));  //

            }
        }

        lv.setAdapter(adapter);

        //for delete
        lv.setClickable(true);
        lv.setLongClickable(true);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int _pos, long id) {
                View v = (View) view.findViewById(R.id.fragment_info_refuel_imageButton_delete);
                v.setVisibility(View.VISIBLE);  //makes Delete Button Visible
                //make checkbox visible
                for(int i=0; i<refuels.size(); i++){
                    refuels.get(i).setDelete(true);
                }
                deleteON=true;
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return view;
    }
    @Override
    public void onBackPressed() {
        //-------- remove delete button ---------
        SharedPreferences sp = getActivity().getSharedPreferences(SP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(VALUE_KEY, deleteON);

        if(deleteON){
            closeDeleteMode();
        }

        edit.commit();
    }

    public void closeDeleteMode(){
        for(int i=0; i<refuels.size();i++){
            refuels.get(i).setDelete(false);
            refuels.get(i).setReadyDelete(false);
        }
        deleteON = false;
        View v1 = (View) getView().findViewById(R.id.fragment_info_refuel_imageButton_delete);
        v1.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}

package at.fhooe.mc.android.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import at.fhooe.mc.android.ListAdapter.InfoPurchaseAdapter;
import at.fhooe.mc.android.ListAdapter.InfoRefuelAdapter;
import at.fhooe.mc.android.Purchase;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Refuel;
import com.google.firebase.database.*;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static at.fhooe.mc.android.Activities.MainActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoRefuel extends Fragment {


    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    public List<Refuel> refuels;
    InfoRefuelAdapter adapter;


    // Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("myTrips");

    DatabaseReference myRefRefuel;
    ValueEventListener refuelListener;


    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        SharedPreferences sp = this.getActivity().getSharedPreferences(SP_KEY, MODE_PRIVATE);
        String tripTitle = sp.getString("ActivityInfoTripTitle", "undefined"); // holt sich String

        myRefRefuel = database.getReference(tripTitle+"Refuel");

        refuelListener = new ValueEventListener() { //addListenerForSingleValueEvent

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Refuel> refuelRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Refuel>>() {});
                if(refuelRestore!=null){
                    refuels=refuelRestore;
                    for(int i = 0;i < refuels.size();i++){      //
                        adapter.add(refuels.get(i));  //
                        Log.i(TAG, refuels.get(i).getmPayer() + "onScreen");
                    }
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
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Inflate the layout for this fragment
        View view = _inflater.inflate(R.layout.fragment_info_refuel, _container, false);

        //---------- Dynamic List ----------
        ListView lv = (ListView) view.findViewById(R.id.fragment_info_refuel_listView);

        adapter = new InfoRefuelAdapter(getActivity());

        lv.setAdapter(adapter);

        return view;
    }

}

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

import at.fhooe.mc.android.ListAdapter.InfoPurchaseAdapter;

import static android.content.Context.MODE_PRIVATE;
import static at.fhooe.mc.android.Activities.MainActivity.TAG;


public class FragmentInfoPurchase extends Fragment implements OnBackPressedListener {

    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    private static final String VALUE_KEY = "FragmentDeleteBoolean";

    Trip currentTrip;
    public List<Purchase> purchases;
    InfoPurchaseAdapter adapter;
    boolean deleteON;

    //firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefPurchase;

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        Intent i = getActivity().getIntent();
        currentTrip = (Trip) i.getSerializableExtra("currentTrip");

        //Intalize List
        purchases = new LinkedList<Purchase>();


        //------------Firebase-------------------------------
        //Referenze for firebase
        myRefPurchase = database.getReference(currentTrip.getmTripTitle() + "Purchase");
        //load RefuelList from Database
        myRefPurchase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                List<Purchase> purchaseListRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Purchase>>() {});
                if(purchaseListRestore!=null){
                    purchases=purchaseListRestore;

                    for(int i=0;i<purchases.size();i++){
                        adapter.add(purchases.get(i));
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
        final View view = _inflater.inflate(R.layout.fragment_info_purchase, _container, false);

        ImageButton ib = null;
        ib = (ImageButton) view.findViewById(R.id.fragment_info_purchase_imageButton_delete);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"delete pressed!");

                //removes checked items from list
                for(int i=0; i<purchases.size();i++){
                    if(purchases.get(i).isReadyDelete()){
                        adapter.remove(purchases.get(i));
                        purchases.remove(i);
                        i--;
                    }
                }
                adapter.notifyDataSetChanged();
                closeDeleteMode();
                myRefPurchase.setValue(purchases);
            }
        });

        //---------- Dynamic List ----------
        ListView lv = (ListView) view.findViewById(R.id.fragment_info_purchase_listView);
        adapter = new InfoPurchaseAdapter(getActivity());

        if (purchases != null) {
            for (int i = 0; i < purchases.size(); i++) {
                adapter.add(purchases.get(i));
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
                View v = (View) view.findViewById(R.id.fragment_info_purchase_imageButton_delete);
                v.setVisibility(View.VISIBLE);  //makes Delete Button Visible
                //make checkbox visible
                for(int i=0; i<purchases.size(); i++){
                    purchases.get(i).setDelete(true);
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
        for(int i=0; i<purchases.size();i++){
            purchases.get(i).setDelete(false);
            purchases.get(i).setReadyDelete(false);
        }
        deleteON = false;
        View v1 = (View) getView().findViewById(R.id.fragment_info_purchase_imageButton_delete);
        v1.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}

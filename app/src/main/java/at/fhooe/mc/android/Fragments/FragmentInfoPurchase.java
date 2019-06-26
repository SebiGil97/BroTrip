package at.fhooe.mc.android.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import at.fhooe.mc.android.ListAdapter.InfoPurchaseAdapter;
import at.fhooe.mc.android.Purchase;
import at.fhooe.mc.android.R;

import static android.content.Context.MODE_PRIVATE;
import static at.fhooe.mc.android.Activities.MainActivity.TAG;


public class FragmentInfoPurchase extends Fragment {

    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    public List<Purchase> purchases;
    InfoPurchaseAdapter adapter;


    // Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefPurchase;
    ValueEventListener purchaseListener;


    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        SharedPreferences sp = this.getActivity().getSharedPreferences(SP_KEY, MODE_PRIVATE);
        String tripTitle = sp.getString("ActivityInfoTripTitle", "undefined"); // holt sich String

        myRefPurchase = database.getReference(tripTitle+"Purchase");
        purchaseListener = new ValueEventListener() { //addListenerForSingleValueEvent

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                List<Purchase> purchasesRestore = dataSnapshot.getValue(new GenericTypeIndicator<List<Purchase>>() {});
                if(purchasesRestore!=null){
                    purchases=purchasesRestore;
                    for(int i = 0;i < purchases.size();i++){      //
                        adapter.add(purchases.get(i));  //
                        Log.i(TAG, purchases.get(i).getmNameShop() + "onScreen");
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };

        myRefPurchase.addListenerForSingleValueEvent(purchaseListener);




    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Inflate the layout for this fragment
        View view = _inflater.inflate(R.layout.fragment_info_purchase, _container, false);

        //---------- Dynamic List ----------
        ListView lv = (ListView) view.findViewById(R.id.fragment_info_purchase_listView);
        adapter = new InfoPurchaseAdapter(getActivity());
        lv.setAdapter(adapter);

        return view;
    }


}

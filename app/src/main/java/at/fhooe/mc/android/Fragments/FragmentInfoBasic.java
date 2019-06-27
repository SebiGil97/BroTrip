package at.fhooe.mc.android.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import at.fhooe.mc.android.*;
import at.fhooe.mc.android.Activities.ActivityInfo;
import at.fhooe.mc.android.ListAdapter.InfoPurchaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Integer.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoBasic extends Fragment implements OnBackPressedListener{

    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    private static final String VALUE_KEY = "FragmentDeleteBoolean";

    Trip currentTrip;
    List<Person> persons;
    ArrayList<String> names;

    public FragmentInfoBasic() {
        // Required empty public constructor
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getActivity().getIntent();
        currentTrip = (Trip) i.getSerializableExtra("CurrentTrip");
        persons = currentTrip.getmPersons();

        names = new ArrayList<>();
        for(int index = 0; index < persons.size(); index++){
            names.add(persons.get(index).getmName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = _inflater.inflate(R.layout.fragment_info_basic, _container, false);

        TextView tv;
        tv = (TextView) view.findViewById(R.id.fragment_info_basic_textView_tripTitle);
        tv.setText(currentTrip.getmTripTitle());
        tv = (TextView) view.findViewById(R.id.fragment_info_basic_textView_car);
        tv.setText(currentTrip.getmCar());
        tv = (TextView) view.findViewById(R.id.fragment_info_basic_textView_mileage);
        tv.setText(String.valueOf(String.format("%.1f", currentTrip.getmMileage())) + " km");

        ListView lv = null;
        lv = (ListView) view.findViewById(R.id.fragment_info_basic_listView_person);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, names);
        lv.setAdapter(adapter);

        return view;
    }
    @Override
    public void onBackPressed() {
        SharedPreferences sp = getActivity().getSharedPreferences(SP_KEY, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(VALUE_KEY, false); //false -> no delete mode
        edit.commit();
    }

}

package at.fhooe.mc.android.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import at.fhooe.mc.android.ListAdapter.InfoPurchaseAdapter;
import at.fhooe.mc.android.Person;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.valueOf;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoBasic extends Fragment {


    List<String> nameList;

    public FragmentInfoBasic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = _inflater.inflate(R.layout.fragment_info_basic, _container, false);

        Intent i = getActivity().getIntent();

        Trip currentTrip = (Trip) i.getSerializableExtra("InfoBasicTrip");

        TextView textView = (TextView) view.findViewById(R.id.fragment_info_basic_textView_tripTitle);
        textView.setText(currentTrip.getTripTitle());
        textView = (TextView) view.findViewById(R.id.fragment_info_basic_textView_car);
        textView.setText(currentTrip.getmCar());
        textView = (TextView) view.findViewById(R.id.fragment_info_basic_textView_mileage);
        //textView.setText(valueOf(currentTrip.getmMileage()));

        //---------- Dynamic List ----------
        nameList=new LinkedList<String>();
        List<Person> persons = currentTrip.getmPersons();

        for(int index = 0; index < persons.size(); index++){
            nameList.add(persons.get(index).getName());
        }
        /*
        ListView lv = (ListView) view.findViewById(R.id.new_trip_listView_persons);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nameList);
        lv.setAdapter(adapter);
        */
        return view;
    }

}

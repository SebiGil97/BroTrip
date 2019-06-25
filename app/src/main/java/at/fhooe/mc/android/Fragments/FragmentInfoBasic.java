package at.fhooe.mc.android.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInfoBasic extends Fragment {


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

        return view;
    }

}

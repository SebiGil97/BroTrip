package at.fhooe.mc.android.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;

public class TripDataAdapter extends ArrayAdapter<Trip> {


    public TripDataAdapter(Context _c){
        super(_c,-1);
    }

    public View getView(int _position, View _convertView, ViewGroup _parent){

        final Trip data = getItem(_position);

        if(_convertView == null){

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _convertView = inflater.inflate(R.layout.list_trip, null);

        }

        TextView tv = null;
        tv = (TextView)_convertView.findViewById(R.id.list_trip_textView_trip_title);
        tv.setText(data.getTripTitle());
        tv = (TextView)_convertView.findViewById(R.id.list_trip_textView_car);
        tv.setText(data.getCar());

        Button b = (Button) _convertView.findViewById(R.id.list_trip_button_settings);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                 Toast.makeText(getContext(), data.getTripTitle() + " options", Toast.LENGTH_SHORT).show();

            }
        });

        return _convertView;
    }
}

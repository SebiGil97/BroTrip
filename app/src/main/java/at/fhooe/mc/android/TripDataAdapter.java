package at.fhooe.mc.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TripDataAdapter extends ArrayAdapter<TripData> {

    public TripDataAdapter(Context _c){
        super(_c,-1);
    }

    public View getView(int _position, View _convertView, ViewGroup _parent){
        if(_convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _convertView = inflater.inflate(R.layout.list_trip, null);

        }
        TripData data = getItem(_position);

        TextView tv = null;
        tv = (TextView)_convertView.findViewById(R.id.list_trip_textView_trip_title);
        tv.setText(data.getTripTitle());
     //   tv = (TextView)_convertView.findViewById(R.id.list_trip_textView_car);
     //   tv.setText(data.getCar());

        return _convertView;
    }
}

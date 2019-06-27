package at.fhooe.mc.android.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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



        final CheckBox cb =(CheckBox) _convertView.findViewById(R.id.list_trip_checkBox_delete);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                          @Override
                                          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                              if(isChecked){
                                                  data.setReadyDelete(true);
                                              }else{
                                                  data.setReadyDelete(false);
                                              }
                                          }
                                      }

        );

        View v1 = _convertView.findViewById(R.id.list_trip_checkBox_delete);
        if(data.isDelete()){
            v1.setVisibility(View.VISIBLE);
            cb.setChecked(false);
        }else{
            v1.setVisibility(View.GONE);
        }

        return _convertView;
    }
}

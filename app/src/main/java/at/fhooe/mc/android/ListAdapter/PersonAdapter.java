package at.fhooe.mc.android.ListAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.fhooe.mc.android.Person;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;

import static java.lang.String.valueOf;

public class PersonAdapter extends ArrayAdapter<Person> {


    public PersonAdapter(@NonNull Context _context) {
        super(_context, -1);
    }

    public View getView(int _position, View _convertView, ViewGroup _parent){

        final Person data = getItem(_position);

        if(_convertView == null){

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _convertView = inflater.inflate(R.layout.list_persons_info, null);

        }



        TextView tv = null;
        tv = (TextView)_convertView.findViewById(R.id.list_persons_name);
        tv.setText(data.getmName());
        tv = (TextView)_convertView.findViewById(R.id.list_persons_number_refuels);
        tv.setText(valueOf(String.format("%.0f", data.getmNumberRefuel())));
        tv = (TextView)_convertView.findViewById(R.id.list_persons_refuels_total);
        tv.setText(valueOf(String.format("%.2f", data.getmExpRefuel())));
        tv = (TextView)_convertView.findViewById(R.id.list_persons_number_purchases);
        tv.setText(valueOf(String.format("%.0f", data.getmNumberPurchase())));
        tv = (TextView)_convertView.findViewById(R.id.list_persons_purchase_total);
        tv.setText(valueOf(String.format("%.2f", data.getmExpPurchase())));
        tv = (TextView)_convertView.findViewById(R.id.list_persons_paidTotal);
        tv.setText(valueOf(String.format("%.2f", data.getmExpenditures())));
        tv = (TextView)_convertView.findViewById(R.id.list_persons_surplus);
        double surplus = data.getmSurplus();
        tv.setText(valueOf(String.format("%.2f", surplus)));
        if(surplus < 0){
            tv.setTextColor(Color.RED);
        }else if(surplus == 0){
            tv.setTextColor(Color.GRAY);
        }else{
            tv.setTextColor(Color.GREEN);
        }

        return _convertView;
    }
}

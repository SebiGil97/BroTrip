package at.fhooe.mc.android.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import at.fhooe.mc.android.Purchase;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Refuel;

import static java.lang.String.valueOf;

public class InfoRefuelAdapter extends ArrayAdapter<Refuel> {

    public InfoRefuelAdapter(Context _context) { super(_context, -1); }

    public View getView(int _position, View _convertView, ViewGroup _parent){

        final Refuel data = getItem(_position);

        if(_convertView == null){

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _convertView = inflater.inflate(R.layout.list_refuel, null);

        }

        TextView tv = null;
        tv = (TextView)_convertView.findViewById(R.id.list_refuel_textView_price);
        tv.setText(valueOf(data.getmCosts()));
        tv = (TextView)_convertView.findViewById(R.id.list_refuel_textView_payer);
        tv.setText(data.getmPayer());
        tv = (TextView)_convertView.findViewById(R.id.list_refuel_textView_date);
        tv.setText(data.getmDate().toString());

        return _convertView;
    }
}

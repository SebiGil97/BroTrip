package at.fhooe.mc.android.ListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import at.fhooe.mc.android.Purchase;
import at.fhooe.mc.android.R;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class InfoPurchaseAdapter extends ArrayAdapter<Purchase> {


    public InfoPurchaseAdapter(Context _context) { super(_context, -1); }

    public View getView(int _position, View _convertView, ViewGroup _parent){

        final Purchase data = getItem(_position);

        if(_convertView == null){

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            _convertView = inflater.inflate(R.layout.list_purchase, null);

        }

        TextView tv = null;
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_shop);
        tv.setText(data.getmNameShop());
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_price);
        tv.setText(valueOf(String.format("%.2f", data.getmCosts())));
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_payer);
        tv.setText(data.getmPayer());
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_date);
        tv.setText(data.getmDate().toString());
        return _convertView;
    }
}

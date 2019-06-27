package at.fhooe.mc.android.ListAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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

        String date = data.getmDate().toString();
        String year = date.substring(30);
        String month = date.substring(4,7);
        String day = date.substring(8,10);
        String time = date.substring(11,16);

        TextView tv = null;
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_shop);
        tv.setText(data.getmNameShop());
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_price);
        tv.setText("€ " + valueOf(String.format("%.2f", data.getmCosts())));
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_payer);
        tv.setText(data.getmPayer());
        tv = (TextView)_convertView.findViewById(R.id.list_purchase_textView_date);
        tv.setText(year + "-" + month + "-" + day + " " + time);

        final CheckBox cb=(CheckBox) _convertView.findViewById(R.id.list_purchase_checkBox_delete);
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

        View v1 = _convertView.findViewById(R.id.list_purchase_checkBox_delete);
        if(data.isDelete()){
            v1.setVisibility(View.VISIBLE);
            cb.setChecked(false);
        }else{
            v1.setVisibility(View.GONE);
        }

        return _convertView;
    }
}

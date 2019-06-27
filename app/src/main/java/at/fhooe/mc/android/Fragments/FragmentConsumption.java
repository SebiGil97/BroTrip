package at.fhooe.mc.android.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import at.fhooe.mc.android.OnBackPressedListener;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Refuel;
import at.fhooe.mc.android.Trip;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.String.valueOf;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentConsumption extends Fragment implements OnBackPressedListener {

    private static final String SP_KEY = "ActivityInfo-SharedPreferences-Key";
    private static final String VALUE_KEY = "FragmentDeleteBoolean";

    Trip currentTrip;
    float averageConsumption=0;
    float averagePrice=0;
    float lastConsumption=0;
    float lastPrice=0;
    float diffdistanz;
    int trend;  //1... bad  , 2... neutal , 3...better
    List<Refuel> refuels;

    public FragmentConsumption() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getActivity().getIntent();
        refuels = (List<Refuel>) i.getSerializableExtra("RefuelList");
        currentTrip = (Trip) i.getSerializableExtra("CurrentTrip");
        trend = 0;

        if(refuels != null) {

            //---------- calculate average consumption and average price -----------
            if (refuels.size() == 2) {
                float distance = refuels.get(refuels.size() - 1).getmDrivenKilometers() - refuels.get(refuels.size() - 2).getmDrivenKilometers();
                distance = distance / 100;
                averageConsumption = refuels.get(refuels.size() - 2).getmLiter() / distance;
                averagePrice = refuels.get(refuels.size() - 2).getmCosts() / distance;
                trend = 2;
            } else if (refuels.size() > 2) {
                float distance = refuels.get(refuels.size() - 2).getmDrivenKilometers() - currentTrip.getmMileage();
                distance = distance / 100;
                for (int y = 0; y < refuels.size() - 1; y++) {
                    averageConsumption = averageConsumption + refuels.get(y).getmLiter();
                    averagePrice = averagePrice + refuels.get(y).getmCosts();
                }
                averageConsumption = averageConsumption / distance;
                averagePrice = averagePrice / distance;
            }
            if (refuels.size() > 1) {
                //--------- calculate last consumption and trend ----------
                diffdistanz = refuels.get(refuels.size() - 1).getmDrivenKilometers() - refuels.get(refuels.size() - 2).getmDrivenKilometers();
                diffdistanz = diffdistanz / 100;

                lastConsumption = refuels.get(refuels.size() - 2).getmLiter() / diffdistanz;
                lastPrice = refuels.get(refuels.size() - 2).getmCosts() / diffdistanz;
                //Log.i(TAG, valueOf(refuels.get(refuels.size() - 2).getmLiter() / diffdistanz));
            }
            if (averageConsumption > lastConsumption) {
                trend = 3;  //"Keep it up!"
            } else if (averageConsumption < lastConsumption) {
                trend = 1; //"You can do that better!"
            } else if (averageConsumption == 0 && lastConsumption == 0) {
                trend = 0; //""
            } else {
                trend = 2;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consumption, container, false);

        TextView tv = null;
        tv = (TextView) view.findViewById(R.id.fragment_info_consumption_textview_average_consumption);
        tv.setText(String.format("%.2f",averageConsumption) +" l / 100km");
        tv = (TextView) view.findViewById(R.id.fragment_info_consumption_textview_average_cost);
        tv.setText(String.format("%.2f",averagePrice) + " € / 100km");
        tv = (TextView) view.findViewById(R.id.fragment_info_consumption_textview_last_consumption);
        tv.setText(String.format("%.2f",lastConsumption) +" l / 100km");
        tv = (TextView) view.findViewById(R.id.fragment_info_consumption_textview_last_cost);
        tv.setText(String.format("%.2f",lastPrice) + " € / 100km");
        tv = (TextView) view.findViewById(R.id.fragment_info_consumption_textview_trend);

        ImageView iv = null;
        iv = (ImageView) view.findViewById(R.id.fragment_info_consumption_imageView_trend);

        if(trend == 3) {
            tv.setText("Well done! Keep it up!");
            tv.setTextColor(Color.GREEN);
            iv.setImageResource(R.drawable.icon_green_arrow_up_225);
        }else if(trend == 1) {
            tv.setText("You can do that better!");
            tv.setTextColor(Color.RED);
            iv.setImageResource(R.drawable.icon_red_arrow_down_225);
        }else if(trend == 2){
            tv.setText("Try to improve!");
            tv.setTextColor(Color.WHITE);
            iv.setImageResource(R.drawable.icon_black_arrow_neutral);
        }else{
            tv.setText("");
            iv.setImageResource(R.drawable.icon_black_arrow_neutral);
        }
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

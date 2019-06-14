package at.fhooe.mc.android;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static at.fhooe.mc.android.MainActivity.TAG;

public class NewTrip extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        Button b = null;
        b = (Button) findViewById(R.id.new_trip_button_save);
        b.setOnClickListener(this);


    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){
            case R.id.new_trip_button_save : {
                Toast.makeText(this, "newTrip--SAVE", Toast.LENGTH_SHORT).show();

                EditText title = (EditText)findViewById(R.id.new_trip_editText_triptitle);
                String tripTitle = title.getText().toString();
                EditText car = (EditText)findViewById(R.id.new_trip_editText_car);
                String tripCar = car.getText().toString();

                TripData trip = new TripData(tripTitle,tripCar);

            } break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }





}

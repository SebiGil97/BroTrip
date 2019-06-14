package at.fhooe.mc.android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static at.fhooe.mc.android.MainActivity.TAG;

public class ActivityNewTrip extends Activity implements View.OnClickListener{

    private static final String SP_KEY      = "ActivityNewTrip_NewTrip";
    public static final String VALUE_KEY    = "TheOneAndOnlyTripTitleKey";

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
        SharedPreferences sp = getSharedPreferences(SP_KEY, MODE_PRIVATE);

        switch(_v.getId()){
            case R.id.new_trip_button_save : {
                Toast.makeText(this, "newTrip--SAVE", Toast.LENGTH_SHORT).show();

                EditText title = (EditText)findViewById(R.id.new_trip_editText_triptitle);
                String tripTitle = title.getText().toString();
                EditText car = (EditText)findViewById(R.id.new_trip_editText_car);
                String tripCar = car.getText().toString();

                SharedPreferences.Editor edit = sp.edit();  // erzeugt Editor
                edit.putString(VALUE_KEY, tripTitle);
                edit.commit();                              //erst jetzt wirklich gespeichert -> schreibt in XML File

                Trip newTrip = new Trip(tripTitle,tripCar/*, Milage*/); // wie serialisieren und zu List hinzufügen?

            } break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }





}

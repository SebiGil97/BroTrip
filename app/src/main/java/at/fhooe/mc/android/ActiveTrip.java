package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActiveTrip extends Activity implements View.OnClickListener {

    private static final String TAG = "BroTripActiveTrip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_trip);

        Button b = null;
        b = (Button) findViewById(R.id.activity_active_trip_map);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_persons);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_refuel);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_shopping);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activity_active_trip_statistics);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()) {
            case R.id.activity_active_trip_shopping: {
                Log.i(TAG, "activity_active_trip_shopping pressed!");
                Intent i = new Intent(ActiveTrip.this, NewPurchase.class);
                startActivity(i);
            }
            break;
            case R.id.activity_active_trip_refuel: {
                Log.i(TAG, "activity_active_trip_refuel pressed!");
                Intent i = new Intent(ActiveTrip.this, Refuel.class);
                startActivity(i);
            }
            break;
            case R.id.activity_active_trip_map: {
                Log.i(TAG, "activity_active_trip_map pressed!");
                Toast.makeText(this, "map pressed", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.activity_active_trip_statistics: {
                Log.i(TAG, "activity_active_trip_statistics pressed!");
                Toast.makeText(this, "statistics pressed", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.activity_active_trip_persons: {
                Log.i(TAG, "activity_active_trip_person pressed!");
                Toast.makeText(this, "persons pressed", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.activity_active_trip_settings: {
                Log.i(TAG, "activity_active_trip_settings pressed!");
                Toast.makeText(this, "settings pressed", Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                Log.e(TAG, "unexpected ID encountered");
        }
    }
}

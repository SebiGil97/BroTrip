package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ActivityActiveTrip extends Activity implements View.OnClickListener {
    private static final String TAG = "BroTripActiveTrip";

    Trip currentTrip;


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

        currentTrip = (Trip) getIntent().getExtras().getSerializable("chosenTrip");
    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()) {
            case R.id.activity_active_trip_shopping: {
                Log.i(TAG, "activity_active_trip_shopping pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityPurchase.class);
                i.putExtra("purchasePerson", (Serializable)currentTrip.getmPersons());
                startActivityForResult(i, 2);
            }
            break;
            case R.id.activity_active_trip_refuel: {
                Log.i(TAG, "activity_active_trip_refuel pressed!");
                Intent i = new Intent(ActivityActiveTrip.this, ActivityRefuel.class);
                startActivityForResult(i, 1);
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
        }// switch
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Refuel newRefuel = (Refuel) data.getExtras().getSerializable("newRefuel");
                Toast.makeText(this, "added new Refuel", Toast.LENGTH_SHORT).show();
                currentTrip.addRefuel(newRefuel); // wie gleich her speichern???
            }
        }

        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                Purchase newPurchase = (Purchase) data.getExtras().getSerializable("newPurchase");
                Toast.makeText(this, "added new Purchase at " + newPurchase.getmNameShop(), Toast.LENGTH_SHORT).show();
                currentTrip.addPurchase(newPurchase); // wie gleich her speichern???
            }
        }


    }//onActivityResult
}

package at.fhooe.mc.android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static at.fhooe.mc.android.MainActivity.TAG;

public class ActivityNewTrip extends Activity implements View.OnClickListener{

    private static final String SP_KEY      = "ActivityNewTrip_NewTrip";
    public static final String KEY_TITLE    = "TheOneAndOnlyTripTitleKey";
    public static final String KEY_CAR      = "TheOneAndOnlyTripCarKey";

    List<String> PersonArrayList;
    ArrayAdapter<String> adapter;
    public int mNumberOfPersons = 0;
    public String[] mPersonList = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        Button b = null;
        b = (Button) findViewById(R.id.new_trip_button_save);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.new_trip_button_add_person);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.new_trip_button_remove_person);
        b.setOnClickListener(this);


        ListView lv = (ListView) findViewById(R.id.new_trip_listView_persons);
        PersonArrayList = new ArrayList<String>(Arrays.asList(mPersonList));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, PersonArrayList);
        lv.setAdapter(adapter);

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
                edit.putString(KEY_TITLE, tripTitle);
                edit.commit();                              //erst jetzt wirklich gespeichert -> schreibt in XML File

                edit.putString(KEY_CAR, tripCar);
                edit.commit();

                Trip newTrip = new Trip(tripTitle,tripCar/*, Milage*/); // wie serialisieren und zu List hinzufügen?
                finish();
            } break;
            case R.id.new_trip_button_add_person : {
                EditText getName = (EditText) findViewById(R.id.new_trip_editText_name_person);

                if(mNumberOfPersons < 5) { // only 5 tripper allowed

                    String name = getName.getText().toString();

                    if(name.isEmpty()){ // editText name is empty
                        Toast.makeText(this, "please enter a name to add", Toast.LENGTH_SHORT).show();
                    }else { // add name
                        PersonArrayList.add(name);
                        adapter.notifyDataSetChanged();
                        mNumberOfPersons++;
                        getName.setText("");
                        Toast.makeText(this, "added " + name, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getName.setText("");
                    Toast.makeText(this, "only 5 tripper allowed", Toast.LENGTH_SHORT).show();
                }
            } break;
            case R.id.new_trip_button_remove_person : { // remove last added person
                if(mNumberOfPersons == 0) {
                    Toast.makeText(this, "no tripper to remove", Toast.LENGTH_SHORT).show();
                }else{
                    mNumberOfPersons--;
                    String name = PersonArrayList.get(mNumberOfPersons);
                    PersonArrayList.remove(mNumberOfPersons);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "removed " + name, Toast.LENGTH_SHORT).show();
                }
            } break;
            default : Log.e(TAG, "unexpected ID encountered");
        }
    }





}

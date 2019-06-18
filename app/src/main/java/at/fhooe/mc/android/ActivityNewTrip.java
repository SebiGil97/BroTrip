package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
    private LinkedList<Person> persons;

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

        persons = new LinkedList<Person>();

        ListView lv = (ListView) findViewById(R.id.new_trip_listView_persons);
        PersonArrayList = new ArrayList<String>(Arrays.asList(mPersonList));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, PersonArrayList);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){
            case R.id.new_trip_button_add_person : {
                EditText getName = (EditText) findViewById(R.id.new_trip_button_add_person);

                if(mNumberOfPersons < 5) { // only 5 tripper allowed

                    String name = getName.getText().toString();

                    if(name.isEmpty()){ // editText name is empty
                        Toast.makeText(this, "please enter a name to add", Toast.LENGTH_SHORT).show();
                    }else { // add name
                        PersonArrayList.add(name); //add to dynamic List for ListView
                        adapter.notifyDataSetChanged();
                        mNumberOfPersons++;

                        persons.add(new Person(name)); // add new Person to LinkedList
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
                    PersonArrayList.remove(mNumberOfPersons); // remove last Person from ListView
                    adapter.notifyDataSetChanged();

                    persons.removeLast(); // remove last Person of LinkedList
                    Toast.makeText(this, "removed " + name, Toast.LENGTH_SHORT).show();
                }
            } break;
            case R.id.new_trip_button_save : {
                Toast.makeText(this, "newTrip--SAVE", Toast.LENGTH_SHORT).show();

                EditText title = (EditText)findViewById(R.id.new_trip_editText_triptitle);
                String tripTitle = title.getText().toString();
                EditText car = (EditText)findViewById(R.id.new_trip_editText_car);
                String tripCar = car.getText().toString();
                EditText mileage = (EditText)findViewById(R.id.new_trip_editText_mileage);
                Integer tripMileage = Integer.parseInt(mileage.getText().toString());

                Trip newTrip = new Trip(tripTitle,tripCar, tripMileage, persons);

                /*--------- return intent ----------*/
                Intent returnIntent = new Intent();
                returnIntent.putExtra("newTripResult", newTrip);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            } break;

            default : Log.e(TAG, "unexpected ID encountered");
        }
    }





}

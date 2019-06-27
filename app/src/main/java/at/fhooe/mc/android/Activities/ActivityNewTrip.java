package at.fhooe.mc.android.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import at.fhooe.mc.android.Person;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;

import static at.fhooe.mc.android.Activities.MainActivity.TAG;

public class ActivityNewTrip extends Activity implements View.OnClickListener{

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

        //---------- Buttons ----------
        ImageButton ib = null;
        ib = (ImageButton) findViewById(R.id.new_trip_imageButton_add_person);
        ib.setOnClickListener(this);
        ib = (ImageButton) findViewById(R.id.new_trip_imageButton_remove_person);
        ib.setOnClickListener(this);

        Button b = null;
        b = (Button) findViewById(R.id.new_trip_button_save);
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
            case R.id.new_trip_imageButton_add_person : {
                EditText inputName = (EditText) findViewById(R.id.new_trip_editText_name_person);

                if(mNumberOfPersons < 5) { // only 5 tripper allowed

                    String name = inputName.getText().toString();

                    if(name.isEmpty()){ // editText name is empty
                        Toast.makeText(this, "please enter a name to add", Toast.LENGTH_SHORT).show();
                    }else { // add name
                        PersonArrayList.add(name); //add to dynamic List for ListView
                        adapter.notifyDataSetChanged();
                        mNumberOfPersons++;

                        persons.add(new Person(name)); // add new Person to LinkedList
                        inputName.setText("");

                        Toast.makeText(this, "added " + name, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    inputName.setText("");
                    Toast.makeText(this, "only 5 tripper allowed", Toast.LENGTH_SHORT).show();
                }
            } break;
            case R.id.new_trip_imageButton_remove_person : { // remove last added person
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
                /*--------- get Strings from ediText-Fields ----------*/
                EditText title = (EditText)findViewById(R.id.new_trip_editText_triptitle);
                String tripTitle = title.getText().toString();
                EditText car = (EditText)findViewById(R.id.new_trip_editText_car);
                String tripCar = car.getText().toString();
                EditText mileage = (EditText)findViewById(R.id.new_trip_editText_mileage);


                //---------- check input ----------
                if(tripTitle.isEmpty()){ // editText name is empty
                    Toast.makeText(this, "please enter a title", Toast.LENGTH_SHORT).show();
                }else if(tripCar.isEmpty()){ // editText name is empty
                    Toast.makeText(this, "please enter a car", Toast.LENGTH_SHORT).show();
                }else if( mileage.getText().toString().equals("")){ // editText name is empty
                    Toast.makeText(this, "please enter mileage", Toast.LENGTH_SHORT).show();
                }else if( persons.size() < 1){ // editText name is empty
                    Toast.makeText(this, "please enter persons", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "new trip saved", Toast.LENGTH_SHORT).show();

                    Integer tripMileage = Integer.parseInt(mileage.getText().toString());
                    /*--------- build Trip-Object ----------*/
                    Trip newTrip = new Trip(tripTitle, tripCar, tripMileage, persons);

                    /*--------- return intent ----------*/
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("newTripResult", newTrip);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            } break;

            default : Log.e(TAG, "unexpected ID encountered");
        }
    }





}

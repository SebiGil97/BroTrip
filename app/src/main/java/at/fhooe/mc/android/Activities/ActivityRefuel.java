package at.fhooe.mc.android.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import at.fhooe.mc.android.Person;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Refuel;

public class ActivityRefuel extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    List<String> nameList;
    float maxMileage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuel);

        maxMileage = (Float) getIntent().getExtras().getSerializable("maxMileage");

       //-------- load friends ----------
        nameList=new LinkedList<String>();
        nameList.add("select a payer");
        ArrayList<Person> persons = (ArrayList<Person>) getIntent().getExtras().getSerializable("purchasePerson");
        for(int index = 0; index < persons.size(); index++){
            nameList.add(persons.get(index).getmName());
        }

        /*----------------ListSpinner------------*/
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, nameList);
        Spinner spinner = (Spinner) findViewById(R.id.activity_refuel_pick_friend);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button b = null;
        b = (Button) findViewById(R.id.activity_refuel_button_save);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){
            case R.id.activity_refuel_button_save: {
                /*---------- get Integers and Person-Object ----------*/
                EditText mileage = (EditText)findViewById(R.id.activity_refuel_editText_current_mileage);
                EditText price = (EditText)findViewById(R.id.acitivity_refuel_editText_price);
                EditText liter = (EditText)findViewById(R.id.acitivity_refuel_editText_liters);
                Spinner payer = (Spinner)findViewById(R.id.activity_refuel_pick_friend);
                String refuelPayer = (String) payer.getSelectedItem();

                //--------- check input ------------
                if(mileage.getText().toString().equals("")){
                    Toast.makeText(this, "please enter a mileage", Toast.LENGTH_SHORT).show();
                }else if(Float.parseFloat(mileage.getText().toString()) < maxMileage){
                    Toast.makeText(this, "last mileage was " + maxMileage, Toast.LENGTH_SHORT).show();
                }else if(price.getText().toString().equals("")){
                    Toast.makeText(this, "please enter a price", Toast.LENGTH_SHORT).show();
                }else if(liter.getText().toString().equals("")){
                    Toast.makeText(this, "please enter an amount of liters", Toast.LENGTH_SHORT).show();
                }else if( refuelPayer.equals("select a payer")){
                    Toast.makeText(this, "please select a payer", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "new purchase saved", Toast.LENGTH_SHORT).show();

                    String p = mileage.getText().toString();
                    changeDotToComma(p);
                    Float refuelMileage = Float.parseFloat(p);

                    p = price.getText().toString();
                    changeDotToComma(p);
                    Float refuelPrice = Float.parseFloat(p);

                    p = liter.getText().toString();
                    changeDotToComma(p);
                    Float refuelLiter = Float.parseFloat(p);

                    /*--------- build new Refuel-Object ----------*/
                    Refuel newRefuel = new Refuel(refuelMileage, refuelPrice, refuelPayer, new Date(), refuelLiter);

                    /* ---------- return Intent (Refuel-Object) ---------- */
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("newRefuel", newRefuel);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }break;
        }// switch
    }

    @Override
    public void onItemSelected(AdapterView<?> _parent, View _view, int _position, long _id) {
        String name = _parent.getItemAtPosition(_position).toString();

        // Toast.makeText(this, "selected " + name, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> _parent) {
        Toast.makeText(this, "Please select friend!", Toast.LENGTH_SHORT).show();
    }

    private void changeDotToComma(String s){
        if(s != null && s != "") {
            if (s.contains(".")) {
                s.replace(".", ",");
            }
        }
    }
}

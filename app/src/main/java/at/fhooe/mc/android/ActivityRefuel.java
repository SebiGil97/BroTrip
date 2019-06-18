package at.fhooe.mc.android;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ActivityRefuel extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuel);

        //implements load Friends of Trip
        //----TestStart------------
        nameList=new LinkedList<String>();
        nameList.add("Max");
        nameList.add("Paul");
        nameList.add("Peter");
        //----TestEND-----------



        //----------------ListSpinner------------
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, nameList);
        Spinner spinner = (Spinner) findViewById(R.id.activity_refuel_pick_friend);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button b = null;
        b = (Button) findViewById(R.id.activity_refuel_button_save);
    }

    @Override
    public void onClick(View _v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> _parent, View _view, int _position, long _id) {


        switch (_position){
            case 0:Toast.makeText(this, nameList.get(0), Toast.LENGTH_SHORT).show();break;
            case 1:Toast.makeText(this, nameList.get(1), Toast.LENGTH_SHORT).show();break;
            case 2:Toast.makeText(this, nameList.get(2), Toast.LENGTH_SHORT).show();break;
            default:Toast.makeText(this, "no Item chosen", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> _parent) {
        Toast.makeText(this, "Please select friend!", Toast.LENGTH_SHORT).show();
    }
}

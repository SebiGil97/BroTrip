package at.fhooe.mc.android.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.widget.Toast;
import at.fhooe.mc.android.*;
import at.fhooe.mc.android.ListAdapter.PersonAdapter;
import at.fhooe.mc.android.R;
import com.google.firebase.database.*;

import static at.fhooe.mc.android.Activities.MainActivity.TAG;

public class ActivityPersons extends Activity {
    Trip currentTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        ListView lv = (ListView) findViewById(R.id.activity_persons_listView);

        currentTrip = (Trip) getIntent().getExtras().getSerializable("persons");


        PersonAdapter adapter = new PersonAdapter(this);
        List<Person> persons = currentTrip.getPersons();

        for(int i = 0;i < persons.size();i++){
            adapter.add(persons.get(i));
        }

        lv.setAdapter(adapter);
    }


    //public String get

}

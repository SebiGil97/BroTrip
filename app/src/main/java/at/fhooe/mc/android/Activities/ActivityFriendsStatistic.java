package at.fhooe.mc.android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.mc.android.Person;
import at.fhooe.mc.android.R;
import at.fhooe.mc.android.Trip;

public class ActivityFriendsStatistic extends AppCompatActivity{
    Trip currentTrip;               //bekomme ich von Gerry Intent?
    List<Person> friendsList;
    //ArrayList<String>;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_statistic);
        ListView lv=(ListView)findViewById(R.id.activity_friends_statistics_listview);

    }

    //public String get

}

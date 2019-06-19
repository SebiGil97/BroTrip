package at.fhooe.mc.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class ActivityFriendsStatistic extends AppCompatActivity {
    List<Person> friendsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_statistic);
    }

    protected void printStatistic(){

    }
}

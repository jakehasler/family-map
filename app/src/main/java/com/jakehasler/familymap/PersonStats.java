package com.jakehasler.familymap;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakehasler.familymap.helpers.EventListViewAdapter;
import com.jakehasler.familymap.helpers.FamListViewAdapter;
import com.jakehasler.familymap.model.Event;
import com.jakehasler.familymap.model.MapsActivity;
import com.jakehasler.familymap.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import static com.jakehasler.familymap.MainModel.getUser;

public class PersonStats extends AppCompatActivity {

    private ArrayList<String> currEvents = new ArrayList();
    private HashMap<String, Person> currFam = new HashMap();
    private String currPersonId;
    private EventListViewAdapter evListAdapter;
    private FamListViewAdapter famListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Configure the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Toast toast = Toast.makeText(this.getBaseContext(), MainModel.getPersonById(MainModel.getCurrPerson()).getFullName(), Toast.LENGTH_SHORT);
        toast.show();

        genInfo();
        fillInfo();

    }


    public void genInfo() {
        currEvents = new ArrayList<String>();
        currFam = new HashMap<>();
        currPersonId = MainModel.getCurrPerson();
        Person currPerson = MainModel.getPersonById(currPersonId);
        TreeSet<String> eventIds = currPerson.getEvents();

        for(String event : eventIds) {
            Event ev = MainModel.getEventById(event);
            String descrip = ev.getEventId();
            currEvents.add(descrip);
        }
    }

    public void fillInfo() {
        // Filling in Name Details
        Person currPerson = MainModel.getPersonById(currPersonId);
        TextView fName = (TextView) findViewById(R.id.statFirstName);
        fName.setText(currPerson.getfName());
        TextView lName = (TextView) findViewById(R.id.statLastName);
        lName.setText(currPerson.getlName());
        TextView gender = (TextView) findViewById(R.id.statGender);
        gender.setText(currPerson.getGender().toUpperCase());
        // Filling in Event List
        //ExpandableListAdapter evListAdapter;
        ListView eventList = (ListView)findViewById(R.id.eventList);
        evListAdapter = new EventListViewAdapter(this, currEvents);
        eventList.setAdapter(evListAdapter);
        // Filling in Family List
        ListView familyList = (ListView)findViewById(R.id.familyList);
        HashMap<Person, String> fam = MainModel.getPersonById(currPersonId).getFamPersons();
        famListAdapter = new FamListViewAdapter(this, fam);
        familyList.setAdapter(famListAdapter);
    }

    public void onFamilySelected(Person p) {
        MainModel.setCurrPerson(p.getPersonId());
        // Start a new person activity
        startActivity(new Intent(this, PersonStats.class));
    }

    public void onEventSelected(Event ev) {
        MainModel.setCurrEvent(ev.getEventId());
        // Start a new person activity
        startActivity(new Intent(this, MapsActivity.class));
    }

    // Enable back navigation
    public boolean onOptionsItemSelected(MenuItem item) {

        // Back button
        if(item.getItemId() == android.R.id.home) { //app icon in action bar clicked; go back
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

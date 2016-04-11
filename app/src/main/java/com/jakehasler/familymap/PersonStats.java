package com.jakehasler.familymap;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakehasler.familymap.model.Event;
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
    private ArrayAdapter<String> evListAdapter;
    private ArrayAdapter<String> famListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            String descrip = ev.getDetails();
            currEvents.add(descrip);
        }

        if(currPerson.getFather() != null) {
            currFam.put("Father", MainModel.getPersonById(currPerson.getFather().getPersonId()));
        }
        if(currPerson.getMother() != null) {
            currFam.put("Mother", MainModel.getPersonById(currPerson.getMother().getPersonId()));
        }
        if(currPerson.getSpouse() != null) {
            currFam.put("Spouse", MainModel.getPersonById(currPerson.getSpouse().getPersonId()));
        }

        System.out.println(currPerson.getChildren().toString());
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
        evListAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textItem, currEvents);
        eventList.setAdapter(evListAdapter);
        // Filling in Family List
        ListView familyList = (ListView)findViewById(R.id.familyList);
        ArrayList<String> fam = new ArrayList<>();
        Iterator it = currFam.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String title = (String) pair.getKey();
            Person per = (Person) pair.getValue();
            String name = (String) per.getFullName();
            String famMember = title + ": " + name;
            fam.add(famMember);
        }
        famListAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textItem, fam);
        familyList.setAdapter(famListAdapter);
    }



}

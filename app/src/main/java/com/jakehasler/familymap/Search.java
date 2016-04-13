package com.jakehasler.familymap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.jakehasler.familymap.helpers.EventListViewAdapter;
import com.jakehasler.familymap.helpers.FamListViewAdapter;
import com.jakehasler.familymap.model.Event;
import com.jakehasler.familymap.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Search extends AppCompatActivity {

    EditText searchBox;
    HashMap<Person, String> persons = new HashMap<>();
    ArrayList<String> events = new ArrayList<>();
    private EventListViewAdapter evListAdapter;
    private FamListViewAdapter famListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchBox = (EditText) findViewById(R.id.searchBox);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("s = " + s);
                genLists(s);
                displayLists();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void displayLists() {
        System.out.println("events = " + events);
        ListView eventList = (ListView)findViewById(R.id.searchedEvents);
        evListAdapter = new EventListViewAdapter(this, events, "search");
        eventList.setAdapter(evListAdapter);

        ListView familyList = (ListView)findViewById(R.id.searchedPeople);
        famListAdapter = new FamListViewAdapter(this, persons, "search");
        familyList.setAdapter(famListAdapter);
    }


    public void genLists(CharSequence s) {
        String str = s.toString();
        String lowered = str.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

        }
        events = new ArrayList<>();
        persons = new HashMap<>();
        HashMap<String, Event> evs = MainModel.getEventMap();
        HashMap<String, Person> peeps = MainModel.getPersonMap();

        Iterator it = evs.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Event ev = (Event)pair.getValue();
            if(ev.getDetails().toLowerCase().contains(lowered)) {
                events.add(ev.getEventId());
            }
        }

        Iterator it2 = peeps.entrySet().iterator();
        while(it2.hasNext()) {
            Map.Entry pair2 = (Map.Entry)it2.next();
            Person peep = (Person) pair2.getValue();
            if(peep.getFullName().toLowerCase().contains(lowered)) {
                persons.put(peep, "Family");
            }
        }
    }

    public void onEventSelected(String ev) {
        MainModel.setCurrEvent(ev);
        startActivity(new Intent(this, MapActivity.class));
    }

    public void onFamilySelected(Person p) {
        MainModel.setCurrPerson(p.getPersonId());
        // Start a new person activity
        startActivity(new Intent(this, PersonStats.class));
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

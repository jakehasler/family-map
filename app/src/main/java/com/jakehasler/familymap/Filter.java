package com.jakehasler.familymap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import com.jakehasler.familymap.model.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Filter extends AppCompatActivity {

    ArrayList<String> eventNames = new ArrayList<>();
    ArrayAdapter<String> evSwitchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        
        Iterator it = MainModel.getEventNames().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String evName = (String) pair.getKey();
            String capName = evName.substring(0, 1).toUpperCase() + evName.substring(1);
            eventNames.add(capName);
        }

        genEventSwitches();

    }

    public void genEventSwitches() {

        ListView filterSwitchList = (ListView)findViewById(R.id.filterSwitchList);
        evSwitchAdapter = new ArrayAdapter<String>(this, R.layout.filter_node, R.id.titleToReplace, eventNames);
        filterSwitchList.setAdapter(evSwitchAdapter);
        System.out.println("evSwitchAdapter = " + evSwitchAdapter.getFilter());
        System.out.println("evSwitchAdapter.getItemViewType(0); = " + evSwitchAdapter.getItemViewType(0));
        System.out.println("filterSwitchList.getChildCount(); = " + filterSwitchList.getChildCount());
        for(int i = 0; i < filterSwitchList.getChildCount(); i++) {
            View view = filterSwitchList.getChildAt(i);
            System.out.println("view = " + view);
            //view.find
        }


    }

}

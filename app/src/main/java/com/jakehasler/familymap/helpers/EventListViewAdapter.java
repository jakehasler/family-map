package com.jakehasler.familymap.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jakehasler.familymap.MainModel;
import com.jakehasler.familymap.PersonStats;
import com.jakehasler.familymap.R;
import com.jakehasler.familymap.Search;
import com.jakehasler.familymap.model.Event;
import com.jakehasler.familymap.model.MapsActivity;
import com.jakehasler.familymap.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jakehasler on 4/11/16.
 */
public class EventListViewAdapter extends BaseAdapter {

    private PersonStats personActivity;
    private Search searchActivity;
    private static LayoutInflater inflater = null;
    private ArrayList<String> events;
    private boolean isPerson = false;


    public EventListViewAdapter(Activity activity, ArrayList<String> events, String type) {
        if(type == "person") {
            this.personActivity = (PersonStats) activity;
            isPerson = true;
        }
        if(type == "search") {
            this.searchActivity = (Search) activity;
            isPerson = false;
        }
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.events = events;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        String ev = this.events.get(position);
        View cellEvent = this.inflater.inflate(R.layout.list_item, null);
        TextView personCellTextTop = (TextView) cellEvent.findViewById(R.id.textItem);
        personCellTextTop.setText(MainModel.getEventById(ev).getDetails());
        cellEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the activities method
                if(isPerson)personActivity.onEventSelected(events.get(position));
                else searchActivity.onEventSelected(events.get(position));
            }
        });

        return cellEvent;
    }

    @Override
    public int getCount() {
        return this.events.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

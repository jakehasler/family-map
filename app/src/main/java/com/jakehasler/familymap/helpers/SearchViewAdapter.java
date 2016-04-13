package com.jakehasler.familymap.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jakehasler.familymap.PersonStats;
import com.jakehasler.familymap.R;
import com.jakehasler.familymap.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jakehasler on 4/11/16.
 */
public class SearchViewAdapter extends BaseAdapter {

    private PersonStats activity;
    private static LayoutInflater inflater = null;
    private HashMap<Person, String> familyMap;
    private ArrayList<Person> family = new ArrayList<>();


    public SearchViewAdapter(Activity activity, HashMap<Person, String> familyMap) {
        this.activity = (PersonStats) activity;
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.familyMap = familyMap;
        Iterator it = this.familyMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Person per = (Person) pair.getKey();
            family.add(per);
        }
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        Person p = this.family.get(position);
        View cellEvent = this.inflater.inflate(R.layout.list_item, null);
        //ImageView personCellIcon = (ImageView) cellEvent.findViewById(R.id.familyCellIcon);
        TextView personCellTextTop = (TextView) cellEvent.findViewById(R.id.textItem);
        //TextView personCellTextBottom = (TextView) cellEvent.findViewById(R.id.familyCellTextBottom);
        String relationship = familyMap.get(p);
        personCellTextTop.setText(relationship + ": " + p.getFullName());
        cellEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the activities method
                activity.onFamilySelected(family.get(position));
            }
        });

        return cellEvent;
    }

    @Override
    public int getCount() {
        return this.family.size();
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

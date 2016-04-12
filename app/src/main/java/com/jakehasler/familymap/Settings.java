package com.jakehasler.familymap;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private String[] storySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        System.out.println("MainModel.ifLines() = " + MainModel.ifLines());
        final Switch ifLines = (Switch) findViewById(R.id.ifLines);
        ifLines.setChecked(MainModel.ifLines());
        ifLines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainModel.setIfLines(isChecked);
                ifLines.setChecked(MainModel.ifLines());
            }
        });

        this.storySpinner = new String[] {"Red", "Blue", "Green", "Purple", "Yellow"};
        Spinner spin = (Spinner) findViewById(R.id.lifeStoryColor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, storySpinner);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent.getItemAtPosition(position) = " + parent.getItemAtPosition(position));
                String color = (String)parent.getItemAtPosition(position);
                switch (color) {
                    case "Blue":
                        System.out.println("Blue");
                        MainModel.setLifeStoryColor(Color.CYAN);
                        break;
                    case "Red":
                        MainModel.setLifeStoryColor(Color.RED);
                        break;
                    case "Green":
                        MainModel.setLifeStoryColor(Color.GREEN);
                        break;
                    case "Purple":
                        MainModel.setLifeStoryColor(Color.MAGENTA);
                        break;
                    case "Yellow":
                        MainModel.setLifeStoryColor(Color.YELLOW);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}

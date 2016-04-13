package com.jakehasler.familymap;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.jakehasler.familymap.login.LoginFragment;
import com.jakehasler.familymap.model.MapFragment;

public class Settings extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener{

    private String[] storySpinner;
    private String[] treeSpinner;
    private String[] spouseSpinner;
    private static LoginFragment loginFragment = new LoginFragment();
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    private Settings activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activity = new Settings();

        final Switch ifLines = (Switch) findViewById(R.id.ifLines);
        ifLines.setChecked(MainModel.ifLines());
        ifLines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainModel.setIfLines(isChecked);
                ifLines.setChecked(MainModel.ifLines());
            }
        });

        final Switch ifTreeLines = (Switch) findViewById(R.id.ifTreeLines);
        ifTreeLines.setChecked(MainModel.ifTreeLines());
        ifTreeLines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainModel.setIfTreeLines(isChecked);
                ifTreeLines.setChecked(MainModel.ifTreeLines());
            }
        });

        final Switch ifSpouseLines = (Switch) findViewById(R.id.ifSpouseLines);
        ifSpouseLines.setChecked(MainModel.ifSpouseLines());
        ifSpouseLines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainModel.setIfSpouseLines(isChecked);
                ifSpouseLines.setChecked(MainModel.ifSpouseLines());
            }
        });

        final TextView logout = (TextView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                MainModel.setAuthToken(null);
                Intent mainIntent = new Intent(v.getContext(), MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
            }
        });

        final TextView reSync = (TextView) findViewById(R.id.resync);
        reSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LoginFragment
                Intent mainIntent = new Intent(v.getContext(), MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
            }
        });

        buildSpinners();
    }

    public void onFragmentInteraction(Uri uri) {

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


    public void buildSpinners() {

        this.storySpinner = new String[]{"Red", "Blue", "Green", "Purple", "Yellow"};
        Spinner spin = (Spinner) findViewById(R.id.lifeStoryColor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, storySpinner);
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent.getItemAtPosition(position) = " + parent.getItemAtPosition(position));
                String color = (String) parent.getItemAtPosition(position);
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

        this.treeSpinner = new String[]{"Red", "Blue", "Green", "Purple", "Yellow"};
        Spinner treeSpin = (Spinner) findViewById(R.id.treeColor);
        ArrayAdapter<String> treeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, treeSpinner);
        treeSpin.setAdapter(treeAdapter);

        treeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent.getItemAtPosition(position) = " + parent.getItemAtPosition(position));
                String color = (String) parent.getItemAtPosition(position);
                switch (color) {
                    case "Blue":
                        System.out.println("Blue");
                        MainModel.setTreeColor(Color.CYAN);
                        break;
                    case "Red":
                        MainModel.setTreeColor(Color.RED);
                        break;
                    case "Green":
                        MainModel.setTreeColor(Color.GREEN);
                        break;
                    case "Purple":
                        MainModel.setTreeColor(Color.MAGENTA);
                        break;
                    case "Yellow":
                        MainModel.setTreeColor(Color.YELLOW);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        this.spouseSpinner = new String[]{"Red", "Blue", "Green", "Purple", "Yellow"};
        Spinner spouseSpin = (Spinner) findViewById(R.id.spouseColor);
        ArrayAdapter<String> spouseAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spouseSpinner);
        spouseSpin.setAdapter(spouseAdapter);

        spouseSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent.getItemAtPosition(position) = " + parent.getItemAtPosition(position));
                String color = (String) parent.getItemAtPosition(position);
                switch (color) {
                    case "Blue":
                        System.out.println("Blue");
                        MainModel.setTreeColor(Color.CYAN);
                        break;
                    case "Red":
                        MainModel.setTreeColor(Color.RED);
                        break;
                    case "Green":
                        MainModel.setTreeColor(Color.GREEN);
                        break;
                    case "Purple":
                        MainModel.setTreeColor(Color.MAGENTA);
                        break;
                    case "Yellow":
                        MainModel.setTreeColor(Color.YELLOW);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

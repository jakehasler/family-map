package com.jakehasler.familymap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.jakehasler.familymap.model.MapFragment;

public class MapActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener{

    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Configure the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.mapFragment = new MapFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container2, this.mapFragment)
                .commit();

//        LatLng provo = new LatLng(40.246507, -111.645781);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(provo, 5));
    }


    // Determine what menu to use
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
        switch(item.toString()) {
            case "Settings":
                Intent intentSettings = new Intent(this, Settings.class);
                startActivity(intentSettings);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}

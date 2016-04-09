package com.jakehasler.familymap;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.jakehasler.familymap.login.LoginFragment;
import com.jakehasler.familymap.model.*;
import com.jakehasler.familymap.model.MapFragment;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements LoginFragment.OnFragmentInteractionListener, LoginFragment.OnCompleteListener, OnMapReadyCallback, MapFragment.OnFragmentInteractionListener {

    private static LoginFragment loginFragment = new LoginFragment();
    private static com.jakehasler.familymap.model.MapFragment mMapFragment = new MapFragment();
    //private GoogleMap googleMap = ((com.google.android.gms.maps.MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMapAsync());
    private ProgressBar spinner; // TODO: Implement this. http://www.tutorialspoint.com/android/android_loading_spinner.htm
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setScreenSize();
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            transaction.add(R.id.container, new LoginFragment()).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(MainModel.getAuthToken() != null) {
            switch(item.toString()) {
                case "Search":
                    Intent intentSearch = new Intent(this, Search.class);
                    startActivity(intentSearch);
                    break;
                case "People":
                    Intent intentPeople = new Intent(this, PersonStats.class);
                    startActivity(intentPeople);
                    break;
                case "Filter":
                    Intent intentFilter = new Intent(this, Filter.class);
                    startActivity(intentFilter);
                    break;
            }

            return true;
        }
        else {
            Toast.makeText(this, "Must Be Logged In to Use This Feature", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onMapReady(GoogleMap map) {
        LatLng provo = new LatLng(40.246507, -111.645781);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(provo, 5));
        map.addMarker(new MarkerOptions()
                .title("Provo")
                .snippet("The most awesome city in Utah.")
                .position(provo));
        // Adding all events from the map
        Iterator it = MainModel.getEventMap().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Event ev = (Event)pair.getValue();
            Person curr = MainModel.getPersonMap().get(ev.getPersonId());
            map.addMarker(new MarkerOptions()
                    .title(curr.getfName() + " " + curr.getlName())
                    .snippet(ev.getDetails())
                    .position(ev.getCoords()));
        }
    }


    public void onComplete() {
        try {
            loginFragment.getPersonsAndEvents();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(getBaseContext(), "OnComplete Called!", Toast.LENGTH_SHORT);
        toast.show();
        FragmentManager fm = this.getSupportFragmentManager();
        /*
        Instead of making a support fragment, make an instance on My Map fragment, and have that take care of everything else
         */

//        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.fragment_map, null);
        //mMapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        Toast.makeText(MainActivity.this, "Opening map...", Toast.LENGTH_SHORT).show();
        mMapFragment = new MapFragment();
        //mMapFragment.getMapAsync(this);
        fm.beginTransaction().replace(R.id.container, mMapFragment).commit();
        //View view = getLayoutInflater().inflate(R.layout.);
    }

    public void setScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        MainModel.setScreenSize(new Point(size.x, size.y));
    }

}

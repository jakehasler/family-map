package com.jakehasler.familymap;

import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.jakehasler.familymap.login.LoginFragment;
import com.jakehasler.familymap.model.Event;
import com.jakehasler.familymap.model.Person;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements LoginFragment.OnFragmentInteractionListener, LoginFragment.OnCompleteListener, OnMapReadyCallback {

    private static LoginFragment loginFragment = new LoginFragment();
    private static SupportMapFragment mMapFragment = new SupportMapFragment();
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


    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onMapReady(GoogleMap map) {
        ViewGroup.LayoutParams params = mMapFragment.getView().getLayoutParams();
        params.height = MainModel.getScreenSize().y;
        mMapFragment.getView().setLayoutParams(params);
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
            map.addMarker(new MarkerOptions().title(curr.getfName() + " " + curr.getlName()).snippet(ev.getName() + " - j" + ev.getYear()).position(ev.getCoords()));
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
        mMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        Toast.makeText(MainActivity.this, "Opening map...", Toast.LENGTH_SHORT).show();
        mMapFragment = new SupportMapFragment();
        mMapFragment.getMapAsync(this);
        fm.beginTransaction().replace(R.id.container, mMapFragment).commit();

    }

    public void setScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        MainModel.setScreenSize(new Point(size.x, size.y));
    }

}

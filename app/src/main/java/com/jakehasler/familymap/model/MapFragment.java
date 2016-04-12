package com.jakehasler.familymap.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jakehasler.familymap.MainActivity;
import com.jakehasler.familymap.MainModel;
import com.jakehasler.familymap.PersonStats;
import com.jakehasler.familymap.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mPersonView;
    private HashMap markerMap = new HashMap<Marker, ArrayList<String>>();
    private ArrayList<String> mPersonEvent = new ArrayList();

    private OnFragmentInteractionListener listener;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Creating the Map Fragment!");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        FragmentManager fm = this.getChildFragmentManager();
        SupportMapFragment smf = (SupportMapFragment) fm.findFragmentById(R.id.map);
        smf.getMapAsync(this);
        mPersonView = view.findViewById(R.id.personDetail);
        mPersonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start activity if there is a person inside of it
            if(mPersonEvent.size() == 2) {
                Intent intentPeople = new Intent(getView().getContext(), PersonStats.class);
                MainModel.setCurrPerson(mPersonEvent.get(0));
                startActivity(intentPeople);
                System.out.println(mPersonEvent.get(0));
            } else Toast.makeText(getView().getContext(), "Please Select a Marker", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (MapFragment.OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException((context.toString() + " must implement OnFragmentInteractionListener"));
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (MainModel.getCurrEvent() != null) {
            System.out.println("Navigating...");
            Event selectedEvent = MainModel.getEventById(MainModel.getCurrEvent());
            System.out.println("selectedEvent.getCoords() = " + selectedEvent.getCoords());
            LatLng coords = selectedEvent.getCoords();
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 8));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(coords, 7.0f));
        }
        else {
            LatLng provo = new LatLng(40.246507, -111.645781);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(provo, 3));
        }
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println("marker Clicked! = " + marker);
                mPersonEvent = (ArrayList)markerMap.get(marker);
                System.out.println(MainModel.getPersonById((String) mPersonEvent.get(0)).getFullName());
                System.out.println(MainModel.getEventById((String) mPersonEvent.get(1)).getDetails());
                String fullName = MainModel.getPersonById((String) mPersonEvent.get(0)).getFullName();
                String details = MainModel.getEventById((String) mPersonEvent.get(1)).getDetails();
                TextView p = (TextView)mPersonView.findViewById(R.id.mapPersonName);
                p.setText(fullName);
                TextView t = (TextView)mPersonView.findViewById(R.id.mapPersonEvent);
                t.setText(details);
                // TODO: Change M/F Icon Here!!
                return false;
            }
        });

        // Adding all events from the map
        Iterator it = MainModel.getEventMap().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Event ev = (Event)pair.getValue();
            Person curr = MainModel.getPersonMap().get(ev.getPersonId());
            Marker marker = map.addMarker(new MarkerOptions()
                    .title(curr.getfName() + " " + curr.getlName())
                    .snippet(ev.getDetails())
                    .position(ev.getCoords()));
            ArrayList<String> personEvent = new ArrayList();
            personEvent.add(curr.getPersonId());
            personEvent.add(ev.getEventId());
            markerMap.put(marker, personEvent);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

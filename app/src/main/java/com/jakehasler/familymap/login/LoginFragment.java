package com.jakehasler.familymap.login;

import android.app.Activity;
import android.graphics.PointF;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.jakehasler.familymap.MainActivity;
import com.jakehasler.familymap.MainModel;
import com.jakehasler.familymap.R;
import com.jakehasler.familymap.async.Async;
import com.jakehasler.familymap.model.Event;
import com.jakehasler.familymap.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.Date;

import static com.jakehasler.familymap.MainModel.*;

/**
 * Created by jakehasler on 3/16/16.
 */
public class LoginFragment extends Fragment implements Button.OnClickListener {

    private OnFragmentInteractionListener listener;
    private OnCompleteListener completeListener;

    private EditText username;
    private EditText password;
    private EditText host;
    private EditText port;
    private Button loginButton;
    private String authToken;
    private String personId;
    private String statusMsg;

    public LoginFragment() {
    }

    /**
     * Takes in the form data, posts to the Database, handles response whether its success or an error.
     *
     */
    public void onLogin() throws MalformedURLException {

        MainModel.setTotalUrl("http://" + this.host.getText().toString() + ":" + this.port.getText().toString());
        JSONObject loginBody = new JSONObject();
        try {
            loginBody.put("username", username.getText().toString());
            loginBody.put("password", password.getText().toString());
            JSONObject loginRes = Async.doLogin(MainModel.getTotalUrl(), loginBody);
            MainModel.setAuthToken(loginRes.getString("Authorization"));
            setUsername(loginRes.getString("userName"));
            setUser(new Person(loginRes.getString("personId")));
            JSONObject currUser = Async.getSinglePerson(MainModel.getTotalUrl(), getUser().getPersonId());
            getUser().setfName(currUser.getString("firstName"));
            getUser().setlName(currUser.getString("lastName"));
            statusMsg = "Welcome " + getUser().getfName() + " " + getUser().getlName() + "!";
            System.out.println("welcomeMsg = " + welcomeMsg);
            System.out.println("MainModel.getAuthToken() = " + MainModel.getAuthToken());
            System.out.println("MainModel.getPersonId() = " + getUser().getPersonId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(MainModel.getAuthToken() == null) {
            statusMsg = "Login Failed!";
        }

        Toast toast = Toast.makeText(this.getContext(), statusMsg, Toast.LENGTH_SHORT);
        toast.show();

        completeListener.onComplete();

    }

    public void getPersonsAndEvents() throws MalformedURLException {
        System.out.println("Inside persons and Events");
        System.out.println("totalUrl = " + MainModel.getTotalUrl());
        JSONObject events = Async.getEvents(MainModel.getTotalUrl());
        JSONObject persons = Async.getAllPersons(MainModel.getTotalUrl());
        try {
            JSONArray eventArr = events.getJSONArray("data");
            JSONArray personArr = persons.getJSONArray("data");
            for(int i = 0; i < personArr.length(); i++) {
                JSONObject obj = personArr.getJSONObject(i);
                String id = obj.getString("personID");
                String fName = obj.getString("firstName");
                String lName = obj.getString("lastName");
                String gender = obj.getString("gender");
                Person newPerson = new Person(fName, lName, id, gender);
                if(obj.has("spouse")) {
                    String spouseId = obj.getString("spouse");
                    newPerson.setSpouse(new Person(spouseId));
                }
                if(obj.has("father")) {
                    String father = obj.getString("father");
                    newPerson.setFather(new Person(father));
                }
                if(obj.has("mother")) {
                    String mother = obj.getString("mother");
                    newPerson.setMother(new Person(mother));
                }
                MainModel.addPerson(newPerson);
            }
            for(int i = 0; i < eventArr.length(); i++) {
                JSONObject obj = eventArr.getJSONObject(i);
                String id = obj.getString("eventID");
                String personId = obj.getString("personID");
                float lat = (float)obj.getDouble("latitude");
                float lon = (float)obj.getDouble("longitude");
                String country = obj.getString("country");
                String city = obj.getString("city");
                String name = obj.getString("description");
                int year = obj.getInt("year");
                Event newEvent = new Event(id, new LatLng(lat, lon), year, city, country, personId, name);
                // Adding eventID String to the person itself.
                MainModel.getPersonMap().get(personId).addEvent(id);
                MainModel.addEvent(newEvent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // TODO: Put all events into model
        System.out.println("Events and Persons loaded into the model!");

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) rootView.findViewById(R.id.login);
        username = (EditText) rootView.findViewById(R.id.username);
        password = (EditText) rootView.findViewById(R.id.password);
        host = (EditText) rootView.findViewById(R.id.host);
        port = (EditText) rootView.findViewById(R.id.port);
        loginButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (LoginFragment.OnFragmentInteractionListener) context;
            completeListener = (OnCompleteListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException((context.toString() + " must implement OnFragmentInteractionListener"));
        }
    }


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void onClick(View v) {
        listener.onFragmentInteraction(null);
        System.out.println("Button Clicked!");
        System.out.println(this.toString());
        try {
            onLogin();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "LoginFragment{" +
                "username='" + username.getText().toString() + '\'' +
                ", password='" + password.getText().toString() + '\'' +
                ", host='" + host.getText().toString() + '\'' +
                ", port='" + port.getText().toString() + '\'' +
                ", authToken='" + authToken + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    public static interface OnCompleteListener {
        public abstract void onComplete();
    }


}

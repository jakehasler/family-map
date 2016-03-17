package com.jakehasler.familymap.login;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakehasler.familymap.MainModel;
import com.jakehasler.familymap.R;
import com.jakehasler.familymap.async.Async;
import com.jakehasler.familymap.model.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

/**
 * Created by jakehasler on 3/16/16.
 */
public class LoginFragment extends Fragment implements Button.OnClickListener {

    private OnFragmentInteractionListener listener;

    private EditText username;
    private EditText password;
    private EditText host;
    private EditText port;
    private Button loginButton;
    private String authToken;
    private String personId;


    public LoginFragment() {
    }

    /**
     * Takes in the form data, posts to the Database, handles response whether its success or an error.
     *
     */
    public void onLogin() throws MalformedURLException {

        String totalUrl = "http://" + this.host.getText().toString() + ":" + this.port.getText().toString();
        JSONObject loginBody = new JSONObject();
        try {
            loginBody.put("username", username.getText().toString());
            loginBody.put("password", password.getText().toString());
            JSONObject loginRes = Async.doLogin(totalUrl, loginBody);
            MainModel.setAuthToken(loginRes.getString("Authorization"));
            MainModel.setUsername(loginRes.getString("userName"));
            MainModel.setUser(new Person(loginRes.getString("personId")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("MainModel.getAuthToken() = " + MainModel.getAuthToken());
        System.out.println("MainModel.getPersonId() = " + MainModel.getUser().getPersonId());

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


}

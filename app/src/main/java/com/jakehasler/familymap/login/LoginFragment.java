package com.jakehasler.familymap.login;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakehasler.familymap.R;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

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
     * @param formData
     */
    public void onLogin(String formData) throws MalformedURLException {
        String totalUrl = "http://" + this.host + ":" + this.port;
        URL url = new URL("http://www.android.com/");
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            System.out.println("in = " + in);;
        }
        catch(IOException e) {
            System.out.println("e = " + e);
        }
        finally{
            urlConnection.disconnect();
        }
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
        System.out.println("v = " + v);
    }


    @Override
    public String toString() {
        return "LoginFragment{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", authToken='" + authToken + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }
}

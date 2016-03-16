package com.jakehasler.familymap.login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
public class LoginFragment extends Fragment {

    private EditText username;
    private EditText password;
    private EditText host;
    private EditText port;
    private String authToken;
    private String personId;


    public LoginFragment() {
    }

    /**
     * Takes in the form data, posts to the Database, handles response whether its success or an error.
     * @param formData
     */
    public void onLogin(String formData) {
        String totalUrl = "http://" + this.host + ":" + this.port;
        URL url = null;
        try {
            url = new URL("http://www.android.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
}

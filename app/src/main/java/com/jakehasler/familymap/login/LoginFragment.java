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

import com.jakehasler.familymap.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        byte[] postData = loginBody.toString().getBytes(StandardCharsets.UTF_8);
        int reqLength = postData.length;
        String uri = "/user/login/";
        URL url = new URL(totalUrl + uri);
        System.out.println("totalUrl = " + totalUrl);
        HttpURLConnection conn = null;
        try {
            System.out.println("Trying connection");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Content-Length", Integer.toString(reqLength));
            DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
            ds.write(postData);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resOutput = new StringBuilder();
            Scanner sc = new Scanner(br);
            while(sc.hasNextLine()) {
                resOutput.append(sc.nextLine());
            }
            System.out.println("resOutput = " + resOutput.toString());
        }
        catch(IOException e) {
            System.out.println("e = " + e);
        }
        finally{
            conn.disconnect();
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

    /**
     * Login Async Task
     */
//    public class Login extends AsyncTask<URL, Integer, >
}

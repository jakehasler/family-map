package com.jakehasler.familymap;

import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jakehasler.familymap.login.LoginFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    private static LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Had to add these lines for the Network Calls?
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment()).commit();
        }
        // Fragment to notify the activity when changes to load the map activity

    }



    public void onFragmentInteraction(Uri uri) {
//        Toast toast = Toast.makeText(this, MainModel.welcomeMsg, Toast.LENGTH_SHORT);
//        toast.show();
    }
}

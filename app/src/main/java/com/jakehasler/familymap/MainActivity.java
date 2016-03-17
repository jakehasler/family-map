package com.jakehasler.familymap;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jakehasler.familymap.login.LoginFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    private static LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment()).commit();
        }

    }



    public void onFragmentInteraction(Uri uri) {
        Toast toast = Toast.makeText(this, "VIEW Loaded", Toast.LENGTH_SHORT);
        toast.show();
    }
}

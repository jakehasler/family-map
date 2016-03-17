package com.jakehasler.familymap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jakehasler.familymap.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private static LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = new LoginFragment();
        // loginFragment.onLogin("Test");
    }
}

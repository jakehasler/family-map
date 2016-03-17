package com.jakehasler.familymap;

import com.jakehasler.familymap.login.LoginFragment;
import com.jakehasler.familymap.model.Filter;
import com.jakehasler.familymap.model.Person;
import com.jakehasler.familymap.model.Settings;

/**
 * Created by jakehasler on 3/16/16.
 */
public class MainModel {

    // One instance of each object for the course of the app.
    private static String personID;
    private static String authToken;
    private static Person user;
    private static String username;
    private static Settings settings;
    private static Filter filter;
    public static String welcomeMsg;


    // TODO: Constructor here?


    public static String getPersonID() {
        return personID;
    }

    public static void setPersonID(String personID) {
        MainModel.personID = personID;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        MainModel.authToken = authToken;
    }

    public static Person getUser() {
        return user;
    }

    public static void setUser(Person user) {
        MainModel.user = user;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MainModel.username = username;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static void setSettings(Settings settings) {
        MainModel.settings = settings;
    }

    public static Filter getFilter() {
        return filter;
    }

    public static void setFilter(Filter filter) {
        MainModel.filter = filter;
    }




}

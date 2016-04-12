package com.jakehasler.familymap;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.jakehasler.familymap.login.LoginFragment;
import com.jakehasler.familymap.model.Event;
import com.jakehasler.familymap.model.Filter;
import com.jakehasler.familymap.model.Person;
import com.jakehasler.familymap.model.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

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
    private static String totalUrl;
    public static String welcomeMsg;
    public static String currentPerson;
    public static String currentEvent;
    public static HashMap<String, Event> eventMap = new HashMap<>();
    public static HashMap<String, Person> personMap = new HashMap<>();
    public static TreeMap<String, ArrayList<String>> eventNames = new TreeMap<>();
    public static HashMap<String, Event> filteredEvents = new HashMap<>();
    public static String currentFilter;
    public static Point screenSize;


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

    public static String getTotalUrl() {
        return totalUrl;
    }

    public static void setTotalUrl(String totalUrl) {
        MainModel.totalUrl = totalUrl;
    }

    public static HashMap<String, Event> getEventMap() {
        return eventMap;
    }

    public static void setEventMap(HashMap<String, Event> eventMap) {
        MainModel.eventMap = eventMap;
    }

    public static HashMap<String, Person> getPersonMap() {
        return personMap;
    }

    public static void setPersonMap(HashMap<String, Person> personMap) {
        MainModel.personMap = personMap;
    }

    public static void addEvent(Event event) {
        eventMap.put(event.getEventId(), event);
    }

    public static void addPerson(Person person) {
        personMap.put(person.getPersonId(), person);
    }

    public static Point getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(Point screenSize) {
        MainModel.screenSize = screenSize;
    }

    public static Person getPersonById(String id) {
        return personMap.get(id);
    }

    public static Event getEventById(String id) {
        return eventMap.get(id);
    }

    public static String getCurrEvent() {
        return currentEvent;
    }

    public static void setCurrEvent(String currentEvent) {
        MainModel.currentEvent = currentEvent;
    }

    public static String getCurrPerson() {
        return currentPerson;
    }

    public static void setCurrPerson(String currentPerson) {
        MainModel.currentPerson = currentPerson;
    }

    public static void addChildToId(String parentId, String childId) {
        personMap.get(parentId).addChild(childId);
    }

    public static TreeMap<String, ArrayList<String>> getEventNames() {
        return eventNames;
    }

    public static boolean ifEventName(String newName) {
        if(eventNames.containsKey(newName)) return true;
        else return false;

    }

    public static void addEventName(String name, String eventId) {
        if(eventNames.containsKey(name)) {
            eventNames.get(name).add(eventId);
        }
        else {
            ArrayList<String> ids = new ArrayList<>();
            ids.add(eventId);
            eventNames.put(name, ids);
        }
    }


}

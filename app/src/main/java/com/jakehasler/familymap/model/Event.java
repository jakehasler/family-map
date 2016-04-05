package com.jakehasler.familymap.model;

import android.graphics.PointF;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.TreeSet;

/**
 * Created by jakehasler on 3/16/16.
 */
public class Event {

    private String eventId;
    private LatLng coords;
    private int year;
    private String city;
    private String country;
    private String personId;
    private String name;

    public Event(String eventId, LatLng coords, int year, String city, String country, String personId, String name) {
        this.eventId = eventId;
        this.coords = coords;
        this.year = year;
        this.city = city;
        this.country = country;
        this.personId = personId;
        this.name = name;
    }


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LatLng getCoords() {
        return coords;
    }

    public void setCoords(LatLng coords) {
        this.coords = coords;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

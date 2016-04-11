package com.jakehasler.familymap.model;

import java.util.TreeSet;

/**
 * Created by jakehasler on 3/16/16.
 */
public class Person {

    private String fName;
    private String lName;
    private String personId;
    private String gender;
    private Person father;
    private Person mother;
    private Person spouse;
    private TreeSet<String> events;
    private TreeSet<String> children;

    public Person(String fName, String lName, String personId, String gender) {
        this.fName = fName;
        this.lName = lName;
        this.personId = personId;
        this.gender = gender;
        events = new TreeSet<>();
        children = new TreeSet<>();
    }

    public Person(String personId) {
        this.personId = personId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getFullName() {
        return fName + " " + lName;
    }


    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public TreeSet<String> getEvents() {
        return events;
    }

    public void setEvents(TreeSet<String> events) {
        this.events = events;
    }

    public void addEvent(String event) {
        events.add(event);
    }

    public void addChild(String child) {
        children.add(child);
    }

    public TreeSet<String> getChildren() {
        return children;
    }

    public void setChildren(TreeSet<String> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", personId='" + personId + '\'' +
                ", gender='" + gender + '\'' +
                ", father=" + father +
                ", mother=" + mother +
                ", spouse=" + spouse +
                ", events=" + events +
                '}';
    }
}

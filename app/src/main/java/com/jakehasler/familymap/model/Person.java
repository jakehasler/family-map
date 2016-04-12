package com.jakehasler.familymap.model;

import com.google.android.gms.maps.model.LatLng;
import com.jakehasler.familymap.MainModel;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<String> children;

    public Person(String fName, String lName, String personId, String gender) {
        this.fName = fName;
        this.lName = lName;
        this.personId = personId;
        this.gender = gender;
        events = new TreeSet<>();
        children = new ArrayList<>();
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

    public ArrayList<String> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<String> children) {
        this.children = children;
    }

    public ArrayList<String> getFamStrings() {
        ArrayList<String> currFam = new ArrayList<>();
        if(father != null) {
            currFam.add("Father: " + MainModel.getPersonById(father.getPersonId()).getFullName());
        }
        if(mother != null) {
            currFam.add("Mother: " + MainModel.getPersonById(mother.getPersonId()).getFullName());
        }
        if(spouse != null) {
            currFam.add("Spouse: " + MainModel.getPersonById(spouse.getPersonId()).getFullName());
        }
        if(children.size() > 0) {
            for(String child : children) {
                String childStr = "Child: " + MainModel.getPersonById(child).getFullName();
                currFam.add(childStr);
            }
        }
        return currFam;
    }

    public ArrayList<String> getFamIds() {
        ArrayList<String> currFam = new ArrayList<>();
        if(father != null) {
            currFam.add(father.getPersonId());
        }
        if(mother != null) {
            currFam.add(mother.getPersonId());
        }
        if(spouse != null) {
            currFam.add(spouse.getPersonId());
        }
        if(children.size() > 0) {
            for(String child : children) {
                currFam.add(child);
            }
        }
        return currFam;
    }

    public HashMap<Person, String> getFamPersons() {
        HashMap<Person, String> currFam = new HashMap<>();
        if(father != null) {
            currFam.put(MainModel.getPersonById(father.getPersonId()), "Father");
        }
        if(mother != null) {
            currFam.put(MainModel.getPersonById(mother.getPersonId()), "Mother");
        }
        if(spouse != null) {
            currFam.put(MainModel.getPersonById(spouse.getPersonId()), "Spouse");
        }
        if(children.size() > 0) {
            for(String child : children) {
                currFam.put(MainModel.getPersonById(child), "Child");
            }
        }
        return currFam;
    }

    public String getEarliestEvent() {
        String earliestEv = "";
        int earliestYear = 5000;
        if(events.size() > 0) {
            for(String ev : events) {
                Event actualEv = MainModel.getEventById(ev);
                if(actualEv.getYear() < earliestYear) {
                    earliestYear = actualEv.getYear();
                    earliestEv = actualEv.getEventId();
                }
            }
        }
        else return null;

        return earliestEv;
    }

    public ArrayList<LatLng> getEventStory() {
        ArrayList<LatLng> coords = new ArrayList<>();
        if(events.size() > 0) {
            for(String ev : events) {
                Event actualEv = MainModel.getEventById(ev);
                coords.add(actualEv.getCoords());
            }
        }
        return coords;
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

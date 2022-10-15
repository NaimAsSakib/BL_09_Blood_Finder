package com.example.bl09bloodfinder;

public class ModelClassAfterFilterAct {
    String bloodGroup, name, currentLocation;

    //empty constructor without argument is for firebase
    public ModelClassAfterFilterAct() {
    }

    public ModelClassAfterFilterAct(String bloodGroup, String name, String currentLocation) {
        this.bloodGroup = bloodGroup;
        this.name = name;
        this.currentLocation = currentLocation;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}

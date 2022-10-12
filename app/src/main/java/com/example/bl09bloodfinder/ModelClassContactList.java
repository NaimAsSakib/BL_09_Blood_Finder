package com.example.bl09bloodfinder;


public class ModelClassContactList {
    String bloodGroup, name;

    //empty constructor without argument is for firebase
    ModelClassContactList(){
    }

    public ModelClassContactList(String bloodGroup, String name) {
        this.bloodGroup = bloodGroup;
        this.name = name;
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
}

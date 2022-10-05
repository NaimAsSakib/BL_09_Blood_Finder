package com.example.bl09bloodfinder;

public interface ItemOnClickListener {
    void onItemClicked(String value,String name); //In name I will pass different data identifier names for
    // different data as I will use this one method to pass many data fromm diff fragments or I could have taken another method passing only one String
}

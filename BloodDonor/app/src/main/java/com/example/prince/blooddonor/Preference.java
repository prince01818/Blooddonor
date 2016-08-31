package com.example.nipu.blooddonor;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nipu on 12/26/2015.
 */
public class Preference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    static final String NAME="batch9";
    static  final String NAME_KEY="name";



    public Preference(Context context ) {

        sharedPreferences=context.getSharedPreferences(NAME,0);
        editor=sharedPreferences.edit();
    }

    public void save(String name){

        editor.putString(NAME_KEY,name);
        editor.commit();

    }
    public String getName(){
        String name=sharedPreferences.getString(NAME_KEY,"Not Yet Save");
        return name;
    }


}

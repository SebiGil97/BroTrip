package at.fhooe.mc.android;

import java.io.Serializable;

public class Person implements Serializable {
    private String mName;
    private int mExpenditures; //Ausgaben

    public Person(){

    }

    public Person(String name){
        mName = name;
        mExpenditures = 0;
    }

    public String getName(){
        return mName;
    }

    public int getExpenditures(){
        return mExpenditures;
    }



}

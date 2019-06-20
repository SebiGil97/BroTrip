package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.List;

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

    //GetterSetterForFirebase
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmExpenditures() {
        return mExpenditures;
    }

    public void setmExpenditures(int mExpenditures) {
        this.mExpenditures = mExpenditures;
    }


}

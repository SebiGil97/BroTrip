package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {
    private String mName;
    private double mExpPurchase;
    private double mNumberPurchase;
    private double mExpRefuel;
    private double mNumberRefuel;
    private double mExpenditures;


    public Person(){

    }

    public Person(String name){
        mName = name;
        mExpenditures = 0;
    }


    public String getName(){
        return mName;
    }


    public void addRefuel(Refuel r){
        mExpenditures = mExpenditures + r.getmCosts();
    }


    //GetterSetterForFirebase
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public double getmExpPurchase() {
        return mExpPurchase;
    }

    public void setmExpPurchase(double mExpPurchase) {
        this.mExpPurchase = mExpPurchase;
    }

    public double getmNumberPurchase() {
        return mNumberPurchase;
    }

    public void setmNumberPurchase(double mNumberPurchase) {
        this.mNumberPurchase = mNumberPurchase;
    }

    public double getmExpRefuel() {
        return mExpRefuel;
    }

    public void setmExpRefuel(double mExpRefuel) {
        this.mExpRefuel = mExpRefuel;
    }

    public double getmNumberRefuel() {
        return mNumberRefuel;
    }

    public void setmNumberRefuel(double mNumberRefuel) {
        this.mNumberRefuel = mNumberRefuel;
    }

    public double getmExpenditures() {
        return mExpenditures;
    }

    public void setmExpenditures(double mExpenditures) {
        this.mExpenditures = mExpenditures;
    }
}

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
    private double mSurplus;


    public Person(){

    }

    public Person(String name){
        mName = name;
        mExpenditures = 0;
    }


    public void addRefuel(Refuel r){
        mExpenditures = mExpenditures + r.getmCosts();
        mExpRefuel = mExpRefuel + r.getmCosts();
        mNumberRefuel++;
    }

    public void addNewRefuel(double Costs,int num){
        mExpenditures =Costs;
        mExpRefuel = Costs;
        mNumberRefuel = num;
    }

    public void deleteRefuel(Refuel r){
        mExpenditures = mExpenditures - r.getmCosts();
        mExpRefuel = mExpRefuel - r.getmCosts();
        mNumberRefuel--;
    }

    public void addPurchase(Purchase p){
        mExpenditures = mExpenditures + p.getmCosts();
        mExpPurchase = mExpPurchase + p.getmCosts();
        mNumberPurchase++;
    }

    public void addNewPurchase(double Costs,int num){
        mExpPurchase = Costs;
        mNumberPurchase = num;
    }

    public void deletePurchase(Purchase p){
        mExpenditures = mExpenditures - p.getmCosts();
        mExpPurchase = mExpPurchase - p.getmCosts();
        mNumberPurchase--;
    }

    public void calcSurplus(double total, int numPerson){
        mSurplus = mExpenditures - total/numPerson;
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

    public double getmSurplus() {
        return mSurplus;
    }

    public void setmSurplus(double surplus) {
        this.mSurplus = surplus;
    }
}

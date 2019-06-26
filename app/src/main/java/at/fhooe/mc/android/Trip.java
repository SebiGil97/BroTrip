package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Trip implements Serializable{
    private String mTripTitle;
    private String mCar;
    private double mMileage;                   // Mileage of the car when the trip starts
    private int mNumberOfPersons;
    private List<Person> mPersons;
    private double mExpPurTotal;
    private double mExpRefTotal;
    private double mExpTotal;


    public Trip() {
    }

    public Trip (String title, String car, int _mileage, LinkedList<Person> persons){
        mTripTitle = title;
        mCar = car;
        mMileage = _mileage;

        mNumberOfPersons = persons.size();
        mPersons = persons;
        mExpPurTotal = 0;
        mExpRefTotal = 0;
        mExpTotal = 0;
    }

    public void addRefuel(Refuel r){
        mExpRefTotal = mExpRefTotal + r.getmCosts();
        mExpTotal = mExpTotal + r.getmCosts();
        calcPersonSurplus(mExpTotal,getmPersons().size());
    }

    public void addPurchase(Purchase p){
        mExpPurTotal = mExpPurTotal + p.getmCosts();
        mExpTotal = mExpTotal + p.getmCosts();
        calcPersonSurplus(mExpTotal,getmPersons().size());
    }

    public void calcPersonSurplus(double total, int numPerson){
        for(int i = 0;i < numPerson;i++){
            getmPersons().get(i).calcSurplus(total,numPerson);
        }
    }
    //GetterSetterForFireBase

    public String getCar() {
        return mCar;
    }

    public String getTripTitle() {
        return mTripTitle;
    }

    public String getmTripTitle() {
        return mTripTitle;
    }

    public void setmTripTitle(String mTripTitle) {
        this.mTripTitle = mTripTitle;
    }

    public String getmCar() {
        return mCar;
    }

    public void setmCar(String mCar) {
        this.mCar = mCar;
    }

    public double getmMileage() {
        return mMileage;
    }

    public void setmMileage(int mMileage) {
        this.mMileage = mMileage;
    }

    public int getmNumberOfPersons() {
        return mNumberOfPersons;
    }

    public void setmNumberOfPersons(int mNumberOfPersons) {
        this.mNumberOfPersons = mNumberOfPersons;
    }

    public List<Person> getmPersons() {
        return mPersons;
    }

    public void setmPersons(ArrayList<Person> mPersons) {
        this.mPersons = mPersons;
    }

    public double getmExpPurTotal() {
        return mExpPurTotal;
    }

    public void setmExpPurTotal(double mExpPurTotal) {
        this.mExpPurTotal = mExpPurTotal;
    }

    public double getmExpRefTotal() {
        return mExpRefTotal;
    }

    public void setmExpRefTotal(double mExpRefTotal) {
        this.mExpRefTotal = mExpRefTotal;
    }

    public double getmExpTotal() {
        return mExpTotal;
    }

    public void setmExpTotal(double mExpTotal) {
        this.mExpTotal = mExpTotal;
    }
}

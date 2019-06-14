package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Trip {
    private String mTripTitle;
    private String mCar;
    private int mMileage;                   // Mileage of the car when the trip starts
    private int mNumberOfPersons;
    private Person[] mPersons;
    private List<Refuel> mRefuels;
    private List<Purchase> mPurchases;

    public Trip (String title, String car/*, int _mileage*/){
        mTripTitle = title;
        mCar = car;
        //mMileage = _mileage;

        mNumberOfPersons = 0;
        mPersons = new Person[10];
        mRefuels = new LinkedList<>();
        mPurchases = new LinkedList<>();
    }

    public String getCar() {
        return mCar;
    }

    public String getTripTitle() {
        return mTripTitle;
    }

    public void addPerson(Person _person){
       mPersons[mNumberOfPersons] = _person;
       mNumberOfPersons++;
    }

    public void addRefuel(Refuel _refuel){
        mRefuels.add(_refuel);
    }

    public void addPurchase(Purchase _purchase){
        mPurchases.add(_purchase);
    }
}

package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Trip implements Serializable{
    private String mTripTitle;
    private String mCar;
    private int mMileage;                   // Mileage of the car when the trip starts
    private int mNumberOfPersons;
    private List<Person> mPersons;
    private List<Refuel> mRefuels;
    private List<Purchase> mPurchases;

    public Trip (String title, String car){
        mTripTitle = title;
        mCar = car;
        mMileage = 0;

        mNumberOfPersons = 0;
        mPersons = new LinkedList<>();
        mRefuels = new LinkedList<>();
        mPurchases = new LinkedList<>();
    }

    public Trip (String title, String car, int _mileage, LinkedList<Person> persons){
        mTripTitle = title;
        mCar = car;
        mMileage = _mileage;

        mNumberOfPersons = persons.size();
        mPersons = persons;
        mRefuels = new LinkedList<>();
        mPurchases = new LinkedList<>();
    }

    public String getCar() {
        return mCar;
    }

    public String getTripTitle() {
        return mTripTitle;
    }

    public int getMileage(){
        return mMileage;
    }

    public int getNumberOfPersons(){
        return mNumberOfPersons;
    }

    public List<Person> getPersons(){
        return mPersons;
    }

    public List<Refuel> getRefuels(){
        return mRefuels;
    }

    public List<Purchase> getPurchases(){
        return mPurchases;
    }

    public void addPerson(Person _person){
       mPersons.add(_person);
       mNumberOfPersons++;
    }

    public void addRefuel(Refuel _refuel){
        mRefuels.add(_refuel);
    }

    public void addPurchase(Purchase _purchase){
        mPurchases.add(_purchase);
    }
}

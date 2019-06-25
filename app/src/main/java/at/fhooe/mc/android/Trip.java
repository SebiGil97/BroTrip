package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Trip implements Serializable{
    private String mTripTitle;
    private String mCar;
    private int mMileage;                   // Mileage of the car when the trip starts
    private int mNumberOfPersons;
    private List<Person> mPersons;
    //private List<Refuel> mRefuels = new LinkedList<Refuel>();
    //private List<Purchase> mPurchases = new LinkedList<Purchase>();

    public Trip() {
    }


    public Trip (String title, String car, int _mileage, LinkedList<Person> persons){
        mTripTitle = title;
        mCar = car;
        mMileage = _mileage;

        mNumberOfPersons = persons.size();
        mPersons = persons;
        //mRefuels = new LinkedList<Refuel>();
        //mPurchases = new LinkedList<Purchase>();
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


    public void addPerson(Person _person){
       mPersons.add(_person);
       mNumberOfPersons++;
    }

    public float calculateCosts(){
        //Refuel
        /*
        float costs=0;
        int index=0;
        while(mRefuels.size()<index){
            costs=costs+mRefuels.get(index).getmCosts();
            index++;
        }

        index=0;
        while(mPurchases.size()<index){
            costs=costs+mPurchases.get(index).getmCosts();
            index++;
        }
        */
        return 0;

    }

    //to return Costs from a special Friend
    public float calculateCosts(Person friend){
        //Refuel
        /*
        float costs=0;
        int index=0;
        while(mRefuels.size()<index){
            if(mRefuels.get(index).getmPayer().equals(friend)) {
                costs = costs + mRefuels.get(index).getmCosts();
            }
            index++;
        }

        index=0;
        while(mPurchases.size()<index){
            if(mRefuels.get(index).getmPayer().equals(friend)) {
                costs = costs + mPurchases.get(index).getmCosts();
            }
            index++;
        }
        */
        return 0;

    }

    //GetterSetterForFireBase

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

    public int getmMileage() {
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
}

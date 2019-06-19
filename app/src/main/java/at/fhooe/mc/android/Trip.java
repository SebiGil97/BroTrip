package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Trip implements Serializable{
    private String mTripTitle;
    private String mCar;
    private int mMileage;                   // Mileage of the car when the trip starts
    private int mNumberOfPersons;
    private List<Person> mPersons;
    private List<Refuel> mRefuels;
    private List<Purchase> mPurchases;

    public Trip() {
    }

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

    public float getCosts(){
        //Refuel
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

        return costs;
    }

    //to return Costs from a special Friend
    public float getCosts(Person friend){
        //Refuel
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

        return costs;
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

    public void setmPersons(List<Person> mPersons) {
        this.mPersons = mPersons;
    }

    public List<Refuel> getmRefuels() {
        return mRefuels;
    }

    public void setmRefuels(List<Refuel> mRefuels) {
        this.mRefuels = mRefuels;
    }

    public List<Purchase> getmPurchases() {
        return mPurchases;
    }

    public void setmPurchases(List<Purchase> mPurchases) {
        this.mPurchases = mPurchases;
    }
}

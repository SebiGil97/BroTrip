package at.fhooe.mc.android;

import java.io.Serializable;

public class Refuel implements Serializable {
    private int mDrivenKilometers;
    private int mCosts;
    private Person mPayer;

    public Refuel() {
    }

    public Refuel(int _drivenKilometers, int _costs, Person _payer){
        mDrivenKilometers = _drivenKilometers;
        mCosts = _costs;
        mPayer = _payer;
    }

    public int getDrivenKilometers(){
        return mDrivenKilometers;
    }

    public int getCosts(){
        return mCosts;
    }

    public Person getPayer(){
        return mPayer;
    }

    //GetterSetterForFireBase
    public int getmDrivenKilometers() {
        return mDrivenKilometers;
    }

    public void setmDrivenKilometers(int mDrivenKilometers) {
        this.mDrivenKilometers = mDrivenKilometers;
    }

    public int getmCosts() {
        return mCosts;
    }

    public void setmCosts(int mCosts) {
        this.mCosts = mCosts;
    }

    public Person getmPayer() {
        return mPayer;
    }

    public void setmPayer(Person mPayer) {
        this.mPayer = mPayer;
    }
}

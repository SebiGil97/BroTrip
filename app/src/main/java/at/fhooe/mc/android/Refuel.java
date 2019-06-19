package at.fhooe.mc.android;

import java.io.Serializable;

public class Refuel implements Serializable {
    private int mDrivenKilometers;
    private float mCosts;
    private Person mPayer;

    public Refuel() {
    }

    public Refuel(int _drivenKilometers, int _costs, Person _payer){
        mDrivenKilometers = _drivenKilometers;
        mCosts = _costs;
        mPayer = _payer;
    }

    //GetterSetterForFireBase
    public int getmDrivenKilometers() {
        return mDrivenKilometers;
    }

    public void setmDrivenKilometers(int mDrivenKilometers) {
        this.mDrivenKilometers = mDrivenKilometers;
    }

    public float getmCosts() {
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

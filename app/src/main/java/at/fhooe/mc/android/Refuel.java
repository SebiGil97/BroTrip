package at.fhooe.mc.android;

import java.io.Serializable;

public class Refuel implements Serializable {
    private int mDrivenKilometers;
    private int mCosts;
    private Person mPayer;

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
}

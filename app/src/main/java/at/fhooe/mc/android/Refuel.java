package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.Date;

public class Refuel implements Serializable {
    private int mDrivenKilometers;
    private float mCosts;
    private String mPayer;
    private Date mDate;


    public Refuel() {
    }

    public Refuel(int _drivenKilometers, int _costs, String _payer, Date _date){
        mDrivenKilometers = _drivenKilometers;
        mCosts = _costs;
        mPayer = _payer;
        mDate = _date;
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

    public String getmPayer() {
        return mPayer;
    }

    public void setmPayer(String mPayer) {
        this.mPayer = mPayer;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}

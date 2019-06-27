package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.Date;

public class Refuel implements Serializable {
    private float mDrivenKilometers;
    private float mCosts;
    private float mLiter;
    private String mPayer;
    private Date mDate;
    boolean delete;
    boolean readyDelete;


    public Refuel() {
    }

    public Refuel(float _drivenKilometers, float _costs, String _payer, Date _date, float _liter){
        mDrivenKilometers = _drivenKilometers;
        mCosts = _costs;
        mPayer = _payer;
        mDate = _date;
        mLiter = _liter;
        delete = false;
        readyDelete = false;
    }


    //GetterSetterForFireBase
    public float getmDrivenKilometers() {
        return mDrivenKilometers;
    }

    public void setmDrivenKilometers(int mDrivenKilometers) {
        this.mDrivenKilometers = mDrivenKilometers;
    }

    public float getmCosts() {
        return mCosts;
    }

    public void setmCosts(float mCosts) {
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

    public float getmLiter() {
        return mLiter;
    }

    public void setmLiter(float mLiter) {
        this.mLiter = mLiter;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isReadyDelete() {
        return readyDelete;
    }

    public void setReadyDelete(boolean readyDelete) {
        this.readyDelete = readyDelete;
    }
}

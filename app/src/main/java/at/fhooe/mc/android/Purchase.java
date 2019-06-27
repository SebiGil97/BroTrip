package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    private String mNameShop;
    private float mCosts;
    private String mPayer;
    private Date mDate;
    boolean delete;
    boolean readyDelete;

    public Purchase(String _name, float _costs, String _payer, Date date){
        mNameShop = _name;
        mCosts = _costs;
        mPayer = _payer;
        mDate = date;
        delete = false;
        readyDelete = false;
    }

    public Purchase() {
    }


    //GetterSetterforFireBase
    public String getmNameShop() {
        return mNameShop;
    }

    public void setmNameShop(String mNameShop) {
        this.mNameShop = mNameShop;
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

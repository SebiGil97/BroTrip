package at.fhooe.mc.android;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    private String mNameShop;
    private float mCosts;
    private String mPayer;
    private Date mDate;

    public Purchase(String _name, float _costs, String _payer, Date date){
        mNameShop = _name;
        mCosts = _costs;
        mPayer = _payer;
        mDate = date;
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
}

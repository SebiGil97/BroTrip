package at.fhooe.mc.android;

import java.io.Serializable;

public class Purchase implements Serializable {
    private String mNameShop;
    private float mCosts;
    private Person mPayer;

    public Purchase(String _name, float _costs, Person _payer){
        mNameShop = _name;
        mCosts = _costs;
        mPayer = _payer;
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

    public Person getmPayer() {
        return mPayer;
    }

    public void setmPayer(Person mPayer) {
        this.mPayer = mPayer;
    }
}

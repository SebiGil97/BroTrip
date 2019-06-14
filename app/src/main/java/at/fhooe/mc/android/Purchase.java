package at.fhooe.mc.android;

public class Purchase {
    private String mNameShop;
    private float mCosts;
    private Person mPayer;

    public Purchase(String _name, float _costs, Person _payer){
        mNameShop = _name;
        mCosts = _costs;
        mPayer = _payer;
    }
}

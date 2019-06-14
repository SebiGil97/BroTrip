package at.fhooe.mc.android;

public class Refuel {
    private int mDrivenKilometers;
    private int mCosts;
    private Person mPayer;

    public Refuel(int _drivenKilometers, int _costs, Person _payer){
        mDrivenKilometers = _drivenKilometers;
        mCosts = _costs;
        mPayer = _payer;
    }
}

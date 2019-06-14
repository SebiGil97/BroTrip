package at.fhooe.mc.android;

public class TripData {

    String mTripTitle = null;
    String mCar = null;
   // long mMileage = 0;
    //PersonArray...

    public TripData(String _title, String _car){
        mTripTitle = _title;
        mCar = _car;

    }

    public String getCar() {return mCar;}
    public String getTripTitle() {return mTripTitle;}
   // public long getMileage() {return mMileage;}
}

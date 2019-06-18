package at.fhooe.mc.android;

public class Person {
    private String mName;
    private int mExpenditures; //Ausgaben

    public Person(String name){
        mName = name;
        mExpenditures = 0;
    }

    public String getName(){
        return mName;
    }

    public int getExpenditures(){
        return mExpenditures;
    }



}

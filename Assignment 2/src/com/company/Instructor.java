package com.company;

public class Instructor implements User{
    private static int _id = 0;
    private String _name;
    private int _uniqueId;

    Instructor(String name){
        this._name = name;
        this._uniqueId = Instructor._id;
        Instructor._id++;
    }

    @Override
    public String getName() {
        return this._name;
    }

    @Override
    public int getID() {
        return this._uniqueId;
    }

    @Override
    public int ret_type() {
        return 0;
    }
}

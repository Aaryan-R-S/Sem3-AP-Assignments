package com.company;

public class Student implements User{
    private static int _id = 0;
    private String _name;
    private int _uniqueId;

    Student(String name){
        this._name = name;
        this._uniqueId = Student._id;
        Student._id++;
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
        return 1;
    }
}

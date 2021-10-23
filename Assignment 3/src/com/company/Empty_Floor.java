package com.company;

public class Empty_Floor extends Floor{
    Empty_Floor(int location){
        super(location, location);
        this._points = 1;
    }

    @Override
    public int isEmpty(){
        return 1;
    }
}

package com.company;

public class Elevator_Floor extends Ladder_Floor{
    Elevator_Floor(int location, int goTo){
        super(location, goTo);
        this._points = 4;
    }

    @Override
    public int isLadder(){
        return 2;
    }
}

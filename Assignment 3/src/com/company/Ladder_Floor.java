package com.company;

public class Ladder_Floor extends Floor {
    Ladder_Floor(int location, int goTo){
        super(location, goTo);
        this._points = 2;
    }

    @Override
    public int isLadder(){
        return 1;
    }
}

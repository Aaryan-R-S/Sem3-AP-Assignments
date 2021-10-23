package com.company;

public class Floor {
    protected final int _location;
    protected final int _goTo;
    protected int _points;

    Floor(int location, int goTo){
        this._location = location;
        this._goTo = goTo;
        this._points = 0;
    }

    public int get_location() {
        return this._location;
    }

    public int get_goTo() {
        return this._goTo;
    }

    public int get_points(){
        return this._points;
    }

    public int isEmpty(){
        return 0;
    }

    public int isSnake(){
        return 0;
    }

    public int isLadder(){
        return 0;
    }

}

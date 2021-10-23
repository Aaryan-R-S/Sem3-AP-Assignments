package com.company;

public class Snake_Floor extends Floor{
    Snake_Floor(int location, int goTo){
        super(location, goTo);
        this._points = -2;
    }

    @Override
    public int isSnake(){
        return 1;
    }
}

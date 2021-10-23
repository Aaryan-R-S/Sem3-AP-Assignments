package com.company;

public class King_Cobra_Floor extends Snake_Floor{
    King_Cobra_Floor(int location, int goTo){
        super(location, goTo);
        this._points = -4;
    }

    @Override
    public int isSnake(){
        return 2;
    }
}

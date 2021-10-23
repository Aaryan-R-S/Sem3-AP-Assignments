package com.company;

import java.util.Random;

public class Dice {
    private final int _noOfFaces;
    private int _faceValue;
    private static Random rand = new Random();

    Dice(int noOfFaces){
        this._noOfFaces = noOfFaces;
        this.roll();
    }

    public int get_noOfFaces(){
        return this._noOfFaces;
    }

    public int get_faceValue() {
        return this._faceValue;
    }

    public void roll(){
        this._faceValue = 1 + rand.nextInt(this._noOfFaces);
    }

    public String toString(){
        return "Dice gave " + this._faceValue;
    }

}

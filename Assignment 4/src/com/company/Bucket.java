package com.company;

import java.util.ArrayList;

public class Bucket {
    private ArrayList<Toy> _toys;

    Bucket(){
        this._toys = new ArrayList<Toy>();
    }

    public void addToy(Toy myToy){
        this._toys.add(myToy);
    }

    public ArrayList<Toy> get_toys(){
        return this._toys;
    }

    public String toString(){
        String myStr = new String();
        for (Toy t:this._toys) {
            myStr += t.get_name()+", ";
        }
        return myStr.substring(0, myStr.length()-2);
    }
}

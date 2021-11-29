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

    public String printBucket() throws NoToyException{
        String myStr = new String();
        for (Toy t:this._toys) {
            myStr += t.get_name()+", ";
        }
        try {
            return myStr.substring(0, myStr.length() - 2);
        }
        catch (Exception e){
            throw new NoToyException("You didn't win any soft toy in the game!");
        }
    }
}

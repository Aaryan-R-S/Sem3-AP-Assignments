package com.company;

public class Toy implements Cloneable{
    private String _name;

    Toy(String name){
        this._name = name;
    }

    public String get_name(){
        return this._name;
    }

    public Toy clone(){
        while(true) {
            try {
                Toy copyToy = (Toy) super.clone();
                return copyToy;
            } catch (CloneNotSupportedException e) {
                System.out.println("Encountered some error! Trying again...");
                return null;
            }
        }
    }
}

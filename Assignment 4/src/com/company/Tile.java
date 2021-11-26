package com.company;

public class Tile {
    private Toy _toy;
    private int _location;

    Tile(String toyName, int location){
        this._toy = new Toy(toyName);
        this._location = location;
    }

    public String get_toy_name() {
        return this._toy.get_name();
    }

    public Toy clone_toy() {
        return this._toy.clone();
    }

    public int get_location() {
        return this._location;
    }
}

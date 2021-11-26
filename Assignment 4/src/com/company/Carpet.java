package com.company;

import java.util.ArrayList;

public class Carpet {
    private ArrayList<Tile> _tiles;

    Carpet(){
        this._tiles = new ArrayList<Tile>();
    }

    public void addTile(String toyName, int location){
        this._tiles.add(new Tile(toyName, location));
    }

    public String get_toy(int idx){
        return this._tiles.get(idx).get_toy_name();
    }

    public Toy clone_toy(int idx){
        return this._tiles.get(idx).clone_toy();
    }
}

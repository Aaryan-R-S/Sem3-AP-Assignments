package com.company;

public class Player {
    private final String _name;
    private int _position;

    Player(String name, int position){
        this._name = name;
        this._position = position;
    }

    public String get_name() {
        return this._name;
    }

    public int get_position() {
        return this._position;
    }

    public void set_position(int position) {
        this._position = position;
    }

}

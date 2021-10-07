package com.company;

public class Vaccine {
    private String _name;           // unique;
    private int _totalDoses;        // minimum = 1;
    private int _gapBetweenDoses;   // minimum = 0;

    Vaccine(String name, int totalDoses, int gapBetweenDoses){
        this._name = name;
        this._totalDoses = totalDoses;
        this._gapBetweenDoses = gapBetweenDoses;
    }

    public String get_name() { return this._name; }
    public int get_totalDoses(){
        return this._totalDoses;
    }
    public int get_gapBetweenDoses(){
        return this._gapBetweenDoses;
    }
    public void print_details(){
        System.out.println(
                "Vaccine name: "+this._name+", "+
                "Number of doses: "+this._totalDoses+", "+
                "Gap between doses: "+this._gapBetweenDoses+" "
        );
    }
}

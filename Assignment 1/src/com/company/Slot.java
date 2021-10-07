package com.company;

public class Slot {
    private int _dayNumber;
    private int _quantity;
    private Vaccine _whichVaccine;

    Slot(int dayNumber, int quantity, Vaccine whichVaccine){
        this._dayNumber = dayNumber;
        this._quantity = quantity;
        this._whichVaccine = whichVaccine;
    }

    public int get_dayNumber() {
        return this._dayNumber;
    }
    public int get_quantity() { return this._quantity; }
    public Vaccine get_whichVaccine() { return this._whichVaccine; }
    public void book_slot(){ this._quantity -= 1; }
}

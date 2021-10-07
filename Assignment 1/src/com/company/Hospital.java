package com.company;

import java.util.ArrayList;

public class Hospital {
    static int _hospitalID = 100000;
    private String _name;
    private String _pinCode;    // 6-digit;
    private int _uniqueID;   // 6-digit; unique;
    private ArrayList<Slot> _slots;

    Hospital(String name, String pinCode){
        this._name = name;
        this._pinCode = pinCode;
        this._uniqueID = Hospital._hospitalID;
        Hospital._hospitalID += 1;
        this._slots = new ArrayList<Slot>();
    }

    public String get_name() {
        return this._name;
    }
    public String get_pinCode() {
        return this._pinCode;
    }
    public int get_uniqueID() {
        return this._uniqueID;
    }
    public void print_details(){
        System.out.println(
                "Hospital name: "+this._name+", "+
                "PinCode: "+this._pinCode+", "+
                "Unique ID: "+this._uniqueID+" "
        );
    }
    public ArrayList<Slot> get_slots() {
        return this._slots;
    }

    public void addSlot(int dayNumber, int quantity, Vaccine whichVaccine){
        Slot newSlot = new Slot(dayNumber, quantity, whichVaccine);
        this._slots.add(newSlot);
        System.out.println(
                "Slot added by Hospital: "+this._uniqueID+
                " for Day: "+newSlot.get_dayNumber()+
                " Available Quantity: "+newSlot.get_quantity()+
                " of Vaccine: "+newSlot.get_whichVaccine().get_name()+" "
        );
    }

    public void print_all_slots() {
        if(this._slots.size()==0){
            System.out.println("No slots available");
            return;
        }
        for (Slot i : this._slots) {
            if(i.get_quantity()==0){
                System.out.println(
                    "Day: " + i.get_dayNumber() + ", " +
                    "Vaccine: " + i.get_whichVaccine().get_name() + ", " +
                    "Available Quantity: " + i.get_quantity() + " [FULLY BOOKED]"
                );
            }
            else {
                System.out.println(
                    "Day: " + i.get_dayNumber() + ", " +
                    "Vaccine: " + i.get_whichVaccine().get_name() + ", " +
                    "Available Quantity: " + i.get_quantity()
                );
            }
        }
    }

    public int print_slots(Citizen person){
        if(person.get_vaccinationStatus().equals("REGISTERED")){
            int idx = 0;
            int choices = 0;
            for (Slot i : this._slots) {
                idx += 1;
                if(i.get_quantity()==0){
                    continue;
                }
                System.out.println(
                        (idx-1)+" -> "+
                                " Day: " + i.get_dayNumber() +
                                " Available Quantity: " + i.get_quantity() +
                                " of Vaccine: " + i.get_whichVaccine().get_name() + " "
                );
                choices += 1;
            }
            return choices;
        }
        else{
            String vaccineName = person.get_vaccineAdministered().get_name();
            int idx = 0;
            int choices = 0;
            for (Slot i : this._slots) {
                idx += 1;
                if(i.get_quantity()==0){
                    continue;
                }
                if(! (i.get_whichVaccine().get_name().equals(vaccineName))){
                    continue;
                }
                if(! (i.get_dayNumber() >=
                        person.get_dayOfLastDose()+person.get_vaccineAdministered().get_gapBetweenDoses())
                ){
                    continue;
                }
                System.out.println(
                        (idx-1)+" -> "+
                                " Day: " + i.get_dayNumber() +
                                " Available Quantity: " + i.get_quantity() +
                                " of Vaccine: " + i.get_whichVaccine().get_name() + " "
                );
                choices += 1;
            }
            return choices;
        }
        // return = 0 signifies no slot is available for that person
        // else something is available
    }

    public int print_slots_v(Citizen person, String vn){
        if(person.get_vaccinationStatus().equals("REGISTERED")){
            int idx = 0;
            int choices = 0;
            for (Slot i : this._slots) {
                idx += 1;
                if(i.get_quantity()==0){
                    continue;
                }
                if(! (i.get_whichVaccine().get_name().equals(vn))){
                    continue;
                }
                System.out.println(
                        (idx-1)+" -> "+
                                " Day: " + i.get_dayNumber() +
                                " Available Quantity: " + i.get_quantity() +
                                " of Vaccine: " + i.get_whichVaccine().get_name() + " "
                );
                choices += 1;
            }
            return choices;
        }
        else{
            String vaccineName = person.get_vaccineAdministered().get_name();
            int idx = 0;
            int choices = 0;
            for (Slot i : this._slots) {
                idx += 1;
                if(i.get_quantity()==0){
                    continue;
                }
                if(! (i.get_whichVaccine().get_name().equals(vaccineName))){
                    continue;
                }
                if(! (i.get_dayNumber() >=
                        person.get_dayOfLastDose()+person.get_vaccineAdministered().get_gapBetweenDoses())
                ){
                    continue;
                }
                System.out.println(
                        (idx-1)+" -> "+
                                " Day: " + i.get_dayNumber() +
                                " Available Quantity: " + i.get_quantity() +
                                " of Vaccine: " + i.get_whichVaccine().get_name() + " "
                );
                choices += 1;
            }
            return choices;
        }
        // return = 0 signifies no slot is available for that person
        // else something is available
    }

    public void give_vaccine(Citizen person, int idx){
        if(idx>=this._slots.size()){
            System.out.println("Invalid slot number has been entered");
            return;
        }
        Slot chosenSlot = this._slots.get(idx);
        person.set_dayOfLastDose(chosenSlot.get_dayNumber());
        person.set_vaccineDosesTaken();
        person.set_vaccinationStatus(chosenSlot.get_whichVaccine());
        chosenSlot.book_slot();
        System.out.println(person.get_name() + " vaccinated with " + person.get_vaccineAdministered().get_name());
    }
}

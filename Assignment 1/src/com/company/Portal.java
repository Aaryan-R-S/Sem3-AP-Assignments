package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Portal {
    private ArrayList<Vaccine> _vaccines;
    private ArrayList<Hospital> _hospitals;
    private ArrayList<Citizen> _citizens;

    Portal(){
        this._vaccines = new ArrayList<Vaccine>();
        this._hospitals = new ArrayList<Hospital>();
        this._citizens = new ArrayList<Citizen>();
    }

    public void add_vaccine(String name, int totalDoses, int gapBetweenDoses){
        if(totalDoses<=0){
            System.out.println("Number of doses of a vaccine should be at least 1");
            return;
        }
        if(gapBetweenDoses<=0 && totalDoses>=2){
            System.out.println("Gap between doses of a vaccine should be at least 1");
            return;
        }
        boolean found = false;
        for (Vaccine v: this._vaccines) {
            if(v.get_name().equals(name)){
                found = true;
                break;
            }
        }
        if(found){
            System.out.println("Vaccine: '"+name+"' already exists");
            return;
        }
        Vaccine newVaccine = new Vaccine(name, totalDoses, gapBetweenDoses);
        this._vaccines.add(newVaccine);
        newVaccine.print_details();
    }

    public void register_hospital(String name, String pinCode){
        if(pinCode.length()!=6){
            System.out.println("PinCode is a 6-digit number");
            return;
        }
        boolean found = false;
        int id = -1;
        for (Hospital h: this._hospitals) {
            if(h.get_name().equals(name) && h.get_pinCode().equals(pinCode)){
                found = true;
                id = h.get_uniqueID();
                break;
            }
        }
        if(found){
            System.out.println("This hospital has already been registered with Unique ID: " + id);
            return;
        }
        Hospital newHospital = new Hospital(name, pinCode);
        this._hospitals.add(newHospital);
        newHospital.print_details();
    }

    public void register_citizen(String name, int age, String uniqueID){
        if(age<18){
            System.out.println("Only above 18 are allowed");
            return;
        }
        if(age>140){
            System.out.println("Invalid age");
            return;
        }
        if(uniqueID.length()!=12){
            System.out.println("Unique Id is a 12-digit number");
            return;
        }
        boolean found = false;
        String id = "";
        for (Citizen c: this._citizens) {
            if(c.get_uniqueID().equals(uniqueID)){
                found = true;
                id = c.get_uniqueID();
                break;
            }
        }
        if(found){
            System.out.println("A citizen with Unique ID: " + id+" has already been registered");
            return;
        }
        Citizen person = new Citizen(name, age, uniqueID);
        this._citizens.add(person);
        person.print_details();
    }

    public void add_slot(int hospitalID, int slots){
        boolean found = false;
        Hospital f_hospital = null;
        for (Hospital h: this._hospitals) {
            if(h.get_uniqueID()==hospitalID){
                found = true;
                f_hospital = h;
            }
        }
        if(!found){
            System.out.println("No hospital with unique ID: "+hospitalID+" has been registered");
            return;
        }
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < slots; ) {
            System.out.print("Enter Day Number: ");
            int dayNum = sc.nextInt();
            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();
            System.out.println("Select vaccine: ");
            int noOfVaccines = 0;
            for (Vaccine v: this._vaccines) {
                System.out.println(noOfVaccines + " -> " + v.get_name());
                noOfVaccines += 1;
            }
            int selectVaccine = sc.nextInt();
            if(dayNum<=0){
                System.out.println("Day number should be at least 1");
                break;
            }
            if(quantity<=0){
                System.out.println("Quantity should be at least 1");
                break;
            }
            if(selectVaccine<0 || selectVaccine>=noOfVaccines){
                System.out.println("Invalid number for vaccine selection");
                break;
            }
            f_hospital.addSlot(dayNum, quantity, this._vaccines.get(selectVaccine));
            i++;
        }
    }

    public void book_slot(String uniqueId){
        boolean found = false;
        Citizen f_person = null;
        for (Citizen c: this._citizens) {
            if(c.get_uniqueID().equals(uniqueId)){
                found = true;
                f_person = c;
                break;
            }
        }
        if(!found){
            System.out.println("No citizen with unique ID: "+uniqueId+" has been registered");
            return;
        }
        if(f_person.get_vaccinationStatus().equals("FULLY VACCINATED")){
            System.out.println("Already Fully Vaccinated");
            return;
        }
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println(
                "1 -> Search by area\n" +
                "2 -> Search by Vaccine\n" +
                "3 -> Exit"
            );
            System.out.print("Enter option: ");
            int query = sc.nextInt();
            if (query == 3) {
                return;
            }
            else if(query==1){
                System.out.print("Enter PinCode: ");
                String pc = sc.next();
                found = false;
                for (Hospital h: this._hospitals) {
                    if(h.get_pinCode().equals(pc)){
                        found = true;
                        System.out.println(h.get_uniqueID() + " " + h.get_name());
                    }
                }
                if(!found){
                    System.out.println("No Hospital is available in given PinCode area");
                    return;
                }
                System.out.print("Enter Hospital ID: ");
                int id = sc.nextInt();
                found = false;
                for (Hospital h: this._hospitals) {
                    if(h.get_pinCode().equals(pc) && h.get_uniqueID()==id){
                        found = true;
                        int verdict = h.print_slots(f_person);
                        if(verdict==0){
                            System.out.println("No slots available");
                        }
                        else{
                            int chosenSlot = sc.nextInt();
                            if(chosenSlot<0){
                                System.out.println("Invalid slot number has been entered");
                            }
                            else{
                                h.give_vaccine(f_person, chosenSlot);
                            }
                        }
                        break;
                    }
                }
                if(!found){
                    System.out.println("Please enter hospital ID from the above displayed hospitals only");
                    continue;
                }
                break;
            }
            else if(query==2){
                System.out.print("Enter Vaccine name: ");
                String vn = sc.next();
                if(f_person.get_vaccinationStatus().equals("PARTIALLY VACCINATED")){
                    if(!(f_person.get_vaccineAdministered().get_name().equals(vn))){
                        System.out.println("Mixing of vaccines is not allowed. Please select following vaccine: "+f_person.get_vaccineAdministered().get_name());
                        continue;
                    }
                }
                found = false;
                for (Hospital h: this._hospitals) {
                    for (Slot s: h.get_slots()) {
                        if (s.get_whichVaccine().get_name().equals(vn)){
                            found = true;
                            System.out.println(h.get_uniqueID() + " " + h.get_name());
                            break;
                        }
                    }
                }
                if(!found){
                    System.out.println("No Hospital is available with given vaccine");
                    return;
                }
                System.out.print("Enter Hospital ID: ");
                int id = sc.nextInt();
                found = false;
                for (Hospital h: this._hospitals) {
                    if(h.get_uniqueID()==id){
                        int verdict = h.print_slots_v(f_person, vn);
                        if(verdict==0){
                            System.out.println("No slots available");
                        }
                        else{
                            int chosenSlot = sc.nextInt();
                            if(chosenSlot<0){
                                System.out.println("Invalid slot number has been entered");
                            }
                            else{
                                h.give_vaccine(f_person, chosenSlot);
                            }
                        }
                        found = true;
                        break;
                    }
                }
                if(!found){
                    System.out.println("Please enter hospital ID from the above displayed hospitals only");
                    continue;
                }
                break;
            }
            else{
                System.out.println("Invalid input number");
            }
        }
    }

    public void print_slots_hospital(int hospitalID){
        boolean found = false;
        Hospital f_hospital = null;
        for (Hospital h: this._hospitals) {
            if(h.get_uniqueID()==hospitalID){
                found = true;
                f_hospital = h;
            }
        }
        if(!found){
            System.out.println("No hospital with unique ID: "+hospitalID+" has been registered");
            return;
        }
        f_hospital.print_all_slots();
    }

    public void vaccination_status(String uniqueId){
        boolean found = false;
        Citizen f_person = null;
        for (Citizen c: this._citizens) {
            if(c.get_uniqueID().equals(uniqueId)){
                found = true;
                f_person = c;
                break;
            }
        }
        if(!found){
            System.out.println("No citizen with unique ID: "+uniqueId+" has been registered");
            return;
        }
        f_person.print_vaccinationStatus();
    }

}

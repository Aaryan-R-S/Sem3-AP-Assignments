package com.company;

public class Citizen {
    private String _name;
    private int _age;                       // minimum = 18
    private String _uniqueID;               // 12-digit; unique;
    private String _vaccinationStatus;
    private Vaccine _vaccineAdministered;
    private int _vaccineDosesTaken;
    private int _dayOfLastDose;

    Citizen(String name, int age, String uniqueID){
        this._name = name;
        this._age = age;
        this._uniqueID = uniqueID;
        this._vaccinationStatus = "REGISTERED";
        this._vaccineAdministered = null;
        this._vaccineDosesTaken = 0;
        this._dayOfLastDose = -1;
    }

    public String get_name() { return this._name; }
    public int get_age() { return this._age; }
    public String get_uniqueID() { return this._uniqueID; }
    public String get_vaccinationStatus() { return this._vaccinationStatus; }
    public Vaccine get_vaccineAdministered() { return this._vaccineAdministered; }
    public int get_vaccineDosesTaken() { return this._vaccineDosesTaken; }
    public int get_dayOfLastDose() { return this._dayOfLastDose; }

    public void print_details(){
        System.out.println(
                "Citizen name: "+this._name+", "+
                "Age: "+this._age+", "+
                "Unique ID: "+this._uniqueID+" "
        );
    }

    public void set_vaccinationStatus(Vaccine chosenVaccine) {
        if(this._vaccineAdministered == null) {
            this._vaccineAdministered = chosenVaccine;
        }
        if(this._vaccineDosesTaken==this._vaccineAdministered.get_totalDoses()){
            this._vaccinationStatus = "FULLY VACCINATED";
        }
        else{
            this._vaccinationStatus = "PARTIALLY VACCINATED";
        }
    }

    public void set_vaccineDosesTaken() {
        this._vaccineDosesTaken += 1;
    }

    public void set_dayOfLastDose(int _dayOfLastDose) {
        this._dayOfLastDose = _dayOfLastDose;
    }

    public void print_vaccinationStatus(){
        if(this._vaccinationStatus.equals("REGISTERED")){
            System.out.println("Citizen " + this._vaccinationStatus);
        }
        else if(this._vaccinationStatus.equals("PARTIALLY VACCINATED")){
            System.out.println(this._vaccinationStatus);
            System.out.println("Vaccine Given: " + this._vaccineAdministered.get_name());
            System.out.println("Number of Doses given: " + this._vaccineDosesTaken);
            System.out.println("Next Dose due date: " + (this._dayOfLastDose+this._vaccineAdministered.get_gapBetweenDoses()));
        }
        else{
            System.out.println(this._vaccinationStatus);
            System.out.println("Vaccine Given: " + this._vaccineAdministered.get_name());
            System.out.println("Number of Doses given: " + this._vaccineDosesTaken);
        }
    }
}

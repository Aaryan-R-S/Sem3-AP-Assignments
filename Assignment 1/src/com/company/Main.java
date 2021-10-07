package com.company;

import java.util.Scanner;

public class Main {

    public static void printLine(){
        System.out.println("-------------------------------");
    }

    public static void printMenu(){
        System.out.println("1. Add Vaccine\n" +
                "2. Register Hospital\n" +
                "3. Register Citizen\n" +
                "4. Add Slot for Vaccination\n" +
                "5. Book Slot for Vaccination\n" +
                "6. List all slots for a hospital\n" +
                "7. Check Vaccination Status\n" +
                "8. Exit"
        );
    }

    public static void main(String[] args) {
        Portal coWin = new Portal();
	    System.out.println("CoWin Portal initialized...");

        String name="", pinCode="", uniqueId="";
        int totalDoses=0, gap=0, age=0, hospitalId=0, slots=0, query=0;

        Scanner sc = new Scanner(System.in);
        boolean finished = false;

        while (true) {
            printLine();
            printMenu();
            printLine();

            try {
                query = sc.nextInt();

                switch (query) {
                    case 8:
                        System.out.println("End of Test Case");
                        printLine();
                        finished = true;
                        break;
                    case 1:
                        System.out.print("Vaccine Name: ");
                        name = sc.next();
                        System.out.print("Number of Doses: ");
                        totalDoses = sc.nextInt();
                        if (totalDoses == 1) {
                            gap = 0;
                        } else {
                            System.out.print("Gap between Doses: ");
                            gap = sc.nextInt();
                        }
                        coWin.add_vaccine(name, totalDoses, gap);
                        break;
                    case 2:
                        System.out.print("Hospital Name: ");
                        name = sc.next();
                        System.out.print("PinCode: ");
                        pinCode = sc.next();
                        coWin.register_hospital(name, pinCode);
                        break;
                    case 3:
                        System.out.print("Citizen Name: ");
                        name = sc.next();
                        System.out.print("Age: ");
                        age = sc.nextInt();
                        System.out.print("Unique ID: ");
                        uniqueId = sc.next();
                        coWin.register_citizen(name, age, uniqueId);
                        break;
                    case 4:
                        System.out.print("Enter Hospital ID: ");
                        hospitalId = sc.nextInt();
                        System.out.print("Enter number of Slots to be added: ");
                        slots = sc.nextInt();
                        coWin.add_slot(hospitalId, slots);
                        break;
                    case 5:
                        System.out.print("Enter patient Unique ID: ");
                        uniqueId = sc.next();
                        coWin.book_slot(uniqueId);
                        break;
                    case 6:
                        System.out.print("Enter Hospital ID: ");
                        hospitalId = sc.nextInt();
                        coWin.print_slots_hospital(hospitalId);
                        break;
                    case 7:
                        System.out.print("Enter Patient ID: ");
                        uniqueId = sc.next();
                        coWin.vaccination_status(uniqueId);
                        break;
                    default:
                        System.out.println("[ERROR] Invalid query");
                }

                if (finished) {
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("Error encountered");
                break;
            }
        }
    }
}

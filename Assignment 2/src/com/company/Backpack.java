package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Backpack {
    private ArrayList<Course> _courses;

    Backpack(){
        this._courses = new ArrayList<Course>();

        // hardcore single course for given testcase
        this._courses.add(new Course());
    }

    public void printInstructMenu(String name){
        System.out.println("-------------------------------------");
        System.out.println("Welcome "+ name);
        System.out.println("INSTRUCTOR MENU\n" +
                "1. Add class material\n" +
                "2. Add assessments\n" +
                "3. View lecture materials\n" +
                "4. View assessments\n" +
                "5. Grade assessments\n" +
                "6. Close assessment\n" +
                "7. View comments\n" +
                "8. Add comments\n" +
                "9. Logout");
        System.out.println("-------------------------------------");
    }

    public void printStudentMenu(String name){
        System.out.println("-------------------------------------");
        System.out.println("Welcome "+ name);
        System.out.println("STUDENT MENU\n" +
                "1. View lecture materials\n" +
                "2. View assessments\n" +
                "3. Submit assessment\n" +
                "4. View grades\n" +
                "5. View comments\n" +
                "6. Add comments\n" +
                "7. Logout");
        System.out.println("-------------------------------------");
    }

    public void printBackpackMenu(){
        System.out.println("-------------------------------------");
        System.out.println("Welcome to Backpack\n" +
                "1. Enter as instructor\n" +
                "2. Enter as student\n" +
                "3. Exit");
        System.out.println("-------------------------------------");
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        int num = 0, idx = 0, q = 0;

        while (done!=true) {
            this.printBackpackMenu();
            q = sc.nextInt();
            switch (q) {
                case 1:
                    System.out.println("Instructors: ");
                    num = this._courses.get(0).printInstruct();
                    if(num==0){
                        System.out.println("No instructor has been registered");
                    }
                    else{
                        idx = sc.nextInt();
                        if(idx>=0 && idx<num){
                            this._courses.get(0).logIn(idx, 0);
                            instructorInterface();
                        }
                        else {
                            System.out.println("Invalid selection");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Students: ");
                    num = this._courses.get(0).printStudent();
                    if(num==0){
                        System.out.println("No student has been registered");
                    }
                    else{
                        idx = sc.nextInt();
                        if(idx>=0 && idx<num){
                            this._courses.get(0).logIn(idx, 1);
                            studentInterface();
                        }
                        else {
                            System.out.println("Invalid selection");
                        }
                    }
                    break;

                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("{End of Test case}");
                    System.out.println("-------------------------------------");
                    done = true;
                    break;

                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }

    public void instructorInterface(){
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        int num = 0, q = 0, n = 0;
        Lecture myLec;
        Assessment myAssess;
        Comment myComm;
        String s, ss, fn;
        ArrayList<String> s_list;

        while (done!=true) {
            this.printInstructMenu(this._courses.get(0).get_logIn().getName());
            q = sc.nextInt();
            switch (q) {
                case 1:
                    System.out.println("1 -> Add Lecture Slide");
                    System.out.println("2 -> Add Lecture Video");
                    num = sc.nextInt(); sc.nextLine();
                    if(num==1){
                        System.out.print("Enter topic of slides: "); s = sc.nextLine();
                        System.out.print("Enter number of slides: "); n = sc.nextInt(); sc.nextLine();
                        s_list = new ArrayList<String>();
                        System.out.println("Enter content of slides");
                        for (int i = 0; i < n; i++) {
                            System.out.print("Content of Slide "+(i+1)+": "); ss = sc.nextLine();
                            s_list.add(ss);
                        }
                        myLec = new Slide(s, n, s_list, this._courses.get(0).get_logIn());
                        this._courses.get(0).addLecture(myLec);
                    }
                    else if(num==2){
                        System.out.print("Enter topic of video: "); s = sc.nextLine();
                        System.out.print("Enter filename of video: "); fn = sc.nextLine();
                        if(fn.length()<=4){
                            System.out.println("Please follow the following format for Lecture video: '<filename>.mp4'");
                        }
                        else if(!(fn.substring(fn.length()-4).equals(".mp4"))) {
                            System.out.println("Please follow the following format for Lecture video: '<filename>.mp4'");
                        }
                        else {
                            myLec = new Video(s, fn, this._courses.get(0).get_logIn());
                            this._courses.get(0).addLecture(myLec);
                        }
                    }
                    else{
                        System.out.println("Invalid selection");
                    }
                    break;

                case 2:
                    System.out.println("1 -> Add Assignment");
                    System.out.println("2 -> Add Quiz");
                    num = sc.nextInt(); sc.nextLine();

                    if(num==1){
                        System.out.print("Enter problem statement: "); s = sc.nextLine();
                        System.out.print("Enter max marks:"); n = sc.nextInt();
                        myAssess = new Assignment(s, n, this._courses.get(0).get_students());
                        this._courses.get(0).addAssessment(myAssess);
                    }
                    else if(num==2) {
                        System.out.print("Enter quiz question: "); s = sc.nextLine();
                        myAssess = new Quiz(s, this._courses.get(0).get_students());
                        this._courses.get(0).addAssessment(myAssess);
                    }
                    else{
                        System.out.println("Invalid selection");
                    }
                    break;

                case 3:
                    this._courses.get(0).viewLecture();
                    break;

                case 4:
                    this._courses.get(0).viewAssessment();
                    break;

                case 5:
                    System.out.println("List of assessments: ");
                    num = this._courses.get(0).viewAssessment();
                    if(num!=0) {
                        System.out.print("Enter ID of assessment to view submissions: ");
                        num = sc.nextInt();
                        this._courses.get(0).gradeAssessment(num, this._courses.get(0).get_logIn());
                    }
                    break;

                case 6:
                    num = this._courses.get(0).printOpenAssessment();
                    if(num!=0) {
                        System.out.print("Enter ID of assessment to close: ");
                        num = sc.nextInt();
                        this._courses.get(0).closeAssessment(num);
                    }
                    break;

                case 7:
                    this._courses.get(0).viewComments();
                    break;

                case 8:
                    sc.nextLine();
                    System.out.print("Enter comment: ");s = sc.nextLine();
                    myComm = new Comment(s, this._courses.get(0).get_logIn());
                    this._courses.get(0).addComment(myComm);
                    break;

                case 9:
                    this._courses.get(0).logOut();
                    done = true;
                    break;

                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }

    public void studentInterface(){
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        int num = 0, q = 0, n = 0;
        Comment myComm;
        String s;

        while (done!=true) {
            this.printStudentMenu(this._courses.get(0).get_logIn().getName());
            q = sc.nextInt();
            switch (q) {
                case 1:
                    this._courses.get(0).viewLecture();
                    break;

                case 2:
                    this._courses.get(0).viewAssessment();
                    break;

                case 3:
                    n = this._courses.get(0).printPendingAssess();
                    if(n!=0) {
                        System.out.print("Enter ID of the assessment: ");
                        num = sc.nextInt();
                        this._courses.get(0).submitAssessment(num);
                    }
                    break;

                case 4:
                    this._courses.get(0).viewGrades();
                    break;

                case 5:
                    this._courses.get(0).viewComments();
                    break;

                case 6:
                    sc.nextLine();
                    System.out.print("Enter comment: "); s = sc.nextLine();
                    myComm = new Comment(s, this._courses.get(0).get_logIn());
                    this._courses.get(0).addComment(myComm);
                    break;

                case 7:
                    this._courses.get(0).logOut();
                    done = true;
                    break;

                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
    }
}

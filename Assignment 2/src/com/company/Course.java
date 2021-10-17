package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private ArrayList<Lecture> _lectures;
    private ArrayList<Assessment> _assessments;
    private ArrayList<Instructor> _instructors;
    private ArrayList<Student> _students;
    private ArrayList<Comment> _comments;
    private User _logIn;

    Course(){
        this._instructors = new ArrayList<Instructor>();
        this._students = new ArrayList<Student>();
        this._comments = new ArrayList<Comment>();
        this._lectures = new ArrayList<Lecture>();
        this._assessments = new ArrayList<Assessment>();
        this._logIn = null;

        // hardcore for running given testcase
        this._instructors.add(new Instructor("I0"));
        this._instructors.add(new Instructor("I1"));
        this._students.add(new Student("S0"));
        this._students.add(new Student("S1"));
        this._students.add(new Student("S2"));
    }

    public ArrayList<Student> get_students(){
        return this._students;
    }

    public User get_logIn(){
        return this._logIn;
    }

    public void addLecture(Lecture myLec){  // instructor only
        this._lectures.add(myLec);
    }

    public void addAssessment(Assessment myAssess){  // instructor only
        this._assessments.add(myAssess);
    }

    public void viewLecture(){   // instructor and student both
        for (int i = 0; i < this._lectures.size(); i++) {
            this._lectures.get(i).view();
            System.out.println();
        }
        if(this._lectures.size()==0){
            System.out.println("No Lecture uploaded yet");
        }
    }

    public int viewAssessment(){   // instructor and student both
        for (int i = 0; i < this._assessments.size(); i++) {
            System.out.print("ID: "+i+" -> ");
            this._assessments.get(i).view();
        }
        if(this._assessments.size()==0){
            System.out.println("No Assessment uploaded yet");
        }
        return this._assessments.size();
    }

    public void gradeAssessment(int idx, User myInstr){   // instructor only
        int num = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose ID from these ungraded submissions");
        if(idx>=0 && idx<this._assessments.size()){
            ArrayList<String> allSubmit = this._assessments.get(idx).get_submission();
            ArrayList<User> g_by = this._assessments.get(idx).get_gradedBy();
            for (int j = 0; j < allSubmit.size(); j++) {
                if((!(allSubmit.get(j).equals(""))) && g_by.get(j)==null){
                    System.out.print("ID: "+j+" -> ");
                    System.out.println(this._students.get(j).getName());
                    num++;
                }
            }
            if(num==0){
                System.out.println("No ungraded submissions are present");
                return;
            }
            System.out.print("Enter student ID: ");
            int sID = sc.nextInt();
            if(sID>=0 && sID<this._students.size()){
                if(!(allSubmit.get(sID).equals(""))){
                    System.out.println("Submission: "+ allSubmit.get(sID));
                    System.out.println("Max Marks: "+ this._assessments.get(idx).get_maxMarks());
                    System.out.print("Marks scored: ");
                    int marks = sc.nextInt();
                    if(marks>=0 && marks<=this._assessments.get(idx).get_maxMarks()){
                        this._assessments.get(idx).get_grades().add(sID, marks);
                        this._assessments.get(idx).get_gradedBy().add(sID, myInstr);
                    }
                }
                else{
                    System.out.println("Assessment not submitted yet");
                }
            }
            else{
                System.out.println("Invalid Student ID");
            }
        }
        else{
            System.out.println("Invalid Assessment ID");
        }
    }

    public int printOpenAssessment(){
        int num=0;
        System.out.println("List of Open Assignments: ");
        for (int i = 0; i < this._assessments.size(); i++) {
            if(this._assessments.get(i).is_close()==false) {
                System.out.print("ID: " + i + " -> ");
                this._assessments.get(i).view();
                num++;
            }
        }
        if(num==0){
            System.out.println("No open assignments are present");
        }
        return num;
    }

    public void closeAssessment(int i){    // instructor only
        if(i>=0 && i<this._assessments.size()){
            this._assessments.get(i).close();
        }
        else{
            System.out.println("Invalid Assessment ID");
        }
    }

    public void addComment(Comment myComm){   // instructor and student both
       this._comments.add(myComm);
    }

    public void viewComments(){   // instructor and student both
        for (int i = 0; i < this._comments.size(); i++) {
            this._comments.get(i).view();
            System.out.println();
        }
        if(this._comments.size()==0){
            System.out.println("No comments yet");
        }
    }

    public void logIn(int idx, int ret_type){   // instructor and student both
        User myUser;
        if(ret_type==0){
            this._logIn = this._instructors.get(idx);
        }
        else{
            this._logIn = this._students.get(idx);
        }
    }

    public void logOut(){   // instructor and student both
        this._logIn = null;
    }

    public int printPendingAssess(){
        int num = 0;
        int id = this._logIn.getID();
        for (int i = 0; i < this._assessments.size(); i++) {
            if(this._assessments.get(i).get_submission().get(id).equals("") && this._assessments.get(i).is_close()==false){
                System.out.print("Id: "+i+" -> ");
                this._assessments.get(i).view();
                num++;
            }
        }
        if(num==0){
            System.out.println("No pending assessments");
        }
        return num;
    }

    public void submitAssessment(int i){      // student only
        Scanner sc = new Scanner(System.in);
        if(i>=0 && i<this._assessments.size()){
            if(this._assessments.get(i).is_close()!=false){
                System.out.println("You can't submit it as this assessment is already closed");
            }
            else {
                if(this._assessments.get(i).get_submission().get(this._logIn.getID()).equals("")) {
                    System.out.print("Submission: "); String submission = sc.nextLine();
                    if(this._assessments.get(i).ret_type()==0){
                        if(submission.length()<=4){
                            System.out.println("Please follow the following format for submission: '<filename>.zip'");
                            return;
                        }
                        if(!(submission.substring(submission.length()-4).equals(".zip"))) {
                            System.out.println("Please follow the following format for submission: '<filename>.zip'");
                            return;
                        }
                    }
                    this._assessments.get(i).get_submission().add(this._logIn.getID(), submission);
                }
                else{
                    System.out.println("Already submitted the assessment");
                }
            }
        }
        else{
            System.out.println("Invalid Assessment ID");
        }
    }

    public void viewGrades(){       // student only
        int num = 0;
        System.out.println("Graded submissions: ");
        for (int i = 0; i < this._assessments.size(); i++) {
            if(this._assessments.get(i).get_gradedBy().get(this._logIn.getID())!=null){
                this._assessments.get(i).view();
                System.out.println("[Submission: "+this._assessments.get(i).get_submission().get(this._logIn.getID())+"]");
                System.out.println("[Marks scored: "+ this._assessments.get(i).get_grades().get(this._logIn.getID())+"]");
                System.out.println("[Graded by: "+ this._assessments.get(i).get_gradedBy().get(this._logIn.getID()).getName()+"]");
                num++;
            }
        }
        if(num==0){
            System.out.println("No graded assessment yet");
        }
        System.out.println();
        num = 0;
        System.out.println("Ungraded submissions: ");
        for (int i = 0; i < this._assessments.size(); i++) {
            if(
                (this._assessments.get(i).get_gradedBy().get(this._logIn.getID())==null)
                && (!(this._assessments.get(i).get_submission().get(this._logIn.getID()).equals("")))
            ){
                this._assessments.get(i).view();
                System.out.println("[Submission -> "+this._assessments.get(i).get_submission().get(this._logIn.getID())+"]");
                num++;
            }
        }
        if(num==0){
            System.out.println("No ungraded assessment yet");
        }
    }

    public int printInstruct(){
        for (int i = 0; i < this._instructors.size(); i++) {
            System.out.println(i+" -> "+this._instructors.get(i).getName());
        }
        return this._instructors.size();
    }

    public int printStudent(){
        for (int i = 0; i < this._students.size(); i++) {
            System.out.println(i+" -> "+this._students.get(i).getName());
        }
        return this._students.size();
    }

}

package com.company;

import java.util.ArrayList;

public class Assignment implements Assessment{
    private String _problemStatement;
    private int _maxMarks;
    private boolean _close;
    private ArrayList<Integer> _grades;
    private ArrayList<String> _submission;
    private ArrayList<User> _gradedBy;

    Assignment(String problemStatement, int maxMarks, ArrayList<Student> students){
        this._problemStatement = problemStatement;
        this._maxMarks = maxMarks;
        this._close = false;
        this._grades = new ArrayList<Integer>();
        this._submission = new ArrayList<String>();
        this._gradedBy = new ArrayList<User>();
        for (int i = 0; i < students.size(); i++) {
            this._grades.add(0);
            this._submission.add("");
            this._gradedBy.add(null);
        }
    }

    @Override
    public int get_maxMarks(){
        return this._maxMarks;
    }

    @Override
    public ArrayList<Integer> get_grades(){
        return this._grades;
    }

    @Override
    public ArrayList<String> get_submission(){
        return this._submission;
    }

    @Override
    public ArrayList<User> get_gradedBy(){
        return this._gradedBy;
    }

    @Override
    public boolean is_close(){
        return this._close;
    }

    @Override
    public void view() {
        System.out.println("Assignment: "+this._problemStatement+" [Max Marks: "+this._maxMarks+"]");
    }

    @Override
    public void close() {
        this._close = true;
    }

    @Override
    public int ret_type() {
        return 0;
    }
}

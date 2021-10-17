package com.company;

import java.util.ArrayList;

public interface Assessment {
    public int get_maxMarks();
    public ArrayList<Integer> get_grades();
    public ArrayList<String> get_submission();
    public ArrayList<User> get_gradedBy();
    public boolean is_close();
    int ret_type();
    void view();
    void close();
}

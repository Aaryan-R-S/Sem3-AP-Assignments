package com.company;

public class GenericCalculator<T> {
    public Integer operate_integer(T op1, T op2){
        try{
            return (Integer)op1/(Integer)op2;
        }
        catch (ArithmeticException e){
            throw new ArithmeticException();
        }
    }

    public String operate_string(T op1, T op2){
        return (String)op1 + (String)op2;
    }
}

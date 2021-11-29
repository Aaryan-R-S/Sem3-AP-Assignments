package com.company;

public final class StringCalculator implements GenericCalculator<String>{
    @Override
    public String operate(String op1, String op2){
        return op1 + op2;
    }
}

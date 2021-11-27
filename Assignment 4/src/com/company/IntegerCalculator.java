package com.company;

public class IntegerCalculator implements GenericCalculator<Integer>{
    @Override
    public Integer operate(Integer op1, Integer op2){
        try{
            return op1/op2;
        }
        catch (ArithmeticException e){
            throw new ArithmeticException();
        }
    }
}

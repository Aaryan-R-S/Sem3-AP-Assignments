package com.company;

public sealed interface GenericCalculator <T> permits IntegerCalculator, StringCalculator{
    public T operate(T op1, T op2);
}

package com.example;

import com.example.devopsapp.Calculator;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(8, calculator.add(5, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(15, calculator.multiply(5, 3));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, calculator.divide(6, 3), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(5, 0);
    }
    @Test
    public void testPower() {
        assertEquals(8, calculator.power(2, 3));
        assertEquals(1, calculator.power(5, 0));
    }
}
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testApp() {
        assertTrue("Basic test should pass", true);
    }

    @Test
    public void testBasicCalculation() {
        assertEquals(4, 2 + 2);
    }
}
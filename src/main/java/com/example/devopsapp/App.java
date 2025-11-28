package com.example.devopsapp;

public class App {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("DevOps CI/CD Application Started!");
        System.out.println("5 + 3 = " + calculator.add(5, 3));
    }
}
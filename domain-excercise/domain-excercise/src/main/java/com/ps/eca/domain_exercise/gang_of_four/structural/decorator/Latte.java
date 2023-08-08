package com.ps.eca.domain_exercise.gang_of_four.structural.decorator;

class Latte implements Coffee {
    @Override
    public String getDescription() {
        return "Latte";
    }

    @Override
    public double cost() {
        return 3.5;
    }
}

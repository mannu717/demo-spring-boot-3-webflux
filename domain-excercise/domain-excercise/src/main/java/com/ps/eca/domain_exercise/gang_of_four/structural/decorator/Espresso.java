package com.ps.eca.domain_exercise.gang_of_four.structural.decorator;

// Concrete component (base coffee drinks)
class Espresso implements Coffee {
    @Override
    public String getDescription() {
        return "Espresso";
    }

    @Override
    public double cost() {
        return 2.0;
    }
}

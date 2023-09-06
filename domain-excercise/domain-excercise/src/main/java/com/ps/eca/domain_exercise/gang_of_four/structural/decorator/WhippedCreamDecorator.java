package com.ps.eca.domain_exercise.gang_of_four.structural.decorator;

class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whipped Cream";
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 1.0;
    }
}

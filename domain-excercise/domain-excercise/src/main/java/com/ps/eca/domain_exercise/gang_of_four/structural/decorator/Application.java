package com.ps.eca.domain_exercise.gang_of_four.structural.decorator;

public class Application {
    public static void main(String[] args) {
        // Order a latte with milk and whipped cream
        Coffee latte = new Latte();
        latte = new MilkDecorator(latte);
        latte = new WhippedCreamDecorator(latte);

        System.out.println("Order: " + latte.getDescription());
        System.out.println("Total cost: $" + latte.cost());
    }
}

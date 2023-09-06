package com.ps.eca.domain_exercise.gang_of_four.creational.builder;

public class Application {
    public static void main(String[] args) {
        // Create a computer using the builder
        Computer computer = new Computer.ComputerBuilder("Intel i7", "16GB", "512GB SSD")
                .graphicsCard("NVIDIA GeForce RTX 3080")
                .motherboard("ASUS ROG Strix Z590-E")
                .build();

        // Print the computer details
        System.out.println(computer);
    }
}

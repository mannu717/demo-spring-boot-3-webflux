package com.ps.eca.domain_exercise.gang_of_four.creational.builder;

class Computer {
    private String cpu;
    private String memory;
    private String storage;
    private String graphicsCard;
    private String motherboard;

    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.memory = builder.memory;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.motherboard = builder.motherboard;
    }

    // Getter methods for components (optional)
    public String getCpu() {
        return cpu;
    }

    public String getMemory() {
        return memory;
    }

    public String getStorage() {
        return storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public String getMotherboard() {
        return motherboard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", memory='" + memory + '\'' +
                ", storage='" + storage + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", motherboard='" + motherboard + '\'' +
                '}';
    }

    // Builder class
    static class ComputerBuilder {
        private String cpu;
        private String memory;
        private String storage;
        private String graphicsCard;
        private String motherboard;

        public ComputerBuilder(String cpu, String memory, String storage) {
            this.cpu = cpu;
            this.memory = memory;
            this.storage = storage;
        }

        public ComputerBuilder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public ComputerBuilder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

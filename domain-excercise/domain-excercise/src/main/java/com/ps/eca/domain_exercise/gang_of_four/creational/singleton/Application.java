package com.ps.eca.domain_exercise.gang_of_four.creational.singleton;

public class Application {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        try {
            logger.log("Application started");

            for (int i = 0; i < 5; i++) {
                logger.log("Task " + i + " completed");
            }

            int result = 10 / 0;

            logger.log("Application finished");
        } catch (Exception e) {
            logger.log("Exception: " + e.getMessage());
        } finally {
            logger.close();
        }
    }
}


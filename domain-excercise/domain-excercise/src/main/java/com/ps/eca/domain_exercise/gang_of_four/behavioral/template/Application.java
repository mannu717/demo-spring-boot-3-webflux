package com.ps.eca.domain_exercise.gang_of_four.behavioral.template;

public class Application {

    public static void main(String[] args) {
        DatabaseAccess postgresDatabase = new PostgreSQLDatabaseAccess("postgresql://localhost/db");
        DatabaseAccess mysqlDatabase = new MySQLDatabaseAccess("mysql://localhost/db");

        System.out.println("Performing CRUD operations for PostgreSQL database:");
        postgresDatabase.performCRUDOperations();

        System.out.println("\nPerforming CRUD operations for MySQL database:");
        mysqlDatabase.performCRUDOperations();
    }
}

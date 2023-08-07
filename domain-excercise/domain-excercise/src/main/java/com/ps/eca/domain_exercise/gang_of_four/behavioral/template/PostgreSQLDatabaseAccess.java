package com.ps.eca.domain_exercise.gang_of_four.behavioral.template;

public class PostgreSQLDatabaseAccess extends DatabaseAccess {

    public PostgreSQLDatabaseAccess(String connectionString) {
        super(connectionString);
    }

    @Override
    protected void connect() {
        System.out.println("Connecting to PostgreSQL database: " + connectionString);
    }

    @Override
    protected void create() {
        System.out.println("Creating record in PostgreSQL database");
    }

    @Override
    protected void read() {
        System.out.println("Reading record from PostgreSQL database");
    }

    @Override
    protected void update() {
        System.out.println("Updating record in PostgreSQL database");
    }

    @Override
    protected void delete() {
        System.out.println("Deleting record from PostgreSQL database");
    }

    @Override
    protected void disconnect() {
        System.out.println("Disconnecting from PostgreSQL database");
    }
}

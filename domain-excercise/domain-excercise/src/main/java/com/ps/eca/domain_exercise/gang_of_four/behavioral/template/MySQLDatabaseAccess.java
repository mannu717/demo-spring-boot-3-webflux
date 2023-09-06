package com.ps.eca.domain_exercise.gang_of_four.behavioral.template;

public class MySQLDatabaseAccess extends DatabaseAccess {

    public MySQLDatabaseAccess(String connectionString) {
        super(connectionString);
    }

    @Override
    protected void connect() {
        System.out.println("Connecting to MySQL database: " + connectionString);
    }

    @Override
    protected void create() {
        System.out.println("Creating record in MySQL database");
    }

    @Override
    protected void read() {
        System.out.println("Reading record from MySQL database");
    }

    @Override
    protected void update() {
        System.out.println("Updating record in MySQL database");
    }

    @Override
    protected void delete() {
        System.out.println("Deleting record from MySQL database");
    }

    @Override
    protected void disconnect() {
        System.out.println("Disconnecting from MySQL database");
    }
}

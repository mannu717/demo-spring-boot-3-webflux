package com.ps.eca.domain_exercise.gang_of_four.behavioral.template;

abstract class DatabaseAccess {
    protected String connectionString;

    public DatabaseAccess(String connectionString) {
        this.connectionString = connectionString;
    }

    public final void performCRUDOperations() {
        connect();
        create();
        read();
        update();
        delete();
        disconnect();
    }

    protected abstract void connect();

    protected abstract void create();

    protected abstract void read();

    protected abstract void update();

    protected abstract void delete();

    protected abstract void disconnect();
}


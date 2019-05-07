package com.cegeka.academy;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Insert with statementt
        DatabaseManager.insertWithStatement();
        //Insert with prepared statement
        DatabaseManager.insertWithPreparedStatement();
        //Select with statement
        DatabaseManager.selectWithStatement();
        //Select with prepared statement
        DatabaseManager.selectWithPreparedStatement();
        //Delete with statement
        DatabaseManager.deleteWithStatement();
        //Delete with prepared statement
        DatabaseManager.deleteWithPreparedStatement();
        //Update with statement
        DatabaseManager.updateWithStatement();
        //Update with prepared statement
        DatabaseManager.updateWithPreparedStatement();

        //TODO CRUD for Player table
        //TODO display all players for a specified team
    }
}

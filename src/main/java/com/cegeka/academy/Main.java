package com.cegeka.academy;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Insert with statement
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


        PlayerRepository.insertWithStatement();
        //Insert with prepared statement
        PlayerRepository.insertWithPreparedStatement();
        //Select with statement
        PlayerRepository.selectWithStatement();
        //Select with prepared statement
        PlayerRepository.selectWithPreparedStatement();
        //Delete with statement
        PlayerRepository.deleteWithStatement();
        //Delete with prepared statement
        PlayerRepository.deleteWithPreparedStatement();
        //Update with statement
        PlayerRepository.updateWithStatement();
        //Update with prepared statement
        PlayerRepository.updateWithPreparedStatement();

        DatabaseManager.displayTeamPlayers("FCSB");
        DatabaseManager.displayTeamPlayersNumber();
    }
}

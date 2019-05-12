package com.cegeka.academy;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {
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

        //TODO CRUD for Player table
        //TODO display all players for a specified team
        //TODO nr de jucatori ai fiecarei echipe
        DatabaseManager.insertWithPreparedStatementPlayer();
        DatabaseManager.selectWithPreparedStatementPlayer();
        DatabaseManager.updateWithPreparedStatementForPlayer();
        DatabaseManager.deleteWithStatementForPlayer();
        DatabaseManager.selectWithStatementPlayersForASpecificTeam("FCSB");
        DatabaseManager.selectWithStatementNumberOfPlayersForATeam();

        //TODO HOMEWORK

       // HomeworkDatabase.createTables();
        HomeworkDatabase.insertWithPreparedStatementForStationTable("roma","italy",16252,1883);
        HomeworkDatabase.insertWithPreparedStatementForStationTable("roma","italy",16252,1883);
        HomeworkDatabase.insertWithPreparedStatementForStationTable("bucuresti","romania",263553,876262);
        HomeworkDatabase.insertWithPreparedStatementForStationTable("tel aviv","israel",96252,23383);
        HomeworkDatabase.selectWithPreparedStatementForEX1();
        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("ion",9,2500);
        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("maria",2,1000);
        HomeworkDatabase.selectWithPreparedStatementForEX2(10,2000);
        HomeworkDatabase.insertWithPreparedStatementForCityTable("bucuresti","ro","d1",3888);
        HomeworkDatabase.insertWithPreparedStatementForCityTable("o1","jpn","d1",12000);
        HomeworkDatabase.insertWithPreparedStatementForCityTable("o1","jpn","d1",23000);
        HomeworkDatabase.insertWithPreparedStatementForCityTable("o1","jpn","d1",89000);
        HomeworkDatabase.selectWithPreparedStatementForEX3("jpn");
        HomeworkDatabase.insertWithPreparedStatementForStudentTable("ana",8);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(0,20);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(10,20);
        HomeworkDatabase.selectWithPreparedStatementForEX4();
        HomeworkDatabase.insertWithPreparedStatementForHackerTable("maria");
        HomeworkDatabase.insertWithPreparedStatementForHackerTable("vasile");
        HomeworkDatabase.insertWithPreparedStatementForHackerTable("ion");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date data=sdf.parse("2019-09-02");
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        HomeworkDatabase.insertWithPreparedStatementForSubmissionTable(sqlDate,1,20);
        HomeworkDatabase.selectWithPreparedStatementForEX6();
    }
}

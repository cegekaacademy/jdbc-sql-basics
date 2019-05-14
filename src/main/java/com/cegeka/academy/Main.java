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
        DatabaseManager.insertInPlayerWithPreparedStatement();
        DatabaseManager.selectFromPlayeWithPreparedStatement();
        DatabaseManager.deleteFromPlayerWithPreparedStatement();
        DatabaseManager.updatePlayerWithPreparedStatement();

        //TODO display all players for a specified team
        DatabaseManager.selectPlayerByTeam();

        //TODO display number of players for each team
        DatabaseManager.selectNoOfPlayersForEachTeam();

        //Homework
        HomeworkDatabase.insertWithPreparedStatementForStationTable("roma", "it", 16252, 1883);
        HomeworkDatabase.insertWithPreparedStatementForStationTable("brasov", "ro", 347, 123);
        HomeworkDatabase.insertWithPreparedStatementForStationTable("paris", "fr", 16252, 1883);
        HomeworkDatabase.insertWithPreparedStatementForStationTable("barcelona", "sp", 16252, 1883);
        HomeworkDatabase.selectCityNamesEndingWithVowels();

        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("Rose", 15, 1868);
        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("Angela", 1, 3443);
        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("Frank", 17, 1608);
        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("Michael", 6, 2017);
        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("Todd", 5, 3396);
        HomeworkDatabase.insertWithPreparedStatementForEmployeeTable("Joe", 9, 3573);
        HomeworkDatabase.selectListOfEmployeesHavingSalaryGreaterThanAValueAndWhoHaveBeenEmployeesForLessThanGivenMonth(2000, 10);

        HomeworkDatabase.insertWithPreparedStatementForCityTable("bucuresti", "ro", "d1", 3888);
        HomeworkDatabase.insertWithPreparedStatementForCityTable("tokio", "JPN", "d1", 1000);
        HomeworkDatabase.insertWithPreparedStatementForCityTable("osaka", "JPN", "d1", 2000);
        HomeworkDatabase.insertWithPreparedStatementForCityTable("nagasaki", "JPN", "d1", 3000);
        HomeworkDatabase.selectThePopulationForAGivenCountry("JPN");

        HomeworkDatabase.insertWithPreparedStatementForStudentTable("julia", 88);
        HomeworkDatabase.insertWithPreparedStatementForStudentTable("samantha", 68);
        HomeworkDatabase.insertWithPreparedStatementForStudentTable("maria", 99);
        HomeworkDatabase.insertWithPreparedStatementForStudentTable("scarlet", 78);
        HomeworkDatabase.insertWithPreparedStatementForStudentTable("ashley", 63);
        HomeworkDatabase.insertWithPreparedStatementForStudentTable("jane", 81);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(0, 9);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(10, 19);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(20, 29);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(30, 39);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(40, 49);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(50, 59);                  
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(60, 69);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(70, 79);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(80, 89);
        HomeworkDatabase.insertWithPreparedStatementForGradeTable(90, 99);

        HomeworkDatabase.selectStudentsWhoHaveGradesGreaterThen8();

        HomeworkDatabase.insertWithPreparedStatementForHackerTable("joe");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date data = sdf.parse("2018-10-19");
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        HomeworkDatabase.insertWithPreparedStatementForSubmissionTable(sqlDate, 1, 31);
        HomeworkDatabase.selectTheTotalNumberOfUniqueHackersWhoMadeAtLeastOneSubmissionEachDayAndFindTheHackerIdAndNameOfHackerWhoMadeMaximumNumberOfSubmissionsEachDay();

    }

}

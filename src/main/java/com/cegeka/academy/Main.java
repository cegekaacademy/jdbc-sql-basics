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

        //TODO CRUD for Player table
        //TODO display all players for a specific team
        //TODO display number of players for a team

        DatabaseManager.insertWithPreparedStatementPlayer();
        DatabaseManager.selectWithStatementPlayer();
        DatabaseManager.deleteWithPreparedStatementPlayer();
        DatabaseManager.updateWithPreparedStatementPlayer();
        DatabaseManager.selectWithStatementPlayersForASpecificTeam("CFR Cluj");
        DatabaseManager.selectWithStatementNumberOfPlayersForATeam();

        HomeworkDatabase.createTables();
        System.out.println("***********************EX1*************************");
        HomeworkDatabase.insertWithPrepareStatementStation();
        HomeworkDatabase.selectCityNamesEndingWithVowels();

        System.out.println("***********************EX2*************************");
        HomeworkDatabase.insertWithPrepareStatementEmployee();
        HomeworkDatabase.selectListOfEmployeesNameHavingSalaryGreaterThanAValueAndWhoHaveBeenEmployeesForLessThanGivenMonth(2000,10);

        System.out.println("***********************EX3*************************");
        HomeworkDatabase.insertWithPrepareStatementCity();
        HomeworkDatabase.selectTheSumOfThePopulationForAGivenCountry("JPN");

        System.out.println("***********************EX4*************************");
        HomeworkDatabase.insertWithPrepareStatementStudents();
        HomeworkDatabase.insertWithPrepareStatementGrades();
        HomeworkDatabase.selectExerciseFour();

        System.out.println("***********************EX6*************************");
        HomeworkDatabase.insertWithPrepareStatementHeackers();
        HomeworkDatabase.insertWithPrepareStatementSubmissions();
        HomeworkDatabase.selectTheTotalNumberOfUniqueHackersWhoMadeAtLeastOneSubmissionEachDayAndFindTheHackerIdAndNameOfHackerWhoMadeMaximumNumberOfSubmissionsEachDay();
    }


}

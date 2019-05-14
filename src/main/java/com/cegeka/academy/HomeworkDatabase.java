package com.cegeka.academy;

import java.sql.*;

public class HomeworkDatabase {

    public static void homeworkQuery1() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT city FROM station " +
                "WHERE LOWER(SUBSTR(city,LENGTH(city),1)) IN ('a','e','i','o','u')");

        showResults(resultSet);

        connection.close();
    }

    public static void homeworkQuery2() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT name FROM employee WHERE salary > 2000 AND months < 10;");

        showResults(resultSet);

        connection.close();
    }

    public static void homeworkQuery3() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT IF(G.Grade <= 7, NULL, S.Name) as name, S.Marks, G.Grade as Name " +
                        "FROM Grades G, Students S WHERE S.Marks BETWEEN G.min_mark AND G.max_mark " +
                        "ORDER BY G.Grade DESC, S.Name ASC;");
        showResults(resultSet);

        connection.close();
    }

    public static void homeworkQuery4() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT h.hacker_id, h.name\n" +
                        "FROM submissions s\n" +
                        "INNER JOIN challenges c\n" +
                        "ON s.challenge_id = c.challenge_id\n" +
                        "INNER JOIN difficulty d\n" +
                        "ON c.difficulty_level = d.difficulty_level \n" +
                        "INNER JOIN hackers h\n" +
                        "ON s.hacker_id = h.hacker_id\n" +
                        "WHERE s.score = d.score AND c.difficulty_level = d.difficulty_level\n" +
                        "GROUP BY h.hacker_id, h.name\n" +
                        "HAVING COUNT(s.hacker_id) > 1\n" +
                        "ORDER BY COUNT(s.hacker_id) DESC, s.hacker_id ASC");
        showResults(resultSet);

        connection.close();
    }

    public static void homeworkQuery5() throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseManager.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery("SELECT \n" +
                        "submission_date ,\n" +
                        "\n" +
                        "( \n" +
                        "SELECT COUNT(DISTINCT hacker_id)  \n" +
                        "FROM Submissions s2  \n" +
                        "WHERE s2.submission_date = s1.submission_date AND   \n" +
                        "(SELECT COUNT(DISTINCT s3.submission_date) \n" +
                        " FROM \n" +
                        " Submissions s3 WHERE s3.hacker_id = s2.hacker_id AND s3.submission_date < s1.submission_date) =     dateDIFF(s1.submission_date , '2016-03-01')) ,\n" +
                        "\n" +
                        "(SELECT hacker_id  FROM submissions s2 where s2.submission_date = s1.submission_date \n" +
                        "GROUP BY hacker_id ORDER BY COUNT(submission_id) DESC, hacker_id limit 1) AS id,\n" +
                        "(SELECT name FROM hackers where hacker_id = id)\n" +
                        "FROM \n" +
                        "(SELECT DISTINCT submission_date FROM submissions) s1\n" +
                        "GROUP BY submission_date");
        showResults(resultSet);

        connection.close();
    }

    public static void showResults(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnsCount = resultSetMetaData.getColumnCount();
        String outputString;
        while(resultSet.next()) {
            outputString = "";
            for (int i = 0; i < columnsCount; i++) {
                if (resultSetMetaData.getColumnTypeName(i).equals("VARCHAR")) {
                    String stringResult = resultSet.getString(resultSetMetaData.getColumnName(i));
                    outputString += resultSetMetaData.getColumnName(i) + " " + stringResult;
                } else if (resultSetMetaData.getColumnTypeName(i).equals("INT")) {
                    int integerResult = resultSet.getInt(resultSetMetaData.getColumnName(i));
                    outputString += resultSetMetaData.getColumnName(i) + " " + integerResult;
                } else if (resultSetMetaData.getColumnTypeName(i).equals("DATE")) {
                    Date dateResult = resultSet.getDate(resultSetMetaData.getColumnName(i));
                    outputString += resultSetMetaData.getColumnName(i) + " " + dateResult;
                }

                outputString += " ";
            }

            System.out.println(outputString);
        }
    }
}

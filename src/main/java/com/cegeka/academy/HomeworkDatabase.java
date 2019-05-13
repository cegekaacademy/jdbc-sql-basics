package com.cegeka.academy;

import java.sql.*;

public class HomeworkDatabase {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka_v2",
                        "root", "admin123!@#");
    }

    //ex1

    public static void selectCitiesWithPreparedStatement() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select distinct city from station where city like ? " +
                "or city like ? or city like ? " +
                "or city like ? or city like ?");
        preparedStatement.setString(1, "%a");
        preparedStatement.setString(2, "%e");
        preparedStatement.setString(3, "%i");
        preparedStatement.setString(4, "%o");
        preparedStatement.setString(5, "%u");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetCities(resultSet);

        preparedStatement.close();
        connection.close();
    }

    private static void showResultSetCities(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String city = resultSet.getString("city");

            System.out.println("City: " + city);
        }
    }

    //ex2

    public static void selectEmployeesWithPreparedStatement() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select name from employee where salary > ? and months < ? order by employee_id;");
        preparedStatement.setInt(1, 2000);
        preparedStatement.setInt(2, 10);
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultEmployees(resultSet);

        preparedStatement.close();
        connection.close();
    }

    private static void showResultEmployees(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String name = resultSet.getString("name");

            System.out.println("Employee: " + name);
        }
    }

}

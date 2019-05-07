package com.cegeka.academy;

import java.sql.*;

public class PlayerRepository {
    public static void insertWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Statement");

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        //int result = statement.executeUpdate("insert into team values(1001,'CegekaAcademy',20)");
        //int result = statement.executeUpdate("insert into team values(null,'CegekaAcademy',20)");
        int result = statement.executeUpdate("insert into player(name, number, id_team, position) values('CegekaAcademy',20, 1, 'Attack')");
        System.out.println("Result: " + result);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void insertWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Prepared Statement");

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into player(name, number, id_team, position) values(?,?, ?, ?)");
        preparedStatement.setString(1, "Cegeka Academy 2");
        preparedStatement.setInt(2, 30);
        preparedStatement.setInt(3, 1);
        preparedStatement.setString(4, "Attack");

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        System.out.println("Select all");
        ResultSet resultSet = statement.executeQuery("select * from player");
        showPlayerResultSetValues(resultSet);

        System.out.println("\nSelect By name");
        resultSet = statement.executeQuery("select * from player where name LIKE 'Cegeka Academy%'");
        showPlayerResultSetValues(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    public static void selectWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = DatabaseManager.getConnection();

        System.out.println("Select all");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        showPlayerResultSetValues(resultSet);

        System.out.println("\nSelect by name");
        preparedStatement = connection.prepareStatement("select * from team where name LIKE ?");
        preparedStatement.setString(1, "Cegeka Academy");
        resultSet = preparedStatement.executeQuery();
        showPlayerResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void deleteWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Statement");
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate("delete from player where id=1006");
        System.out.println("Result: " + result + "\n");
        ResultSet resultSet = statement.executeQuery("select * from player");
        showPlayerResultSetValues(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void deleteWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Prepared Statement");
        Connection connection = DatabaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from player where id=?");
        preparedStatement.setLong(1, 1007);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result + "\n");

        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        showPlayerResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void updateWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Update with statement");
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate("update player set name='Cegeka Academy 3' where name='CegekaAcademy'");
        System.out.println("Result: " + result + "\n");
        ResultSet resultSet = statement.executeQuery("select * from player");
        showPlayerResultSetValues(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void updateWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Update with prepared statement");
        Connection connection = DatabaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update player set name=? where name=?");
        preparedStatement.setString(1, "Cegeka Academy 4");
        preparedStatement.setString(2, "Cegeka Academy 2");

        int result = preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Result: " + result + "\n");
        showPlayerResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    private static void showPlayerResultSetValues(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer number = resultSet.getInt("number");
            String position = resultSet.getString("position");

            System.out.println("Player: " + id + ", " + name + ", " + number + ", " + position);
        }
    }
}

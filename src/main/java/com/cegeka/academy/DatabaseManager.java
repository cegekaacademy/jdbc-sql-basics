package com.cegeka.academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // va lua driver-ul de mysql si il urca in memorie
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka",
                        "root", "Ozzyeste1");
    }

    public static void insertWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Statement");
        //comment

        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        //int result = statement.executeUpdate("insert into team values(1001,'CegekaAcademy',20)");
        //int result = statement.executeUpdate("insert into team values(null,'CegekaAcademy',20)");
        // executeUpadate se foloseste pt modificari in bd
        //executeQuery = cand nu se fac modificari
        int result = statement.executeUpdate("insert into team(name,points) values('CegekaAcademy',20)");
        System.out.println("Result: " + result);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void insertWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Prepared Statement");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into team(name,points) values(?,?)");
        preparedStatement.setString(1, "Cegeka Academy 2");
        preparedStatement.setInt(2, 30);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        System.out.println("Select all");
        ResultSet resultSet = statement.executeQuery("select * from team");
        showResultSetValues(resultSet);

        System.out.println("\nSelect By name");
        resultSet = statement.executeQuery("select * from team where name='FCSB'");
        showResultSetValues(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    public static void selectWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();

        System.out.println("Select all");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from team");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);

        System.out.println("\nSelect by name");
        preparedStatement = connection.prepareStatement("select * from team where name=?");
        preparedStatement.setString(1, "FCSB");
        resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void deleteWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate("delete from team where id=1006");
        System.out.println("Result: " + result + "\n");
        ResultSet resultSet = statement.executeQuery("select * from team");
        showResultSetValues(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void deleteWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from team where id=?");
        preparedStatement.setLong(1, 1007);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result + "\n");

        preparedStatement = connection.prepareStatement("select * from team");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void updateWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Update with statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate("update team set name='Cegeka Academy 3' where name='CegekaAcademy'");
        System.out.println("Result: " + result + "\n");
        ResultSet resultSet = statement.executeQuery("select * from team");
        showResultSetValues(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void updateWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Update with prepared statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update team set name=? where name=?");
        preparedStatement.setString(1, "Cegeka Academy 4");
        preparedStatement.setString(2, "Cegeka Academy 2");

        int result = preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("select * from team");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Result: " + result + "\n");
        showResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }



    public static void insertWithPreparedStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Prepared Statement Player");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into player (name, number, id_team, position) values(?,?,?,?)");
        preparedStatement.setString(1, "Cegeka Academy 2");
        preparedStatement.setInt(2, 30);
        preparedStatement.setInt(3,1);
        preparedStatement.setString(4,"Attack");

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectWithStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement Player");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        System.out.println("Select all players");
        ResultSet resultSet = statement.executeQuery("select * from player");
        showResultSetValuesPlayer(resultSet);

        System.out.println("\nSelect By name");
        resultSet = statement.executeQuery("select * from player where name='Cegeka Academy 2'");
        showResultSetValuesPlayer(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    private static void showResultSetValues(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer points = resultSet.getInt("points");

            System.out.println("Team: " + id + ", " + name + ", " + points);
        }
    }

    public static void deleteWithPreparedStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Prepared Statement Player");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from player where id=?");
        preparedStatement.setLong(1, 1);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result + "\n");

        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValuesPlayer(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void updateWithPreparedStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Update with prepared statement Player");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update player set name=? where name=?");
        preparedStatement.setString(1, "Cegeka Academy 4");
        preparedStatement.setString(2, "Cegeka Academy 2");

        int result = preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Result: " + result + "\n");
        showResultSetValuesPlayer(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectWithStatementPlayersForATeam() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();


        System.out.println("\nSelect By name");
        ResultSet resultSet = statement.executeQuery("select p.name from player p, team t where p.id_team = t.id and t.name = 'FCSB';");
        //showResultSetValues(resultSet);
        while (resultSet.next()) {
            String name = resultSet.getString("name");

            System.out.println("Player " + name);
        }

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    public static void selectWithStatementNumberOfPlayersForATeam() throws SQLException, ClassNotFoundException {
        System.out.println("Number of players for a team");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select t.name, count(p.id) from player p, team t where p.id_team = t.id group by t.name;");
        //showResultSetValues(resultSet);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int noOfPlayers = resultSet.getInt("count(p.id)");

            System.out.println(name + " has " + noOfPlayers + " players");
        }

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }


    private static void showResultSetValuesPlayer(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer number = resultSet.getInt("number");
            Integer id_team = resultSet.getInt("id_team");
            String position = resultSet.getString("position");

            System.out.println("Team: " + id + ", " + name + ", " + number + "," + id_team + "," + position);
        }
    }



}

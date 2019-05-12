package com.cegeka.academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // va lua driver-ul de mysql si il urca in memorie
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka",
                        "root", "Ozzyeste1");
    }

    public static void insertWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        //int result = statement.executeUpdate("insert into team values(1001,'CegekaAcademy',20)");
        //int result = statement.executeUpdate("insert into team values(null,'CegekaAcademy',20)");
        // executeUpdate se foloseste pt modificari in bd
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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("points");
        showResultSetValues(resultSet, columns);


        System.out.println("\nSelect By name");
        resultSet = statement.executeQuery("select * from team where name='FCSB'");
        showResultSetValues(resultSet, columns);

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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("points");
        showResultSetValues(resultSet, columns);

        System.out.println("\nSelect by name");
        preparedStatement = connection.prepareStatement("select * from team where name=?");
        preparedStatement.setString(1, "FCSB");
        resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet, columns);

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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("points");
        showResultSetValues(resultSet, columns);

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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("points");
        showResultSetValues(resultSet, columns);

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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("points");
        showResultSetValues(resultSet, columns);

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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("points");
        showResultSetValues(resultSet, columns);

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
        preparedStatement.setInt(3, 1);
        preparedStatement.setString(4, "Attack");

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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("number");
        columns.add("id_team");
        columns.add("position");
        showResultSetValues(resultSet, columns);


        System.out.println("\nSelect By name");
        resultSet = statement.executeQuery("select * from player where name='Cegeka Academy 2'");
        showResultSetValues(resultSet, columns);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    private static void showResultSetValues(ResultSet resultSet, ArrayList<String> columns) throws SQLException {
        String result = "";
        while (resultSet.next()) {
            for (String name : columns) {
                result += resultSet.getObject(name) + ",";
            }
            result = result.substring(0, result.length() - 1);
            result += "\n";

        }
        System.out.println(result);
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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("number");
        columns.add("id_team");
        columns.add("position");
        showResultSetValues(resultSet, columns);

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
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("name");
        columns.add("number");
        columns.add("id_team");
        columns.add("position");
        showResultSetValues(resultSet, columns);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectWithStatementPlayersForASpecificTeam(String teamName) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        System.out.println("\nSelect players for a specific team");
        ResultSet resultSet = statement.executeQuery("select p.name, t.name from player p, team t where p.id_team = t.id and t.name = '" + teamName +"';");
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("t.name");
        columns.add("p.name");
        showResultSetValues(resultSet,columns);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    public static void selectWithStatementNumberOfPlayersForATeam() throws SQLException, ClassNotFoundException {
        System.out.println("Number of players for a team");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select t.name, count(p.id) from player p, team t where p.id_team = t.id group by t.name;");
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("name");
        columns.add("count(p.id)");
        showResultSetValues(resultSet, columns);
        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

}

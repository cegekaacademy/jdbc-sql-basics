package com.cegeka.academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka",
                        "root", "georgiana123");
    }

    public static void insertWithStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Statement");

        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        //int result = statement.executeUpdate("insert into team values(1001,'CegekaAcademy',20)");
        //int result = statement.executeUpdate("insert into team values(null,'CegekaAcademy',20)");
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

    private static void showResultSetValues(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer points = resultSet.getInt("points");

            System.out.println("Team: " + id + ", " + name + ", " + points);
        }
    }

    //pentru tabela player
    public static void insertWithPreparedStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Prepared Statement la player");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into player(name, number, id_team, position) values(?,?,?,?)");
        preparedStatement.setString(1, "Cegeka Academy 2");
        preparedStatement.setInt(2, 10);
        preparedStatement.setInt(3,2);
        preparedStatement.setString(4,"atac");

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    private static void showResultSetValuesForPlayers(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer number = resultSet.getInt("number");
            Integer id_team = resultSet.getInt("id_team");
            String position = resultSet.getString("name");

            System.out.println("Players: " + id + ", " + name + ", " + name+","+id_team+", "+position);
        }
    }

    public static void selectWithPreparedStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement for player ");
        Connection connection = getConnection();

        System.out.println("Select all");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValuesForPlayers(resultSet);

        System.out.println("\nSelect by name");
        preparedStatement = connection.prepareStatement("select * from team where name=?");
        preparedStatement.setString(1, "Popescu Mihai");
        resultSet = preparedStatement.executeQuery();
        showResultSetValuesForPlayers(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    public static void updateWithPreparedStatementForPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Update with prepared statement for player");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update player set name=? where name=?");
        preparedStatement.setString(1, "Popescu Mihai");
        preparedStatement.setString(2, "Vasile Ion");

        int result = preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Result: " + result + "\n");
        showResultSetValuesForPlayers(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void deleteWithStatementForPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Statement for player");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate("delete from player where id=1");
        System.out.println("Result: " + result + "\n");
        ResultSet resultSet = statement.executeQuery("select * from player");
        showResultSetValuesForPlayers(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    private static void showResultSetValuesForPlayers1(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String team=resultSet.getString("t.name");
            String name = resultSet.getString("p.name");
            Integer number = resultSet.getInt("p.number");
            String position = resultSet.getString("p.position");

            System.out.println("Team: "+team + "Echipa: "+ name + ", " + number+","+position);
        }
    }
    public static void selectWithStatementPlayersForASpecificTeam() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement by team");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        System.out.println("Select all");
        ResultSet resultSet = statement.executeQuery("select t.name,p.name,p.number,p.position from player p inner join team t on t.id=p.id_team and t.name='FCSB';\n");
        showResultSetValuesForPlayers1(resultSet);


        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }
}

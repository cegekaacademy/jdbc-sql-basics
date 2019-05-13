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
                .getConnection("jdbc:mysql://localhost:3306/cegeka_v2",
                        "root", "admin123!@#");
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

    public static void insertPlayerWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert into player with Prepared Statement");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into player(name,number,id_team,position) values(?,?,?,?)");
        preparedStatement.setString(1, "Tatarusanu");
        preparedStatement.setInt(2, 12);
        preparedStatement.setInt(3, 3);
        preparedStatement.setString(4, "Goalkeeper");


        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectPlayersWithPreparedStatement()throws SQLException, ClassNotFoundException {
        System.out.println("Select players with prepared statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from player");

        System.out.println("Select all");
        ResultSet resultSet = preparedStatement.executeQuery();
        showPlayerResultSetValues(resultSet);
        System.out.println("-----------------------------------------");

        System.out.println("Select names and number from team with id 3");
        preparedStatement = connection.prepareStatement("select * from player where id_team=?");
        preparedStatement.setInt(1, 3);
        resultSet = preparedStatement.executeQuery();
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
            Long id_team = resultSet.getLong("id_team");
            String position = resultSet.getString("position");

            System.out.println("Player: " + id + ", " + name + ", " + number + "," + id_team + ", " + position);
        }
    }

    private static void showJoinPlayersResultSetValues(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String player_name = resultSet.getString("name");
            Integer number = resultSet.getInt("number");
            String position = resultSet.getString("position");
            String team_name = resultSet.getString("name");

            System.out.println("Player: " + player_name + ", " + number + ", " + position + "," + team_name);
        }
    }

    private static void showCountPlayersResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String team_name = resultSet.getString("name");
            Integer number = resultSet.getInt("count(player.id)");

            System.out.println("Team: " + team_name + ", number of players: " + number);
        }
    }

    public static void deleteFromPlayerWhitPreparedStatement() throws ClassNotFoundException, SQLException{
        System.out.println("Delete from player where name='Tatarusanu'");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("delete from player where name=?");
        preparedStatement.setString(1, "Tatarusanu");

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result + "\n");

        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        showPlayerResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void updatePlayerWithPreparedStatement() throws ClassNotFoundException, SQLException {
        System.out.println("Update player");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("update player set name=? where name=?");
        preparedStatement.setString(1, "Aliber");
        preparedStatement.setString(2,"Denis Alibec");
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: "+ result + "\n");

        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        showPlayerResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectPlayersFromTeamPreparedStatement() throws ClassNotFoundException, SQLException {
        System.out.println("Select players from team FCSB");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select player.name, player.number, player.position, team.name from player join" +
                " team on player.id_team=team.id where team.name=?");
        preparedStatement.setString(1, "FCSB");
        ResultSet resultSet = preparedStatement.executeQuery();
        showJoinPlayersResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectNumberOfPlayersFromEachTeam() throws ClassNotFoundException, SQLException {
        System.out.println("Select number of players from each team");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select team.name, count(player.id) from" +
                " player join team on player.id_team=team.id group by team.name;");
        ResultSet resultSet = preparedStatement.executeQuery();
        showCountPlayersResultSet(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

}

package com.cegeka.academy;

import java.sql.*;

import static com.cegeka.academy.DatabaseManager.getConnection;

public class PlayerDBManager {

    public static void insertWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Prepared Statement");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into player(name,number) values(?,?)");
        preparedStatement.setString(1, "Player 1");
        preparedStatement.setInt(2, 10);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();

        System.out.println("Select number of players for each team");


        PreparedStatement preparedStatement = connection.prepareStatement("select t.name, COUNT(*) FROM team t,player p where t.id=p.id_team GROUP BY id_team; ");

        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);

        System.out.println("\nSelect all players for a specified team");
        preparedStatement = connection.prepareStatement("select * from player where id_team=?");
        preparedStatement.setString(1, "1");
        resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void deleteWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from player where id=?");
        preparedStatement.setLong(1, 2);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result + "\n");

        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void updateWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Update with prepared statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update player set name=? where name=?");
        preparedStatement.setString(1, "Popescu Mihai");
        preparedStatement.setString(2, "Player 2");

        int result = preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Result: " + result + "\n");
        showResultSetValues(resultSet);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }


    private static void showResultSetValues(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (resultSet.next()) {

            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print( rsmd.getColumnName(i)+ ": "+ columnValue);
            }
            System.out.println("");
        }
    }



}

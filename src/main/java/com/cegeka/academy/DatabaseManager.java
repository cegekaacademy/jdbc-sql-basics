package com.cegeka.academy;

import exception.InvalidParametersException;

import java.security.InvalidParameterException;
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
                .getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", Joda.pass);
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

    public static void insertPlayer(String nume, int number, int id_team, String position) throws SQLException, ClassNotFoundException, InvalidParametersException {

        if(id_team < 0)
            throw new InvalidParametersException();
        if(number < 0)
            throw new InvalidParametersException();
        if(nume == null)
            throw new InvalidParametersException();
        if(nume.length() == 0)
            throw new InvalidParametersException();
        if(position == null)
            throw new InvalidParametersException();
        if(position.length() == 0)
            throw new InvalidParametersException();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into player values(null,?,?,?,?);");
        preparedStatement.setString(1,  nume);
        preparedStatement.setInt(2 ,number);
        preparedStatement.setInt(3, id_team);
        preparedStatement.setString(4, position);
        int result = preparedStatement.executeUpdate();
        System.out.println("Insert result: " + result+" \n");
        preparedStatement.close();
        connection.close();
    }

    public static void updatePlayer(int id,int id_team,String name, int number, String position) throws SQLException, ClassNotFoundException, InvalidParametersException {
        Connection connection = getConnection();
        String statement = "update player set ";
        int flag = 0;
        if(id < 0)
            throw new InvalidParameterException();
        if(id_team < 0)
            throw new InvalidParametersException();
        if(name != null && name.length() > 0)
        {
            statement += "name = ?";
            flag |= 1;
        }
        if(number > 0)
        {
            if(flag == 0)
            {
                statement += "number = ?";
            }
            else
            {
                statement += ", number = ?";
            }
            flag |= 2;
        }

        if(position != null && position.length() > 0)
        {
            if(flag == 0)
            {
                statement += "position=? ";
            }
            else
            {
                statement += ", position=? ";
            }
            flag |= 4;
        }

        if(flag != 0)
        {
            statement += " where id=?"+" and id_team=?;";
        }
        else
            throw new InvalidParametersException();
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        int parameters = 0;
        if((flag & 1) == 1)
        {
            preparedStatement.setString(++parameters, name);
        }
        if((flag & 2) == 2)
        {
            preparedStatement.setInt(++parameters, number);
        }
        if((flag & 4) == 4)
        {
            preparedStatement.setString(++parameters,position);
        }
        preparedStatement.setInt(++parameters, id);
        preparedStatement.setInt(++parameters, id_team);
        int result = preparedStatement.executeUpdate();
        System.out.println("Update result: "+result);
        preparedStatement.close();
        connection.close();
    }


    public static void selectPlayer(String name, int number, int id_team, String position) throws SQLException, ClassNotFoundException, InvalidParametersException {
        Connection connection = getConnection();
        String statement = "select * from player where ";
        int flag = 0;

        if(name != null && name.length() > 0)
        {
            statement += "name = ?";
            flag |= 1;
        }

        if(number > 0)
        {
            if(flag == 0)
            {
                statement += "number = ?";
            }
            else
            {
                statement += " and number = ?";
            }
            flag |= 2;
        }

        if(position != null && position.length() > 0)
        {
            if(flag == 0)
            {
                statement += "position = ? ";
            }
            else
            {
                statement += " and position = ? ";
            }
            flag |= 4;
        }

        if(id_team > 0)
        {
            if(flag == 0)
            {
                statement += "id_team = ? ";
            }
            else
            {
                statement += "and id_team = ? ";
            }
            flag |= 8;
        }

        statement += ";";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        int parameters = 0;
        if((flag & 1) == 1)
        {
            preparedStatement.setString(++parameters, name);
        }
        if((flag & 2) == 2)
        {
            preparedStatement.setInt(++parameters, number);
        }
        if((flag & 4) == 4)
        {
            preparedStatement.setString(++parameters,position);
        }
        if((flag & 8) == 8)
        {
            preparedStatement.setInt(++parameters, id_team);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            Long sId = resultSet.getLong("id");
            String sName = resultSet.getString("name");
            String sPosition = resultSet.getString("position");
            Long sNumber = resultSet.getLong("number");
            Long sIdTeam = resultSet.getLong("id_team");
            System.out.println(sId+" "+sName+" "+sPosition+" "+sNumber+" "+sIdTeam);
        }
        preparedStatement.close();
        connection.close();
    }

    public static void deletePlayer(String name, int number, int id_team, String position) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String statement = "delete from player where ";
        int flag = 0;

        if(name != null && name.length() > 0)
        {
            statement += "name = ?";
            flag |= 1;
        }

        if(number > 0)
        {
            if(flag == 0)
            {
                statement += "number = ?";
            }
            else
            {
                statement += " and number = ?";
            }
            flag |= 2;
        }

        if(position != null && position.length() > 0)
        {
            if(flag == 0)
            {
                statement += "position = ? ";
            }
            else
            {
                statement += " and position = ? ";
            }
            flag |= 4;
        }

        if(id_team > 0)
        {
            if(flag == 0)
            {
                statement += "id_team = ? ";
            }
            else
            {
                statement += "and id_team = ? ";
            }
            flag |= 8;
        }

        statement += ";";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        int parameters = 0;
        if((flag & 1) == 1)
        {
            preparedStatement.setString(++parameters, name);
        }
        if((flag & 2) == 2)
        {
            preparedStatement.setInt(++parameters, number);
        }
        if((flag & 4) == 4)
        {
            preparedStatement.setString(++parameters,position);
        }
        if((flag & 8) == 8)
        {
            preparedStatement.setInt(++parameters, id_team);
        }

        int result = preparedStatement.executeUpdate();
        System.out.println("Update result: "+result);

        preparedStatement.close();
        connection.close();
    }

    public static void selectFromTeam(int id_team) throws SQLException, ClassNotFoundException, InvalidParametersException {
        Connection connection = getConnection();
        if(id_team < 0)
            throw new InvalidParametersException();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from player where id_team = ?;");
        preparedStatement.setInt(1,id_team);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            Long sId = resultSet.getLong("id");
            String sName = resultSet.getString("name");
            String sPosition = resultSet.getString("position");
            Long sNumber = resultSet.getLong("number");
            Long sIdTeam = resultSet.getLong("id_team");
            System.out.println(sId+" "+sName+" "+sPosition+" "+sNumber+" "+sIdTeam);
        }
        preparedStatement.close();
        connection.close();
    }
}

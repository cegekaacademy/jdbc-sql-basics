package com.cegeka.academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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

        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"points");

        System.out.println("Select all");
        ResultSet resultSet = statement.executeQuery("select * from team");
        showResultSetValues(resultSet,hashView);

        System.out.println("\nSelect By name");
        resultSet = statement.executeQuery("select * from team where name='FCSB'");

        showResultSetValues(resultSet,hashView);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    public static void selectWithPreparedStatement() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"points");
        System.out.println("Select all");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from team");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet,hashView);

        System.out.println("\nSelect by name");
        preparedStatement = connection.prepareStatement("select * from team where name=?");
        preparedStatement.setString(1, "FCSB");
        resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet,hashView);

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
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"points");
        showResultSetValues(resultSet,hashView);

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
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"points");
        showResultSetValues(resultSet,hashView);

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
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"points");
        showResultSetValues(resultSet,hashView);

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
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"points");
        showResultSetValues(resultSet,hashView);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    private static void showResultSetValues(ResultSet resultSet,HashMap<Integer,String> map) throws SQLException {
       String result="";
        while (resultSet.next()) {
            for(Integer columnNumber:map.keySet()){
                Object col = resultSet.getObject(map.get(columnNumber));
                result+=col+",";
            }
            result=result.substring(0,result.length()-1);
            result+="\n";
        }
        System.out.println(result);
    }


    //TODO 1 CRUD for Player table
    // TODO 1-INSERT

      public static void insertWithPreparedStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with Prepared Statement for player");

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

    // TODO 1 - SELECT

    public static void selectWithPreparedStatementPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement for player ");
        Connection connection = getConnection();

        System.out.println("Select all");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from player");
        ResultSet resultSet = preparedStatement.executeQuery();
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"number");
        hashView.put(4,"id_team");
        hashView.put(5,"position");
        showResultSetValues(resultSet,hashView);

        System.out.println("\nSelect by name");
        preparedStatement = connection.prepareStatement("select * from team where name=?");
        preparedStatement.setString(1, "Popescu Mihai");
        resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet,hashView);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    // TODO 1 - UPDATE

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
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"number");
        hashView.put(4,"id_team");
        hashView.put(5,"position");
        showResultSetValues(resultSet,hashView);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    // TODO 1 - DELETE


    public static void deleteWithStatementForPlayer() throws SQLException, ClassNotFoundException {
        System.out.println("Delete with Statement for player");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        int result = statement.executeUpdate("delete from player where id=1");
        System.out.println("Result: " + result + "\n");
        ResultSet resultSet = statement.executeQuery("select * from player");
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"id");
        hashView.put(2,"name");
        hashView.put(3,"number");
        hashView.put(4,"id_team");
        hashView.put(5,"position");
        showResultSetValues(resultSet,hashView);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    // TODO 2
    public static void selectWithStatementPlayersForASpecificTeam(String denumireEchipa) throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement by team");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        System.out.println("Select players for a specific team");
        ResultSet resultSet = statement.executeQuery("select t.name,p.name,p.number,p.position from player p inner join team t on t.id=p.id_team and t.name='"+denumireEchipa+"';\n");

        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"t.name");
        hashView.put(2,"p.name");
        hashView.put(3,"p.number");
        hashView.put(4,"p.position");

        showResultSetValues(resultSet,hashView);


        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    //TODO 3

    public static void selectWithStatementNumberOfPlayersForATeam() throws SQLException, ClassNotFoundException {
        System.out.println("Number of players for a team");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select t.name, count(p.id) from player p, team t where p.id_team = t.id group by t.name;");

        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"name");
        hashView.put(2,"count(p.id)");

        showResultSetValues(resultSet,hashView);
        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }
}

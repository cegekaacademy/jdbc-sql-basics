package com.cegeka.academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HomeworkDatabase
{
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka_curs",
                        "root", "parola123456");
    }

    ////// ex1
    public static void selectPlayersEndingVowelsPS() throws SQLException, ClassNotFoundException {
        System.out.println(">>>>>>>>>> Nume jucatori care se termina in vocala <<<<<<<<<<");
        Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement("select name from player where right(name,1) in (?,?,?,?,?)");
        ps.setString(1,"a");
        ps.setString(2,"e");
        ps.setString(3,"i");
        ps.setString(4,"o");
        ps.setString(5,"u");
        ResultSet resultSet=ps.executeQuery();
        showResultSetValuesName(resultSet);
        ps.close();
        conn.close();
        System.out.println(">>>>>>>>>> Nume jucatori care se termina in vocala <<<<<<<<<<");
    }

    ////// ex2
    public static void selectEmployeeSalaries() throws SQLException, ClassNotFoundException {
        System.out.println(">>>>>>>>>> Salarii angajati <<<<<<<<<<");
        Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement("select name from employee where salary > 2000 and months < 10 order by employee_id;");
        ResultSet resultSet=ps.executeQuery();
        showResultSetValuesSalary(resultSet);
        ps.close();
        conn.close();
        System.out.println(">>>>>>>>>> Salarii angajati <<<<<<<<<<");
    }

    ////// ex3
    public static void selectPopulation() throws SQLException, ClassNotFoundException {
        System.out.println(">>>>>>>>>> Populatia Japoniei <<<<<<<<<<");
        Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement("select sum(population) from city where countrycode=?");
        ps.setString(1,"JPN");
        ResultSet resultSet=ps.executeQuery();
        showResultSetValuesPopulation(resultSet);
        ps.close();
        conn.close();
        System.out.println(">>>>>>>>>> Populatia Japoniei <<<<<<<<<<");
    }


    private static void showResultSetValuesName(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            System.out.println(name);
        }
    }

    private static void showResultSetValuesSalary(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            Integer salary=resultSet.getInt("salary");
            System.out.println(name + " " +salary);
        }
    }

    private static void showResultSetValuesPopulation(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String countryCode = resultSet.getString("countrycode");
            Integer pop=resultSet.getInt("population");
            System.out.println(countryCode + " " + pop);
        }
    }


}
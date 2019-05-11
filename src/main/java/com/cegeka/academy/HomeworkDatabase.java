package com.cegeka.academy;

import com.mysql.cj.protocol.ResultListener;

import java.sql.*;

public class HomeworkDatabase {

    public static void createTableStation() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement statement = connection.prepareStatement(
                "create table station(" +
                        "id numeric PRIMARY KEY," +
                        "city varchar(21)," +
                        "state varchar(2)," +
                        "LAT_N numeric," +
                        "LONG_W numeric);");
        int result = statement.executeUpdate();
        System.out.println("Rezultatul este" + result);
        statement.close();
        connection.close();
    }

    public static void exercitiul1() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                                "root",Joda.pass);
        PreparedStatement preparedStatement = connection.prepareStatement("select distinct city from station where RIGHT(city, 1) in ('a','e','o','u','i');");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Orase care se termina cu o vocala: \n");
        while(resultSet.next())
        {
            String name = resultSet.getString("city");
            System.out.println(name+"\n");
        }
        preparedStatement.close();
        connection.close();
    }

    public static void createTableEmployee() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "create table employee(" +
                        "employee_id integer Primary Key," +
                        "name varchar(50)," +
                        "months integer," +
                        "salary integer" +
                        ");"
        );
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void exercitiul2() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement statement = connection.prepareStatement("select name from employee where salary > 2000 and months < 10 ORDER BY employee_id ASC;");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next())
        {
            String name =resultSet.getString("name");
            System.out.println(name);
        }
        statement.close();
        connection.close();
    }

    public static void createTableCity() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "create table city(" +
                        "id numeric Primary Key," +
                        "name varchar(17)," +
                        "countrycode varchar(3)," +
                        "district varchar(20)," +
                        "population numeric" +
                        ");"
        );
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void exercitiul3() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement statement = connection.prepareStatement("select sum(population) as \"PopulatieJaponia\" from city where countrycode like 'JPN';");
        ResultSet rezultat = statement.executeQuery();
        rezultat.next();
        int populatie = rezultat.getInt("PopulatieJaponia");
        System.out.println("Populatia Japoniei este: "+populatie);
        statement.close();
        connection.close();
    }

    public static void createTableStudents() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "create table students(" +
                        "id integer Primary Key," +
                        "name varchar(17)," +
                        "marks integer" +
                        ");"
        );
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void createTableGrades() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement preparedStatement = connection.prepareStatement(
                "create table grades(" +
                        "grade integer," +
                        "min_mark integer," +
                        "max_mark integer"+
                        ");"
        );
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public static void excercitiul4() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka_v2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",Joda.pass);
        PreparedStatement preparedStatement = connection.prepareStatement("select\n" +
                "\t(case\n" +
                "\twhen (select grade from grades where marks <= max_mark and marks >= min_mark) > 7 then name\n" +
                "    else null\n" +
                "    END) as \"name\",\n" +
                "    (select grade from grades where marks <= max_mark and marks >= min_mark) as \"grades\",\n" +
                "    marks\n" +
                "from students\n" +
                " ORDER BY grades desc, (case\n" +
                " when marks > 70 then name\n" +
                " else marks\n" +
                " END\n" +
                " ) asc;");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Studenti:\n");
        while(resultSet.next())
        {
            String name = resultSet.getString("name");
            int grades = resultSet.getInt("grades");
            int marks = resultSet.getInt("marks");
            System.out.println(name+" "+grades+" "+marks+"\n");
        }
        preparedStatement.close();
        connection.close();
    }

}

package com.cegeka.academy;

import com.cegeka.academy.Exception.NegativeException;
import com.cegeka.academy.Exception.NullException;

import java.sql.*;
import java.util.ArrayList;

public class HomeworkDatabase {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka", "root", "root");
    }

    //inserturi
    public static void insertWithPreparedStatementForStationTable(String city,String state,int lat,int longit) throws SQLException, ClassNotFoundException {
        if(city==null || city.isEmpty() || state==null || state.isEmpty())
            throw new NullException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Station(city,state,lat_n,long_w) values(?,?,?,?)");
        preparedStatement.setString(1, city);
        preparedStatement.setString(2, state);
        preparedStatement.setInt(3,lat);
        preparedStatement.setInt(4,longit);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }


    public static void insertWithPreparedStatementForEmployeeTable(String name,int months,int salary) throws SQLException, ClassNotFoundException {
        if(name==null || name.isEmpty())
            throw new NullException();
        if(months<0 || salary<0)
            throw new NegativeException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Employee(name,months,salary) values(?,?,?)");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, months);
        preparedStatement.setInt(3,salary);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }


    public static void insertWithPreparedStatementForCityTable(String name,String countryCode,String district,int population) throws SQLException, ClassNotFoundException {
        if(name==null || name.isEmpty() || countryCode==null || countryCode.isEmpty() || district==null || district.isEmpty())
            throw new NullException();
        if(population<0)
            throw new NegativeException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into City(name,countrycode,district,population) values(?,?,?,?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, countryCode);
        preparedStatement.setString(3,district);
        preparedStatement.setInt(4,population);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }


    public static void insertWithPreparedStatementForStudentTable(String name,int mark) throws SQLException, ClassNotFoundException {
        if(name==null || name.isEmpty())
            throw new NullException();
        if(mark<0)
            throw new NegativeException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Student(name,marks) values(?,?)");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, mark);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }



    public static void insertWithPreparedStatementForGradeTable(int min,int max) throws SQLException, ClassNotFoundException {

        if(min<0 || max<0)
            throw new NegativeException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Grades(min_mark,max_mark) values(?,?)");
        preparedStatement.setInt(1, min);
        preparedStatement.setInt(2, max);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }


    public static void insertWithPreparedStatementForHackerTable(String name) throws SQLException, ClassNotFoundException {
        if(name==null || name.isEmpty())
            throw new NullException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Hackers(name) values(?)");
        preparedStatement.setString(1, name);


        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void insertWithPreparedStatementForSubmissionTable(Date data,int hackerId,int score) throws SQLException, ClassNotFoundException {
        if(data==null)
            throw new NullException();
        if(score<0)
            throw new NegativeException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Submissions(submission_date,hacker_id,score) values(?,?,?)");
        preparedStatement.setDate(1, data);
        preparedStatement.setInt(2, hackerId);
        preparedStatement.setInt(3,score);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }


    //select

    public static void selectCityNamesEndingWithVowels() throws SQLException, ClassNotFoundException {
        System.out.println("Select city names ending with vowels");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select distinct city from station where upper(substr(city,-1)) in ('A','E','I','O','U');");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("city");
        showResultSetValues(resultSet, columns);

    }


    public static void selectListOfEmployeesHavingSalaryGreaterThanAValueAndWhoHaveBeenEmployeesForLessThanGivenMonth(int salary, int month) throws SQLException, ClassNotFoundException {
        System.out.println("Select list of employees name having salary greater than a value and who have been employees for less than given month");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name " +
                "FROM employee " +
                "WHERE months < " + month +
                " AND salary > " + salary +";");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("name");
        showResultSetValues(resultSet, columns);

    }

    public static void selectThePopulationForAGivenCountry(String countryCode) throws SQLException, ClassNotFoundException {
        System.out.println("Select the sum of the populatio  for a given country");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(population) FROM city  WHERE countrycode ='" + countryCode +"';");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("SUM(population)");
        showResultSetValues(resultSet, columns);
    }

    public static void selectStudentsWhoHaveGradesGreaterThen8() throws SQLException, ClassNotFoundException {
        System.out.println("Select students who have grades greater than 8");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select (case when grade <8 THEN NULL ELSE name END) name, grade, marks\n" +
                "from " +
                "(select name, grade, marks " +
                "from student, grades " +
                "where marks between min_Mark and Max_Mark) result " +
                "order by result.grade desc, result.name;");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("name");
        columns.add("grade");
        columns.add("marks");
        showResultSetValues(resultSet, columns);
    }

    public static void selectTheTotalNumberOfUniqueHackersWhoMadeAtLeastOneSubmissionEachDayAndFindTheHackerIdAndNameOfHackerWhoMadeMaximumNumberOfSubmissionsEachDay() throws SQLException, ClassNotFoundException {
        System.out.println("Select the total number of unique hacker who made at least one submission each day and the hacker who made maximum number of submission each day");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select big_1.submission_date, big_1.hkr_cnt, big_2.hacker_id, h.name\n" +
                "from\n" +
                "(select submission_date, count(distinct hacker_id) as hkr_cnt\n" +
                "from \n" +
                "(select s.*, dense_rank() over(order by submission_date) as date_rank, \n" +
                "dense_rank() over(partition by hacker_id order by submission_date) as hacker_rank \n" +
                "from submissions s ) a \n" +
                "where date_rank = hacker_rank \n" +
                "group by submission_date) big_1 \n" +
                "join \n" +
                "(select submission_date,hacker_id, \n" +
                " rank() over(partition by submission_date order by sub_cnt desc, hacker_id) as max_rank \n" +
                "from (select submission_date, hacker_id, count(*) as sub_cnt \n" +
                "      from submissions \n" +
                "      group by submission_date, hacker_id) b ) big_2\n" +
                "on big_1.submission_date = big_2.submission_date and big_2.max_rank = 1 \n" +
                "join hackers h on h.hacker_id = big_2.hacker_id \n" +
                "order by 1;");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("submission_date");
        columns.add("hkr_cnt");
        columns.add("hacker_id");
        columns.add("name");
        showResultSetValues(resultSet,columns);
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
}

package com.cegeka.academy;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomeworkDatabase {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cegeka", "root", "Ozzyeste1");
    }

    public static void createTables() throws SQLException, ClassNotFoundException {
        Connection connection =getConnection();
        String createTableStation = "CREATE TABLE station (" +
                "  id integer NOT NULL AUTO_INCREMENT," +
                "  city varchar(21), " +
                "  state varchar(2)," +
                "  lat_n numeric ," +
                "  long_w numeric ," +
                "  PRIMARY KEY (id)" +
                ");";
        String deleteTableStation = "DROP TABLE IF EXISTS station;";

        String createTableEmployee = "CREATE TABLE employee (" +
                "  employee_id integer NOT NULL AUTO_INCREMENT," +
                "  name varchar(21), " +
                "  months numeric ," +
                "  salary numeric ," +
                "  PRIMARY KEY (employee_id)" +
                ");";
        String deleteTableEmployee = "DROP TABLE IF EXISTS employee;";

        String createTableCity = "CREATE TABLE city (" +
                "  id integer NOT NULL AUTO_INCREMENT," +
                "  name varchar(17), " +
                "  countryCode varchar(3) ," +
                "  district varchar(20) ," +
                "  population numeric ,"+
                "  PRIMARY KEY (id)" +
                ");";
        String deleteTableCity = "DROP TABLE IF EXISTS city;";

        String createTableStudents = "CREATE TABLE student (" +
                "  id integer NOT NULL AUTO_INCREMENT," +
                "  name varchar(30), " +
                "  marks integer ,"+
                "  PRIMARY KEY (id)" +
                ");";
        String deleteTableStudent = "DROP TABLE IF EXISTS student;";

        String createTableGrades = "CREATE TABLE grades (" +
                "  grade integer NOT NULL AUTO_INCREMENT," +
                "  min_mark integer, " +
                "  max_mark integer ,"+
                "  PRIMARY KEY (grade)" +
                ");";
        String deleteTableGrades = "DROP TABLE IF EXISTS grades;";

        String createTableHackers = "CREATE TABLE hackers (" +
                "  hacker_id integer NOT NULL AUTO_INCREMENT," +
                "  name varchar(30), " +
                "  PRIMARY KEY (hacker_id)" +
                ");";
        String deleteTableHackers = "DROP TABLE IF EXISTS hackers;";

        String createTableSubmissions = "CREATE TABLE submissions (" +
                "  submission_id integer NOT NULL AUTO_INCREMENT," +
                "  submission_date date, " +
                "  hacker_id integer ," +
                "  score integer ," +
                "  PRIMARY KEY (submission_id) ," +
                "  KEY hacker_id (hacker_id), " +
                "  CONSTRAINT hacker_id_fk FOREIGN KEY (hacker_id) REFERENCES hackers (hacker_id) "+
                ");";
        String deleteTableSubmissions = "DROP TABLE IF EXISTS submissions;";

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(deleteTableStation);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(createTableStation);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(deleteTableEmployee);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(createTableEmployee);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(deleteTableCity);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(createTableCity);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(deleteTableStudent);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(createTableStudents);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(deleteTableGrades);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(createTableGrades);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(deleteTableSubmissions);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(deleteTableHackers);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(createTableHackers);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(deleteTableSubmissions);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(createTableSubmissions);
        preparedStatement.executeUpdate();
        System.out.println("Tabelele au fost create");
        System.out.println("-----------------------------------------");


    }

    public static void insertWithPrepareStatementStation() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with prepare statement Station");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into station (city, state, lat_n, long_w) values(?,?,?,?)");
        preparedStatement.setString(1,"Bucharest");
        preparedStatement.setString(2,"RO");
        preparedStatement.setLong(3,100);
        preparedStatement.setLong(4,22);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Iasi");
        preparedStatement.setString(2,"RO");
        preparedStatement.setLong(3,120);
        preparedStatement.setLong(4,33);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Brasov");
        preparedStatement.setString(2,"RO");
        preparedStatement.setLong(3,110);
        preparedStatement.setLong(4,23);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Piatra Neamt");
        preparedStatement.setString(2,"RO");
        preparedStatement.setLong(3,220);
        preparedStatement.setLong(4,11);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);
        System.out.println("-----------------------------------------");

        preparedStatement.close();
        connection.close();

    }

    public static void insertWithPrepareStatementEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with prepare statement Employee");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into employee (name, months, salary) values(?,?,?)");
        preparedStatement.setString(1,"Rose");
        preparedStatement.setInt(2,15);
        preparedStatement.setInt(3,1968);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Angela");
        preparedStatement.setInt(2,1);
        preparedStatement.setInt(3,3443);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Frank");
        preparedStatement.setInt(2,17);
        preparedStatement.setInt(3,1680);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Michael");
        preparedStatement.setInt(2,6);
        preparedStatement.setInt(3,2017);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);
        System.out.println("-----------------------------------------");

        preparedStatement.close();
        connection.close();

    }

    public static void insertWithPrepareStatementCity() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with prepare statement City");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into city (name, countrycode, district, population) values(?,?,?,?)");
        preparedStatement.setString(1,"Tokyo");
        preparedStatement.setString(2,"JPN");
        preparedStatement.setString(3,"RO");
        preparedStatement.setLong(4,2200);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Osaka");
        preparedStatement.setString(2,"JPN");
        preparedStatement.setString(3,"RO");
        preparedStatement.setLong(4,1000);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Brasov");
        preparedStatement.setString(2,"RO");
        preparedStatement.setString(3,"RO");
        preparedStatement.setLong(4,800);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Hiroshima ");
        preparedStatement.setString(2,"JPN");
        preparedStatement.setString(3,"RO");
        preparedStatement.setLong(4,1200);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);
        System.out.println("-----------------------------------------");

        preparedStatement.close();
        connection.close();

    }

    public static void insertWithPrepareStatementStudents() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with prepare statement Students");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into student (name, marks) values(?,?)");
        preparedStatement.setString(1,"Rose");
        preparedStatement.setInt(2,10);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Angela");
        preparedStatement.setInt(2,8);

        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Frank");
        preparedStatement.setInt(2,5);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Michael");
        preparedStatement.setInt(2,6);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);
        System.out.println("-----------------------------------------");

        preparedStatement.close();
        connection.close();

    }

    public static void insertWithPrepareStatementGrades() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with prepare statement Grades");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into grades (min_mark, max_mark) values(?,?)");
        preparedStatement.setInt(1,0);
        preparedStatement.setInt(2,9);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);


        preparedStatement.setInt(1,10);
        preparedStatement.setInt(2,19);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setInt(1,20);
        preparedStatement.setInt(2,29);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);


        preparedStatement.setInt(1,30);
        preparedStatement.setInt(2,39);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);
        System.out.println("-----------------------------------------");

        preparedStatement.close();
        connection.close();

    }


    public static void insertWithPrepareStatementHeackers() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with prepare statement Hackers");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into hackers (name) values(?)");
        preparedStatement.setString(1,"Hackers 1");

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);


        preparedStatement.setString(1,"Hackers 2");

        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setString(1,"Hackers 3");

        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);


        preparedStatement.setString(1,"Hackers 4");

        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);
        System.out.println("-----------------------------------------");

        preparedStatement.close();
        connection.close();

    }

    public static void insertWithPrepareStatementSubmissions() throws SQLException, ClassNotFoundException {
        System.out.println("Insert with prepare statement Submissions");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into submissions (submission_date, hacker_id, score) values(?,?,?)");
        java.util.Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse("22/09/2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());


        preparedStatement.setDate(1,sqlDate);
        preparedStatement.setInt(2,1);
        preparedStatement.setInt(3,20);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);


        preparedStatement.setDate(1,sqlDate);
        preparedStatement.setInt(2,2);
        preparedStatement.setInt(3,11);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setDate(1,sqlDate);
        preparedStatement.setInt(2,2);
        preparedStatement.setInt(3,30);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.setDate(1,sqlDate);
        preparedStatement.setInt(2,3);
        preparedStatement.setInt(3,39);
        result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);
        System.out.println("-----------------------------------------");

        preparedStatement.close();
        connection.close();

    }

    public static void selectCityNamesEndingWithVowels() throws SQLException, ClassNotFoundException {
        System.out.println("Select city names ending with vowels");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select distinct city from station where upper(substr(city,-1)) in ('A','E','I','O','U');");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("city");
        showResultSetValues(resultSet, columns);

    }

    public static void selectListOfEmployeesNameHavingSalaryGreaterThanAValueAndWhoHaveBeenEmployeesForLessThanGivenMonth(int salary, int month) throws SQLException, ClassNotFoundException {
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

    public static void selectTheSumOfThePopulationForAGivenCountry(String countryCode) throws SQLException, ClassNotFoundException {
        System.out.println("Select the sum of the populatio  for a given country");
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(population) FROM city  WHERE countrycode ='" + countryCode +"';");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("SUM(population)");
        showResultSetValues(resultSet, columns);
    }

    public static void selectExerciseFour() throws SQLException, ClassNotFoundException {
        System.out.println("Select exercise 4");
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
        System.out.println("Select exercise 6");
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

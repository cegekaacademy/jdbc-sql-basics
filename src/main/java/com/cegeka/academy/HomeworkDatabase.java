package com.cegeka.academy;

import com.cegeka.academy.exception.NegativeException;
import com.cegeka.academy.exception.NullException;

import java.sql.*;
import java.util.HashMap;

public class HomeworkDatabase {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka",
                        "root", "georgiana123");
    }

    public static void createTables() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        //Station table
        String sqlDrop="DROP TABLE IF EXISTS `Station`;";
        String sql = "CREATE TABLE Station " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " city VARCHAR(255), " +
                " state VARCHAR(255), " +
                " lat_n INTEGER, " +
                " long_w INTEGER, " +
                " PRIMARY KEY ( id ))";

        statement.executeUpdate(sqlDrop);
        statement.executeUpdate(sql);

        //Employee table
        String sqlDrop1="DROP TABLE IF EXISTS `Employee`;";
        String sql1 = "CREATE TABLE Employee " +
                "(employee_id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " months INTEGER, " +
                " salary INTEGER, " +
                " PRIMARY KEY ( employee_id ))";

        statement.executeUpdate(sqlDrop1);
        statement.executeUpdate(sql1);

        //City table
        String sqlDrop2="DROP TABLE IF EXISTS `City`;";
        String sql2 = "CREATE TABLE City " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(17), " +
                " countrycode VARCHAR(3), " +
                " district VARCHAR(20), " +
                " population INTEGER, " +
                " PRIMARY KEY ( id ))";

        statement.executeUpdate(sqlDrop2);
        statement.executeUpdate(sql2);


        //Student table
        String sqlDrop3="DROP TABLE IF EXISTS `Student`;";
        String sql3 = "CREATE TABLE Student " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(17), " +
                " marks INTEGER, " +
                " PRIMARY KEY ( id ))";

        statement.executeUpdate(sqlDrop3);
        statement.executeUpdate(sql3);

        //Grade table
        String sqlDrop4="DROP TABLE IF EXISTS `Grade`;";
        String sql4 = "CREATE TABLE Grade " +
                "(grade INTEGER not NULL AUTO_INCREMENT, " +
                " min_mark INTEGER, " +
                " max_mark INTEGER, " +
                " PRIMARY KEY ( grade ))";

        statement.executeUpdate(sqlDrop4);
        statement.executeUpdate(sql4);

        //Hacker table
        String sqlDrop5="DROP TABLE IF EXISTS `Hacker`;";
        String sql5 = "CREATE TABLE Hacker " +
                "(hacker_id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(17), " +
                " PRIMARY KEY ( hacker_id ))";

        statement.executeUpdate(sqlDrop5);
        statement.executeUpdate(sql5);

        //Submission table
        String sqlDrop6="DROP TABLE IF EXISTS `Submission`;";
        String sql6 = "CREATE TABLE Submission " +
                "(submission_id INTEGER not NULL AUTO_INCREMENT, " +
                " submission_date DATE, " +
                " hacker_id INTEGER, " +
                " score INTEGER, " +
                " PRIMARY KEY ( submission_id ),"+
                "foreign key(hacker_id) references Hacker(hacker_id))";

        statement.executeUpdate(sqlDrop6);
        statement.executeUpdate(sql6);

    }

    // TODO PRIMUL EX

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



    public static void selectWithPreparedStatementForEX1() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();
        System.out.println("Select all DISTINCT cities which end with o vovel");
        PreparedStatement preparedStatement = connection.prepareStatement("select distinct city from Station where upper(substr(city,-1)) in ('A','E','I','O','U');");
        ResultSet resultSet = preparedStatement.executeQuery();
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(2,"city");
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

    //TODO AL DOILEA EX
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
    public static void selectWithPreparedStatementForEX2(int months,int salary) throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();
        System.out.println("Select all emplyees with salary >2000 and months<10");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name\n" +
                "FROM Employee\n" +
                "WHERE months <" +months+
                "  AND salary >"+salary+";");
        ResultSet resultSet = preparedStatement.executeQuery();
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(2,"name");
        showResultSetValues(resultSet,hashView);


        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    // TODO AL TREILEA EX
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
    public static void selectWithPreparedStatementForEX3(String countryCode) throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();
        System.out.println("Select sum of population of cities with country code jpn");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(POPULATION) suma FROM CITY  WHERE COUNTRYCODE ='"+countryCode+"';");
        ResultSet resultSet = preparedStatement.executeQuery();
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"suma");
        showResultSetValues(resultSet,hashView);


        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    //TODO EX 4
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
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Grade(min_mark,max_mark) values(?,?)");
        preparedStatement.setInt(1, min);
        preparedStatement.setInt(2, max);

        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    public static void selectWithPreparedStatementForEX4() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select (case when grade <8 THEN NULL ELSE name END) col, name, grade, marks\n" +
                "from\n" +
                "(select name, grade, marks\n" +
                "from student, grade\n" +
                "where marks between min_mark and max_mark) a\n" +
                "order by grade desc, name;");
        ResultSet resultSet = preparedStatement.executeQuery();
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"col");
        hashView.put(2,"name");
        hashView.put(3,"grade");
        hashView.put(4,"marks");
        showResultSetValues(resultSet,hashView);


        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    //TODO EX 6
    public static void insertWithPreparedStatementForHackerTable(String name) throws SQLException, ClassNotFoundException {
        if(name==null || name.isEmpty())
            throw new NullException();
        System.out.println("Insert with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Hacker(name) values(?)");
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
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Submission(submission_date,hacker_id,score) values(?,?,?)");
        preparedStatement.setDate(1, data);
        preparedStatement.setInt(2, hackerId);
        preparedStatement.setInt(3,score);
        int result = preparedStatement.executeUpdate();
        System.out.println("Result: " + result);

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void selectWithPreparedStatementForEX6() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Prepared Statement");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select big_1.submission_date, big_1.hkr_cnt, big_2.hacker_id, h.name\n" +
                "    from\n" +
                "            (select submission_date, count(distinct hacker_id) as hkr_cnt\n" +
                "    from\n" +
                "            (select s.*, dense_rank() over(order by submission_date) as date_rank,\n" +
                "    dense_rank() over(partition by hacker_id order by submission_date) as hacker_rank\n" +
                "    from submission s ) a\n" +
                "    where date_rank = hacker_rank\n" +
                "    group by submission_date) big_1\n" +
                "    join\n" +
                "            (select submission_date,hacker_id,\n" +
                "             rank() over(partition by submission_date order by sub_cnt desc, hacker_id) as max_rank\n" +
                "    from (select submission_date, hacker_id, count(*) as sub_cnt\n" +
                "    from submission\n" +
                "    group by submission_date, hacker_id) b ) big_2\n" +
                "    on big_1.submission_date = big_2.submission_date and big_2.max_rank = 1\n" +
                "    join hacker h on h.hacker_id = big_2.hacker_id\n" +
                "    order by 1;");
        ResultSet resultSet = preparedStatement.executeQuery();
        HashMap<Integer,String>hashView=new HashMap<Integer, String>();
        hashView.put(1,"submission_date");
        hashView.put(2,"hkr_cnt");
        hashView.put(3,"hacker_id");
        hashView.put(4,"name");
        showResultSetValues(resultSet,hashView);


        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }



}

package com.cegeka.academy;

import java.sql.*;

public class HomeworkDatabase {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka_db",
                        "root", "password");
    }
    public static void createTableStation() throws SQLException, ClassNotFoundException {
        System.out.println("Creating table Station");

        Connection connection = getConnection();
        PreparedStatement preparedStatement0 = connection.prepareStatement("drop table if exists station;");
        int result0 = preparedStatement0.executeUpdate();
        PreparedStatement preparedStatement = connection.prepareStatement("create table station( "+
                                                        "id bigint(20) unsigned not null auto_increment primary key,"+                 "\t\t\t\t\tcity varchar(21),\n" +
                                                        " state varchar(21)," +
                                                        "lat_n bigint(20)," +
                                                        "long_w bigint(20));");

        int result = preparedStatement.executeUpdate();
        System.out.println("Table created!");

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    public static void selectCityEndingWithVowel() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        System.out.println("Select city ending with vowel");
        PreparedStatement preparedStatement = connection.prepareStatement("select distinct city from station where city rlike '[aeiou]$';");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);
        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void createTableEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("Creating table Employee");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists employee;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table employee( employee_id bigint(20) unsigned not null auto_increment primary key,\n" +
                                                        "\t\t\t\t\tname varchar(21),\n" +
                                                        "                    months bigint(20),\n" +
                                                        "                    salary bigint(20));");

        preparedStatement.executeUpdate();
        System.out.println("Table created!");

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void employeeSalaries() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        System.out.println("Select employee salaries");
        PreparedStatement preparedStatement = connection.prepareStatement("select name from employee where salary > ? and months < ? order by ?;");
        preparedStatement.setInt(1,2000);
        preparedStatement.setInt(2,10);
        preparedStatement.setString(3,"employee_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);
        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }


    public static void createTableCity() throws SQLException, ClassNotFoundException {
        System.out.println("Creating table City");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists city;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table city( id bigint(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\tname varchar(21),\n" +
                "                    countrycode varchar(3),\n" +
                "                    district varchar(20),\n" +
                "                    population bigint(20));");

        preparedStatement.executeUpdate();
        System.out.println("Table created!");

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void japanPopulation() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        System.out.println("Japan Population");
        PreparedStatement preparedStatement = connection.prepareStatement("select sum(population) from city where countrycode = ?;");

        preparedStatement.setString(1,"JPN");
        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);
        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    public static void createTableStudents() throws SQLException, ClassNotFoundException {
        System.out.println("Creating table Students");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists students;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table students( id bigint(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\tname varchar(21),\n" +
                "                    marks varchar(21));");

        preparedStatement.executeUpdate();
        System.out.println("Table created!");

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    public static void createTableGrades() throws SQLException, ClassNotFoundException {
        System.out.println("Creating table Grades");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("drop table if exists grades;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table grades( grade bigint(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\tmin_mark int(21),\n" +
                "                    max_mark int(21));");

        preparedStatement.executeUpdate();
        System.out.println("Table created!");

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }
    public static void reportProblem() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        System.out.println("Report Problem");
        PreparedStatement preparedStatement = connection.prepareStatement("select (case g.grade >=? when true then s.name else null end), g.grade, s.marks\n" +
                "\t\tfrom students s inner join grades g\n" +
                "        on s.marks between min_mark and max_mark\n" +
                "        order by g.grade desc, s.name, ifnull(s.name, s.marks) desc;");
        preparedStatement.setInt(1,8);

        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);
        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");}

    public static void createTablesTopCompetitors() throws SQLException, ClassNotFoundException {
        System.out.println("Creating table Hackers");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("drop table if exists hackers;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table hackers( hacker_id bigint(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\tname varchar(21));");

        preparedStatement.executeUpdate();
        System.out.println("Table hackers created!");

        preparedStatement = connection.prepareStatement("drop table if exists difficulty;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table difficulty( difficulty_level int(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\tscore int(21));");

        preparedStatement.executeUpdate();
        System.out.println("Table difficulty created!");

        preparedStatement = connection.prepareStatement("drop table if exists challenges;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table challenges( challenge_id bigint(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\thacker_id bigint(20) unsigned not null ,\n" +
                "                    difficulty_level int(20),\n" +
                "                    foreign key (hacker_id) references hackers(hacker_id));");

        preparedStatement.executeUpdate();
        System.out.println("Table difficulty challenges!");

        preparedStatement = connection.prepareStatement("drop table if exists submissions;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table submissions( submission_id bigint(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\thacker_id bigint(20) unsigned not null,\n" +
                "                    challenge_id bigint(20) unsigned not null,\n" +
                "                    score bigint(20),\n" +
                "                    foreign key (hacker_id) references hackers(hacker_id),\n" +
                "                    foreign key (challenge_id) references challenges(challenge_id));");

        preparedStatement.executeUpdate();
        System.out.println("Table difficulty submissions!");

        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void topCompetitors() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        System.out.println("Top Competitors Problem");
        PreparedStatement preparedStatement = connection.prepareStatement("select h.hacker_id, h.name from submissions s\n" +
                "inner join challenges c on s.challenge_id = c.challenge_id\n" +
                "inner join difficulty d on c.difficulty_level = d.difficulty_level\n" +
                "inner join hackers h on h.hacker_id = s.hacker_id\n" +
                "where s.score = d.score and c.difficulty_level = d.difficulty_level\n" +
                "group by h.hacker_id, h.name having count(s.hacker_id)>1\n" +
                "order by count(s.hacker_id) desc, h.hacker_id asc;");

        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);
        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");}

    public static void createTablesDaysOfLearning() throws SQLException, ClassNotFoundException {
        System.out.println("Creating table Hackers");

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("drop table if exists hackers;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table hackers( hacker_id bigint(20) unsigned not null auto_increment primary key,\n" +
                "\t\t\t\t\tname varchar(21));");

        preparedStatement.executeUpdate();
        System.out.println("Table hackers created!");
        System.out.println("Creating table Submissions");

        preparedStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("drop table if exists submissions;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1;");
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("create table submissions( submission_date date ,\n" +
                "\t\t\t\t\tsubmission_id bigint(20) unsigned not null,\n" +
                "\t\t\t\t\thacker_id bigint(20) unsigned not null,\n" +
                "                    score bigint(20),\n" +
                "                    foreign key (hacker_id) references hackers(hacker_id));");

        preparedStatement.executeUpdate();
        System.out.println("Table submissions created!");


        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");
    }

    public static void daysOfLearningProblem() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        System.out.println("Days of learning Problem");
        PreparedStatement preparedStatement = connection.prepareStatement("select submission_date ,( select count(distinct hacker_id)  from submissions s2  \n" +
                "where s2.submission_date = s1.submission_date and \n" +
                "(select count(distinct s3.submission_date) \n" +
                "from submissions s3 where s3.hacker_id = s2.hacker_id and  \n" +
                "s3.submission_date < s1.submission_date) = dateDIFF(s1.submission_date , '2016-03-01')) as counts ,\n" +
                "(select hacker_id  from submissions s2 \n" +
                "where s2.submission_date = s1.submission_date group by hacker_id \n" +
                "order by count(submission_id) desc , hacker_id limit 1) as hacker,\n" +
                "(select name from hackers where hacker_id = hacker) as Names from \n" +
                "(select distinct submission_date from submissions) s1\n" +
                "group by submission_date order by submission_date asc;");

        ResultSet resultSet = preparedStatement.executeQuery();
        showResultSetValues(resultSet);
        preparedStatement.close();
        connection.close();
        System.out.println("-----------------------------------------");}


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

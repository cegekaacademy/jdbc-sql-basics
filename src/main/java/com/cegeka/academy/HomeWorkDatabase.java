package com.cegeka.academy;

import java.sql.*;

public class HomeWorkDatabase {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka_2",
                        "root", "cegeka");
    }
    public static void ex1() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from station where city like '%a' or city like'%e' or city like '%i' or city like '%o' or city like '%u'");
        showResultEx1(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }
    public static void ex2() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee where salary > 2000 and months<10 order by employee_id; ");
        showResultEx2(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }
    public static void ex3() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select sum(population) as NumberPopulation from city where countrycode=\"JPN\"; ");
        showResultEx3(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }
    public static void ex5() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select sub.hacker_id ,h.name  from hackers h,difficulty d,challenges c,submissions sub where  h.hacker_id=sub.hacker_id and sub.challenge_id=c.challenge_id and c.difficulty_level=d.difficulty_level and sub.score=d.score group by h.hacker_id having count(sub.hacker_id)>1  order by count(sub.hacker_id) desc,h.hacker_id asc ; ");
        showResultEx5(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }
    public static void ex4() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("\"select\\n\" +\n" +
                "                \"\\t(case\\n\" +\n" +
                "                \"\\twhen (select grade from grades where marks <= max_mark and marks >= min_mark) > 7 then name\\n\" +\n" +
                "                \"    else null\\n\" +\n" +
                "                \"    END) as \\\"name\\\",\\n\" +\n" +
                "                \"    (select grade from grades where marks <= max_mark and marks >= min_mark) as \\\"grades\\\",\\n\" +\n" +
                "                \"    marks\\n\" +\n" +
                "                \"from students\\n\" +\n" +
                "                \" ORDER BY grades desc, (case\\n\" +\n" +
                "                \" when marks > 70 then name\\n\" +\n" +
                "                \" else marks\\n\" +\n" +
                "                \" END\\n\" +\n" +
                "                \" ) asc;\"");
        showResultEx4(resultSet);
        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }
    public static void ex6() throws SQLException, ClassNotFoundException {
        System.out.println("Select with Statement");
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT \n" +
                "    submission_date ,\n" +
                "( SELECT \n" +
                " COUNT(distinct hacker_id) as nr\n" +
                " FROM Submissions hackerCount  \n" +
                " WHERE hackerCount.submission_date = dates.submission_date \n" +
                " AND (SELECT \n" +
                "        COUNT(distinct submissionCount.submission_date) \n" +
                "      FROM Submissions submissionCount \n" +
                "      WHERE submissionCount.hacker_id = hackerCount.hacker_id \n" +
                "      AND submissionCount.submission_date < dates.submission_date) \n" +
                "                = dateDIFF(dates.submission_date , '2016-03-01')\n" +
                "     ) ,\n" +
                "( SELECT \n" +
                "    hacker_id  \n" +
                "    FROM submissions hackerList \n" +
                "    WHERE hackerList.submission_date = dates.submission_date \n" +
                "    GROUP BY hacker_id \n" +
                "    ORDER BY count(submission_id) DESC , hacker_id limit 1) as topHack,\n" +
                "(SELECT \n" +
                "    name \n" +
                "    FROM hackers \n" +
                "    WHERE hacker_id = topHack) as h_name\n" +
                "    FROM (SELECT distinct submission_date from submissions) dates\n" +
                "    GROUP BY submission_date;");
        showResultEx6(resultSet);

        statement.close();
        connection.close();
        System.out.println("-----------------------------------------");

    }

    private static void showResultEx5(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("sub.hacker_id");
            String name = resultSet.getString("h.name");
            System.out.println("Points: " + id + ", " + name);
        }
    }
    private static void showResultEx4(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int grades = resultSet.getInt("grades");
            int marks = resultSet.getInt("marks");
            System.out.println("Results:"+name+" "+grades+" "+marks+"\n");
        }
    }
    private static void showResultEx6(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Date date = resultSet.getDate("submission_date");
            Integer nr = resultSet.getInt("nr");
            Integer id = resultSet.getInt("hacker_id");
            String name = resultSet.getString("name");
            System.out.println("Results: " + date + ", " + nr+","+id+","+name);
        }
    }


    private static void showResultEx1(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String city = resultSet.getString("city");
            Double lat_n = resultSet.getDouble("lat_n");
            Double lat_w = resultSet.getDouble("lat_w");
            System.out.println("Team: " + id + ", " + city + ", " + lat_n+","+lat_w);
        }
    }
    private static void showResultEx2(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Long id = resultSet.getLong("employee_id");
            String name = resultSet.getString("name");
            Integer months = resultSet.getInt("months");
            Integer salary = resultSet.getInt("salary");
            System.out.println("Team: " + id + ", " + name + ", " + months+","+salary);
        }
    }
    private static void showResultEx3(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
             Integer sum=resultSet.getInt("NumberPopulation");
            System.out.println("Number population for Japan:"+sum);
        }
    }

}

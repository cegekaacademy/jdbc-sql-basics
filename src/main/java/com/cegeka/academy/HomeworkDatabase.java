package com.cegeka.academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HomeworkDatabase {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/cegeka_v2",
                        "root", "admin123!@#");
    }




}

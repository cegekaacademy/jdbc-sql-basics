package com.cegeka.academy;
import com.cegeka.academy.DatabaseManager;
import exception.InvalidParametersException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidParametersException {
        //DatabaseManager.insertPlayer("Bitica",10,3,"Defender");
        // DatabaseManager.updatePlayer(13, 3, "Alexandru", -1,"Mijlocas");
        //DatabaseManager.selectPlayer(null,-2,1,null);
       // DatabaseManager.deletePlayer(null,-1,-1,"Mijlocas");
       // DatabaseManager.selectFromTeam(1);
        //HomeworkDatabase.createTableStation();
        //HomeworkDatabase.exercitiul1();
        //HomeworkDatabase.createTableEmployee();
        // HomeworkDatabase.exercitiul2();
       // HomeworkDatabase.createTableCity();
        //HomeworkDatabase.exercitiul3();
        //HomeworkDatabase.createTableStudents();
       // HomeworkDatabase.createTableGrades();
        HomeworkDatabase.excercitiul4();
    }
}

package main;

import java.sql.*;

public class userDBControl {
    public static void accessDB() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://X://My Documents//Reigate College//Year 1//Computer Science//Java Programming//collaborativeProject//src//resources//databases//userDB.accdb");
            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("An error occurred");
        }
    }
}
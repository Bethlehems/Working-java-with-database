/**
 * Created by betty on 7/19/17.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Clearance {
    public static void update(){

    }public static void show(){

    }
    public static void search(){

    }public static void addRow(){

    }
  
    public static void deleteRow(){

    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Clearance_System", "root", "made2begr8");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM Student";
            ResultSet rs = stmt.executeQuery(SQL);
            System.out.println("Name    ID    DepName");
            while (rs.next()) {

                String stud_name = rs.getString("Stud_Name");
                String stud_id = rs.getString("Stud_ID");
                String dep_id = rs.getString("Dep_ID");
                System.out.println(stud_name + "    " + stud_id + "    " + dep_id);

            }
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter the id you want to search");
            String search = scn.next();
            rs.first();
            rs.previous();
            while (rs.next()) {
                if (rs.getString("Stud_ID").compareTo(search) == 0) {
                    String stud_name = rs.getString("Stud_Name");
                    String dep_id = rs.getString("Dep_ID");
                    System.out.println("Name    ID    DepName");
                    System.out.println(rs.getString("Stud_ID") + "    " + stud_name + "    " + dep_id);

                }
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }


    }
}

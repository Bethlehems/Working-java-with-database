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
    public static ResultSet connection(){
        try {Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Clearance_System", "root", "made2begr8");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String SQL = "SELECT * FROM Student";
            ResultSet rs = stmt.executeQuery(SQL);
            return rs;
        }


        catch (SQLException err) {
            System.out.println(err.getMessage());


        }
    return null;}
    public static void update() {
        try {

            Scanner scn = new Scanner(System.in);
            System.out.println("Enter the id you want to update");
            String search = scn.next();
            //lets say we want to update the student name
            System.out.println("Enter new name:");
            String newName = scn.next();
            connection().first();
            connection().previous();
            while (connection().next()) {
                if (connection().getString("Stud_ID").compareTo(search) == 0) {
                    connection().updateString("Stud_Name", newName);
                    System.out.println("Name Updated Successfully.");

                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }

    }

    public static void show() {
        try {

            System.out.println("Name    ID    DepName");
            while (connection().next()) {

                String stud_name = connection().getString("Stud_Name");
                String stud_id = connection().getString("Stud_ID");
                String dep_id = connection().getString("Dep_ID");
                System.out.println(stud_name + "    " + stud_id + "    " + dep_id);

            }


        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }

    }


    public static void search() {
        try {
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter the id you want to search");
            String search = scn.next();
            connection().first();
           connection().previous();

            while (connection().next()) {
                if (connection().getString("Stud_ID").compareTo(search) == 0) {
                    String stud_name = connection().getString("Stud_Name");
                    String dep_id = connection().getString("Dep_ID");
                    System.out.println("Name    ID    DepName");
                    System.out.println(connection().getString("Stud_ID") + "    " + stud_name + "    " + dep_id);

                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }

    }

    public static void addRow() {

    }

    public static void deleteRow() {
        try {
            System.out.println("Enter the Student id you want to delete");
            Scanner scn = new Scanner(System.in);
            String delete = scn.next();
            connection().first();
            connection().previous();

            while (connection().next()) {
                if (connection().getString("Stud_ID").compareTo(delete) == 0) {
                    connection().deleteRow();
                    System.out.println("Row Deleted Successfully.");

                }
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());

    }}


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            System.out.println("Choose an action:");
            System.out.println("1,Display all student Information.");
            System.out.println("2,Search Specific Student.");
            System.out.println("3,Update Student Info");
            System.out.println("4,delete Student Info");
            System.out.println("5,Add Student.");
        boolean over = false;//Flag to check if valid choice is made...
        String inp=input.next();
        while (!over) {
            while (inp.length() != 1) {
                System.out.print("INVALID INPUT. CHOOSE AGAIN.\n");

            }
            switch (inp.charAt(0)) {
                case '1':
                {
                    show();
                    break;
                }
                case '2':
                { search();
                    break;}
                case '3':
                {update();
                    break;}
                case '4':{
                    deleteRow();
                    break;}
                case '5':{
                    addRow();
                    break;}
                default:
                        System.out.println("Invalid input. Enter again");

            }

        }
    }
}
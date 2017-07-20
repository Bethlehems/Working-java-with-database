/**
 * Created by betty on 7/19/17.
 */

import java.sql.*;
import java.util.Scanner;

class student {
    String name;
    String ID;
    String D_ID;

}


class actions {
    public static Statement connection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Clearance_System", "root", "made2begr8");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            return stmt;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return null;
    }

    public static void update(student std) {
        try {
            String SQL = "SELECT * FROM Student";
            ResultSet rs = connection().executeQuery(SQL);
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter the id you want to update");
            String search = scn.next();
            //lets say we want to update the student name
            System.out.println("Enter new name:");
            String newName = scn.next();
            boolean found = false;
            rs.first();
            rs.previous();
            while (rs.next()) {
                if (rs.getString("Stud_ID").compareTo(search) == 0) {
                    rs.updateString("Stud_Name", newName);
                    System.out.println("Name Updated Successfully.");
                    found = true;
                    break;

                }
            }
            if (found == false) {
                System.out.println("Student not found.");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }
        menu();


    }

    public static void show(student std) {
        try {
            String SQL = "SELECT * FROM Student";
            ResultSet rs = connection().executeQuery(SQL);
            System.out.println("Name    ID    DepName");
            rs.first();
            rs.previous();
            boolean found = false;
            while (rs.next()) {

                std.name = rs.getString("Stud_Name");
                std.ID= rs.getString("Stud_ID");
                std.D_ID= rs.getString("Dep_ID");
                System.out.println(std.name + "    " + std.ID + "    " + std.D_ID);
                found = true;
            }
            if (found == false) {
                System.out.println("Student not found.");
            }


        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }
        menu();

    }


    public static void search(student std) {
        try {
            String SQL = "SELECT * FROM Student";
            ResultSet rs = connection().executeQuery(SQL);
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter the id you want to search");
            String search = scn.next();
            rs.first();
            rs.previous();
            boolean found = false;
            while (rs.next()) {
                if (rs.getString("Stud_ID").compareTo(search) == 0) {
                    String stud_name = rs.getString("Stud_Name");
                    String dep_id = rs.getString("Dep_ID");
                    System.out.println("Name    ID    DepName");
                    System.out.println(rs.getString("Stud_ID") + "    " + stud_name + "    " + dep_id);
                    found = true;
                }
            }
            if (found == false) {
                System.out.println("Student not found.");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());


        }
        menu();

    }

    public static void addRow(student std) {
        try {
            Scanner s = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Clearance_System", "root", "made2begr8");
            System.out.println("Enter new student's Info.");
            System.out.println("ID:");
            std.ID = s.next();
            System.out.println("Name:");
            std.name= s.next();
            System.out.println("Department ID:");
            std.D_ID = s.next();
            String query = "insert into Student (Stud_ID,Stud_Name,Dep_ID)" + "values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, std.ID);
            ps.setString(2, std.name);
            ps.setString(3, std.D_ID);
            ps.execute();
            con.close();
            System.out.println("Insering records......Student Added Successfuly.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        menu();


    }

    public static void deleteRow(student std) {
        try {
            String SQL = "SELECT * FROM Student";
            ResultSet rs = connection().executeQuery(SQL);
            System.out.println("Enter the Student id you want to delete");
            Scanner scn = new Scanner(System.in);
            String delete = scn.next();
            rs.first();
            rs.previous();
            boolean found = false;
            while (rs.next()) {
                if (rs.getString("Stud_ID").compareTo(delete) == 0) {
                    rs.deleteRow();
                    System.out.println("Row Deleted Successfully.");
                    found = true;
                }
            }
            if (found == false) {
                System.out.println("Student not found.");
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());

        }
        menu();
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\n1,Display all student Information.");
        System.out.println("2,Search Specific Student.");
        System.out.println("3,Update Student Info");
        System.out.println("4,delete Student Info");
        System.out.println("5,Add Student.");
        boolean over = false;//Flag to check if valid choice is made...
        student std=new student();
        while (!over) {
            System.out.println("Choose an action:");
            String inp = input.next();
            while (inp.length() != 1) {
                System.out.print("INVALID INPUT. CHOOSE AGAIN.\n");

            }
            switch (inp.charAt(0)) {
                case '1': {
                    show(std);
                    over = true;
                    break;
                }
                case '2': {
                    search(std);
                    over = true;
                    break;
                }
                case '3': {
                    update(std);
                    over = true;
                    break;
                }
                case '4': {
                    deleteRow(std);
                    over = true;
                    break;
                }
                case '5': {
                    addRow(std);
                    over = true;
                    break;
                }
                default: {
                    System.out.println("Invalid input. Enter again");
                    break;
                }

            }

        }


    }
}

public class Clearance {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        actions act=new actions();
        act.menu();

    }
}
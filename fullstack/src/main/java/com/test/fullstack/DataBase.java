package com.test.fullstack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * @author Javin Paul
 */
public class DataBase {

    // JDBC URL, username and password of MySQL server
    private final static String url = "jdbc:mysql://localhost:3306/dog_exhebition?autoReconnect=true&useSSL=false";
    private final static String user = "root";
    private final static String password = "1234qseft";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    public static void main(String args[]) {
        Auto auto = getAuto(6);
        System.out.println(auto.getId() + " " + auto.getName() + " " + auto.getComand());
    }

    private static void GetDBConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private static void CloseDBConnection() {
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
    }

    public static Iterable<Auto> getAutos() {
        GetDBConnection();
        String query = "select * from auto";
        ArrayList<Auto> ar_auto = new ArrayList<>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setAuto(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("comand"), rs.getString("discription"), rs.getString("url"));
                ar_auto.add(auto);
            }
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }finally {
            //close connection ,stmt and resultset here
            CloseDBConnection();
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ } 
            //try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        return ar_auto;
    }

    public static void addAuto(Auto auto) {
        GetDBConnection();
        try {
            String query = "INSERT INTO auto (name, comand, discription, url)" + "VALUES (?, ?, ?, ?)";
            PreparedStatement prSt = con.prepareStatement(query);
            prSt.setString(1, auto.getName());
            prSt.setString(2, auto.getComand());
            prSt.setString(3, auto.getDiscription());
            prSt.setString(4, auto.getUrl());
            prSt.executeUpdate(); 
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }finally {
            CloseDBConnection();
        }
    }

    public static Auto getAuto(long id) {
        GetDBConnection();
        Auto auto = new Auto();
        try {
            String query = "SELECT * FROM auto WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            auto.setAuto(Integer.parseInt(rs.getString("id")), rs.getString("name"), rs.getString("comand"), rs.getString("discription"), rs.getString("url"));
        }
        catch (SQLException sqlEx) {
            //sqlEx.printStackTrace();
            return null;
        }finally {
            CloseDBConnection();
        }
        return auto;
    }

    public static void updAuto(Auto auto) {
        GetDBConnection();
        try {
            PreparedStatement st = con.prepareStatement("UPDATE auto SET name = ?, comand = ?, discription = ?, url = ? WHERE id = ?");
            st.setString(1, auto.getName());
            st.setString(2, auto.getComand());
            st.setString(3, auto.getDiscription());
            st.setString(4, auto.getUrl());
            st.setInt(5, auto.getId());
            st.executeUpdate();
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }finally {
            CloseDBConnection();
        }
    }

    public static void delAuto(Auto auto) {
        GetDBConnection();
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM auto WHERE id = ?");
            st.setInt(1, auto.getId());
            st.executeUpdate(); 
        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }finally {
            CloseDBConnection();
        }
    }
}
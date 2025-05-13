package com.example.final_project;

import java.sql.*;

public class DBAdapter {
    private static final String URL = "jdbc:postgresql://localhost:5432/shoplist";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1111";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {//создаёт новое соединение с базой.
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

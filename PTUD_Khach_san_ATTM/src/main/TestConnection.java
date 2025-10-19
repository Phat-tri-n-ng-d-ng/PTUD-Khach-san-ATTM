package main;

import database.connectDB;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = connectDB.getConnection()) {
            if (conn != null)
                System.out.println("✅ Kết nối thành công!");
            else
                System.out.println("❌ Kết nối thất bại!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

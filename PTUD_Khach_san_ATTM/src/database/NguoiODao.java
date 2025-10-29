package database;

import entity.NguoiO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NguoiODao {
    public boolean themNguoiO(NguoiO nguoiO, String maHD, String maPhong) {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO NguoiO (tenNguoiO, ngaySinh, gioiTinh, sdt, soCccd, maHD, maPhong) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, nguoiO.getHoTen());
            stmt.setDate(2, java.sql.Date.valueOf(nguoiO.getNgaySinh()));
            stmt.setBoolean(3, nguoiO.isGioiTinh());
            stmt.setString(4, nguoiO.getSDT());
            stmt.setString(5, nguoiO.getCCCD());
            stmt.setString(6, maHD);
            stmt.setString(7, maPhong);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng kết nối
            try {
                if (stmt != null) stmt.close();
                if (connection != null) ConnectDB.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
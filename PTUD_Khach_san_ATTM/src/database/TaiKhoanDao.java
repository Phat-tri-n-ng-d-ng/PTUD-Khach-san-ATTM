package database;

import entity.TaiKhoan;
import enums.TrangThaiTaiKhoan;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class TaiKhoanDao {
    public boolean CapTaiKhoanNhanVien(TaiKhoan taiKhoan, String maNV){
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{call CapTaiNhanVien(?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1,taiKhoan.getTenDangNhap());
            stmt.setString(2,taiKhoan.getMatKhau());
            stmt.setString(3, taiKhoan.getVaiTro().toString());
            stmt.setString(4,taiKhoan.getTrangThai().toString());
            stmt.setString(5,maNV);
            int n = stmt.executeUpdate();
            return n>0;
        }catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khác: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally {
            ConnectDB.closeConnection(connection);
        }
    }

    public boolean VoHieuHoaTaiKhoan(String maNV, TrangThaiTaiKhoan trangThaiTaiKhoan){
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{call VoHieuHoaTaiKhoan(?,?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1,maNV);
            stmt.setString(2,trangThaiTaiKhoan.toString());
            int n = stmt.executeUpdate();
            return n>0;
        }catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khác: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally {
            ConnectDB.closeConnection(connection);
        }
    }
}

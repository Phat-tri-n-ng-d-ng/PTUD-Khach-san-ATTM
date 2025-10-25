package database;

import entity.KhachHang;
import entity.NhanVien;
import enums.ChucVuNhanVien;
import enums.HangKhachHang;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class KhachHangDao {
    public ArrayList<KhachHang> getTatCaKhachHang(){
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "select * from KhachHang";
            CallableStatement stmt = connection.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String soCCCD = rs.getString("soCCCD");
                int diemTichLuy = rs.getInt("diemTichLuy");
                KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, ngaySinh, email, sdt,
                        soCCCD, diemTichLuy);
                dsKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return dsKhachHang;
    }

    public int getSoLuongKhachHang() {
        Connection connection = null;
        int soLuong = 0;
        try {
            connection = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM KhachHang";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return soLuong;
    }

    public boolean themKhachHang(KhachHang khachHang) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{call ThemKhachHang(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1,khachHang.getMaKH());
            stmt.setString(2,khachHang.getTenKH());
            stmt.setDate(3, Date.valueOf(khachHang.getNgaySinh()));
            stmt.setBoolean(4,khachHang.isGioiTinh());
            stmt.setString(5,khachHang.getSdt());
            stmt.setString(6,khachHang.getEmail());
            stmt.setString(7,khachHang.getSoCCCD());
            stmt.setString(8,khachHang.getHangKH().toString());
            stmt.setInt(9,khachHang.getDiemTichLuy());
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

    public ArrayList<KhachHang> getKhachHangTheoHang(String hangKhachHang) {
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection()) {
            CallableStatement stmt = con.prepareCall("{call LocKhachHangTheoHang(?)}");
            stmt.setString(1, hangKhachHang);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String soCCCD = rs.getString("soCCCD");
                int diemTichLuy = rs.getInt("diemTichLuy");
                KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, ngaySinh, email, sdt,
                        soCCCD, diemTichLuy);
                dsKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }

    public boolean CapNhatKhachHang(KhachHang khachHang) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{call CapNhatKhachHang(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1,khachHang.getMaKH());
            stmt.setString(2,khachHang.getTenKH());
            stmt.setDate(3, Date.valueOf(khachHang.getNgaySinh()));
            stmt.setBoolean(4,khachHang.isGioiTinh());
            stmt.setString(5,khachHang.getSdt());
            stmt.setString(6,khachHang.getEmail());
            stmt.setString(7,khachHang.getSoCCCD());
            stmt.setString(8,khachHang.getHangKH().toString());
            stmt.setInt(9,khachHang.getDiemTichLuy());
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
    public KhachHang TimKhachHang(String keyword, String type){
        KhachHang kh = null;
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql;
            if (type.equalsIgnoreCase("CCCD")) {
                sql = "{call TimKhachHangTheoSoCCCD(?)}";
            } else if (type.equalsIgnoreCase("SDT")) {
                sql = "{call TimKhachHangTheoSoDT(?)}";
            } else {
                throw new IllegalArgumentException("Loại tìm kiếm không hợp lệ: " + type);
            }
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1,keyword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String soCCCD = rs.getString("soCCCD");
                int diemTichLuy = rs.getInt("diemTichLuy");
                kh = new KhachHang(maKH, tenKH, gioiTinh, ngaySinh, email, sdt,
                        soCCCD, diemTichLuy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return kh;
    }
}

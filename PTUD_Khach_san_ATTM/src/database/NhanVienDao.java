package database;

import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import enums.ChucVuNhanVien;
import enums.TrangThaiTaiKhoan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVienDao {
    public ArrayList<NhanVien> getTatCaNhanVien(){
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{call GetTatCaNhanVien}";
            CallableStatement stmt = connection.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String string_chucVu = rs.getString("chucVu");
                ChucVuNhanVien chucVu = ChucVuNhanVien.valueOf(string_chucVu);
                String tenDangNhap = rs.getString("tenDangNhap");
                String trangThaiTaiKhoan = rs.getString("trangThai");
                TaiKhoan tk = null;
                if (tenDangNhap != null && trangThaiTaiKhoan != null) {
                    TrangThaiTaiKhoan trangThai = TrangThaiTaiKhoan.valueOf(trangThaiTaiKhoan);
                    tk = new TaiKhoan(tenDangNhap, trangThai);
                }
                NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, sdt, gioiTinh, email, chucVu,tk);
                dsNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return dsNhanVien;
    }
    public boolean themNhanVien(NhanVien nhanVien){
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{call ThemNhanVien(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1,nhanVien.getMaNV());
            stmt.setString(2,nhanVien.getTenNV());
            stmt.setDate(3, Date.valueOf(nhanVien.getNgaySinh()));
            stmt.setBoolean(4,nhanVien.isGioiTinh());
            stmt.setString(5,nhanVien.getSdt());
            stmt.setString(6,nhanVien.getEmail());
            stmt.setString(7,nhanVien.getChucVu().toString());
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

    public int getSoLuongNhanVien() {
        Connection connection = null;
        int soLuong = 0;
        try {
            connection = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM NhanVien";
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

    public boolean CapNhatNhanVien(NhanVien nhanVien) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{call CapNhatNhanVien(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1,nhanVien.getMaNV());
            stmt.setString(2,nhanVien.getTenNV());
            stmt.setDate(3, Date.valueOf(nhanVien.getNgaySinh()));
            stmt.setBoolean(4,nhanVien.isGioiTinh());
            stmt.setString(5,nhanVien.getSdt());
            stmt.setString(6,nhanVien.getEmail());
            stmt.setString(7,nhanVien.getChucVu().toString());
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

    public ArrayList<NhanVien> getNhanVienTheoChucVu(String chucVuChon) {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection()) {
            CallableStatement stmt = con.prepareCall("{call LocNhanVienTheoChucVu(?)}");
            stmt.setString(1, chucVuChon);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String string_chucVu = rs.getString("chucVu");
                ChucVuNhanVien chucVu = ChucVuNhanVien.valueOf(string_chucVu);
                NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, sdt, gioiTinh, email, chucVu);
                dsNhanVien.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public NhanVien TimNhanVien(String keyword, String type) {
        NhanVien nv = null;
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql;
            if (type.equalsIgnoreCase("MA")) {
                sql = "{call TimNhanVienTheoMa(?)}";
            } else if (type.equalsIgnoreCase("SDT")) {
                sql = "{call TimNhanVienTheoSoDT(?)}";
            } else {
                throw new IllegalArgumentException("Loại tìm kiếm không hợp lệ: " + type);
            }

            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1, keyword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String sdt = rs.getString("sdt");
                String email = rs.getString("email");
                String string_chucVu = rs.getString("chucVu");
                ChucVuNhanVien chucVu = ChucVuNhanVien.valueOf(string_chucVu);
                nv = new NhanVien(maNV, tenNV, ngaySinh, sdt, gioiTinh, email, chucVu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return nv;
    }
}
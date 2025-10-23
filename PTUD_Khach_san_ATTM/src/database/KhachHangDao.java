package database;

import entity.KhachHang;
import entity.NhanVien;
import enums.ChucVuNhanVien;
import enums.HangKhachHang;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
                HangKhachHang hangKhachHang = HangKhachHang.valueOf(rs.getString("hangKH"));
                int diemTichLuy = rs.getInt("diemTichLuy");
                KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, ngaySinh, email, sdt,
                        soCCCD, hangKhachHang, diemTichLuy);
                dsKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return dsKhachHang;
    }
}

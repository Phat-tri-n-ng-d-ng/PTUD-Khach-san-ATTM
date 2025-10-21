package database;

import entity.NhanVien;
import enums.ChucVuNhanVien;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVienDao {
    public ArrayList<NhanVien> getTatCaNhanVien(){
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "select * from NhanVien";
            stmt = connection.prepareCall(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
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
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return dsNhanVien;
    }
}

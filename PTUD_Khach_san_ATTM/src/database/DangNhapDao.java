/**
 * @ (#) DangNhapDao.java       1.0     28/10/2025
 * <p>
 * Copuright (c) 2025 IUH, All rights reserved
 */
package database;

import entity.TaiKhoan;
import enums.TrangThaiTaiKhoan;
import enums.VaiTro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @description:
 * @auther: Pham Le Huu Thang
 * @date: 28/10/2025
 * @version: 1.0
 */
public class DangNhapDao {
    public TaiKhoan dangNhap(String tenDangNhap, String matKhau){
        TaiKhoan taiKhoan = null;
        Connection connection = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT tk.*, nv.tenNV, nv.chucVu " +
                    "FROM TaiKhoan tk " +
                    "INNER JOIN NhanVien nv ON tk.maNV = nv.maNV " +
                    "WHERE tk.tenDangNhap = ? AND tk.matKhau = ? AND tk.trangThai = 'DangHoatDong'";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Lấy thông tin tài khoản từ ResultSet
                VaiTro vaiTro = VaiTro.valueOf(rs.getString("vaiTro"));
                TrangThaiTaiKhoan trangThai = TrangThaiTaiKhoan.valueOf(rs.getString("trangThai"));
                String maNV = rs.getString("maNV");
                String tenNV = rs.getString("tenNV");
                String chucVu = rs.getString("chucVu");

                taiKhoan = new TaiKhoan("tenDangNhap", "matKhau", vaiTro);
                taiKhoan.setTrangThai(trangThai);
                taiKhoan.setMaNV(maNV);
                taiKhoan.setTenNV(tenNV);
                taiKhoan.setChucVu(chucVu);
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) ConnectDB.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return taiKhoan;
    }
}

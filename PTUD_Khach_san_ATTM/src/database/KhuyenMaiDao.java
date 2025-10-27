/**
 * @ (#) KhuyenMaiDao.java       1.0     23/10/2025
 * <p>
 * Copuright (c) 2025 IUH, All rights reserved
 */
package database;

import entity.KhuyenMai;
import enums.TrangThaiKhuyenMai;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @description:
 * @auther: Pham Le Huu Thang
 * @date: 23/10/2025
 * @version: 1.0
 */
public class KhuyenMaiDao {
    public ArrayList<KhuyenMai> getTatCaKhuyenMai(){
        ArrayList<KhuyenMai> danhSachKhuyenMai = new ArrayList<>();
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "select * from KhuyenMai";
            stmt = connection.prepareCall(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
               danhSachKhuyenMai.add(taoKhuyenMaiTuResultSet(rs));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return danhSachKhuyenMai;
    }

    // Phương thức thêm khuyến mãi mới vào cơ sở dữ liệu
    public boolean themKhuyenMai(KhuyenMai khuyenMai){
        Connection ketNoi = null;
        PreparedStatement stmt = null;
        try{
            ketNoi = ConnectDB.getConnection();
            String sql = "INSERT INTO KhuyenMai (maKM, tenKM, dieuKienApDung, tyLeGiam, ngayBatDau, ngayKetThuc, trangThai) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = ketNoi.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getMaKM());
            stmt.setString(2, khuyenMai.getTenKM());
            stmt.setString(3, khuyenMai.getDieuKienApDung());
            stmt.setDouble(4, khuyenMai.getTyLeGiam());
            stmt.setTimestamp(5, Timestamp.valueOf(khuyenMai.getNgayBatDau()));
            stmt.setTimestamp(6, Timestamp.valueOf(khuyenMai.getNgayketThuc()));
            stmt.setString(7, khuyenMai.getTrangThai().toString());

            int n = stmt.executeUpdate();
            return n > 0;
        }catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khác: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            ConnectDB.closeConnection(ketNoi);
        }
    }

    // Phương thức cập nhật thông tin khuyến mãi trong cơ sở dữ liệu
    public boolean capNhatKhuyenMai(KhuyenMai khuyenMai){
        Connection ketNoi = null;
        PreparedStatement stmt = null;
        try{
            ketNoi = ConnectDB.getConnection();
            String sql = "UPDATE KhuyenMai SET tenKM = ?, dieuKienApDung = ?, tyLeGiam = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = ? " +
                    "WHERE maKM = ?";
            stmt = ketNoi.prepareStatement(sql);
            stmt.setString(1, khuyenMai.getTenKM());
            stmt.setString(2, khuyenMai.getDieuKienApDung());
            stmt.setDouble(3, khuyenMai.getTyLeGiam());
            stmt.setTimestamp(4, Timestamp.valueOf(khuyenMai.getNgayBatDau()));
            stmt.setTimestamp(5, Timestamp.valueOf(khuyenMai.getNgayketThuc()));
            stmt.setString(6, khuyenMai.getTrangThai().toString());
            stmt.setString(7, khuyenMai.getMaKM());

            int n = stmt.executeUpdate();
            return n > 0;
        }catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khác: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            ConnectDB.closeConnection(ketNoi);
        }
    }

    // Phương thức lấy số lượng khuyến mãi hiện có trong cơ sở dữ liệu
    public int getSoLuongKhuyenMai() {
        Connection connection = null;
        int soLuong = 0;
        try {
            connection = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM KhuyenMai";
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

    // Lấy số lượng của khuyến mãi theo từng năm
    public int getSoLuongKhuyenMaiTheoNam(int nam) {
        Connection ketNoi = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int soThuTuLonNhat = 0;

        try {
            ketNoi = ConnectDB.getConnection();
            // Tìm tất cả mã khuyến mãi có dạng KM + năm + số thứ tự
            String sql = "SELECT maKM FROM KhuyenMai WHERE maKM LIKE ?";
            stmt = ketNoi.prepareStatement(sql);
            stmt.setString(1, "KM" + String.valueOf(nam).substring(2) + "%"); // Sửa ở đây
            rs = stmt.executeQuery();

            while (rs.next()) {
                String maKM = rs.getString("maKM");
                // Kiểm tra xem mã có đúng định dạng KM + 2 số năm + 3 số không
                if (maKM.matches("KM" + String.valueOf(nam).substring(2) + "\\d{3}")) {
                    // Lấy 3 số cuối - SỬA LẠI CÁCH LẤY CHỈ SỐ
                    String soThuTuStr = maKM.substring(4); // KM25 -> 4 ký tự
                    try {
                        int soThuTu = Integer.parseInt(soThuTuStr);
                        if (soThuTu > soThuTuLonNhat) {
                            soThuTuLonNhat = soThuTu;
                        }
                    } catch (NumberFormatException e) {
                        // Bỏ qua nếu không parse được số
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDB.closeConnection(ketNoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return soThuTuLonNhat;
    }

    // Phương thức lấy khuyến mãi theo mã khuyến mãi
    public ArrayList<KhuyenMai> getKhuyenMaiTheoMa(String maKM){
        ArrayList<KhuyenMai> danhSachKhuyenMai = new ArrayList<>();
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "select * from KhuyenMai where maKM = ?";
            stmt = connection.prepareCall(sql);
            stmt.setString(1, maKM);
            rs = stmt.executeQuery();
            while (rs.next()){
                danhSachKhuyenMai.add(taoKhuyenMaiTuResultSet(rs));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return danhSachKhuyenMai;
    }

    // Lấy khuyến mãi theo mã
    public KhuyenMai layKhuyenMaiTheoMa(String maKM) {
        Connection ketNoi = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ketNoi = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
            stmt = ketNoi.prepareStatement(sql);
            stmt.setString(1, maKM);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return taoKhuyenMaiTuResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDB.closeConnection(ketNoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Lọc khuyến mãi theo trạng thái
//    public ArrayList<KhuyenMai> locKhuyenMaiTheoTrangThai(String trangThai) {
//        ArrayList<KhuyenMai> ketQua = new ArrayList<>();
//        Connection ketNoi = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            ketNoi = ConnectDB.getConnection();
//            String sql = "SELECT * FROM KhuyenMai WHERE trangThai = ?";
//            stmt = ketNoi.prepareStatement(sql);
//            stmt.setString(1, trangThai);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                ketQua.add(taoKhuyenMaiTuResultSet(rs));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                ConnectDB.closeConnection(ketNoi);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return ketQua;
//    }

    // Lọc khuyến mãi theo khoảng thời gian
    public ArrayList<KhuyenMai> locKhuyenMaiTheoKhoangThoiGian(LocalDateTime tuNgay, LocalDateTime denNgay) {
        ArrayList<KhuyenMai> ketQua = new ArrayList<>();
        Connection ketNoi = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ketNoi = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhuyenMai WHERE ngayBatDau >= ? AND ngayKetThuc <= ? ORDER BY maKM";
            stmt = ketNoi.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(tuNgay));
            stmt.setTimestamp(2, Timestamp.valueOf(denNgay));
            rs = stmt.executeQuery();

            while (rs.next()) {
                ketQua.add(taoKhuyenMaiTuResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDB.closeConnection(ketNoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    // Lọc khuyến mãi theo điều kiện áp dụng
    public ArrayList<KhuyenMai> locKhuyenMaiTheoDieuKien(String dieuKien) {
        ArrayList<KhuyenMai> ketQua = new ArrayList<>();
        Connection ketNoi = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ketNoi = ConnectDB.getConnection();

            // Nếu là "Tất cả", không cần điều kiện WHERE
            if ("Tất cả".equals(dieuKien)) {
                String sql = "SELECT * FROM KhuyenMai ORDER BY maKM";
                stmt = ketNoi.prepareStatement(sql);
            } else {
                String sql = "SELECT * FROM KhuyenMai WHERE dieuKienApDung LIKE ? ORDER BY maKM";
                stmt = ketNoi.prepareStatement(sql);
                stmt.setString(1, "%" + dieuKien + "%");
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                ketQua.add(taoKhuyenMaiTuResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDB.closeConnection(ketNoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ketQua;
    }

    // Phương thức hỗ trợ tạo đối tượng KhuyenMai từ ResultSet
    private KhuyenMai taoKhuyenMaiTuResultSet(ResultSet rs) throws SQLException {
        String maKM = rs.getString("maKM");
        String tenKM = rs.getString("tenKM");
        String dieuKienApDung = rs.getString("dieuKienApDung");
        double tyLeGiam = rs.getDouble("tyLeGiam");
        LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime();
        LocalDateTime ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime();
        TrangThaiKhuyenMai trangThai = TrangThaiKhuyenMai.valueOf(rs.getString("trangThai"));

        return new KhuyenMai(maKM, tenKM, dieuKienApDung, tyLeGiam, ngayBatDau, ngayKetThuc, trangThai);
    }

    // Cập nhật chỉ trạng thái khuyến mãi
    public boolean capNhatTrangThaiKhuyenMai(String maKM, TrangThaiKhuyenMai trangThai) {
        Connection ketNoi = null;
        PreparedStatement stmt = null;

        try {
            ketNoi = ConnectDB.getConnection();
            String sql = "UPDATE KhuyenMai SET trangThai = ? WHERE maKM = ?";
            stmt = ketNoi.prepareStatement(sql);
            stmt.setString(1, trangThai.toString());
            stmt.setString(2, maKM);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                ConnectDB.closeConnection(ketNoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Tự động cập nhật trạng thái khuyến mãi theo ngày hiện tại
    public void tuDongCapNhatTrangThaiKhuyenMai() {
        Connection ketNoi = null;
        PreparedStatement stmtSelect = null;
        PreparedStatement stmtUpdate = null;
        ResultSet rs = null;

        try {
            ketNoi = ConnectDB.getConnection();
            LocalDateTime ngayHienTai = LocalDateTime.now();

            // Lấy tất cả khuyến mãi
            String sqlSelect = "SELECT * FROM KhuyenMai";
            stmtSelect = ketNoi.prepareStatement(sqlSelect);
            rs = stmtSelect.executeQuery();

            String sqlUpdate = "UPDATE KhuyenMai SET trangThai = ? WHERE maKM = ?";
            stmtUpdate = ketNoi.prepareStatement(sqlUpdate);

            while (rs.next()) {
                String maKM = rs.getString("maKM");
                LocalDateTime ngayBatDau = rs.getTimestamp("ngayBatDau").toLocalDateTime();
                LocalDateTime ngayKetThuc = rs.getTimestamp("ngayKetThuc").toLocalDateTime();
                String trangThaiHienTai = rs.getString("trangThai");

                // Xác định trạng thái mới dựa trên ngày hiện tại
                TrangThaiKhuyenMai trangThaiMoi;

                if (ngayHienTai.isBefore(ngayBatDau)) {
                    trangThaiMoi = TrangThaiKhuyenMai.SapDienRa;
                } else if (ngayHienTai.isAfter(ngayKetThuc)) {
                    trangThaiMoi = TrangThaiKhuyenMai.HetHan;
                } else {
                    // Chỉ cập nhật thành Đang hoạt động nếu không phải Tạm ngừng
                    if (!trangThaiHienTai.equals("TamNgung")) {
                        trangThaiMoi = TrangThaiKhuyenMai.DangHoatDong;
                    } else {
                        continue; // Giữ nguyên trạng thái Tạm ngừng
                    }
                }

                // Cập nhật nếu trạng thái thay đổi
                if (!trangThaiHienTai.equals(trangThaiMoi.toString())) {
                    stmtUpdate.setString(1, trangThaiMoi.toString());
                    stmtUpdate.setString(2, maKM);
                    stmtUpdate.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmtSelect != null) stmtSelect.close();
                if (stmtUpdate != null) stmtUpdate.close();
                ConnectDB.closeConnection(ketNoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Kiểm tra mã khuyến mãi đã tồn tại chưa
    public boolean kiemTraMaKMTonTai(String maKM) {
        Connection ketNoi = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ketNoi = ConnectDB.getConnection();
            String sql = "SELECT COUNT(*) FROM KhuyenMai WHERE maKM = ?";
            stmt = ketNoi.prepareStatement(sql);
            stmt.setString(1, maKM);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                ConnectDB.closeConnection(ketNoi);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

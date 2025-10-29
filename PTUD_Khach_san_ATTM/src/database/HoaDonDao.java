package database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.Phong;
import enums.ChucVuNhanVien;
import enums.PhuongThucThanhToan;
import enums.TrangThaiHoaDon;

public class HoaDonDao {
    public ArrayList<HoaDon> getTatCaHoaDon(){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        HoaDon hd = null;
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{CALL getDanhSachHoaDon()}";
            CallableStatement stmt = connection.prepareCall(sql);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(tenKH,sdt);
                String pTTTString = rs.getString("pTTT");
                String tenNV = rs.getString("tenNV");
                String trangThai = rs.getString("trangThai");
                TrangThaiHoaDon trangThaiHD = TrangThaiHoaDon.valueOf(trangThai);


                NhanVien nv = new NhanVien();

                nv.setTenNV(tenNV);
                PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
                //Lay du lieu tu chi tiet hoa don
                ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);
                hd = new HoaDon(maHD, ngayLap, pttt,trangThaiHD , kh, dsCTDH , nv);
                hd.setTongTien();
                dsHoaDon.add(hd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return dsHoaDon;

    }
    public HoaDon timHoaDonTheoMa(String ma) {
        Connection connection = ConnectDB.getConnection();
        HoaDon hd = null;
        String sql = "{CALL timMaHoaDon(?)}";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ma);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(tenKH,sdt);
                String pTTTString = rs.getString("pTTT");
                String tenNV = rs.getString("tenNV");
                String trangThai = rs.getString("trangThai");
                TrangThaiHoaDon trangThaiHD = TrangThaiHoaDon.valueOf(trangThai);
                double tienNhan =  rs.getDouble("tienNhan");
                double tienTra =  rs.getDouble("tienTra");
                double khuyenMai = rs.getDouble("tyLeGiam");
                KhuyenMai km = new KhuyenMai(khuyenMai);
                NhanVien nv = new NhanVien();
                nv.setTenNV(tenNV);
                PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
                //Lay du lieu tu chi tiet hoa don
                ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);

                hd = new HoaDon(maHD, ngayLap, pttt, trangThaiHD, kh, dsCTDH , nv, km, tienNhan,tienTra);
                hd.setTongTien();
                hd.setTienThue();
                hd.setPhiDoiPhong();
                hd.setTongTienThanhToan();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return hd;
    }
    public HoaDon timHoaDonTheoSDT(String SDT) {
        Connection connection = ConnectDB.getConnection();
        HoaDon hd = null;
        String sql = "{CALL timSDT(?)}";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, SDT);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(tenKH,sdt);
                String pTTTString = rs.getString("pTTT");
                String tenNV = rs.getString("tenNV");
                String trangThai = rs.getString("trangThai");
                TrangThaiHoaDon trangThaiHD = TrangThaiHoaDon.valueOf(trangThai);
                double tienNhan =  rs.getDouble("tienNhan");
                double tienTra =  rs.getDouble("tienTra");
                double khuyenMai = rs.getDouble("tyLeGiam");
                KhuyenMai km = new KhuyenMai(khuyenMai);
                NhanVien nv = new NhanVien();
                nv.setTenNV(tenNV);
                PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
                //Lay du lieu tu chi tiet hoa don
                ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);

                hd = new HoaDon(maHD, ngayLap, pttt, trangThaiHD, kh, dsCTDH , nv, km, tienNhan,tienTra);
                hd.setTongTien();
                hd.setTienThue();
                hd.setPhiDoiPhong();
                hd.setTongTienThanhToan();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return hd;
    };
    public ArrayList<HoaDon> timHoaDonTheoNgay(LocalDateTime ngay) {
        Connection connection = ConnectDB.getConnection();
        HoaDon hd = null;
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        String sql = "{CALL timNgayLap(?)}";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(1, Timestamp.valueOf(ngay));
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(tenKH,sdt);
                String pTTTString = rs.getString("pTTT");
                String trangThai = rs.getString("trangThai");
                TrangThaiHoaDon trangThaiHoaDon = TrangThaiHoaDon.valueOf(trangThai);
                String tenNV = rs.getString("tenNV");
                NhanVien nv = new NhanVien();
                nv.setTenNV(tenNV);
                PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
                //Lay du lieu tu chi tiet hoa don
                ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);
                hd = new HoaDon(maHD, ngayLap, pttt, trangThaiHoaDon, kh, dsCTDH , nv);
                hd.setTongTien();
                dsHoaDon.add(hd);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return dsHoaDon;
    };
    public ArrayList<HoaDon> timHoaDonTheoKhoang(LocalDateTime ngayBD, LocalDateTime ngayKT) {
        Connection connection = ConnectDB.getConnection();
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        HoaDon hd = null;
        String sql = "{CALL timTrongKhoang(?,?)}";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(1, Timestamp.valueOf(ngayBD));
            st.setTimestamp(2, Timestamp.valueOf(ngayKT));
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(tenKH,sdt);
                String trangThai = rs.getString("trangThai");
                TrangThaiHoaDon trangThaiHoaDon = TrangThaiHoaDon.valueOf(trangThai);
                String pTTTString = rs.getString("pTTT");
                String tenNV = rs.getString("tenNV");
                NhanVien nv = new NhanVien();
                nv.setTenNV(tenNV);
                PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
                //Lay du lieu tu chi tiet hoa don
                ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);
                hd = new HoaDon(maHD, ngayLap, pttt, trangThaiHoaDon, kh, dsCTDH , nv);
                hd.setTongTien();
                dsHoaDon.add(hd);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return dsHoaDon;
    };
    public ArrayList<KhachHang> getKhachHangTheoHD(String ma){
        ArrayList<KhachHang> dsKh = new ArrayList<>();
        KhachHang kh = null;
        try {
            Connection connection = ConnectDB.getConnection();
            String sql = "{CALL getDanhSachKhachHangTheoMa(?)}";
            CallableStatement st = connection.prepareCall(sql);
            st.setString(1, ma);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                String tenKH = rs.getString("tenKH");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                String sdt = rs.getString("sdt");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String soCCCD = rs.getString("soCCCD");
                kh = new KhachHang(tenKH,gioiTinh,ngaySinh,sdt,soCCCD);
                dsKh.add(kh);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dsKh;
    }
    //Loc hoa don theo trang tthai
    public ArrayList<HoaDon> getHoaDonTheoTrangThai(String trangThaiHD){
        Connection connection = ConnectDB.getConnection();
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        String sql = "{CALL locHoaDonTheoTrangThai(?)}";
        HoaDon hd = null;
        try {
            CallableStatement st = connection.prepareCall(sql);
            st.setString(1, trangThaiHD);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(tenKH,sdt);
                String pTTTString = rs.getString("pTTT");
                String tenNV = rs.getString("tenNV");
                String trangThai = rs.getString("trangThai");
                TrangThaiHoaDon trangThaiHoaDon = TrangThaiHoaDon.valueOf(trangThai);
                NhanVien nv = new NhanVien();
                nv.setTenNV(tenNV);
                PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
                //Lay du lieu tu chi tiet hoa don
                ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);
                hd = new HoaDon(maHD, ngayLap, pttt,trangThaiHoaDon , kh, dsCTDH , nv);
                hd.setTongTien();
                dsHoaDon.add(hd);

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return dsHoaDon;


    }
    //CHI TIET HOA DOn
    public ArrayList<ChiTietHoaDon> getChiTietHoaDon(String maHD){
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
        Connection connection = null;
        Phong p = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{CALL getChiTietHoaDon(?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1, maHD);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String maPhong = rs.getString("maPhong");
                double giaPhong = rs.getDouble("giaPhong");
                p = new Phong(maPhong, giaPhong);
                int soNgayO = rs.getInt("soNgayO");
                ChiTietHoaDon cthd =  new ChiTietHoaDon(p, soNgayO);
                dsChiTietHoaDon.add(cthd);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return dsChiTietHoaDon;
    }

    public static String sinhMaHoaDonMoi() {
        Connection con = null;
        try {
            con = ConnectDB.getConnection();

            // Lấy ngày hiện tại
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");
            String datePart = now.format(formatter);
            String prefix = "HD" + datePart;

            // Query để lấy mã hóa đơn lớn nhất có cùng prefix trong ngày
            String sql = "SELECT MAX(maHD) as maxMaHD FROM HoaDon WHERE maHD LIKE ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, prefix + "%");

            ResultSet rs = st.executeQuery();

            int nextNumber = 1; // Mặc định bắt đầu từ 001

            if (rs.next()) {
                String maxMaHD = rs.getString("maxMaHD");
                if (maxMaHD != null && maxMaHD.startsWith(prefix)) {
                    // Lấy 3 số cuối và tăng lên 1
                    String lastThreeDigits = maxMaHD.substring(maxMaHD.length() - 3);
                    try {
                        nextNumber = Integer.parseInt(lastThreeDigits) + 1;
                    } catch (NumberFormatException e) {
                        nextNumber = 1;
                    }
                }
            }

            // Format số thứ tự thành 3 chữ số
            String sequencePart = String.format("%03d", nextNumber);

            return prefix + sequencePart;

        } catch (SQLException e) {
            e.printStackTrace();
            // Fallback
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");
            String datePart = now.format(formatter);
            String randomPart = String.format("%03d", (int)(Math.random() * 1000));
            return "HD" + datePart + randomPart;
        } finally {
            ConnectDB.closeConnection(con);
        }
    }

    public boolean themHoaDon(HoaDon hoaDon) {
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            con.setAutoCommit(false); // Bắt đầu transaction

            // 1. Thêm hóa đơn chính
            String sqlHoaDon = "INSERT INTO HoaDon (maHD, ngayLap, ngayNhanPhong, ngayTraPhong, pTTT, trangThai, tienNhan, maKH, maNV, tongTien, tienThue, tienGiam, phiDoiPhong, tongTienThanhToan, tienTra) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stHoaDon = con.prepareStatement(sqlHoaDon);

            stHoaDon.setString(1, hoaDon.getMaHD());
            stHoaDon.setTimestamp(2, Timestamp.valueOf(hoaDon.getNgayLap()));
            stHoaDon.setTimestamp(3, Timestamp.valueOf(hoaDon.getNgayNhanPhong()));
            stHoaDon.setTimestamp(4, Timestamp.valueOf(hoaDon.getNgayTraPhong()));
            stHoaDon.setString(5, hoaDon.getpTTT().name());
            stHoaDon.setString(6, hoaDon.getTrangThai().name());
            stHoaDon.setDouble(7, hoaDon.getTienNhan());
            stHoaDon.setString(8, hoaDon.getKhachHang().getMaKH());
            stHoaDon.setString(9, hoaDon.getNhanVien().getMaNV());
            stHoaDon.setDouble(10, hoaDon.getTongTien());
            stHoaDon.setDouble(11, hoaDon.getTienThue());
            stHoaDon.setDouble(12, hoaDon.getTienGiam());
            stHoaDon.setDouble(13, hoaDon.getPhiDoiPhong());
            stHoaDon.setDouble(14, hoaDon.getTongTienThanhToan());
            stHoaDon.setDouble(15, hoaDon.getTienTra());

            n = stHoaDon.executeUpdate();

            // 2. Thêm chi tiết hóa đơn
            if (n > 0) {
                themChiTietHoaDon(hoaDon, con);
            }

            con.commit(); // Commit transaction
            return n > 0;

        } catch (SQLException e) {
            try {
                if (con != null) con.rollback(); // Rollback nếu có lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectDB.closeConnection(con);
        }
    }

    private void themChiTietHoaDon(HoaDon hoaDon, Connection con) throws SQLException {
        String sql = "INSERT INTO ChiTietHoaDon (maHD, maPhong, thanhTien, soNgayO) VALUES (?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(sql);

        for (ChiTietHoaDon cthd : hoaDon.getcTHD()) {
            st.setString(1, hoaDon.getMaHD());
            st.setString(2, cthd.getPhong().getMaPhong());
            st.setDouble(3, cthd.getThanhTien());
            st.setInt(4, cthd.getSoNgayO());
            st.addBatch();
        }

        st.executeBatch();
    }
}
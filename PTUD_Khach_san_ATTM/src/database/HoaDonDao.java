package database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;

import entity.*;
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
			CallableStatement st = connection.prepareCall(sql);
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
	             NhanVien nv = new NhanVien();
	             nv.setTenNV(tenNV);
	             PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
	             //Lay du lieu tu chi tiet hoa don
	             ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);
	             hd = new HoaDon(maHD, ngayLap, pttt, trangThaiHD, kh, dsCTDH , nv, tienNhan,tienTra);
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
	public ArrayList<HoaDon> timHoaDonTheoSDT(String SDT) {
		Connection connection = ConnectDB.getConnection();
		HoaDon hd = null;
		String sql = "{CALL timSDT(?)}";
		ArrayList<HoaDon> dsHD = new ArrayList<>();
		try {
			CallableStatement st = connection.prepareCall(sql);
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
	             NhanVien nv = new NhanVien();
	             nv.setTenNV(tenNV);
	             PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
	             //Lay du lieu tu chi tiet hoa don
	             ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);
	             
	             hd = new HoaDon(maHD, ngayLap, pttt, trangThaiHD, kh, dsCTDH , nv, tienNhan,tienTra);
	             hd.setTongTien();
	             hd.setTienThue();
	             hd.setPhiDoiPhong();
	             hd.setTongTienThanhToan();		
	             dsHD.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            ConnectDB.closeConnection(connection);
        }
		return dsHD;
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

    public void tuDongCapNhatTrangThaiPhong(LocalDate ngayHomNay) {
        try (Connection conn = ConnectDB.getConnection();
             CallableStatement st = conn.prepareCall("{CALL TuDongCapNhatTrangThaiPhong(?)}")) {
            st.setDate(1, java.sql.Date.valueOf(ngayHomNay));
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<HoaDon> getHoaDonDatPhong() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        String sql = "{CALL TimHoaDonDatPhong()}";

        try {
            CallableStatement st = connection.prepareCall(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                TrangThaiHoaDon trangThai = TrangThaiHoaDon.valueOf(rs.getString("trangThai"));

                // Thông tin khách hàng
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(tenKH, sdt);

                // Thông tin phòng
                String maPhong = rs.getString("maPhong");
                String tenLoaiPhong = rs.getString("tenLoaiPhong");
                int soLuongToiDa = rs.getInt("soLuongToiDa");

                LoaiPhong loaiPhong = new LoaiPhong();
                loaiPhong.setTenLoaiPhong(tenLoaiPhong);

                Phong phong = new Phong();
                phong.setMaPhong(maPhong);
                phong.setLoaiPhong(loaiPhong);
                phong.setSoLuongToiDa(soLuongToiDa);

                // Chi tiết hóa đơn
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setPhong(phong);

                ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
                dsCTHD.add(cthd);

                HoaDon hoaDon = new HoaDon(
                        maHD,
                        ngayLap,
                        trangThai,
                        kh,
                        dsCTHD
                );

                dsHoaDon.add(hoaDon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(connection);
        }

        return dsHoaDon;
    }

    public void TuDongCapNhatTrangThaiPhong_TheoKhoangNgay(Date ngayBatDau, Date ngayKetThuc) {
        try (Connection conn = ConnectDB.getConnection();
             CallableStatement st = conn.prepareCall("{CALL TuDongCapNhatTrangThaiPhong_TheoKhoangNgay(?, ?)}")) {

            java.sql.Date sqlNgayBatDau = new java.sql.Date(ngayBatDau.getTime());
            java.sql.Date sqlNgayKetThuc = new java.sql.Date(ngayKetThuc.getTime());

            st.setDate(1, sqlNgayBatDau);
            st.setDate(2, sqlNgayKetThuc);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChiTietHoaDon timChiTietHoaDonTheoMaPhongVaTrangThaiHoaDon(String maPhongTim,String trangThaiHD){
        ChiTietHoaDon cthd = null;
        Connection connection = null;
        Phong p = null;
        try {
            connection = ConnectDB.getConnection();
            String sql = "{CALL timChiTietHoaDonTheoMaPhongVaTrangThaiHoaDon(?,?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setString(1, maPhongTim);
            stmt.setString(2,trangThaiHD);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String maPhongget = rs.getString("maPhong");
                double giaPhong = rs.getDouble("giaPhong");
                p = new Phong(maPhongget, giaPhong);
                int soNgayO = rs.getInt("soNgayO");
                cthd =  new ChiTietHoaDon(p, soNgayO);

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return cthd;
    }
    public HoaDon timHoaDonTheoMaPhongVaTrangThaiHoaDon(String maPhongTim,String trangThaiHDTim){
        Connection connection = ConnectDB.getConnection();
        HoaDon hd = null;

        try {
            CallableStatement st = connection.prepareCall("{call timHoaDonTheoMaPhongVaTrangThaiHoaDon(?,?)}");
            st.setString(1, maPhongTim);
            st.setString(2, trangThaiHDTim);
            ResultSet rs= st.executeQuery();
            while(rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
                LocalDateTime ngayNhanPhong = rs.getTimestamp("ngayNhanPhong").toLocalDateTime();
                LocalDateTime ngayTraPhong = rs.getTimestamp("ngayTraPhong").toLocalDateTime();
                String pTTTString = rs.getString("pTTT");
                PhuongThucThanhToan pttt = PhuongThucThanhToan.fromString(pTTTString);
                String trangThai = rs.getString("trangThai");
                TrangThaiHoaDon trangThaiHD = TrangThaiHoaDon.valueOf(trangThai);
                Double tongTienThanhToan = rs.getDouble("tongTienThanhToan");
                Double tongTien = rs.getDouble("tongTien");
                Double tienGiam= rs.getDouble("tienGiam");
                Double tienThue=rs.getDouble("tienThue");
                Double phiDoiPhong=rs.getDouble("phiDoiPhong");
                String maKM= rs.getString("maKM");
                String maKH=rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                String sdt = rs.getString("sdt");
                KhachHang kh = new KhachHang(maKH,tenKH,sdt);
                ArrayList<ChiTietHoaDon> dsCTDH = this.getChiTietHoaDon(maHD);
                String maNV=rs.getString("maNV");
                NhanVien nv = new NhanVien();
                nv.setTenNV(maNV);


                hd= new HoaDon(maHD,ngayLap,ngayNhanPhong,ngayTraPhong,pttt,trangThaiHD,tongTienThanhToan,tongTien,tienGiam,tienThue,phiDoiPhong,
                        new KhuyenMai(maKM),kh,dsCTDH,nv);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return hd;
    }

    public boolean capNhatHoaDonVaPhongSauKhiTraPhong(String maHD, int soNgayO, String pttt,
                                                      double thanhTien, double tongTien, double tienGiam, double phiDoiPhong,
                                                      double tongTienTT, double tienThue, double tienNhan, double tienTra){
        Connection con = ConnectDB.getConnection();
        int n = 0;
        try {
            CallableStatement cstmt = con.prepareCall("{call capNhatHoaDonVaPhongSauKhiTraPhong(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cstmt.setString(1, maHD);
            cstmt.setInt(2, soNgayO);
            cstmt.setString(3, pttt);
            cstmt.setDouble(4, thanhTien);
            cstmt.setDouble(5, tongTien);
            cstmt.setDouble(6, tienGiam);
            cstmt.setDouble(7, phiDoiPhong);
            cstmt.setDouble(8, tongTienTT);
            cstmt.setDouble(9, tienThue);
            cstmt.setDouble(10, tienNhan);
            cstmt.setDouble(11, tienTra);
            n = cstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(con);
        }
        return n > 0;
    }
}
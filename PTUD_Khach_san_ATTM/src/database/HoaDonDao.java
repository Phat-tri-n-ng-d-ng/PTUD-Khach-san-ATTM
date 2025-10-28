package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
}

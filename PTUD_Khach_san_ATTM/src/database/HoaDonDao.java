package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.Phong;
import enums.TrangThaiHoaDon;

public class HoaDonDao {
	public ArrayList<HoaDon> getTatCaHoaDon(){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
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
                double tongTien = rs.getDouble("tongTien");
                KhachHang kh = new KhachHang();
                kh.setTenKH(tenKH); // nhan du lieu data base
                kh.setSdt(sdt);	// nhan du lieu data base
                HoaDon hd = new HoaDon(maHD, ngayLap, TrangThaiHoaDon.HoaDonDatPhong,tongTien,kh, new ArrayList<ChiTietHoaDon>() );               
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
	                double tongTien = rs.getDouble("tongTien");
	                KhachHang kh = new KhachHang();
	                kh.setTenKH(tenKH); // nhan du lieu data base
	                kh.setSdt(sdt);	// nhan du lieu data base
	                hd = new HoaDon(maHD, ngayLap, TrangThaiHoaDon.HoaDonDatPhong,tongTien,kh, new ArrayList<ChiTietHoaDon>() ); 
				
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
	                double tongTien = rs.getDouble("tongTien");
	                KhachHang kh = new KhachHang();
	                kh.setTenKH(tenKH); // nhan du lieu data base
	                kh.setSdt(sdt);	// nhan du lieu data base
	                hd = new HoaDon(maHD, ngayLap, TrangThaiHoaDon.HoaDonDatPhong,tongTien,kh, new ArrayList<ChiTietHoaDon>() ); 
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
            ConnectDB.closeConnection(connection);
        }
		return hd;
	};
	public ArrayList<HoaDon> timHoaDonTheoNgay(Date ngay) {
		Connection connection = ConnectDB.getConnection();
		HoaDon hd = null;
		ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
		String sql = "{CALL timNgayLap(?)}";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setDate(1, ngay);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				 String maHD = rs.getString("maHD");
	                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
	                String tenKH = rs.getString("tenKH");
	                String sdt = rs.getString("sdt");
	                double tongTien = rs.getDouble("tongTien");
	                KhachHang kh = new KhachHang();
	                kh.setTenKH(tenKH); // nhan du lieu data base
	                kh.setSdt(sdt);	// nhan du lieu data base
	                hd = new HoaDon(maHD, ngayLap, TrangThaiHoaDon.HoaDonDatPhong,tongTien,kh, new ArrayList<ChiTietHoaDon>() ); 
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
	public ArrayList<HoaDon> timHoaDonTheoKhoang(Date ngayBD, Date ngayKT) {
		Connection connection = ConnectDB.getConnection();
		ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
		HoaDon hd = null;
		String sql = "{CALL timTrongKhoang(?,?)}";
		try {
			PreparedStatement st = connection.prepareStatement(sql);
			st.setDate(1, ngayBD);
			st.setDate(2, ngayKT);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				 String maHD = rs.getString("maHD");
	                LocalDateTime ngayLap = rs.getTimestamp("ngayLap").toLocalDateTime();
	                String tenKH = rs.getString("tenKH");
	                String sdt = rs.getString("sdt");
	                double tongTien = rs.getDouble("tongTien");
	                KhachHang kh = new KhachHang();
	                kh.setTenKH(tenKH); // nhan du lieu data base
	                kh.setSdt(sdt);	// nhan du lieu data base
	                hd = new HoaDon(maHD, ngayLap, TrangThaiHoaDon.HoaDonDatPhong,tongTien,kh, new ArrayList<ChiTietHoaDon>() ); 
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
	
	
}

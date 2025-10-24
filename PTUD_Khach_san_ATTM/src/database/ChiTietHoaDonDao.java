package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.ChiTietHoaDon;
import entity.Phong;

public class ChiTietHoaDonDao {
	public ArrayList<ChiTietHoaDon> getChiTietHoaDon(String maHD){
		ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
		Connection connection = null;
		Phong p = null;
		try {
			connection = ConnectDB.getConnection();
			String sql = "{CALL getChiTietHoaDon(?)}";
			CallableStatement stmt = connection.prepareCall(sql);
			ResultSet rs = stmt.executeQuery();
			stmt.setString(1, maHD);
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

package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entity.HoaDon;

public class HoaDonDao {
	public ArrayList<HoaDon> getDanhSachHoaDon() {
		Connection con = connectDB.getConnection();
		ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from HoaDon");
			while (rs.next()) {
				
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		};

}

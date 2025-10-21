//package database;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import entity.HoaDon;
//
//public class HoaDonDao {
//	public ArrayList<HoaDon> getDanhSachHoaDon() {
//		Connection con = ConnectDB.getConnection();
//		ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
//		try {
//			PreparedStatement st = con.prepareStatement();
//			ResultSet rs = st.executeQuery("Select maHD  from HoaDon");
//			while (rs.next()) {
//
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		};
//
//}

package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.HoaDon;
import entity.NguoiO;
import entity.Phong;

public class NguoiODao {
	public boolean themNguoiO(NguoiO nguoiO){
		Connection con = ConnectDB.getConnection();
		String sql = "{CALL themNguoiO(?,?,?,?,?,?,?)}";
		try {
			CallableStatement st = con.prepareCall(sql);
			st.setString(1, nguoiO.getCCCD());
			st.setString(2, nguoiO.getHoTen());
			st.setDate(3, Date.valueOf(nguoiO.getNgaySinh()));
			st.setBoolean(4, nguoiO.isGioiTinh());
			st.setString(5, nguoiO.getSDT());
			st.setString(6, nguoiO.getHoaDon().getMaHD());
			st.setString(7, nguoiO.getPhong().getMaPhong());
			int n = st.executeUpdate();
			return n>0;
		} catch (SQLException e) {
            System.out.println("Lỗi SQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Lỗi khác: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally {
            ConnectDB.closeConnection(con);
        }
	}
	public ArrayList<NguoiO> timNguoiOTheoMaPhong(String ma) {
		Connection con = ConnectDB.getConnection();
		String sql = "{CALL getNguoiOTheoMaPhong(?)}";
		NguoiO ngO = null;
		ArrayList<NguoiO> dsNgO = new ArrayList<>();
		try {
			CallableStatement st = con.prepareCall(sql);
			st.setString(1, ma);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				String maP = rs.getString("maPhong");
				HoaDon hd = new HoaDon();
				hd.setMaHD(maHD);
				Phong p = new Phong();
				p.setMaPhong(maP);
				ngO = new NguoiO(hd, p);
				dsNgO.add(ngO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			ConnectDB.closeConnection(con);
		}
		return dsNgO;
	}

}

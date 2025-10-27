package database;

import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiPhong;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class PhongDao {
    public ArrayList<Phong> getDSPhong(){
        Connection con = ConnectDB.getConnection();
        ArrayList<Phong> dsp= new ArrayList<>();

        try {
            CallableStatement st = con.prepareCall("{call getDanhSachPhong}");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Phong p = new Phong(rs.getString(1),TrangThaiPhong.valueOf(rs.getString(2)),
                        rs.getDouble(3),rs.getDouble(4),
                        new LoaiPhong(rs.getString(5),rs.getString(6)),rs.getInt(7));
                dsp.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
        return dsp;
    }

    public boolean themPhong(Phong phong){
        Connection con = ConnectDB.getConnection();
        int n=0;

        try {
            CallableStatement st = con.prepareCall("{call themPhong(?,?,?,?,?,?)}");
            st.setString(1,phong.getMaPhong());
            st.setString(2,phong.getTrangThai().name());
            st.setDouble(3,phong.getGiaPhong());
            st.setDouble(4,phong.getTienCoc());
            st.setString(5,phong.getLoaiPhong().getMaLoaiPhong());
            st.setInt(6,phong.getSoLuongToiDa());
            n= st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi thêm phòng: " + e.getMessage());
            return false;
//            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
        return n>0;

    }
    public boolean capNhatPhong(Phong p){
        Connection con = ConnectDB.getConnection();
        int n=0;
        try {
            CallableStatement st = con.prepareCall("{call capNhatThongTinPhong (?,?,?,?,?,?)}");
            st.setString(1,p.getMaPhong());
            st.setString(2,p.getTrangThai().name());
            st.setDouble(3,p.getGiaPhong());
            st.setDouble(4,p.getTienCoc());
            st.setString(5,p.getLoaiPhong().getMaLoaiPhong());
            st.setInt(6,p.getSoLuongToiDa());

            n=st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
        return n>0;
    }
    public Phong timPhongBangMa(String ma) {
        Connection con = ConnectDB.getConnection();
        Phong p = null;
        try {
            CallableStatement st = con.prepareCall("{call timPhongBangMa(?)}");
            st.setString(1,ma);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                p= new Phong(rs.getString(1),TrangThaiPhong.valueOf(rs.getString(2)),
                        rs.getDouble(3),rs.getDouble(4),
                        new LoaiPhong(rs.getString(5),rs.getString(6)),rs.getInt(7));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            ConnectDB.closeConnection(con);
        }
        return p;

    }

    public ArrayList<Phong> locPhongTheoLoai(String s){
        Connection con = ConnectDB.getConnection();
        ArrayList<Phong> dsp= new ArrayList<>();
        try {
            CallableStatement st = con.prepareCall("{call locTheoLoaiPhong (?)}");
            st.setString(1,s);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                Phong p = new Phong(rs.getString(1),TrangThaiPhong.valueOf(rs.getString(2)),
                        rs.getDouble(3),rs.getDouble(4),
                        new LoaiPhong(rs.getString(5),rs.getString(6)),rs.getInt(7));
                dsp.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(con);
        }
            return dsp;

    }

}

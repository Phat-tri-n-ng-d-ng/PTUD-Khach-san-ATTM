package services;

import database.KhachHangDao;
import database.NhanVienDao;
import entity.KhachHang;
import entity.NhanVien;

import java.util.ArrayList;

public class KhachHangService {
    private KhachHangDao khachHangDao;

    public  KhachHangService(){
        khachHangDao = new KhachHangDao();
    }

    public ArrayList<KhachHang> getTatCaKhachHang(){
        return khachHangDao.getTatCaKhachHang();
    }

    public int getSoLuongKhachHang() {
        return khachHangDao.getSoLuongKhachHang();
    }

    public boolean themKhachHang(KhachHang khachHang) {
        return khachHangDao.themKhachHang(khachHang);
    }

    public ArrayList<KhachHang> getKhachHangTheoHang(String hangKhachHang){
        return khachHangDao.getKhachHangTheoHang(hangKhachHang);
    }
    public boolean CapNhatKhachHang(KhachHang khachHang) {
        return khachHangDao.CapNhatKhachHang(khachHang);
    }
    public KhachHang TimKhachHang(String keyword, String type){
        return khachHangDao.TimKhachHang(keyword,type);
    }
}
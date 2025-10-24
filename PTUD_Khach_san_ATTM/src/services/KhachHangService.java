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

//    public boolean themKhachHang(KhachHang khachHang){
//        return KhachHangDao.themKhachHang(khachHang);
//    }
//
//    public int getSoLuongKhachHang() {
//        return KhachHangDao.getSoLuongKhachHang();
//    }
//
//    public boolean CapNhatKhachHang(KhachHang khachHang) {
//        return KhachHangDao.CapNhatKhachHang(khachHang);
//    }
}

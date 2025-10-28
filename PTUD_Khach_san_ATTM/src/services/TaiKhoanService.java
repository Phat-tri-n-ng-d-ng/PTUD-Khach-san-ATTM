package services;

import database.NhanVienDao;
import database.TaiKhoanDao;
import entity.TaiKhoan;
import enums.TrangThaiTaiKhoan;

public class TaiKhoanService {
    private TaiKhoanDao taiKhoanDao;
    public TaiKhoanService(){
        taiKhoanDao = new TaiKhoanDao();
    }

    public boolean CapTaiKhoanNhanVien(TaiKhoan tk, String maNV){
        return taiKhoanDao.CapTaiKhoanNhanVien(tk,maNV);
    }
    public boolean VoHieuHoaTaiKhoan(String maNV, TrangThaiTaiKhoan trangThaiTaiKhoan){
        return taiKhoanDao.VoHieuHoaTaiKhoan(maNV,trangThaiTaiKhoan);
    }
}

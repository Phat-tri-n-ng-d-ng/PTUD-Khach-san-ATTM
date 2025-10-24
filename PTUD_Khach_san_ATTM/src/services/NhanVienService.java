package services;

import database.NhanVienDao;
import entity.NhanVien;

import java.util.ArrayList;

public class NhanVienService {
    private NhanVienDao nhanVienDao;

    public  NhanVienService(){
        nhanVienDao = new NhanVienDao();
    }

    public ArrayList<NhanVien>  getTatCaNhanVien(){
        return nhanVienDao.getTatCaNhanVien();
    }

    public boolean themNhanVien(NhanVien nhanVien){
        return nhanVienDao.themNhanVien(nhanVien);
    }

    public int getSoLuongNhanVien() {
        return nhanVienDao.getSoLuongNhanVien();
    }

    public boolean CapNhatNhanVien(NhanVien nhanVien) {
        return nhanVienDao.CapNhatNhanVien(nhanVien);
    }

    public ArrayList<NhanVien>  getNhanVienTheoChucVu(String chucVu){
        return nhanVienDao.getNhanVienTheoChucVu(chucVu);
    }
}

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
}

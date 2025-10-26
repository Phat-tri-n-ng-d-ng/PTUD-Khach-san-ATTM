package services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import database.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import enums.TrangThaiHoaDon;

public class HoaDonService {
	private HoaDonDao hoaDonDao;

	public HoaDonService() {
		hoaDonDao =  new HoaDonDao();
	}
	public ArrayList<HoaDon> getDanhSachHoaDon(){
		return hoaDonDao.getTatCaHoaDon();
	}
	public ArrayList<HoaDon> timHoaDonTheoKhoang(LocalDateTime ngayBD, LocalDateTime ngayKT) {
	    return hoaDonDao.timHoaDonTheoKhoang(ngayBD, ngayKT);
	}
	public HoaDon timHoaDonTheoMa(String ma) {
		return hoaDonDao.timHoaDonTheoMa(ma);
	}
	public HoaDon timHoaDonTheoSDT(String SDT) {
		return hoaDonDao.timHoaDonTheoSDT(SDT);
	}
	public ArrayList<HoaDon> timHoaDonTheoNgay(LocalDateTime ngay) {
	    return hoaDonDao.timHoaDonTheoNgay(ngay);
	}
	public ArrayList<KhachHang> getKhachHangTheoHD (String ma){
		return hoaDonDao.getKhachHangTheoHD(ma);
	}
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMa(String ma){
		return hoaDonDao.getChiTietHoaDon(ma);
	}
	public ArrayList<HoaDon> getHoaDonTheoTrangThai(String trangThai){
		return hoaDonDao.getHoaDonTheoTrangThai(trangThai);
	}

}

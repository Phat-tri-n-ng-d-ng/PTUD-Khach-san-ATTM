package services;

import java.sql.Date;
import java.util.ArrayList;

import database.HoaDonDao;
import entity.HoaDon;

public class HoaDonService {
	private HoaDonDao hoaDonDao;

	public HoaDonService() {
		hoaDonDao =  new HoaDonDao();
	}
	public ArrayList<HoaDon> getDanhSachHoaDon(){
		return hoaDonDao.getTatCaHoaDon();
	}
	public ArrayList<HoaDon> timHoaDonTheoKhoang(Date ngayBD, Date ngayKT) {
	    return hoaDonDao.timHoaDonTheoKhoang(ngayBD, ngayKT);
	}
	public HoaDon timHoaDonTheoMa(String ma) {
		return hoaDonDao.timHoaDonTheoMa(ma);
	}
	public HoaDon timHoaDonTheoSDT(String SDT) {
		return hoaDonDao.timHoaDonTheoSDT(SDT);
	}
	public ArrayList<HoaDon> timHoaDonTheoNgay(Date ngay) {
	    return hoaDonDao.timHoaDonTheoNgay(ngay);
	}

}

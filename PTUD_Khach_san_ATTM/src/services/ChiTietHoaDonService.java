package services;

import java.util.ArrayList;

import database.ChiTietHoaDonDao;
import entity.ChiTietHoaDon;

public class ChiTietHoaDonService {
	private ChiTietHoaDonDao cthdDao;

	public ChiTietHoaDonService() {
		cthdDao = new ChiTietHoaDonDao();
	}
	public ArrayList<ChiTietHoaDon> getChiTietHoaDonTheoMa(String ma){
		return cthdDao.getChiTietHoaDon(ma);
	}
	

}

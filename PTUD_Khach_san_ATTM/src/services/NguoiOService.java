package services;

import java.util.ArrayList;

import database.NguoiODao;
import entity.NguoiO;

public class NguoiOService {
	private NguoiODao nguoiODao;
	public NguoiOService() {
		nguoiODao = new NguoiODao();
	}
	public boolean themNguoiO(NguoiO nguoiO) {
		return nguoiODao.themNguoiO(nguoiO);
	}
	public ArrayList<NguoiO> getNguoiOTheoMaPhong(String ma){
		return nguoiODao.timNguoiOTheoMaPhong(ma);
	}
}

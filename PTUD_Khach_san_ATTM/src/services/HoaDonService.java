package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

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
	public ArrayList<HoaDon> timHoaDonTheoSDT(String SDT) {
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
    public HoaDon timHoaDonTheoMaPhongVaTrangThaiHoaDon(String maPhongTim,String trangThaiHDTim){
        return hoaDonDao.timHoaDonTheoMaPhongVaTrangThaiHoaDon(maPhongTim, trangThaiHDTim);
    }
    public boolean capNhatHoaDonVaPhongSauKhiTraPhong(String maHD, int soNgayO,  String pttt,
                                                      double thanhTien, double tongTien, double tienGiam, double phiDoiPhong,
                                                      double tongTienTT, double tienThue, double tienNhan, double tienTra){
        return hoaDonDao.capNhatHoaDonVaPhongSauKhiTraPhong(maHD, soNgayO, pttt,
                thanhTien, tongTien, tienGiam, phiDoiPhong,
                tongTienTT, tienThue, tienNhan, tienTra);
    }
	public ChiTietHoaDon timChiTietHoaDonTheoMaPhongVaTrangThaiHoaDon(String ma, String trangThai) {
		return hoaDonDao.timChiTietHoaDonTheoMaPhongVaTrangThaiHoaDon(ma, trangThai);
	}
	public HoaDon getHoaDonTheoMaPhongVaTTHD(String maHD, String trangThai) {
		return hoaDonDao.getHoaDonTheoMaPhongVaTTHD(maHD, trangThai);
	}
	public boolean capNhatTrangThai(String ma) {
		return hoaDonDao.capNhatTrangThaiHoaDon(ma);
	}
	public HoaDon getHoaDon(String ma,String trangThai) {
		return hoaDonDao.getHoaDon(ma, trangThai);
	}

    public void tuDongCapNhatTrangThaiPhong(LocalDate ngayHomNay) {
        hoaDonDao.tuDongCapNhatTrangThaiPhong(ngayHomNay);
    }

    public ArrayList<HoaDon> getHoaDonDatPhong() {
        return  hoaDonDao.getHoaDonDatPhong();
    }

    public void TuDongCapNhatTrangThaiPhong_TheoKhoangNgay(Date ngayBatDau, Date ngayKetThuc) {
        hoaDonDao.TuDongCapNhatTrangThaiPhong_TheoKhoangNgay(ngayBatDau,ngayKetThuc);
    }
}

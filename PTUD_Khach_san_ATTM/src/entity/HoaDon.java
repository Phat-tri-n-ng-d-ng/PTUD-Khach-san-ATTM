package entity;

import enums.PhuongThucThanhToan;
import enums.TrangThaiHoaDon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;


public class HoaDon {
	private String maHD;
    private LocalDateTime ngayLap;
    private LocalDateTime ngayNhanPhong;
    private LocalDateTime ngayTraPhong;
    private PhuongThucThanhToan pTTT;
    private TrangThaiHoaDon trangThai;
    private double tongTienThanhToan;
    private double tongTien;
    private double tienGiam;
    private double tienNhan;
    private double tienTra;
    private double tienThue;
    private double phiDoiPhong;
    private KhuyenMai khuyenMai;
    private KhachHang khachHang;
    private ArrayList<ChiTietHoaDon> cTHD;
    private NhanVien nhanVien;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDateTime getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(LocalDateTime ngayLap) {
		this.ngayLap = ngayLap;
	}
	public LocalDateTime getNgayNhanPhong() {
		return ngayNhanPhong;
	}
	public void setNgayNhanPhong(LocalDateTime ngayNhanPhong) {
		this.ngayNhanPhong = ngayNhanPhong;
	}
	public LocalDateTime getNgayTraPhong() {
		return ngayTraPhong;
	}
	public void setNgayTraPhong(LocalDateTime ngayTraPhong) {
		this.ngayTraPhong = ngayTraPhong;
	}
	public PhuongThucThanhToan getpTTT() {
		return pTTT;
	}
	public void setpTTT(PhuongThucThanhToan pTTT) {
		this.pTTT = pTTT;
	}
	public double getTienNhan() {
		return tienNhan;
	}
	public void setTienNhan(double tienNhan) {
		this.tienNhan = tienNhan;
	}
	public TrangThaiHoaDon getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(TrangThaiHoaDon trangThai) {
		this.trangThai = trangThai;
	}
	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}
	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public ArrayList<ChiTietHoaDon> getcTHD() {
		return cTHD;
	}
	public void setcTHD(ArrayList<ChiTietHoaDon> cTHD) {
		this.cTHD = cTHD;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
    // dẫn xuất
	public double getTongTienThanhToan() {
		return tongTienThanhToan;
	}
	public void setTongTienThanhToan() {
		
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien() {
		
	}
	public double getTienGiam() {
		return tienGiam;
	}
	public void setTienGiam() {
		
	}
	public double getTienTra() {
		return tienTra;
	}
	public void setTienTra() {
		
	}
	public double getTienThue() {
		return tienThue;
	}
	public void setTienThue() {
		
	}
	public double getPhiDoiPhong() {
		return phiDoiPhong;
	}
	public void setPhiDoiPhong() {
		
	}
	public HoaDon(String maHD, LocalDateTime ngayLap, LocalDateTime ngayNhanPhong, LocalDateTime ngayTraPhong,
			PhuongThucThanhToan pTTT, TrangThaiHoaDon trangThai, double tienNhan,  KhuyenMai khuyenMai,
			KhachHang khachHang, ArrayList<ChiTietHoaDon> cTHD, NhanVien nhanVien) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.ngayNhanPhong = ngayNhanPhong;
		this.ngayTraPhong = ngayTraPhong;
		this.pTTT = pTTT;
		this.trangThai = trangThai;
		setTongTienThanhToan();
		setTongTien();
		setTienGiam();
		this.tienNhan = tienNhan;
		setTienTra();
		setTienThue();
		setPhiDoiPhong();
		this.khuyenMai = khuyenMai;
		this.khachHang = khachHang;
		this.cTHD = cTHD;
		this.nhanVien = nhanVien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cTHD);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(cTHD, other.cTHD);
	}
    
}

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
    private ArrayList<ChiTietHoaDon> dsCTHD;
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
		return dsCTHD;
	}
	public void setcTHD(ArrayList<ChiTietHoaDon> dsCTHD) {
		this.dsCTHD = dsCTHD;
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
		this.tongTienThanhToan = tongTien + tienThue -tienGiam+ phiDoiPhong;
	}
	public double getTongTien() {
		return tongTien;
	}
	//doi lai tong tien
	public void setTongTien() {
		double tong = 0;
		for(ChiTietHoaDon cthd : dsCTHD) {
			tong += cthd.getThanhTien();
		}
		this.tongTien = tong;
		
	}
	
	public double getTienGiam() {
		return tienGiam;
	}
	public void setTienGiam() {
		this.tienGiam = tongTien * khuyenMai.getTyLeGiam();
	}
	public double getTienTra() {
		return tienTra;
	}
	public void setTienTra() {
		this.tienTra = tienNhan - tongTienThanhToan;
		
	}
	public double getTienThue() {
		return tienThue;
	}
	public void setTienThue() {
		this.tienThue = tongTien * 0.1;
		
	}
	public double getPhiDoiPhong() {
		return phiDoiPhong;
	}
	public void setPhiDoiPhong() {
		
		
	}
	public HoaDon(String maHD, LocalDateTime ngayLap, LocalDateTime ngayNhanPhong, LocalDateTime ngayTraPhong,
			PhuongThucThanhToan pTTT, TrangThaiHoaDon trangThai, double tienNhan,  KhuyenMai khuyenMai,
			KhachHang khachHang, ArrayList<ChiTietHoaDon> dsCTHD, NhanVien nhanVien) {
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.ngayNhanPhong = ngayNhanPhong;
		this.ngayTraPhong = ngayTraPhong;
		this.pTTT = pTTT;
		this.trangThai = trangThai;
		setTongTien();
		setTienThue();
		setTienGiam();
		setPhiDoiPhong();
		setTongTienThanhToan();
		setTienTra();
		this.tienNhan = tienNhan;
		this.khuyenMai = khuyenMai;
		this.khachHang = khachHang;
		this.dsCTHD = dsCTHD;
		this.nhanVien = nhanVien;
	}
	
	
	
	
	
	public HoaDon(String maHD, LocalDateTime ngayLap, PhuongThucThanhToan pTTT, TrangThaiHoaDon trangThai,
			KhachHang khachHang, ArrayList<ChiTietHoaDon> dsCTHD, NhanVien nhanVien) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.pTTT = pTTT;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.dsCTHD = dsCTHD;
		this.nhanVien = nhanVien;
	}
	public HoaDon(String maHD, LocalDateTime ngayLap, PhuongThucThanhToan pTTT, TrangThaiHoaDon trangThai,
			KhachHang khachHang, ArrayList<ChiTietHoaDon> dsCTHD, NhanVien nhanVien , KhuyenMai khuyenMai, double tienNhan, double tienTra ) {
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.pTTT = pTTT;
		this.trangThai = trangThai;
		this.khachHang = khachHang;
		this.dsCTHD = dsCTHD;
		this.nhanVien = nhanVien;
		this.khuyenMai = khuyenMai;
		this.tienNhan = tienNhan;
		this.tienTra = tienTra;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(dsCTHD);
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
		return Objects.equals(dsCTHD, other.dsCTHD);
	}
    
}

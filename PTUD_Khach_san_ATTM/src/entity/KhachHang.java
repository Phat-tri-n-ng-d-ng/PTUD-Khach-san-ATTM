package entity;


import enums.HangKhachHang;

import java.time.LocalDate;
import java.util.Objects;

public class KhachHang {
	private String maKH;
    private String tenKH;
    private boolean gioiTinh;
    private LocalDate ngaySinh;
    private String email;
    private String sdt;
    private String soCCCD;
    private HangKhachHang hangKH;
    private int diemTichLuy;
	public String getMaKH() {
		return maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getSoCCCD() {
		return soCCCD;
	}
	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}
    // dẫn xuất
	public HangKhachHang getHangKH() {
		return hangKH;
	}
	public void setHangKH(int diemTichLuy) {
		if(diemTichLuy>=0 && diemTichLuy<=10) this.hangKH = HangKhachHang.Dong;
        else if(diemTichLuy>10 && diemTichLuy<=20) this.hangKH = HangKhachHang.Bac;
        else if(diemTichLuy>20 && diemTichLuy<=40) this.hangKH = HangKhachHang.Vang;
        else this.hangKH = HangKhachHang.KimCuong;
	}
	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}
//	public KhachHang(String maKH, String tenKH, boolean gioiTinh, LocalDate ngaySinh, String email, String sdt,
//			String soCCCD) {
//		super();
//		this.maKH = maKH;
//		this.tenKH = tenKH;
//		this.gioiTinh = gioiTinh;
//		this.ngaySinh = ngaySinh;
//		this.email = email;
//		this.sdt = sdt;
//		this.soCCCD = soCCCD;
//		setHangKH();
//		setDiemTichLuy();
//	}
    public KhachHang(String maKH, String tenKH, boolean gioiTinh, LocalDate ngaySinh, String email, String sdt,
                     String soCCCD, int diemTichLuy) {
        super();
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.sdt = sdt;
        this.soCCCD = soCCCD;
        setHangKH(diemTichLuy);
        this.diemTichLuy = diemTichLuy;
    }
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	
}

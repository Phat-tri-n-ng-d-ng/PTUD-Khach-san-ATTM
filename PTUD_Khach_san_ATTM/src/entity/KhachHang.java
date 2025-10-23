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
	public void setHangKH() {
		this.hangKH = HangKhachHang.Dong;
	}
	public int getDiemTichLuy() {
		return diemTichLuy;
	}
	public void setDiemTichLuy() {
		this.diemTichLuy = 0;
	}
	public KhachHang(String maKH, String tenKH, boolean gioiTinh, LocalDate ngaySinh, String email, String sdt,
			String soCCCD) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.sdt = sdt;
		this.soCCCD = soCCCD;
		setHangKH();
		setDiemTichLuy();
	}
    public KhachHang(String maKH, String tenKH, boolean gioiTinh, LocalDate ngaySinh, String email, String sdt,
                     String soCCCD,HangKhachHang hangKhachHang, int diemTichLuy) {
        super();
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.sdt = sdt;
        this.soCCCD = soCCCD;
        this.hangKH = hangKhachHang;
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

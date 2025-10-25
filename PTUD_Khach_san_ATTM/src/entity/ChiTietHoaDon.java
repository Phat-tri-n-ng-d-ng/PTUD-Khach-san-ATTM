package entity;

public class ChiTietHoaDon {
	private double thanhTien;
    private Phong phong;
    private HoaDon HoaDon;
    private int soNgayO;
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
		this.phong = phong;
	}
	public HoaDon getHoaDon() {
		return HoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		HoaDon = hoaDon;
	}
	// dẫn xuất
	public double getThanhTien() {
		return thanhTien;
	}
	public double setThanhTien(Phong phong,int soNgayO) {
		return phong.getGiaPhong() * soNgayO;
	}
	public ChiTietHoaDon(Phong phong, HoaDon hoaDon,int soNgayO) {
		setThanhTien(phong,soNgayO);
		this.phong = phong;
		HoaDon = hoaDon;
	}

	
}

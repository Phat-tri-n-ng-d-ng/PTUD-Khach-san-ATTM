package entity;

public class ChiTietHoaDon {
	private double thanhTien;
    private Phong phong;
    private HoaDon HoaDon;
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
	public void setThanhTien() {
		
	}
	public ChiTietHoaDon(Phong phong, HoaDon hoaDon) {
		setThanhTien();
		this.phong = phong;
		HoaDon = hoaDon;
	}

	
}

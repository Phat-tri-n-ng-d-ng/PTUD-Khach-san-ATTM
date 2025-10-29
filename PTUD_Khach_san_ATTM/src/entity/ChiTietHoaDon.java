package entity;

public class ChiTietHoaDon {
    private double thanhTien;
    private Phong phong;
    private HoaDon HoaDon;
    private int soNgayO;

    public int getSoNgayO() {
        return soNgayO;
    }

    public void setSoNgayO(int soNgayO) {
        this.soNgayO = soNgayO;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    //	public HoaDon getHoaDon() {
//		return HoaDon;
//	}
//	public void setHoaDon(HoaDon hoaDon) {
//		HoaDon = hoaDon;
//	}
    // dẫn xuất
    private double tinhThanhTien() {
        return soNgayO * phong.getGiaPhong();
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public double setThanhTien(Phong phong, int soNgayO) {
        return phong.getGiaPhong() * soNgayO;
    }

    public ChiTietHoaDon(Phong phong, HoaDon hoaDon, int soNgayO) {
        setThanhTien(phong, soNgayO);
    }

    public ChiTietHoaDon(Phong phong,  int soNgayO) {
        this.phong = phong;
        this.soNgayO = soNgayO;
        this.thanhTien = tinhThanhTien();
    }
}

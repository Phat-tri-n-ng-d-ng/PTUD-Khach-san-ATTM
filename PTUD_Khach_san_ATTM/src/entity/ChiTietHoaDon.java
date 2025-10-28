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
    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Phong phong, int soNgayO) {
        this.thanhTien = phong.getGiaPhong() * soNgayO;
    }

    public ChiTietHoaDon(Phong phong, HoaDon hoaDon, int soNgayO) {
        setThanhTien(phong, soNgayO);
    }
    public ChiTietHoaDon(Phong phong,  int soNgayO) {
        this.phong = phong;
        this.soNgayO = soNgayO;
        setThanhTien(phong, soNgayO);
//	    System.out.println("=maPhong=" + phong.getMaPhong() + ", soNgayO=" + soNgayO + ", thanhTien=" + thanhTien);
    }
}

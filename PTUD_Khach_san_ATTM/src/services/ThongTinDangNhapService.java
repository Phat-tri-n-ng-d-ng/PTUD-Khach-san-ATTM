package services;

import entity.TaiKhoan;

public class ThongTinDangNhapService {
    private TaiKhoan taiKhoan;
    private String tenNV;
    private String chucVu;
    private String maNV;

    public ThongTinDangNhapService(TaiKhoan taiKhoan, String tenNV, String chucVu, String maNV) {
        this.taiKhoan = taiKhoan;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.maNV = maNV;
    }

    // Getter methods
    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public String getMaNV() {
        return maNV;
    }
}
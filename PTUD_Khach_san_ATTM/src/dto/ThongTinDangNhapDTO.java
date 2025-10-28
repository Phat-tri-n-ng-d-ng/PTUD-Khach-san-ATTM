/**
 * @ (#) ThongTinDangNhapDTO.java       1.0     28/10/2025
 * <p>
 * Copuright (c) 2025 IUH, All rights reserved
 */
package dto;

import entity.TaiKhoan;
import enums.TrangThaiTaiKhoan;
import enums.VaiTro;

public class ThongTinDangNhapDTO {
    private TaiKhoan taiKhoan;
    private String tenNV;
    private String chucVu;
    private String maNV;

    public ThongTinDangNhapDTO(TaiKhoan taiKhoan, String tenNV, String chucVu, String maNV) {
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

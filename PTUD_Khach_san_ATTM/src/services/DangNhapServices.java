/**
 * @ (#) DangNhapServices.java       1.0     29/10/2025
 * <p>
 * Copuright (c) 2025 IUH, All rights reserved
 */
package services;

import database.DangNhapDao;
import entity.NhanVien;
import entity.TaiKhoan;

/**
 * @description:
 * @auther: Pham Le Huu Thang
 * @date: 29/10/2025
 * @version: 1.0
 */
public class DangNhapServices {
    private static DangNhapServices instance;
    private DangNhapDao dangNhapDao = new DangNhapDao();
    private NhanVienService nhanVienService = new NhanVienService();
    private NhanVien nv;

    // Private constructor
    private DangNhapServices() {}

    // Public static method to get instance
    public static DangNhapServices getInstance() {
        if (instance == null) {
            instance = new DangNhapServices();
        }
        return instance;
    }

    // Reset instance khi đăng xuất
    public static void resetInstance() {
        instance = null;
    }

    public TaiKhoan dangNhap(String tenDangNhap, String matKhau) {
        TaiKhoan tk = dangNhapDao.dangNhap(tenDangNhap, matKhau);
        if (tk != null) {
            nv = nhanVienService.TimNhanVien(tk.getMaNV(), "MA");
        }
        return tk;
    }

    public NhanVien getNhanVienDangNhap() {
        return nv;
    }
}

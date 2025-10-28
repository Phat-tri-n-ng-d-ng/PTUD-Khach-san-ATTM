/**
 * @ (#) DangNhapController.java       1.0     28/10/2025
 * <p>
 * Copuright (c) 2025 IUH, All rights reserved
 */
package controller;

import database.DangNhapDao;
import entity.TaiKhoan;
import views.DangNhapFrame;
import views.MainFrame;

import javax.swing.*;

public class DangNhapController {
    private DangNhapFrame dangNhapFrame;
    private DangNhapDao dangNhapDao;

    public DangNhapController(DangNhapFrame dangNhapFrame) {
        this.dangNhapFrame = dangNhapFrame;
        this.dangNhapDao = new DangNhapDao();

        this.dangNhapFrame.btn_DangNhap.addActionListener(e -> xuLyDangNhap());
        this.dangNhapFrame.btn_QuenMatKhau.addActionListener(e -> xuLyQuenMatKhau());
    }

    private void xuLyDangNhap() {
        String tenDangNhap = dangNhapFrame.txt_TaiKhoan.getText();
        String matKhau = new String(dangNhapFrame.passwordField_MatKhau.getPassword());

        // Kiểm tra thông tin đăng nhập
        if(tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(dangNhapFrame, "Vui lòng nhập đầy đủ thông tin đăng nhập.",
                    "Lỗi đăng nhập",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        TaiKhoan taiKhoan = dangNhapDao.dangNhap(tenDangNhap, matKhau);

        if(taiKhoan != null) {
            JOptionPane.showMessageDialog(dangNhapFrame,
                    "Đăng nhập thành công! Chào mừng " + taiKhoan.getTenNV(),
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            // Mở MainFrame và truyền thông tin tài khoản
            MainFrame mainFrame = new MainFrame();
            mainFrame.setTaiKhoanDangNhap(taiKhoan);
            mainFrame.setVisible(true);

            // Đóng frame đăng nhập
            dangNhapFrame.dispose(); // ← ĐÓNG hoàn toàn cửa sổ đăng nhập
        } else {
            JOptionPane.showMessageDialog(dangNhapFrame, "Tên đăng nhập hoặc mật khẩu không đúng.",
                    "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xuLyQuenMatKhau() {
    }
}

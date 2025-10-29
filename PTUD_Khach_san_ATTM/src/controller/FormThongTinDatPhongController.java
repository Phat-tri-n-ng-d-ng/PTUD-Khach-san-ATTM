/**
 * @ (#) FormThongTinDatPhongController.java       1.0     29/10/2025
 * <p>
 * Copuright (c) 2025 IUH, All rights reserved
 */
package controller;

import database.HoaDonDao;
import database.KhachHangDao;
import database.NguoiODao;
import database.PhongDao;
import entity.*;
import enums.PhuongThucThanhToan;
import enums.TrangThaiHoaDon;
import services.DangNhapServices;
import services.NhanVienService;
import views.FormThongTinDatPhong;
import views.FormThongTinThuePhong;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @auther: Pham Le Huu Thang
 * @date: 29/10/2025
 * @version: 1.0
 */
public class FormThongTinDatPhongController {
    private FormThongTinDatPhong formThongTinDatPhong;
    private ArrayList<Phong> danhSachPhong;
    private double tongTienThue = 0;
    private double tienCoc = 0;
    public DangNhapServices dangNhapServices;
    public NhanVienService nhanVienService;

    public FormThongTinDatPhongController(FormThongTinDatPhong formThongTinDatPhong) {
        this.formThongTinDatPhong = formThongTinDatPhong;
        this.dangNhapServices = DangNhapServices.getInstance();
        this.nhanVienService = new NhanVienService();

        initEvents();
    }

    private void initEvents() {
        formThongTinDatPhong.btn_XacNhan.addActionListener(e -> xuLyXacNhan());

        // Sự kiện cho nút hủy
        formThongTinDatPhong.btn_Huy.addActionListener(e -> formThongTinDatPhong.dispose());

        formThongTinDatPhong.ngayBatDau.addPropertyChangeListener(e -> tinhToanTongTien());

        formThongTinDatPhong.ngayKetThuc.addPropertyChangeListener(e -> tinhToanTongTien());

        // Sự kiện cho phương thức thanh toán
        ActionListener paymentListener = e -> capNhatPhuongThucThanhToan();
        // Lấy các radio button từ form (cần thêm chúng vào form)
        // Giả sử đã thêm các radio button vào form như biến instance
        if (formThongTinDatPhong.rdbtn_TienMat != null) {
            formThongTinDatPhong.rdbtn_TienMat.addActionListener(paymentListener);
        }
        if (formThongTinDatPhong.rdbtn_ChuyenKhoan != null) {
            formThongTinDatPhong.rdbtn_ChuyenKhoan.addActionListener(paymentListener);
        }

        formThongTinDatPhong.txt_TienKhachDua.addActionListener(e -> tinhTienThoiLai());
    }

    private void capNhatPhuongThucThanhToan() {
        String phuongThuc = "Tiền mặt";
        if (formThongTinDatPhong.rdbtn_ChuyenKhoan != null && formThongTinDatPhong.rdbtn_ChuyenKhoan.isSelected()) {
            phuongThuc = "Chuyển khoản";
        }
        formThongTinDatPhong.lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setText(phuongThuc);
    }

    private void tinhToanTongTien() {
        if (danhSachPhong == null || danhSachPhong.isEmpty()) return;

        // Lấy ngày bắt đầu và kết thúc
        Date ngayBatDau = formThongTinDatPhong.ngayBatDau.getDate();
        Date ngayKetThuc = formThongTinDatPhong.ngayKetThuc.getDate();

        if (ngayBatDau == null || ngayKetThuc == null) {
            return;
        }

        // Tính số ngày thuê
        long soNgay = tinhSoNgayThue(ngayBatDau, ngayKetThuc);
        if (soNgay <= 0) {
            JOptionPane.showMessageDialog(formThongTinDatPhong,
                    "Ngày kết thúc phải sau ngày bắt đầu!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tính tổng tiền thuê và tiền cọc
        double tongGiaPhong = tinhTongGiaPhong();
        tienCoc = tinhTienCoc();

        // Tổng tiền = (số ngày * tổng giá phòng) - tiền cọc
        tongTienThue = soNgay * tongGiaPhong;

        // Đảm bảo tổng tiền không âm
        if (tongTienThue < 0) {
            tongTienThue = 0;
        }

        // Cập nhật giao diện
        formThongTinDatPhong.lbl_TienCuaTongTienTrongPnlTongTien.setText(String.format("%,.0f VND", tongTienThue));
        formThongTinDatPhong.lbl_TienCuaTienCocTrongPnlTongTien.setText(String.format("%,.0f VND", tienCoc));

        // Cập nhật phương thức thanh toán
        capNhatPhuongThucThanhToan();

        // Tính lại tiền thối nếu đã có tiền khách đưa
        tinhTienThoiLai();
    }

    private void tinhTienThoiLai() {
        try {
            String tienKhachDuaStr = formThongTinDatPhong.txt_TienKhachDua.getText().trim();
            if (!tienKhachDuaStr.isEmpty()) {
                double tienKhachDua = Double.parseDouble(tienKhachDuaStr.replaceAll("[^\\d.]", ""));
                double tienThoiLai = tienKhachDua - tienCoc;

                if (tienThoiLai >= 0) {
                    formThongTinDatPhong.lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setText(String.format("%,.0f VND", tienKhachDua));
                    formThongTinDatPhong.lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setText(String.format("%,.0f VND", tienThoiLai));
                } else {
                    formThongTinDatPhong.lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setText("0 VND");
                    formThongTinDatPhong.lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setText("0 VND");
                    JOptionPane.showMessageDialog(formThongTinDatPhong,
                            "Tiền khách đưa không đủ!",
                            "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            formThongTinDatPhong.lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setText("0 VND");
            formThongTinDatPhong.lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setText("0 VND");
        }
    }

    private double tinhTienCoc() {
        double tienCoc = 0;
        for (Phong phong : danhSachPhong) {
            tienCoc += phong.getTienCoc();
        }
        return tienCoc;
    }

    private double tinhTongGiaPhong() {
        double tongGia = 0;
        for (Phong phong : danhSachPhong) {
            tongGia += phong.getGiaPhong();
        }
        return tongGia;
    }

    private void xuLyXacNhan() {
        try {
            // Kiểm tra dữ liệu đầu vào (giữ nguyên phần kiểm tra)
            if (danhSachPhong == null || danhSachPhong.isEmpty()) {
                JOptionPane.showMessageDialog(formThongTinDatPhong,
                        "Vui lòng chọn phòng!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date ngayBatDau = formThongTinDatPhong.ngayBatDau.getDate();
            Date ngayKetThuc = formThongTinDatPhong.ngayKetThuc.getDate();

            if (ngayBatDau == null || ngayKetThuc == null) {
                JOptionPane.showMessageDialog(formThongTinDatPhong,
                        "Vui lòng chọn ngày bắt đầu và kết thúc!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy thông tin khách hàng
            String sdtKhachHang = formThongTinDatPhong.txt_SDT.getText().trim();
            KhachHang khachHang = new KhachHangDao().TimKhachHang(sdtKhachHang, "SDT");

            if (khachHang == null) {
                JOptionPane.showMessageDialog(formThongTinDatPhong,
                        "Không tìm thấy thông tin khách hàng!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy nhân viên từ tài khoản đăng nhập
            NhanVien nhanVienDangNhap = dangNhapServices.getNhanVienDangNhap();
            System.out.println("Nhân viên đăng nhập: " + (nhanVienDangNhap != null ? nhanVienDangNhap.getMaNV() : "NULL"));

            if (nhanVienDangNhap == null) {
                JOptionPane.showMessageDialog(formThongTinDatPhong,
                        "Không tìm thấy thông tin nhân viên!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo hóa đơn mới
            String maHD = HoaDonDao.sinhMaHoaDonMoi();
            LocalDateTime ngayLap = LocalDateTime.now();

            LocalDateTime ngayNhanPhong = ngayBatDau.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
            LocalDateTime ngayTraPhong = ngayKetThuc.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();

            // Xác định phương thức thanh toán
            PhuongThucThanhToan pttt = PhuongThucThanhToan.TienMat;
            if (formThongTinDatPhong.rdbtn_ChuyenKhoan != null &&
                    formThongTinDatPhong.rdbtn_ChuyenKhoan.isSelected()) {
                pttt = PhuongThucThanhToan.ChuyenKhoan;
            }

            // Lấy tiền khách đưa
            double tienNhan = 0;
            try {
                String tienKhachDuaStr = formThongTinDatPhong.txt_TienKhachDua.getText().trim();
                if (!tienKhachDuaStr.isEmpty()) {
                    tienNhan = Double.parseDouble(tienKhachDuaStr.replaceAll("[^\\d.]", ""));
                }
            } catch (NumberFormatException ex) {
                tienNhan = 0;
            }

            // Tạo danh sách chi tiết hóa đơn - ĐẢM BẢO KHÔNG NULL
            ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
            long soNgayThue = tinhSoNgayThue(ngayBatDau, ngayKetThuc);

            // KIỂM TRA: danhSachPhong có null hoặc rỗng không
            if (danhSachPhong != null && !danhSachPhong.isEmpty()) {
                for (Phong phong : danhSachPhong) {
                    ChiTietHoaDon cthd = new ChiTietHoaDon(phong, (int) soNgayThue);
                    dsCTHD.add(cthd);
                }
            } else {
                JOptionPane.showMessageDialog(formThongTinDatPhong,
                        "Danh sách phòng không hợp lệ!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo hóa đơn và tính toán các giá trị
            //"INSERT INTO HoaDon (maHD, ngayLap, ngayNhanPhong, ngayTraPhong, pTTT, trangThai, tienNhan, maKH, maNV,
            // tongTien, tienThue, tienGiam, phiDoiPhong, tongTienThanhToan, tienTra) "
            HoaDon hoaDon = new HoaDon(
                    maHD, ngayLap, ngayNhanPhong, ngayTraPhong,
                    pttt, TrangThaiHoaDon.HoaDonThuePhong, tienNhan,
                    null, khachHang, dsCTHD, nhanVienDangNhap
            );

            // Tính toán các giá trị cho hóa đơn - CHỈ GỌI NẾU dsCTHD KHÔNG RỖNG
            if (dsCTHD != null && !dsCTHD.isEmpty()) {
                hoaDon.setTongTien();
                hoaDon.setTienThue();
                hoaDon.setTienGiam();
                hoaDon.setPhiDoiPhong();
                hoaDon.setTongTienThanhToan();
                hoaDon.setTienTra();
            }

            // Lưu hóa đơn vào database
            HoaDonDao hoaDonDao = new HoaDonDao();
            boolean success = hoaDonDao.themHoaDon(hoaDon);

            if (success) {
                // Cập nhật trạng thái phòng
                PhongDao phongDao = new PhongDao();
                for (Phong phong : danhSachPhong) {
                    phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), "DaDat");
                }


                JOptionPane.showMessageDialog(formThongTinDatPhong,
                        "Xác nhận thuê phòng thành công!\nMã hóa đơn: " + maHD,
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

                formThongTinDatPhong.dispose();
            } else {
                JOptionPane.showMessageDialog(formThongTinDatPhong,
                        "Lỗi khi lưu hóa đơn!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(formThongTinDatPhong,
                    "Lỗi khi xác nhận thuê phòng: " + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private long tinhSoNgayThue(Date ngayBatDau, Date ngayKetThuc) {
        long diffInMillies = ngayKetThuc.getTime() - ngayBatDau.getTime();
        long soNgay = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return soNgay;
    }

    public void hienThiThongTin(KhachHang khachHang, ArrayList<Phong> danhSachPhong){
        this.danhSachPhong = danhSachPhong;
        // Hiển thị thông tin khách hàng
        if (khachHang != null) {
            formThongTinDatPhong.txt_SDT.setText(khachHang.getSdt());
            formThongTinDatPhong.txt_HoTen.setText(khachHang.getTenKH());
            formThongTinDatPhong.txt_CCCD.setText(khachHang.getSoCCCD());

            if (khachHang.getNgaySinh() != null) {
                formThongTinDatPhong.txt_NgaySinh.setText(khachHang.getNgaySinh().toString());
            }

            formThongTinDatPhong.txt_GioiTinh.setText(khachHang.isGioiTinh() ? "Nam" : "Nữ");
            formThongTinDatPhong.txt_Email.setText(khachHang.getEmail());
            formThongTinDatPhong.txt_HangKhachHang.setText(khachHang.getHangKH().toString());
            formThongTinDatPhong.txt_DiemTichLuy.setText(String.valueOf(khachHang.getDiemTichLuy()));
        }

        // Hiển thị thông tin phòng
        formThongTinDatPhong.model.setRowCount(0); // Xóa dữ liệu cũ
        for (Phong phong : danhSachPhong) {
            Object[] row = {
                    phong.getMaPhong(),
                    phong.getLoaiPhong().getTenLoaiPhong(),
                    phong.getSoLuongToiDa(),
                    String.format("%,.0f VND", phong.getGiaPhong()),
                    String.format("%,.0f VND", phong.getTienCoc())
            };
            formThongTinDatPhong.model.addRow(row);
        }

        // Tính toán tổng tiền ban đầu
        tinhToanTongTien();
    }
}

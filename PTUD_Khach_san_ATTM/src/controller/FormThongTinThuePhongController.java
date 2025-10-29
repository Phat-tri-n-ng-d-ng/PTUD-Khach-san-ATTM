
package controller;

import database.*;
import entity.*;
import enums.PhuongThucThanhToan;
import enums.TrangThaiHoaDon;
import services.DangNhapServices;
import services.NhanVienService;
import views.FormThongTinThuePhong;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FormThongTinThuePhongController {
    private FormThongTinThuePhong formThongTinThuePhong;
    private ArrayList<Phong> danhSachPhong;
    private ArrayList<NguoiO> danhSachNguoiO;
    private double tongTienThue = 0;
    private double tienCoc = 0;
    public DangNhapServices dangNhapServices;
    public NhanVienService nhanVienService;

    public FormThongTinThuePhongController(FormThongTinThuePhong formThongTinThuePhong) {
        this.formThongTinThuePhong = formThongTinThuePhong;
        this.danhSachNguoiO = new ArrayList<>();
        this.dangNhapServices = DangNhapServices.getInstance(); // Dùng Singleton
        this.nhanVienService = new NhanVienService();

        initEvents();

    }

    private void initEvents() {
        // Sự kiện cho nút xác nhận
        formThongTinThuePhong.btn_XacNhan.addActionListener(e -> xuLyXacNhan());

        // Sự kiện cho nút hủy
        formThongTinThuePhong.btn_Huy.addActionListener(e -> formThongTinThuePhong.dispose());

        formThongTinThuePhong.ngayBatDau.addPropertyChangeListener(e -> tinhToanTongTien());

        formThongTinThuePhong.ngayKetThuc.addPropertyChangeListener(e -> tinhToanTongTien());

        // Sự kiện cho phương thức thanh toán
        ActionListener paymentListener = e -> capNhatPhuongThucThanhToan();
        // Lấy các radio button từ form (cần thêm chúng vào form)
        // Giả sử đã thêm các radio button vào form như biến instance
        if (formThongTinThuePhong.rdbtn_TienMat != null) {
            formThongTinThuePhong.rdbtn_TienMat.addActionListener(paymentListener);
        }
        if (formThongTinThuePhong.rdbtn_ChuyenKhoan != null) {
            formThongTinThuePhong.rdbtn_ChuyenKhoan.addActionListener(paymentListener);
        }


        formThongTinThuePhong.btn_ThemNguoiOVaoDanhSach.addActionListener(e -> themNguoiO());
        // Sự kiện cho trường tiền khách đưa (nhấn Enter)
        formThongTinThuePhong.txt_TienKhachDua.addActionListener(e -> tinhTienThoiLai());
    }

    // Kiểm tra tên hợp lệ (chỉ chứa chữ cái và khoảng trắng)
//    private boolean kiemTraTen(String ten) {
//
//    }

    private boolean kiemTraSoDienThoai(String sdt) {
        // Regex: Bắt đầu bằng 0, theo sau là 9 chữ số (tổng 10 số)
        String regex = "^0[0-9]{9}$";
        return sdt.matches(regex);
    }

    private boolean kiemTraCCCD(String cccd) {
        // Regex: Chính xác 12 chữ số
        String regex = "^[0-9]{12}$";
        return cccd.matches(regex);
    }

    private void themNguoiO() {
        try {
            String hoTen = formThongTinThuePhong.txt_HoTenNguoiO.getText().trim();
            String sdt = formThongTinThuePhong.txt_SDTNguoiO.getText().trim();
            String cccd = formThongTinThuePhong.txt_CCCDNguoiO.getText().trim();
            Date ngaySinh = formThongTinThuePhong.ngaySinhNguoiO.getDate();
            boolean gioiTinh = formThongTinThuePhong.rdbtn_NamNguoiO.isSelected(); // true: Nam, false: Nữ

            // Kiểm tra dữ liệu đầu vào
            if(hoTen.isEmpty() || sdt.isEmpty() || cccd.isEmpty() || ngaySinh == null){
                JOptionPane.showMessageDialog(formThongTinThuePhong,
                        "Vui lòng điền đầy đủ thông tin người ở!",
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Chuyển đổi Date sang LocalDate
            LocalDate localNgaySinh = ngaySinh.toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();

            NguoiO nguoiO = new NguoiO(hoTen, localNgaySinh, sdt, cccd, gioiTinh);

            // THÊM: Khởi tạo danh sách nếu chưa có
            if (danhSachNguoiO == null) {
                danhSachNguoiO = new ArrayList<>();
            }

            // THÊM: Thêm vào danh sách
            danhSachNguoiO.add(nguoiO);

            // THÊM: Thêm vào bảng hiển thị
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String ngaySinhStr = sdf.format(ngaySinh);
            String gioiTinhStr = gioiTinh ? "Nam" : "Nữ";

            formThongTinThuePhong.model_DanhSachNguoiO.addRow(new Object[]{
                    hoTen,
                    ngaySinhStr,
                    gioiTinhStr,
                    sdt,
                    cccd
            });

        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(formThongTinThuePhong,
                    "Lỗi khi thêm người ở: " + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<NguoiO> getDanhSachNguoiO() {
        if (danhSachNguoiO == null) {
            danhSachNguoiO = new ArrayList<>();
        }
        return danhSachNguoiO;
    }

    private void xuLyXacNhan() {
        try {
            // Kiểm tra dữ liệu đầu vào (giữ nguyên phần kiểm tra)
            if (danhSachPhong == null || danhSachPhong.isEmpty()) {
                JOptionPane.showMessageDialog(formThongTinThuePhong,
                        "Vui lòng chọn phòng!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date ngayBatDau = formThongTinThuePhong.ngayBatDau.getDate();
            Date ngayKetThuc = formThongTinThuePhong.ngayKetThuc.getDate();

            if (ngayBatDau == null || ngayKetThuc == null) {
                JOptionPane.showMessageDialog(formThongTinThuePhong,
                        "Vui lòng chọn ngày bắt đầu và kết thúc!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy thông tin khách hàng
            String sdtKhachHang = formThongTinThuePhong.txt_SDT.getText().trim();
            KhachHang khachHang = new KhachHangDao().TimKhachHang(sdtKhachHang, "SDT");

            if (khachHang == null) {
                JOptionPane.showMessageDialog(formThongTinThuePhong,
                        "Không tìm thấy thông tin khách hàng!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy nhân viên từ tài khoản đăng nhập
            NhanVien nhanVienDangNhap = dangNhapServices.getNhanVienDangNhap();
            System.out.println("Nhân viên đăng nhập: " + (nhanVienDangNhap != null ? nhanVienDangNhap.getMaNV() : "NULL"));

            if (nhanVienDangNhap == null) {
                JOptionPane.showMessageDialog(formThongTinThuePhong,
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
            if (formThongTinThuePhong.rdbtn_ChuyenKhoan != null &&
                    formThongTinThuePhong.rdbtn_ChuyenKhoan.isSelected()) {
                pttt = PhuongThucThanhToan.ChuyenKhoan;
            }

            // Lấy tiền khách đưa
            double tienNhan = 0;
            try {
                String tienKhachDuaStr = formThongTinThuePhong.txt_TienKhachDua.getText().trim();
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
                JOptionPane.showMessageDialog(formThongTinThuePhong,
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
                // Lưu danh sách người ở
                if (danhSachNguoiO != null && !danhSachNguoiO.isEmpty()) {
                    NguoiODao nguoiODao = new NguoiODao();
                    // Giả sử người ở ở cùng phòng đầu tiên trong danh sách
                    String maPhongDauTien = danhSachPhong.get(0).getMaPhong();
                    for (NguoiO nguoiO : danhSachNguoiO) {
                        nguoiODao.themNguoiO(nguoiO, maHD, maPhongDauTien);
                    }
                }

                PhongDao phongDao = new PhongDao();
                for (Phong phong : danhSachPhong) {
                    phongDao.capNhatTrangThaiPhong(phong.getMaPhong(), "DangSuDung");
                }


                JOptionPane.showMessageDialog(formThongTinThuePhong,
                        "Xác nhận thuê phòng thành công!\nMã hóa đơn: " + maHD,
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

                formThongTinThuePhong.dispose();
            } else {
                JOptionPane.showMessageDialog(formThongTinThuePhong,
                        "Lỗi khi lưu hóa đơn!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(formThongTinThuePhong,
                    "Lỗi khi xác nhận thuê phòng: " + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void hienThiThongTin(KhachHang khachHang, ArrayList<Phong> danhSachPhong){
        this.danhSachPhong = danhSachPhong;
        // Hiển thị thông tin khách hàng
        if (khachHang != null) {
            formThongTinThuePhong.txt_SDT.setText(khachHang.getSdt());
            formThongTinThuePhong.txt_HoTen.setText(khachHang.getTenKH());
            formThongTinThuePhong.txt_CCCD.setText(khachHang.getSoCCCD());

            if (khachHang.getNgaySinh() != null) {
                formThongTinThuePhong.txt_NgaySinh.setText(khachHang.getNgaySinh().toString());
            }

            formThongTinThuePhong.txt_GioiTinh.setText(khachHang.isGioiTinh() ? "Nam" : "Nữ");
            formThongTinThuePhong.txt_Email.setText(khachHang.getEmail());
            formThongTinThuePhong.txt_HangKhachHang.setText(khachHang.getHangKH().toString());
            formThongTinThuePhong.txt_DiemTichLuy.setText(String.valueOf(khachHang.getDiemTichLuy()));
        }

        // Hiển thị thông tin phòng
        formThongTinThuePhong.model_DanhSachPhong.setRowCount(0); // Xóa dữ liệu cũ
        for (Phong phong : danhSachPhong) {
            Object[] row = {
                    phong.getMaPhong(),
                    phong.getLoaiPhong().getTenLoaiPhong(),
                    phong.getSoLuongToiDa(),
                    String.format("%,.0f VND", phong.getGiaPhong()),
                    String.format("%,.0f VND", phong.getTienCoc())
            };
            formThongTinThuePhong.model_DanhSachPhong.addRow(row);
        }

        // Tính toán tổng tiền ban đầu
        tinhToanTongTien();
    }

    private void tinhToanTongTien() {
        if (danhSachPhong == null || danhSachPhong.isEmpty()) return;

        // Lấy ngày bắt đầu và kết thúc
        Date ngayBatDau = formThongTinThuePhong.ngayBatDau.getDate();
        Date ngayKetThuc = formThongTinThuePhong.ngayKetThuc.getDate();

        if (ngayBatDau == null || ngayKetThuc == null) {
            return;
        }

        // Tính số ngày thuê
        long soNgay = tinhSoNgayThue(ngayBatDau, ngayKetThuc);
        if (soNgay <= 0) {
            JOptionPane.showMessageDialog(formThongTinThuePhong,
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
        formThongTinThuePhong.lbl_TienCuaTongTienTrongPnlTongTien.setText(String.format("%,.0f VND", tongTienThue));
        formThongTinThuePhong.lbl_TienCuaTienCocTrongPnlTongTien.setText(String.format("%,.0f VND", tienCoc));

        // Cập nhật phương thức thanh toán
        capNhatPhuongThucThanhToan();

        // Tính lại tiền thối nếu đã có tiền khách đưa
        tinhTienThoiLai();
    }

    private long tinhSoNgayThue(Date ngayBatDau, Date ngayKetThuc) {
        long diffInMillies = ngayKetThuc.getTime() - ngayBatDau.getTime();
        long soNgay = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return soNgay;
    }

    private void capNhatPhuongThucThanhToan() {
        String phuongThuc = "Tiền mặt";
        if (formThongTinThuePhong.rdbtn_ChuyenKhoan != null && formThongTinThuePhong.rdbtn_ChuyenKhoan.isSelected()) {
            phuongThuc = "Chuyển khoản";
        }
        formThongTinThuePhong.lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setText(phuongThuc);
    }

    private double tinhTongGiaPhong() {
        double tongGia = 0;
        for (Phong phong : danhSachPhong) {
            tongGia += phong.getGiaPhong();
        }
        return tongGia;
    }

    private double tinhTienCoc() {
        double tienCoc = 0;
        for (Phong phong : danhSachPhong) {
            tienCoc += phong.getTienCoc();
        }
        return tienCoc;
    }

    private void tinhTienThoiLai() {
        try {
            String tienKhachDuaStr = formThongTinThuePhong.txt_TienKhachDua.getText().trim();
            if (!tienKhachDuaStr.isEmpty()) {
                double tienKhachDua = Double.parseDouble(tienKhachDuaStr.replaceAll("[^\\d.]", ""));
                double tienThoiLai = tienKhachDua - tienCoc;

                if (tienThoiLai >= 0) {
                    formThongTinThuePhong.lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setText(String.format("%,.0f VND", tienKhachDua));
                    formThongTinThuePhong.lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setText(String.format("%,.0f VND", tienThoiLai));
                } else {
                    formThongTinThuePhong.lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setText("0 VND");
                    formThongTinThuePhong.lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setText("0 VND");
                    JOptionPane.showMessageDialog(formThongTinThuePhong,
                            "Tiền khách đưa không đủ!",
                            "Cảnh báo",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            formThongTinThuePhong.lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setText("0 VND");
            formThongTinThuePhong.lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setText("0 VND");
        }
    }

}

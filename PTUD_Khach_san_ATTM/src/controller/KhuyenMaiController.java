/**
 * @ (#) KhuyenMaiController.java       1.0     23/10/2025
 * <p>
 * Copuright (c) 2025 IUH, All rights reserved
 */
package controller;

import com.toedter.calendar.JDateChooser;
import database.KhuyenMaiDao;
import entity.KhuyenMai;
import enums.TrangThaiKhuyenMai;
import views.KhuyenMaiPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * @description:
 * @auther: Pham Le Huu Thang
 * @date: 23/10/2025
 * @version: 1.0
 */
public class KhuyenMaiController implements MouseListener {
    private KhuyenMaiDao khuyenMaiDao;
    private KhuyenMaiPanel khuyenMaiPanel;

    public KhuyenMaiController(KhuyenMaiPanel khuyenMaiPanel){
        khuyenMaiDao = new KhuyenMaiDao();
        this.khuyenMaiPanel = khuyenMaiPanel;

        // Đăng ký sự kiện cho các nút và bảng
        khuyenMaiPanel.btn_Them.addActionListener(e -> themKhuyenMai());
        khuyenMaiPanel.btn_CapNhat.addActionListener(e -> capNhatKhuyenMai());
        khuyenMaiPanel.btn_TimMa.addActionListener(e -> timKiemKhuyenMaiTheoMa());
        khuyenMaiPanel.table.addMouseListener(this);

        // THÊM SỰ KIỆN TỰ ĐỘNG CHO BỘ LỌC - HIỆN KẾT QUẢ NGAY KHI CHỌN
        themSuKienTuDongTimKiem();

        // Tự động cập nhật trạng thái và tải dữ liệu ban đầu
        tuDongCapNhatTrangThai();

        // Load tất cả khuyến mãi khi khởi tạo controller
        taiTatCaKhuyenMai();
    }

    // Thêm sự kiện tự động tìm kiếm khi thay đổi bộ lọc
    private void themSuKienTuDongTimKiem() {
        // Sự kiện cho DateChooser trong bộ lọc
        khuyenMaiPanel.ngayBD_1.addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                thucHienTimKiemTuDong();
            }
        });

        khuyenMaiPanel.ngayKT_1.addPropertyChangeListener(e -> {
            if ("date".equals(e.getPropertyName())) {
                thucHienTimKiemTuDong();
            }
        });

        // Sự kiện cho checkbox trong bộ lọc
        ActionListener suKienCheckbox = e -> thucHienTimKiemTuDong();

        khuyenMaiPanel.chckbx_Standard_1.addActionListener(suKienCheckbox);
        khuyenMaiPanel.chckbx_Superior_1.addActionListener(suKienCheckbox);
        khuyenMaiPanel.chckbx_Family_1.addActionListener(suKienCheckbox);
        khuyenMaiPanel.chckbx_Deluxe_1.addActionListener(suKienCheckbox);
        khuyenMaiPanel.chckbx_Suite_1.addActionListener(suKienCheckbox);
        khuyenMaiPanel.chckbx_TatCa_1.addActionListener(suKienCheckbox);

        // Sự kiện cho ô tìm mã - khi xóa nội dung thì tự động lọc
        khuyenMaiPanel.txt_TimMaKhuyenMai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Nếu ô mã trống, thực hiện lọc tự động
                if (khuyenMaiPanel.txt_TimMaKhuyenMai.getText().trim().isEmpty()) {
                    thucHienTimKiemTuDong();
                }
            }
        });
    }

    // Thực hiện tìm kiếm tự động khi bộ lọc thay đổi
    private void thucHienTimKiemTuDong() {
        // Chỉ thực hiện nếu ô tìm mã trống
        if (khuyenMaiPanel.txt_TimMaKhuyenMai.getText().trim().isEmpty()) {
            timKiemKhuyenMaiTuDong();
        }
    }

    // Tự động tìm kiếm khuyến mãi theo bộ lọc
    private void timKiemKhuyenMaiTuDong() {
        try {
            // Lấy điều kiện từ các checkbox trong panel bộ lọc
            String dieuKien = layDieuKienApDungTuCheckbox();
            LocalDateTime tuNgay = layNgayTuDateChooser(khuyenMaiPanel.ngayBD_1);
            LocalDateTime denNgay = layNgayTuDateChooser(khuyenMaiPanel.ngayKT_1);

            ArrayList<KhuyenMai> ketQua;

            // Nếu có ít nhất một điều kiện lọc được chọn
            if (!dieuKien.isEmpty() || (tuNgay != null && denNgay != null)) {
                // Bắt đầu với tất cả khuyến mãi
                ketQua = khuyenMaiDao.getTatCaKhuyenMai();

                // Lọc theo điều kiện áp dụng nếu có
                if (!dieuKien.isEmpty()) {
                    ArrayList<KhuyenMai> ketQuaDieuKien = khuyenMaiDao.locKhuyenMaiTheoDieuKien(dieuKien);
                    ketQua.retainAll(ketQuaDieuKien);
                }

                // Lọc theo khoảng thời gian nếu có
                if (tuNgay != null && denNgay != null) {
                    ArrayList<KhuyenMai> ketQuaThoiGian = khuyenMaiDao.locKhuyenMaiTheoKhoangThoiGian(tuNgay, denNgay);
                    ketQua.retainAll(ketQuaThoiGian);
                }
            } else {
                // Nếu không có điều kiện lọc nào, hiển thị tất cả
                ketQua = khuyenMaiDao.getTatCaKhuyenMai();
            }

            // Hiển thị kết quả
            DefaultTableModel model = khuyenMaiPanel.model;
            model.setRowCount(0);

            for (KhuyenMai km : ketQua) {
                model.addRow(new Object[]{
                        km.getMaKM(),
                        km.getTenKM(),
                        (int)(km.getTyLeGiam() * 100) + "%",
                        km.getDieuKienApDung(),
                        km.getNgayBatDau().toLocalDate().toString(),
                        km.getNgayketThuc().toLocalDate().toString(),
                        getTrangThaiHienThi(km.getTrangThai())
                });
            }

            // KHÔNG HIỂN THỊ THÔNG BÁO KHI KHÔNG TÌM THẤY (vì đây là tự động)

        } catch (Exception e) {
            e.printStackTrace();
            // Không hiển thị thông báo lỗi cho người dùng trong chế độ tự động
        }
    }

    // Tự động cập nhật trạng thái khuyến mãi theo ngày
    public void tuDongCapNhatTrangThai() {
        try {
            khuyenMaiDao.tuDongCapNhatTrangThaiKhuyenMai();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(khuyenMaiPanel, "Lỗi khi cập nhật trạng thái khuyến mãi");
        }
    }

    private void themKhuyenMai() {
        try {
            // Lấy dữ liệu từ giao diện người dùng
            String tenKM = khuyenMaiPanel.txt_TenKhachHang.getText().trim();
            String tyLeGiamText = khuyenMaiPanel.txt_TyLeGiam.getText().trim();

            String dieuKienApDung = layDieuKienApDungTuCheckbox();

            // Validate(Xác thực) dữ liệu
            if(tenKM.isEmpty() || tyLeGiamText.isEmpty()){
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            // kiểm tra tỷ lệ giảm phải từ 0 đến 1
            // Chuyển đổi từ phần trăm sang số thập phân
            double tyLeGiam;
            try {
                tyLeGiam = Double.parseDouble(tyLeGiamText) / 100.0;
                if(tyLeGiam < 0 || tyLeGiam > 1){
                    JOptionPane.showMessageDialog(khuyenMaiPanel, "Tỷ lệ giảm phải từ 0% đến 100%");
                    return;
                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Tỷ lệ giảm phải là một con số hợp");
                return;
            }

            // lấy ngày bắt đầu và kết thúc từ giao diện (JDateChooser)
            LocalDateTime ngayBatDau = layNgayTuDateChooser(khuyenMaiPanel.ngayBD);
            LocalDateTime ngayKetThuc = layNgayTuDateChooser(khuyenMaiPanel.ngayKT);

            // Kiểm tra ngày bắt đầu và kết thúc không được để trống
            if (ngayBatDau == null || ngayKetThuc == null) {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Vui lòng chọn ngày bắt đầu và ngày kết thúc");
                return;
            }

            // Kiểm tra ngày kết thúc phải sau ngày bắt đầu
            if (ngayKetThuc.isBefore(ngayBatDau)) {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Ngày kết thúc phải sau ngày bắt đầu");
                return;
            }

            // Tạo mã khuyến mãi tự động
            String maKM = taoMaKhuyenMaiTuDong(ngayBatDau);

            //
            TrangThaiKhuyenMai trangThai = xacDinhTrangThaiTuDong(ngayBatDau, ngayKetThuc);

            // Tạo đối tượng KhuyenMai
            KhuyenMai khuyenMai = new KhuyenMai(
                    maKM, tenKM, dieuKienApDung, tyLeGiam,
                    ngayBatDau, ngayKetThuc, trangThai
            );

            // Thêm khuyến mãi vào cơ sở dữ liệu
            if(khuyenMaiDao.themKhuyenMai(khuyenMai)){
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Thêm khuyến mãi thành công");
                lamMoiForm();
                taiTatCaKhuyenMai(); // Tải lại danh sách khuyến mãi
            }else {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Thêm khuyến mãi thất bại");
            }

        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(khuyenMaiPanel, "Vui lòng nhập đầy đủ thông tin");
        }
    }

    private TrangThaiKhuyenMai xacDinhTrangThaiTuDong(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) {
        LocalDateTime ngayHienTai = LocalDateTime.now();

        if (ngayHienTai.isBefore(ngayBatDau)) {
            return TrangThaiKhuyenMai.SapDienRa;
        } else if (!ngayHienTai.isAfter(ngayKetThuc)) {
            return TrangThaiKhuyenMai.DangHoatDong;
        } else {
            return TrangThaiKhuyenMai.HetHan;
        }
    }

    // Cập nhật khuyến mãi
    private void capNhatKhuyenMai() {
        int dongDuocChon = khuyenMaiPanel.table.getSelectedRow();
        if(dongDuocChon == -1){
            JOptionPane.showMessageDialog(khuyenMaiPanel, "Vui lòng chọn khuyến mãi cần cập nhật");
            return;
        }

        try{
            String maKM = khuyenMaiPanel.table.getValueAt(dongDuocChon, 0).toString();
            String tenKM = khuyenMaiPanel.txt_TenKhachHang.getText().trim();
            String tyLeGiamText = khuyenMaiPanel.txt_TyLeGiam.getText().trim();
            String dieuKienApDung = layDieuKienApDungTuCheckbox();

            // Validate(Xác thực) dữ liệu
            if(tenKM.isEmpty() || tyLeGiamText.isEmpty()){
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Vui lòng nhập đầy đủ thông tin");
                return;
            }

            // lấy ngày bắt đầu và kết thúc từ giao diện (JDateChooser)
            LocalDateTime ngayBatDau = layNgayTuDateChooser(khuyenMaiPanel.ngayBD);
            LocalDateTime ngayKetThuc = layNgayTuDateChooser(khuyenMaiPanel.ngayKT);

            if (ngayBatDau == null || ngayKetThuc == null) {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Vui lòng chọn ngày bắt đầu và ngày kết thúc");
                return;
            }

            if (ngayKetThuc.isBefore(ngayBatDau)) {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Ngày kết thúc phải sau ngày bắt đầu");
                return;
            }

            // Trang thái
            TrangThaiKhuyenMai trangThai = layTrangThaiTuComboBox();

            // Tạo đối tượng KhuyenMai cần cập nhật
            KhuyenMai khuyenMai = new KhuyenMai(
                    maKM, tenKM, dieuKienApDung,
                    Double.parseDouble(tyLeGiamText) / 100.0,
                    ngayBatDau, ngayKetThuc, trangThai
            );

            // Cập nhật khuyến mãi vào cơ sở dữ liệu
            if(khuyenMaiDao.capNhatKhuyenMai(khuyenMai)){
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Cập nhật khuyến mãi thành công");
                lamMoiForm();
                taiTatCaKhuyenMai(); // Tải lại danh sách khuyến mãi
            }else {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Cập nhật khuyến mãi thất bại");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(khuyenMaiPanel, "Tỷ lệ giảm phải là một con số hợp lệ");
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(khuyenMaiPanel, "Lỗi khi cập nhật khuyến mãi");
        }
    }

    // Tìm kiếm khuyến mãi
    private void timKiemKhuyenMaiTheoMa() {
        String maKM = khuyenMaiPanel.txt_TimMaKhuyenMai.getText().trim();
        if (maKM.isEmpty()) {
            taiTatCaKhuyenMai();
            return;
        }

        try {
            ArrayList<KhuyenMai> ketQua = khuyenMaiDao.getKhuyenMaiTheoMa(maKM);
            DefaultTableModel model = khuyenMaiPanel.model;
            model.setRowCount(0);

            for (KhuyenMai km : ketQua) {
                model.addRow(new Object[]{
                        km.getMaKM(),
                        km.getTenKM(),
                        km.getTyLeGiam() * 100 + "%",
                        km.getDieuKienApDung(),
                        km.getNgayBatDau().toLocalDate().toString(),
                        km.getNgayketThuc().toLocalDate().toString(),
                        getTrangThaiHienThi(km.getTrangThai())
                });
            }

            if (ketQua.isEmpty()) {
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Không tìm thấy khuyến mãi nào");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(khuyenMaiPanel, "Lỗi khi tìm kiếm khuyến mãi");
        }
    }


    // các phương thức hỗ trợ
    // Tải tất cả khuyến mãi và hiển thị lên bảng
    private void taiTatCaKhuyenMai() {
        try {
            ArrayList<KhuyenMai> danhSachKhuyenMai = khuyenMaiDao.getTatCaKhuyenMai();
            DefaultTableModel model = khuyenMaiPanel.model;
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng trước khi load dữ liệu mới lên

            for (KhuyenMai km : danhSachKhuyenMai) {
                model.addRow(new Object[]{
                        km.getMaKM(),
                        km.getTenKM(),
                        (int)(km.getTyLeGiam() * 100) + "%", // Hiển thị phần trăm
                        km.getDieuKienApDung(),
                        km.getNgayBatDau().toLocalDate().toString(),
                        km.getNgayketThuc().toLocalDate().toString(),
                        getTrangThaiHienThi(km.getTrangThai())
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(khuyenMaiPanel, "Lỗi khi tải danh sách khuyến mãi");
        }
    }

    // Tạo mã khuyến mãi tự động
    private String taoMaKhuyenMaiTuDong(LocalDateTime ngayBatDau) {
        try {
            // Lấy năm từ ngày bắt đầu
            int namApDung = ngayBatDau.getYear();
            String namStr = String.valueOf(namApDung).substring(2); // Lấy 2 số cuối của năm

            // Lấy số thứ tự lớn nhất trong năm
            int soThuTuLonNhat = khuyenMaiDao.getSoLuongKhuyenMaiTheoNam(namApDung);
            int soThuTuMoi = soThuTuLonNhat + 1;

            // Kiểm tra nếu số thứ tự vượt quá 999
            if (soThuTuMoi > 999) {
                throw new RuntimeException("Đã vượt quá số lượng khuyến mãi cho phép trong năm " + namApDung);
            }

            // Tạo mã mới
            String maKMMoi = "KM" + namStr + String.format("%03d", soThuTuMoi);

            // KIỂM TRA MÃ ĐÃ TỒN TẠI CHƯA - THÊM ĐOẠN NÀY
            int dem = 0;
            while (khuyenMaiDao.kiemTraMaKMTonTai(maKMMoi) && dem < 100) {
                soThuTuMoi++;
                maKMMoi = "KM" + namStr + String.format("%03d", soThuTuMoi);
                dem++;
            }

            if (dem >= 100) {
                throw new RuntimeException("Không thể tạo mã khuyến mãi mới cho năm " + namApDung);
            }

            return maKMMoi;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tạo mã khuyến mãi tự động: " + e.getMessage());
        }
    }

    // Làm mới form nhập liệu
    private void lamMoiForm() {
        // Xóa dữ liệu trong các trường nhập liệu
        khuyenMaiPanel.txt_TenKhachHang.setText("");
        khuyenMaiPanel.txt_TyLeGiam.setText("");

        // Reset JDateChooser
        khuyenMaiPanel.ngayBD.setDate(null);
        khuyenMaiPanel.ngayKT.setDate(null);

        // Reset các checkbox
        khuyenMaiPanel.chckbx_Standard.setSelected(false);
        khuyenMaiPanel.chckbx_Superior.setSelected(false);
        khuyenMaiPanel.chckbx_Family.setSelected(false);
        khuyenMaiPanel.chckbx_Deluxe.setSelected(false);
        khuyenMaiPanel.chckbx_Suite.setSelected(false);
        khuyenMaiPanel.chckbx_TatCa.setSelected(false);

        // Reset combobox trạng thái
        khuyenMaiPanel.comboBox_TrangThai.setSelectedIndex(0);

        // Đặt con trỏ về trường tên khách hàng
        khuyenMaiPanel.txt_TenKhachHang.requestFocus();
    }

    // Lấy trạng thái hiển thị từ enum
    private String getTrangThaiHienThi(TrangThaiKhuyenMai trangThai) {
        switch (trangThai) {
            case DangHoatDong -> { return "Đang hoạt động"; }
            case SapDienRa -> { return "Sắp diễn ra"; }
            case HetHan -> { return "Hết hạn"; }
            case TamNgung -> { return "Tạm ngừng"; }
            default -> { return ""; }
        }
    }

    // Lấy ngày từ JDateChooser và chuyển đổi sang LocalDateTime
    private LocalDateTime layNgayTuDateChooser(JDateChooser ngayChon) {
        if (ngayChon!= null && ngayChon.getDate() != null) {
            Date ngay = ngayChon.getDate();
            return ngay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }

    // Lấy trạng thái từ combobox
    private TrangThaiKhuyenMai layTrangThaiTuComboBox() {
        String trangThaiStr = (String) khuyenMaiPanel.comboBox_TrangThai.getSelectedItem();
        switch (trangThaiStr) {
            case "Đang hoạt động": return TrangThaiKhuyenMai.DangHoatDong;
            case "Sắp diễn ra": return TrangThaiKhuyenMai.SapDienRa;
            case "Hết hạn": return TrangThaiKhuyenMai.HetHan;
            case "Tạm ngừng": return TrangThaiKhuyenMai.TamNgung;
            default: return TrangThaiKhuyenMai.DangHoatDong;
        }
    }

    // Lấy điều kiện áp dụng từ các checkbox
    private String layDieuKienApDungTuCheckbox() {
        StringBuilder dieuKien = new StringBuilder();

        if (khuyenMaiPanel.chckbx_Standard.isSelected()) {
            dieuKien.append("Standard, ");
        }
        if (khuyenMaiPanel.chckbx_Superior.isSelected()) {
            dieuKien.append("Superior, ");
        }
        if (khuyenMaiPanel.chckbx_Family.isSelected()) {
            dieuKien.append("Family Room, ");
        }
        if (khuyenMaiPanel.chckbx_Deluxe.isSelected()) {
            dieuKien.append("Deluxe, ");
        }
        if (khuyenMaiPanel.chckbx_Suite.isSelected()) {
            dieuKien.append("Suite, ");
        }
        if (khuyenMaiPanel.chckbx_TatCa.isSelected()) {
            return "Tất cả";
        }

        // Xóa dấu phẩy cuối cùng
        if (dieuKien.length() > 0) {
            dieuKien.setLength(dieuKien.length() - 2);
        }

        return dieuKien.toString();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int dongDuocChon = khuyenMaiPanel.table.getSelectedRow();
        if (dongDuocChon >= 0) {
            try {
                String maKM = khuyenMaiPanel.table.getValueAt(dongDuocChon, 0).toString();

                KhuyenMai khuyenMai = khuyenMaiDao.layKhuyenMaiTheoMa(maKM);

                if (khuyenMai != null) {
                    // Hiển thị thông tin lên form
                    khuyenMaiPanel.txt_TenKhachHang.setText(khuyenMai.getTenKM());

                    String tyLeGiam = String.valueOf(khuyenMai.getTyLeGiam() * 100);
                    khuyenMaiPanel.txt_TyLeGiam.setText(tyLeGiam);

                    // Cập nhật các checkbox theo điều kiện áp dụng
                    capNhatCheckboxTheoDieuKien(khuyenMai.getDieuKienApDung());

                    // Cập nhật trạng thái combobox
                    capNhatComboBoxTrangThai(khuyenMai.getTrangThai());

                    // Cập nhật ngày tháng
                    capNhatDateChooser(khuyenMaiPanel.ngayBD, khuyenMai.getNgayBatDau());
                    capNhatDateChooser(khuyenMaiPanel.ngayKT, khuyenMai.getNgayketThuc());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(khuyenMaiPanel, "Lỗi khi tải thông tin khuyến mãi");
            }
        }
    }

    private void capNhatDateChooser(JDateChooser dateChooser, LocalDateTime localDateTime) {
        // Chuyển đổi LocalDateTime sang Date và đặt vào JDateChooser
        if (localDateTime != null) {
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            dateChooser.setDate(date);
        }
    }

    private void capNhatComboBoxTrangThai(TrangThaiKhuyenMai trangThai) {
        String trangThaiHienThi = getTrangThaiHienThi(trangThai);
        khuyenMaiPanel.comboBox_TrangThai.setSelectedItem(trangThaiHienThi);
    }

    private void capNhatCheckboxTheoDieuKien(String dieuKienApDung) {
        // Reset tất cả checkbox
        khuyenMaiPanel.chckbx_Standard.setSelected(false);
        khuyenMaiPanel.chckbx_Superior.setSelected(false);
        khuyenMaiPanel.chckbx_Family.setSelected(false);
        khuyenMaiPanel.chckbx_Deluxe.setSelected(false);
        khuyenMaiPanel.chckbx_Suite.setSelected(false);
        khuyenMaiPanel.chckbx_TatCa.setSelected(false);

        if (dieuKienApDung.contains("Standard")) {
            khuyenMaiPanel.chckbx_Standard.setSelected(true);
        }
        if (dieuKienApDung.contains("Superior")) {
            khuyenMaiPanel.chckbx_Superior.setSelected(true);
        }
        if (dieuKienApDung.contains("Family Room")) {
            khuyenMaiPanel.chckbx_Family.setSelected(true);
        }
        if (dieuKienApDung.contains("Deluxe")) {
            khuyenMaiPanel.chckbx_Deluxe.setSelected(true);
        }
        if (dieuKienApDung.contains("Suite")) {
            khuyenMaiPanel.chckbx_Suite.setSelected(true);
        }
        if (dieuKienApDung.contains("Tất cả")) {
            khuyenMaiPanel.chckbx_TatCa.setSelected(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

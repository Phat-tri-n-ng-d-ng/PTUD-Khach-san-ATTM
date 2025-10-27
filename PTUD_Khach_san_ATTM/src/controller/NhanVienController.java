package controller;

import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;
import enums.ChucVuNhanVien;
import enums.TrangThaiTaiKhoan;
import enums.VaiTro;
import services.NhanVienService;
import services.TaiKhoanService;
import views.NhanVienPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class NhanVienController implements MouseListener {
    private TaiKhoanService taiKhoanService;
    private NhanVienService nhanVienService;
    private NhanVienPanel nhanVienPanel;

    public NhanVienController(NhanVienPanel nhanVienPanel){
        nhanVienService = new NhanVienService();
        this.nhanVienPanel = nhanVienPanel;
        taiKhoanService = new TaiKhoanService();

        nhanVienPanel.btn_ThemNhanVien.addActionListener(e -> ThemNhanVien());
        nhanVienPanel.btn_CapNhat.addActionListener(e -> CapNhatNhanVien());
        nhanVienPanel.cbb_LocChucVu.addActionListener(e -> LocNhanVienTheoChucVu());
        nhanVienPanel.btn_Tim.addActionListener(e -> TimNhanVien());
        nhanVienPanel.btn_LamMoi.addActionListener(e -> LamMoi());
        nhanVienPanel.btn_CapTaiKhoan.addActionListener(e -> CapTaiKhoanNhanVien());
        nhanVienPanel.btn_VoHieuHoaTaiKhoan.addActionListener(e -> VoHieuHoaTaiKhoan());
        nhanVienPanel.rdbtn_TimSoDienThoai.addActionListener(e -> {
            nhanVienPanel.txt_TimSoDienThoai.setEditable(true);
            nhanVienPanel.txt_TimMaNhanVien.setEditable(false);
        });
        nhanVienPanel.rdbtn_TimMaNhanVien.addActionListener(e -> {
            nhanVienPanel.txt_TimSoDienThoai.setEditable(false);
            nhanVienPanel.txt_TimMaNhanVien.setEditable(true);
        });
        nhanVienPanel.table.addMouseListener(this);
    }

    private void TimNhanVien() {
        try {
            NhanVien nv;
            if (nhanVienPanel.rdbtn_TimMaNhanVien.isSelected()) {
                String maNV = nhanVienPanel.txt_TimMaNhanVien.getText().strip();
                nv = nhanVienService.TimNhanVien(maNV, "MA");
            } else {
                String soDT = nhanVienPanel.txt_TimSoDienThoai.getText().strip();
                nv = nhanVienService.TimNhanVien(soDT, "SDT");
            }

            if (nv != null) {
                DefaultTableModel model = nhanVienPanel.model;
                model.setRowCount(0);
                String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";
                model.addRow(new Object[]{
                        nv.getMaNV(),
                        nv.getTenNV(),
                        gioiTinh,
                        nv.getNgaySinh(),
                        nv.getSdt(),
                        nv.getEmail(),
                        getChucVuHienThi(nv.getChucVu())
                });
            } else {
                JOptionPane.showMessageDialog(nhanVienPanel, "Không tìm thấy nhân viên");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTatCaNhanVien(){
        try {
            ArrayList<NhanVien> dsNhanVien = nhanVienService.getTatCaNhanVien();
            DefaultTableModel model = nhanVienPanel.model;
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng trước khi load mới
            for (NhanVien nv : dsNhanVien) {
                String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ"; // Nếu có kiểu boolean
                model.addRow(new Object[]{
                        nv.getMaNV(),
                        nv.getTenNV(),
                        gioiTinh,
                        nv.getNgaySinh(),
                        nv.getSdt(),
                        nv.getEmail(),
                        getChucVuHienThi(nv.getChucVu()),
                        (nv.getTaiKhoan() != null)
                                ? getTrangThaiTaiKhoanHienThi(nv.getTaiKhoan().getTrangThai())
                                : ""
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ThemNhanVien(){
        int row = nhanVienPanel.table.getSelectedRow();
        if(row == -1){
            int namHienTai = LocalDate.now().getYear();
            String maNV = "NV" + (namHienTai % 100) + String.format("%03d", nhanVienService.getSoLuongNhanVien() + 1);
            String tenNV = nhanVienPanel.txt_TenNhanVien.getText().strip();
            String sdt = nhanVienPanel.txt_SoDienThoai.getText().strip();
            boolean gioiTinh = nhanVienPanel.rdbtn_Nam.isSelected() ? true : false;
            Date date = nhanVienPanel.ngaySinh.getDate();
            LocalDate ngaySinh = null;
            if (date != null) {
                ngaySinh = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }
            String chucVuDaChon = nhanVienPanel.cbb_ChucVu.getSelectedItem().toString();
            ChucVuNhanVien chucVu = getChucVu(chucVuDaChon);
            String email = nhanVienPanel.txt_Email.getText().strip();

            NhanVien nhanVien = new NhanVien(maNV,tenNV,ngaySinh,sdt,gioiTinh,email,chucVu);
            if(nhanVienService.themNhanVien(nhanVien)){
                JOptionPane.showMessageDialog(nhanVienPanel, "Thêm thành công");
                LamMoi();
                getTatCaNhanVien();
            }else{
                JOptionPane.showMessageDialog(nhanVienPanel, "Thêm thất bại");
            }
        }else JOptionPane.showMessageDialog(nhanVienPanel, "Nhân viên đã tồn tại");
    }


    private void CapNhatNhanVien() {
        int row = nhanVienPanel.table.getSelectedRow();
        String maNV =nhanVienPanel.table.getValueAt(row,0).toString();
        String tenNV = nhanVienPanel.txt_TenNhanVien.getText().strip();
        String sdt = nhanVienPanel.txt_SoDienThoai.getText().strip();
        boolean gioiTinh = nhanVienPanel.rdbtn_Nam.isSelected() ? true : false;
        Date date = nhanVienPanel.ngaySinh.getDate();
        LocalDate ngaySinh = null;
        if (date != null) {
            ngaySinh = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        String chucVuDaChon = nhanVienPanel.cbb_ChucVu.getSelectedItem().toString();
        ChucVuNhanVien chucVu = getChucVu(chucVuDaChon);
        String email = nhanVienPanel.txt_Email.getText().strip();
        NhanVien nhanVien = new NhanVien(maNV,tenNV,ngaySinh,sdt,gioiTinh,email,chucVu);
        int confirm = JOptionPane.showConfirmDialog(nhanVienPanel, "Bạn có chắc chắn muốn cập nhật thông tin nhân viên" + maNV,"Chú ý",JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            if(nhanVienService.CapNhatNhanVien(nhanVien)){
                JOptionPane.showMessageDialog(nhanVienPanel, "Cập nhật thành công");
                LamMoi();
                getTatCaNhanVien();
            }else{
                JOptionPane.showMessageDialog(nhanVienPanel, "Cập nhật thất bại");
            }
        }else {
            return;
        }
    }

    private void VoHieuHoaTaiKhoan() {
        int row = nhanVienPanel.table.getSelectedRow();
        if(row != -1){
            String maNV = nhanVienPanel.table.getValueAt(row,0).toString();
            if(taiKhoanService.VoHieuHoaTaiKhoan(maNV,TrangThaiTaiKhoan.VoHieuHoa)){
                JOptionPane.showMessageDialog(nhanVienPanel,"Vô hiệu hóa tài khoản thành công");
                LamMoi();
                getTatCaNhanVien();
            }
        } else{
            JOptionPane.showMessageDialog(nhanVienPanel,"Vui lòng chọn nhân viên muốn vô hiệu hóa tài khoản");
        }
    }

    private void LocNhanVienTheoChucVu() {
        String chucVuChon = nhanVienPanel.cbb_LocChucVu.getSelectedItem().toString();
        ArrayList<NhanVien> dsNhanVien;
        if (chucVuChon.equals("Tất cả")) {
            dsNhanVien = nhanVienService.getTatCaNhanVien(); // gọi lấy toàn bộ
        } else {
            ChucVuNhanVien chucVu = getChucVu(chucVuChon);
            dsNhanVien = nhanVienService.getNhanVienTheoChucVu(chucVu.toString());
        }
        DefaultTableModel model = nhanVienPanel.model;
        model.setRowCount(0);
        for (NhanVien nv : dsNhanVien) {
            String chucVuHienThi = getChucVuHienThi(nv.getChucVu());
            // Nếu chọn "Tất cả" thì hiển thị hết
            if (chucVuChon.equals("Tất cả") || chucVuHienThi.equals(chucVuChon)) {
                String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";
                model.addRow(new Object[]{
                        nv.getMaNV(),
                        nv.getTenNV(),
                        gioiTinh,
                        nv.getNgaySinh(),
                        nv.getSdt(),
                        nv.getEmail(),
                        chucVuHienThi
                });
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = nhanVienPanel.table.getSelectedRow();
        nhanVienPanel.txt_TenNhanVien.setText(nhanVienPanel.table.getValueAt(row,1).toString());
        String phai = nhanVienPanel.table.getValueAt(row,2).toString();
        if(phai.equals("Nam")) {
            nhanVienPanel.rdbtn_Nam.setSelected(true);
            nhanVienPanel.rdbtn_Nu.setSelected(false);
        }else {
            nhanVienPanel.rdbtn_Nam.setSelected(false);
            nhanVienPanel.rdbtn_Nu.setSelected(true);
        }
        String ngaySinhStr = nhanVienPanel.table.getValueAt(row, 3).toString(); // "1985-08-22"
        LocalDate localDate = LocalDate.parse(ngaySinhStr);
        Date date = java.sql.Date.valueOf(localDate);
        nhanVienPanel.ngaySinh.setDate(date);
        nhanVienPanel.txt_SoDienThoai.setText(nhanVienPanel.table.getValueAt(row,4).toString());
        nhanVienPanel.txt_Email.setText(nhanVienPanel.table.getValueAt(row,5).toString());
        nhanVienPanel.cbb_ChucVu.setSelectedItem(nhanVienPanel.table.getValueAt(row,6).toString());
    }

    private void CapTaiKhoanNhanVien() {
        int row = nhanVienPanel.table.getSelectedRow();
        if(row != -1){
            ChucVuNhanVien chucVu = getChucVu(nhanVienPanel.table.getValueAt(row,7).toString());
            VaiTro vaiTro;
            if(chucVu.equals(ChucVuNhanVien.QuanLy)){
                vaiTro = VaiTro.QuanLy;
            }else if(chucVu.equals(ChucVuNhanVien.LeTan)){
                vaiTro = VaiTro.NhanVien;
            }else {
                JOptionPane.showMessageDialog(nhanVienPanel,"Tài khoản chỉ được cấp cho nhân viên");
                return;
            }
            String maNV = nhanVienPanel.table.getValueAt(row,0).toString();
            String tenDangNhap = nhanVienPanel.table.getValueAt(row,4).toString();
            String matKhau = "11111111";
            TaiKhoan tk = new TaiKhoan(tenDangNhap, matKhau, vaiTro);
            if(taiKhoanService.CapTaiKhoanNhanVien(tk,maNV)){
                JOptionPane.showMessageDialog(nhanVienPanel,"Cấp tài khoản thành công \nMặt khẩu mặt định là '11111111'");
                LamMoi();
                getTatCaNhanVien();
            }
        } else{
            JOptionPane.showMessageDialog(nhanVienPanel,"Vui lòng chọn nhân viên muốn cấp tài khoản");
        }
    }

    private ChucVuNhanVien getChucVu(String tenChucVu) {
        switch (tenChucVu) {
            case "Kế toán" -> { return ChucVuNhanVien.KeToan ; }
            case "Kỹ thuật" -> { return ChucVuNhanVien.KyThuat; }
            case "Lễ tân" -> { return ChucVuNhanVien.LeTan; }
            case "Buồng phòng" -> { return ChucVuNhanVien.BuongPhong; }
            case "Bếp" -> { return ChucVuNhanVien.Bep; }
            case "Bảo vệ" -> { return ChucVuNhanVien.BaoVe; }
            case "Quản lý" -> { return ChucVuNhanVien.QuanLy; }
            default -> { return ChucVuNhanVien.LeTan; }
        }
    }

    private String getChucVuHienThi(ChucVuNhanVien chucVu) {
        switch (chucVu) {
            case QuanLy -> { return "Quản lý"; }
            case LeTan -> { return "Lễ tân"; }
            case KeToan -> { return "Kế toán"; }
            case KyThuat -> { return "Kỹ thuật"; }
            case BuongPhong -> { return "Buồng phòng"; }
            case Bep -> { return "Bếp"; }
            case BaoVe -> { return "Bảo vệ"; }
            default -> { return ""; }
        }
    }

    private String getTrangThaiTaiKhoanHienThi(TrangThaiTaiKhoan trangThaiTaiKhoan) {
        switch (trangThaiTaiKhoan) {
            case DangHoatDong -> { return "Đang hoạt động"; }
            case VoHieuHoa -> { return "Vô Hiệu Hóa"; }
            default -> { return ""; }
        }
    }

    private  void LamMoi(){
        nhanVienPanel.txt_TenNhanVien.setText("");
        nhanVienPanel.rdbtn_Nam.setSelected(true);
        nhanVienPanel.rdbtn_Nu.setSelected(false);
        nhanVienPanel.txt_SoDienThoai.setText("");
        nhanVienPanel.txt_Email.setText("");
        nhanVienPanel.txt_TimMaNhanVien.setText("");
        nhanVienPanel.txt_TimSoDienThoai.setText("");
        nhanVienPanel.ngaySinh.setDate(null);
        nhanVienPanel.cbb_ChucVu.setSelectedIndex(0);
        nhanVienPanel.cbb_LocChucVu.setSelectedIndex(0);
        nhanVienPanel.txt_TenNhanVien.requestFocus();
        getTatCaNhanVien();
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
package controller;

import entity.NhanVien;
import enums.ChucVuNhanVien;
import services.NhanVienService;
import views.NhanVienPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhanVienController implements MouseListener {
    private NhanVienService nhanVienService;
    private NhanVienPanel nhanVienPanel;

    public NhanVienController(NhanVienPanel nhanVienPanel){
        nhanVienService = new NhanVienService();
        this.nhanVienPanel = nhanVienPanel;

        nhanVienPanel.btn_ThemNhanVien.addActionListener(e -> ThemNhanVien());
        nhanVienPanel.btn_CapNhat.addActionListener(e -> CapNhatNhanVien());
        nhanVienPanel.cbb_LocChucVu.addActionListener(e -> locNhanVienTheoChucVu());
        nhanVienPanel.table.addMouseListener(this);
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
                        getChucVuHienThi(nv.getChucVu())
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
            LocalDate ngaySinh = LocalDate.now();
            String chucVuDaChon = nhanVienPanel.cbb_ChuVu.getSelectedItem().toString();
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
        LocalDate ngaySinh = LocalDate.now();
        String chucVuDaChon = nhanVienPanel.cbb_ChuVu.getSelectedItem().toString();
        ChucVuNhanVien chucVu = getChucVu(chucVuDaChon);
        String email = nhanVienPanel.txt_Email.getText().strip();
        NhanVien nhanVien = new NhanVien(maNV,tenNV,ngaySinh,sdt,gioiTinh,email,chucVu);
        if(nhanVienService.CapNhatNhanVien(nhanVien)){
            JOptionPane.showMessageDialog(nhanVienPanel, "Cập nhật thành công");
            LamMoi();
            getTatCaNhanVien();
        }else{
            JOptionPane.showMessageDialog(nhanVienPanel, "Cập nhật thất bại");
        }
    }

    private void locNhanVienTheoChucVu() {
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

        nhanVienPanel.txt_SoDienThoai.setText(nhanVienPanel.table.getValueAt(row,4).toString());
        nhanVienPanel.txt_Email.setText(nhanVienPanel.table.getValueAt(row,5).toString());
        nhanVienPanel.cbb_ChuVu.setSelectedItem(nhanVienPanel.table.getValueAt(row,6).toString());
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

    private  void LamMoi(){
        nhanVienPanel.txt_TenNhanVien.setText("");
        nhanVienPanel.rdbtn_Nam.setSelected(true);
        nhanVienPanel.rdbtn_Nu.setSelected(false);
        nhanVienPanel.txt_SoDienThoai.setText("");
        nhanVienPanel.txt_Email.setText("");
        nhanVienPanel.cbb_ChuVu.setSelectedIndex(0);
        nhanVienPanel.txt_TenNhanVien.requestFocus();
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

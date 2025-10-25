package controller;

import entity.KhachHang;
import entity.NhanVien;
import enums.ChucVuNhanVien;
import enums.HangKhachHang;
import services.KhachHangService;
import views.KhachHangPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class KhachHangController implements MouseListener {
    private KhachHangService khachHangService;
    private KhachHangPanel khachHangPanel;

    public KhachHangController(KhachHangPanel khachHangPanel){
        khachHangService = new KhachHangService();
        this.khachHangPanel = khachHangPanel;

        khachHangPanel.btn_ThemKhachHang.addActionListener(e -> ThemKhachHang());
        khachHangPanel.btn_LamMoi.addActionListener(e -> LamMoi());
        khachHangPanel.btn_CapNhat.addActionListener(e -> CapNhatKhachHang());
        khachHangPanel.cbb_LocHangKhachHang.addActionListener(e -> LocHangKhachHang());
        khachHangPanel.btn_Tim.addActionListener(e -> TimKhachHang());
        khachHangPanel.rdbtn_TimSoDienThoai.addActionListener(e -> {
            khachHangPanel.txt_TimSoDienThoai.setEditable(true);
            khachHangPanel.txt_TimSoCanCuocCongDan.setEditable(false);
        });
        khachHangPanel.rdbtn_TimSoCanCuocCongDan.addActionListener(e -> {
            khachHangPanel.txt_TimSoDienThoai.setEditable(false);
            khachHangPanel.txt_TimSoCanCuocCongDan.setEditable(true);
        });
        khachHangPanel.table.addMouseListener(this);
    }

    private void TimKhachHang() {
        try {
            KhachHang kh;
            if(khachHangPanel.rdbtn_TimSoCanCuocCongDan.isSelected()){
                String soCCCD = khachHangPanel.txt_TimSoCanCuocCongDan.getText().strip();
                kh = khachHangService.TimKhachHang(soCCCD,"CCCD");
            }else{
                String soDT = khachHangPanel.txt_TimSoDienThoai.getText().strip();
                kh = khachHangService.TimKhachHang(soDT,"SDT");
            }
            if(kh != null){
                DefaultTableModel model = khachHangPanel.model;
                model.setRowCount(0);
                String gioiTinh = kh.isGioiTinh() ? "Nam" : "Nữ"; // Nếu có kiểu boolean
                model.addRow(new Object[]{
                        kh.getMaKH(),
                        kh.getTenKH(),
                        gioiTinh,
                        kh.getNgaySinh(),
                        kh.getSdt(),
                        kh.getEmail(),
                        kh.getSoCCCD(),
                        getHangKhachHienThi(kh.getHangKH()),
                        kh.getDiemTichLuy()
                });
            }else JOptionPane.showMessageDialog(khachHangPanel,"Không tìm thấy khách hàng");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(khachHangPanel, "Lỗi khi tìm khách hàng: " + e.getMessage());
        }
    }

    public void getTatCaKhachHang(){
        try {
            ArrayList<KhachHang> dsKhachHang = khachHangService.getTatCaKhachHang();
            DefaultTableModel model = khachHangPanel.model;
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng trước khi load mới
            for (KhachHang kh : dsKhachHang) {
                String gioiTinh = kh.isGioiTinh() ? "Nam" : "Nữ"; // Nếu có kiểu boolean
                model.addRow(new Object[]{
                        kh.getMaKH(),
                        kh.getTenKH(),
                        gioiTinh,
                        kh.getNgaySinh(),
                        kh.getSdt(),
                        kh.getEmail(),
                        kh.getSoCCCD(),
                        getHangKhachHienThi(kh.getHangKH()),
                        kh.getDiemTichLuy()
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ThemKhachHang(){
        int row = khachHangPanel.table.getSelectedRow();
        if(row == -1){
            int namHienTai = LocalDate.now().getYear();
            String maKH = "KH" + (namHienTai % 100) + String.format("%03d", khachHangService.getSoLuongKhachHang() + 1);
            String tenKH = khachHangPanel.txt_TenKhachHang.getText().strip();
            String sdt = khachHangPanel.txt_SoDienThoai.getText().strip();
            boolean gioiTinh = khachHangPanel.rdbtn_Nam.isSelected() ? true : false;
            LocalDate ngaySinh = LocalDate.now();
            String soCCCD = khachHangPanel.txt_soCCCD.getText().strip();
            String email = khachHangPanel.txt_Email.getText().strip();

            KhachHang khachHang = new KhachHang(maKH, tenKH, gioiTinh, ngaySinh, email, sdt,
                    soCCCD,0);
            if(khachHangService.themKhachHang(khachHang)){
                JOptionPane.showMessageDialog(khachHangPanel, "Thêm thành công");
                LamMoi();
                getTatCaKhachHang();
            }else{
                JOptionPane.showMessageDialog(khachHangPanel, "Thêm thất bại");
            }
        }else JOptionPane.showMessageDialog(khachHangPanel, "Nhân viên đã tồn tại");
    }

    private void CapNhatKhachHang() {
        int row = khachHangPanel.table.getSelectedRow();
        String maKH =khachHangPanel.table.getValueAt(row,0).toString();
        String tenKH = khachHangPanel.txt_TenKhachHang.getText().strip();
        String sdt = khachHangPanel.txt_SoDienThoai.getText().strip();
        boolean gioiTinh = khachHangPanel.rdbtn_Nam.isSelected() ? true : false;
        LocalDate ngaySinh = LocalDate.now();
        String email = khachHangPanel.txt_Email.getText().strip();
        String soCCCD = khachHangPanel.txt_soCCCD.getText().strip();
        int diemTichLuy = Integer.parseInt(khachHangPanel.table.getValueAt(row,8).toString());
        KhachHang khachHang = new KhachHang(maKH,tenKH,gioiTinh, ngaySinh, email, sdt,soCCCD,diemTichLuy);
        if(khachHangService.CapNhatKhachHang(khachHang)){
            JOptionPane.showMessageDialog(khachHangPanel, "Cập nhật thành công");
            LamMoi();
            getTatCaKhachHang();
        }else{
            JOptionPane.showMessageDialog(khachHangPanel, "Cập nhật thất bại");
        }
    }

    private void LocHangKhachHang() {
        String hangKhachHangChon = khachHangPanel.cbb_LocHangKhachHang.getSelectedItem().toString();
        ArrayList<KhachHang> dsKhachHang;
        if (hangKhachHangChon.equals("Tất cả")) {
            dsKhachHang = khachHangService.getTatCaKhachHang(); // gọi lấy toàn bộ
        } else {
            HangKhachHang hangKhachHang = getHangKhachHang(hangKhachHangChon);
            dsKhachHang = khachHangService.getKhachHangTheoHang(hangKhachHang.toString());
        }
        DefaultTableModel model = khachHangPanel.model;
        model.setRowCount(0);
        for (KhachHang kh : dsKhachHang) {
            String HangkhachHienThi = getHangKhachHienThi(kh.getHangKH());
            // Nếu chọn "Tất cả" thì hiển thị hết
            if (hangKhachHangChon.equals("Tất cả") || HangkhachHienThi.equals(hangKhachHangChon)) {
                String gioiTinh = kh.isGioiTinh() ? "Nam" : "Nữ";
                model.addRow(new Object[]{
                        kh.getMaKH(),
                        kh.getTenKH(),
                        gioiTinh,
                        kh.getNgaySinh(),
                        kh.getSdt(),
                        kh.getEmail(),
                        kh.getSoCCCD(),
                        getHangKhachHienThi(kh.getHangKH()),
                        kh.getDiemTichLuy()
                });
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = khachHangPanel.table.getSelectedRow();
        khachHangPanel.txt_TenKhachHang.setText(khachHangPanel.table.getValueAt(row,1).toString());
        String phai = khachHangPanel.table.getValueAt(row,2).toString();
        if(phai.equals("Nam")) {
            khachHangPanel.rdbtn_Nam.setSelected(true);
            khachHangPanel.rdbtn_Nu.setSelected(false);
        }else {
            khachHangPanel.rdbtn_Nam.setSelected(false);
            khachHangPanel.rdbtn_Nu.setSelected(true);
        }
        String ngaySinhStr = khachHangPanel.table.getValueAt(row, 3).toString(); // "1985-08-22"
        LocalDate localDate = LocalDate.parse(ngaySinhStr);
        Date date = java.sql.Date.valueOf(localDate);
        khachHangPanel.ngaySinh.setDate(date);
        khachHangPanel.txt_SoDienThoai.setText(khachHangPanel.table.getValueAt(row,4).toString());
        khachHangPanel.txt_Email.setText(khachHangPanel.table.getValueAt(row,5).toString());
        khachHangPanel.txt_soCCCD.setText(khachHangPanel.table.getValueAt(row,6).toString());
    }

    private HangKhachHang getHangKhachHang(String tenHangKhachHang) {
        switch (tenHangKhachHang) {
            case "Đồng" -> { return HangKhachHang.Dong ; }
            case "Bạc" -> { return HangKhachHang.Bac; }
            case "Vàng" -> { return HangKhachHang.Vang; }
            case "Kim cương" -> { return HangKhachHang.KimCuong; }
            default -> { return HangKhachHang.Dong; }
        }
    }

    private String getHangKhachHienThi(HangKhachHang hangKhachHang) {
        switch (hangKhachHang) {
            case Dong -> { return "Đồng"; }
            case Bac -> { return "Bạc"; }
            case Vang -> { return "Vàng"; }
            case KimCuong -> { return "Kim cương"; }
            default -> { return ""; }
        }
    }

    private  void LamMoi(){
        khachHangPanel.txt_TenKhachHang.setText("");
        khachHangPanel.rdbtn_Nam.setSelected(true);
        khachHangPanel.rdbtn_Nu.setSelected(false);
        khachHangPanel.txt_SoDienThoai.setText("");
        khachHangPanel.txt_Email.setText("");
        khachHangPanel.txt_soCCCD.setText("");
        khachHangPanel.txt_TenKhachHang.setText("");
        khachHangPanel.txt_TimSoDienThoai.setText("");
        khachHangPanel.txt_TimSoCanCuocCongDan.setText("");
        khachHangPanel.cbb_LocHangKhachHang.setSelectedIndex(0);
        khachHangPanel.ngaySinh.setDate(null);
        getTatCaKhachHang();
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

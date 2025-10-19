package controller;

import entity.NhanVien;
import services.NhanVienService;
import views.NhanVienPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class NhanVienController {
    private NhanVienService nhanVienService;
    private NhanVienPanel nhanVienPanel;
    public NhanVienController(NhanVienPanel nhanVienPanel){
        nhanVienService = new NhanVienService();
        this.nhanVienPanel = nhanVienPanel;
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
                        nv.getChucVu()
                });
                System.out.println("Danh sách NV: " + dsNhanVien.size());
                for (NhanVien nv1 : dsNhanVien) {
                    System.out.println(nv1.getMaNV() + " - " + nv1.getTenNV());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi khi tải menu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

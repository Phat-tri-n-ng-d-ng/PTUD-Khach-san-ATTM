package controller;

import entity.KhachHang;
import entity.Phong;
import enums.TrangThaiPhong;
import services.KhachHangService;
import services.PhongServices;
import views.ThueDatPhongPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ThueDatPhongController {
    private ThueDatPhongPanel thueDatPhongPanel;
    private PhongServices phongServices;
    private KhachHangService khachHangServies;
    private ArrayList<Phong> danhSachPhong;
    private ArrayList<Phong> danhSachPhongHienThi;
//    private String trangThai;

    public ThueDatPhongController(ThueDatPhongPanel thueDatPhongPanel){
        this.thueDatPhongPanel = thueDatPhongPanel;
        phongServices = new PhongServices();
        khachHangServies = new KhachHangService();
        danhSachPhong = phongServices.getDSP();
        thueDatPhongPanel.btn_BoChon.addActionListener(e -> BoChonPhong());
        thueDatPhongPanel.txt_SoDienThoai.addActionListener(e -> getKhachHang());
        thueDatPhongPanel.btn_Loc.addActionListener(e-> LocTrangThaiPhong());
    }

    public void getTatCaPhong(){
        danhSachPhongHienThi = danhSachPhong;
        // Thêm các ô giả lập
        for (Phong phong : danhSachPhongHienThi) {
            JPanel phongPanel = new JPanel();
            phongPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            phongPanel.setLayout(null);

            if(phong.getTrangThai().equals(TrangThaiPhong.Trong)){
                phongPanel.setBackground(Color.white);
            }else if(phong.getTrangThai().equals(TrangThaiPhong.DangSuDung)){
                phongPanel.setBackground(Color.GREEN);
            }else{
                phongPanel.setBackground(Color.red);
            }

            JLabel label = new JLabel("Phòng: P" );
            label.setBounds(6, 12, 0, 0);
            phongPanel.add(label);
            phongPanel.setPreferredSize(new Dimension(200, 100));

            JLabel lbl_Phong = new JLabel("Phòng: "+ phong.getMaPhong());
            lbl_Phong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            lbl_Phong.setBounds(6, 10, 184, 20);
            phongPanel.add(lbl_Phong);

            JLabel lbl_LoaiPhong = new JLabel("Loại phòng: " + phong.getLoaiPhong().getTenLoaiPhong());
            lbl_LoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            lbl_LoaiPhong.setBounds(6, 36, 184, 17);
            phongPanel.add(lbl_LoaiPhong);

            JLabel lbl_SoLuongToiDa = new JLabel("Số lượng tối đa: " + phong.getSoLuongToiDa());
            lbl_SoLuongToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            lbl_SoLuongToiDa.setBounds(6, 57, 184, 16);
            phongPanel.add(lbl_SoLuongToiDa);

            JLabel lbl_Gia = new JLabel("Giá: " + phong.getGiaPhong());
            lbl_Gia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            lbl_Gia.setBounds(6, 78, 149, 16);
            phongPanel.add(lbl_Gia);

            JButton btn_ThemPhong = new JButton("+");
            btn_ThemPhong.setBounds(187, 65, 50, 30);
            phongPanel.add(btn_ThemPhong);

            btn_ThemPhong.addActionListener(e -> {
                ThemPhongChon(phong);
            });
            thueDatPhongPanel.danhSachPhongPanel.add(phongPanel);
        }
    }
    private void ThemPhongChon(Phong phong) {
        for (int i = 0; i < thueDatPhongPanel.model.getRowCount(); i++) {
            String maPhongTrongBang = thueDatPhongPanel.model.getValueAt(i, 0).toString();
            if (maPhongTrongBang.equals(phong.getMaPhong())) {
                JOptionPane.showMessageDialog(null,
                        "Phòng này đã được chọn!",
                        "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        thueDatPhongPanel.model.addRow(new Object[]{
                phong.getMaPhong(),
                phong.getLoaiPhong().getTenLoaiPhong(),
                phong.getSoLuongToiDa(),
                phong.getGiaPhong()
        });
    }


    private void BoChonPhong() {
        int row = thueDatPhongPanel.table.getSelectedRow();
        if(row != -1){
            thueDatPhongPanel.model.removeRow(row);
        }else if(thueDatPhongPanel.table.getRowCount() == 0){
            JOptionPane.showMessageDialog(thueDatPhongPanel,"Chưa có phòng nào trong danh sách");
        }else{
            JOptionPane.showMessageDialog(thueDatPhongPanel,"Vui lòng chọn phòng muốn bỏ khỏi danh sách");
        }
    }

    private void getKhachHang(){
        String soDTKhachHang = thueDatPhongPanel.txt_SoDienThoai.getText();
        KhachHang khachHang = khachHangServies.TimKhachHang(soDTKhachHang,"SDT");
        if(khachHang != null){
            thueDatPhongPanel.txt_TenKhachHang.setText(khachHang.getTenKH());
            if(khachHang.isGioiTinh() == true) {
                thueDatPhongPanel.rdbtn_Nam.setSelected(true);
                thueDatPhongPanel.rdbtn_Nu.setSelected(false);
            }else {
                thueDatPhongPanel.rdbtn_Nam.setSelected(false);
                thueDatPhongPanel.rdbtn_Nu.setSelected(true);
            }
            thueDatPhongPanel.txt_Email.setText(khachHang.getEmail());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String ngaySinhFormatted = khachHang.getNgaySinh().format(formatter);
            thueDatPhongPanel.txt_ngaySinhKhachHang.setText(ngaySinhFormatted);
        }
    }

    private void LocTrangThaiPhong() {
        ArrayList<Phong> danhSachTam = new ArrayList<>();
        if(thueDatPhongPanel.chckbx_phongTrong.isSelected()){
            for(Phong phong : danhSachPhong){
                if(phong.getTrangThai().equals(TrangThaiPhong.Trong)){
                    danhSachTam.add(phong);
                }
            }
            danhSachPhongHienThi = danhSachTam;
        }
    }
}

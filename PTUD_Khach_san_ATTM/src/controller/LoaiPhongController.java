/*
 * @ (#) LoaiPhongController.java     1.0     10/22/2025
 *
 *Copyright (c) 2025 IUH. All rights reserved.
 */
package controller;


import database.LoaiPhongDao;
import entity.LoaiPhong;
import services.LoaiPhongService;
import services.PhongServices;
import views.LoaiPhongPanel;
import views.NhanVienPanel;
import views.PhongPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/*
 * @description: This class represents a bank with many bank accounts
 * @author: Anh, Le The Anh
 * @date: 10/22/2025
 * @version: 1.0
 */
public class LoaiPhongController {
        LoaiPhongService loaiPhongService;
        LoaiPhongPanel loaiPhongPanel;

    public LoaiPhongController(LoaiPhongPanel lpp){
        loaiPhongService= new LoaiPhongService();
        this.loaiPhongPanel=lpp;
        SuKien();
    }


    public void SuKien(){
        loaiPhongPanel.btn_Them.addActionListener(e -> {
            themLoaiPhong();
        });

        loaiPhongPanel.table.getSelectionModel().addListSelectionListener(e -> {
            docLenTxtField();
        });

        loaiPhongPanel.btn_CapNhat.addActionListener(e -> {
            capNhatLoaiPhong();
        });
        loaiPhongPanel.btn_LamMoi.addActionListener(e -> {
            lamMoi();
        });
    }

    public void lamMoi() {
        loaiPhongPanel.txt_TenLoaiPhong.setText("");
        loaiPhongPanel.txt_SoNguoiMacDinh.setText("");
        loaiPhongPanel.txt_TyLeCoc.setText("");
        loaiPhongPanel.txt_GiaNiemYet.setText("");
        loaiPhongPanel.table.clearSelection();
        getDanhDachLoaiPhong();
    }

    public boolean kiemTraDuLieuNhap(){
        String tenLP= loaiPhongPanel.txt_TenLoaiPhong.getText();
        String giaNY= loaiPhongPanel.txt_GiaNiemYet.getText().trim();
        String tyLC= loaiPhongPanel.txt_TyLeCoc.getText().trim();
        String soNguoi= loaiPhongPanel.txt_SoNguoiMacDinh.getText().trim();

        if (tenLP.isEmpty() || !tenLP.matches("^[\\p{L}0-9 ]{1,50}$")) {
            baoLoi("Tên loại phòng không được rỗng, tối đa 50 ký tự và không chứa ký tự đặc biệt.");
            loaiPhongPanel.txt_TenLoaiPhong.requestFocus();
            return false;
        }
        double gia;
        try {
            gia = Double.parseDouble(giaNY);
            if (gia < 1000000) {
                baoLoi("Giá niêm yết phải >= 1.000.000");
                loaiPhongPanel.txt_GiaNiemYet.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            baoLoi("Giá niêm yết phải là số và >= 1.000.000");
            loaiPhongPanel.txt_GiaNiemYet.requestFocus();
            return false;
        }double tyLe;
        try {
            tyLe = Double.parseDouble(tyLC);
            if (tyLe < 10 || tyLe > 50) {
                baoLoi("Tỷ lệ cọc phải nằm trong khoảng 10% - 50%.");
                loaiPhongPanel.txt_TyLeCoc.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            baoLoi("Tỷ lệ cọc phải là số hợp lệ.");
            loaiPhongPanel.txt_TyLeCoc.requestFocus();
            return false;
        }
        int soNguoiInt;
        try {
            soNguoiInt = Integer.parseInt(soNguoi);
            if (soNguoiInt <=1) {
                baoLoi("Số người mặc định phải lớn hơn hoặc bằng 1.");
                loaiPhongPanel.txt_SoNguoiMacDinh.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            baoLoi("Số người mặc định phải là số hợp lệ.");
            loaiPhongPanel.txt_SoNguoiMacDinh.requestFocus();
            return false;
        }

        return true;
    }

    public void capNhatLoaiPhong() {
        int r=loaiPhongPanel.table.getSelectedRow();
        if(r<0){
            baoLoi("Hãy chọn dòng để cập nhật");
            return;
        }
        if(kiemTraDuLieuNhap()==false) return;
        String ma= loaiPhongPanel.table.getValueAt(r,0)+"";
        String ten = loaiPhongPanel.txt_TenLoaiPhong.getText();
        Double gia = Double.parseDouble(loaiPhongPanel.txt_GiaNiemYet.getText());
        Double coc= Double.parseDouble(loaiPhongPanel.txt_TyLeCoc.getText());
        Integer soNguoi = Integer.parseInt(loaiPhongPanel.txt_SoNguoiMacDinh.getText());
        LoaiPhong lp = new LoaiPhong(ma,ten,gia,coc,soNguoi);
        if(loaiPhongService.capNhatLoaiPhong(lp)){
            loaiPhongPanel.table.setValueAt(ten,r,1);
            loaiPhongPanel.table.setValueAt(gia,r,2);
            loaiPhongPanel.table.setValueAt(coc+"%",r,3);
            loaiPhongPanel.table.setValueAt(soNguoi,r,4);
            baoLoi("Cập nhật loại phòng thành công!");
            // Làm mới lại danh sách phòng



        }else{
            baoLoi("Lỗi cập nhật");
        }

    }

    public void docLenTxtField() {
        int r=loaiPhongPanel.table.getSelectedRow();

        String ma= loaiPhongPanel.table.getValueAt(r,0)+"";
        loaiPhongPanel.txt_TenLoaiPhong.setText(loaiPhongPanel.table.getValueAt(r,1)+"");
        loaiPhongPanel.txt_GiaNiemYet.setText(loaiPhongPanel.table.getValueAt(r,2)+"");
        String tyLe = loaiPhongPanel.table.getValueAt(r,3)+"";
        if (tyLe.endsWith("%")) {
            tyLe = tyLe.substring(0, tyLe.length()-1);
        }
        loaiPhongPanel.txt_TyLeCoc.setText(tyLe);
        loaiPhongPanel.txt_SoNguoiMacDinh.setText(loaiPhongPanel.table.getValueAt(r,4)+"");

    }

    public void getDanhDachLoaiPhong(){
        ArrayList<LoaiPhong> dslp= loaiPhongService.getDanhSachLoaiPhong();
        DefaultTableModel model = loaiPhongPanel.model;
        model.setRowCount(0);
        for (LoaiPhong lp : dslp){
            model.addRow(new Object[]{lp.getMaLoaiPhong(),lp.getTenLoaiPhong(),lp.getGiaNiemYet(),lp.getTyLeCoc()+"%",lp.getSoNguoiMacDinh()});
        }
    }
    public void themLoaiPhong(){

            if(kiemTraDuLieuNhap()==false) return;
            int so = loaiPhongService.getSoLuongLoaiPhong() + 1;
            String ma = String.format("LP%03d", so);
            String ten = loaiPhongPanel.txt_TenLoaiPhong.getText();
            Double gia = Double.parseDouble(loaiPhongPanel.txt_GiaNiemYet.getText());
            Double coc= Double.parseDouble(loaiPhongPanel.txt_TyLeCoc.getText());
            Integer soNguoi = Integer.parseInt(loaiPhongPanel.txt_SoNguoiMacDinh.getText());
            DefaultTableModel model = loaiPhongPanel.model;
            LoaiPhong lp = new LoaiPhong(ma,ten,gia,coc,soNguoi);
            if(loaiPhongService.themLoaiPhong(lp)){
                model.addRow(new Object[]{ma,ten,gia,coc+"%",soNguoi});
                baoLoi("Thêm loại phòng thành công!");
            }else{
                baoLoi("Lỗi thêm");
            }
        }
    public void baoLoi(String s){
        JOptionPane.showMessageDialog(null,s);
    }

}

/*
 * @ (#) PhongController.java     1.0     10/23/2025
 *
 *Copyright (c) 2025 IUH. All rights reserved.
 */
package controller;


import entity.LoaiPhong;
import entity.Phong;
import enums.TrangThaiPhong;
import services.LoaiPhongService;
import services.PhongServices;
import views.PhongPanel;

import javax.swing.*;
import java.util.ArrayList;

/*
 * @description: This class represents a bank with many bank accounts
 * @author: Anh, Le The Anh
 * @date: 10/23/2025
 * @version: 1.0
 */
public class PhongController {
    LoaiPhongService loaiPhongService;
    PhongPanel phongPanel;
    PhongServices phongServices;


    public PhongController(PhongPanel pp) {
        this.phongPanel = pp;
        phongServices=new PhongServices();
        loaiPhongService= new LoaiPhongService();
        suKien();


    }

    private void suKien() {
        phongPanel.btn_Tim.addActionListener(e -> {
            timPhong();
        });

        phongPanel.btn_ThemPhong.addActionListener(e -> {
            themPhong();
        });
        phongPanel.btn_CapNhat.addActionListener(e -> {
            capNhatPhong();
        });
        // Chuot click
        phongPanel.table.getSelectionModel().addListSelectionListener(e -> {
            docLenTextField();
        });
        phongPanel.chckbx_Suite.addActionListener(e->{locPhongTheoLoai();});
        phongPanel.chckbx_Superior.addActionListener(e->{locPhongTheoLoai();});
        phongPanel.chckbx_Standard.addActionListener(e->{locPhongTheoLoai();});
        phongPanel.chckbx_FamilyRoom.addActionListener(e->{locPhongTheoLoai();});
        phongPanel.chckbx_Deluxe.addActionListener(e->{locPhongTheoLoai();});
    }

    private void locPhongTheoLoai() {
        String ds="";
        if(phongPanel.chckbx_Deluxe.isSelected()) ds+=phongPanel.chckbx_Deluxe.getText()+",";
        if(phongPanel.chckbx_Suite.isSelected()) ds+=phongPanel.chckbx_Suite.getText()+",";
        if(phongPanel.chckbx_Standard.isSelected()) ds+=phongPanel.chckbx_Standard.getText()+",";
        if(phongPanel.chckbx_FamilyRoom.isSelected()) ds+=phongPanel.chckbx_FamilyRoom.getText()+",";
        if(phongPanel.chckbx_Superior.isSelected()) ds+=phongPanel.chckbx_Superior.getText()+",";
            // xóa dấu , cuối cùng
        if(!ds.isEmpty()){
            ds=ds.substring(0,ds.length()-1);
        }
        ArrayList<Phong> dsp = phongServices.locPhongTheoLoai(ds);
        phongPanel.model.setRowCount(0);
        for (Phong p : dsp) {
            phongPanel.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
        }
    }

    public boolean kiemTraDuLieuNhap(){
        String soPhong= phongPanel.txt_SoPhong.getText();
        String sLTD= phongPanel.txt_SoLuongToiDa.getText();
        String tang= phongPanel.txt_Tang.getText();
        if(!soPhong.matches("\\d{1,3}") || Integer.parseInt(soPhong) <= 0){
            baoLoi("Số phòng phải là số nguyên > 0 và tối đa 3 chữ số");
            phongPanel.txt_SoPhong.requestFocus();
            return false;
        }
        if(!sLTD.matches("\\d+")||Integer.parseInt(sLTD)<=1){
            baoLoi("Số lượng tối đa phải là số nguyên > 1");
            phongPanel.txt_SoLuongToiDa.requestFocus();
            return false;
        }
        if(!tang.matches("\\d{1,2}")||Integer.parseInt(tang)<=0){
            baoLoi("Tầng phải là số nguyên > 0 và tối đa 2 chữ số");
            phongPanel.txt_Tang.requestFocus();
            return false;
        }
        return true;
    }
    private void docLenTextField() {
        int r = phongPanel.table.getSelectedRow();
        if(r<0) return;
        String ma= phongPanel.table.getValueAt(r,0)+"";
        String tang= ma.substring(1,3);
        String stt= ma.substring(3);
        phongPanel.txt_SoPhong.setText(stt);
        phongPanel.txt_SoLuongToiDa.setText(phongPanel.table.getValueAt(r,2)+"");
        phongPanel.txt_Tang.setText(tang);
        phongPanel.cbb_LoaiPhong.setSelectedItem(phongPanel.table.getValueAt(r,1));
    }

    private void timPhong() {
        String ma= phongPanel.txt_TimMaPhong.getText().trim();
        if (ma.isEmpty()) {
            baoLoi("Hãy nhập mã để tìm");
            phongPanel.txt_TimMaPhong.requestFocus();
            return;
        }
        Phong p = phongServices.timPhongBangMa(ma);
        if(p!=null){
            phongPanel.model.setRowCount(0);
            phongPanel.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
        }else{
            baoLoi("Không tìm thấy phòng có mã: "+ma);
            phongPanel.txt_TimMaPhong.requestFocus();
        }
    }

    private void capNhatPhong() {
        int r = phongPanel.table.getSelectedRow();
        if(r<0) baoLoi("Hãy chọn dòng để cập nhật");
        else{
            String ma = phongPanel.table.getValueAt(r,0)+"";
            if(kiemTraDuLieuNhap()==false) return;
            String tang=String.format("%02d",Integer.parseInt(phongPanel.txt_Tang.getText()));
            String soPhong= String.format("%03d",Integer.parseInt(phongPanel.txt_SoPhong.getText()));
            if(!tang.equals(ma.substring(1,3))){
                baoLoi("Không được thay đổi tầng");
                phongPanel.txt_Tang.requestFocus();
                return;
            }

            if(!soPhong.equals(ma.substring(3))){
                baoLoi("Không được thay đổi số phòng");
                phongPanel.txt_SoPhong.requestFocus();
                return;
            }

            String tenlp= phongPanel.cbb_LoaiPhong.getSelectedItem()+"";
            LoaiPhong lp =loaiPhongService.getThongTinLoaiPhong(tenlp);
            int sltd= Integer.parseInt(phongPanel.txt_SoLuongToiDa.getText());
            // tự tính lại giá và tiền cọc trong thuộc tính dẫn xuất của Phong
            Phong p = new Phong(ma,TrangThaiPhong.Trong,lp,sltd);

            if(phongServices.capNhatPhong(p)){
                phongPanel.table.setValueAt(p.getLoaiPhong().getTenLoaiPhong(),r,1);
                phongPanel.table.setValueAt(p.getSoLuongToiDa(),r,2);
                phongPanel.table.setValueAt(p.getGiaPhong(),r,3);
                phongPanel.table.setValueAt(p.getTienCoc(),r,4);
                phongPanel.table.setValueAt(p.getTrangThai().getMoTa(),r,5);
                baoLoi("Cập nhật phòng thành công");
            }else{
                baoLoi("Lỗi cập nhật");
            }

        }
    }

    private void themPhong() {
        if(kiemTraDuLieuNhap()==false) return;
        String tang=String.format("%02d",Integer.parseInt(phongPanel.txt_Tang.getText()));
        String stt= String.format("%03d",Integer.parseInt(phongPanel.txt_SoPhong.getText()));


        String ma= "P"+tang+stt;
        String tenlp= phongPanel.cbb_LoaiPhong.getSelectedItem()+"";
        LoaiPhong lp =loaiPhongService.getThongTinLoaiPhong(tenlp);
        int sltd= Integer.parseInt(phongPanel.txt_SoLuongToiDa.getText());
        Phong p = new Phong(ma,TrangThaiPhong.Trong,lp,sltd);
        if(phongServices.themPhong(p)){
            phongPanel.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
            baoLoi("Thêm phòng thành công!");
        }else{
            baoLoi("Thêm phòng thất bại (Số phòng "+stt+" ở tầng "+tang+" đã tồn tại)");
        }

    }

    public void hienThiDanhSachPhong(){
        ArrayList<Phong> dsp= phongServices.getDSP();
        phongPanel.model.setRowCount(0);
        for (Phong p : dsp) {
            phongPanel.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
        }
    }
    public void hienThiLoaiPhong(){
        ArrayList<LoaiPhong> dslp = loaiPhongService.getDanhSachLoaiPhong();
        for (LoaiPhong l : dslp) {
            phongPanel.cbb_LoaiPhong.addItem(l.getTenLoaiPhong());
        }
    }

    public void baoLoi(String s){
        JOptionPane.showMessageDialog(null,s);
    }

}

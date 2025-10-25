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
    LoaiPhongService lps;
    PhongPanel pp;
    PhongServices ps;


    public PhongController(PhongPanel pp) {
        this.pp = pp;
        ps=new PhongServices();
        lps= new LoaiPhongService();
        suKien();


    }

    private void suKien() {
        pp.btn_Tim.addActionListener(e -> {
            timPhong();
        });

        pp.btn_ThemPhong.addActionListener(e -> {
            themPhong();
        });
        pp.btn_CapNhat.addActionListener(e -> {
            capNhatPhong();
        });
        // Chuot click
        pp.table.getSelectionModel().addListSelectionListener(e -> {
            docLenManHinh();
        });
        pp.chckbx_Suite.addActionListener(e->{locPhongTheoLoai();});
        pp.chckbx_Superior.addActionListener(e->{locPhongTheoLoai();});
        pp.chckbx_Standard.addActionListener(e->{locPhongTheoLoai();});
        pp.chckbx_FamilyRoom.addActionListener(e->{locPhongTheoLoai();});
        pp.chckbx_Deluxe.addActionListener(e->{locPhongTheoLoai();});
    }

    private void locPhongTheoLoai() {
        String ds="";
        if(pp.chckbx_Deluxe.isSelected()) ds+=pp.chckbx_Deluxe.getText()+",";
        if(pp.chckbx_Suite.isSelected()) ds+=pp.chckbx_Suite.getText()+",";
        if(pp.chckbx_Standard.isSelected()) ds+=pp.chckbx_Standard.getText()+",";
        if(pp.chckbx_FamilyRoom.isSelected()) ds+=pp.chckbx_FamilyRoom.getText()+",";
        if(pp.chckbx_Superior.isSelected()) ds+=pp.chckbx_Superior.getText()+",";
            // xóa dấu , cuối cùng
        if(!ds.isEmpty()){
            ds=ds.substring(0,ds.length()-1);
        }
        ArrayList<Phong> dsp = ps.locPhongTheoLoai(ds);
        pp.model.setRowCount(0);
        for (Phong p : dsp) {
            pp.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
        }
    }

    public boolean kiemTraDuLieuNhap(){
        String soPhong= pp.txt_SoPhong.getText();
        String sLTD= pp.txt_SoLuongToiDa.getText();
        String tang= pp.txt_Tang.getText();
        if(!soPhong.matches("\\d{1,3}") || Integer.parseInt(soPhong) <= 0){
            baoLoi("Số phòng phải là số nguyên > 0 và tối đa 3 chữ số");
            pp.txt_SoPhong.requestFocus();
            return false;
        }
        if(!sLTD.matches("\\d+")||Integer.parseInt(sLTD)<=1){
            baoLoi("Số lượng tối đa phải là số nguyên > 1");
            pp.txt_SoLuongToiDa.requestFocus();
            return false;
        }
        if(!tang.matches("\\d{1,2}")||Integer.parseInt(tang)<=0){
            baoLoi("Tầng phải là số nguyên > 0 và tối đa 2 chữ số");
            pp.txt_Tang.requestFocus();
            return false;
        }
        return true;
    }
    private void docLenManHinh() {
        int r = pp.table.getSelectedRow();
        if(r<0) return;
        String ma= pp.table.getValueAt(r,0)+"";
        String tang= ma.substring(1,3);
        String stt= ma.substring(3);
        pp.txt_SoPhong.setText(stt);
        pp.txt_SoLuongToiDa.setText(pp.table.getValueAt(r,2)+"");
        pp.txt_Tang.setText(tang);
        pp.cbb_LoaiPhong.setSelectedItem(pp.table.getValueAt(r,1));
    }

    private void timPhong() {
        String ma= pp.txt_TimMaPhong.getText().trim();
        if (ma.isEmpty()) {
            baoLoi("Hãy nhập mã để tìm");
            pp.txt_TimMaPhong.requestFocus();
            return;
        }
        Phong p = ps.timPhongBangMa(ma);
        if(p!=null){
            pp.model.setRowCount(0);
            pp.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
        }else{
            baoLoi("Không tìm thấy phòng có mã: "+ma);
            pp.txt_TimMaPhong.requestFocus();
        }
    }

    private void capNhatPhong() {
        int r = pp.table.getSelectedRow();
        if(r<0) baoLoi("Hãy chọn dòng để cập nhật");
        else{
            String ma = pp.table.getValueAt(r,0)+"";
            if(kiemTraDuLieuNhap()==false) return;
            String tang=String.format("%02d",Integer.parseInt(pp.txt_Tang.getText()));
            String soPhong= String.format("%03d",Integer.parseInt(pp.txt_SoPhong.getText()));
            if(!tang.equals(ma.substring(1,3))){
                baoLoi("Không được thay đổi tầng");
                pp.txt_Tang.requestFocus();
                return;
            }

            if(!soPhong.equals(ma.substring(3))){
                baoLoi("Không được thay đổi số phòng");
                pp.txt_SoPhong.requestFocus();
                return;
            }

            String tenlp= pp.cbb_LoaiPhong.getSelectedItem()+"";
            LoaiPhong lp =lps.getThongTinLoaiPhong(tenlp);
            int sltd= Integer.parseInt(pp.txt_SoLuongToiDa.getText());
            Phong p = new Phong(ma,TrangThaiPhong.Trong,lp,sltd);

            if(ps.capNhatPhong(p)){
                pp.table.setValueAt(p.getLoaiPhong().getTenLoaiPhong(),r,1);
                pp.table.setValueAt(p.getSoLuongToiDa(),r,2);
                pp.table.setValueAt(p.getGiaPhong(),r,3);
                pp.table.setValueAt(p.getTienCoc(),r,4);
                pp.table.setValueAt(p.getTrangThai().getMoTa(),r,5);
            }else{
                baoLoi("Lỗi cập nhật");
            }

        }
    }

    private void themPhong() {
        if(kiemTraDuLieuNhap()==false) return;
        String tang=String.format("%02d",Integer.parseInt(pp.txt_Tang.getText()));
        String stt= String.format("%03d",Integer.parseInt(pp.txt_SoPhong.getText()));


        String ma= "P"+tang+stt;
        String tenlp= pp.cbb_LoaiPhong.getSelectedItem()+"";
        LoaiPhong lp =lps.getThongTinLoaiPhong(tenlp);
        int sltd= Integer.parseInt(pp.txt_SoLuongToiDa.getText());
        Phong p = new Phong(ma,TrangThaiPhong.Trong,lp,sltd);
//        System.out.println(p.getMaPhong());
//        System.out.println(p.getGiaPhong());
//        System.out.println(p.getTienCoc());
        if(ps.themPhong(p)){
            pp.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
        }else{
            baoLoi("Thêm phòng thất bại (Số phòng "+stt+" ở tầng "+tang+" đã tồn tại)");
        }

    }

    public void hienThiDanhSachPhong(){
        ArrayList<Phong> dsp= ps.getDSP();
        for (Phong p : dsp) {
            pp.model.addRow(new Object[]{p.getMaPhong(),p.getLoaiPhong().getTenLoaiPhong(),p.getSoLuongToiDa(),p.getGiaPhong(),p.getTienCoc(),p.getTrangThai().getMoTa()});
        }
    }
    public void hienThiLoaiPhong(){
        ArrayList<LoaiPhong> dslp = lps.getDanhSachLoaiPhong();
        for (LoaiPhong l : dslp) {
            pp.cbb_LoaiPhong.addItem(l.getTenLoaiPhong());
        }
    }

    public void baoLoi(String s){
        JOptionPane.showMessageDialog(null,s);
    }

}

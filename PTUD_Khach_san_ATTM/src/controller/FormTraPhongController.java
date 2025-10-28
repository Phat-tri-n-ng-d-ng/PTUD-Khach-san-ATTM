/*
 * @ (#) FormTraPhongController.java     1.0     10/28/2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */
package controller;

import database.KhuyenMaiDao;
import entity.*;
import services.HoaDonService;
import services.KhachHangService;
import services.PhongServices;
import views.FormThongTinTraPhong;
import views.ThueDatPhongPanel;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

public class FormTraPhongController {
    private FormThongTinTraPhong formThongTinTraPhong;
    private ThueDatPhongPanel thueDatPhongPanel;
    private PhongServices phongServices;
    private KhachHangService khachHangServies;
    private HoaDonService hoaDonService;
    private KhuyenMaiDao khuyenMaiDao;

    public FormTraPhongController(ThueDatPhongPanel thueDatPhongPanel) {
        this.thueDatPhongPanel = thueDatPhongPanel;
        phongServices= new PhongServices();
        khachHangServies= new KhachHangService();
        hoaDonService= new HoaDonService();
        khuyenMaiDao= new KhuyenMaiDao();

        thueDatPhongPanel.btn_TraPhong.addActionListener(e -> {
            formThongTinTraPhong= new FormThongTinTraPhong();
            if (!hienThiThongTinLenForm()) return;
            formThongTinTraPhong.setVisible(true);
        });
    }

    private boolean hienThiThongTinLenForm() {
        String sdt=thueDatPhongPanel.txt_SoDienThoai.getText();
        int r=thueDatPhongPanel.model.getRowCount();

        if (sdt.isEmpty() || r<=0) {
            JOptionPane.showMessageDialog(null,"Hãy chọn phòng có trạng thái Đang sử dụng cần trả");
            return false;
        } else if(r>1){
            JOptionPane.showMessageDialog(null,"Mỗi lần chỉ được trả 1 phòng");
            return false;
        }else {
            // Lấy dữ liệu khách hàng
            KhachHang kh = khachHangServies.TimKhachHang(sdt,"SDT");
            formThongTinTraPhong.txt_SDT.setText(kh.getSdt());
            formThongTinTraPhong.txt_HoTen.setText(kh.getTenKH());
            formThongTinTraPhong.txt_NgaySinh.setText(kh.getNgaySinh()+"");
            formThongTinTraPhong.txt_HangKhachHang.setText(kh.getHangKH().getTenHang());
            formThongTinTraPhong.txt_CCCD.setText(kh.getSoCCCD());
            formThongTinTraPhong.txt_DiemTichLuy.setText(kh.getDiemTichLuy()+"");
            formThongTinTraPhong.txt_Email.setText(kh.getEmail());
            formThongTinTraPhong.txt_GioiTinh.setText(kh.isGioiTinh() ? "Nam":"Nữ");

            // Lấy dữ liệu phòng và chi tiết hóa đơn
            String maPhong=thueDatPhongPanel.table.getValueAt(r-1,0)+"";
            ChiTietHoaDon cthd = hoaDonService.timChiTietHoaDonTheoMaPhongVaTrangThaiHoaDon(maPhong,"HoaDonThuePhong");
            Phong phong= phongServices.timPhongBangMa(maPhong);
            String lp=thueDatPhongPanel.table.getValueAt(r-1, 1) + "";
            String sltd=thueDatPhongPanel.table.getValueAt(r-1, 2) + "";
            String gia=thueDatPhongPanel.table.getValueAt(r-1, 3) + "";
            int soNgayO=cthd.getSoNgayO();
            double tienCoc=phong.getTienCoc();
            double thanhTien=Double.parseDouble(gia)*soNgayO-tienCoc;

            formThongTinTraPhong.model.addRow(new Object[]{maPhong,lp,sltd,soNgayO,gia, tienCoc,thanhTien});

            // Lấy ngày nhận phòng
            HoaDon hoaDon=hoaDonService.timHoaDonTheoMaPhongVaTrangThaiHoaDon(maPhong, "HoaDonThuePhong");


//            Date date = java.util.Date.from(hd.getNgayNhanPhong().atZone(java.time.ZoneId.systemDefault()).toInstant());
//            formThongTinTraPhong.ngayBatDau.setDate(date);



            // Xử lý chọn phương thức thanh toán
            formThongTinTraPhong.rdbtn_ChuyenKhoan.addActionListener(e -> {
                formThongTinTraPhong.txt_TienKhachDua.setEditable(false);
                formThongTinTraPhong.lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setText("Chuyển khoản");
            });
            formThongTinTraPhong.rdbtn_TienMat.addActionListener(e -> {
                formThongTinTraPhong.txt_TienKhachDua.setEditable(true);
                formThongTinTraPhong.lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setText("Tiền mặt");
            });

            // Xử lý chọn loại hóa đơn
            formThongTinTraPhong.rdbtn_GopHoaDon.addActionListener(e -> {
                formThongTinTraPhong.lbl_PhuongThucThanhToanDuocChonGopHoaDon.setText("Gộp hóa đơn");
            });
            formThongTinTraPhong.rdbtn_TachHoaDon.addActionListener(e -> {
                formThongTinTraPhong.lbl_PhuongThucThanhToanDuocChonGopHoaDon.setText("Tách hóa đơn");
            });

            // Hiển thị thông tin các loại tiền
            Double tongTienPhong=0.0;
            int soDongTrongDanhSachPhongForm=formThongTinTraPhong.model.getRowCount();
            for (int i=0;i<soDongTrongDanhSachPhongForm;i++) {
                tongTienPhong+=Double.parseDouble(formThongTinTraPhong.table.getValueAt(i,6)+"");
            }
            formThongTinTraPhong.lbl_TienCuaTongTienTrongPnlTongTien.setText(tongTienPhong+" VND");

            KhuyenMai km = null;
            if (hoaDon!=null&&hoaDon.getMaHD()!=null) {
                km=khuyenMaiDao.get1KhuyenMaiTheoMa(hoaDon.getMaHD());
            }
            double tongTienSauGiam=tongTienPhong;
            if (km!=null) {
                double giamGia= km.getTyLeGiam();
                tongTienSauGiam =tongTienPhong-(tongTienPhong*giamGia/100);
            }
            formThongTinTraPhong.lbl_TienCuaTienTruKhuyenMaiPnlTongTien.setText(tongTienSauGiam+" VND");
            double phiDoiPhong = hoaDon.getPhiDoiPhong();
            formThongTinTraPhong.lbl_TienCuaPhiDoiPhongTrongPnlTongTien.setText(phiDoiPhong+" VND");
            double tienThue=hoaDon.getTienThue();
            double tongTienThanhToan= tongTienSauGiam+phiDoiPhong+tienThue;
            formThongTinTraPhong.lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1.setText(tongTienThanhToan+" VND");
            formThongTinTraPhong.lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1_1.setText(tienThue+" VND");

            // Nhập tiền khách đưa xong nhấn enter
            formThongTinTraPhong.txt_TienKhachDua.addActionListener(e->{
                String tienKhachDua= formThongTinTraPhong.txt_TienKhachDua.getText();
                Double tienThua= Double.parseDouble(tienKhachDua)-tongTienThanhToan;
                formThongTinTraPhong.lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setText(tienKhachDua+" VND");
                formThongTinTraPhong.lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setText(tienThua+" VND");
            });

            //









        }
        return true;
    }
}

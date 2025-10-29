package controller;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import enums.TrangThaiPhong;
import services.HoaDonService;
import services.PhongServices;
import views.MangHinhChinhPanel;

import java.util.ArrayList;

public class MangHinhChinhController {
    private MangHinhChinhPanel mangHinhChinhPanel;
    private HoaDonService hoaDonService ;

    public MangHinhChinhController (MangHinhChinhPanel mangHinhChinhPanel){
        hoaDonService = new HoaDonService();
        this.mangHinhChinhPanel = mangHinhChinhPanel;
    }

    public void getDanhSachPhongDatHomNay (){
        ArrayList<HoaDon> danhSachHoaDon = hoaDonService.getHoaDonTheoTrangThai("HoaDonDatPhong");
        for(HoaDon hoaDon : danhSachHoaDon){
            for(ChiTietHoaDon chiTietHoaDon : hoaDon.getcTHD()){
                mangHinhChinhPanel.model.addRow(new Object[]{
                    chiTietHoaDon.getPhong().getMaPhong(),
                    chiTietHoaDon.getPhong().getLoaiPhong(),
                    chiTietHoaDon.getPhong().getSoLuongToiDa(),
                    hoaDon.getKhachHang().getTenKH(),
                    hoaDon.getKhachHang().getSdt()
                });
            }
        }
    }
}

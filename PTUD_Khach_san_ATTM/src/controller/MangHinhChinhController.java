package controller;

import database.PhongDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import enums.TrangThaiPhong;
import services.HoaDonService;
import services.PhongServices;
import views.MangHinhChinhPanel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MangHinhChinhController {
    private PhongDao phongServices;
    private MangHinhChinhPanel mangHinhChinhPanel;
    private HoaDonService hoaDonService ;

    public MangHinhChinhController (MangHinhChinhPanel mangHinhChinhPanel){
        hoaDonService = new HoaDonService();
        this.mangHinhChinhPanel = mangHinhChinhPanel;
        phongServices = new PhongDao();
    }

    public void getDanhSachPhongDatHomNay() {
        ArrayList<HoaDon> danhSachHomNay = hoaDonService.getHoaDonDatPhong();
        for (HoaDon hd : danhSachHomNay) {
            for (ChiTietHoaDon cthd : hd.getcTHD()) {
                Phong p = cthd.getPhong();
                mangHinhChinhPanel.model.addRow(new Object[]{
                        p.getMaPhong(),
                        p.getLoaiPhong().getTenLoaiPhong(),
                        p.getSoLuongToiDa(),
                        hd.getKhachHang().getTenKH(),
                        hd.getKhachHang().getSdt()
                });
            }
        }
    }

    public void getTinhTrangPhong(){
        ArrayList<Phong> danhSachPhong = phongServices.getDSPhong();
        int phongTrong = 0;
        int phongDat = 0;
        int phongThue = 0;
        for(Phong phong : danhSachPhong){
           if(phong.getTrangThai().equals(TrangThaiPhong.DangSuDung)){
               phongThue ++;
           }else if(phong.getTrangThai().equals(TrangThaiPhong.DaDat)){
               phongDat ++;
           }else{
               phongTrong++;
           }
        }
        mangHinhChinhPanel.lbl_SoPhongThue.setText(String.valueOf(phongThue));
        mangHinhChinhPanel.lbl_SoPhongDat.setText(String.valueOf(phongDat));
        mangHinhChinhPanel.lbl_SoPhongTrong.setText(String.valueOf(phongTrong));
    }

    public void tuDongCapNhatTrangThaiPhong(){
        LocalDate ngayHomNay = LocalDate.now();
        hoaDonService.tuDongCapNhatTrangThaiPhong(ngayHomNay);
    }
}

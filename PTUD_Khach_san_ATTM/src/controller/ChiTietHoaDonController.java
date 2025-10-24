package controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import services.ChiTietHoaDonService;
import services.HoaDonService;
import views.HoaDonPanel;

public class ChiTietHoaDonController {
	private HoaDonPanel hoaDonPanel;
	private ChiTietHoaDonService cthdService;

	public ChiTietHoaDonController(HoaDonPanel hoaDonPanel) {
		cthdService = new ChiTietHoaDonService();
		this.cthdService = cthdService;
		this.hoaDonPanel = hoaDonPanel;
	}
	public void getChiTietHoaDonTheoMa(){
        try {
        	String ma = hoaDonPanel.txt_MaHoaDon.getText();
        	ArrayList<ChiTietHoaDon> dsChiTietHoaDon = cthdService.getChiTietHoaDonTheoMa(ma);
        	DefaultTableModel model = hoaDonPanel.model2;
        	model.setRowCount(0);
        	 for (ChiTietHoaDon cthd : dsChiTietHoaDon) {
                 model.addRow(new Object[]{
                     cthd.getPhong().getMaPhong(),
                     cthd.getSoNgayO(),
                     cthd.getPhong().getGiaPhong(),
                     cthd.getThanhTien()
//                     hd.getTrangThai()
                 });
             }

        }catch (Exception e) {
            e.printStackTrace();
        }

	
	

	}
}

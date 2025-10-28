package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import com.toedter.calendar.JDateChooser;

import database.KhachHangDao;
import entity.HoaDon;
import entity.KhachHang;
import services.HoaDonService;
import services.KhachHangService;
import views.FormThongTinNhanPhong;
import views.ThueDatPhongPanel;

public class FormNhanPhongController implements ActionListener {

	private FormThongTinNhanPhong formNhanPhong;
	private ThueDatPhongController thueDatController;
	private KhachHangService khachHangService;
	private ThueDatPhongPanel thueDatPhongPanel;
	private HoaDonService hoaDonService;
	
	

	public FormNhanPhongController(FormThongTinNhanPhong formNhanPhong, ThueDatPhongPanel thueDatPhongPanel) {
	
		this.formNhanPhong = formNhanPhong;
		khachHangService = new KhachHangService();
		hoaDonService = new HoaDonService();
		this.thueDatPhongPanel = thueDatPhongPanel;
		formNhanPhong.btn_Them.addActionListener(this);
	}
	public LocalDateTime layNgayTuDateChooser(JDateChooser ngayChon) {
        if (ngayChon!= null && ngayChon.getDate() != null) {
            java.util.Date ngay = ngayChon.getDate();
            return ngay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }
	public java.util.Date chuyenNgaySangDateChoose(LocalDateTime ngay) {
		if (ngay == null) {
	        return null; 
	    }
		return Date.from(ngay.atZone(ZoneId.systemDefault()).toInstant());
	}

	
	public void getKhachHang() {
		String sdt = thueDatPhongPanel.txt_SoDienThoai.getText();
		KhachHang kh = khachHangService.TimKhachHang(sdt, "SDT");
		ArrayList<HoaDon> dshd = hoaDonService.timHoaDonTheoSDT(sdt);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String ngaySinhFormatted = hd.getNgayNhanPhong().format(formatter);
		if(kh != null) {
			formNhanPhong.txt_SDT.setText(sdt);
			formNhanPhong.txt_HoTen.setText(kh.getTenKH());
			formNhanPhong.txt_CCCD.setText(kh.getSoCCCD());
			formNhanPhong.txt_NgaySinh.setText(kh.getNgaySinh().toString());
			formNhanPhong.txt_GioiTinh.setText(kh.isGioiTinh() ? "Nam":"Nữ");
//			formNhanPhong.ngayBatDau.;
			
		}
	}
	public void getNguoiO() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == formNhanPhong.btn_Them) {
			String sdt = formNhanPhong.txt_SDT.getText();
			KhachHang kh = khachHangService.TimKhachHang(sdt, "SDT");
			formNhanPhong.model_NguoiO.setRowCount(0);
			formNhanPhong.model_NguoiO.addRow(new Object[] {
					kh.getTenKH(),
					kh.getNgaySinh(),
					kh.isGioiTinh() ? "Nam" : "Nữ",
					kh.getSdt(),
					kh.getSoCCCD()
			});
			
		}
		
	}
	
	
	
	

}

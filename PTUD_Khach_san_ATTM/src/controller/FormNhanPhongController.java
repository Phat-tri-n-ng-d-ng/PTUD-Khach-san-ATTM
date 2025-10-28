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

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import database.KhachHangDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NguoiO;
import entity.Phong;
import services.HoaDonService;
import services.KhachHangService;
import services.NguoiOService;
import services.PhongServices;
import views.FormThongTinNhanPhong;
import views.ThueDatPhongPanel;

public class FormNhanPhongController implements ActionListener {

	private FormThongTinNhanPhong formNhanPhong;
	private ThueDatPhongController thueDatController;
	private KhachHangService khachHangService;
	private ThueDatPhongPanel thueDatPhongPanel;
	private HoaDonService hoaDonService;
	private PhongServices phongService;
	private NguoiOService nguoiOService;
	private ArrayList<NguoiO> dsnguoiO;
	
	

	public FormNhanPhongController(FormThongTinNhanPhong formNhanPhong, ThueDatPhongPanel thueDatPhongPanel) {
	
		this.formNhanPhong = formNhanPhong;
		khachHangService = new KhachHangService();
		hoaDonService = new HoaDonService();
		phongService = new PhongServices();
		nguoiOService = new NguoiOService();
		this.thueDatPhongPanel = thueDatPhongPanel;
		formNhanPhong.btn_Them.addActionListener(this);
		formNhanPhong.btn_XacNhan.addActionListener(this);
		formNhanPhong.btn_Huy.addActionListener(this);
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

	
//	public void getKhachHang() {
//		String sdt = thueDatPhongPanel.txt_SoDienThoai.getText();
//		KhachHang kh = khachHangService.TimKhachHang(sdt, "SDT");
//		ArrayList<HoaDon> dshd = hoaDonService.timHoaDonTheoSDT(sdt);
////		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
////        String ngaySinhFormatted = hd.getNgayNhanPhong().format(formatter);
//		if(kh != null) {
//			formNhanPhong.txt_SDT.setText(sdt);
//			formNhanPhong.txt_HoTen.setText(kh.getTenKH());
//			formNhanPhong.txt_CCCD.setText(kh.getSoCCCD());
//			formNhanPhong.txt_NgaySinh.setText(kh.getNgaySinh().toString());
//			formNhanPhong.txt_GioiTinh.setText(kh.isGioiTinh() ? "Nam":"Nữ");
//		}
//	}
	public void getPhong() {
		int r=thueDatPhongPanel.model.getRowCount();
		int col = thueDatPhongPanel.model.getColumnCount();
		if(col < 4) {
			JOptionPane.showMessageDialog(null,"Khong co thong tin nhan phong" );
		}
		 for (int i = 0; i < r; i++) {
		        String maPhong = thueDatPhongPanel.table.getValueAt(i, 0).toString();
		        String lp = thueDatPhongPanel.table.getValueAt(i, 1).toString();
		        String sltd = thueDatPhongPanel.table.getValueAt(i, 2).toString();
		        String gia = thueDatPhongPanel.table.getValueAt(i, 3).toString();

		        Phong phong = phongService.timPhongBangMa(maPhong);
		        double tienCoc = phong.getTienCoc();
		        formNhanPhong.model.addRow(new Object[]{maPhong, lp, sltd, gia, tienCoc});
		    }
	}
	
	public void getThoiGian() {
		String maPhong = thueDatPhongPanel.table.getValueAt(0, 0).toString();
		HoaDon hd = hoaDonService.getHoaDon(maPhong, "HoaDonDatPhong");
		if (hd == null) {
			 JOptionPane.showMessageDialog(formNhanPhong, "Khong tim thay hoa don dat phong cho phong: " + maPhong);
		     return; 
		}
		formNhanPhong.ngayBatDau.setDate(java.util.Date.from(hd.getNgayNhanPhong().atZone(ZoneId.systemDefault()).toInstant()));
		formNhanPhong.ngayKetThuc.setDate(java.util.Date.from(hd.getNgayTraPhong().atZone(ZoneId.systemDefault()).toInstant()));

	}
	public void lamMoi() {
		formNhanPhong.txt_SDT.setText("");
		formNhanPhong.txt_HoTen.setText("");
		formNhanPhong.txt_CCCD.setText("");
		formNhanPhong.txt_GioiTinh.setText("");
		formNhanPhong.txt_NgaySinh.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o == formNhanPhong.btn_Them) {
			int row = formNhanPhong.table_NguoiO.getRowCount();
				if(row == 0) 
				{
					formNhanPhong.model_NguoiO.addRow(new Object[] {
							formNhanPhong.txt_HoTen.getText(),
							formNhanPhong.txt_NgaySinh.getText(),
							formNhanPhong.txt_GioiTinh.getText(),
							formNhanPhong.txt_SDT.getText(),
							formNhanPhong.txt_CCCD.getText()
					});
				}else {
					for(int i = 0; i < row; i ++) {
						if(formNhanPhong.table_NguoiO.getValueAt(i, 4).equals(formNhanPhong.txt_CCCD.getText())) {
							JOptionPane.showMessageDialog(formNhanPhong, "Trung so CCCD");
							lamMoi();
							return;
						}else {
							formNhanPhong.model_NguoiO.addRow(new Object[] {
									formNhanPhong.txt_HoTen.getText(),
									formNhanPhong.txt_NgaySinh.getText(),
									formNhanPhong.txt_GioiTinh.getText(),
									formNhanPhong.txt_SDT.getText(),
									formNhanPhong.txt_CCCD.getText()
							});
						}
					}
					
				}
		}else if(o == formNhanPhong.btn_XacNhan) {
			int rowP = formNhanPhong.table_Phong.getSelectedRow();
			if (rowP == -1) {
		        JOptionPane.showMessageDialog(formNhanPhong, "Vui long chon phong truoc!");
		        return;
		    }
			String maP = (String) formNhanPhong.table_Phong.getValueAt(rowP, 0);
			Phong p = new Phong();
			p.setMaPhong(maP);
			HoaDon hd = hoaDonService.getHoaDonTheoMaPhongVaTTHD(maP, "HoaDonDatPhong");
		    int rowNguoiO = formNhanPhong.table_NguoiO.getRowCount();
		    int soNguoiThem = 0;
			 for (int i = 0; i < rowNguoiO; i++) {
			        String hoTen = formNhanPhong.table_NguoiO.getValueAt(i, 0).toString();
			        LocalDate ngaySinh = LocalDate.parse(formNhanPhong.table_NguoiO.getValueAt(i, 1).toString());
			        String gT = formNhanPhong.table_NguoiO.getValueAt(i, 2).toString();
			        boolean gioiTinh = gT.equalsIgnoreCase("Nam");
			        String sdt = formNhanPhong.table_NguoiO.getValueAt(i, 3).toString();
			        String soCCCD = formNhanPhong.table_NguoiO.getValueAt(i, 4).toString();
			        ArrayList<NguoiO> dsNgO = nguoiOService.getNguoiOTheoMaPhong(maP);
					NguoiO ngO = new NguoiO(hoTen, ngaySinh, sdt, soCCCD, gioiTinh, hd,p);
			        boolean kq = nguoiOService.themNguoiO(ngO);
			        if (kq) soNguoiThem++;
			    }

			    if (soNguoiThem > 0) {
			        hoaDonService.capNhatTrangThai(hd.getMaHD());
			        JOptionPane.showMessageDialog(formNhanPhong, "Da them " + soNguoiThem + " nguoi o va cap nhat trang thai hoa don!");
			    } else {
			        JOptionPane.showMessageDialog(formNhanPhong, "Khong co nguoi nao duoc them!");
			    }
			
			
			
		}else if(o == formNhanPhong.btn_Huy){
			int chon = JOptionPane.showConfirmDialog(formNhanPhong, "Ban co chac muon huy va dong form khong?", "Xac nhan",JOptionPane.YES_NO_OPTION);
			    if (chon == JOptionPane.YES_OPTION) {
			        formNhanPhong.dispose();
			    }
		}
	
	}
}
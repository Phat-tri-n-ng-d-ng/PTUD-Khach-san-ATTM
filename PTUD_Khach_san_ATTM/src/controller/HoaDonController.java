package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import entity.HoaDon;
import entity.NhanVien;

import services.HoaDonService;
import services.NhanVienService;
import views.HoaDonPanel;
import views.NhanVienPanel;

public class HoaDonController implements MouseListener {
	private HoaDonService hoaDonService;
	private HoaDonPanel hoaDonPanel;
	public HoaDonController( HoaDonPanel hoaDonPanel) {
		this.hoaDonService = new HoaDonService();
		this.hoaDonPanel = hoaDonPanel;
		hoaDonPanel.addMouseListener(this);
	}
	public void getTatCaHoaDOn(){
        try {
            ArrayList<HoaDon> dsHoaDon = hoaDonService.getDanhSachHoaDon();
            DefaultTableModel model = hoaDonPanel.model;
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng trước khi load mới
            for (HoaDon hd : dsHoaDon) {
                model.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getNgayLap(),
                    hd.getKhachHang().getTenKH(),
                    hd.getKhachHang().getSdt(),
                    hd.getTongTien(),
//                    hd.getTrangThai()
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//TIM HOA DOn trong khoan
//	public void timHoaDonTheoKhoang(Date ngayBD, Date ngayKT) {
//	    try {
//	        ArrayList<HoaDon> dsHoaDon = hoaDonService.timHoaDonTheoKhoang(ngayBD, ngayKT);
//	        DefaultTableModel model = hoaDonPanel.model;
//	        model.setRowCount(0);
//
//	        if (dsHoaDon.isEmpty()) {
//	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don trong khoang ngay nay!");
//	            return;
//	        }
//
//	        for (HoaDon hd : dsHoaDon) {
//	            model.addRow(new Object[]{
//	                hd.getMaHD(),
//	                hd.getNgayLap(),
//	                hd.getKhachHang().getTenKH(),
//	                hd.getKhachHang().getSdt(),
//	                hd.getTongTien()
//	            });
//	        }
//
//	    } catch (Exception e) {
//	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don: " + e.getMessage());
//	        e.printStackTrace();
//	    }
//	}
	
	//TIM HOA DOn trong trong
	public void timHoaDonTheoKhoang(Date ngay) {
	    try {
	        ArrayList<HoaDon> dsHoaDon = hoaDonService.timHoaDonTheoNgay(ngay);
	        DefaultTableModel model = hoaDonPanel.model;
	        model.setRowCount(0);

	        if (dsHoaDon.isEmpty()) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don trong khoang ngay nay!");
	            return;
	        }

	        for (HoaDon hd : dsHoaDon) {
	            model.addRow(new Object[]{
	                hd.getMaHD(),
	                hd.getNgayLap(),
	                hd.getKhachHang().getTenKH(),
	                hd.getKhachHang().getSdt(),
	                hd.getTongTien()
	            });
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	//Tim theo HD ma
	public void timHoaDonTheoMa(String maHD) {
	    try {
	        HoaDon hd = hoaDonService.timHoaDonTheoMa(maHD);
	        DefaultTableModel model = hoaDonPanel.model;
	        model.setRowCount(0);

	        if (hd == null) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don co ma: " + maHD);
	            return;
	        }

	        model.addRow(new Object[]{
	            hd.getMaHD(),
	            hd.getNgayLap(),
	            hd.getKhachHang().getTenKH(),
	            hd.getKhachHang().getSdt(),
	            hd.getTongTien()
	        });

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don theo ma: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	//TIm hoa don theo SDT
	public void timHoaDonTheoSDT(String SDT) {
	    try {
	        HoaDon hd = hoaDonService.timHoaDonTheoMa(SDT);
	        DefaultTableModel model = hoaDonPanel.model;
	        model.setRowCount(0);

	        if (hd == null) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don co ma: " + SDT);
	            return;
	        }

	        model.addRow(new Object[]{
	            hd.getMaHD(),
	            hd.getNgayLap(),
	            hd.getKhachHang().getTenKH(),
	            hd.getKhachHang().getSdt(),
	            hd.getTongTien()
	        });

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don theo ma: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
//	
	
//	private  void LamMoi(){
//        hoaDonPanel.table1.setText("");
//        hoaDonPanel.rdbtn_Nam.setSelected(true);
//        hoaDonPanel.rdbtn_Nu.setSelected(false);
//        hoaDonPanel.txt_SoDienThoai.setText("");
//        hoaDonPanel.txt_Email.setText("");
//        hoaDonPanel.cbb_ChuVu.setSelectedIndex(0);
//        hoaDonPanel.txt_TenNhanVien.requestFocus();
//    }

	@Override
	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		 if (e.getSource() == hoaDonPanel.table) {
//	            int row = hoaDonPanel.table.getSelectedRow();
//	            if (row >= 0) {
//	                try {
//	                    // Lấy dữ liệu từ table hoadon
//	                    String maHD = hoaDonPanel.table.getValueAt(row, 0).toString();
//	                    String ngayLap = hoaDonPanel.table1.getValueAt(row, 1).toString();
//	                    String tenKH = hoaDonPanel.table1.getValueAt(row, 2).toString();
//	                    String sdt = hoaDonPanel.table1.getValueAt(row, 3).toString();
//	                    String tongTien = hoaDonPanel.table1.getValueAt(row, 4).toString();
//	                    
//	                    // Hiển thị lên các textfield bên phải
//	                    hoaDonPanel.txt_MaHoaDon.setText(maHD);
//	                    hoaDonPanel.txtChonNgay.setText(ngayLap);
//	                    hoaDonPanel.txt_SoDienThoaiKhachHang.setText(sdt);
//	                    
//	                    // Có thể set thêm các field khác nếu cần
//	              
//	                    
//	                } catch (Exception ex) {
//	                    JOptionPane.showMessageDialog(hoaDonPanel,
//	                        "Lỗi khi chọn hóa đơn: " + ex.getMessage(),
//	                        "Lỗi", JOptionPane.ERROR_MESSAGE);
//	                }
//	            }
//	        }
		
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}

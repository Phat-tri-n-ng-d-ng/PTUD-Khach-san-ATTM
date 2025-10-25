package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import enums.PhuongThucThanhToan;
import services.HoaDonService;
import services.NhanVienService;
import views.HoaDonPanel;
import views.NhanVienPanel;

public class HoaDonController implements MouseListener,ActionListener {
	private HoaDonService hoaDonService;
	private HoaDonPanel hoaDonPanel;
	public HoaDonController( HoaDonPanel hoaDonPanel) {
		this.hoaDonService = new HoaDonService();
		this.hoaDonPanel = hoaDonPanel;
		
		hoaDonPanel.table_DanhSachHoaDon.addMouseListener(this);
		hoaDonPanel.btn_TimHoaDon_1.addActionListener(this);
	}
	public void getTatCaHoaDOn(){
        try {
            ArrayList<HoaDon> dsHoaDon = hoaDonService.getDanhSachHoaDon();
            hoaDonPanel.model_DSHD.setRowCount(0); // Xóa dữ liệu cũ trong bảng trước khi load mới
            for (HoaDon hd : dsHoaDon) {
            	String tongTien = String.format("%,.0f VND", hd.getTongTien());
            	hoaDonPanel.model_DSHD.addRow(new Object[]{
                    hd.getMaHD(),
                    hd.getNgayLap(),
                    hd.getKhachHang().getTenKH(),
                    hd.getKhachHang().getSdt(),
                    tongTien
                    
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
	
	//TIM HOA DOn trong ngay
	public void timHoaDonTheoKhoang(Date ngay) {
	    try {
	        ArrayList<HoaDon> dsHoaDon = hoaDonService.timHoaDonTheoNgay(ngay);
	        DefaultTableModel model = hoaDonPanel.model_DSHD;
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
	        DefaultTableModel model = hoaDonPanel.model_DSHD;
	        model.setRowCount(0);

	        if (hd == null) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don co ma: " + maHD);
	            return;
	        }
	        String tongTien = String.format("%,.0f VND", hd.getTongTien());
	        model.addRow(new Object[]{
	            hd.getMaHD(),
	            hd.getNgayLap(),
	            hd.getKhachHang().getTenKH(),
	            hd.getKhachHang().getSdt(),
	            tongTien
	        });

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don theo ma: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	//TIm hoa don theo SDT
	public void timHoaDonTheoSDT(String SDT) {
	    try {
	        HoaDon hd = hoaDonService.timHoaDonTheoSDT(SDT);
	        DefaultTableModel model = hoaDonPanel.model_DSHD;
	        model.setRowCount(0);

	        if (hd == null) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don co so dien thoai: " + SDT);
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
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don theo so dien thoai: " + e.getMessage());
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
		// TODO Auto-generated method stub
		 if (e.getSource() == hoaDonPanel.table_DanhSachHoaDon) {
	            int row = hoaDonPanel.table_DanhSachHoaDon.getSelectedRow();
	            if (row >= 0) {
	                try {
	                    // Lấy dữ liệu từ table hoadon
	                    String maHD = hoaDonPanel.table_DanhSachHoaDon.getValueAt(row, 0).toString();
	                    ArrayList<KhachHang> dsKh = hoaDonService.getKhachHangTheoHD(maHD);
	                    ArrayList<HoaDon> dsHoaDon = hoaDonService.getDanhSachHoaDon();
	                    ArrayList<ChiTietHoaDon> dsCTHD = hoaDonService.getChiTietHoaDonTheoMa(maHD);
	                    hoaDonPanel.model_DSKH.setRowCount(0);
	                    for(KhachHang kh : dsKh) {
	                    	String gioiTinh = kh.isGioiTinh() ? "Nam" : "Nữ";
	                    	hoaDonPanel.model_DSKH.addRow(new Object[]{
	                                kh.getTenKH(),
	                                gioiTinh,
	                                kh.getNgaySinh(),
	                                kh.getSdt(),
	                                kh.getSoCCCD(),
	                                
	                            });
	                    	HoaDon hd = hoaDonService.timHoaDonTheoMa(maHD);
	                   
		                    if(hd!= null) {
		                    	
		                    	if(PhuongThucThanhToan.TienMat.equals(hd.getpTTT())) {
		                    		hoaDonPanel.rdbtn_TienMat.setSelected(true);
		                    		hoaDonPanel.rdbtn_ChuyenKhoan.setSelected(false);
		                    	}else {
		                    		hoaDonPanel.rdbtn_ChuyenKhoan.setSelected(true);
		                    		hoaDonPanel.rdbtn_TienMat.setSelected(false);
		                    	}
		                    	
		                    	
		                    	hoaDonPanel.txt_TongTien.setText(String.valueOf(hd.getTongTien()));
		                    	hoaDonPanel.txt_Thue.setText(String.valueOf(hd.getTienThue()));
		                    	hoaDonPanel.txt_PhiDoiPhong.setText(String.valueOf(hd.getPhiDoiPhong()));
		                    	hoaDonPanel.txt_TongTienThanhToan.setText(String.valueOf(hd.getTongTienThanhToan()));
		                    	hoaDonPanel.txt_KhuyenMai.setText(String.valueOf(hd.getKhuyenMai().getTyLeGiam()));
		                    	hoaDonPanel.txt_TienNhan.setText(String.valueOf(hd.getTienNhan()));
		                    	hoaDonPanel.txt_TienTra.setText(String.valueOf(hd.getTienTra()));
		                    	hoaDonPanel.txt_NhanVienThucHien.setText(hd.getNhanVien().getTenNV());
		                    }
	                    	
	                    
	                    }
	                    for(ChiTietHoaDon cthd : dsCTHD) {
	                    	hoaDonPanel.model2.setRowCount(0);
	                    	hoaDonPanel.model2.addRow(new Object[]{
	                             cthd.getPhong().getMaPhong(),
	                             cthd.getSoNgayO(),
	                             cthd.getPhong().getGiaPhong(),
	                             cthd.getThanhTien(),
	                                
	                      });
	                    	
	                    }
	                    
	                    
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(hoaDonPanel,
	                        "Lỗi khi chọn hóa đơn: " + ex.getMessage(),
	                        "Lỗi", JOptionPane.ERROR_MESSAGE);
	                }
	                
	            }
	        }
		 
//		  try {
//	        	String ma = hoaDonPanel.txt_MaHoaDon.getText();
//	        	ArrayList<ChiTietHoaDon> dsChiTietHoaDon = cthdService.getChiTietHoaDonTheoMa(ma);
//	        	DefaultTableModel model = hoaDonPanel.model2;
//	        	model.setRowCount(0);
//	        	 for (ChiTietHoaDon cthd : dsChiTietHoaDon) {
//	                 model.addRow(new Object[]{
//	                     cthd.getPhong().getMaPhong(),
//	                     cthd.getSoNgayO(),
//	                     cthd.getPhong().getGiaPhong(),
//	                     cthd.getThanhTien()
////	                     hd.getTrangThai()
//	                 });
//	             }
//
//	        }catch (Exception e) {
//	            e.printStackTrace();
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o == hoaDonPanel.btn_TimHoaDon_1) {
			String maHD = hoaDonPanel.txt_MaHoaDon.getText().trim();
			String soDT = hoaDonPanel.txt_SoDienThoaiKhachHang.getText().trim();
			
			//DOi kieu date
//			java.util.Date ngayChonTEst = hoaDonPanel.ChonNgay.getDate();
//			Date ngayChon = new Date(ngayChonTEst.getTime());
			
			if(hoaDonPanel.rdbtn_TimMaHoaDon.isSelected()) {
				if (maHD.isEmpty()) {
			        JOptionPane.showMessageDialog(hoaDonPanel, "Vui long nhap ma hoa don!");
			        return;
			    }
				timHoaDonTheoMa(maHD);
			}else if(hoaDonPanel.rdbtn_SoDTKH.isSelected()) {
				if (soDT.isEmpty()) {
			    	JOptionPane.showMessageDialog(hoaDonPanel, "Vui long nhap so dien thoai!");
			        return;
				}
				timHoaDonTheoSDT(soDT);
			}
		    
		    
		    
		    
		}
		
	}


}

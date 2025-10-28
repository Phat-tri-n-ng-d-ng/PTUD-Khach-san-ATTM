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
import java.time.ZoneId;
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
import enums.ChucVuNhanVien;
import enums.PhuongThucThanhToan;
import enums.TrangThaiHoaDon;
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
	public String doiDonVi(double tien) {
		return String.format("%,.0f VND",tien);
	}
	public void getTatCaHoaDon(){
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
	public void timHoaDonTheoKhoang(LocalDateTime ngayBD, LocalDateTime ngayKT) {
	    try {
	        ArrayList<HoaDon> dsHoaDon = hoaDonService.timHoaDonTheoKhoang(ngayBD, ngayKT);
	        hoaDonPanel.model_DSHD.setRowCount(0);

	        if (dsHoaDon.isEmpty()) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don trong khoang ngay nay!");
	            return;
	        }

	        for (HoaDon hd : dsHoaDon) {
	        	 hoaDonPanel.model_DSHD.addRow(new Object[]{
	                hd.getMaHD(),
	                hd.getNgayLap(),
	                hd.getKhachHang().getTenKH(),
	                hd.getKhachHang().getSdt(),
	                doiDonVi(hd.getTongTien())
	            });
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	//TIM HOA DOn trong ngay
	public void timHoaDonTheoNgay(LocalDateTime ngay) {
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
	                doiDonVi(hd.getTongTien())
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
	        hoaDonPanel.model_DSHD.setRowCount(0);

	        if (hd == null) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don co ma: " + maHD);
	            return;
	        }
	       
	        hoaDonPanel.model_DSHD.addRow(new Object[]{
	            hd.getMaHD(),
	            hd.getNgayLap(),
	            hd.getKhachHang().getTenKH(),
	            hd.getKhachHang().getSdt(),
	            doiDonVi(hd.getTongTien())
	        });

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don theo ma: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	//TIm hoa don theo SDT
	public void timHoaDonTheoSDT(String SDT) {
	    try {
	        ArrayList<HoaDon> dshd = hoaDonService.timHoaDonTheoSDT(SDT);
	   
	        hoaDonPanel.model_DSHD.setRowCount(0);
	        if (dshd == null) {
	            JOptionPane.showMessageDialog(hoaDonPanel, "Khong tim thay hoa don co so dien thoai: " + SDT);
	            return;
	        }
	        for(HoaDon hd : dshd) {
	        	hoaDonPanel.model_DSHD.addRow(new Object[]{
	    	            hd.getMaHD(),
	    	            hd.getNgayLap(),
	    	            hd.getKhachHang().getTenKH(),
	    	            hd.getKhachHang().getSdt(),
	    	            doiDonVi(hd.getTongTien())
	    	        });
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(hoaDonPanel, "Loi khi tim hoa don theo so dien thoai: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
//	
	
//	private  void LamMoi(){
//        ArrayList<HoaDon> dsHoaDon =  new ArrayList<>();
//        hienThiDanhSachHoaDon(dsHoaDon);
//        
//        
//        
//       
//    }
	public void locHoaDonTheoTrangThai() {
	    String trangThai = hoaDonPanel.cbb_TrangThaiHoaDon.getSelectedItem().toString();
	    ArrayList<HoaDon> dsHoaDon;
	    if (trangThai.equals("Tất cả")) {
	    	dsHoaDon = hoaDonService.getDanhSachHoaDon();
		}else {
			TrangThaiHoaDon trangThaiHD = getTrangThai(trangThai);
			dsHoaDon = hoaDonService.getHoaDonTheoTrangThai(trangThaiHD.toString());
		}
	    hoaDonPanel.model_DSHD.setRowCount(0);
	    for(HoaDon hd : dsHoaDon) {
	    	hoaDonPanel.model_DSHD.addRow(new Object[] {
	    			hd.getMaHD(),
		            hd.getNgayLap(),
		            hd.getKhachHang().getTenKH(),
		            hd.getKhachHang().getSdt(),
		            doiDonVi(hd.getTongTien())
	    	});
	    	
	    }
	     
	}
	private TrangThaiHoaDon getTrangThai(String trangThai) {
        switch (trangThai) {
            case "Hóa đơn đặt phòng" -> { return TrangThaiHoaDon.HoaDonDatPhong ; }
            case "Hóa đơn hoàn thành" -> { return TrangThaiHoaDon.HoaDonHoanThanh; }
            case "Hóa đơn tạm" -> { return TrangThaiHoaDon.HoaDonTam; }
            case "Hóa đơn thuê phòng" -> { return TrangThaiHoaDon.HoaDonThuePhong; }
            case "Tất cả" -> { return TrangThaiHoaDon.TatCa;}
            default -> {return TrangThaiHoaDon.HoaDonDatPhong;}
        }
    }

	public void hienThiDanhSachHoaDon(ArrayList<HoaDon> dsHoaDons) {
		hoaDonPanel.model_DSHD.setRowCount(0);
		for(HoaDon hd : dsHoaDons) {
			hoaDonPanel.model_DSHD.addRow(new Object[] {
					   hd.getMaHD(),
		               hd.getNgayLap(),
		               hd.getKhachHang().getTenKH(),
		               hd.getKhachHang().getSdt(),
		               doiDonVi(hd.getTongTien())
		               
			});
			
		}
	}

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
		                    	hoaDonPanel.txt_KhuyenMai.setText(String.valueOf(hd.getKhuyenMai() != null ? hd.getKhuyenMai().getTyLeGiam() : 0 ));
		                    	hoaDonPanel.txt_TienNhan.setText(String.valueOf(hd.getTienNhan()));
		                    	hoaDonPanel.txt_TienTra.setText(String.valueOf(hd.getTienTra()));
		                    	hoaDonPanel.txt_NhanVienThucHien.setText(hd.getNhanVien().getTenNV());
		                    }
	                    	
	                    
	                    }
	                    hoaDonPanel.model2.setRowCount(0);
	                    for(ChiTietHoaDon cthd : dsCTHD) {
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
			LocalDateTime ngayChon = layNgayTuDateChooser(hoaDonPanel.ChonNgay);
			LocalDateTime ngayBD = layNgayTuDateChooser(hoaDonPanel.NgayBD);
			LocalDateTime ngayKT = layNgayTuDateChooser(hoaDonPanel.ngayKT);
			String trangThai = hoaDonPanel.cbb_TrangThaiHoaDon.getSelectedItem().toString();
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
			}else if (hoaDonPanel.rdbtn_ChonNgay.isSelected()) {
				timHoaDonTheoNgay(ngayChon);
				
			}else if (hoaDonPanel.rdbtn_ChonKhoangTG.isSelected()) {
				timHoaDonTheoKhoang(ngayBD, ngayKT);
			}else if (hoaDonPanel.rdbtn_TrangThai.isSelected()) {
				locHoaDonTheoTrangThai();
				
			}else {
				JOptionPane.showMessageDialog(hoaDonPanel, "Vui long chon dieu kien tim kiem!");
				return;
			}		    
		}
		
	
	}
	private LocalDateTime layNgayTuDateChooser(JDateChooser ngayChon) {
        if (ngayChon!= null && ngayChon.getDate() != null) {
            java.util.Date ngay = ngayChon.getDate();
            return ngay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }


}

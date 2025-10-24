package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JCheckBox;

public class ThueDatPhong extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txt_TimSoDienThoai;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public ThueDatPhong() {

//		setUndecorated(true);
		setBounds(100, 100, 1336, 768);
		setBackground(new Color(236, 247, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lbl_TieuDe = new JLabel("Khách Hàng");
		lbl_TieuDe.setForeground(new Color(10, 100, 189));
		lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbl_TieuDe.setBounds(725, 10, 133, 29);
		add(lbl_TieuDe);
		
		JPanel pnlSoDienThoai = new JPanel();
		pnlSoDienThoai.setBackground(new Color(255, 255, 255));
		pnlSoDienThoai.setBorder(new LineBorder(new Color(0, 0, 0)));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		int doDaiThongTinNhanVien = screenSize.width - 40;
		int viChiDauThongTinNhanVien = (screenSize.width - doDaiThongTinNhanVien) / 2;
		pnlSoDienThoai.setBounds(20, 64,1496 , 73);
		add(pnlSoDienThoai);
		pnlSoDienThoai.setLayout(null);
		
		JLabel lpl_TimSoDienThoai = new JLabel("Tìm số điện thoại khách hàng:");
		lpl_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lpl_TimSoDienThoai.setBounds(10, 10, 170, 20);
		pnlSoDienThoai.add(lpl_TimSoDienThoai);
		
		txt_TimSoDienThoai = new JTextField();
		txt_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		int doDaiTxt = doDaiThongTinNhanVien/2 - 20;
		txt_TimSoDienThoai.setBounds(10, 33, 325, 30);
		pnlSoDienThoai.add(txt_TimSoDienThoai);
		txt_TimSoDienThoai.setColumns(10);
		
		JLabel lbl_NgayBatDau = new JLabel("Ngày bắt đầu:");
		lbl_NgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_NgayBatDau.setBounds(455, 10, 104, 20);
		pnlSoDienThoai.add(lbl_NgayBatDau);
		
		JLabel lbl_KhuyenMai = new JLabel("Khuyến mãi:");
		lbl_KhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_KhuyenMai.setBounds(731, 9, 104, 20);
		pnlSoDienThoai.add(lbl_KhuyenMai);
		
		JLabel lbl_NgayKetThuc = new JLabel("Ngày kết thúc:");
		lbl_NgayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_NgayKetThuc.setBounds(587, 10, 104, 20);
		pnlSoDienThoai.add(lbl_NgayKetThuc);
		
		JButton btn_Tim = new JButton("Tìm");
		btn_Tim.setBounds(345, 34, 100, 30);
		pnlSoDienThoai.add(btn_Tim);
		
		JComboBox cbb_KhuyenMai = new JComboBox();
		cbb_KhuyenMai.setBounds(731, 33, 249, 30);
		pnlSoDienThoai.add(cbb_KhuyenMai);
		
		JLabel lbl_TrangThaiPhong = new JLabel("Trạng thái phòng:");
		lbl_TrangThaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TrangThaiPhong.setBounds(986, 10, 116, 20);
		pnlSoDienThoai.add(lbl_TrangThaiPhong);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Phòng trống ");
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNewCheckBox.setBounds(986, 33, 110, 30);
		pnlSoDienThoai.add(chckbxNewCheckBox);
		
		JCheckBox chckbxPhngangThu = new JCheckBox("Phòng đang thuê");
		chckbxPhngangThu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxPhngangThu.setBounds(1106, 33, 130, 30);
		pnlSoDienThoai.add(chckbxPhngangThu);
		
		JCheckBox chckbxPhngangThu_1 = new JCheckBox("Phòng đang thuê");
		chckbxPhngangThu_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxPhngangThu_1.setBounds(1246, 33, 130, 30);
		pnlSoDienThoai.add(chckbxPhngangThu_1);
		
		JButton btn_Loc = new JButton("Lọc");
		btn_Loc.setBounds(1386, 33, 100, 30);
		pnlSoDienThoai.add(btn_Loc);
		
		JLabel lbl_BoLoc = new JLabel("Bộ lọc");
		lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_BoLoc.setBounds(20, 34, 58, 20);
		add(lbl_BoLoc);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(836, 177, 676, 640);
		add(panel);
		panel.setLayout(null);
		
		model = new DefaultTableModel(new String[] {"Mã phòng","Loại phòng","SLTĐ","Giá"}, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 40, 636, 296);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
        header.setBackground(new Color(10, 100, 189)); 
        header.setForeground(Color.WHITE);           
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        panel.add(scrollPane);
		
		JLabel lbl_ThongTin_1 = new JLabel("Danh sách phòng đã chọn:");
		lbl_ThongTin_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ThongTin_1.setBounds(10, 10, 232, 20);
		panel.add(lbl_ThongTin_1);
		
		JLabel lbl_ThongTin_1_1 = new JLabel("Thông tin khách hàng:");
		lbl_ThongTin_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ThongTin_1_1.setBounds(10, 383, 210, 20);
		panel.add(lbl_ThongTin_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(20, 415, 636, 135);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lpl_SoDienThoai = new JLabel("Số điện thoại:");
		lpl_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lpl_SoDienThoai.setBounds(10, 10, 130, 20);
		panel_1.add(lpl_SoDienThoai);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(10, 33, 298, 30);
		panel_1.add(textField);
		
		JLabel lpl_TenKhachHang = new JLabel("Tên khách hàng:");
		lpl_TenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lpl_TenKhachHang.setBounds(318, 10, 130, 20);
		panel_1.add(lpl_TenKhachHang);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(318, 33, 298, 30);
		panel_1.add(textField_1);
		
		JLabel lpl_GioiTinh = new JLabel("Giới tính:");
		lpl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lpl_GioiTinh.setBounds(10, 69, 104, 30);
		panel_1.add(lpl_GioiTinh);
		
		JRadioButton rdbtn_Nam = new JRadioButton("Nam");
		rdbtn_Nam.setSelected(true);
		rdbtn_Nam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_Nam.setBackground(Color.WHITE);
		rdbtn_Nam.setBounds(10, 96, 65, 21);
		panel_1.add(rdbtn_Nam);
		
		JRadioButton rdbtn_Nu = new JRadioButton("Nữ");
		rdbtn_Nu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_Nu.setBackground(Color.WHITE);
		rdbtn_Nu.setBounds(75, 97, 47, 21);
		panel_1.add(rdbtn_Nu);
		
		JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
		lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_NgaySinh.setBounds(124, 69, 104, 30);
		panel_1.add(lbl_NgaySinh);
		
		JLabel lpl_Email = new JLabel("Email:");
		lpl_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lpl_Email.setBounds(318, 73, 130, 20);
		panel_1.add(lpl_Email);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(318, 96, 298, 30);
		panel_1.add(textField_2);
		
		JButton btn_ThuePhong = new JButton("Thuê phòng");
		btn_ThuePhong.setBounds(30, 560, 200, 30);
		panel.add(btn_ThuePhong);
		
		JButton btn_DatPhong = new JButton("Đặt phòng");
		btn_DatPhong.setBounds(240, 560, 200, 30);
		panel.add(btn_DatPhong);
		
		JButton btn_NhanPhong = new JButton("Nhận phòng");
		btn_NhanPhong.setBounds(450, 560, 200, 30);
		panel.add(btn_NhanPhong);
		
		JButton btn_TraPhong = new JButton("Trả phòng");
		btn_TraPhong.setBounds(30, 600, 200, 30);
		panel.add(btn_TraPhong);
		
		JButton btn_DoiPhong = new JButton("Đổi phòng");
		btn_DoiPhong.setBounds(240, 600, 200, 30);
		panel.add(btn_DoiPhong);
		
		JButton btn_HuyPhong = new JButton("Hủy phòng");
		btn_HuyPhong.setBounds(450, 600, 200, 30);
		panel.add(btn_HuyPhong);
		
		JButton btn_BoChon = new JButton("Bỏ chọn");
		btn_BoChon.setBounds(456, 346, 200, 30);
		panel.add(btn_BoChon);
		
		JLabel lbl_ThongTin = new JLabel("Thông tin:");
		lbl_ThongTin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ThongTin.setBounds(836, 147, 84, 20);
		add(lbl_ThongTin);
		
		JPanel pnlDanhSachPhong = new JPanel();
		pnlDanhSachPhong.setBackground(new Color(255, 255, 255));
		pnlDanhSachPhong.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDanhSachPhong.setBounds(20, 177, 806, 640);
		pnlDanhSachPhong.setLayout(null);
		add(pnlDanhSachPhong);
		
		JLabel lbl_DanhSachPhong = new JLabel("Danh Sách Phòng");
		lbl_DanhSachPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_DanhSachPhong.setBounds(20, 147, 180, 20);
		add(lbl_DanhSachPhong);
		
		JPanel panelPhong = new JPanel();
        panelPhong.setLayout(new GridLayout(0, 3, 20, 20)); // 3 cột, tự động xuống hàng
        
        // Thêm các ô giả lập
        for (int i = 1; i <= 20; i++) {
    		JPanel phong = new JPanel();
            phong.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            phong.setBackground(i % 3 == 0 ? Color.PINK : Color.GREEN);
            phong.setLayout(null);
            JLabel label = new JLabel("Phòng: P" + (3000 + i));
            label.setBounds(6, 12, 0, 0);
            phong.add(label);
            phong.setPreferredSize(new Dimension(200, 100));
            panelPhong.add(phong);
            
            JLabel lbl_Phong = new JLabel("Phòng:");
            lbl_Phong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            lbl_Phong.setBounds(6, 10, 184, 20);
            phong.add(lbl_Phong);
            
            JLabel lbl_LoaiPhong = new JLabel("Loại phòng:");
            lbl_LoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            lbl_LoaiPhong.setBounds(6, 36, 184, 16);
            phong.add(lbl_LoaiPhong);
            
            JLabel lbl_SoLuongToiDa = new JLabel("Số lượng tối đa:");
            lbl_SoLuongToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            lbl_SoLuongToiDa.setBounds(6, 57, 184, 16);
            phong.add(lbl_SoLuongToiDa);
            
            JLabel lbl_Gia = new JLabel("Giá:");
            lbl_Gia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            lbl_Gia.setBounds(6, 78, 149, 16);
            phong.add(lbl_Gia);
            
            JButton btn_ThemPhong = new JButton("+");
            btn_ThemPhong.setBounds(187, 65, 50, 30);
            phong.add(btn_ThemPhong);
        }

        // Đưa panelPhong vào JScrollPane
        JScrollPane scrollPane_Phong = new JScrollPane(panelPhong);
        scrollPane_Phong.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Phong.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane_Phong.setBounds(10, 50, 786, 580);
        pnlDanhSachPhong.add(scrollPane_Phong);
        
        JButton btn_TatCa = new JButton("Tắt cả");
        btn_TatCa.setBounds(78, 10, 100, 30);
        pnlDanhSachPhong.add(btn_TatCa);
        
        JButton btn_Standard = new JButton("Standard");
        btn_Standard.setBounds(188, 10, 100, 30);
        pnlDanhSachPhong.add(btn_Standard);
        
        JButton btn_Superior = new JButton("Superior");
        btn_Superior.setBounds(298, 10, 100, 30);
        pnlDanhSachPhong.add(btn_Superior);
        
        JButton btn_Deluxe = new JButton("Deluxe");
        btn_Deluxe.setBounds(408, 10, 100, 30);
        pnlDanhSachPhong.add(btn_Deluxe);
        
        JButton btn_Suite = new JButton("Suite");
        btn_Suite.setBounds(518, 10, 100, 30);
        pnlDanhSachPhong.add(btn_Suite);
        
        JButton btn_FamilyRoom = new JButton("  Family room");
        btn_FamilyRoom.setBounds(628, 10, 120, 30);
        pnlDanhSachPhong.add(btn_FamilyRoom);
	}
}

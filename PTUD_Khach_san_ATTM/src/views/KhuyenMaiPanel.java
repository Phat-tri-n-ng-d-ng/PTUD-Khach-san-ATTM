package views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

//import com.toedter.calendar.JDateChooser;


public class KhuyenMaiPanel extends JPanel {
	private JTextField txt_TenKhachHang;
	private JTextField txt_TyLeGiam;
	private JTextField txt_TimTenNhanVien;
	private JTable table;
	private DefaultTableModel model;

	public KhuyenMaiPanel() {

		setBounds(100, 100, 1336, 768);
		setBackground(new Color(236, 247, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lbl_KhuyenMai = new JLabel("Khuyến mãi");
		lbl_KhuyenMai.setForeground(new Color(10, 100, 189));
		lbl_KhuyenMai.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbl_KhuyenMai.setBounds(725, 10, 133, 29);
		add(lbl_KhuyenMai);
		
		JPanel pnlThongTinKhuyenMai = new JPanel();
		pnlThongTinKhuyenMai.setBackground(new Color(255, 255, 255));
		pnlThongTinKhuyenMai.setBorder(new LineBorder(new Color(0, 0, 0)));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		int doDaiThongTinNhanVien = screenSize.width - 40;
		int viChiDauThongTinNhanVien = (screenSize.width - doDaiThongTinNhanVien) / 2;
		pnlThongTinKhuyenMai.setBounds(20, 84,1496 , 175);
		add(pnlThongTinKhuyenMai);
		pnlThongTinKhuyenMai.setLayout(null);
		
		JLabel lpl_TenKhuyenMai = new JLabel("Tên khuyến mãi:");
		lpl_TenKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lpl_TenKhuyenMai.setBounds(10, 10, 104, 20);
		pnlThongTinKhuyenMai.add(lpl_TenKhuyenMai);
		
		txt_TenKhachHang = new JTextField();
		txt_TenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		int doDaiTxt = doDaiThongTinNhanVien/2 - 20;
		txt_TenKhachHang.setBounds(10, 33, doDaiTxt, 30);
		pnlThongTinKhuyenMai.add(txt_TenKhachHang);
		txt_TenKhachHang.setColumns(10);
		
		txt_TyLeGiam = new JTextField();
		txt_TyLeGiam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_TyLeGiam.setColumns(10);
		txt_TyLeGiam.setBounds(doDaiTxt + 30, 33, doDaiTxt, 30);
		pnlThongTinKhuyenMai.add(txt_TyLeGiam);
		
		JLabel lbl_TyLeGiam = new JLabel("Tỷ lệ giảm:");
		lbl_TyLeGiam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TyLeGiam.setBounds(758, 11, 104, 19);
		pnlThongTinKhuyenMai.add(lbl_TyLeGiam);
		
		JLabel lbl_NgayBatDau = new JLabel("Ngày bắt đầu:");
		lbl_NgayBatDau.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_NgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_NgayBatDau.setBounds(10, 73, 104, 20);
		pnlThongTinKhuyenMai.add(lbl_NgayBatDau);
		
		JLabel lbl_TrangThai = new JLabel("Trạng Thái:");
		lbl_TrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TrangThai.setBounds(758, 74, 78, 13);
		pnlThongTinKhuyenMai.add(lbl_TrangThai);
		
		JButton btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Them.setBounds(1142, 134, 120, 30);
		pnlThongTinKhuyenMai.add(btn_Them);
		
		JButton btn_CapNhat = new JButton("Cập nhật");
		btn_CapNhat.setBounds(1012, 134, 120, 30);
		pnlThongTinKhuyenMai.add(btn_CapNhat);
		
		JComboBox comboBox_TrangThai = new JComboBox();
		comboBox_TrangThai.setBounds(758, 94, 728, 30);
		pnlThongTinKhuyenMai.add(comboBox_TrangThai);
		
		JLabel lbl_NgayKetThuc = new JLabel("Ngày kết thúc:");
		lbl_NgayKetThuc.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_NgayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_NgayKetThuc.setBounds(186, 73, 104, 20);
		pnlThongTinKhuyenMai.add(lbl_NgayKetThuc);
		JDateChooser NgayBD = new JDateChooser();
		NgayBD.setDateFormatString("dd/MM/yyyy");

		NgayBD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		NgayBD.setBounds(10, 97, 162, 30);
		pnlThongTinKhuyenMai.add(NgayBD);

		JDateChooser ngayKT = new JDateChooser();
		ngayKT.setDateFormatString("dd/MM/yyyy");
		ngayKT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ngayKT.setBounds(186, 97, 162, 30);
		pnlThongTinKhuyenMai.add(ngayKT);
		
		JLabel lbl_DieuKienApDung = new JLabel("Điều kiện áp dụng:");
		lbl_DieuKienApDung.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_DieuKienApDung.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_DieuKienApDung.setBounds(387, 74, 132, 20);
		pnlThongTinKhuyenMai.add(lbl_DieuKienApDung);
		
		JCheckBox chckbx_Standard = new JCheckBox("Standard");
		chckbx_Standard.setBounds(397, 97, 87, 26);
		pnlThongTinKhuyenMai.add(chckbx_Standard);
		chckbx_Standard.setBackground(Color.white);
		
		JCheckBox chckbx_Superior = new JCheckBox("Superior");
		chckbx_Superior.setBounds(508, 97, 87, 26);
		pnlThongTinKhuyenMai.add(chckbx_Superior);
		chckbx_Superior.setBackground(Color.white);
		
		JCheckBox chckbx_Family = new JCheckBox("Family Room");
		chckbx_Family.setBounds(618, 97, 120, 26);
		pnlThongTinKhuyenMai.add(chckbx_Family);
		chckbx_Family.setBackground(Color.white);
		
		JCheckBox chckbx_Deluxe = new JCheckBox("Deluxe");
		chckbx_Deluxe.setBounds(397, 130, 87, 26);
		pnlThongTinKhuyenMai.add(chckbx_Deluxe);
		chckbx_Deluxe.setBackground(Color.white);
		
		JCheckBox chckbx_Suite = new JCheckBox("Suite");
		chckbx_Suite.setBounds(508, 130, 87, 26);
		pnlThongTinKhuyenMai.add(chckbx_Suite);
		chckbx_Suite.setBackground(Color.white);
		
		JCheckBox chckbx_TatCa = new JCheckBox("Tất cả");
		chckbx_TatCa.setBounds(618, 130, 87, 26);
		pnlThongTinKhuyenMai.add(chckbx_TatCa);
		chckbx_TatCa.setBackground(Color.white);
		
		
		JPanel pnlBoLoc = new JPanel();
		pnlBoLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlBoLoc.setBackground(new Color(255, 255, 255));
		pnlBoLoc.setBounds(20, 299, 1496, 73);
		add(pnlBoLoc);
		pnlBoLoc.setLayout(null);
		
		JLabel lbl_TimMaKhuyenMai = new JLabel("Tìm mã khuyến mãi:");
		lbl_TimMaKhuyenMai.setBounds(662, 10, 142, 20);
		lbl_TimMaKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlBoLoc.add(lbl_TimMaKhuyenMai);
		
		txt_TimTenNhanVien = new JTextField();
		txt_TimTenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_TimTenNhanVien.setColumns(10);
		txt_TimTenNhanVien.setBounds(662, 33, 307, 30);
		pnlBoLoc.add(txt_TimTenNhanVien);
		
		JButton btn_Tim = new JButton("Tìm");
		btn_Tim.setBounds(1382, 32, 100, 30);
		pnlBoLoc.add(btn_Tim);
		
		JLabel lbl_ApDUngTrongKhoang = new JLabel("Áp dụng trong khoảng:");
		lbl_ApDUngTrongKhoang.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ApDUngTrongKhoang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_ApDUngTrongKhoang.setBounds(1001, 10, 162, 20);
		pnlBoLoc.add(lbl_ApDUngTrongKhoang);
		
		JDateChooser NgayBD_1 = new JDateChooser();
		NgayBD_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		NgayBD_1.setDateFormatString("dd/MM/yyyy");
		NgayBD_1.setBounds(1001, 33, 162, 30);
		pnlBoLoc.add(NgayBD_1);
		
		JDateChooser ngayKT_1 = new JDateChooser();
		ngayKT_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ngayKT_1.setDateFormatString("dd/MM/yyyy");
		ngayKT_1.setBounds(1177, 33, 162, 30);
		pnlBoLoc.add(ngayKT_1);
		
		JLabel lbl_DieuKienApDung_1 = new JLabel("Điều kiện áp dụng:");
		lbl_DieuKienApDung_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_DieuKienApDung_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_DieuKienApDung_1.setBounds(14, 10, 132, 20);
		pnlBoLoc.add(lbl_DieuKienApDung_1);
		
		JCheckBox chckbx_Standard_1 = new JCheckBox("Standard");
		chckbx_Standard_1.setBounds(14, 33, 87, 26);
		pnlBoLoc.add(chckbx_Standard_1);
		chckbx_Standard_1.setBackground(Color.white);
		
		JCheckBox chckbx_Superior_1 = new JCheckBox("Superior");
		chckbx_Superior_1.setBounds(130, 33, 87, 26);
		pnlBoLoc.add(chckbx_Superior_1);
		chckbx_Superior_1.setBackground(Color.white);
		
		JCheckBox chckbx_Family_1 = new JCheckBox("Family Room");
		chckbx_Family_1.setBounds(238, 33, 120, 26);
		pnlBoLoc.add(chckbx_Family_1);
		chckbx_Family_1.setBackground(Color.white);
		
		JCheckBox chckbx_Deluxe_1 = new JCheckBox("Deluxe");
		chckbx_Deluxe_1.setBounds(375, 33, 87, 26);
		pnlBoLoc.add(chckbx_Deluxe_1);
		chckbx_Deluxe_1.setBackground(Color.white);
		
		JCheckBox chckbx_Suite_1 = new JCheckBox("Suite");
		chckbx_Suite_1.setBounds(480, 33, 87, 26);
		pnlBoLoc.add(chckbx_Suite_1);
		chckbx_Suite_1.setBackground(Color.white);
		
		JCheckBox chckbx_TatCa_1 = new JCheckBox("Tất cả");
		chckbx_TatCa_1.setBounds(565, 33, 87, 26);
		pnlBoLoc.add(chckbx_TatCa_1);
		chckbx_TatCa_1.setBackground(Color.white);
		
		JLabel lbl_ThongTinKhuyenMai = new JLabel("Thông tin khuyến mãi ");
		lbl_ThongTinKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ThongTinKhuyenMai.setBounds(20, 54, 197, 27);
		add(lbl_ThongTinKhuyenMai);
		
		JLabel lbl_BoLoc = new JLabel("Bộ lọc");
		lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_BoLoc.setBounds(20, 269, 58, 20);
		add(lbl_BoLoc);
		
		JLabel lbl_DanhSachKhuyenMai = new JLabel("Danh sách khuyến mãi");
		lbl_DanhSachKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_DanhSachKhuyenMai.setBounds(20, 382, 219, 29);
		add(lbl_DanhSachKhuyenMai);
		
		JPanel pnlDanhSachNhanVien = new JPanel();
		pnlDanhSachNhanVien.setBackground(new Color(255, 255, 255));
		pnlDanhSachNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDanhSachNhanVien.setBounds(20, 413, 1496, 366);
		add(pnlDanhSachNhanVien);
		
		model = new DefaultTableModel(new String[] {"Mã khuyến mãi","Tên khuyến mãi","Tỷ lệ giảm","Điều kiện áp dụng","Ngày bắt đầu","Ngày kết thúc","Trạng thái"}, 0);
		pnlDanhSachNhanVien.setLayout(null);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 15, 1476, 336);
		JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
        header.setBackground(new Color(10, 100, 189)); 
        header.setForeground(Color.WHITE);           
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlDanhSachNhanVien.add(scrollPane);
	}
}


package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextArea;
import java.awt.SystemColor;

public class HoaDonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txt_MaHoaDon;
	private JTextField txt_SoDienThoaiKhachHang;
	private JTable table;
	private JComboBox cbb_TrangThaiHoaDon;
	private DefaultTableModel model;
	private JTextField txtChonNgay;
	private JTextField txt_NgayBD;
	private JTextField txt_NgayKT;
	private JTable table_1;
	private JTable table1;
	private DefaultTableModel model1;
	private JTable table_2;
	private DefaultTableModel model2;
	private JTextField txt_TongTien;
	private JTextField txt_Thue;
	private JTextField txt_KhuyenMai;
	private JTextField txt_PhiDoiPhong;
	private JTextField txt_TongTienThanhToan;
	private JTextField txt_TienNhan;
	private JTextField txt_TienTra;
	private JTextField txt_NhanVienThucHien;


	public HoaDonPanel() {
		setBounds(100, 100, 1336, 768);
		setBackground(new Color(236, 247, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lbl_TieuDe = new JLabel("Hóa Đơn");
		lbl_TieuDe.setForeground(new Color(10, 100, 189));
		lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbl_TieuDe.setBounds(725, 10, 114, 24);
		add(lbl_TieuDe);

		JPanel pnlBoLocHoaDon = new JPanel();
		pnlBoLocHoaDon.setBackground(new Color(255, 255, 255));
		pnlBoLocHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		int doDaiThongTinNhanVien = screenSize.width - 40;
		int viChiDauThongTinNhanVien = (screenSize.width - doDaiThongTinNhanVien) / 2;
		pnlBoLocHoaDon.setBounds(20, 79,1496 , 110);
		add(pnlBoLocHoaDon);
		pnlBoLocHoaDon.setLayout(null);

		txt_MaHoaDon = new JTextField();
		txt_MaHoaDon.setText("Tìm mã hóa đơn");
		txt_MaHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		int doDaiTxt = doDaiThongTinNhanVien/2 - 20;
		txt_MaHoaDon.setBounds(36, 10, 702, 30);
		pnlBoLocHoaDon.add(txt_MaHoaDon);
		txt_MaHoaDon.setColumns(10);

		txt_SoDienThoaiKhachHang = new JTextField();
		txt_SoDienThoaiKhachHang.setText("Tìm số điện thoại khách hàng\r\n");
		txt_SoDienThoaiKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_SoDienThoaiKhachHang.setColumns(10);
		txt_SoDienThoaiKhachHang.setBounds(784, 10, 576, 30);
		pnlBoLocHoaDon.add(txt_SoDienThoaiKhachHang);

		JLabel lbl_TrangThai = new JLabel("Trạng Thái:");
		lbl_TrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TrangThai.setBounds(784, 70, 85, 22);
		pnlBoLocHoaDon.add(lbl_TrangThai);

		cbb_TrangThaiHoaDon = new JComboBox();
		cbb_TrangThaiHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cbb_TrangThaiHoaDon.setModel(new DefaultComboBoxModel(new String[] {"Hóa đơn tạm", "Hóa đơn cuối cùng"}));
		cbb_TrangThaiHoaDon.setBounds(862, 66, 498, 30);
		pnlBoLocHoaDon.add(cbb_TrangThaiHoaDon);

		JRadioButton rdbtn_TimMaHoaDon = new JRadioButton("");
		rdbtn_TimMaHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_TimMaHoaDon.setBackground(Color.WHITE);
		rdbtn_TimMaHoaDon.setBounds(9, 10, 21, 21);
		pnlBoLocHoaDon.add(rdbtn_TimMaHoaDon);

		JRadioButton rdbtn_SoDTKH = new JRadioButton("");
		rdbtn_SoDTKH.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_SoDTKH.setBackground(Color.WHITE);
		rdbtn_SoDTKH.setBounds(758, 10, 21, 21);
		pnlBoLocHoaDon.add(rdbtn_SoDTKH);

		JButton btn_TimHoaDon_1 = new JButton("Tìm");
		btn_TimHoaDon_1.setBounds(1370, 10, 120, 30);
		pnlBoLocHoaDon.add(btn_TimHoaDon_1);

		txtChonNgay = new JTextField();
		txtChonNgay.setText("Chọn");
		txtChonNgay.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtChonNgay.setColumns(10);
		txtChonNgay.setBounds(114, 66, 172, 30);
		pnlBoLocHoaDon.add(txtChonNgay);

		JRadioButton rdbtn_ChonNgay = new JRadioButton("  Chọn ngày: ");
		rdbtn_ChonNgay.setBounds(9, 70, 120, 21);
		pnlBoLocHoaDon.add(rdbtn_ChonNgay);
		rdbtn_ChonNgay.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_ChonNgay.setBackground(Color.WHITE);

		JRadioButton rdbtn_ChonKhoangTG = new JRadioButton("  Hóa đơn trong khoảng: ");
		rdbtn_ChonKhoangTG.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_ChonKhoangTG.setBackground(Color.WHITE);
		rdbtn_ChonKhoangTG.setBounds(321, 70, 181, 21);
		pnlBoLocHoaDon.add(rdbtn_ChonKhoangTG);

		JDateChooser NgayBD = new JDateChooser();
		NgayBD.setDateFormatString("dd/MM/yyyy");

		NgayBD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		NgayBD.setBounds(508, 66, 102, 30);
		pnlBoLocHoaDon.add(NgayBD);

		JDateChooser ngayKT = new JDateChooser();
		ngayKT.setDateFormatString("dd/MM/yyyy");
		ngayKT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ngayKT.setBounds(636, 66, 102, 30);
		pnlBoLocHoaDon.add(ngayKT);

		JLabel lbl_Boloc = new JLabel("Bộ lọc");
		lbl_Boloc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_Boloc.setBounds(20, 49, 62, 20);
		add(lbl_Boloc);

		JLabel lbl_DanhSachHoaDon = new JLabel("Danh Sách Hóa Đơn");
		lbl_DanhSachHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_DanhSachHoaDon.setBounds(20, 199, 180, 20);
		add(lbl_DanhSachHoaDon);

		JPanel pnlDanhSachHoaDon = new JPanel();
		pnlDanhSachHoaDon.setBackground(new Color(255, 255, 255));
		pnlDanhSachHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDanhSachHoaDon.setBounds(20, 234, 780, 547);
		add(pnlDanhSachHoaDon);

		model = new DefaultTableModel(new String[] {"Mã hóa đơn","Ngày lập","Tên khách hàng","Số điện thoại KH", "Tổng tiền"}, 0);
		pnlDanhSachHoaDon.setLayout(null);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 760, 526);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 18));
		header.setBackground(new Color(10, 100, 189));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(header.getWidth(), 35));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlDanhSachHoaDon.add(scrollPane);

		JLabel lbl_ThongTinHoaDon = new JLabel("Thông tin hóa đơn");
		lbl_ThongTinHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ThongTinHoaDon.setBounds(838, 199, 180, 20);
		add(lbl_ThongTinHoaDon);

		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setLayout(null);
		pnlThongTinHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlThongTinHoaDon.setBackground(Color.WHITE);
		pnlThongTinHoaDon.setBounds(838, 234, 678, 329);
		add(pnlThongTinHoaDon);

		model1= new DefaultTableModel(new String[] {"Tên KH","Giới tính","Ngày sinh","Số điện thoại", "Số CCCD"}, 0);
		pnlDanhSachHoaDon.setLayout(null);
		table1 = new JTable(model1);
		JScrollPane scrollPane_1 = new JScrollPane(table1);
		scrollPane_1.setBounds(10, 28, 658, 114);

		header = table1.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 18));
		header.setBackground(new Color(10, 100, 189));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(header.getWidth(), 35));
		table1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlThongTinHoaDon.add(scrollPane_1);





		scrollPane_1.setColumnHeaderView(table_1);

		JLabel lbl_PhuongThuc = new JLabel("Phương thức:");
		lbl_PhuongThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_PhuongThuc.setBounds(286, 144, 85, 22);
		pnlThongTinHoaDon.add(lbl_PhuongThuc);

		JRadioButton rdbtn_TienMat = new JRadioButton("Tiền mặt ");
		rdbtn_TienMat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_TienMat.setBackground(Color.WHITE);
		rdbtn_TienMat.setBounds(377, 146, 93, 21);
		pnlThongTinHoaDon.add(rdbtn_TienMat);

		JRadioButton rdbtn_ChuyenKhoan = new JRadioButton("Chuyển khoản");
		rdbtn_ChuyenKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		rdbtn_ChuyenKhoan.setBackground(Color.WHITE);
		rdbtn_ChuyenKhoan.setBounds(472, 145, 122, 21);
		pnlThongTinHoaDon.add(rdbtn_ChuyenKhoan);

		JLabel lbl_Thue = new JLabel("Thuế(10%):");
		lbl_Thue.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Thue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_Thue.setBounds(377, 193, 85, 22);
		pnlThongTinHoaDon.add(lbl_Thue);

		JLabel lbl_TongTien = new JLabel("Tổng tiền:");
		lbl_TongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TongTien.setBounds(377, 168, 85, 22);
		txt_TongTien = new JTextField();
		txt_TongTien.setEditable(false);
		txt_TongTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_TongTien.setColumns(10);
		txt_TongTien.setBounds(517, 168, 151, 22);
		pnlThongTinHoaDon.add(lbl_TongTien);

		JLabel lbl_KhuyenMai = new JLabel("Khuyến mãi:");
		lbl_KhuyenMai.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_KhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_KhuyenMai.setBounds(377, 218, 85, 22);
		pnlThongTinHoaDon.add(lbl_KhuyenMai);

		JLabel lbl_PhiDoiPhong = new JLabel("Phí đổi phòng:");
		lbl_PhiDoiPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_PhiDoiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_PhiDoiPhong.setBounds(345, 245, 117, 22);
		pnlThongTinHoaDon.add(lbl_PhiDoiPhong);

		JLabel lbl_TongTienThanhToan = new JLabel("Tổng tiền thanh toán:");
		lbl_TongTienThanhToan.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongTienThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TongTienThanhToan.setBounds(331, 270, 131, 22);
		pnlThongTinHoaDon.add(lbl_TongTienThanhToan);

		JLabel lbl_TienNhan = new JLabel("Tiền nhận từ khách:");
		lbl_TienNhan.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TienNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TienNhan.setBounds(331, 295, 131, 22);
		pnlThongTinHoaDon.add(lbl_TienNhan);

		JLabel lbl_TienTra = new JLabel("Tiền trả lại khách:");
		lbl_TienTra.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TienTra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_TienTra.setBounds(335, 320, 131, 22);
		pnlThongTinHoaDon.add(lbl_TienTra);

		JLabel lbl_NhanVienThucHien = new JLabel("Nhân viên thực hiện:");
		lbl_NhanVienThucHien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_NhanVienThucHien.setBounds(10, 279, 131, 22);
		pnlThongTinHoaDon.add(lbl_NhanVienThucHien);

		JLabel lbl_KhachHang = new JLabel("Khách hàng");
		lbl_KhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_KhachHang.setBounds(10, 2, 160, 24);
		pnlThongTinHoaDon.add(lbl_KhachHang);


		pnlThongTinHoaDon.add(txt_TongTien);

		txt_Thue = new JTextField();
		txt_Thue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_Thue.setEditable(false);
		txt_Thue.setColumns(10);
		txt_Thue.setBounds(517, 193, 151, 22);
		pnlThongTinHoaDon.add(txt_Thue);

		txt_KhuyenMai = new JTextField();
		txt_KhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_KhuyenMai.setEditable(false);
		txt_KhuyenMai.setColumns(10);
		txt_KhuyenMai.setBounds(517, 218, 151, 22);
		pnlThongTinHoaDon.add(txt_KhuyenMai);

		txt_PhiDoiPhong = new JTextField();
		txt_PhiDoiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_PhiDoiPhong.setEditable(false);
		txt_PhiDoiPhong.setColumns(10);
		txt_PhiDoiPhong.setBounds(517, 245, 151, 22);
		pnlThongTinHoaDon.add(txt_PhiDoiPhong);

		txt_TongTienThanhToan = new JTextField();
		txt_TongTienThanhToan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_TongTienThanhToan.setEditable(false);
		txt_TongTienThanhToan.setColumns(10);
		txt_TongTienThanhToan.setBounds(517, 270, 151, 22);
		pnlThongTinHoaDon.add(txt_TongTienThanhToan);

		txt_TienNhan = new JTextField();
		txt_TienNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_TienNhan.setEditable(false);
		txt_TienNhan.setColumns(10);
		txt_TienNhan.setBounds(517, 295, 151, 22);
		pnlThongTinHoaDon.add(txt_TienNhan);

		txt_TienTra = new JTextField();
		txt_TienTra.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_TienTra.setEditable(false);
		txt_TienTra.setColumns(10);
		txt_TienTra.setBounds(517, 323, 151, 22);
		pnlThongTinHoaDon.add(txt_TienTra);

		txt_NhanVienThucHien = new JTextField();
		txt_NhanVienThucHien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_NhanVienThucHien.setEditable(false);
		txt_NhanVienThucHien.setColumns(10);
		txt_NhanVienThucHien.setBounds(151, 279, 151, 22);
		pnlThongTinHoaDon.add(txt_NhanVienThucHien);

		JPanel pnlChiTietHoaDon = new JPanel();
		pnlChiTietHoaDon.setLayout(null);
		pnlChiTietHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlChiTietHoaDon.setBackground(Color.WHITE);
		pnlChiTietHoaDon.setBounds(838, 591, 678, 190);
		add(pnlChiTietHoaDon);




		JScrollPane scrollPane_1_1 = new JScrollPane((Component) null);
		model2= new DefaultTableModel(new String[] {"Mã phòng","Số ngày ở","Đơn giá","Thành Tiền"}, 0);
		pnlChiTietHoaDon.setLayout(null);
		table_2 = new JTable(model2);
		scrollPane_1_1.setBounds(10, 10, 658, 177);


		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane_1_1.setViewportView(table_2);

		JLabel lbl_ChiTietHoaDon = new JLabel("Chi tiết hóa đơn");
		lbl_ChiTietHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ChiTietHoaDon.setBounds(838, 571, 180, 20);

		header = table_2.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 18));
		header.setBackground(new Color(10, 100, 189));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(header.getWidth(), 35));
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlChiTietHoaDon.add(scrollPane_1_1);
		add(lbl_ChiTietHoaDon);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 32, 32);
		add(panel);
	}
}


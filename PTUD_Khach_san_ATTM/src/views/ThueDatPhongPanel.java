package views;

import com.toedter.calendar.JDateChooser;
import controller.NhanVienController;
import controller.ThueDatPhongController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class ThueDatPhongPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    public final JButton btn_LamMoi;
    public final JButton btn_ThuePhong;
    public final JButton btn_DatPhong;
    public final JButton btn_NhanPhong;
    public final JButton btn_DoiPhong;
    public final JButton btn_HuyPhong;
    public JCheckBox chckbx_phongTrong;
    public JCheckBox chckbx_phongThue;
    public JCheckBox chckbx_phongDat;
    public JTextField txt_TenKhachHang;
    public JTextField txt_Email;
    public JButton btn_BoChon;
    public JButton btn_Tim;
    private  ThueDatPhongController thueDatPhongController;
    public JPanel danhSachPhongPanel;
    private JTextField txt_TimSoDienThoai;
	public JTable table;
	public DefaultTableModel model;
	public JTextField txt_SoDienThoai;
	private JTextField textField_1;
	private JTextField txtSoDienThoai;
    public JRadioButton rdbtn_Nam;
    public JRadioButton rdbtn_Nu;
    public JTextField txt_ngaySinhKhachHang;
    public JButton btn_Loc;

	public ThueDatPhongPanel() {
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
        pnlSoDienThoai.setBounds(20, 82,1496 , 73);
        add(pnlSoDienThoai);
        pnlSoDienThoai.setLayout(null);

        JLabel lpl_TimSoDienThoai = new JLabel("Tìm số điện thoại khách hàng:");
        lpl_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_TimSoDienThoai.setBounds(10, 10, 188, 20);
        pnlSoDienThoai.add(lpl_TimSoDienThoai);

        txt_TimSoDienThoai = new JTextField();
        txt_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        int doDaiTxt = doDaiThongTinNhanVien/2 - 20;
        txt_TimSoDienThoai.setBounds(10, 33, 325, 30);
        pnlSoDienThoai.add(txt_TimSoDienThoai);
        txt_TimSoDienThoai.setColumns(10);

        btn_Tim = new JButton("Tìm");
        btn_Tim.setBounds(345, 34, 100, 30);
        pnlSoDienThoai.add(btn_Tim);

        JLabel lbl_NgayBatDau = new JLabel("Ngày bắt đầu:");
        lbl_NgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgayBatDau.setBounds(453, 10, 104, 20);
        pnlSoDienThoai.add(lbl_NgayBatDau);

        JLabel lbl_NgayKetThuc = new JLabel("Ngày kết thúc:");
        lbl_NgayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgayKetThuc.setBounds(566, 10, 104, 20);
        pnlSoDienThoai.add(lbl_NgayKetThuc);

        JButton btn_Loc_1 = new JButton("Áp dụng");
        btn_Loc_1.setBounds(680, 33, 100, 30);
        pnlSoDienThoai.add(btn_Loc_1);

        JPanel panel = new JPanel();
        panel.setBounds(453, 33, 104, 30);
        pnlSoDienThoai.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(566, 33, 104, 30);
        pnlSoDienThoai.add(panel_1);

        JComboBox cbb_KhuyenMai = new JComboBox();
        cbb_KhuyenMai.setBounds(788, 33, 192, 30);
        pnlSoDienThoai.add(cbb_KhuyenMai);

        JLabel lbl_TrangThaiPhong = new JLabel("Trạng thái phòng:");
        lbl_TrangThaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TrangThaiPhong.setBounds(986, 10, 116, 20);
        pnlSoDienThoai.add(lbl_TrangThaiPhong);

        JLabel lbl_KhuyenMai = new JLabel("Khuyến mãi:");
        lbl_KhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_KhuyenMai.setBounds(787, 10, 130, 21);
        pnlSoDienThoai.add(lbl_KhuyenMai);

        chckbx_phongTrong = new JCheckBox("Phòng trống ");
        chckbx_phongTrong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chckbx_phongTrong.setBounds(986, 33, 110, 30);
        pnlSoDienThoai.add(chckbx_phongTrong);

        chckbx_phongThue = new JCheckBox("Phòng đang thuê");
        chckbx_phongThue.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chckbx_phongThue.setBounds(1106, 33, 130, 30);
        pnlSoDienThoai.add(chckbx_phongThue);

        chckbx_phongDat = new JCheckBox("Phòng đã đặt");
        chckbx_phongDat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        chckbx_phongDat.setBounds(1246, 33, 130, 30);
        pnlSoDienThoai.add(chckbx_phongDat);

        btn_Loc = new JButton("Lọc");
        btn_Loc.setBounds(1386, 33, 100, 30);
        pnlSoDienThoai.add(btn_Loc);

        JLabel lbl_BoLoc = new JLabel("Bộ lọc");
        lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_BoLoc.setBounds(20, 52, 58, 20);
        add(lbl_BoLoc);

        JPanel pnl_ThongTin = new JPanel();
        pnl_ThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnl_ThongTin.setBackground(new Color(255, 255, 255));
        pnl_ThongTin.setBounds(836, 195, 676, 607);
        add(pnl_ThongTin);
        pnl_ThongTin.setLayout(null);

        model = new DefaultTableModel(new String[] {"Mã phòng","Loại phòng","SLTĐ","Giá"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 40, 636, 256);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        pnl_ThongTin.add(scrollPane);

        JLabel lbl_DanhSachPhongDaChon = new JLabel("Danh sách phòng đã chọn:");
        lbl_DanhSachPhongDaChon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachPhongDaChon.setBounds(10, 10, 232, 20);
        pnl_ThongTin.add(lbl_DanhSachPhongDaChon);

        JLabel lbl_ThongTinKhachHang= new JLabel("Thông tin khách hàng:");
        lbl_ThongTinKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTinKhachHang.setBounds(20, 346, 210, 20);
        pnl_ThongTin.add(lbl_ThongTinKhachHang);

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(20, 382, 636, 135);
        pnl_ThongTin.add(pnlThongTin);
        pnlThongTin.setLayout(null);

        JLabel lpl_SoDienThoai = new JLabel("Số điện thoại:");
        lpl_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_SoDienThoai.setBounds(10, 10, 130, 20);
        pnlThongTin.add(lpl_SoDienThoai);

        txt_SoDienThoai = new JTextField();
        txt_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_SoDienThoai.setColumns(10);
        txt_SoDienThoai.setBounds(10, 33, 298, 30);
        pnlThongTin.add(txt_SoDienThoai);

        JLabel lpl_TenKhachHang = new JLabel("Tên khách hàng:");
        lpl_TenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_TenKhachHang.setBounds(318, 10, 130, 20);
        pnlThongTin.add(lpl_TenKhachHang);

        txt_TenKhachHang = new JTextField();
        txt_TenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TenKhachHang.setColumns(10);
        txt_TenKhachHang.setBounds(318, 33, 298, 30);
        txt_TenKhachHang.setEditable(false);
        pnlThongTin.add(txt_TenKhachHang);

        JLabel lpl_GioiTinh = new JLabel("Giới tính:");
        lpl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_GioiTinh.setBounds(10, 69, 104, 30);
        pnlThongTin.add(lpl_GioiTinh);

        rdbtn_Nam = new JRadioButton("Nam");
        rdbtn_Nam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nam.setBackground(Color.WHITE);
        rdbtn_Nam.setBounds(10, 96, 65, 21);
        rdbtn_Nam.setEnabled(false);
        pnlThongTin.add(rdbtn_Nam);

        rdbtn_Nu = new JRadioButton("Nữ");
        rdbtn_Nu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nu.setBackground(Color.WHITE);
        rdbtn_Nu.setBounds(75, 97, 47, 21);
        rdbtn_Nu.setEnabled(false);
        pnlThongTin.add(rdbtn_Nu);

        JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
        lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgaySinh.setBounds(124, 69, 104, 30);
        pnlThongTin.add(lbl_NgaySinh);

        JLabel lpl_Email = new JLabel("Email:");
        lpl_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_Email.setBounds(318, 73, 130, 20);
        pnlThongTin.add(lpl_Email);

        txt_Email = new JTextField();
        txt_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_Email.setColumns(10);
        txt_Email.setBounds(318, 96, 298, 30);
        txt_Email.setEditable(false);
        pnlThongTin.add(txt_Email);

        txt_ngaySinhKhachHang = new JTextField();
        txt_ngaySinhKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_ngaySinhKhachHang.setBounds(124, 96, 184, 30);
        txt_ngaySinhKhachHang.setEditable(false);
        pnlThongTin.add(txt_ngaySinhKhachHang);

        btn_ThuePhong = new JButton("Thuê phòng");
        btn_ThuePhong.setBounds(30, 527, 200, 30);
        pnl_ThongTin.add(btn_ThuePhong);

        btn_DatPhong = new JButton("Đặt phòng");
        btn_DatPhong.setBounds(240, 527, 200, 30);
        pnl_ThongTin.add(btn_DatPhong);

        btn_NhanPhong = new JButton("Nhận phòng");
        btn_NhanPhong.setBounds(450, 527, 200, 30);
        pnl_ThongTin.add(btn_NhanPhong);

        JButton btn_TraPhong = new JButton("Trả phòng");
        btn_TraPhong.setBounds(30, 567, 200, 30);
        pnl_ThongTin.add(btn_TraPhong);

        btn_DoiPhong = new JButton("Đổi phòng");
        btn_DoiPhong.setBounds(240, 567, 200, 30);
        pnl_ThongTin.add(btn_DoiPhong);

        btn_HuyPhong = new JButton("Hủy phòng");
        btn_HuyPhong.setBounds(450, 567, 200, 30);
        pnl_ThongTin.add(btn_HuyPhong);

        btn_BoChon = new JButton("Bỏ chọn");
        btn_BoChon.setBounds(456, 306, 200, 30);
        pnl_ThongTin.add(btn_BoChon);

        btn_LamMoi = new JButton("Làm mới ");
        btn_LamMoi.setBounds(240, 306, 200, 30);
        pnl_ThongTin.add(btn_LamMoi);

        JLabel lbl_ThongTin = new JLabel("Thông tin:");
        lbl_ThongTin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTin.setBounds(836, 165, 84, 20);
        add(lbl_ThongTin);

        JPanel pnlDanhSachPhong = new JPanel();
        pnlDanhSachPhong.setBackground(new Color(255, 255, 255));
        pnlDanhSachPhong.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlDanhSachPhong.setBounds(20, 195, 806, 607);
        add(pnlDanhSachPhong);

        JLabel lbl_DanhSachPhong = new JLabel("Danh Sách Phòng");
        lbl_DanhSachPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachPhong.setBounds(20, 165, 180, 20);
        add(lbl_DanhSachPhong);
        pnlDanhSachPhong.setLayout(null);

        danhSachPhongPanel = new JPanel();
        danhSachPhongPanel.setLayout(new GridLayout(0, 3, 20, 20)); // 3 cột, tự động xuống hàng

        // Đưa panelPhong vào JScrollPane
        JScrollPane scrollPane_Phong = new JScrollPane(danhSachPhongPanel);
        scrollPane_Phong.setBounds(10, 50, 786, 547);
        scrollPane_Phong.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Phong.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

        thueDatPhongController = new ThueDatPhongController(this);
        thueDatPhongController.getTatCaPhong();
	}
}

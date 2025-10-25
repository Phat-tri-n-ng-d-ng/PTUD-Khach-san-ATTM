package views;

import controller.PhongController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class PhongPanel extends JPanel {

	private static final long serialVersionUID = 1L;
    public final JCheckBox chckbx_Standard,chckbx_Superior,chckbx_FamilyRoom,chckbx_Deluxe,chckbx_Suite;
    public JTextField txt_Tang;
    public JTextField txt_SoLuongToiDa;
    public JTextField txt_SoPhong;
    public JTextField txt_TimMaPhong;
    public JTable table;
    public JComboBox cbb_LoaiPhong;
	public DefaultTableModel model;
    public JButton btn_ThemPhong,btn_CapNhat,btn_Tim;

	public PhongPanel() {
		setBounds(100, 100, 1336, 768);
		setBackground(new Color(236, 247, 255));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lbl_TieuDe = new JLabel("Phòng");
		lbl_TieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe.setForeground(new Color(10, 100, 189));
		lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbl_TieuDe.setBounds(725, 10, 114, 25);
		add(lbl_TieuDe);

		JPanel pnlThongTinPhong = new JPanel();
		pnlThongTinPhong.setBackground(new Color(255, 255, 255));
		pnlThongTinPhong.setBorder(new LineBorder(new Color(0, 0, 0)));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		int doDaiThongTinPhong = screenSize.width - 40;
		pnlThongTinPhong.setBounds(20, 80,1496 , 149);
		add(pnlThongTinPhong);
		pnlThongTinPhong.setLayout(null);
		int doDaiTxt = doDaiThongTinPhong/2 - 20;

		txt_SoLuongToiDa = new JTextField();
		txt_SoLuongToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_SoLuongToiDa.setColumns(10);
		txt_SoLuongToiDa.setBounds(605, 32, 450, 30);
		pnlThongTinPhong.add(txt_SoLuongToiDa);

		JLabel lbl_SoLuongToiDa = new JLabel("Số lượng tối đa:");
		lbl_SoLuongToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_SoLuongToiDa.setBounds(605, 10, 104, 19);
		pnlThongTinPhong.add(lbl_SoLuongToiDa);

		JLabel lbl_SoPhong = new JLabel("Số phòng:");
		lbl_SoPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lbl_SoPhong.setBounds(10, 10, 79, 19);
		pnlThongTinPhong.add(lbl_SoPhong);

		txt_SoPhong = new JTextField();
		txt_SoPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_SoPhong.setColumns(10);
		txt_SoPhong.setBounds(10, 33, 450, 30);
		pnlThongTinPhong.add(txt_SoPhong);



		btn_ThemPhong = new JButton("Thêm");
		btn_ThemPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_ThemPhong.setBounds(1321, 93, 120, 30);
		pnlThongTinPhong.add(btn_ThemPhong);

		btn_CapNhat = new JButton("Cập nhật");
		btn_CapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_CapNhat.setBounds(1156, 93, 120, 30);
		pnlThongTinPhong.add(btn_CapNhat);

		JLabel lpl_LoaiPhong = new JLabel("Tầng:");
		lpl_LoaiPhong.setBounds(10, 74, 104, 20);
		pnlThongTinPhong.add(lpl_LoaiPhong);
		lpl_LoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		txt_Tang = new JTextField();
		txt_Tang.setBounds(10, 94, 450, 30);
		pnlThongTinPhong.add(txt_Tang);
		txt_Tang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_Tang.setColumns(10);

		cbb_LoaiPhong = new JComboBox();

		cbb_LoaiPhong.setBounds(605, 93, 450, 30);
		pnlThongTinPhong.add(cbb_LoaiPhong);
		cbb_LoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JLabel lpl_LoaiPhong_1 = new JLabel("Loại phòng:");
		lpl_LoaiPhong_1.setBounds(605, 73, 104, 20);
		pnlThongTinPhong.add(lpl_LoaiPhong_1);
		lpl_LoaiPhong_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JPanel pnlBoLoc = new JPanel();
		pnlBoLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlBoLoc.setBackground(new Color(255, 255, 255));
		pnlBoLoc.setBounds(20, 281, 1496, 82);
		add(pnlBoLoc);
		pnlBoLoc.setLayout(null);

		JLabel lbl_TimMaPhong = new JLabel("Tìm mã phòng:");
		lbl_TimMaPhong.setBounds(774, 12, 117, 20);
		lbl_TimMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlBoLoc.add(lbl_TimMaPhong);

		txt_TimMaPhong = new JTextField();
		txt_TimMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txt_TimMaPhong.setColumns(10);
		txt_TimMaPhong.setBounds(774, 35, 450, 30);
		pnlBoLoc.add(txt_TimMaPhong);

		btn_Tim = new JButton("Tìm");
		btn_Tim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Tim.setBounds(1321, 35, 120, 30);
		pnlBoLoc.add(btn_Tim);

		JLabel lpl_LoaiPhongTim = new JLabel("Loại phòng:");
		lpl_LoaiPhongTim.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lpl_LoaiPhongTim.setBounds(10, 11, 104, 20);
		pnlBoLoc.add(lpl_LoaiPhongTim);

		chckbx_Standard = new JCheckBox("Standard");
		chckbx_Standard.setBounds(10, 40, 100, 22);
		chckbx_Standard.setBackground(Color.white);
		pnlBoLoc.add(chckbx_Standard);

        chckbx_Superior = new JCheckBox("Superior");
		chckbx_Superior.setBounds(145, 40, 100, 22);
		chckbx_Superior.setBackground(Color.white);
		pnlBoLoc.add(chckbx_Superior);

		chckbx_FamilyRoom = new JCheckBox("Family Room");
		chckbx_FamilyRoom.setBounds(550, 40, 117, 22);
		chckbx_FamilyRoom.setBackground(Color.white);
		pnlBoLoc.add(chckbx_FamilyRoom);

		chckbx_Deluxe = new JCheckBox("Deluxe");
        chckbx_Deluxe.setBounds(280, 40, 100, 22);
        chckbx_Deluxe.setBackground(Color.white);
		pnlBoLoc.add(chckbx_Deluxe);

		chckbx_Suite = new JCheckBox("Suite");
		chckbx_Suite.setBounds(415, 40, 100, 22);
		chckbx_Suite.setBackground(Color.white);
		pnlBoLoc.add(chckbx_Suite);

		JLabel lbl_ThongTinPhong = new JLabel("Thông tin phòng");
		lbl_ThongTinPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_ThongTinPhong.setBounds(20, 50, 161, 20);
		add(lbl_ThongTinPhong);

		JLabel lbl_BoLoc = new JLabel("Bộ lọc");
		lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_BoLoc.setBounds(20, 251, 58, 20);
		add(lbl_BoLoc);

		JLabel lbl_DanhSachPhong = new JLabel("Danh sách phòng");
		lbl_DanhSachPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbl_DanhSachPhong.setBounds(20, 378, 180, 20);
		add(lbl_DanhSachPhong);

		JPanel pnlDanhSachNhanVien = new JPanel();
		pnlDanhSachNhanVien.setBackground(new Color(255, 255, 255));
		pnlDanhSachNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlDanhSachNhanVien.setBounds(20, 410, 1496, 367);
		add(pnlDanhSachNhanVien);

		model = new DefaultTableModel(new String[] {"Mã phòng","Loại phòng","Số lượng tối đa","Giá phòng","Tiền cọc","Trạng thái"}, 0);
		pnlDanhSachNhanVien.setLayout(null);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 1476, 346);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Times New Roman", Font.BOLD, 18));
		header.setBackground(new Color(10, 100, 189));
		header.setForeground(Color.WHITE);
		header.setPreferredSize(new Dimension(header.getWidth(), 35));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlDanhSachNhanVien.add(scrollPane);

        PhongController pc = new PhongController(this);
        pc.hienThiDanhSachPhong();
        pc.hienThiLoaiPhong();
	}
}

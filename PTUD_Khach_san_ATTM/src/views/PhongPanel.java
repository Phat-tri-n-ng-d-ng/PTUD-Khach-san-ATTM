package views;

import controller.PhongController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public final JCheckBox chckbx_Standard, chckbx_Superior, chckbx_FamilyRoom, chckbx_Deluxe, chckbx_Suite;
    public JTextField txt_Tang;
    public JTextField txt_SoLuongToiDa;
    public JTextField txt_SoPhong;
    public JTextField txt_TimMaPhong;
    public JTable table;
    public JComboBox cbb_LoaiPhong;
    public DefaultTableModel model;
    public JButton btn_ThemPhong, btn_CapNhat, btn_Tim, btn_LamMoi;

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
        pnlThongTinPhong.setBackground(Color.WHITE);
        pnlThongTinPhong.setBorder(new LineBorder(Color.BLACK));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        pnlThongTinPhong.setBounds(20, 80, 1496, 175);
        add(pnlThongTinPhong);
        pnlThongTinPhong.setLayout(null);

        txt_SoLuongToiDa = new JTextField();
        txt_SoLuongToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_SoLuongToiDa.setBounds(758, 33, 728, 30);
        pnlThongTinPhong.add(txt_SoLuongToiDa);

        JLabel lbl_SoLuongToiDa = new JLabel("Số lượng tối đa:");
        lbl_SoLuongToiDa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_SoLuongToiDa.setBounds(758, 10, 104, 19);
        pnlThongTinPhong.add(lbl_SoLuongToiDa);

        JLabel lbl_SoPhong = new JLabel("Số phòng:");
        lbl_SoPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_SoPhong.setBounds(10, 10, 79, 19);
        pnlThongTinPhong.add(lbl_SoPhong);

        txt_SoPhong = new JTextField();
        txt_SoPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_SoPhong.setBounds(10, 33, 728, 30);
        pnlThongTinPhong.add(txt_SoPhong);

        btn_ThemPhong = new JButton("Thêm");
        btn_ThemPhong.setBounds(1012, 134, 120, 30);
        pnlThongTinPhong.add(btn_ThemPhong);

        btn_CapNhat = new JButton("Cập nhật");
        btn_CapNhat.setBounds(1142, 134, 120, 30);
        pnlThongTinPhong.add(btn_CapNhat);

        btn_LamMoi = new JButton("Làm mới");
        btn_LamMoi.setBounds(1272, 134, 120, 30);
        pnlThongTinPhong.add(btn_LamMoi);

        JLabel lbl_Tang = new JLabel("Tầng:");
        lbl_Tang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_Tang.setBounds(10, 74, 104, 20);
        pnlThongTinPhong.add(lbl_Tang);

        txt_Tang = new JTextField();
        txt_Tang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_Tang.setBounds(10, 94, 728, 30);
        pnlThongTinPhong.add(txt_Tang);

        cbb_LoaiPhong = new JComboBox();
        cbb_LoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cbb_LoaiPhong.setModel(new DefaultComboBoxModel<>(new String[]{"Standard", "Superior", "Deluxe", "Suite", "Family Room"}));
        cbb_LoaiPhong.setBounds(758, 93, 728, 30);
        pnlThongTinPhong.add(cbb_LoaiPhong);

        JLabel lbl_LoaiPhong = new JLabel("Loại phòng:");
        lbl_LoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_LoaiPhong.setBounds(758, 73, 104, 20);
        pnlThongTinPhong.add(lbl_LoaiPhong);

        JPanel pnlBoLoc = new JPanel();
        pnlBoLoc.setBorder(new LineBorder(Color.BLACK));
        pnlBoLoc.setBackground(Color.WHITE);
        pnlBoLoc.setBounds(20, 291, 1496, 82);
        add(pnlBoLoc);
        pnlBoLoc.setLayout(null);

        JLabel lbl_TimMaPhong = new JLabel("Tìm mã phòng:");
        lbl_TimMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TimMaPhong.setBounds(758, 12, 117, 20);
        pnlBoLoc.add(lbl_TimMaPhong);

        txt_TimMaPhong = new JTextField();
        txt_TimMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimMaPhong.setBounds(758, 35, 600, 30);
        pnlBoLoc.add(txt_TimMaPhong);

        btn_Tim = new JButton("Tìm");
        btn_Tim.setBounds(1370, 35, 115, 30);
        pnlBoLoc.add(btn_Tim);


        JLabel lbl_LoaiPhongTim = new JLabel("Loại phòng:");
        lbl_LoaiPhongTim.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_LoaiPhongTim.setBounds(10, 11, 104, 20);
        pnlBoLoc.add(lbl_LoaiPhongTim);

        chckbx_Standard = new JCheckBox("Standard");
        chckbx_Standard.setBounds(10, 35, 100, 22);
        chckbx_Standard.setBackground(Color.WHITE);
        pnlBoLoc.add(chckbx_Standard);

        chckbx_Superior = new JCheckBox("Superior");
        chckbx_Superior.setBounds(125, 35, 100, 22);
        chckbx_Superior.setBackground(Color.WHITE);
        pnlBoLoc.add(chckbx_Superior);

        chckbx_Deluxe = new JCheckBox("Deluxe");
        chckbx_Deluxe.setBounds(240, 35, 100, 22);
        chckbx_Deluxe.setBackground(Color.WHITE);
        pnlBoLoc.add(chckbx_Deluxe);

        chckbx_Suite = new JCheckBox("Suite");
        chckbx_Suite.setBounds(355, 35, 100, 22);
        chckbx_Suite.setBackground(Color.WHITE);
        pnlBoLoc.add(chckbx_Suite);

        chckbx_FamilyRoom = new JCheckBox("Family Room");
        chckbx_FamilyRoom.setBounds(470, 35, 120, 22);
        chckbx_FamilyRoom.setBackground(Color.WHITE);
        pnlBoLoc.add(chckbx_FamilyRoom);

        JLabel lbl_ThongTinPhong = new JLabel("Thông tin phòng");
        lbl_ThongTinPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTinPhong.setBounds(20, 50, 161, 20);
        add(lbl_ThongTinPhong);

        JLabel lbl_BoLoc = new JLabel("Bộ lọc");
        lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_BoLoc.setBounds(20, 265, 58, 20);
        add(lbl_BoLoc);

        JLabel lbl_DanhSachPhong = new JLabel("Danh sách phòng");
        lbl_DanhSachPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachPhong.setBounds(20, 383, 180, 20);
        add(lbl_DanhSachPhong);

        JPanel pnlDanhSachPhong = new JPanel();
        pnlDanhSachPhong.setBackground(Color.WHITE);
        pnlDanhSachPhong.setBorder(new LineBorder(Color.BLACK));
        pnlDanhSachPhong.setBounds(20, 410, 1496, 367);
        add(pnlDanhSachPhong);
        pnlDanhSachPhong.setLayout(null);

        model = new DefaultTableModel(
                new String[] { "Mã phòng", "Loại phòng", "Số lượng tối đa", "Giá phòng", "Tiền cọc", "Trạng thái" }, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 1476, 346);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlDanhSachPhong.add(scrollPane);

        // Controller
        PhongController pc = new PhongController(this);
        pc.hienThiDanhSachPhong();
        pc.hienThiLoaiPhong();
    }
}
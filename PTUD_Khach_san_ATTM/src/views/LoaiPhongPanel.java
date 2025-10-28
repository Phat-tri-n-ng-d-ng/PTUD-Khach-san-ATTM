package views;

import controller.LoaiPhongController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class LoaiPhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public JTextField txt_TyLeCoc;
    public JTextField txt_GiaNiemYet;
    public JTextField txt_TenLoaiPhong;
    public JTextField txt_SoNguoiMacDinh; // Thêm mới
    public JTable table;
    public DefaultTableModel model;
    public JButton btn_Them, btn_CapNhat, btn_LamMoi; // thêm btn_LamMoi
    LoaiPhongController loaiPhongController;

    public LoaiPhongPanel() {
        // Cấu hình chung
        setBounds(100, 100, 1336, 768);
        setBackground(new Color(236, 247, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        // Tiêu đề
        JLabel lbl_TieuDe = new JLabel("Loại phòng");
        lbl_TieuDe.setForeground(new Color(10, 100, 189));
        lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lbl_TieuDe.setBounds(725, 10, 166, 25);
        add(lbl_TieuDe);

        // Panel thông tin loại phòng
        JPanel pnlThongTinLoaiPhong = new JPanel();
        pnlThongTinLoaiPhong.setBackground(Color.WHITE);
        pnlThongTinLoaiPhong.setBorder(new LineBorder(Color.BLACK));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        pnlThongTinLoaiPhong.setBounds(20, 87, 1496, 171);
        add(pnlThongTinLoaiPhong);
        pnlThongTinLoaiPhong.setLayout(null);

        // Label + textfield Tên loại phòng
        JLabel lbl_TenLoaiPhong = new JLabel("Tên loại phòng:");
        lbl_TenLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TenLoaiPhong.setBounds(10, 10, 150, 19);
        pnlThongTinLoaiPhong.add(lbl_TenLoaiPhong);

        txt_TenLoaiPhong = new JTextField();
        txt_TenLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TenLoaiPhong.setBounds(10, 33, 728, 30);
        pnlThongTinLoaiPhong.add(txt_TenLoaiPhong);

        // Label + textfield Giá niêm yết
        JLabel lbl_GiaNiemYet = new JLabel("Giá niêm yết:");
        lbl_GiaNiemYet.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_GiaNiemYet.setBounds(758, 10, 150, 19);
        pnlThongTinLoaiPhong.add(lbl_GiaNiemYet);

        txt_GiaNiemYet = new JTextField();
        txt_GiaNiemYet.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_GiaNiemYet.setBounds(758, 33, 728, 30);
        pnlThongTinLoaiPhong.add(txt_GiaNiemYet);

        // Label + textfield Tỷ lệ cọc
        JLabel lbl_TyLeCoc = new JLabel("Tỷ lệ cọc:");
        lbl_TyLeCoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TyLeCoc.setBounds(10, 72, 150, 20);
        pnlThongTinLoaiPhong.add(lbl_TyLeCoc);

        txt_TyLeCoc = new JTextField();
        txt_TyLeCoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TyLeCoc.setBounds(10, 94, 728, 30);
        pnlThongTinLoaiPhong.add(txt_TyLeCoc);

        // Label + textfield Số người mặc định
        JLabel lbl_SoNguoiMacDinh = new JLabel("Số người mặc định:");
        lbl_SoNguoiMacDinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_SoNguoiMacDinh.setBounds(758, 72, 150, 20);
        pnlThongTinLoaiPhong.add(lbl_SoNguoiMacDinh);

        txt_SoNguoiMacDinh = new JTextField();
        txt_SoNguoiMacDinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_SoNguoiMacDinh.setBounds(758, 94, 728, 30);
        pnlThongTinLoaiPhong.add(txt_SoNguoiMacDinh);

        // Các nút thao tác
        btn_Them = new JButton("Thêm");
        btn_Them.setBounds(1012, 134, 120, 30);
        pnlThongTinLoaiPhong.add(btn_Them);

        btn_CapNhat = new JButton("Cập nhật");
        btn_CapNhat.setBounds(1142, 134, 120, 30);
        pnlThongTinLoaiPhong.add(btn_CapNhat);

        btn_LamMoi = new JButton("Làm mới");
        btn_LamMoi.setBounds(1272, 134, 120, 30);
        pnlThongTinLoaiPhong.add(btn_LamMoi);

        // Nhãn tiêu đề khu vực
        JLabel lbl_ThongTinLoaiPhong = new JLabel("Thông tin loại phòng");
        lbl_ThongTinLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTinLoaiPhong.setBounds(20, 57, 197, 20);
        add(lbl_ThongTinLoaiPhong);

        JLabel lbl_DanhSachLoaiPhong = new JLabel("Danh sách loại phòng");
        lbl_DanhSachLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachLoaiPhong.setBounds(20, 268, 180, 20);
        add(lbl_DanhSachLoaiPhong);

        // Panel danh sách loại phòng (table)
        JPanel pnlDanhSachLoaiPhong = new JPanel();
        pnlDanhSachLoaiPhong.setBackground(Color.WHITE);
        pnlDanhSachLoaiPhong.setBorder(new LineBorder(Color.BLACK));
        pnlDanhSachLoaiPhong.setBounds(20, 298, 1496, 479);
        add(pnlDanhSachLoaiPhong);
        pnlDanhSachLoaiPhong.setLayout(null);

        model = new DefaultTableModel(
                new String[]{"Mã loại phòng", "Tên loại phòng", "Giá niêm yết", "Tỷ lệ cọc", "Số người mặc định"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 1476, 466);
        pnlDanhSachLoaiPhong.add(scrollPane);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        // Gắn controller
        loaiPhongController = new LoaiPhongController(this);
        loaiPhongController.getDanhDachLoaiPhong();
    }
}


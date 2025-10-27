package views;

import controller.KhachHangController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.toedter.calendar.JDateChooser;
import java.awt.*;

public class KhachHangPanel extends JPanel {
    public JRadioButton rdbtn_Nu;
    public JRadioButton rdbtn_Nam;
    public JDateChooser ngaySinh;
    public JTextField txt_soCCCD;
    private KhachHangController khachHangController;
    public JButton btn_Tim;
    public JButton btn_ThemKhachHang;
    public JButton btn_CapNhat;
    public JTextField txt_TenKhachHang;
    public JTextField txt_SoDienThoai;
    public JTextField txt_Email;
    public JTextField txt_TimSoCanCuocCongDan;
    public JTextField txt_TimSoDienThoai;
    public JTable table;
    public DefaultTableModel model;
    public JRadioButton rdbtn_TimSoCanCuocCongDan;
    public JRadioButton  rdbtn_TimSoDienThoai;
    public JComboBox cbb_LocHangKhachHang;
    public JButton btn_LamMoi;

    public KhachHangPanel() {
        jbInit();
    }
    private void jbInit() {
        setBounds(100, 100, 1336, 768);
        setBackground(new Color(236, 247, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lpl_TieuDe = new JLabel("Khách Hàng");
        lpl_TieuDe.setForeground(new Color(10, 100, 189));
        lpl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lpl_TieuDe.setBounds(725, 10, 133, 29);
        add(lpl_TieuDe);

        JPanel pnlThongTinKhachHang = new JPanel();
        pnlThongTinKhachHang.setBackground(new Color(255, 255, 255));
        pnlThongTinKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1536, 818);
        int doDaiThongTinNhanVien = screenSize.width - 40;
        int viChiDauThongTinNhanVien = (screenSize.width - doDaiThongTinNhanVien) / 2;

        pnlThongTinKhachHang.setBounds(20, 79,1496 , 190);
        add(pnlThongTinKhachHang);
        pnlThongTinKhachHang.setLayout(null);

        JLabel lpl_TenNhanVien = new JLabel("Tên khách hàng:");
        lpl_TenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_TenNhanVien.setBounds(10, 10, 104, 16);
        pnlThongTinKhachHang.add(lpl_TenNhanVien);

        txt_TenKhachHang = new JTextField();
        txt_TenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        int doDaiTxt = doDaiThongTinNhanVien/2 - 20;
        txt_TenKhachHang.setBounds(10, 33, doDaiTxt, 30);
        pnlThongTinKhachHang.add(txt_TenKhachHang);
        txt_TenKhachHang.setColumns(10);

        txt_SoDienThoai = new JTextField();
        txt_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_SoDienThoai.setColumns(10);
        txt_SoDienThoai.setBounds(doDaiTxt + 30, 33, doDaiTxt, 30);
        pnlThongTinKhachHang.add(txt_SoDienThoai);

        JLabel lbl_SoDienThoai = new JLabel("Số điện thoại:");
        lbl_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_SoDienThoai.setBounds(758, 11, 104, 16);
        pnlThongTinKhachHang.add(lbl_SoDienThoai);

        JLabel lpl_GioiTinh = new JLabel("Giới tính:");
        lpl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_GioiTinh.setBounds(10, 134, 104, 16);
        pnlThongTinKhachHang.add(lpl_GioiTinh);

        rdbtn_Nam = new JRadioButton("Nam");
        rdbtn_Nam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nam.setSelected(true);
        rdbtn_Nam.setBackground(new Color(255, 255, 255));
        rdbtn_Nam.setBounds(10, 153, 65, 21);
        pnlThongTinKhachHang.add(rdbtn_Nam);

        rdbtn_Nu = new JRadioButton("Nữ");
        rdbtn_Nu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nu.setBackground(Color.WHITE);
        rdbtn_Nu.setBounds(77, 153, 47, 21);
        pnlThongTinKhachHang.add(rdbtn_Nu);

        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(rdbtn_Nam);
        groupGioiTinh.add(rdbtn_Nu);

        JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
        lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgaySinh.setBounds(130, 134, 104, 16);
        pnlThongTinKhachHang.add(lbl_NgaySinh);

        ngaySinh = new JDateChooser();
        ngaySinh.setDateFormatString("dd/MM/yyyy");
        ngaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngaySinh.setBounds(130, 153, 173, 30);
        pnlThongTinKhachHang.add(ngaySinh);

        JLabel lbl_Email = new JLabel("Email:");
        lbl_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_Email.setBounds(758, 74, 47, 16);
        pnlThongTinKhachHang.add(lbl_Email);

        txt_Email = new JTextField();
        txt_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_Email.setColumns(10);
        txt_Email.setBounds(758, 94, 728, 30);
        pnlThongTinKhachHang.add(txt_Email);

        btn_ThemKhachHang = new JButton("Thêm KH");
        btn_ThemKhachHang.setBounds(961, 149, 120, 30);
        pnlThongTinKhachHang.add(btn_ThemKhachHang);

        btn_CapNhat = new JButton("Cập nhật");
        btn_CapNhat.setBounds(1091, 149, 120, 30);
        pnlThongTinKhachHang.add(btn_CapNhat);

        btn_LamMoi = new JButton("Làm mới");
        btn_LamMoi.setBounds(1221, 149, 120, 30);
        pnlThongTinKhachHang.add(btn_LamMoi);

        JLabel lbl_soCCCD = new JLabel("Số căn cước công dân:");
        lbl_soCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_soCCCD.setBounds(10, 74, 161, 16);
        pnlThongTinKhachHang.add(lbl_soCCCD);

        txt_soCCCD = new JTextField();
        txt_soCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_soCCCD.setColumns(10);
        txt_soCCCD.setBounds(10, 94, 728, 30);
        pnlThongTinKhachHang.add(txt_soCCCD);

        JPanel pnlBoLoc = new JPanel();
        pnlBoLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlBoLoc.setBackground(new Color(255, 255, 255));
        pnlBoLoc.setBounds(20, 309, 1496, 73);
        add(pnlBoLoc);
        pnlBoLoc.setLayout(null);

        rdbtn_TimSoCanCuocCongDan = new JRadioButton("Tìm số căn cước công dân:");
        rdbtn_TimSoCanCuocCongDan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_TimSoCanCuocCongDan.setBackground(Color.WHITE);
        rdbtn_TimSoCanCuocCongDan.setBounds(10, 9, 220, 16);
        rdbtn_TimSoCanCuocCongDan.setSelected(true);
        pnlBoLoc.add(rdbtn_TimSoCanCuocCongDan);

        rdbtn_TimSoDienThoai = new JRadioButton("Tìm số điện thoại:");
        rdbtn_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_TimSoDienThoai.setBackground(Color.WHITE);
        rdbtn_TimSoDienThoai.setBounds(646, 11, 159, 16);
        pnlBoLoc.add(rdbtn_TimSoDienThoai);

        ButtonGroup groupTimKiem = new ButtonGroup();
        groupTimKiem.add(rdbtn_TimSoCanCuocCongDan);
        groupTimKiem.add(rdbtn_TimSoDienThoai);

        txt_TimSoCanCuocCongDan = new JTextField();
        txt_TimSoCanCuocCongDan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimSoCanCuocCongDan.setColumns(10);
        txt_TimSoCanCuocCongDan.setBounds(10, 33, 616, 30);
        pnlBoLoc.add(txt_TimSoCanCuocCongDan);

        txt_TimSoDienThoai = new JTextField();
        txt_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimSoDienThoai.setColumns(10);
        txt_TimSoDienThoai.setBounds(646, 33, 616, 30);
        txt_TimSoDienThoai.setEditable(false);
        pnlBoLoc.add(txt_TimSoDienThoai);

        btn_Tim = new JButton("Tìm");
        btn_Tim.setBounds(1272, 34, 100, 30);
        pnlBoLoc.add(btn_Tim);

        JLabel lbl_HangKhachHang = new JLabel("Hạng khách hàng:");
        lbl_HangKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_HangKhachHang.setBounds(1382, 10, 114, 20);
        pnlBoLoc.add(lbl_HangKhachHang);

        cbb_LocHangKhachHang = new JComboBox();
        cbb_LocHangKhachHang.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Đồng", "Bạc", "Vàng", "Kim cương"}));
        cbb_LocHangKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cbb_LocHangKhachHang.setBounds(1382, 33, 104, 30);
        pnlBoLoc.add(cbb_LocHangKhachHang);

        JLabel lbl_ThongTinKhachHang = new JLabel("Thông Tin Khách Hàng");
        lbl_ThongTinKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTinKhachHang.setBounds(20, 49, 190, 20);
        add(lbl_ThongTinKhachHang);

        JLabel lbl_BoLoc = new JLabel("Bộ Lọc");
        lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_BoLoc.setBounds(20, 279, 70, 20);
        add(lbl_BoLoc);

        JLabel lbl_DanhSachKhachHang = new JLabel("Danh Sách Khách Hàng");
        lbl_DanhSachKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachKhachHang.setBounds(20, 392, 200, 21);
        add(lbl_DanhSachKhachHang);

        JPanel pnlDanhSachKhachHang = new JPanel();
        pnlDanhSachKhachHang.setBackground(new Color(255, 255, 255));
        pnlDanhSachKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlDanhSachKhachHang.setBounds(20, 422, 1496, 369);
        add(pnlDanhSachKhachHang);

        model = new DefaultTableModel(new String[] {"Mã khách hàng","Tên khách hàng","Giới tính","Ngày sinh","Số điện thoại","Email","Số CCCD","Hạng khách hàng","Điểm tích lũy"}, 0);
        pnlDanhSachKhachHang.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 1476, 349);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlDanhSachKhachHang.add(scrollPane);

        khachHangController = new KhachHangController(this);
        khachHangController.getTatCaKhachHang();
    }
}
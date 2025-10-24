package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JDateChooser;
import controller.NhanVienController;

public class NhanVienPanel extends JPanel{
    public JComboBox cbb_LocChucVu;
    public JRadioButton rdbtn_Nam;
    public JRadioButton rdbtn_Nu;
    private NhanVienController nhanVienController;
    public JButton btn_Tim;
    public JButton btn_CapTaiKhoan;
    public JButton btn_ThemNhanVien;
    public JButton btn_CapNhat;
    public JTextField txt_TenNhanVien;
    public JTextField txt_SoDienThoai;
    public JTextField txt_Email;
    public JTextField txt_TimTenNhanVien;
    public JTextField txt_TimSoDienThoai;
    public JTable table;
    public JComboBox cbb_ChuVu;
    public DefaultTableModel model;

    public NhanVienPanel() {
        setBounds(100, 100, 1336, 768);
        setBackground(new Color(236, 247, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lbl_TieuDe = new JLabel("Nhân Viên");
        lbl_TieuDe.setForeground(new Color(10, 100, 189));
        lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lbl_TieuDe.setBounds(725, 10, 114, 24);
        add(lbl_TieuDe);

        JLabel lbl_ThongTinNhanVien = new JLabel("Thông Tin Nhân Viên");
        lbl_ThongTinNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTinNhanVien.setBounds(20, 49, 180, 20);
        add(lbl_ThongTinNhanVien);

        JPanel pnlThongTinNhanVien = new JPanel();
        pnlThongTinNhanVien.setBackground(new Color(255, 255, 255));
        pnlThongTinNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // lấy kích thước màng hình hiện tại
        setSize(screenSize.width, screenSize.height);
        int doDaiThongTinNhanVien = screenSize.width - 40;
        int viChiDauThongTinNhanVien = (screenSize.width - doDaiThongTinNhanVien) / 2;
        pnlThongTinNhanVien.setBounds(20, 79,1496 , 175);
        add(pnlThongTinNhanVien);
        pnlThongTinNhanVien.setLayout(null);

        JLabel lpl_TenNhanVien = new JLabel("Tên nhân viên:");
        lpl_TenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_TenNhanVien.setBounds(10, 10, 104, 16);
        pnlThongTinNhanVien.add(lpl_TenNhanVien);

        txt_TenNhanVien = new JTextField();
        txt_TenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        int doDaiTxt = doDaiThongTinNhanVien/2 - 20;
        txt_TenNhanVien.setBounds(10, 33, doDaiTxt, 30);
        pnlThongTinNhanVien.add(txt_TenNhanVien);


        txt_SoDienThoai = new JTextField();
        txt_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_SoDienThoai.setBounds(doDaiTxt + 30, 33, doDaiTxt, 30);
        pnlThongTinNhanVien.add(txt_SoDienThoai);

        JLabel lbl_SoDienThoai = new JLabel("Số điện thoại:");
        lbl_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_SoDienThoai.setBounds(758, 11, 104, 16);
        pnlThongTinNhanVien.add(lbl_SoDienThoai);

        JLabel lpl_GioiTinh = new JLabel("Giới tính:");
        lpl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_GioiTinh.setBounds(10, 73, 104, 16);
        pnlThongTinNhanVien.add(lpl_GioiTinh);

        rdbtn_Nam = new JRadioButton("Nam");
        rdbtn_Nam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nam.setSelected(true);
        rdbtn_Nam.setBackground(new Color(255, 255, 255));
        rdbtn_Nam.setBounds(8, 103, 65, 21);
        pnlThongTinNhanVien.add(rdbtn_Nam);

        rdbtn_Nu = new JRadioButton("Nữ");
        rdbtn_Nu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nu.setBackground(Color.WHITE);
        rdbtn_Nu.setBounds(75, 103, 47, 21);
        pnlThongTinNhanVien.add(rdbtn_Nu);

        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(rdbtn_Nam);
        groupGioiTinh.add(rdbtn_Nu);

        JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
        lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgaySinh.setBounds(128, 73, 104, 16);
        pnlThongTinNhanVien.add(lbl_NgaySinh);

        JDateChooser ngaySinh = new JDateChooser();
        ngaySinh.setDateFormatString("dd/MM/yyyy");
        ngaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngaySinh.setBounds(128, 99, 173, 30);
        pnlThongTinNhanVien.add(ngaySinh);

        JLabel lbl_Email = new JLabel("Email:");
        lbl_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_Email.setBounds(758, 74, 47, 16);
        pnlThongTinNhanVien.add(lbl_Email);

        txt_Email = new JTextField();
        txt_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_Email.setBounds(758, 94, 728, 30);
        pnlThongTinNhanVien.add(txt_Email);

        btn_CapTaiKhoan = new JButton("Cấp tài khoản");
        btn_CapTaiKhoan.setBounds(961, 134, 120, 30);
        pnlThongTinNhanVien.add(btn_CapTaiKhoan);

        btn_ThemNhanVien = new JButton("Thêm nv");
        btn_ThemNhanVien.setBounds(1091, 134, 120, 30);
        pnlThongTinNhanVien.add(btn_ThemNhanVien);

        btn_CapNhat = new JButton("Cập nhật");
        btn_CapNhat.setBounds(1221, 134, 120, 30);
        pnlThongTinNhanVien.add(btn_CapNhat);

        JLabel lbl_ChucVu = new JLabel("Chức vụ:");
        lbl_ChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_ChucVu.setBounds(311, 73, 104, 16);
        pnlThongTinNhanVien.add(lbl_ChucVu);

        cbb_ChuVu = new JComboBox();
        cbb_ChuVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cbb_ChuVu.setModel(new DefaultComboBoxModel(new String[] {"Quản lý", "Lễ tân", "Kế toán", "Kỹ thuật", "Buồng phòng", "Bếp", "Bảo vệ"}));
        cbb_ChuVu.setBounds(311, 99, 104, 30);
        pnlThongTinNhanVien.add(cbb_ChuVu);

        JPanel pnlBoLoc = new JPanel();
        pnlBoLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlBoLoc.setBackground(new Color(255, 255, 255));
        pnlBoLoc.setBounds(20, 294, 1496, 73);
        add(pnlBoLoc);
        pnlBoLoc.setLayout(null);

        txt_TimTenNhanVien = new JTextField();
        txt_TimTenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimTenNhanVien.setBounds(10, 33, 616, 30);
        pnlBoLoc.add(txt_TimTenNhanVien);

        txt_TimSoDienThoai = new JTextField();
        txt_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimSoDienThoai.setBounds(646, 33, 616, 30);
        pnlBoLoc.add(txt_TimSoDienThoai);

        btn_Tim = new JButton("Tìm");
        btn_Tim.setBounds(1272, 34, 100, 30);
        pnlBoLoc.add(btn_Tim);

        JLabel lbl_ChucVu_1 = new JLabel("Chức vụ:");
        lbl_ChucVu_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_ChucVu_1.setBounds(1382, 10, 104, 20);
        pnlBoLoc.add(lbl_ChucVu_1);

        cbb_LocChucVu = new JComboBox();
        cbb_LocChucVu.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Quản lý", "Lễ tân", "Kế toán", "Kỹ thuật", "Buồng phòng", "Bếp", "Bảo vệ"}));
        cbb_LocChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cbb_LocChucVu.setBounds(1382, 33, 104, 30);
        pnlBoLoc.add(cbb_LocChucVu);

        JRadioButton rdbtn_TimMaNhanVien = new JRadioButton("Tìm mã nhân viên");
        rdbtn_TimMaNhanVien.setSelected(true);
        rdbtn_TimMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_TimMaNhanVien.setBackground(Color.WHITE);
        rdbtn_TimMaNhanVien.setBounds(10, 11, 137, 21);
        pnlBoLoc.add(rdbtn_TimMaNhanVien);

        JRadioButton rdbtn_TimSoDienThoai = new JRadioButton("Tìm số điện thoại");
        rdbtn_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_TimSoDienThoai.setBackground(Color.WHITE);
        rdbtn_TimSoDienThoai.setBounds(646, 6, 137, 21);
        pnlBoLoc.add(rdbtn_TimSoDienThoai);

        ButtonGroup groupTimKiem = new ButtonGroup();
        groupTimKiem.add(rdbtn_TimMaNhanVien);
        groupTimKiem.add(rdbtn_TimSoDienThoai);

        JLabel lbl_BoLoc = new JLabel("Bộ Lọc");
        lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_BoLoc.setBounds(20, 264, 70, 20);
        add(lbl_BoLoc);

        JLabel lbl_DanhSachNhanVien = new JLabel("Danh Sách Nhân Viên");
        lbl_DanhSachNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachNhanVien.setBounds(20, 377, 180, 20);
        add(lbl_DanhSachNhanVien);

        JPanel pnlDanhSachNhanVien = new JPanel();
        pnlDanhSachNhanVien.setBackground(new Color(255, 255, 255));
        pnlDanhSachNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlDanhSachNhanVien.setBounds(20, 407, 1496, 384);
        add(pnlDanhSachNhanVien);

        model = new DefaultTableModel(new String[] {"Mã nhân viên","Tên nhân viên","Giới tính","Ngày sinh","Số điện thoại","Email","Chức vụ"}, 0);
        pnlDanhSachNhanVien.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 1476, 364);
        JTableHeader header = table.getTableHeader(); // chỉnh sửa header
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlDanhSachNhanVien.add(scrollPane);

        nhanVienController = new NhanVienController(this);
        nhanVienController.getTatCaNhanVien();;
    }
}
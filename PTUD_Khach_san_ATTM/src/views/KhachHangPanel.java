package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class KhachHangPanel extends JPanel {
    public JButton btn_Tim;
    public JButton btn_ThemKhachHang;
    public JButton btn_CapNhat;
    public JTextField txt_TenKhachHang;
    public JTextField txt_SoDienThoai;
    public JTextField txt_Email;
    public JTextField txt_TimTenNhanVien;
    public JTextField txt_TimSoDienThoai;
    public JTable table;
    public DefaultTableModel model;

    public KhachHangPanel() {
        setBounds(100, 100, 1336, 768);
        setBackground(new Color(236, 247, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lbl_TieuDe = new JLabel("Khách Hàng");
        lbl_TieuDe.setForeground(new Color(10, 100, 189));
        lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lbl_TieuDe.setBounds(725, 10, 133, 29);
        add(lbl_TieuDe);

        JPanel pnlThongTinNhanVien = new JPanel();
        pnlThongTinNhanVien.setBackground(new Color(255, 255, 255));
        pnlThongTinNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        int doDaiThongTinNhanVien = screenSize.width - 40;
        int viChiDauThongTinNhanVien = (screenSize.width - doDaiThongTinNhanVien) / 2;
        pnlThongTinNhanVien.setBounds(20, 64,1496 , 175);
        add(pnlThongTinNhanVien);
        pnlThongTinNhanVien.setLayout(null);

        JLabel lpl_TenNhanVien = new JLabel("Tên khách hàng:");
        lpl_TenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_TenNhanVien.setBounds(10, 10, 104, 13);
        pnlThongTinNhanVien.add(lpl_TenNhanVien);

        txt_TenKhachHang = new JTextField();
        txt_TenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        int doDaiTxt = doDaiThongTinNhanVien/2 - 20;
        txt_TenKhachHang.setBounds(10, 33, doDaiTxt, 30);
        pnlThongTinNhanVien.add(txt_TenKhachHang);
        txt_TenKhachHang.setColumns(10);

        txt_SoDienThoai = new JTextField();
        txt_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_SoDienThoai.setColumns(10);
        txt_SoDienThoai.setBounds(doDaiTxt + 30, 33, doDaiTxt, 30);
        pnlThongTinNhanVien.add(txt_SoDienThoai);

        JLabel lbl_SoDienThoai = new JLabel("Số điện thoại:");
        lbl_SoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_SoDienThoai.setBounds(758, 11, 104, 13);
        pnlThongTinNhanVien.add(lbl_SoDienThoai);

        JLabel lpl_GioiTinh = new JLabel("Giới tính:");
        lpl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lpl_GioiTinh.setBounds(10, 73, 104, 13);
        pnlThongTinNhanVien.add(lpl_GioiTinh);

        JRadioButton rdbtn_Nam = new JRadioButton("Nam");
        rdbtn_Nam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nam.setSelected(true);
        rdbtn_Nam.setBackground(new Color(255, 255, 255));
        rdbtn_Nam.setBounds(10, 92, 65, 21);
        pnlThongTinNhanVien.add(rdbtn_Nam);

        JRadioButton rdbtn_Nu = new JRadioButton("Nữ");
        rdbtn_Nu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_Nu.setBackground(Color.WHITE);
        rdbtn_Nu.setBounds(75, 93, 47, 21);
        pnlThongTinNhanVien.add(rdbtn_Nu);

        JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
        lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgaySinh.setBounds(124, 73, 104, 13);
        pnlThongTinNhanVien.add(lbl_NgaySinh);

        JLabel lbl_Email = new JLabel("Email:");
        lbl_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_Email.setBounds(758, 74, 47, 13);
        pnlThongTinNhanVien.add(lbl_Email);

        txt_Email = new JTextField();
        txt_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_Email.setColumns(10);
        txt_Email.setBounds(758, 94, 728, 30);
        pnlThongTinNhanVien.add(txt_Email);

        btn_ThemKhachHang = new JButton("Thêm KH");
        btn_ThemKhachHang.setBounds(1012, 134, 120, 30);
        pnlThongTinNhanVien.add(btn_ThemKhachHang);

        btn_CapNhat = new JButton("Cập nhật");
        btn_CapNhat.setBounds(1142, 134, 120, 30);
        pnlThongTinNhanVien.add(btn_CapNhat);

        JPanel pnlBoLoc = new JPanel();
        pnlBoLoc.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlBoLoc.setBackground(new Color(255, 255, 255));
        pnlBoLoc.setBounds(20, 279, 1496, 73);
        add(pnlBoLoc);
        pnlBoLoc.setLayout(null);

        JLabel lbl_ = new JLabel("Tìm tên khách hàng:");
        lbl_.setBounds(10, 10, 126, 20);
        lbl_.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlBoLoc.add(lbl_);

        txt_TimTenNhanVien = new JTextField();
        txt_TimTenNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimTenNhanVien.setColumns(10);
        txt_TimTenNhanVien.setBounds(10, 33, 616, 30);
        pnlBoLoc.add(txt_TimTenNhanVien);

        JLabel lbl_TmSoDienThoai = new JLabel("Tìm số điện thoại:");
        lbl_TmSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TmSoDienThoai.setBounds(646, 10, 117, 20);
        pnlBoLoc.add(lbl_TmSoDienThoai);

        txt_TimSoDienThoai = new JTextField();
        txt_TimSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimSoDienThoai.setColumns(10);
        txt_TimSoDienThoai.setBounds(646, 33, 616, 30);
        pnlBoLoc.add(txt_TimSoDienThoai);

        btn_Tim = new JButton("Tìm");
        btn_Tim.setBounds(1272, 34, 100, 30);
        pnlBoLoc.add(btn_Tim);

        JLabel lbl_HangKhachHang = new JLabel("Hạng khách hàng:");
        lbl_HangKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_HangKhachHang.setBounds(1382, 10, 114, 20);
        pnlBoLoc.add(lbl_HangKhachHang);

        JComboBox cbb_LocChuVu = new JComboBox();
        cbb_LocChuVu.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Đồng", "Bạc", "Vàng", "Kim cương"}));
        cbb_LocChuVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cbb_LocChuVu.setBounds(1382, 33, 104, 30);
        pnlBoLoc.add(cbb_LocChuVu);

        JLabel lbl_ThongTinKhachHang = new JLabel("Thông tin khách hàng");
        lbl_ThongTinKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTinKhachHang.setBounds(20, 34, 180, 20);
        add(lbl_ThongTinKhachHang);

        JLabel lbl_BoLoc = new JLabel("Bộ lọc");
        lbl_BoLoc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_BoLoc.setBounds(20, 249, 58, 20);
        add(lbl_BoLoc);

        JLabel lbl_DanhSachNhanVien = new JLabel("Danh Sách Nhân Viên");
        lbl_DanhSachNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachNhanVien.setBounds(20, 362, 180, 20);
        add(lbl_DanhSachNhanVien);

        JPanel pnlDanhSachNhanVien = new JPanel();
        pnlDanhSachNhanVien.setBackground(new Color(255, 255, 255));
        pnlDanhSachNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlDanhSachNhanVien.setBounds(20, 395, 1496, 422);
        add(pnlDanhSachNhanVien);

        model = new DefaultTableModel(new String[] {"Mã khách hàng","Tên khách hàng","Giới tính","Ngày sinh","Số điện thoại","Email","Hạng khách hàng","Điểm tích lũy"}, 0);
        pnlDanhSachNhanVien.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 1476, 402);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlDanhSachNhanVien.add(scrollPane);
    }
}

package views;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;
import controller.KhuyenMaiController;

public class KhuyenMaiPanel extends JPanel {
    // Khai báo các component là public để controller có thể truy cập
    public JTextField txt_TenKhachHang;
    public JTextField txt_TyLeGiam;
    public JTextField txt_TimMaKhuyenMai;
    public JTable table;
    public DefaultTableModel model;
    public JButton btn_Them;
    public JButton btn_CapNhat;
    public JButton btn_TimMa;
    public JComboBox comboBox_TrangThai;
    public JDateChooser ngayBD;
    public JDateChooser ngayKT;
    public JCheckBox chckbx_Standard;
    public JCheckBox chckbx_Superior;
    public JCheckBox chckbx_Family;
    public JCheckBox chckbx_Deluxe;
    public JCheckBox chckbx_Suite;
    public JCheckBox chckbx_TatCa;

    // Các component cho bộ lọc
    public JDateChooser ngayBD_1;
    public JDateChooser ngayKT_1;
    public JCheckBox chckbx_Standard_1;
    public JCheckBox chckbx_Superior_1;
    public JCheckBox chckbx_Family_1;
    public JCheckBox chckbx_Deluxe_1;
    public JCheckBox chckbx_Suite_1;
    public JCheckBox chckbx_TatCa_1;

    public KhuyenMaiPanel() {
        // Code khởi tạo giao diện không thay đổi...
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

        btn_Them = new JButton("Thêm");
        btn_Them.setBounds(1142, 134, 120, 30);
        pnlThongTinKhuyenMai.add(btn_Them);

        btn_CapNhat = new JButton("Cập nhật");
        btn_CapNhat.setBounds(1012, 134, 120, 30);
        pnlThongTinKhuyenMai.add(btn_CapNhat);

        comboBox_TrangThai = new JComboBox();
        comboBox_TrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đang hoạt động", "Sắp diễn ra", "Hết hạn", "Tạm ngừng"}));
        comboBox_TrangThai.setBounds(758, 94, 728, 30);
        pnlThongTinKhuyenMai.add(comboBox_TrangThai);

        JLabel lbl_NgayKetThuc = new JLabel("Ngày kết thúc:");
        lbl_NgayKetThuc.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_NgayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgayKetThuc.setBounds(186, 73, 104, 20);
        pnlThongTinKhuyenMai.add(lbl_NgayKetThuc);

        ngayBD = new JDateChooser();
        ngayBD.setDateFormatString("dd/MM/yyyy");
        ngayBD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayBD.setBounds(10, 97, 162, 30);
        pnlThongTinKhuyenMai.add(ngayBD);

        ngayKT = new JDateChooser();
        ngayKT.setDateFormatString("dd/MM/yyyy");
        ngayKT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayKT.setBounds(186, 97, 162, 30);
        pnlThongTinKhuyenMai.add(ngayKT);

        JLabel lbl_DieuKienApDung = new JLabel("Điều kiện áp dụng:");
        lbl_DieuKienApDung.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_DieuKienApDung.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_DieuKienApDung.setBounds(387, 74, 132, 20);
        pnlThongTinKhuyenMai.add(lbl_DieuKienApDung);

        chckbx_Standard = new JCheckBox("Standard");
        chckbx_Standard.setBounds(397, 97, 87, 26);
        pnlThongTinKhuyenMai.add(chckbx_Standard);
        chckbx_Standard.setBackground(Color.white);

        chckbx_Superior = new JCheckBox("Superior");
        chckbx_Superior.setBounds(508, 97, 87, 26);
        pnlThongTinKhuyenMai.add(chckbx_Superior);
        chckbx_Superior.setBackground(Color.white);

        chckbx_Family = new JCheckBox("Family Room");
        chckbx_Family.setBounds(618, 97, 120, 26);
        pnlThongTinKhuyenMai.add(chckbx_Family);
        chckbx_Family.setBackground(Color.white);

        chckbx_Deluxe = new JCheckBox("Deluxe");
        chckbx_Deluxe.setBounds(397, 130, 87, 26);
        pnlThongTinKhuyenMai.add(chckbx_Deluxe);
        chckbx_Deluxe.setBackground(Color.white);

        chckbx_Suite = new JCheckBox("Suite");
        chckbx_Suite.setBounds(508, 130, 87, 26);
        pnlThongTinKhuyenMai.add(chckbx_Suite);
        chckbx_Suite.setBackground(Color.white);

        chckbx_TatCa = new JCheckBox("Tất cả");
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

        txt_TimMaKhuyenMai = new JTextField();
        txt_TimMaKhuyenMai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TimMaKhuyenMai.setColumns(10);
        txt_TimMaKhuyenMai.setBounds(662, 33, 307, 30);
        pnlBoLoc.add(txt_TimMaKhuyenMai);

        btn_TimMa = new JButton("Tìm");
        btn_TimMa.setBounds(1382, 32, 100, 30);
        pnlBoLoc.add(btn_TimMa);

        JLabel lbl_ApDUngTrongKhoang = new JLabel("Áp dụng trong khoảng:");
        lbl_ApDUngTrongKhoang.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_ApDUngTrongKhoang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_ApDUngTrongKhoang.setBounds(1001, 10, 162, 20);
        pnlBoLoc.add(lbl_ApDUngTrongKhoang);

        ngayBD_1 = new JDateChooser();
        ngayBD_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayBD_1.setDateFormatString("dd/MM/yyyy");
        ngayBD_1.setBounds(1001, 33, 162, 30);
        pnlBoLoc.add(ngayBD_1);

        ngayKT_1 = new JDateChooser();
        ngayKT_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayKT_1.setDateFormatString("dd/MM/yyyy");
        ngayKT_1.setBounds(1177, 33, 162, 30);
        pnlBoLoc.add(ngayKT_1);

        JLabel lbl_DieuKienApDung_1 = new JLabel("Điều kiện áp dụng:");
        lbl_DieuKienApDung_1.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_DieuKienApDung_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_DieuKienApDung_1.setBounds(14, 10, 132, 20);
        pnlBoLoc.add(lbl_DieuKienApDung_1);

        chckbx_Standard_1 = new JCheckBox("Standard");
        chckbx_Standard_1.setBounds(14, 33, 87, 26);
        pnlBoLoc.add(chckbx_Standard_1);
        chckbx_Standard_1.setBackground(Color.white);

        chckbx_Superior_1 = new JCheckBox("Superior");
        chckbx_Superior_1.setBounds(130, 33, 87, 26);
        pnlBoLoc.add(chckbx_Superior_1);
        chckbx_Superior_1.setBackground(Color.white);

        chckbx_Family_1 = new JCheckBox("Family Room");
        chckbx_Family_1.setBounds(238, 33, 120, 26);
        pnlBoLoc.add(chckbx_Family_1);
        chckbx_Family_1.setBackground(Color.white);

        chckbx_Deluxe_1 = new JCheckBox("Deluxe");
        chckbx_Deluxe_1.setBounds(375, 33, 87, 26);
        pnlBoLoc.add(chckbx_Deluxe_1);
        chckbx_Deluxe_1.setBackground(Color.white);

        chckbx_Suite_1 = new JCheckBox("Suite");
        chckbx_Suite_1.setBounds(480, 33, 87, 26);
        pnlBoLoc.add(chckbx_Suite_1);
        chckbx_Suite_1.setBackground(Color.white);

        chckbx_TatCa_1 = new JCheckBox("Tất cả");
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

        // Thêm sự kiện cho checkbox
        themSuKienCheckBoxThongTin();
        themSuKienCheckBoxBoLoc();

        // Khởi tạo controller
        new KhuyenMaiController(this);
    }

    private void themSuKienCheckBoxThongTin() {
        // Khi chọn "Tất cả", bỏ chọn các checkbox khác
        ActionListener suKien_ChekBox_TatCa = e -> {
            if(chckbx_TatCa.isSelected()){
                chckbx_Standard.setSelected(false);
                chckbx_Superior.setSelected(false);
                chckbx_Family.setSelected(false);
                chckbx_Deluxe.setSelected(false);
                chckbx_Suite.setSelected(false);
            }
        };

        // Khi chọn các checkbox khác, bỏ chọn "Tất cả"
        ActionListener suKien_Cac_CheckBox_Khac = e -> {
            if (chckbx_Standard.isSelected() || chckbx_Superior.isSelected() ||
                    chckbx_Family.isSelected() || chckbx_Deluxe.isSelected() ||
                    chckbx_Suite.isSelected()) {
                chckbx_TatCa.setSelected(false);
            }
        };

        // Đăng ký sự kiện
        chckbx_TatCa.addActionListener(suKien_ChekBox_TatCa);
        chckbx_Standard.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Superior.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Family.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Deluxe.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Suite.addActionListener(suKien_Cac_CheckBox_Khac);
    }

    private void themSuKienCheckBoxBoLoc() {
        // Khi chọn "Tất cả", bỏ chọn các checkbox khác
        ActionListener suKien_ChekBox_TatCa = e -> {
            if(chckbx_TatCa_1.isSelected()){
                chckbx_Standard_1.setSelected(false);
                chckbx_Superior_1.setSelected(false);
                chckbx_Family_1.setSelected(false);
                chckbx_Deluxe_1.setSelected(false);
                chckbx_Suite_1.setSelected(false);
            }
        };

        // Khi chọn các checkbox khác, bỏ chọn "Tất cả"
        ActionListener suKien_Cac_CheckBox_Khac = e -> {
            if (chckbx_Standard_1.isSelected() || chckbx_Superior_1.isSelected() ||
                    chckbx_Family_1.isSelected() || chckbx_Deluxe_1.isSelected() ||
                    chckbx_Suite_1.isSelected()) {
                chckbx_TatCa_1.setSelected(false);
            }
        };

        // Đăng ký sự kiện
        chckbx_TatCa_1.addActionListener(suKien_ChekBox_TatCa);
        chckbx_Standard_1.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Superior_1.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Family_1.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Deluxe_1.addActionListener(suKien_Cac_CheckBox_Khac);
        chckbx_Suite_1.addActionListener(suKien_Cac_CheckBox_Khac);
    }
}
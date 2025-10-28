package views;

import com.toedter.calendar.JDateChooser;

import controller.FormNhanPhongController;
import controller.ThueDatPhongController;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Component;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormThongTinNhanPhong extends JDialog {

    public static final long serialVersionUID = 1L;
    public JTextField txt_SDT;
    public JTextField txt_HoTen;
    public JTextField txt_CCCD;
    public JTextField txt_NgaySinh;
    public JTextField txt_GioiTinh;
    public JDateChooser ngayBatDau;
    public JDateChooser ngayKetThuc;
    public JButton btn_XacNhan;
    public JButton btn_Huy;
    public JTable table_Phong;
    public DefaultTableModel model;
    public JTable table_NguoiO;
    public DefaultTableModel model_NguoiO;
	private FormNhanPhongController FormNhanPhongController;
	private ThueDatPhongPanel thueDatPanel;
	public JButton btn_Them;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        try {
//            FormThongTinNhanPhong dialog = new FormThongTinNhanPhong();
//            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//            dialog.setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Create the dialog.
     */
//    public FormThongTinNhanPhong() {
//        jbInit();
//    }
    public FormThongTinNhanPhong(ThueDatPhongPanel thuedatPanel) {
    	this.thueDatPanel = thuedatPanel;
        getContentPane().setBackground(new Color(236, 247, 255));
        setBounds(100, 100, 1100, 650);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        

        JLabel lblTenKhachSanMenu = new JLabel("ATTM");
        lblTenKhachSanMenu.setFont(new Font("Lucida Calligraphy", Font.BOLD, 24));
        lblTenKhachSanMenu.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenKhachSanMenu.setForeground(new Color(10, 110, 189));
        lblTenKhachSanMenu.setBounds(10, 10, 90, 24);
        getContentPane().add(lblTenKhachSanMenu);

        JLabel lblTieuDeForm = new JLabel("Thông tin nhận phòng");
        lblTieuDeForm.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeForm.setForeground(new Color(10, 110, 189));
        lblTieuDeForm.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTieuDeForm.setBounds(419, 1, 302, 40);
        getContentPane().add(lblTieuDeForm);

        JLabel lblFromThongTinKhachHang = new JLabel("Thông tin khách hàng ở: ");
        lblFromThongTinKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblFromThongTinKhachHang.setBounds(10, 60, 207, 26);
        getContentPane().add(lblFromThongTinKhachHang);

        JPanel pnl_ThongTinKhachHang = new JPanel();
        // Tạo viền mờ màu xám nhạt với bo góc 15px
        pnl_ThongTinKhachHang.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), HIDE_ON_CLOSE)// Viền ngoài
                , null
        ));
        pnl_ThongTinKhachHang.setBackground(new Color(255, 255, 255));
        pnl_ThongTinKhachHang.setBounds(10, 100, 525, 261);
        getContentPane().add(pnl_ThongTinKhachHang);
        pnl_ThongTinKhachHang.setLayout(null);

        JLabel lbl_SDT = new JLabel("Số điện thoại: ");
        lbl_SDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_SDT.setBounds(10, 20, 102, 20);
        pnl_ThongTinKhachHang.add(lbl_SDT);

        JLabel lbl_HoTen = new JLabel("Họ tên:");
        lbl_HoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_HoTen.setBounds(10, 70, 102, 20);
        pnl_ThongTinKhachHang.add(lbl_HoTen);

        JLabel lbl_CCCD = new JLabel("CCCD:");
        lbl_CCCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_CCCD.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_CCCD.setBounds(10, 120, 102, 20);
        pnl_ThongTinKhachHang.add(lbl_CCCD);

        txt_SDT = new JTextField();
        txt_SDT.setBounds(132, 19, 373, 25);
        pnl_ThongTinKhachHang.add(txt_SDT);
        txt_SDT.setColumns(10);

        txt_HoTen = new JTextField();
        txt_HoTen.setColumns(10);
        txt_HoTen.setBounds(132, 69, 373, 25);
        pnl_ThongTinKhachHang.add(txt_HoTen);

        txt_CCCD = new JTextField();
        txt_CCCD.setColumns(10);
        txt_CCCD.setBounds(132, 119, 373, 25);
        pnl_ThongTinKhachHang.add(txt_CCCD);

        JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
        lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgaySinh.setBounds(10, 170, 112, 20);
        pnl_ThongTinKhachHang.add(lbl_NgaySinh);

        txt_NgaySinh = new JTextField();
        txt_NgaySinh.setColumns(10);
        txt_NgaySinh.setBounds(132, 169, 110, 25);
        pnl_ThongTinKhachHang.add(txt_NgaySinh);

        JLabel lbl_GioiTinh = new JLabel("Giới tính:");
        lbl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_GioiTinh.setBounds(291, 170, 92, 20);
        pnl_ThongTinKhachHang.add(lbl_GioiTinh);

        txt_GioiTinh = new JTextField();
        txt_GioiTinh.setBounds(393, 169, 112, 25);
        pnl_ThongTinKhachHang.add(txt_GioiTinh);
        txt_GioiTinh.setColumns(10);
        
        btn_Them = new JButton("Thêm ");
        btn_Them.setBounds(406, 218, 99, 33);
        pnl_ThongTinKhachHang.add(btn_Them);
        btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 11));
  

        // Tạo nhóm và thêm radio button vào nhóm
        ButtonGroup paymentGroup = new ButtonGroup();

        JLabel lbl_DanhSachPhong = new JLabel("Danh sách phòng");
        lbl_DanhSachPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachPhong.setBounds(550, 60, 147, 26);
        getContentPane().add(lbl_DanhSachPhong);

        JPanel pnl_DanhSachPhong = new JPanel();
        pnl_DanhSachPhong.setBackground(new Color(255, 255, 255));
        pnl_DanhSachPhong.setBounds(550, 100, 525, 178);
        pnl_DanhSachPhong.setLayout(null);
        getContentPane().add(pnl_DanhSachPhong);


        model = new DefaultTableModel(new String[] {"Mã phòng","Loại phòng","SLTĐ","Giá","Tiền cọc"}, 0);
        table_Phong = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table_Phong);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(10, 10, 505, 158);
        JTableHeader header = table_Phong.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table_Phong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnl_DanhSachPhong.add(scrollPane);

        JLabel lbl_ThoiGianThue = new JLabel("Thời gian thuê");
        lbl_ThoiGianThue.setBackground(new Color(255, 255, 255));
        lbl_ThoiGianThue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThoiGianThue.setBounds(10, 371, 128, 26);
        getContentPane().add(lbl_ThoiGianThue);

        JPanel pnl_ThoiGianThue = new JPanel();
        pnl_ThoiGianThue.setBackground(new Color(255, 255, 255));
        pnl_ThoiGianThue.setBounds(10, 407, 525, 95);
        getContentPane().add(pnl_ThoiGianThue);
        pnl_ThoiGianThue.setLayout(null);

        JLabel lbl_NgayBatDau = new JLabel("Ngày bắt đầu: ");
        lbl_NgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgayBatDau.setBounds(10, 21, 100, 20);
        pnl_ThoiGianThue.add(lbl_NgayBatDau);

        ngayBatDau = new JDateChooser();
        ngayBatDau.setDateFormatString("dd/MM/yyyy");
        ngayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayBatDau.setBounds(140, 20, 375, 25);  // <-- SỬA 125 thành 10
        pnl_ThoiGianThue.add(ngayBatDau);

        JLabel lbl_TienKhachDuaTrongPnlTongTien = new JLabel("Ngày kết thúc: ");
        lbl_TienKhachDuaTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TienKhachDuaTrongPnlTongTien.setBounds(10, 61, 100, 20);
        pnl_ThoiGianThue.add(lbl_TienKhachDuaTrongPnlTongTien);

        ngayKetThuc = new JDateChooser();
        ngayKetThuc.setDateFormatString("dd/MM/yyyy");
        ngayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayKetThuc.setBounds(140, 60, 375, 25);
        pnl_ThoiGianThue.add(ngayKetThuc);

        JLabel lbl_DanhSachNguoiO = new JLabel("Danh sách người ở: ");
        lbl_DanhSachNguoiO.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachNguoiO.setBounds(550, 288, 172, 26);
        getContentPane().add(lbl_DanhSachNguoiO);

        JPanel pnl_DanhSachNguoiO = new JPanel();
        pnl_DanhSachNguoiO.setBackground(new Color(255, 255, 255));
        pnl_DanhSachNguoiO.setBounds(550, 324, 525, 178);
        getContentPane().add(pnl_DanhSachNguoiO);
        pnl_DanhSachNguoiO.setLayout(null);
        
        JScrollPane scrollPane_1 = new JScrollPane((Component) null);
        scrollPane_1.setBackground(Color.WHITE);
        scrollPane_1.setBounds(10, 10, 505, 158);
        pnl_DanhSachNguoiO.add(scrollPane_1);
        
        model_NguoiO = new DefaultTableModel(new String[] {"Tên người ở","Ngày sinh","Giới tính","SĐT","CCCD"}, 0);
        table_NguoiO = new JTable(model_NguoiO);
        table_NguoiO.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        scrollPane_1.setViewportView(table_NguoiO);

        class RoundedButton extends JButton {
            private int radius;

            public RoundedButton(String label, Color bg, Color fg, int radius) {
                super(label);
                this.radius = radius;
                setBackground(bg);
                setForeground(fg);
                setContentAreaFilled(false);
                setFocusPainted(false);
                setBorder(null);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                super.paintComponent(g2);
                g2.dispose();
            }
        }

        btn_XacNhan = new RoundedButton("Xác nhận", new Color(76, 175, 80), Color.WHITE, 30);
        btn_XacNhan.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_XacNhan.setBounds(430, 570, 120, 30);
        getContentPane().add(btn_XacNhan);

        btn_Huy = new RoundedButton("Hủy", new Color(244, 67, 54), Color.WHITE, 30);
        btn_Huy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_Huy.setBounds(612, 570, 120, 30);
        getContentPane().add(btn_Huy);
        FormNhanPhongController = new FormNhanPhongController(this ,thueDatPanel);
//        FormNhanPhongController.getKhachHang();
        FormNhanPhongController.getPhong();
        FormNhanPhongController.getThoiGian();
        
    }
}
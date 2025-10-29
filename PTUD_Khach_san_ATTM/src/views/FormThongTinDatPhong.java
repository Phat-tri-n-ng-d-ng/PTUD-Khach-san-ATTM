package views;

import com.toedter.calendar.JDateChooser;
import controller.FormThongTinDatPhongController;
import controller.FormThongTinThuePhongController;
import entity.KhachHang;
import entity.Phong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Dimension;

import java.awt.Font;
import java.util.ArrayList;

public class FormThongTinDatPhong extends JDialog {

    private static final long serialVersionUID = 1L;
    public JTextField txt_SDT;
    public JTextField txt_HoTen;
    public JTextField txt_CCCD;
    public JTextField txt_NgaySinh;
    public JTextField txt_GioiTinh;
    public JTextField txt_Email;
    public JTextField txt_HangKhachHang;
    public JTextField txt_DiemTichLuy;
    public JTextField txt_TienKhachDua;
    public JDateChooser ngayBatDau;
    public JDateChooser ngayKetThuc;
    public JLabel lbl_PhuongThucThanhToanTrongPnlTongTien;
    public JLabel lbl_TongTienTrongPnlTongTien;
    public JLabel lbl_TienCocTrongPnlTongTien;
    public JLabel lbl_TienNhanTuKhachTrongPnlTongTien;
    public JLabel lbl_TienTraLaiKhachTrongPnlTongTien;
    public JLabel lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien;
    public JLabel lbl_TienCuaTongTienTrongPnlTongTien;
    public JLabel lbl_TienCuaTienCocTrongPnlTongTien;
    public JLabel lbl_TienCuaTienNhanTuKhachTrongPnlTongTien;
    public JLabel lbl_TienCuaTienTraLaiKhachTrongPnlTongTien;
    public JButton btn_XacNhan;
    public JButton btn_Huy;
    public JRadioButton rdbtn_TienMat;
    public JRadioButton rdbtn_ChuyenKhoan;
    public FormThongTinDatPhongController controller;
    public JTable table;
    public DefaultTableModel model;


    public FormThongTinDatPhong() {
        jbInit();
        this.controller = new FormThongTinDatPhongController(this);
    }
    private void jbInit() {
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

        JLabel lblTieuDeForm = new JLabel("Thông tin đặt phòng");
        lblTieuDeForm.setForeground(new Color(10, 110, 189));
        lblTieuDeForm.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTieuDeForm.setBounds(430, 3, 302, 40);
        getContentPane().add(lblTieuDeForm);

        JLabel lblFromThongTinKhachHang = new JLabel("Thông tin khách hàng đặt phòng");
        lblFromThongTinKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblFromThongTinKhachHang.setBounds(10, 60, 302, 26);
        getContentPane().add(lblFromThongTinKhachHang);

        JPanel pnl_ThongTinKhachHang = new JPanel();
        // Tạo viền mờ màu xám nhạt với bo góc 15px
        pnl_ThongTinKhachHang.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), HIDE_ON_CLOSE)// Viền ngoài
                , null
        ));
        pnl_ThongTinKhachHang.setBackground(new Color(255, 255, 255));
        pnl_ThongTinKhachHang.setBounds(10, 100, 525, 310);
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
        txt_SDT.setEditable(false);
        txt_SDT.setBackground(Color.WHITE);
        txt_SDT.setFocusable(false); // <-- THÊM DÒNG NÀY
        pnl_ThongTinKhachHang.add(txt_SDT);
        txt_SDT.setColumns(10);

        txt_HoTen = new JTextField();
        txt_HoTen.setColumns(10);
        txt_HoTen.setBounds(132, 69, 373, 25);
        txt_HoTen.setEditable(false);
        txt_HoTen.setBackground(Color.WHITE);
        txt_HoTen.setFocusable(false);
        pnl_ThongTinKhachHang.add(txt_HoTen);

        txt_CCCD = new JTextField();
        txt_CCCD.setColumns(10);
        txt_CCCD.setBounds(132, 119, 373, 25);
        txt_CCCD.setEditable(false);
        txt_CCCD.setBackground(Color.WHITE);
        txt_CCCD.setFocusable(false);
        pnl_ThongTinKhachHang.add(txt_CCCD);

        JLabel lbl_NgaySinh = new JLabel("Ngày sinh:");
        lbl_NgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgaySinh.setBounds(10, 170, 112, 20);
        pnl_ThongTinKhachHang.add(lbl_NgaySinh);

        txt_NgaySinh = new JTextField();
        txt_NgaySinh.setColumns(10);
        txt_NgaySinh.setBounds(132, 169, 110, 25);
        txt_NgaySinh.setEditable(false);
        txt_NgaySinh.setBackground(Color.WHITE);
        txt_NgaySinh.setFocusable(false);
        pnl_ThongTinKhachHang.add(txt_NgaySinh);

        JLabel lbl_GioiTinh = new JLabel("Giới tính:");
        lbl_GioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_GioiTinh.setBounds(291, 170, 92, 20);
        pnl_ThongTinKhachHang.add(lbl_GioiTinh);

        txt_GioiTinh = new JTextField();
        txt_GioiTinh.setBounds(393, 169, 112, 25);
        txt_GioiTinh.setEditable(false);
        txt_GioiTinh.setBackground(Color.WHITE);
        txt_GioiTinh.setFocusable(false);
        pnl_ThongTinKhachHang.add(txt_GioiTinh);
        txt_GioiTinh.setColumns(10);

        JLabel lbl_Email = new JLabel("Email:");
        lbl_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_Email.setBounds(10, 220, 112, 20);
        pnl_ThongTinKhachHang.add(lbl_Email);

        txt_Email = new JTextField();
        txt_Email.setBounds(132, 219, 373, 25);
        txt_Email.setEditable(false);
        txt_Email.setBackground(Color.WHITE);
        txt_Email.setFocusable(false);
        pnl_ThongTinKhachHang.add(txt_Email);
        txt_Email.setColumns(10);

        JLabel lbl_HangKhachHang = new JLabel("Hạng khách hàng:");
        lbl_HangKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_HangKhachHang.setBounds(10, 270, 112, 20);
        pnl_ThongTinKhachHang.add(lbl_HangKhachHang);

        txt_HangKhachHang = new JTextField();
        txt_HangKhachHang.setColumns(10);
        txt_HangKhachHang.setEditable(false);
        txt_HangKhachHang.setBackground(Color.WHITE);
        txt_HangKhachHang.setFocusable(false);
        txt_HangKhachHang.setBounds(132, 269, 110, 25);
        pnl_ThongTinKhachHang.add(txt_HangKhachHang);

        JLabel lbl_DiemTichLuy = new JLabel("Điểm tích lũy:");
        lbl_DiemTichLuy.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_DiemTichLuy.setBounds(298, 270, 92, 20);
        pnl_ThongTinKhachHang.add(lbl_DiemTichLuy);

        txt_DiemTichLuy = new JTextField();
        txt_DiemTichLuy.setColumns(10);
        txt_DiemTichLuy.setEditable(false);
        txt_DiemTichLuy.setBackground(Color.WHITE);
        txt_DiemTichLuy.setFocusable(false);
        txt_DiemTichLuy.setBounds(393, 269, 112, 25);
        pnl_ThongTinKhachHang.add(txt_DiemTichLuy);

        JLabel lbl_PhuongThucThangToan = new JLabel("Phương thức thanh toán: ");
        lbl_PhuongThucThangToan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_PhuongThucThangToan.setBounds(10, 440, 155, 20);
        getContentPane().add(lbl_PhuongThucThangToan);

        rdbtn_TienMat = new JRadioButton("Tiền mặt");
        rdbtn_TienMat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_TienMat.setBackground(new Color(236, 247, 255));
        rdbtn_TienMat.setBounds(200, 440, 102, 20);
        getContentPane().add(rdbtn_TienMat);

        rdbtn_ChuyenKhoan = new JRadioButton("Chuyển khoản");
        rdbtn_ChuyenKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_ChuyenKhoan.setBackground(new Color(236, 247, 255));
        rdbtn_ChuyenKhoan.setBounds(340, 440, 113, 20);
        getContentPane().add(rdbtn_ChuyenKhoan);

        // Tạo nhóm và thêm radio button vào nhóm
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(rdbtn_TienMat);
        paymentGroup.add(rdbtn_ChuyenKhoan);

        // Nếu muốn một radio mặc định được chọn
        rdbtn_TienMat.setSelected(true);


        JLabel lbl_TienKhachDua = new JLabel("Tiền khách đưa:");
        lbl_TienKhachDua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_TienKhachDua.setBounds(10, 490, 128, 24);
        getContentPane().add(lbl_TienKhachDua);

        txt_TienKhachDua = new JTextField();
        txt_TienKhachDua.setBounds(148, 489, 377, 25);
        getContentPane().add(txt_TienKhachDua);
        txt_TienKhachDua.setColumns(10);

        JLabel lbl_DanhSachPhong = new JLabel("Danh sách phòng");
        lbl_DanhSachPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachPhong.setBounds(550, 60, 147, 26);
        getContentPane().add(lbl_DanhSachPhong);

        JPanel pnl_DanhSachPhong = new JPanel();
        pnl_DanhSachPhong.setBackground(new Color(255, 255, 255));
        pnl_DanhSachPhong.setBounds(550, 100, 525, 107);
        pnl_DanhSachPhong.setLayout(null);
        getContentPane().add(pnl_DanhSachPhong);

        model = new DefaultTableModel(new String[] {"Mã phòng","Loại phòng","SLTĐ","Giá","Tiền cọc"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(10, 10, 505, 87);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnl_DanhSachPhong.add(scrollPane);

        JLabel lbl_ThoiGianThue = new JLabel("Thời gian thuê");
        lbl_ThoiGianThue.setBackground(new Color(255, 255, 255));
        lbl_ThoiGianThue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThoiGianThue.setBounds(550, 220, 128, 26);
        getContentPane().add(lbl_ThoiGianThue);

        JPanel pnl_ThoiGianThue = new JPanel();
        pnl_ThoiGianThue.setBackground(new Color(255, 255, 255));
        pnl_ThoiGianThue.setBounds(550, 260, 525, 80);
        getContentPane().add(pnl_ThoiGianThue);
        pnl_ThoiGianThue.setLayout(null);

        JLabel lbl_NgayBatDau = new JLabel("Ngày bắt đầu: ");
        lbl_NgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgayBatDau.setBounds(10, 10, 100, 20);
        pnl_ThoiGianThue.add(lbl_NgayBatDau);

        ngayBatDau = new JDateChooser();
        ngayBatDau.setDateFormatString("dd/MM/yyyy");
        ngayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayBatDau.setBounds(140, 9, 250, 25);  // <-- SỬA 125 thành 10
        pnl_ThoiGianThue.add(ngayBatDau);

        JLabel lbl_TienKhachDuaTrongPnlTongTien = new JLabel("Ngày kết thúc: ");
        lbl_TienKhachDuaTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TienKhachDuaTrongPnlTongTien.setBounds(10, 50, 100, 20);
        pnl_ThoiGianThue.add(lbl_TienKhachDuaTrongPnlTongTien);

        ngayKetThuc = new JDateChooser();
        ngayKetThuc.setDateFormatString("dd/MM/yyyy");
        ngayKetThuc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayKetThuc.setBounds(140, 49, 250, 25);
        pnl_ThoiGianThue.add(ngayKetThuc);

        JLabel lbl_TongTien = new JLabel("Tổng tiền");
        lbl_TongTien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_TongTien.setBounds(550, 360, 102, 26);
        getContentPane().add(lbl_TongTien);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(550, 400, 525, 150);
        getContentPane().add(panel);
        panel.setLayout(null);

        lbl_PhuongThucThanhToanTrongPnlTongTien = new JLabel("Phương thức thanh toán:");
        lbl_PhuongThucThanhToanTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_PhuongThucThanhToanTrongPnlTongTien.setBounds(10, 10, 163, 18);
        panel.add(lbl_PhuongThucThanhToanTrongPnlTongTien);

        lbl_TongTienTrongPnlTongTien = new JLabel("Tổng tiền:");
        lbl_TongTienTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TongTienTrongPnlTongTien.setBounds(10, 38, 163, 18);
        panel.add(lbl_TongTienTrongPnlTongTien);

        lbl_TienCocTrongPnlTongTien = new JLabel("Tiền cọc:");
        lbl_TienCocTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCocTrongPnlTongTien.setBounds(10, 66, 163, 18);
        panel.add(lbl_TienCocTrongPnlTongTien);

        lbl_TienNhanTuKhachTrongPnlTongTien = new JLabel("Tiền nhận từ khách:");
        lbl_TienNhanTuKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienNhanTuKhachTrongPnlTongTien.setBounds(10, 94, 163, 18);
        panel.add(lbl_TienNhanTuKhachTrongPnlTongTien);

        lbl_TienTraLaiKhachTrongPnlTongTien = new JLabel("Tiền trả lại khách:");
        lbl_TienTraLaiKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienTraLaiKhachTrongPnlTongTien.setBounds(10, 122, 163, 18);
        panel.add(lbl_TienTraLaiKhachTrongPnlTongTien);

        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien = new JLabel("Tiền mặt");
        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setBounds(455, 10, 120, 18);
        panel.add(lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien);

        lbl_TienCuaTongTienTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTongTienTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTongTienTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTongTienTrongPnlTongTien.setBounds(455, 38, 120, 18);
        panel.add(lbl_TienCuaTongTienTrongPnlTongTien);

        lbl_TienCuaTienCocTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTienCocTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTienCocTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTienCocTrongPnlTongTien.setBounds(455, 66, 120, 18);
        panel.add(lbl_TienCuaTienCocTrongPnlTongTien);

        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setBounds(455, 94, 120, 18);
        panel.add(lbl_TienCuaTienNhanTuKhachTrongPnlTongTien);

        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setBounds(455, 122, 120, 18);
        panel.add(lbl_TienCuaTienTraLaiKhachTrongPnlTongTien);

        btn_XacNhan = new RoundedButton("Xác nhận", new Color(76, 175, 80), Color.WHITE, 30);
        btn_XacNhan.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_XacNhan.setBounds(430, 570, 120, 30);
        getContentPane().add(btn_XacNhan);

        btn_Huy = new RoundedButton("Hủy", new Color(244, 67, 54), Color.WHITE, 30);
        btn_Huy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_Huy.setBounds(612, 570, 120, 30);
        getContentPane().add(btn_Huy);
    }

    // Thêm phương thức setThongTin
    public void setThongTin(KhachHang khachHang, ArrayList<Phong> danhSachPhong, String loaiThue) {
        controller.hienThiThongTin(khachHang, danhSachPhong);
    }

    class RoundedButton extends JButton {
        private int radius;
        private Color originalBg;
        private boolean isHovered = false;
        private boolean isPressed = false;

        public RoundedButton(String label, Color bg, Color fg, int radius) {
            super(label);
            this.radius = radius;
            this.originalBg = bg;
            setBackground(bg);
            setForeground(fg);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(null);

            // Add mouse listener for hover and press effects
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    isHovered = false;
                    repaint();
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    isPressed = true;
                    repaint();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent e) {
                    isPressed = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Determine color based on state
            Color bgColor = originalBg;
            if (isPressed) {
                bgColor = originalBg.darker();  // Darker on press
            } else if (isHovered) {
                bgColor = originalBg.brighter();  // Brighter on hover
            }

            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }
    }
}
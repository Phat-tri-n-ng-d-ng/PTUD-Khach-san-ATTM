package views;

import com.toedter.calendar.JDateChooser;
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

public class FormThongTinTraPhong extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txt_SDT;
    private JTextField txt_HoTen;
    private JTextField txt_CCCD;
    private JTextField txt_NgaySinh;
    private JTextField txt_GioiTinh;
    private JTextField txt_Email;
    private JTextField txt_HangKhachHang;
    private JTextField txt_DiemTichLuy;
    private JTextField txt_TienKhachDua;
    public JDateChooser ngayBatDau;
    public JDateChooser ngayKetThuc;
    JLabel lbl_PhuongThucThanhToanTrongPnlTongTien;
    JLabel lbl_TongTienPhongTrongPnlTongTien;
    JLabel lbl_PhiDoiPhongTrongPnlTongTien;
    JLabel lbl_TienNhanTuKhachTrongPnlTongTien;
    JLabel lbl_TienTraLaiKhachTrongPnlTongTien;
    JLabel lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien;
    JLabel lbl_TienCuaTongTienTrongPnlTongTien;
    JLabel lbl_TienCuaPhiDoiPhongTrongPnlTongTien;
    JLabel lbl_TienCuaTienNhanTuKhachTrongPnlTongTien;
    JLabel lbl_TienCuaTienTraLaiKhachTrongPnlTongTien;
    JButton btn_XacNhan;
    JButton btn_Huy;
    public JTable table;
    public DefaultTableModel model;



    public  FormThongTinTraPhong() {
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

        JLabel lblTieuDeForm = new JLabel("Thông tin trả phòng");
        lblTieuDeForm.setForeground(new Color(10, 110, 189));
        lblTieuDeForm.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTieuDeForm.setBounds(430, 3, 302, 40);
        getContentPane().add(lblTieuDeForm);

        JLabel lblFromThongTinKhachHang = new JLabel("Thông tin khách hàng ");
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

        JLabel lbl_Email = new JLabel("Email:");
        lbl_Email.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_Email.setBounds(10, 220, 112, 20);
        pnl_ThongTinKhachHang.add(lbl_Email);

        txt_Email = new JTextField();
        txt_Email.setBounds(132, 219, 373, 25);
        pnl_ThongTinKhachHang.add(txt_Email);
        txt_Email.setColumns(10);

        JLabel lbl_HangKhachHang = new JLabel("Hạng khách hàng:");
        lbl_HangKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_HangKhachHang.setBounds(10, 270, 112, 20);
        pnl_ThongTinKhachHang.add(lbl_HangKhachHang);

        txt_HangKhachHang = new JTextField();
        txt_HangKhachHang.setColumns(10);
        txt_HangKhachHang.setBounds(132, 269, 110, 25);
        pnl_ThongTinKhachHang.add(txt_HangKhachHang);

        JLabel lbl_DiemTichLuy = new JLabel("Điểm tích lũy:");
        lbl_DiemTichLuy.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_DiemTichLuy.setBounds(298, 270, 92, 20);
        pnl_ThongTinKhachHang.add(lbl_DiemTichLuy);

        txt_DiemTichLuy = new JTextField();
        txt_DiemTichLuy.setColumns(10);
        txt_DiemTichLuy.setBounds(393, 269, 112, 25);
        pnl_ThongTinKhachHang.add(txt_DiemTichLuy);

        JLabel lbl_PhuongThucThangToan = new JLabel("Phương thức thanh toán: ");
        lbl_PhuongThucThangToan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_PhuongThucThangToan.setBounds(10, 440, 155, 20);
        getContentPane().add(lbl_PhuongThucThangToan);

        JRadioButton rdbtn_TienMat = new JRadioButton("Tiền mặt");
        rdbtn_TienMat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_TienMat.setBackground(new Color(236, 247, 255));
        rdbtn_TienMat.setBounds(200, 440, 102, 20);
        getContentPane().add(rdbtn_TienMat);

        JRadioButton rdbtn_ChuyenKhoan = new JRadioButton("Chuyển khoản");
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
        lbl_TienKhachDua.setBounds(10, 509, 128, 24);
        getContentPane().add(lbl_TienKhachDua);

        txt_TienKhachDua = new JTextField();
        txt_TienKhachDua.setBounds(148, 508, 377, 25);
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


        model = new DefaultTableModel(new String[] {"Mã phòng","Loại phòng","SLTĐ","Giá","Tiền cọc","Thành tiền"}, 0);
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
        pnl_ThoiGianThue.setBounds(550, 245, 525, 80);
        getContentPane().add(pnl_ThoiGianThue);
        pnl_ThoiGianThue.setLayout(null);

        JLabel lbl_NgayBatDau = new JLabel("Ngày bắt đầu: ");
        lbl_NgayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_NgayBatDau.setBounds(10, 10, 100, 20);
        pnl_ThoiGianThue.add(lbl_NgayBatDau);

        ngayBatDau = new JDateChooser();
        ngayBatDau.setDateFormatString("dd/MM/yyyy");
        ngayBatDau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        ngayBatDau.setBounds(140, 9, 250, 25);  
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
        lbl_TongTien.setBounds(545, 335, 102, 26);
        getContentPane().add(lbl_TongTien);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(550, 359, 525, 196);
        getContentPane().add(panel);
        panel.setLayout(null);

        lbl_PhuongThucThanhToanTrongPnlTongTien = new JLabel("Phương thức thanh toán:");
        lbl_PhuongThucThanhToanTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_PhuongThucThanhToanTrongPnlTongTien.setBounds(10, 10, 163, 18);
        panel.add(lbl_PhuongThucThanhToanTrongPnlTongTien);

        lbl_TongTienPhongTrongPnlTongTien = new JLabel("Tổng tiền phòng:");
        lbl_TongTienPhongTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TongTienPhongTrongPnlTongTien.setBounds(10, 50, 163, 18);
        panel.add(lbl_TongTienPhongTrongPnlTongTien);

        lbl_PhiDoiPhongTrongPnlTongTien = new JLabel("Phí đổi phòng:");
        lbl_PhiDoiPhongTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_PhiDoiPhongTrongPnlTongTien.setBounds(10, 90, 163, 18);
        panel.add(lbl_PhiDoiPhongTrongPnlTongTien);

        lbl_TienNhanTuKhachTrongPnlTongTien = new JLabel("Tiền nhận từ khách:");
        lbl_TienNhanTuKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienNhanTuKhachTrongPnlTongTien.setBounds(10, 150, 163, 18);
        panel.add(lbl_TienNhanTuKhachTrongPnlTongTien);

        lbl_TienTraLaiKhachTrongPnlTongTien = new JLabel("Tiền trả lại khách:");
        lbl_TienTraLaiKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienTraLaiKhachTrongPnlTongTien.setBounds(10, 170, 163, 18);
        panel.add(lbl_TienTraLaiKhachTrongPnlTongTien);

        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien = new JLabel("Tiền mặt");
        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien.setBounds(455, 10, 60, 18);
        panel.add(lbl_PhuongThucThanhToanDuocChonTrongPnlTongTien);

        lbl_TienCuaTongTienTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTongTienTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTongTienTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTongTienTrongPnlTongTien.setBounds(455, 50, 60, 18);
        panel.add(lbl_TienCuaTongTienTrongPnlTongTien);

        lbl_TienCuaPhiDoiPhongTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaPhiDoiPhongTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaPhiDoiPhongTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaPhiDoiPhongTrongPnlTongTien.setBounds(455, 90, 60, 18);
        panel.add(lbl_TienCuaPhiDoiPhongTrongPnlTongTien);

        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTienNhanTuKhachTrongPnlTongTien.setBounds(455, 150, 60, 18);
        panel.add(lbl_TienCuaTienNhanTuKhachTrongPnlTongTien);

        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTienTraLaiKhachTrongPnlTongTien.setBounds(455, 170, 60, 18);
        panel.add(lbl_TienCuaTienTraLaiKhachTrongPnlTongTien);
        
        JLabel lbl_PhuongThucXuatHoaDonIn = new JLabel("Phương thức xuất hóa đơn:");
        lbl_PhuongThucXuatHoaDonIn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_PhuongThucXuatHoaDonIn.setBounds(10, 30, 163, 18);
        panel.add(lbl_PhuongThucXuatHoaDonIn);
        
        JLabel lbl_PhuongThucThanhToanDuocChonGopHoaDon = new JLabel("Gộp hóa đơn");
        lbl_PhuongThucThanhToanDuocChonGopHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_PhuongThucThanhToanDuocChonGopHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_PhuongThucThanhToanDuocChonGopHoaDon.setBounds(431, 30, 84, 18);
        panel.add(lbl_PhuongThucThanhToanDuocChonGopHoaDon);
        
        JLabel lbl_TienTruKhuyenMaiTrongPnlTongTien = new JLabel("Tiền trừ khuyến mãi:");
        lbl_TienTruKhuyenMaiTrongPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienTruKhuyenMaiTrongPnlTongTien.setBounds(10, 70, 163, 18);
        panel.add(lbl_TienTruKhuyenMaiTrongPnlTongTien);
        
        JLabel lbl_TienCuaTienTruKhuyenMaiPnlTongTien = new JLabel("0 VND");
        lbl_TienCuaTienTruKhuyenMaiPnlTongTien.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTienTruKhuyenMaiPnlTongTien.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTienTruKhuyenMaiPnlTongTien.setBounds(455, 70, 60, 18);
        panel.add(lbl_TienCuaTienTruKhuyenMaiPnlTongTien);
        
        JLabel lbl_TongTienThanhToanTrongPnlTongTien_1 = new JLabel("Tổng tiền thanh toán:");
        lbl_TongTienThanhToanTrongPnlTongTien_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TongTienThanhToanTrongPnlTongTien_1.setBounds(10, 110, 163, 18);
        panel.add(lbl_TongTienThanhToanTrongPnlTongTien_1);
        
        JLabel lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1 = new JLabel("0 VND");
        lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1.setBounds(455, 110, 60, 18);
        panel.add(lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1);
        
        JLabel lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1_1 = new JLabel("0 VND");
        lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1_1.setBounds(455, 130, 60, 18);
        panel.add(lbl_TienCuaTongTienThanhToanTrongPnlTongTien_1_1);
        
        JLabel lbl_TienThueTrongPnlTongTien_1_1 = new JLabel("Thuế (10% tổng tiền):");
        lbl_TienThueTrongPnlTongTien_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lbl_TienThueTrongPnlTongTien_1_1.setBounds(10, 130, 163, 18);
        panel.add(lbl_TienThueTrongPnlTongTien_1_1);

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

        RoundedButton btn_XacNhan = new RoundedButton("Xác nhận", new Color(76, 175, 80), Color.WHITE, 30);
        btn_XacNhan.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_XacNhan.setBounds(430, 570, 120, 30);
        getContentPane().add(btn_XacNhan);

        RoundedButton btn_Huy = new RoundedButton("Hủy", new Color(244, 67, 54), Color.WHITE, 30);
        btn_Huy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btn_Huy.setBounds(612, 570, 120, 30);
        getContentPane().add(btn_Huy);
        
        JLabel lbl_PhuongThucXuatHoaDon = new JLabel("Phương thức xuất hóa đơn: ");
        lbl_PhuongThucXuatHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_PhuongThucXuatHoaDon.setBounds(10, 466, 172, 20);
        getContentPane().add(lbl_PhuongThucXuatHoaDon);
        
        JRadioButton rdbtn_GopHoaDon = new JRadioButton("Gộp hóa đơn");
        rdbtn_GopHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_GopHoaDon.setBackground(new Color(236, 247, 255));
        rdbtn_GopHoaDon.setBounds(340, 466, 128, 20);
        getContentPane().add(rdbtn_GopHoaDon);
        
        JRadioButton rdbtn_TachHoaDon = new JRadioButton("Tách hóa đơn");
        rdbtn_TachHoaDon.setSelected(true);
        rdbtn_TachHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        rdbtn_TachHoaDon.setBackground(new Color(236, 247, 255));
        rdbtn_TachHoaDon.setBounds(200, 466, 113, 20);
        getContentPane().add(rdbtn_TachHoaDon);
    }
}
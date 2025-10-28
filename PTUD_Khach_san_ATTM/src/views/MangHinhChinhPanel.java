package views;

import controller.MangHinhChinhController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MangHinhChinhPanel extends JPanel{
    public DefaultTableModel model;
    public JTable table;
    private MangHinhChinhController mangHinhChinhController;

    public  MangHinhChinhPanel(){
        setBounds(100, 100, 1336, 768);
        setBackground(new Color(236, 247, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lbl_TieuDe = new JLabel("Nhân Viên");
        lbl_TieuDe.setForeground(new Color(10, 100, 189));
        lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lbl_TieuDe.setBounds(725, 10, 114, 24);
        add(lbl_TieuDe);

        JLabel lpl_SoPhong = new JLabel("Tình Trạng Phòng Hiện Tại & Danh Thu Trong Ngày");
        lpl_SoPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lpl_SoPhong.setBounds(20, 49, 426, 20);
        add(lpl_SoPhong);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // lấy kích thước màng hình hiện tại
        setSize(screenSize.width, screenSize.height);

        JLabel lbl_DanhSachPhongDatTrongNgay = new JLabel("Danh Sách Phòng Đặt Hôm Nay");
        lbl_DanhSachPhongDatTrongNgay.setHorizontalAlignment(SwingConstants.LEFT);
        lbl_DanhSachPhongDatTrongNgay.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachPhongDatTrongNgay.setBounds(20, 189, 267, 20);
        add(lbl_DanhSachPhongDatTrongNgay);

        JPanel pnlDanhSachNhanVien = new JPanel();
        pnlDanhSachNhanVien.setBackground(new Color(255, 255, 255));
        pnlDanhSachNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlDanhSachNhanVien.setBounds(20, 219, 1496, 572);
        add(pnlDanhSachNhanVien);

        model = new DefaultTableModel(new String[] {"Mã phòng","Loại phòng","SLTD","Tên khách hàng","SĐT khách hành"}, 0);
        pnlDanhSachNhanVien.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 1476, 512);
        JTableHeader header = table.getTableHeader(); // chỉnh sửa header
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlDanhSachNhanVien.add(scrollPane);

        JButton btn_NhanPhong = new JButton("Nhận phòng");
        btn_NhanPhong.setBounds(1330, 532, 156, 30);
        pnlDanhSachNhanVien.add(btn_NhanPhong);

        JPanel pnl_PhongTrong = new JPanel();
        pnl_PhongTrong.setBackground(new Color(255, 255, 255));
        pnl_PhongTrong.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnl_PhongTrong.setBounds(20, 79, 356, 100);
        add(pnl_PhongTrong);
        pnl_PhongTrong.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 128, 0));
        panel.setBounds(1, 1, 10, 98);
        pnl_PhongTrong.add(panel);

        JLabel lbl_PhongTrong = new JLabel("Số phòng trống");
        lbl_PhongTrong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_PhongTrong.setBounds(20, 10, 186, 20);
        pnl_PhongTrong.add(lbl_PhongTrong);

        JLabel lbl_SoPhongTrong = new JLabel("");
        lbl_SoPhongTrong.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lbl_SoPhongTrong.setBounds(156, 40, 30, 35);
        pnl_PhongTrong.add(lbl_SoPhongTrong);

        JPanel pnl_PhongThue = new JPanel();
        pnl_PhongThue.setBackground(new Color(255, 255, 255));
        pnl_PhongThue.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnl_PhongThue.setBounds(400, 79, 356, 100);
        add(pnl_PhongThue);
        pnl_PhongThue.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(1, 1, 10, 98);
        panel_1.setBackground(new Color(0, 255, 0));
        pnl_PhongThue.add(panel_1);

        JLabel lbl_PhongThue = new JLabel("Số phòng thuê");
        lbl_PhongThue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_PhongThue.setBounds(20, 10, 186, 20);
        pnl_PhongThue.add(lbl_PhongThue);

        JLabel lbl_SoPhongThue = new JLabel("");
        lbl_SoPhongThue.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lbl_SoPhongThue.setBounds(156, 40, 30, 35);
        pnl_PhongThue.add(lbl_SoPhongThue);

        JPanel pnl_PhongDat = new JPanel();
        pnl_PhongDat.setBackground(new Color(255, 255, 255));
        pnl_PhongDat.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnl_PhongDat.setBounds(781, 79, 356, 100);
        add(pnl_PhongDat);
        pnl_PhongDat.setLayout(null);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(255, 0, 0));
        panel2.setBounds(1, 1, 10, 98);
        pnl_PhongDat.add(panel2);

        JLabel lbl_PhongDat = new JLabel("Số phòng đặt");
        lbl_PhongDat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_PhongDat.setBounds(23, 10, 186, 20);
        pnl_PhongDat.add(lbl_PhongDat);

        JLabel lbl_SoPhongDat = new JLabel("");
        lbl_SoPhongDat.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lbl_SoPhongDat.setBounds(159, 40, 30, 35);
        pnl_PhongDat.add(lbl_SoPhongDat);

        JPanel pnl_DanhThu = new JPanel();
        pnl_DanhThu.setBackground(new Color(255, 255, 255));
        pnl_DanhThu.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnl_DanhThu.setBounds(1160, 79, 356, 100);
        add(pnl_DanhThu);
        pnl_DanhThu.setLayout(null);

        JPanel panel3 = new JPanel();
        panel3.setBounds(1, 1, 10, 98);
        pnl_DanhThu.add(panel3);
        panel3.setBackground(new Color(0, 0, 255));

        JLabel lbl_PhongThue_2 = new JLabel("Danh thu trong ngày");
        lbl_PhongThue_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_PhongThue_2.setBounds(20, 10, 186, 20);
        pnl_DanhThu.add(lbl_PhongThue_2);

        JLabel lbl_DanhThuTrongNgay = new JLabel("");
        lbl_DanhThuTrongNgay.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lbl_DanhThuTrongNgay.setBounds(156, 40, 30, 35);
        pnl_DanhThu.add(lbl_DanhThuTrongNgay);

        mangHinhChinhController = new MangHinhChinhController(this);
        mangHinhChinhController.getDanhSachPhongDatHomNay();
    }
}

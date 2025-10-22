package views;

import javax.swing.*;
import java.awt.*;


public class MangHinhChinhPanel extends JPanel {
    public JButton btn_MH_Nhan_Vien;
    public JButton btnKhachHang;
    public MangHinhChinhPanel() {
        setLayout(new BorderLayout());
        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.add(new JLabel("Màng hình chính"));
        add(pnlTieuDe,BorderLayout.NORTH);
        JPanel pnlCenter = new JPanel();
        btnKhachHang = new JButton("Khách Hàng");
        pnlCenter.add(btnKhachHang);
        add(pnlCenter);
    }
}

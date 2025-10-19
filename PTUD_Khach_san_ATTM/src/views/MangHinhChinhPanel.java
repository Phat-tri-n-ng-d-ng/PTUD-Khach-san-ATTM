package views;

import javax.swing.*;
import java.awt.*;

public class MangHinhChinhPanel extends JPanel {
    public JButton btnNhanVien;
    public JButton btnKhachHang;

    public MangHinhChinhPanel() {
        setLayout(new BorderLayout());
        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.add(new JLabel("Màng hình chính"));
        add(pnlTieuDe,BorderLayout.NORTH);
        JPanel pnlCenter = new JPanel();
        btnNhanVien = new JButton("Nhân Viên");
        btnKhachHang = new JButton("Khách Hàng");
        pnlCenter.add(btnKhachHang);
        pnlCenter.add(btnNhanVien);
        add(pnlCenter);
    }
}

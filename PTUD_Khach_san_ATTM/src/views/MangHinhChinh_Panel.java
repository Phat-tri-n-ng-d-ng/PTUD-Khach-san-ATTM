package views;

import javax.swing.*;
import java.awt.*;

public class MangHinhChinh_Panel extends JPanel {
    public JButton btnChuyen;

    public MangHinhChinh_Panel() {
        setLayout(new BorderLayout());
        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.add(new JLabel("Màng hình chính"));
        add(pnlTieuDe,BorderLayout.NORTH);
        JPanel pnlCenter = new JPanel();
        btnChuyen = new JButton("Nhân Viên");
        add(btnChuyen, BorderLayout.CENTER);
    }
}

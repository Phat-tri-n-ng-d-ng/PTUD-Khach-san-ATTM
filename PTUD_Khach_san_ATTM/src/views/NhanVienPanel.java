package views;

import javax.swing.*;
import java.awt.*;

public class NhanVienPanel extends JPanel{
    public JButton btnBack;

    public NhanVienPanel() {
        setLayout(new BorderLayout());

        //tiêu đề
        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(new java.awt.Color(191, 227, 255, 75));
        JLabel lplTieuDe;
        pnlTieuDe.add(lplTieuDe = new JLabel("Nhân Viên"));
        lplTieuDe.setFont(new Font("Times New Roman", 1, 24));
        lplTieuDe.setForeground(new java.awt.Color(10, 110, 189));

//        Box b,b1,b2,b3;
//
//        b = Box.createVerticalBox();
//        //

        add(pnlTieuDe,BorderLayout.NORTH);
    }
}
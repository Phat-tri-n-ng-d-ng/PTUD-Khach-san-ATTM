package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    public JButton btn_MH_Nhan_Vien;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel pnl_Menu = new JPanel();
    private JLabel lbl_Nut_Close = new JLabel("X");
    private JLabel lbl_Nut_Mo_Menu = new JLabel("");
    public MainFrame() {
        setTitle("Ứng dụng quản lý khách sạn ATTM");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        pnl_Menu.setBackground(new Color(255, 255, 255));
        pnl_Menu.setBounds(0, 0, 0, 786);
        add(pnl_Menu);
        pnl_Menu.setLayout(null);
        lbl_Nut_Close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dongMenuBar();
            }

            private void dongMenuBar() {
                int height = 786;
                int width = 210;
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        for(int i = width; i > 0; i--) {
                            pnl_Menu.setSize(i, height);
                            //đóng từ từ
                            try {
                                Thread.sleep(2);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        });


        lbl_Nut_Close.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Nut_Close.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lbl_Nut_Close.setBounds(176, 10, 24, 40);
        pnl_Menu.add(lbl_Nut_Close);

        JLabel lbl_Ten_KS_Phan__Menu = new JLabel("ATTM");
        lbl_Ten_KS_Phan__Menu.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Ten_KS_Phan__Menu.setForeground(new Color(10, 110, 189));
        lbl_Ten_KS_Phan__Menu.setBackground(new Color(255, 255, 255));
        lbl_Ten_KS_Phan__Menu.setFont(new Font("Lucida Calligraphy", Font.BOLD, 24));
        lbl_Ten_KS_Phan__Menu.setBounds(24, 13, 131, 33);
        pnl_Menu.add(lbl_Ten_KS_Phan__Menu);

        JButton btn_MH_Trang_Chu = new JButton("Màn hình chính");
        btn_MH_Trang_Chu.setBackground(new Color(255, 255, 255));
        btn_MH_Trang_Chu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Trang_Chu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_MH_Trang_Chu.setBounds(10, 74, 190, 35);
        pnl_Menu.add(btn_MH_Trang_Chu);

        JButton btn_MH_Phong = new JButton("Phòng");
        btn_MH_Phong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Phong.setBounds(10, 191, 190, 35);
        pnl_Menu.add(btn_MH_Phong);

        JButton btn_MH_Dat_Thue_Phong = new JButton("Đặt/Thuê phòng");
        btn_MH_Dat_Thue_Phong.setHorizontalTextPosition(SwingConstants.CENTER);
        btn_MH_Dat_Thue_Phong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Dat_Thue_Phong.setBounds(10, 131, 190, 35);
        pnl_Menu.add(btn_MH_Dat_Thue_Phong);

        JButton btn_MH_Khuyen_Mai = new JButton("Khuyến mãi");
        btn_MH_Khuyen_Mai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Khuyen_Mai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_MH_Khuyen_Mai.setBounds(10, 247, 190, 35);
        pnl_Menu.add(btn_MH_Khuyen_Mai);

        JButton btn_MH_Hoa_Don = new JButton("Hóa đơn");
        btn_MH_Hoa_Don.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Hoa_Don.setBounds(10, 308, 190, 35);
        pnl_Menu.add(btn_MH_Hoa_Don);

        JButton btn_MH_Khach_Hang = new JButton("Khách hàng");
        btn_MH_Khach_Hang.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Khach_Hang.setBounds(10, 368, 190, 35);
        pnl_Menu.add(btn_MH_Khach_Hang);

        btn_MH_Nhan_Vien = new JButton("Nhân viên");
        btn_MH_Nhan_Vien.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Nhan_Vien.setBounds(10, 428, 190, 35);
        pnl_Menu.add(btn_MH_Nhan_Vien);

        JButton btn_MH_Thong_Ke = new JButton("Thống kê");
        btn_MH_Thong_Ke.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_MH_Thong_Ke.setBounds(10, 487, 190, 35);
        pnl_Menu.add(btn_MH_Thong_Ke);

        JSeparator sp_Duong_Ke = new JSeparator();
        sp_Duong_Ke.setBackground(new Color(0, 0, 0));
        sp_Duong_Ke.setBounds(0, 554, 210, 2);
        pnl_Menu.add(sp_Duong_Ke);

        JButton btn_Dang_Xuat = new JButton("Đăng xuất");
        btn_Dang_Xuat.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_Dang_Xuat.setBounds(10, 696, 190, 35);
        pnl_Menu.add(btn_Dang_Xuat);

        JButton btn_Tro_Giup = new JButton("Trợ giúp");
        btn_Tro_Giup.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_Tro_Giup.setBounds(10, 631, 190, 35);
        pnl_Menu.add(btn_Tro_Giup);

        JPanel pnl_Chua_Nut_Mo_Menu = new JPanel();
//        pnl_Chua_Nut_Mo_Menu.setBackground(new Color(191, 227, 255));
        pnl_Chua_Nut_Mo_Menu.setBounds(10, 10, 32, 32);
        add(pnl_Chua_Nut_Mo_Menu);
        pnl_Chua_Nut_Mo_Menu.setLayout(null);
        lbl_Nut_Mo_Menu.setBounds(0, 0, 32, 32);
        pnl_Chua_Nut_Mo_Menu.add(lbl_Nut_Mo_Menu);
        lbl_Nut_Mo_Menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                moMenuBar();
            }

            public void moMenuBar() {
                int height = 786;
                int width = 210;
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        for(int i = 0; i < width; i++) {
                            pnl_Menu.setSize(i, height);
                            //mở từ từ
                            try {
                                Thread.sleep(2);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
        lbl_Nut_Mo_Menu.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_Nut_Mo_Menu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/Steve-Zondicons-Menu.32.png.jpg")));
    }

    public void addPanel(JPanel panel, String name) {
        mainPanel.add(panel, name);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}
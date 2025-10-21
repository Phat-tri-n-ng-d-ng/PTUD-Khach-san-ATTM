package views;

import controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JPanel pnlChuaNoiDung;
    private JPanel pnlMenu = new JPanel();
    private JLabel lblNutDongMenu = new JLabel("X");
    private JLabel lblNutMoMenu = new JLabel("");
    private NutBoGoc nutDangChon = null; // Lưu nút đang được chọn
    private JPanel pnlNoiDung;
    private MainController mainController;

    public MainFrame() {
        khoiTaoGiaoDien();
        mainController = new MainController(this);
    }

    private void khoiTaoGiaoDien() {
        setTitle("Quan ly khach san-ATTM");

        // Thiết lập full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pnlChuaNoiDung = new JPanel();
        pnlChuaNoiDung.setBackground(new Color(191, 227, 255));
        setContentPane(pnlChuaNoiDung);
        pnlChuaNoiDung.setLayout(null);

        // Lấy kích thước màn hình
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        pnlMenu.setBackground(new Color(255, 255, 255));
        pnlMenu.setBounds(0, 0, 0, screenHeight);
        pnlChuaNoiDung.add(pnlMenu);
        pnlMenu.setLayout(null);

        lblNutDongMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dongThanhMenu();
            }

            // Thay đổi con trỏ khi di chuột vào nút đóng menu
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // Thay đổi con trỏ khi di chuột ra khỏi nút đóng menu
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        lblNutDongMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lblNutDongMenu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblNutDongMenu.setBounds(176, 10, 24, 40);
        pnlMenu.add(lblNutDongMenu);

        JLabel lblTenKhachSanMenu = new JLabel("ATTM");
        lblTenKhachSanMenu.setHorizontalAlignment(SwingConstants.CENTER);
        lblTenKhachSanMenu.setForeground(new Color(10, 110, 189));
        lblTenKhachSanMenu.setBackground(new Color(255, 255, 255));
        lblTenKhachSanMenu.setFont(new Font("Lucida Calligraphy", Font.BOLD, 24));
        lblTenKhachSanMenu.setBounds(24, 13, 131, 33);
        pnlMenu.add(lblTenKhachSanMenu);

        // THÊM: Panel hiển thị thông tin tài khoản
        JPanel pnlTaiKhoan = new JPanel();
        pnlTaiKhoan.setBackground(new Color(240, 240, 240));
        pnlTaiKhoan.setBounds(10, 50, 190, 80);
        pnlTaiKhoan.setLayout(null);
        pnlTaiKhoan.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        pnlMenu.add(pnlTaiKhoan);

        // THÊM: Ảnh đại diện (placeholder)
        JLabel lblAnhDaiDien = new JLabel();
        lblAnhDaiDien.setBounds(10, 10, 40, 40);
        lblAnhDaiDien.setBackground(new Color(200, 200, 200));
        lblAnhDaiDien.setOpaque(true);
        lblAnhDaiDien.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        pnlTaiKhoan.add(lblAnhDaiDien);

        // THÊM: Tên người dùng
        JLabel lblTenNguoiDung = new JLabel("Admin");
        lblTenNguoiDung.setBounds(60, 10, 120, 20);
        lblTenNguoiDung.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pnlTaiKhoan.add(lblTenNguoiDung);

        // THÊM: Chức vụ
        JLabel lblChucVu = new JLabel("Quản lý");
        lblChucVu.setBounds(60, 30, 120, 20);
        lblChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblChucVu.setForeground(new Color(100, 100, 100));
        pnlTaiKhoan.add(lblChucVu);

        int startY = 140;

        // Các nút trong menu - ĐÃ ĐIỀU CHỈNH VỊ TRÍ
        NutBoGoc btnTrangChu = new NutBoGoc("Màn hình chính");
        btnTrangChu.setBounds(10, startY, 190, 35);
        pnlMenu.add(btnTrangChu);

        NutBoGoc btnDatThuePhong = new NutBoGoc("Đặt/Thuê phòng");
        btnDatThuePhong.setBounds(10, startY + 45, 190, 35);
        pnlMenu.add(btnDatThuePhong);

        NutBoGoc btnPhong = new NutBoGoc("Phòng");
        btnPhong.setBounds(10, startY + 90, 190, 35);
        pnlMenu.add(btnPhong);

        // THÊM: Nút Loại phòng
        NutBoGoc btnLoaiPhong = new NutBoGoc("Loại phòng");
        btnLoaiPhong.setBounds(10, startY + 135, 190, 35);
        pnlMenu.add(btnLoaiPhong);

        NutBoGoc btnKhuyenMai = new NutBoGoc("Khuyến mãi");
        btnKhuyenMai.setBounds(10, startY + 180, 190, 35);
        pnlMenu.add(btnKhuyenMai);

        NutBoGoc btnHoaDon = new NutBoGoc("Hóa đơn");
        btnHoaDon.setBounds(10, startY + 225, 190, 35);
        pnlMenu.add(btnHoaDon);

        NutBoGoc btnKhachHang = new NutBoGoc("Khách hàng");
        btnKhachHang.setBounds(10, startY + 270, 190, 35);
        pnlMenu.add(btnKhachHang);

        NutBoGoc btnNhanVien = new NutBoGoc("Nhân viên");
        btnNhanVien.setBounds(10, startY + 315, 190, 35);
        pnlMenu.add(btnNhanVien);

        NutBoGoc btnThongKe = new NutBoGoc("Thống kê");
        btnThongKe.setBounds(10, startY + 360, 190, 35);
        pnlMenu.add(btnThongKe);

        JSeparator duongKe = new JSeparator();
        duongKe.setBackground(Color.BLACK);
        duongKe.setBounds(0, startY + 425, 210, 2);
        pnlMenu.add(duongKe);

        NutBoGoc btnTroGiup = new NutBoGoc("Trợ giúp");
        btnTroGiup.setBounds(10, startY + 435, 190, 35);
        pnlMenu.add(btnTroGiup);

        NutBoGoc btnDangXuat = new NutBoGoc("Đăng xuất");
        btnDangXuat.setBounds(10, startY + 480, 190, 35);
        pnlMenu.add(btnDangXuat);

        // Giữ nguyên vị trí nút mở menu (10,10)
        JPanel pnlChuaNutMoMenu = new JPanel();
        pnlChuaNutMoMenu.setBackground(new Color(236, 247, 255));
        pnlChuaNutMoMenu.setBounds(10, 10, 32, 32);
        pnlChuaNoiDung.add(pnlChuaNutMoMenu);
        pnlChuaNutMoMenu.setLayout(null);
        lblNutMoMenu.setBounds(0, 0, 32, 32);
        pnlChuaNutMoMenu.add(lblNutMoMenu);

        // Sự kiện mở menu
        lblNutMoMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                moThanhMenu();
            }

            // Thay đổi con trỏ khi di chuột vào nút mở menu
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            // Thay đổi con trỏ khi di chuột ra khỏi nút mở menu
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        try {
            lblNutMoMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/Steve-Zondicons-Menu.32.png")));
        } catch (Exception e) {
            System.out.println("Lỗi tải biểu tượng menu: " + e.getMessage());
        }

        lblNutMoMenu.setHorizontalAlignment(SwingConstants.CENTER);

        pnlNoiDung = new JPanel();
        pnlNoiDung.setBackground(new Color(191, 227, 255));
        pnlNoiDung.setBounds(0, 0, screenWidth, screenHeight);
        pnlChuaNoiDung.add(pnlNoiDung);
        pnlNoiDung.setLayout(new BorderLayout());

        // Đảm bảo menu luôn ở trên cùng
        pnlChuaNoiDung.setComponentZOrder(pnlMenu, 0);
        pnlChuaNoiDung.setComponentZOrder(pnlChuaNutMoMenu, 1);
        pnlChuaNoiDung.setComponentZOrder(pnlNoiDung, 2);

//        JLabel lblTest = new JLabel("NOI DUNG CHINH - Click ☰ de mo menu");
//        lblTest.setFont(new Font("Segoe UI", Font.BOLD, 24));
//        lblTest.setForeground(new Color(0, 51, 102));
//        lblTest.setHorizontalAlignment(SwingConstants.CENTER);
//        lblTest.setBounds((screenWidth - 900) / 2, (screenHeight - 50) / 2, 900, 50);
//        pnlNoiDung.add(lblTest);

        // Thêm sự kiện cho các nút menu
        themSuKienChoNut(btnTrangChu, "TrangChu");
        themSuKienChoNut(btnDatThuePhong, "DatThuePhong");
        themSuKienChoNut(btnPhong, "Phong");
        themSuKienChoNut(btnLoaiPhong, "LoaiPhong"); // THÊM SỰ KIỆN CHO NÚT LOẠI PHÒNG
        themSuKienChoNut(btnKhuyenMai , "KhuyenMai");
        themSuKienChoNut(btnHoaDon, "HoaDon");
        themSuKienChoNut(btnKhachHang, "KhachHang");
        themSuKienChoNut(btnNhanVien, "NhanVien");
        themSuKienChoNut(btnThongKe, "ThongKe");
        themSuKienChoNut(btnTroGiup, "TroGiup");
        themSuKienChoNut(btnDangXuat, "DangXuat");

        // Sự kiện khi thay đổi kích thước cửa sổ
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                mainController.onWindowResized();
            }
        });
    }

    private void themSuKienChoNut(NutBoGoc nut, String action) {
        nut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Khi nhấn vào nút, đặt nút đó là được chọn
                datNutDuocChon(nut);
                // Thực hiện hành động khi chọn nút (tùy theo nút)
                // Ví dụ: thay đổi nội dung panel pnlNoiDung
                xuLyChonMenu(action);
            }
        });
    }

    private void xuLyChonMenu(String action) {
        switch (action) {
//            case "trangChu":
//                mainController.showTrangChu();
//                break;
//            case "datThuePhong":
//                mainController.showDatThuePhong();
//                break;
            case "Phong":
                mainController.showPhong_Panel();
                break;
            case "LoaiPhong": // THÊM CASE CHO LOẠI PHÒNG
                mainController.showLoai_Phong_Panel();
                break;
//            case "khuyenMai":
//                mainController.showKhuyenMai();
//                break;
            case "HoaDon":
                mainController.showHoa_Don_Panel();
                break;
            case "KhachHang":
                mainController.showKhach_Hang_Panel();
                break;
            case "NhanVien":
                mainController.showNhan_Vien_Panel();
                break;
//            case "thongKe":
//                mainController.showThongKe();
//                break;
//            case "troGiup":
//                mainController.showTroGiup();
//                break;
//            case "dangXuat":
//                mainController.showDangXuat();
//                break;
        }
        dongThanhMenu();
    }

    private void datNutDuocChon(NutBoGoc nutMoi) {
        // Nếu đã có nút được chọn trước đó, thì trả về màu bình thường
        if (nutDangChon != null) {
            nutDangChon.setTrangThaiBinhThuong();
        }
        // Đặt nút mới được chọn
        nutDangChon = nutMoi;
        nutDangChon.setTrangThaiDuocChon();
    }

    private void moThanhMenu() {
        int chieuCao = getHeight();
        int chieuRong = 210;
        new Thread(() -> {
            for (int i = 0; i < chieuRong; i++) {
                pnlMenu.setSize(i, chieuCao);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    private void dongThanhMenu() {
        int chieuCao = getHeight();
        int chieuRong = 210;
        new Thread(() -> {
            for (int i = chieuRong; i > 0; i--) {
                pnlMenu.setSize(i, chieuCao);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    public JPanel getPnlNoiDung() {
        return pnlNoiDung;
    }

    class NutBoGoc extends JButton {
        private Color mauNenHover = new Color(191, 227, 255);
        private Color mauNenNhan = new Color(150, 200, 255);
        private Color mauNenThuong = Color.WHITE;
        private Color mauChuThuong = Color.BLACK;
        private Color mauChuHover = new Color(0, 51, 102);
        private Color mauNenDuocChon = new Color(100, 180, 255); // Màu đậm hơn khi được chọn
        private Color mauChuDuocChon = Color.WHITE; // Chữ trắng khi được chọn

        private boolean duocChon = false;

        public NutBoGoc(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setBackground(mauNenThuong);
            setForeground(mauChuThuong);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setHorizontalAlignment(SwingConstants.LEFT);
            setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

            // Sự kiện di chuột
            addMouseListener(new MouseAdapter() {
                // Khi di chuột vào nút
                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                // Khi di chuột ra khỏi nút
                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }

        public void setTrangThaiDuocChon() {
            duocChon = true;
            setBackground(mauNenDuocChon);
            setForeground(mauChuDuocChon);
            repaint();
        }

        public void setTrangThaiBinhThuong() {
            duocChon = false;
            setBackground(mauNenThuong);
            setForeground(mauChuThuong);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (duocChon) {
                g2.setColor(mauNenDuocChon);
            } else if (getModel().isPressed()) {
                g2.setColor(mauNenNhan);
            } else if (getModel().isRollover()) {
                g2.setColor(mauNenHover);
            } else {
                g2.setColor(getBackground());
            }

            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
            // Không làm gì cả
        }
    }

//    public void addPanel(JPanel panel, String name) {
//        mainPanel.add(panel, name);
//    }
//
//    public void showPanel(String name) {
//        cardLayout.show(mainPanel, name);
//    }
}
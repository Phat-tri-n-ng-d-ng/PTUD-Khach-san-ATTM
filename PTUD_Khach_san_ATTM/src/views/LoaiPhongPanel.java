package views;

import controller.LoaiPhongController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class LoaiPhongPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    public JTextField txt_TyLeCoc;
    public JTextField txt_GiaNiemYet;
    public JTextField txt_TenLoaiPhong;
    public JTable table;
    public DefaultTableModel model;
    LoaiPhongController loaiPhongController;
    public JButton btn_Them, btn_CapNhat;


    public LoaiPhongPanel() {
        setBounds(100, 100, 1336, 768);
        setBackground(new Color(236, 247, 255));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JLabel lbl_TieuDe = new JLabel("Loại phòng");
        lbl_TieuDe.setBackground(new Color(240, 240, 240));
        lbl_TieuDe.setForeground(new Color(10, 100, 189));
        lbl_TieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lbl_TieuDe.setBounds(725, 10, 166, 25);
        add(lbl_TieuDe);

        JPanel pnlThongTinLoaiPhong = new JPanel();
        pnlThongTinLoaiPhong.setBackground(new Color(255, 255, 255));
        pnlThongTinLoaiPhong.setBorder(new LineBorder(new Color(0, 0, 0)));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        int doDaiThongTinLoaiPhong = screenSize.width - 40;
        pnlThongTinLoaiPhong.setBounds(20, 87,1496 , 149);
        add(pnlThongTinLoaiPhong);
        pnlThongTinLoaiPhong.setLayout(null);
        int doDaiTxt = doDaiThongTinLoaiPhong/2 - 20;

        txt_GiaNiemYet = new JTextField();
        txt_GiaNiemYet.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_GiaNiemYet.setColumns(10);
        txt_GiaNiemYet.setBounds(526, 33, 450, 30);
        pnlThongTinLoaiPhong.add(txt_GiaNiemYet);

        JLabel lbl_GiaNiemYet = new JLabel("Giá niêm yết:");
        lbl_GiaNiemYet.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_GiaNiemYet.setBounds(526, 10, 104, 19);
        pnlThongTinLoaiPhong.add(lbl_GiaNiemYet);

        JLabel lbl_TenLoaiPhong = new JLabel("Tên loại phòng:");
        lbl_TenLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lbl_TenLoaiPhong.setBounds(10, 10, 104, 19);
        pnlThongTinLoaiPhong.add(lbl_TenLoaiPhong);

        txt_TenLoaiPhong = new JTextField();
        txt_TenLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TenLoaiPhong.setColumns(10);
        txt_TenLoaiPhong.setBounds(10, 33, 450, 30);
        pnlThongTinLoaiPhong.add(txt_TenLoaiPhong);

        btn_Them = new JButton("Thêm");
        btn_Them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_Them.setBounds(1322, 93, 120, 30);
        pnlThongTinLoaiPhong.add(btn_Them);

        btn_CapNhat = new JButton("Cập nhật");
        btn_CapNhat.setBounds(1157, 93, 120, 30);
        pnlThongTinLoaiPhong.add(btn_CapNhat);

        JLabel lpl_TyLeCoc = new JLabel("Tỷ lệ cọc:");
        lpl_TyLeCoc.setBounds(1036, 10, 104, 20);
        pnlThongTinLoaiPhong.add(lpl_TyLeCoc);
        lpl_TyLeCoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        txt_TyLeCoc = new JTextField();
        txt_TyLeCoc.setBounds(1036, 33, 450, 30);
        pnlThongTinLoaiPhong.add(txt_TyLeCoc);
        txt_TyLeCoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txt_TyLeCoc.setColumns(10);

        JLabel lbl_ThongTinLoaiPhong = new JLabel("Thông tin loại phòng");
        lbl_ThongTinLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_ThongTinLoaiPhong.setBounds(20, 57, 197, 20);
        add(lbl_ThongTinLoaiPhong);

        JLabel lbl_DanhSachLoaiPhong = new JLabel("Danh sách loại phòng");
        lbl_DanhSachLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lbl_DanhSachLoaiPhong.setBounds(20, 258, 180, 20);
        add(lbl_DanhSachLoaiPhong);

        JPanel pnlDanhSachLoaiPhong = new JPanel();
        pnlDanhSachLoaiPhong.setBackground(new Color(255, 255, 255));
        pnlDanhSachLoaiPhong.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlDanhSachLoaiPhong.setBounds(20, 292, 1496, 485);
        add(pnlDanhSachLoaiPhong);

        model = new DefaultTableModel(new String[] {"Mã loại phòng","Tên loại phòng","Giá niêm yết","Tỷ lệ cọc"}, 0);
        pnlDanhSachLoaiPhong.setLayout(null);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 1476, 466);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times New Roman", Font.BOLD, 18));
        header.setBackground(new Color(10, 100, 189));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        pnlDanhSachLoaiPhong.add(scrollPane);

//        btn_Them.addActionListener(this);
//        btn_CapNhat.addActionListener(this);

        loaiPhongController= new LoaiPhongController(this);
        loaiPhongController.getDanhDachLoaiPhong();


    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object o = e.getSource();
//        if(o== btn_Them){
//            loaiPhongController.themLoaiPhong();
//        }
//    }


}
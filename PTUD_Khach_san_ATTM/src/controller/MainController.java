package controller;


import views.KhachHangPanel;
import views.MainFrame;
import views.NhanVienPanel;

import javax.swing.*;
import java.awt.*;

public class MainController {
    private MainFrame mainFrame;
    private JPanel pnl_Theo_Doi_Panel_Dang_Hien_Thi;

    // Chổ để khai báo các JPanel khác của app
    private JPanel nhanVienPanel;
    private JPanel khachHangPanel;


    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        khoi_Tao_Cac_Panel();
//        showTrang_Chu();
    }

    private void khoi_Tao_Cac_Panel() {
        // Khởi tạo các JPanel khác của app
        nhanVienPanel = new NhanVienPanel();
        khachHangPanel = new KhachHangPanel();
    }


//    private void showTrang_Chu() {
//
//    }

    public void showKhach_Hang_Panel() {
        doi_Panel(khachHangPanel);
    }
    public void showNhan_Vien_Panel() {
        doi_Panel(nhanVienPanel);

    }

    private void doi_Panel(JPanel panel_Moi) {
        if (pnl_Theo_Doi_Panel_Dang_Hien_Thi != null) {
            mainFrame.getPnlNoiDung().remove(pnl_Theo_Doi_Panel_Dang_Hien_Thi);
        }
        pnl_Theo_Doi_Panel_Dang_Hien_Thi = panel_Moi;

        if(pnl_Theo_Doi_Panel_Dang_Hien_Thi.getLayout() == null){
            pnl_Theo_Doi_Panel_Dang_Hien_Thi.setBounds(0,0,
                    mainFrame.getPnlNoiDung().getWidth(),
                    mainFrame.getPnlNoiDung().getHeight());
            mainFrame.getPnlNoiDung().add(pnl_Theo_Doi_Panel_Dang_Hien_Thi);
        }else{
            mainFrame.getPnlNoiDung().add(pnl_Theo_Doi_Panel_Dang_Hien_Thi, BorderLayout.CENTER);
        }
        mainFrame.getPnlNoiDung().revalidate();
        mainFrame.getPnlNoiDung().repaint();
    }

    /*
        "Khi người dùng kéo to/nhỏ cửa sổ chương trình, phương thức này sẽ tự
        động điều chỉnh kích thước của panel đang hiển thị để LUÔN VỪA KHÍT với cửa sổ."
    */
    public void onWindowResized() {
        if (pnl_Theo_Doi_Panel_Dang_Hien_Thi != null &&
                pnl_Theo_Doi_Panel_Dang_Hien_Thi.getLayout() == null) {
            pnl_Theo_Doi_Panel_Dang_Hien_Thi.setBounds(0, 0,
                    mainFrame.getPnlNoiDung().getWidth(),
                    mainFrame.getPnlNoiDung().getHeight());
        }
    }
}
package controller;

import views.KhachHangPanel;
import views.MangHinhChinhPanel;
import views.MainFrame;
import views.NhanVienPanel;

public class MangHinhChinhController {
    private MainFrame mainFrame;
    private MangHinhChinhPanel homePanel;
    private NhanVienPanel nhanVienPanel;
    private KhachHangPanel khachHangPanel;

    public MangHinhChinhController(MainFrame frame) {
        this.mainFrame = frame;
        this.homePanel = new MangHinhChinhPanel();
        this.nhanVienPanel = new NhanVienPanel();
        this.khachHangPanel = new KhachHangPanel();

        mainFrame.addPanel(homePanel, "home");
        mainFrame.addPanel(nhanVienPanel, "NhanVien");
        mainFrame.addPanel(khachHangPanel, "KhachHang");

        addEventHandlers();
        mainFrame.showPanel("home");
    }

    private void addEventHandlers() {
        homePanel.btnKhachHang.addActionListener(e -> mainFrame.showPanel("KhachHang"));
        homePanel.btnNhanVien.addActionListener(e -> mainFrame.showPanel("NhanVien"));
//        xuLyPanel.btnBack.addActionListener(e -> mainFrame.showPanel("home"));
    }
}
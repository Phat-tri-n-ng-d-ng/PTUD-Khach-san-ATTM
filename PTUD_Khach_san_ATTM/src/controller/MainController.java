package controller;

import views.MangHinhChinh_Panel;
import views.MainFrame;
import views.NhanVienPanel;

public class MainController {
    private MainFrame mainFrame;
    private MangHinhChinh_Panel homePanel;
    private NhanVienPanel nhanVienPanel;

    public MainController(MainFrame frame) {
        this.mainFrame = frame;
        this.homePanel = new MangHinhChinh_Panel();
        this.nhanVienPanel = new NhanVienPanel();

        mainFrame.addPanel(homePanel, "home");
        mainFrame.addPanel(nhanVienPanel, "xuly");

        addEventHandlers();
        mainFrame.showPanel("home");
    }

    private void addEventHandlers() {
        homePanel.btnChuyen.addActionListener(e -> mainFrame.showPanel("xuly"));
//        xuLyPanel.btnBack.addActionListener(e -> mainFrame.showPanel("home"));
    }
}
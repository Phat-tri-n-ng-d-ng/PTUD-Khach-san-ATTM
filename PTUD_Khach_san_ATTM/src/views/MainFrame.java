package views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Ứng dụng quản lý khách sạn ATTM");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        add(mainPanel);
    }

    public void addPanel(JPanel panel, String name) {
        mainPanel.add(panel, name);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}
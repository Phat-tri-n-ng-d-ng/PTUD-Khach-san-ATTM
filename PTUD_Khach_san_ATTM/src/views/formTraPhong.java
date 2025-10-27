package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;

public class formTraPhong extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formTraPhong frame = new formTraPhong();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public formTraPhong() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(61, 71, 45, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_Tittle = new JLabel("Thông tin trả phòng");
		lbl_Tittle.setBounds(616, 10, 204, 29);
		lbl_Tittle.setForeground(SystemColor.textHighlight);
		lbl_Tittle.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(lbl_Tittle);

	}

}

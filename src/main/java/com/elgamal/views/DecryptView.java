package com.elgamal.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.elgamal.contexts.CypherContext;
import com.elgamal.cypher.Elgamal;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.math.BigInteger;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DecryptView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea inputC1;
	private JTextArea inputXa;
	private JTextArea outputM;

	public DecryptView() {
		this("");
	}
	
	public DecryptView(String C1) {
		setCurrentFrame(this);
		setTitle("Giải mã Elgamal");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 819, 616);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblGiiM = new JLabel("Giải mã");
		lblGiiM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1_1 = new JLabel("Bản mã:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Khóa bí mật Xa:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Giải mã");
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.addActionListener(e -> handleDecode());
		
		JButton btntLi = new JButton("Đặt lại");
		btntLi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btntLi.setForeground(Color.WHITE);
		btntLi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btntLi.setFocusable(false);
		btntLi.setBackground(Color.GRAY);
		btntLi.addActionListener(e -> handleReset());
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Bản rõ:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		
		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGiiM, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(scrollPane_1_1_1, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
									.addComponent(scrollPane_1_1, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btntLi, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_1_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblGiiM, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(102)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addGap(153)
							.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane_1_1_1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(81)
							.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btntLi, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		outputM = new JTextArea();
		outputM.setEditable(false);
		outputM.setWrapStyleWord(true);
		outputM.setLineWrap(true);
		scrollPane_1_1_1.setViewportView(outputM);
		
		inputXa = new JTextArea();
		inputXa.setWrapStyleWord(true);
		inputXa.setLineWrap(true);
		scrollPane_1_1.setViewportView(inputXa);
		
		inputC1 = new JTextArea();
		inputC1.setWrapStyleWord(true);
		inputC1.setLineWrap(true);
		scrollPane_1.setViewportView(inputC1);
		inputC1.setText(C1);
		contentPane.setLayout(gl_contentPane);
	}
	
	private boolean checkValid() {
		String C1 = inputC1.getText();
		String strX = inputXa.getText();
		if(C1 == null || C1.equals("")) {
			JOptionPane.showConfirmDialog(this, "Bản mã không hợp lệ!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(strX == null || strX.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập khóa bí mật!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {	
			try {
				BigInteger x = new BigInteger(strX);
			} catch(Exception e) {
				JOptionPane.showConfirmDialog(this, "Khóa bí mật là số nguyên!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		return true;
	}
	
	private void handleDecode() {
		if(checkValid()) {
			String C1 = inputC1.getText();
			String strX = inputXa.getText();
			BigInteger q = CypherContext.getPublicKey().getQ();
			String M = Elgamal.decryptV2(C1, new BigInteger(strX), q);
			outputM.setText(M);
		}
	}
	
	private void handleReset() {
		inputC1.setText("");
		inputXa.setText("");
		outputM.setText("");
	}
	
	private static DecryptView view;
	
	public static DecryptView getCurrentFrame() {
		return view;
	}
	
	public static void setCurrentFrame(DecryptView v) {
		view = v;
	}
	
}

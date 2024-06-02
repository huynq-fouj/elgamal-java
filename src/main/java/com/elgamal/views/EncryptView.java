package com.elgamal.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import javax.swing.border.TitledBorder;

import com.elgamal.contexts.CypherContext;
import com.elgamal.cypher.Elgamal;
import com.elgamal.cypher.PublicKey;

import javax.swing.border.EtchedBorder;
import java.awt.Cursor;

public class EncryptView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea inputA;
	private JTextArea inputXa;
	private JTextArea outputYa;
	private JTextArea inputQ;
	private JTextArea inputM;
	private JTextArea inputK;
	private JTextArea outputC1;
	private static final int BITLENGTH = 1024;//Độ dài số nguyên tố q
	private static final SecureRandom random = new SecureRandom();
	private String C;

	/**
	 * Create the frame.
	 */
	public EncryptView() {
		setResizable(false);
		setTitle("Mã hóa Elgamal");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Generate Key", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Decrypt", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		
		JButton btnGii = new JButton("Gửi đi");
		btnGii.setForeground(Color.WHITE);
		btnGii.setFont(new Font("Arial", Font.BOLD, 14));
		btnGii.setFocusable(false);
		btnGii.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnGii.setBackground(new Color(0, 128, 255));
		btnGii.addActionListener(e -> navigateDecryptView());
		
		JButton btnSKNgu = new JButton("Số k ngẫu nhiên");
		btnSKNgu.setForeground(Color.WHITE);
		btnSKNgu.setFont(new Font("Arial", Font.BOLD, 14));
		btnSKNgu.setFocusable(false);
		btnSKNgu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSKNgu.setBackground(new Color(33, 171, 36));
		btnSKNgu.addActionListener(e -> handleRandomK());
		
		JButton btnMHa = new JButton("Mã hóa");
		btnMHa.setForeground(Color.WHITE);
		btnMHa.setFont(new Font("Arial", Font.BOLD, 14));
		btnMHa.setFocusable(false);
		btnMHa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMHa.setBackground(new Color(0, 128, 255));
		btnMHa.addActionListener(e -> handleEncode());
		
		JLabel lblNewLabel_1_1 = new JLabel("Bản rõ M:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMHa = new JLabel("Mã hóa");
		lblMHa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2_1 = new JLabel("Số k:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1_1_2 = new JScrollPane();
		
		JScrollPane scrollPane_2_2_1_2 = new JScrollPane();
		
		JScrollPane scrollPane_2_2_2 = new JScrollPane();
		
		JLabel lblBnM = new JLabel("Bản mã");
		lblBnM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGii, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_2_2_1_1_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
						.addComponent(scrollPane_2_2_1_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
						.addComponent(scrollPane_2_2_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnSKNgu, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMHa, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMHa, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1, Alignment.LEADING)
						.addComponent(lblBnM, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblMHa, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2_2_2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2_2_1_2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMHa, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSKNgu, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblBnM, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2_2_1_1_2, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGii, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		
		outputC1 = new JTextArea();
		outputC1.setWrapStyleWord(true);
		outputC1.setEditable(false);
		outputC1.setSelectionColor(new Color(0, 128, 255));
		outputC1.setSelectedTextColor(new Color(255, 255, 255));
		outputC1.setLineWrap(true);
		outputC1.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane_2_2_1_1_2.setViewportView(outputC1);
		
		inputK = new JTextArea();
		inputK.setWrapStyleWord(true);
		inputK.setSelectionColor(new Color(0, 128, 255));
		inputK.setSelectedTextColor(new Color(255, 255, 255));
		inputK.setLineWrap(true);
		inputK.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane_2_2_1_2.setViewportView(inputK);
		
		inputM = new JTextArea();
		inputM.setWrapStyleWord(true);
		inputM.setSelectionColor(new Color(0, 128, 255));
		inputM.setSelectedTextColor(new Color(255, 255, 255));
		inputM.setLineWrap(true);
		inputM.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane_2_2_2.setViewportView(inputM);
		panel_1.setLayout(gl_panel_1);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 702, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 651, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("Sinh khóa");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Số nguyên tố q:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2 = new JScrollPane();
		
		inputQ = new JTextArea();
		inputQ.setSelectionColor(new Color(0, 128, 255));
		inputQ.setSelectedTextColor(new Color(255, 255, 255));
		inputQ.setBorder(new EmptyBorder(5, 5, 5, 5));
		inputQ.setWrapStyleWord(true);
		inputQ.setLineWrap(true);
		scrollPane_2_2.setViewportView(inputQ);
		
		JLabel lblNewLabel_2 = new JLabel("a:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1 = new JScrollPane();
		
		JLabel lblNewLabel_3 = new JLabel("Xa:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1_1 = new JScrollPane();
		
		JLabel lblNewLabel_3_1 = new JLabel("Ya:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1_1_1 = new JScrollPane();
		
		JButton btnNewButton = new JButton("Tạo khóa");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setFocusable(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.addActionListener(e -> handleCreateKey());
		
		JButton btnSinhKhaNgu = new JButton("Sinh khóa ngẫu nhiên");
		btnSinhKhaNgu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSinhKhaNgu.setForeground(Color.WHITE);
		btnSinhKhaNgu.setFont(new Font("Arial", Font.BOLD, 14));
		btnSinhKhaNgu.setFocusable(false);
		btnSinhKhaNgu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSinhKhaNgu.setBackground(new Color(33, 171, 36));
		btnSinhKhaNgu.addActionListener(e -> handleRandomKey());
		
		JButton btntLi = new JButton("Đặt lại");
		btntLi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btntLi.setForeground(Color.WHITE);
		btntLi.setFont(new Font("Arial", Font.BOLD, 14));
		btntLi.setFocusable(false);
		btntLi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btntLi.setBackground(new Color(128, 128, 128));
		btntLi.addActionListener(e -> handleReset());
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_2_2_1_1, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
								.addComponent(scrollPane_2_2_1, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
								.addComponent(scrollPane_2_2, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSinhKhaNgu, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btntLi, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, Alignment.LEADING)
								.addComponent(lblNewLabel_3, Alignment.LEADING)
								.addComponent(scrollPane_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2_2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2_2_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2_2_1_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2_2_1_1_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSinhKhaNgu, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btntLi, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		
		outputYa = new JTextArea();
		outputYa.setEditable(false);
		outputYa.setWrapStyleWord(true);
		outputYa.setSelectionColor(new Color(0, 128, 255));
		outputYa.setSelectedTextColor(new Color(255, 255, 255));
		outputYa.setLineWrap(true);
		outputYa.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane_2_2_1_1_1.setViewportView(outputYa);
		
		inputXa = new JTextArea();
		inputXa.setWrapStyleWord(true);
		inputXa.setSelectionColor(new Color(0, 128, 255));
		inputXa.setSelectedTextColor(new Color(255, 255, 255));
		inputXa.setLineWrap(true);
		inputXa.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane_2_2_1_1.setViewportView(inputXa);
		
		inputA = new JTextArea();
		inputA.setWrapStyleWord(true);
		inputA.setSelectionColor(new Color(0, 128, 255));
		inputA.setSelectedTextColor(new Color(255, 255, 255));
		inputA.setLineWrap(true);
		inputA.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane_2_2_1.setViewportView(inputA);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	private boolean checkValidKey() {
		String strQ = inputQ.getText();
		String strA = inputA.getText();
		String strX = inputXa.getText();
		//Q
		if(strQ == null || strQ.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập số nguyên tố q đủ lớn!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			try {
				BigInteger q = new BigInteger(strQ);
				if(!q.isProbablePrime(BITLENGTH)) {
					JOptionPane.showConfirmDialog(this, "Số q phải là số nguyên tố!", "Error",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, "Số q không đúng định dạng số nguyên!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		//A
		if(strA == null || strA.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập số nguyên a (0 < a < q)!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			try {
				BigInteger a = new BigInteger(strA);
				BigInteger q = new BigInteger(strQ);
				if(a.compareTo(new BigInteger("0")) <= 0 || a.compareTo(q) >= 0) {
					JOptionPane.showConfirmDialog(this, "Số a phải là căn nguyên thủy của q (a < p và a > 0)!", "Error",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
					return false;
				} else {
					BigInteger g = a.gcd(q);
					BigInteger i1 = new BigInteger("1");
					if(g.compareTo(i1) != 0) {
						JOptionPane.showConfirmDialog(this, "Số a phải là căn nguyên thủy của q!", "Error",
								JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, "Số a không đúng định dạng số nguyên!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		//X
		if(strX == null || strX.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập khóa bí mật x (0 < x < q - 1)!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			try {
				BigInteger x = new BigInteger(strX);
				BigInteger i0 = new BigInteger("0");
				BigInteger q1 = new BigInteger(strQ).subtract(new BigInteger("1"));
				if(x.compareTo(i0) <= 0 || x.compareTo(q1) >= 0) {
					JOptionPane.showConfirmDialog(this, "Khóa bí mật x phải nằm trong khoảng (0 < x < q - 1)!", "Error",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, "Số x không đúng định dạng số nguyên!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	private void handleCreateKey() {
		if(checkValidKey()) {
			String strQ = inputQ.getText();
			String strA = inputA.getText();
			String strX = inputXa.getText();
			BigInteger q = new BigInteger(strQ);
			BigInteger a = new BigInteger(strA);
			BigInteger x = new BigInteger(strX);
			BigInteger y = a.modPow(x, q);
			CypherContext.setPublicKey(q, a, y);
			CypherContext.setPrivateKey(x);
			outputYa.setText(y.toString());
		}
	}
	
	private void handleRandomKey() {
		BigInteger q = BigInteger.probablePrime(BITLENGTH, random);
		BigInteger a = new BigInteger(BITLENGTH - 1, random);
		BigInteger x = new BigInteger(BITLENGTH - 2, random);
		BigInteger y = a.modPow(x, q);
		CypherContext.setPublicKey(q, a, y);
		CypherContext.setPrivateKey(x);
		
		inputQ.setText(q.toString());
		inputA.setText(a.toString());
		inputXa.setText(x.toString());
		outputYa.setText(y.toString());
	}
	
	private void handleRandomK() {
		PublicKey pk = CypherContext.getPublicKey();
		if(pk != null && pk.getQ() != null) {	
			int length = pk.getQ().bitLength();
			BigInteger k = new BigInteger(length - 1, random);
			inputK.setText(k.toString());
		} else {
			JOptionPane.showConfirmDialog(this, "Khóa công khai không hợp lệ!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void handleReset() {
		inputQ.setText("");
		inputA.setText("");
		inputXa.setText("");
		outputYa.setText("");
		inputM.setText("");
		inputK.setText("");
		outputC1.setText("");
		CypherContext.setPublicKey(null);
		CypherContext.setPrivateKey(null);
	}
	
	private boolean checkK() {
		String strK = inputK.getText();
		if(strK == null || strK.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập số k!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				BigInteger k = new BigInteger(strK);
				BigInteger q = CypherContext.getPublicKey().getQ();
				if(k.compareTo(new BigInteger("0")) <= 0 || k.compareTo(q) >= 0) {
					JOptionPane.showConfirmDialog(this, "Số k phải nằm trong khoảng 0 < k < q!", "Error",
							JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, "Số k không đúng định dạng số nguyên!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	private boolean checkBitLength(String message, BigInteger q) {
		int qLength = q.bitLength();
		int mLength = new BigInteger((message.charAt(0) + "").getBytes(StandardCharsets.UTF_8)).bitLength();
		int vt = 0;
		for(int i = 0; i < message.length(); i++) {
			BigInteger M = new BigInteger((message.charAt(i) + "").getBytes(StandardCharsets.UTF_8));
			if(M.bitLength() > mLength) {
				mLength = M.bitLength();
				vt = i;
			}
		}
		
		if(mLength > 7) {
			mLength = new BigInteger((" " + message.charAt(vt)).getBytes(StandardCharsets.UTF_8)).bitLength();
		}
		
		if(mLength >= qLength) {
			JOptionPane.showConfirmDialog(this, "Kích thước số q ("+qLength+" bit) không đủ để mã hóa bản rõ (min q > "+mLength+" bit)!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private void handleEncode() {
		PublicKey pk = CypherContext.getPublicKey();
		if(pk != null) {
			String message = inputM.getText();
			if(message != null && !message.equals("")) {
				if(!checkBitLength(message, pk.getQ())) return;
				if(checkK()) {
					String strK = inputK.getText();
					C = Elgamal.encryptV2(message, pk, new BigInteger(strK));
					outputC1.setText(C);
				}
			} else {
				JOptionPane.showConfirmDialog(this, "Vui lòng nhập bản rõ!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showConfirmDialog(this, "Khóa công khai không hợp lệ!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void navigateDecryptView() {
		String C = outputC1.getText();
		if(C != null && !C.equals("")) {
			DecryptView dv = DecryptView.getCurrentFrame();
			if(dv != null) dv.dispose();
			dv = new DecryptView(C);
			dv.setVisible(true);
		} else {
			JOptionPane.showConfirmDialog(this, "Bản mã không hợp lệ!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
}

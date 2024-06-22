package com.elgamal.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.elgamal.contexts.CypherContext;
import com.elgamal.cypher.Elgamal;
import com.elgamal.cypher.Encrypted;
import com.elgamal.cypher.Key;
import com.elgamal.cypher.PublicKey;
import com.elgamal.utils.FileUtil;
import com.elgamal.utils.HashUtil;

import javax.swing.border.EtchedBorder;
import java.awt.Cursor;

public class EncryptView extends JFrame {

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
	private Encrypted encrypted;

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
		panel.setBounds(5, 5, 536, 651);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Generate Key", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(577, 5, 702, 651);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Decrypt", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		
		JButton btnGii = new JButton("Giải mã");
		btnGii.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGii.setBounds(582, 591, 104, 42);
		btnGii.setForeground(Color.WHITE);
		btnGii.setFont(new Font("Arial", Font.BOLD, 14));
		btnGii.setFocusable(false);
		btnGii.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnGii.setBackground(new Color(0, 128, 255));
		btnGii.addActionListener(e -> navigateDecryptView());
		
		JButton btnSKNgu = new JButton("Số k ngẫu nhiên");
		btnSKNgu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSKNgu.setBounds(406, 273, 166, 42);
		btnSKNgu.setForeground(Color.WHITE);
		btnSKNgu.setFont(new Font("Arial", Font.BOLD, 14));
		btnSKNgu.setFocusable(false);
		btnSKNgu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSKNgu.setBackground(new Color(33, 171, 36));
		btnSKNgu.addActionListener(e -> handleRandomK());
		
		JButton btnMHa = new JButton("Mã hóa");
		btnMHa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMHa.setBounds(582, 273, 104, 42);
		btnMHa.setForeground(Color.WHITE);
		btnMHa.setFont(new Font("Arial", Font.BOLD, 14));
		btnMHa.setFocusable(false);
		btnMHa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnMHa.setBackground(new Color(0, 128, 255));
		btnMHa.addActionListener(e -> handleEncode());
		
		JLabel lblNewLabel_1_1 = new JLabel("Bản rõ M:");
		lblNewLabel_1_1.setBounds(16, 49, 74, 17);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMHa = new JLabel("Mã hóa");
		lblMHa.setBounds(16, 16, 96, 27);
		lblMHa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2_1 = new JLabel("Số k:");
		lblNewLabel_2_1.setBounds(16, 161, 32, 17);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1_1_2 = new JScrollPane();
		scrollPane_2_2_1_1_2.setBounds(16, 354, 670, 226);
		
		JScrollPane scrollPane_2_2_1_2 = new JScrollPane();
		scrollPane_2_2_1_2.setBounds(16, 184, 670, 83);
		
		JScrollPane scrollPane_2_2_2 = new JScrollPane();
		scrollPane_2_2_2.setBounds(16, 72, 670, 83);
		
		JLabel lblBnM = new JLabel("Bản mã");
		lblBnM.setBounds(16, 321, 96, 27);
		lblBnM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
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
		
		JLabel lblNewLabel = new JLabel("Sinh khóa");
		lblNewLabel.setBounds(16, 16, 96, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Số nguyên tố q:");
		lblNewLabel_1.setBounds(16, 49, 111, 17);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2 = new JScrollPane();
		scrollPane_2_2.setBounds(16, 72, 504, 83);
		
		inputQ = new JTextArea();
		inputQ.setSelectionColor(new Color(0, 128, 255));
		inputQ.setSelectedTextColor(new Color(255, 255, 255));
		inputQ.setBorder(new EmptyBorder(5, 5, 5, 5));
		inputQ.setWrapStyleWord(true);
		inputQ.setLineWrap(true);
		scrollPane_2_2.setViewportView(inputQ);
		
		JLabel lblNewLabel_2 = new JLabel("a:");
		lblNewLabel_2.setBounds(16, 161, 12, 17);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1 = new JScrollPane();
		scrollPane_2_2_1.setBounds(16, 184, 504, 83);
		
		JLabel lblNewLabel_3 = new JLabel("Xa:");
		lblNewLabel_3.setBounds(16, 273, 20, 17);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1_1 = new JScrollPane();
		scrollPane_2_2_1_1.setBounds(16, 296, 504, 83);
		
		JLabel lblNewLabel_3_1 = new JLabel("Ya:");
		lblNewLabel_3_1.setBounds(16, 385, 20, 17);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_2_2_1_1_1 = new JScrollPane();
		scrollPane_2_2_1_1_1.setBounds(16, 408, 504, 83);
		
		JButton btnNewButton = new JButton("Tạo khóa");
		btnNewButton.setBounds(16, 502, 104, 42);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setFocusable(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.addActionListener(e -> handleCreateKey());
		
		JButton btnSinhKhaNgu = new JButton("Sinh khóa ngẫu nhiên");
		btnSinhKhaNgu.setBounds(130, 502, 193, 42);
		btnSinhKhaNgu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSinhKhaNgu.setForeground(Color.WHITE);
		btnSinhKhaNgu.setFont(new Font("Arial", Font.BOLD, 14));
		btnSinhKhaNgu.setFocusable(false);
		btnSinhKhaNgu.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSinhKhaNgu.setBackground(new Color(33, 171, 36));
		btnSinhKhaNgu.addActionListener(e -> handleRandomKey());
		
		JButton btntLi = new JButton("Đặt lại");
		btntLi.setBounds(333, 502, 104, 42);
		btntLi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btntLi.setForeground(Color.WHITE);
		btntLi.setFont(new Font("Arial", Font.BOLD, 14));
		btntLi.setFocusable(false);
		btntLi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btntLi.setBackground(new Color(128, 128, 128));
		btntLi.addActionListener(e -> handleReset());
		contentPane.setLayout(null);
		
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
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(scrollPane_2_2_1_1);
		panel.add(scrollPane_2_2_1);
		panel.add(scrollPane_2_2);
		panel.add(btnNewButton);
		panel.add(btnSinhKhaNgu);
		panel.add(btntLi);
		panel.add(lblNewLabel);
		panel.add(lblNewLabel_1);
		panel.add(lblNewLabel_2);
		panel.add(lblNewLabel_3);
		panel.add(scrollPane_2_2_1_1_1);
		panel.add(lblNewLabel_3_1);
		
		JButton btnSaveKey = new JButton("Lưu khóa");
		btnSaveKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveKey.setForeground(Color.WHITE);
		btnSaveKey.setFont(new Font("Arial", Font.BOLD, 14));
		btnSaveKey.setFocusable(false);
		btnSaveKey.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSaveKey.setBackground(new Color(128, 128, 255));
		btnSaveKey.setBounds(16, 555, 133, 42);
		btnSaveKey.addActionListener(e -> handleSaveKey());
		panel.add(btnSaveKey);
		
		JButton btnImportKey = new JButton("Nhập file");
		btnImportKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportKey.setForeground(Color.WHITE);
		btnImportKey.setFont(new Font("Arial", Font.BOLD, 14));
		btnImportKey.setFocusable(false);
		btnImportKey.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnImportKey.setBackground(new Color(128, 128, 192));
		btnImportKey.setBounds(159, 555, 133, 42);
		btnImportKey.addActionListener(e -> handleImportKey());
		panel.add(btnImportKey);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.add(btnGii);
		panel_1.add(scrollPane_2_2_1_1_2);
		panel_1.add(scrollPane_2_2_1_2);
		panel_1.add(scrollPane_2_2_2);
		panel_1.add(btnSKNgu);
		panel_1.add(btnMHa);
		panel_1.add(lblMHa);
		panel_1.add(lblNewLabel_1_1);
		panel_1.add(lblNewLabel_2_1);
		panel_1.add(lblBnM);
		
		JButton btnSaveMessage = new JButton("Lưu file");
		btnSaveMessage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveMessage.setForeground(Color.WHITE);
		btnSaveMessage.setFont(new Font("Arial", Font.BOLD, 14));
		btnSaveMessage.setFocusable(false);
		btnSaveMessage.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSaveMessage.setBackground(new Color(128, 128, 255));
		btnSaveMessage.setBounds(582, 31, 104, 33);
		btnSaveMessage.addActionListener(e -> handleSaveMessage());
		panel_1.add(btnSaveMessage);
		
		JButton btnImportMessage = new JButton("Nhập file");
		btnImportMessage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportMessage.setForeground(Color.WHITE);
		btnImportMessage.setFont(new Font("Arial", Font.BOLD, 14));
		btnImportMessage.setFocusable(false);
		btnImportMessage.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnImportMessage.setBackground(new Color(128, 128, 192));
		btnImportMessage.setBounds(468, 31, 104, 33);
		btnImportMessage.addActionListener(e -> handleImportMessage());
		panel_1.add(btnImportMessage);
		
		JButton btnSaveCode = new JButton("Lưu bản mã");
		btnSaveCode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSaveCode.setForeground(Color.WHITE);
		btnSaveCode.setFont(new Font("Arial", Font.BOLD, 14));
		btnSaveCode.setFocusable(false);
		btnSaveCode.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSaveCode.setBackground(new Color(128, 128, 255));
		btnSaveCode.setBounds(439, 591, 133, 42);
		btnSaveCode.addActionListener(e -> handleSaveCode());
		panel_1.add(btnSaveCode);

		setCurrentFrame(this);
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
			//Y = a ^ x mod q
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
		//Y = a ^ x mod q
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
		CypherContext.setEmcrypted(null);
		encrypted = null;
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
		int mLength = BigInteger.valueOf(message.charAt(0)).bitLength();
		for(int i = 1; i < message.length(); i++) {
			int bl = BigInteger.valueOf(message.charAt(i)).bitLength();
			if(bl > mLength) mLength = bl;
		}
		
		if(mLength >= qLength) {
			JOptionPane.showConfirmDialog(this, "Kích thước số q (" + qLength + " bit) không đủ để mã hóa bản rõ!\n"
			+ "Yêu cầu: Số nguyên tố q phải ≥ " + (mLength + 1) + " bit.\n"
			+ "Ví dụ: " + BigInteger.probablePrime(mLength + 1, random) + "\n"
			+ "Hoặc dùng thử chức năng sinh khóa ngẫu nhiên.", "Error",
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
					encrypted = new Encrypted();
					String strK = inputK.getText();
					C = Elgamal.encrypt(message, pk, new BigInteger(strK));
					outputC1.setText(C);
					// 
					encrypted.setEncryptedText(C);
					encrypted.setQ(pk.getQ());
					encrypted.setHashPrivateKey(HashUtil.createBase64Hash(CypherContext.getPrivateKey().toString()));
					encrypted.setHashMessage(HashUtil.createBase64Hash(message));
					CypherContext.setEmcrypted(encrypted);
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
	
	private void handleImportKey() {
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File f = chooser.getSelectedFile();
		if(f == null) return;
		try {
			Key key = FileUtil.readKey(f.getAbsolutePath());
			PublicKey puk = key.getPublicKey();
			BigInteger prk = key.getPrivateKey();
			inputQ.setText(puk.getQ().toString());
			inputA.setText(puk.getA().toString());
			inputXa.setText(prk.toString());
			outputYa.setText(puk.getY().toString());
			CypherContext.setPublicKey(puk);
			CypherContext.setPrivateKey(prk);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, "Tệp dữ liệu không hợp lệ hoặc có lỗi trong quá trình xử lý tệp tin!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void handleSaveKey() {
		if(!checkValidKey()) {
			return;
		}
		// Create key
		String strQ = inputQ.getText();
		String strA = inputA.getText();
		String strX = inputXa.getText();
		BigInteger q = new BigInteger(strQ);
		BigInteger a = new BigInteger(strA);
		BigInteger x = new BigInteger(strX);
		//Y = a ^ x mod q
		BigInteger y = a.modPow(x, q);
		CypherContext.setPublicKey(q, a, y);
		CypherContext.setPrivateKey(x);
		outputYa.setText(y.toString());
		Key key = new Key();
		key.setPublicKey(CypherContext.getPublicKey());
		key.setPrivateKey(CypherContext.getPrivateKey());
		// Save key
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text document (.txt)", "txt");
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(txt);
		chooser.setFileFilter(txt);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setSelectedFile(new File("key"));
		int returnVal = chooser.showSaveDialog(this);
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
			String path = fileToSave.getAbsolutePath();
			try {
				FileUtil.saveKey(path, key);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, e.getMessage(), "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void handleImportMessage() {
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File f = chooser.getSelectedFile();
		if(f == null) return;
		try {
			String content = FileUtil.getContent(f);
			inputM.setText(content);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, e.getMessage(), "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void handleSaveMessage() {
		String message = inputM.getText();
		if(message.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập bản rõ", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return;
		}
        FileNameExtensionFilter docx = new FileNameExtensionFilter("Microsoft Excel 2007 to lastest (.docx)", "docx");
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text document (.txt)", "txt");
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(txt);
        chooser.addChoosableFileFilter(docx);
		chooser.setFileFilter(txt);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setSelectedFile(new File("message"));
		int returnVal = chooser.showSaveDialog(this);
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
			FileNameExtensionFilter filter = (FileNameExtensionFilter) chooser.getFileFilter();
            String ext = filter.getExtensions()[0];
			try {
				FileUtil.saveFile(fileToSave, ext, message);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, e.getMessage(), "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void handleSaveCode() {
		if(encrypted == null || encrypted.getEncryptedText().trim().equals("")) {
			JOptionPane.showConfirmDialog(this, "Chưa có bản mã!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return;
		}
		FileNameExtensionFilter txt = new FileNameExtensionFilter("Text document (.txt)", "txt");
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(txt);
		chooser.setFileFilter(txt);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setSelectedFile(new File("encrypted"));
		int returnVal = chooser.showSaveDialog(this);
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
			String path = fileToSave.getAbsolutePath();
			try {
				FileUtil.saveEncrypted(path, encrypted);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, e.getMessage(), "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void navigateDecryptView() {
		String c = outputC1.getText();
		// Xóa bớt frame nếu nó đã tồn tại
		DecryptView dv = DecryptView.getCurrentFrame();
		if(dv != null) dv.dispose();
		dv = new DecryptView(c);
		dv.setVisible(true);
	}

	private static EncryptView currentFrame;
	
	public static void setCurrentFrame(EncryptView view) {
		currentFrame = view;
	}

	public static EncryptView getCurrentFrame() {
		return currentFrame;
	}

	public void setInputQ(String value) {
		inputQ.setText(value);
	}

	public void setInputA(String value) {
		inputA.setText(value);
	}

	public void setInputXa(String value) {
		inputXa.setText(value);
	}

	public void setOutputYa(String value) {
		outputYa.setText(value);
	}

}

package com.elgamal.views;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.elgamal.contexts.CypherContext;
import com.elgamal.cypher.Elgamal;
import com.elgamal.cypher.Encrypted;
import com.elgamal.cypher.Key;
import com.elgamal.cypher.PublicKey;
import com.elgamal.utils.FileUtil;
import com.elgamal.utils.HashUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.math.BigInteger;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Cursor;

public class DecryptView extends JFrame {

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
		lblGiiM.setBounds(15, 5, 102, 27);
		lblGiiM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1_1 = new JLabel("Bản mã:");
		lblNewLabel_1_1.setBounds(15, 112, 76, 17);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Khóa bí mật Xa:");
		lblNewLabel_1_1_1.setBounds(15, 304, 111, 17);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton = new JButton("Giải mã");
		btnNewButton.setBounds(15, 532, 100, 36);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.addActionListener(e -> handleDecode());
		
		JButton btntLi = new JButton("Đặt lại");
		btntLi.setBounds(121, 532, 104, 36);
		btntLi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btntLi.setBorder(new EmptyBorder(0, 0, 0, 0));
		btntLi.setForeground(Color.WHITE);
		btntLi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btntLi.setFocusable(false);
		btntLi.setBackground(Color.GRAY);
		btntLi.addActionListener(e -> handleReset());
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Bản rõ:");
		lblNewLabel_1_1_2.setBounds(15, 444, 111, 17);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(136, 39, 653, 167);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(136, 267, 653, 96);
		
		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		scrollPane_1_1_1.setBounds(136, 381, 653, 133);
		
		JButton btntSaveMessage = new JButton("Lưu bản rõ");
		btntSaveMessage.setBounds(231, 532, 118, 36);
		btntSaveMessage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btntSaveMessage.setForeground(Color.WHITE);
		btntSaveMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		btntSaveMessage.setFocusable(false);
		btntSaveMessage.setBorder(new EmptyBorder(0, 0, 0, 0));
		btntSaveMessage.setBackground(new Color(128, 128, 192));
		btntSaveMessage.addActionListener(e -> handleSaveMessage());
		
		JButton btnImportCode = new JButton("Tải bản mã");
		btnImportCode.setBounds(136, 213, 118, 36);
		btnImportCode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportCode.setForeground(Color.WHITE);
		btnImportCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImportCode.setFocusable(false);
		btnImportCode.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnImportCode.setBackground(new Color(128, 128, 192));
		btnImportCode.addActionListener(e -> handleImportCode());
		
		JButton btnImportKey = new JButton("Tải khóa");
		btnImportKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportKey.setBounds(260, 213, 105, 36);
		btnImportKey.setForeground(Color.WHITE);
		btnImportKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImportKey.setFocusable(false);
		btnImportKey.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnImportKey.setBackground(new Color(128, 128, 192));
		btnImportKey.addActionListener(e -> handleImportKey());
		
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
		contentPane.setLayout(null);
		contentPane.add(btntSaveMessage);
		contentPane.add(btnImportCode);
		contentPane.add(btnImportKey);
		contentPane.add(lblGiiM);
		contentPane.add(lblNewLabel_1_1_1);
		contentPane.add(lblNewLabel_1_1_2);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(scrollPane_1);
		contentPane.add(scrollPane_1_1_1);
		contentPane.add(scrollPane_1_1);
		contentPane.add(btnNewButton);
		contentPane.add(btntLi);
	}
	
	private boolean checkValid() {
		String C1 = inputC1.getText();
		String strX = inputXa.getText();
		Encrypted encrypted = CypherContext.getEncrypted();
		if(C1 == null || C1.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập bản mã!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			if(encrypted == null || !C1.equals(encrypted.getEncryptedText())) {
				JOptionPane.showConfirmDialog(this, "Bản mã không hợp lệ hặc đã bị sửa đổi!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(strX == null || strX.equals("")) {
			JOptionPane.showConfirmDialog(this, "Vui lòng nhập khóa bí mật!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {	
			try {
				BigInteger x = new BigInteger(strX);
				if(encrypted == null || !HashUtil.verify(encrypted.getHashPrivateKey(), x.toString())) {
					JOptionPane.showConfirmDialog(this, "Khóa bí mật không chính xác hoặc đã bị sửa đổi!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
					return false;
				}
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
			BigInteger x = new BigInteger(inputXa.getText());
			Encrypted encrypted = CypherContext.getEncrypted();
			BigInteger q = encrypted.getQ();
			String hashM = encrypted.getHashMessage();
			String m = "";
			m = Elgamal.decrypt(C1, x, q);
			outputM.setText(m);
			if(HashUtil.verify(hashM, m)) {
				JOptionPane.showConfirmDialog(this, "Giải mã thành công!", "Success",
						JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(this, "Giải mã không thành công!", "Error",
						JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}
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
	
	private void handleSaveMessage() {
		String message = outputM.getText();
		if(message.equals("")) {
			JOptionPane.showConfirmDialog(this, "Chưa có bản rõ!", "Error",
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
	
	private void handleImportCode() {
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File f = chooser.getSelectedFile();
		if(f == null) return;
		try {
			Encrypted encrypted = FileUtil.readEncrypted(f.getAbsolutePath());
			CypherContext.setEmcrypted(encrypted);
			inputC1.setText(encrypted.getEncryptedText());
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, "Tệp dữ liệu không hợp lệ hoặc có lỗi trong quá trình xử lý tệp tin!", "Error",
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
			EncryptView eView = EncryptView.getCurrentFrame();
			eView.setInputQ(puk.getQ().toString());
			eView.setInputA(puk.getA().toString());
			eView.setInputXa(prk.toString());
			eView.setOutputYa(puk.getY().toString());
			inputXa.setText(prk.toString());
			CypherContext.setPublicKey(puk);
			CypherContext.setPrivateKey(prk);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, "Tệp dữ liệu không hợp lệ hoặc có lỗi trong quá trình xử lý tệp tin!", "Error",
					JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

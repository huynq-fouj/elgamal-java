package com.elgamal;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.elgamal.views.EncryptView;

public class App {
	
	private static Logger logger = Logger.getLogger(App.class.getName());
	
    public static void main( String[] args ) {
    	//initLaF();
    	EventQueue.invokeLater(() -> {
			EncryptView frame = new EncryptView();
			frame.setVisible(true);
		});
    }
    
    private static void initLaF() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	
	}
}

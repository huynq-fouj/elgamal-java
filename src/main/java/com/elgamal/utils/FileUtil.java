package com.elgamal.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.elgamal.cypher.Encrypted;
import com.elgamal.cypher.Key;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.*;

public class FileUtil {
	
	private FileUtil() {}
	
	public static String getContent(File file) throws IOException, Exception {
		String path = file.getAbsolutePath();
		String fileExtension = getFileExtension(file);
		switch(fileExtension) {
		case "txt": return readTxtFile(path);
		case "doc": return readDocFile(path);
		case "docx": return readDocxFile(path);
		default: throw new Exception("Không hỗ trợ định dạng tệp này!");
		}
	}
	
	private static String readTxtFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static String readDocFile(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            HWPFDocument document = new HWPFDocument(fis);
            try (WordExtractor extractor = new WordExtractor(document)) {
				return extractor.getText();
			}
        }
    }

    private static String readDocxFile(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            XWPFDocument document = new XWPFDocument(fis);
            try (XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
				return extractor.getText();
			}
        }
    }
    
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
    
    public static void saveFile(File file, String extension, String content) throws IOException, Exception {
    	String path = file.getAbsolutePath();
		switch(extension) {
			case "txt": 
				saveTxtFile(path + "." + extension, content);
				break;
			case "docx": 
				saveDocxFile(path + "." + extension, content);
				break;
			default: throw new Exception("Không hỗ trợ định dạng tệp này!");
		}
    }
    
    private static void saveTxtFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private static void saveDocxFile(String filePath, String content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
				XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(content);
            document.write(fos);
        }
    }
    
    public static void saveKey(String path, Key key) {
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + ".txt"))) {
            oos.writeObject(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static Key readKey(String path) {
		Key key = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            key = (Key) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return key;
	}
	
	public static void saveEncrypted(String path, Encrypted encrypted) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + ".txt"))) {
            oos.writeObject(encrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static Encrypted readEncrypted(String path) {
		Encrypted encrypted = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
        	encrypted = (Encrypted) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return encrypted;
	}
	
}

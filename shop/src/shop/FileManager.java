package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	private FileWriter fileWriter;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	private File file;
	private String fileName;
	
	public FileManager() {
		fileName = "shop.txt";
		file = new File(fileName);
	}
	
	private String createUserString(UserManager userManager) {
		String userString = "";
		userString += userManager.createFile();
		return userString;
	}
	
	private String createItemString(ItemManager itemManager) {
		String userString = "";
		userString += itemManager.createFile();
		return userString;
	}
	
	public void createString(UserManager user, ItemManager item) {
		ItemManager itemManager = item;
		UserManager userManager = user;
		
		String data = "";
		data += createItemString(itemManager)+"\n";
		data += createUserString(userManager);
		
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(data);
			fileWriter.close();
			
			System.out.println("파일저장 성공");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("파일저장 실패");
		}
	}
}

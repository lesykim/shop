package shop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
	
	public void createString(UserManager user, ItemManager item) {
		UserManager userManager = user;
		ItemManager itemManager = item;
		
		createUserString(userManager);
	}
}

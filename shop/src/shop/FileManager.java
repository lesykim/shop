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
	
	private ItemManager itemManager;
	private UserManager userManager;
	
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
	
	private void insertItemManager(String[] itemData){
		for(int i = 0; i<itemData.length; i++) {
			String[] itemInfo = itemData[i].split(",");
			int code = Integer.parseInt(itemInfo[0]);
			String title = itemInfo[1];
			int price = Integer.parseInt(itemInfo[2]);
			int count = Integer.parseInt(itemInfo[3]);
			itemManager.insertItem(code, title,price,count);
		}		
	}
	
	private void insertUserInfo(String data) {
		String[] userInfo = data.split(",");
		int code = Integer.parseInt(userInfo[0]);
		String name = userInfo[1];
		String id = userInfo[2];
		String password = userInfo[3];
		userManager.insertUser(code, name, id, password);
	}
	
	private void insertUserManager(String[] allData) {
		for(int i = 1; i<allData.length; i++) {
			String[] temp = allData[i].split("&");
			
			insertUserInfo(temp[0]);
			
		}
	}
	
	private void parseLoadedData(String data) {
		itemManager = new ItemManager();
		userManager = new UserManager();
		String[] allData = data.split("\n");
		String[] itemData = allData[0].split("/");
		insertItemManager(itemData);
		insertUserManager(allData);
	}
	
	public void loadString(UserManager userManager, ItemManager itemManager) {
		if(file.exists()) {
			String data = "";
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				
				while(bufferedReader.ready()) {
					data += bufferedReader.readLine()+"\n";
				}
				
				bufferedReader.close();				
				fileReader.close();
				
				parseLoadedData(data);
				
				itemManager = this.itemManager;
				userManager = this.userManager;
				
				System.out.println("파일로드 성공");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("파일로드 실패");
			}
		}
	}
}

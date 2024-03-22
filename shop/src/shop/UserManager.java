package shop;

import java.util.ArrayList;
import java.util.Random;

public class UserManager {
	private Random ran = new Random();
	private ArrayList<User> list;
	
	public UserManager() {
		list = new ArrayList<User>();
	}
	
	private User findUserByUserId(String id) {
		for (User user : list) {
			if (user.getId().equals(id))
				return user;
		}
		return new User();
	}
	
	private boolean isValidId(String id) {
		User user = findUserByUserId(id);
		if(user.getCode() == 0) {
			return true;
		}
		return false;
	}
	
	public User findUserByUserCode(int code) {
		for (User user : list) {
			if (user.getCode() == code)
				return user;
		}
		return new User();
	}
	
	private int generateUserCode() {
		int code = 0;
		while (true) {
			code = ran.nextInt(9000) + 1000;

			User user = findUserByUserCode(code);
			if (user.getCode() == 0)
				break;
		}
		return code;
	}
	
	public User createUser(String name, String id, String password) {
		if (isValidId(id)) {
			int code = generateUserCode();
			User user = new User(code, name, id, password);
			list.add(user);
			return user.clone();
		}
		return new User();
	}
	
	public User findUserByInfo(String id, String password) {
		for (User user : list) {
			if (user.getId().equals(id)&&user.getPassword().equals(password))
				return user;
		}
		return new User();
	}
	
	public Item deleteItem(int code) {
		for(User user : list) {
			ArrayList<Item> itemList = user.getCart().getList();
			for(int i = 0; i<itemList.size(); i++) {
				Item item = itemList.get(i);
				if(item.getCode() == code) {
					itemList.remove(i);
					return item.clone();
				}
			}
		}
		return new Item();
	}
	
	public Item updateItem(int code, int price) {
		for(User user : list) {
			ArrayList<Item> itemList = user.getCart().getList();
			for(int i = 0; i<itemList.size(); i++) {
				Item item = itemList.get(i);
				if(item.getCode() == code) {
					item.setPrice(price);
					return item.clone();
				}
			}
		}
		return new Item();
	}
	
	public void shoppingItem(int code, Item item) {
		User user = findUserByUserCode(code);
		ArrayList<Item> itemList = user.getCart().getList();
		
		boolean isDupl = false;
		for(Item temp : itemList) {
			if(temp.getCode() == item.getCode()) {
				temp.setCount(temp.getCount()+1);
				isDupl = true;
			}
		}
		
		if(!isDupl) {
			itemList.add(item);
		}
	}
}

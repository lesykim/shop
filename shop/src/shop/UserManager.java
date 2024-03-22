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
				return user.clone();
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
	
	private User findUserByUserCode(int code) {
		for (User user : list) {
			if (user.getCode() == code)
				return user.clone();
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
}

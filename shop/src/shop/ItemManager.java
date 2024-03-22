package shop;

import java.util.ArrayList;
import java.util.Random;

public class ItemManager {
	private Random ran = new Random();
	private ArrayList<Item> list;
	
	public ItemManager() {
		list = new ArrayList<Item>();
	}
	
	private Item findItemByItemCode(int code) {
		for(Item item : list) {
			if(code == item.getCode()) {
				return item.clone();
			}
		}
		return new Item();
	}
	
	private int generateUserCode() {
		int code = 0;
		while (true) {
			code = ran.nextInt(9000) + 1000;

			Item item = findItemByItemCode(code);
			if (item.getCode() == 0)
				break;
		}
		return code;
	}

	public Item addItem(String title, int price) {
		int code = generateUserCode();
		Item item = new Item(code,price,title);
		list.add(item);
		return item.clone();
	}
	
	public Item deleteItem(int code) {
		Item temp = null;
		for(int i =0; i<list.size(); i++) {
			Item item = list.get(i);
			if(item.getCode() == code) {
				temp = item.clone();
				list.remove(item);
			}
		}
		return temp;
	}
	
	public ArrayList<Item> getList() {
		return this.list;
	}
}

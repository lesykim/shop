package shop;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> list;
	
	public ItemManager() {
		list = new ArrayList<Item>();
	}
	
	public Item addItem(int code, String title, int price) {
		Item item = new Item(code,price,title);
		list.add(item);
		return item.clone();
	}
}

package shop;

import java.util.ArrayList;
import java.util.Random;

public class ItemManager {
	private Random ran = new Random();
	private ArrayList<Item> list;
	
	public ItemManager() {
		list = new ArrayList<Item>();
	}
	
	public Item findItemByItemCode(int code) {
		for(Item item : list) {
			if(code == item.getCode()) {
				return item;
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
	
	public void updatePaidItemCount(Cart cart) {
		for(Item item : list) {
			int count = cart.findItemCountByCode(item.getCode());
			int itemCnt = item.getCount()-count;
			item.setCount(itemCnt);
		}
	}
	
	public void deleteleaveItemCount(Cart cart) {
		for(Item item : list) {
			int code = item.getCode();
			int count = item.getCount() - cart.findItemCountByCode(code);
			item.setCount(count);
		}
	}
	
	public void deleteItemCount(int code, int count) {
		for(Item item : list) {
			if(item.getCode() == code) {
				int cnt = item.getCount()-count;
				item.setCount(cnt);
			}
		}
	}
	
	public void updateItemCount(int itemCount, int code, int count) {
		for(Item item : list) {
			if(item.getCode() == code) {
				int cnt = item.getCount()-itemCount+count;
				item.setCount(cnt);
			}
		}
	}

	public Item addItem(String title, int price) {
		int code = generateUserCode();
		Item item = new Item(code,price,title);
		list.add(item);
		return item.clone();
	}
	
	public Item deleteItem(int code) {
		for(int i =0; i<list.size(); i++) {
			Item item = list.get(i);
			if(item.getCode() == code) {
				list.remove(item);
				return item;
			}
		}
		return new Item();
	}
	
	public void updateItem(int price, Item item) {
		item.setPrice(price);
	}
	
	public ArrayList<Item> getList() {
		return this.list;
	}
}

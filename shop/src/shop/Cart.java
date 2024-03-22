package shop;

import java.util.ArrayList;

public class Cart {
	private int code;
	private int listsize;
	private ArrayList<Item> list;
	
	public Cart() {
		
	}
	
	public Cart(int code) {
		this.code = code;
		list = new ArrayList<Item>();
	}
	

	public ArrayList<Item> getList() {
		return list;
	}

	public void setList(ArrayList<Item> list) {
		this.list = list;
	}

	public int getCode() {
		return code;
	}

	public int getListsize() {
		listsize = list.size();
		return listsize;
	}
	
	public Item findItemByItemCode(int code) {
		for(Item item : list) {
			if(code == item.getCode()) {
				return item;
			}
		}
		return new Item();
	}
	
	public void deleteItem(int code) {
		for(Item item : list) {
			if(item.getCode() == code) {
				list.remove(item);
				System.out.println("상품 삭제 완료");
				return;
			}
		}
		System.out.println("존재하지 않는 상품");
	}
}

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
}

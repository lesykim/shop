package shop;

public class Item {
	private int code;
	private int price;
	private int count;
	private String title;
	
	public Item() {
		
	}
	
	public Item(int code, int price, String title) {
		this.code = code;
		this.title = title;
		this.price = price;
	}
	
	public Item(int code, String title, int price, int count) {
		this.code = code;
		this.title = title;
		this.price = price;
		this.count = count;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public Item clone() {
		return new Item(this.code, this.price, this.title);
	}
}

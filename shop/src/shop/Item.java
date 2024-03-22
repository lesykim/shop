package shop;

public class Item {
	private int code;
	private int price;
	private String title;
	
	public Item(int code, int price, String title) {
		this.title = title;
		this.code = code;
		this.price = price;
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
}

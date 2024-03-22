package shop;

public class User {
	private int money;
	private String name;
	private String id;
	private String password;
	private int cartSize;
	
	private Cart cart;
	
	public User(String name, String id, String password) {
		this.name = name;
		this.id = id;
		this.password = password;
	} 

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getCartSize() {
		return cartSize;
	}

	public String getPassword() {
		return password;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s) %s",id,password,name);
	}
}

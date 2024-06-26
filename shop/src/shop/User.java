package shop;

public class User {
	private int code;
	private String name;
	private String id;
	private String password;
	private Cart cart;
	
	public User() {
		
	}
	
	public User(int code, String name, String id, String password) {
		this.code = code;
		this.name = name;
		this.id = id;
		this.password = password;
		cart = new Cart(code);
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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

	public int getCode() {
		return code;
	}
	
	public User clone() {
		return new User(this.code, this.name,this.id,this.password);
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s) %s",id,password,name);
	}
}

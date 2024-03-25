package shop;

import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
	private Scanner sc = new Scanner(System.in);

	private final int USER = 1;
	private final int FILE = 2;
	private final int ADMIN = 3;
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int SHOPPING = 5;
	private final int MY_PAGE = 6;
	
	private final int DELETE = 1;
	private final int UPDATE = 2;
	private final int PAYMENT = 3;
	
	private final int SAVE = 1;
	private final int LOAD = 2;

	private final int ITEM = 1;
	private final int TURNOVER = 2;

	private final int ADD_ITEM = 1;
	private final int DELETE_ITEM = 2;
	private final int UPDATE_ITEM = 3;

	private String title;
	private int logCode;
	
	private UserManager userManager = new UserManager();
	private ItemManager itemManager = new ItemManager();
	private FileManager fileManager = new FileManager();
	
	private void setSystem() {
		logCode = 0;
	}
	
	public Shop(String message) {
		this.title = message;
		setSystem();
	}
	
	private void printMainMenu() {
		System.out.println("-=- "+title+" -=-");
		if(logCode != 0) {
			User user = userManager.findUserByUserCode(logCode);
			System.out.printf("%s님 로그인 중...\n",user.getName());			
		}
		System.out.println("[1] 유저");
		System.out.println("[2] 파일");
		System.out.println("[3] 관리자");
	}
	
	private int inputNumber(String message) {
		int number = -1;
		try {
			System.out.print(message + " : ");
			String input = sc.next();
			number = Integer.parseInt(input);
			System.out.println("-------------");
		} catch (NumberFormatException e) {
			System.err.println("숫자를 입력하세요.");
		}
		return number;
	} 
	
	private void printUser() {
		System.out.println("[1] 회원가입");
		System.out.println("[2] 탈퇴");
		System.out.println("[3] 로그인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 쇼핑하기");
		System.out.println("[6] 마이페이지");
	}
	
	private String inputString(String message) {
		System.out.print(message + " : ");
		return sc.next();
	}
	
	private void printWelcomeMessage(User user) {
		String message = user.getCode() != 0 ? String.format("%s(%s) %s 회원님 환영합니다.", user.getId(), user.getPassword(), user.getName())
				: "회원가입 실패";
		System.out.println(message);
	}
	
	private void join() {
		String name = inputString("name");
		String id = inputString("id");
		String password = inputString("password");
		
		User user = userManager.createUser(name, id, password);
		
		printWelcomeMessage(user);
	}
	
	private void leave() {
		String password = inputString("password");
		User user = userManager.findUserByUserCode(logCode);
		if(!user.getPassword().equals(password)) {
			System.out.println("비밀번호를 다시 확인하세요");
			return;
		}
		
		Cart cart = user.getCart();
		itemManager.deleteleaveItemCount(cart);
		userManager.deleteUser(user);
		System.out.println("탈퇴되었습니다.");
	}
	
	private void printLoginMessage(User user) {
		String message = logCode != 0 ? String.format("%s님 로그인되었습니다.",user.getName())
				: "회원정보를 다시 확인하세요.";
		System.out.println(message);
	}
	
	private void login() {
		String id = inputString("id");
		String password = inputString("password");
		
		User user = userManager.findUserByInfo(id, password);
		logCode = user.getCode();
		
		printLoginMessage(user);
	}
	
	private void logout() {
		logCode = 0;
		System.out.println("로그아웃 되었습니다.");
	}
	
	private void shopping() {
		printItemAll();
		
		int code = inputNumber("추가할 상품 코드");
		Item item = itemManager.findItemByItemCode(code).clone();
		if(item.getCode() == 0) {
			System.out.println("존재하지 않는 상품입니다.");
			return;
		}
		item.setCount(1);
		userManager.shoppingItem(logCode, item);
		Item temp = itemManager.findItemByItemCode(code);
		int count = temp.getCount()+1;
		temp.setCount(count);
		System.out.printf("장바구니에 %s 상품을 추가하였습니다.\n",item.getTitle());
	}
	
	private void printCart() {
		User user = userManager.findUserByUserCode(logCode);		
		userManager.printUserCart(user);
	}
	
	private void printMypageSubMenu() {
		System.out.println("[1] 항목삭제");
		System.out.println("[2] 수량수정");
		System.out.println("[3] 결제");
	}
	
	private void delete() {
		printCart();
		int code = inputNumber("삭제할 상품 코드");
		User user = userManager.findUserByUserCode(logCode);		
		Cart cart = user.getCart();
		itemManager.deleteItemCount(code,cart.findItemCountByCode(code));
		cart.deleteItem(code);
	}
	
	private void update() {
		printCart();
		User user = userManager.findUserByUserCode(logCode);
		Cart cart = user.getCart();
		int code = inputNumber("변경할 상품 코드");
		Item item = cart.findItemByItemCode(code);
		if(item.getCode() == 0) {
			System.out.println("존재하지 않는 상품");
			return;
		}
		int count = inputNumber("변경 할 수량");
		if(count < 0) {
			System.out.println("수량은 0 이상 가능합니다.");
			return;
		}
		itemManager.updateItemCount(item.getCount(), item.getCode(), count);
		item.setCount(count);
		System.out.printf("%s 상품의 수량이 변경되었습니다.\n",item.getTitle());
		printCart();
	}
	
	private int printTotal(Cart cart) {
		int sum = cart.getTotal();
		System.out.printf("총 결제금액 : %d원\n",sum);
		return sum;
	}
	
	private void payment() {
		User user = userManager.findUserByUserCode(logCode);
		Cart cart = user.getCart();
		printCart();
		int sum = printTotal(cart);
		int input = inputNumber("입금");
		if(sum>input) {
			System.out.println("현금이 부족합니다.");
		}
		
		itemManager.updatePaidItemCount(cart);
		cart.clearCart();
		
		System.out.println("결제완료");
	}
	
	private void runMyPageSubMenu(int select) {
		if(select == DELETE) {
			delete();
		}else if(select == UPDATE) {
			update();
		}else if(select == PAYMENT) {
			payment();
		}
	}
	
	private void myPage() {
		printCart();
		printMypageSubMenu();
		int sel = inputNumber("Mypage Menu");
		runMyPageSubMenu(sel);
	}
	
	private void runUser(int select) {
		if(select == JOIN && !isLogin()) {
			join();
		}else if(select == LEAVE && isLogin()) {
			leave();
		}else if(select == LOG_IN && !isLogin()) {
			login();
		}else if(select == LOG_OUT && isLogin()) {
			logout();
		}else if(select == SHOPPING && isLogin()) {
			shopping();
		}else if(select == MY_PAGE && isLogin()) {
			myPage();
		}
	}
	
	private void printFile() {
		System.out.println("[1] 자동저장");
		System.out.println("[2] 자동로드");
	}
	
	private void save() {
		fileManager.createString(userManager, itemManager);
	}
	
	private void load() {
		fileManager.loadString(userManager, itemManager);
	}
		
	private void runFile(int select) {
		if(select == SAVE) {
			save();
		}else if(select == LOAD) {
			load();
		}
	}
	
	private void printAdmin() {
		System.out.println("[1] 아이템");
		System.out.println("[2] 조회");
	}
	
	
	private void printItemSubMenu() {
		System.out.println("[1] 등록");
		System.out.println("[2] 삭제");
		System.out.println("[3] 수정");
	}
	
	private void addItem() {
		printItemAll();
		String title = inputString("상품명");
		int price = inputNumber("가격");
		
		if(price < 0) {
			System.err.println("잘못된 가격입니다.");
			return;
		}
		
		Item item = itemManager.addItem(title, price);
		System.out.printf("%s (%d원) 상품이 추가되었습니다.\n",item.getTitle(),item.getPrice());
	}
	
	private void printItemAll() {
		ArrayList<Item> list = itemManager.getList();
		System.out.println("상품 목록 ▼");
		for(Item item : list) {
			System.out.printf("(%d) %s : %d원\n",item.getCode(),item.getTitle(),item.getPrice());
		}
	}
	
	private void printItemMessage(Item item) {
		String message = item.getCode() != 0 ? String.format("%s 상품이 삭제되었습니다.",item.getTitle())
				: "상품코드를 다시 확인하세요.";
		System.out.println(message);
	}
	
	private void deleteItem() {
		printItemAll();
		int code = inputNumber("삭제할 상품코드");
		Item item = itemManager.deleteItem(code);
		userManager.deleteItem(code);
		printItemMessage(item);
	}
	
	private void updateItem() {
		printItemAll();
		int code = inputNumber("수정할 상품코드");
		Item item = itemManager.findItemByItemCode(code);
		if(item.getCode() == 0) {
			System.out.println("상품코드를 다시 확인하세요.");
			return;
		}
		
		int price = inputNumber("수정할 가격");
		
		if(price < 0) {
			System.out.println("잘못된 가격입니다.");
			return;
		}
		
		itemManager.updateItem(price,item);
		userManager.updateItem(code,price);
	}
	
	private void runItemSubMenu(int select) {
		if(select == ADD_ITEM) {
			addItem();
		}else if(select == DELETE_ITEM) {
			deleteItem();
		}else if(select == UPDATE_ITEM) {
			updateItem();
		}
	}
	
	private void trunOver() {
		int turnover = itemManager.getTurnOver();
		System.out.println("총 매출액 : "+turnover);
	}
	
	private void runAdmin(int select) {
		if(select == ITEM) {
			printItemSubMenu();
			int sel = inputNumber("Item SubMenu");
			runItemSubMenu(sel);
		}else if(select == TURNOVER) {
			trunOver();
		}
	}
	
	private void runMainMenu(int select) {
		if(select == USER) {
			printUser();
			int sel = inputNumber("User subMenu");
			runUser(sel);
		}else if(select == FILE) {
			printFile();
			int sel = inputNumber("File subMenu");
			runFile(sel);
		}else if(select == ADMIN) {
			printAdmin();
			int sel = inputNumber("Admin subMenu");
			runAdmin(sel);
		}
	}
	
	private boolean isLogin() {
		return logCode != 0 ? true : false;
	}
	
	public void run() {
		while(true) {
			printMainMenu();
			int select = inputNumber("main menu");
			runMainMenu(select);
		}
	}
}

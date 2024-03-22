package shop;

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
	
	private void setSystem() {
		
	}
	
	public Shop(String message) {
		this.title = message;
		setSystem();
	}
	
	private void printMainMenu() {
		System.out.println("-=- "+title+" -=-");
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
	
	private void join() {
		
	}
	
	private void leave() {
		
	}
	
	private void login() {
		
	}
	
	private void logout() {
		
	}
	
	private void shopping() {
		
	}
	
	private void printCart() {
		
	}
	
	private void printMypageSubMenu() {
		System.out.println("[1] 항목삭제");
		System.out.println("[2] 수량수정");
		System.out.println("[3] 결제");
	}
		
	private void delete() {
		
	}
	
	private void update() {
		
	}
	
	private void payment() {
		
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
		if(select == JOIN) {
			join();
		}else if(select == LEAVE) {
			leave();
		}else if(select == LOG_IN) {
			login();
		}else if(select == LOG_OUT) {
			logout();
		}else if(select == SHOPPING) {
			shopping();
		}else if(select == MY_PAGE) {
			myPage();
		}
	}
	
	private void printFile() {
		System.out.println("[1] 자동저장");
		System.out.println("[2] 자동로드");
	}
	
	private void save() {
		
	}
	
	private void load() {
		
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
		
	}
	
	private void deleteItem() {
		
	}
	
	private void updateItem() {
		
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
	
	public void run() {
		while(true) {
			printMainMenu();
			int select = inputNumber("main menu");
			runMainMenu(select);
		}
	}
}

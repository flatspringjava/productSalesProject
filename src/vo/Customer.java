package vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 박연재, 이재원
 *
 */
public class Customer{ // 회원 정보

	private int no; // 회원번호
	private int money; // 자산
	private String id; // 아이디
	private String password; // 비밀번호
	private String address; // 주소
	private Cart cart = new Cart();
	private List<Product> ownProducts = new ArrayList<Product>();
	
	public Customer(int no, String id, String password, String address,int money) {
		// 들어온 인자들을 Custormer의 필드에 저장

		this.no = no;
		this.money = money;
		this.id = id;
		this.password = password;
		this.address = address;
	}

	// 각 필드들에 대한 Getter Setter 선언
	public List<Product> getOwnProducts() {
		return ownProducts;
	}



	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	// Customer의 toString() 메서드 오버라이딩
	@Override
	public String toString() {
		String s1 = null;
		s1 = String.format("%3d    %4s    %4s    %7s    %7d", no, id, password, address, money);
		return s1;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	
}

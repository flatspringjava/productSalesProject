package vo;

/**
 * 필드 선언 및 기본 생성자, 투스트링, 게터세터, 유징필드 생성
 * @author 이창용 
 */
public class Product implements Comparable<Product>, Cloneable{


	private int no; // 상품번호
	private String name; // 상품명
	private int price = 5000; // 판매가격
	private int cnt = 20; // 재고
//	private int currentCnt; // 현재 장바구니에 담긴 갯수
	private int category; // 카테고리

	public Product(int no, String name, int category) {
		super();
		this.no = no;
		this.name = name;
		this.category = category;
	}
	

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	@Override
	public int compareTo(Product o) {
		return -(o.getNo() - getNo());
	}

	@Override
	public String toString() {	// 상품 리스트 조회시 나올 정보 
		String line = String.format("%4d    %7s    %7d    %7d", no, name, price, cnt);		
		return line;
	}
	
	@Override
	public Product clone() {
		Product product = null;
		try {
			product = (Product) super.clone();		
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return product;
	}

}

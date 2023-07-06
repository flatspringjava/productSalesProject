package vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 이재원
 * Get, Set / Using Field / ToString / 기본생성자 선언
 */
public class Cart implements Cloneable{
	private int totalPrice; // 상품 총 금액
	private int totalCnt; // 상품 총 갯수
	private List<Product> productList;
	

	public List<Product> getProductList() {
		return productList;
	}
	


	public void setProductList(List<Product> productList) {
		this.productList = productList;
		for(Product p : productList) {
			totalPrice += p.getPrice() * p.getCnt();
			totalCnt += p.getCnt();
		}
	}



	public Cart(List<Product> products) {
		setProductList(products);
	}


	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalcnt() {
		return totalCnt;
	}

	public void setTotalcnt(int totalcnt) {
		this.totalCnt = totalcnt;
	}

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



	@Override
	public Cart clone() {
		Cart cart = null;
		try {
			cart = (Cart) super.clone();
			cart.productList = new ArrayList<Product>(productList);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cart;
	}
	
}

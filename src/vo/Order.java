package vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order{
	/**
	 * @author 이시화 
	 * 변수선언 및 기본생성자, 게터세터, tostring, using 필드 생성
	 * 
	 */
	private int no; // 주문번호
	private Date orderTime;// 주문시각
	private Cart cart;
	private Customer customer;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Order(int no, Date orderTime, Cart cart, Customer customer) {
		super();
		this.no = no;
		this.orderTime = orderTime;
		this.cart = cart;
		this.customer = customer;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "주문번호:" + no + "		주문자ID: " + customer.getId() +"\r\n주문일시:" + today.format(orderTime) ;
	}

	
//	@Override
//	public Order clone() {
//		Order order = null;
//		try {
//			order = (Order) super.clone();
//		} catch (CloneNotSupportedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return order;
//	}
	
}

package service;

import static utils.CustomerUtils.*;
import static utils.ExceptionUtils.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.NullException;
import vo.Order;
import vo.Product;
import vo.Cart;
import vo.Customer;


public class OrderServiceImpl implements OrderService {
	/**
	 * 싱글턴
	 * 
	 * @author 이시화
	 */
	private static OrderService orderService = new OrderServiceImpl();

	private OrderServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public static OrderService getInstance() {
		return orderService;
	}

	/**
	 * 싱글턴 불러오기
	 * 
	 * @이시화
	 */
	private static CustomerService customerService = CustomerServiceImpl.getInstance();
	private static CartService cartService = CartServiceImpl.getInstance();
	private static ProductService productService = ProductServiceImpl.getInstance();
	/**
	 * 필드 선언
	 */
	private List<Order> orders = new ArrayList<Order>();// 지금까지 주문서모음 매출조회 위해서
	
	private Customer user() {
		return customerService.getLoginUser();
	}

	
	
	

	/**
	 * findby 선언
	 */
	private Order findBy(int no) {
		Order order = null;
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getNo() == no) {
				order = orders.get(i);
				break;
			}
		}
		return order;
	}

	/**
	 * @author 이시화 구매 기능 구현 구매 버튼시 장바구니 불러와서 출력
	 */

	@Override
	public void buy() {
		Customer loginUser = customerService.getLoginUser();
		Cart cart = new Cart(cartService.stuffProducts());
        List<Product> copyOfProductList = new ArrayList<Product>();
        for (Product p : cart.getProductList()) {
        	copyOfProductList.add(p.clone());
        }
        
        if(copyOfProductList.isEmpty()) {
        	System.err.println("현재 장바구니에는 비어있습니다.");
        	return;
        }
        
        
        Cart copyCart = new Cart(copyOfProductList).clone();
        if(checkRange(nextInt("구매하시겠습니까?\n" + "1. 예     2. 아니오"), 1, 2) == 2) {
			return;
		}
        
        
        
		if(loginUser.getMoney() < copyCart.getTotalPrice()) {
			System.err.println("잔고가 부족합니다.");
			return;
		}
		
		
		
		int no = 0;//주문번호 부여
		if (orders.isEmpty()) {
			no = 1;
		} else {
			no = orders.get(orders.size() - 1).getNo() + 1;
		}


		Date orderTime = new Date();//주문일시 현재시간으로지정
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("=====================" + user().getId() + "님 주문서 ======================");
		System.out.println("주문일시 :" +today.format(orderTime));						// 주문일시 출력
		cartService.list();															//카트리스트 출력
		

		// 주문 완료 로직
		loginUser.setMoney(loginUser.getMoney() - copyCart.getTotalPrice());
		for(int i = 0; i < copyCart.getProductList().size(); i++) {
			for(int j = 0; j < productService.getProducts().size(); j++) {
				if(productService.getProducts().get(j).getNo() == copyCart.getProductList().get(i).getNo()) {
					productService.getProducts().get(j).setCnt(productService.getProducts().get(j).getCnt() - copyCart.getProductList().get(i).getCnt());
				}
			}
		}
		System.out.println("주문이 성공적으로 완료되었습니다. 감사합니다");
		System.out.println();
		Order order = new Order(no, orderTime, copyCart, user());//오더리스트 담기 ( Cart의 clone() 깊은 복사를 활용하여 구현
		orders.add(order);
		cart.getProductList().clear();
	}
	


	/**
	 * 매출 조회(관리자 시점) 현재까지의 매출
	 */
	public void sales() {
		int sales = 0;
		int cnt = 0;
		for (int i = 0; i < orders.size(); i++) {
			sales += orders.get(i).getCart().getTotalPrice();
		}
		for (int j = 0; j < orders.size(); j++) {
			cnt += orders.get(j).getCart().getTotalcnt();
		}
		System.out.println("현재까지의 총 주문 건수는" + orders.size() + "건이며 총 상품 판매 개수는" + cnt + "개, 총매출은" + sales + "원입니다.");

	}

	/**
	 * 현재까지 주문서 각각 불러오기 (주문번호로)(관리자시점) 주문번호 받아서 해당투스트링
	 */
	public void salesList() {
		StringBuilder sb = new StringBuilder();
		
		System.out.println("=================== 주문서 조회 ===================");
		Order order = findBy(nextInt("주문번호를 입력해주세요"));
		if (order == null) {// 예외값 처리
			System.out.println("해당하는 주문번호가 없습니다");
			return;
		}
		System.out.println(order.toString());
		
		System.out.println("상품번호  품 명   판매가격   구매 갯수   합  계 ");
		for (Product p : order.getCart().getProductList()) {
			sb.append(String.format("%4d    %4s    %5d    %6d     %9d\n", p.getNo(), p.getName(), p.getPrice(),
					p.getCnt(), p.getPrice() * p.clone().getCnt()));
		}
		System.out.println(sb);
		System.out.println("===================== 합  계 ========================");
		System.out.println("총 상품갯수 : " + order.getCart().getTotalcnt() + "개");
		System.out.println("총 결제금액 : " + order.getCart().getTotalPrice() + "원");
	}

}
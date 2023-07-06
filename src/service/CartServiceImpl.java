package service;

import static utils.CustomerUtils.nextInt;
import static utils.ExceptionUtils.*;
import static utils.NullException.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import utils.NullException;
import vo.Cart;
import vo.Product;

/**
 * 
 * <h1>카트 서비스</h1>
 * <li>list(), add(), remove() 기능을 이용할 수 있습니다.</li>
 * <hr>
 * </hr>
 * 
 * @author 박연재, 이창용
 * 
 * 
 */
public class CartServiceImpl implements CartService {
	// 카트 싱글톤 추가
	private static CartService cartService = new CartServiceImpl();

	private CartServiceImpl() { // 다른 클래스에서 접근하지 못하게 하려는 의도로 접근제어자 설정을 통해 보안성을 높임.

	}

	public static CartService getInstance() { // getInstance메서드를 통해 CardService를
		return cartService;

	}

	// 싱글톤을 활용해 현재 사용자의 보유 장바구니와 상품 리스트를 가져옴
	private CustomerService customerService = CustomerServiceImpl.getInstance();
	private ProductService productService = ProductServiceImpl.getInstance();

	private List<Product> productsInCart = new ArrayList<Product>();

	public Cart getCart() {
		return customerService.getLoginUser().getCart();
	}
	
	public List<Product> stuffProducts(){
		return productsInCart;
	}

	/**
	 * 
	 * <li>현재 장바구니의 리스트 조회</li>
	 * 
	 */
	@Override
	public void list() {
		StringBuilder sb = new StringBuilder();
		System.out.println("================== 장바구니 ====================");

		System.out.println("상품번호  품 명   판매가격   구매 갯수   합  계 ");
		for (Product p : productsInCart) {
			sb.append(String.format("%4d    %4s    %5d    %6d     %9d\n", p.getNo(), p.getName(), p.getPrice(),
					p.getCnt(), p.getPrice() * p.getCnt()));
		}
		int sumprice = 0; // vo. Card에있는 total 필드 선언한곳에 넣기.
		int sumcnt = 0;
		for(Product p : productsInCart) { // 장바구니에 있는 상품의 총 개수를 출력할 때마다 sumcnt에 누적시킴.
			sumcnt += p.getCnt();
			sumprice += p.getPrice() * p.getCnt();
		}
		
		getCart().setTotalPrice(sumprice);
		getCart().setTotalcnt(sumcnt);
		System.out.println(sb);
		System.out.println("===================== 합  계 ========================");
		System.out.println("총 상품갯수 : " + sumcnt + "개");
		System.out.println("총 결제금액 : " + sumprice + "원");
	}

	/**
	 * 
	 * 장바구니에 추가할 상품 번호 입력 받고 갯수까지 입력
	 * 
	 */
	@Override
	public void addBy(int productNo) {

		int remain = productService.findBy(productNo).clone().getCnt();// 현재 남아 있는 재고 갯수
		if(productService.findBy(productNo).getCnt() == 0) {
			System.err.println("재고가 없습니다.");
		}
		
		int cnt = checkRange(nextInt("갯수를 입력하세요."), 1, remain);
		
		if(cnt > remain) {
			System.err.println("재고가 부족합니다.");
			return;
		}
		if (productsInCart.isEmpty()) { 																				// 장바구니의 상품 리스트가 비어있으면 추가한다.
			productsInCart.add(productService.findBy(productNo).clone());																																			
			productsInCart.get(productsInCart.size() - 1).setCnt(cnt);											// 상품 재고 갯수를 현재 고객의 장바구니 내에 있는 상품의 현재																										
			System.out.println("상품을 성공적으로 추가하였습니다."); 														// 상품을 성공적으로 추가하였다는 문구를 띄워준다.
			sort(); 																										// 장바구니 내의 상품들을 번호를 기준으로 오름차순 정렬시킨다.
			return;
		}
		if(findBy(productNo) == null) {
			productsInCart.add(productService.findBy(productNo).clone()); 						// 장바구니에 추가
			productsInCart.get(productsInCart.size() - 1).setCnt(cnt); 							// 선택한 상품의 갯수 입력 받음
			System.out.println("상품을 성공적으로 추가하였습니다."); 							// 추가하였다는 문구를 띄워줌
			sort();
			return;
		}
		if (productService.findBy(productNo).getNo() == this.findBy(productNo).getNo()) { 							// 카트에 있는 상품과 진열되어 있는 상품이 동일할 경우
			if(findBy(productNo).getCnt() + cnt > remain) {
				System.err.println("재고가 부족합니다.");
				return;
			}
			cartService.findBy(productNo).setCnt(findBy(productNo).getCnt() + cnt); 							// 카트내의 상품에 번호를 겹쳐서 추가한다.
			System.out.println("상품을 성공적으로 추가하였습니다.");
			sort();
			return;
		}

		
																					// 오름차순 정렬
	}

	/**
	 * 
	 * <li>제거할 상품을 상품번호로 입력받아 상품 탐색</li>
	 * 
	 * @param no
	 * @return Product
	 */
	@Override
	public Product findBy(int no) {
		Product product = null;
		for(int i = 0; i < productsInCart.size(); i++) {
			if(productsInCart.get(i).getNo() == no) {
				product = productsInCart.get(i);
				break;
			}
		}
		return product;
	}

	/**
	 * 
	 * <li>장바구니 내에 있는 상품 제거</li>
	 * 
	 */
	@Override
	public void remove() { // 현재 로그인 된 유저의 장바구니를 삭제하는 기능
		try{
			if(productsInCart.isEmpty()) {
				System.err.println("제거할 상품이 존재하지 않습니다.");
				return;
			}
			list();																			// 목록을 보여준다.
			productsInCart.remove(checkNull(findBy(nextInt("제거할 상품을 선택하세요 : "))));  // 제거할 상품을 번호로 선택한다. ( 만약에 입력받은 번호의 상품이 존재하지 않으면 문구를 띄우고 메뉴로 돌아간다.
			System.out.println("장바구니의 상품이 성공적으로 제거되었습니다.");
		}catch (NullException e) {
			System.err.println(e.getMessage());
		}
		sort();
	}

	/**
	 * 
	 * 
	 * <h1>List를 활용하여 고객의 장바구니 내에 있는 상품번호 정렬</h1>
	 *
	 */
	public void sort() {
		productsInCart.sort(new Comparator<Product>() { // 로그인된 회원의 장바구니를 Comparator 기능을 사용하여 정렬 의도
			@Override
			public int compare(Product o1, Product o2) { // 비교대상 o1, o2 객체 생성
				// TODO Auto-generated method stub
				return o1.getNo() - o2.getNo();
			}

		});

	}

}

package main;

import static utils.CustomerUtils.*;
import static utils.ExceptionUtils.*;

import java.util.List;

import service.CartService;
import service.CartServiceImpl;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.OrderService;
import service.OrderServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;
import utils.MyRangeException;
import vo.Product;

/**
 * 
 * <p>
 * 기능을 실행하는 구간</br>
 * 조장 - 박연재 </br>
 * 팀원 - 박연재, 이시화, 이창용, 이재원
 * </p>
 * 
 * @author 박연재
 * @author 이시화
 * @author 이창용
 * @author 이재원
 * @version 1.0.0
 *
 */
public class Main {

	// 각 서비스를 싱글톤을 이용하여 가져옴
	private static CustomerService customerService = CustomerServiceImpl.getInstance();
	private static ProductService productService = ProductServiceImpl.getInstance();
	private static CartService cartService = CartServiceImpl.getInstance();
	private static OrderService orderService = OrderServiceImpl.getInstance();

	/**
	 * 메인 메서드
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("마켓에 오신 것을 환영합니다!");
		initProducts();
		while (true) {
			try {
				mainMenu();
			} catch (NumberFormatException e) {
				System.err.println("숫자를 입력해야 합니다.");
			} catch (MyRangeException e) {
				System.err.println(e.getMessage());
			}
		}

	}

	/**
	 * 상품 생성
	 * 
	 */
	private static void initProducts() {
		productService.initProducts();
	}

	private static void mainMenu() {
		while (true) {
			try {
				System.out.println("================== 메인 메뉴 =====================");
				switch (checkRange(nextInt("1. 장보기    2. 로그인    3. 회원가입    4. 종료하기"), 1, 4)) {
				case 1:
					if (customerService.getLoginUser() == null) {
						System.out.println("로그인이 되어 있지 않습니다.");
						break;
					}
					break;
				case 2:
					if (customerService.login() == null) {
						System.out.println("로그인에 실패하였습니다.");
						break;
					}
					System.out.println("성공적으로 로그인 되었습니다.");
					LoggedMenu();
					break;
				case 3:
					customerService.register();
					break;
				default:
					System.exit(0);
				}
			}catch (NumberFormatException e) {
				System.err.println("숫자를 입력해야 합니다.");
			} catch (MyRangeException e) {
				System.err.println(e.getMessage());
			}
			
		}

	}

	/**
	 * 
	 * 로그인 되어 있는 메뉴
	 * 
	 */
	private static void LoggedMenu() {
		System.out.println(customerService.getLoginUser().getId() + "님, 어서오세요!");
		while (true) {
			try {
				System.out.println("==================== 메인 메뉴 =====================");
				switch (checkRange(nextInt("1. 장보기    2. 영수증 조회    3. 총 매출액    4. 회원 리스트 \n" 
				+ "5. 회원수정     6. 회원탈퇴    7. 로그아웃"), 1, 7)) {
				case 1:
					
					if (customerService.getLoginUser() == null) {
						System.out.println("로그인이 되어 있지 않습니다.");
						break;
					}
					shopMenu();
					break;
				case 2:
					orderService.salesList();
					break;
				case 3:
					orderService.sales();
					break;
				case 4:
					customerService.list();
					break;
				case 5:
					customerService.modify();
					break;
				case 6:
					customerService.delete();
					
				default:
					mainMenu();
				}
			}catch (NumberFormatException e) {
				System.err.println("숫자를 입력해야 합니다.");
			} catch (MyRangeException e) {
				System.err.println(e.getMessage());
			}
			
		}
	}

	/**
	 * 
	 * 장보기 메뉴 구성
	 * 
	 */
	private static void shopMenu() {
		while (true) {
			try {
				System.out.println("==================== 서비스 메뉴 =====================");
				switch (checkRange(nextInt("1. 상품 고르기    2. 장바구니	 3. 장바구니 상품 제거   4. 결제    5. 뒤로가기"), 1, 5)) {
				case 1:
					selectProductMenu(); // 카테고리가 나오고 카테고리의 상품을 고를 수 있는 메뉴가 나온다.
					break;
				case 2:
					if (cartService.stuffProducts().isEmpty()) {
						System.err.println("현재 장바구니에는 비어있습니다.");
						break;
					}
					cartService.list();
					break;
				case 3:
					// orderService 관련 기능이 포함되어 있어야함 ( 주문 확인서 => 결제)
					cartService.remove();
					break;
				case 4:					
					orderService.buy();
				default:
					LoggedMenu(); // 로그인된 메뉴로 돌아감
					break;
				}
			}catch (NumberFormatException e) {
				System.err.println("숫자를 입력해야 합니다.");
			} catch (MyRangeException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * 상품 카테고리 메뉴
	 */
	private static void selectProductMenu() {
		int select, start, end;
		while (true) {
			try {
				System.out.println("==================== 카테고리 =====================");
				int input = checkRange(nextInt("1. 과일     2. 채소     3. 육류    4. 전자 제품\n5. 견과류    6. 뒤로가기"), 1, 6);
				if (input == 6) {
					shopMenu();
				} else {
					List<Product> list = productService.findByCategory(input);	// 카테고리 번호로 해당 카테고리의 상품을 뽑아온다.
					start = list.get(0).getNo();
					end = list.get(list.size() - 1).getNo();
					select = checkRange(nextInt("번호로 선택하실 상품을 고르세요 : "), start, end);
					cartService.addBy(select);									// 상품 번호를 add(int no)의 인자로 받아서 상품의 수량 추가 및 상품을 추가한다. 
				}
			}catch (NumberFormatException e) {
				System.err.println("숫자를 입력해야 합니다.");
			}catch (MyRangeException e) {
				System.err.println(e.getMessage());
			}
			
		}
	}

}

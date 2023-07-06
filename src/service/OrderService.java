package service;

import vo.Order;
import vo.Cart;
import vo.Customer;
/**
 * buy - Customer, Cart, Product Service의 메서드와 필드를 끌어와 유저가 가지고 있는 카트안에 있는 제품을 구매하는 기능 
 * sales - 관리자시점에서 매출을 조회하는 기능
 * salesList - 관리자시점에서 과거의 주문서를 조회할 수 있는 기능
 * @author 이시화
 */
public interface OrderService {
	
    void buy();
    void sales();
    void salesList();
    
}
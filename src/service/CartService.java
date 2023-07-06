package service;

import java.util.List;

import vo.Cart;
import vo.Product;

/**
 * <h1>카트 서비스 인터페이스</h1>
 * <p> list(), add(int productNo), remove(), sort(), findByProductNo(int no)
 * <br>등의 기능을 추상 메서드로 선언했다</br></p>
 * 
 * @author 박연재 
 * @author 이창용
 *
 */
public interface CartService {

	void list();

	void addBy(int productNo);

	void remove();

	void sort();
	
	Product findBy(int no);
	
	List<Product> stuffProducts();
	
	Cart getCart();
	
}
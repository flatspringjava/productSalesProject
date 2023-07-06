package service;

import static utils.CustomerUtils.nextInt;
import static utils.ExceptionUtils.checkRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import vo.Product;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 
 * 
 * 
 * 인터페이스에서 선언하여 필드에 상품 addProduct(), subtractProduct(), productList()를 구현 카테고리
 * 이름을 일차원 배열로 초기화함 장바구니 담기까지의 과정.서비스를 구현 1. 과일 2. 채소 3. 육류 누르면 하위 카테고리 설정 2. 물품
 * 개수를 입력 하세요(정수로) 3. 선택하신 "물품명" + "개수" = "총 금액" 입니다 4. 구매 하시겠습니까?
 * 
 * @author 박연재
 * @author 이재원
 */
public class ProductServiceImpl implements ProductService {

	/**
	 * 상품 싱글턴 및 필드 선언
	 */
	private static ProductService productService = new ProductServiceImpl();

	private ProductServiceImpl() {
	}

	public static ProductService getInstance() {
		return productService;
	}
	
	private String categoryName[] = { "과일", "채소", "육류", "전자제품", "견과류"};
	

	// 상품을 각각의 카테고리 별로 상품명을 일차원 배열로 선언 및 초기화 
	private String fruit[] = { APPLE, PEAR, WATERMELON, MELON, BANANA, STRAWBERRY, KIWI };
	private String vegetable[] = { CARROT, CUCUMBER, GARLIC, CABBAGE, BROCCOLI, LETTUCE };
	private String meat[] = { PORK_BELLY, BEEF, CHICKEN, SAUSAGE, SPAM, LAMB };
	private String electronic[] = { COMPUTER, TELEPHONE, TELEVISION, MONITER, CALCULATOR, RADIO, REFRIGERATOR,
			AIR_CONDITIONER };
	private String nuts[] = { WALNUT, PEANUT, ALMOND, CASHEW_NUTS, PISTACHIO, HAZELNUT };
	private String[][] allProducts = { fruit, vegetable, meat, electronic, nuts };

	private List<Product> products = new ArrayList<Product>();					// 진열할 상품 리스트

	public List<Product> getProducts() {
		return products;
	}

	public Product findBy(int no) { // 상품 진열장 중에 하나만 고른다.
		Product product = null;
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getNo() == no) {
				product = products.get(i);
			}
		}
		return product;
	}

	
	private void printBy(List<Product> productList,int categoryNo) {
		int idx = categoryNo - 1;
		System.out.println("=================" + categoryName[idx] + "==================");
		System.out.println("번호       상품명       가격       재고");
		for (Product p : products) {
			if (p.getCategory() == categoryNo) {
				productList.add(p);
				System.out.println(p.toString());
			}
		}
	}
	
	public List<Product> findByCategory(int no) {
		List<Product> list = new ArrayList<Product>();
		System.out.println();
		switch (no) {
		case FRUIT:
				printBy(list, FRUIT);
			break;
		case VEGETABLE:
				printBy(list, VEGETABLE);
			break;
		case MEAT:
				printBy(list, MEAT);
			break;
		case ELECTRONIC_PRODUCTS:
				printBy(list, ELECTRONIC_PRODUCTS);
			break;
		case NUTS:
				printBy(list, NUTS);
			break;
		}
		return list;
	}

	/**
	 * 
	 * <p>상품 리스트 내에 진열할 상품 생성</p>
	 * @author 박연재
	 * 
	 */
	// 상품이 추가되는 기능
	public void initProducts() {

		int no = 0;
		int categoryNo = 1;
		for (int i = 0; i < allProducts.length; i++) {
			for (int j = 0; j < allProducts[i].length; j++) {
				products.add(no++, new Product(no, allProducts[i][j], categoryNo));
			}
			categoryNo++;
			sort();
		}
	}

	/**
	 * 
	 * 
	 * List를 활용하여 배열리스트로 변환한 다음 Entry로 정렬
	 * 
	 * @author 박연재
	 *
	 */
	public void sort() {
		products.sort(new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getNo() - o2.getNo();
			}

		});

	}

}

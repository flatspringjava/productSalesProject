package service;

import java.util.List;
import java.util.Map;

import vo.Product;

/**
 * 
 * 
 * 상수값(카테고리, 각 물품명)을 배열에 넣기위한 인터페이스
 * @author 박연재
 * @author 이재원
 */
public interface ProductService {
	
	
	// 카테고리 ( 번호로 구분 )
	public static final int FRUIT = 1;
	public static final int VEGETABLE = 2;
	public static final int MEAT = 3;
	public static final int ELECTRONIC_PRODUCTS = 4;
	public static final int NUTS = 5;
	
	// 과일
	public static final String APPLE = "사과";
	public static final String PEAR = "배";
	public static final String WATERMELON = "수박";
	public static final String MELON = "멜론";	
	public static final String BANANA = "바나나";
	public static final String STRAWBERRY = "딸기";
	public static final String KIWI = "키위";
	// 채소
	public static final String CARROT = "당근";
	public static final String CUCUMBER = "오이";
	public static final String GARLIC = "마늘";
	public static final String CABBAGE = "배추";
	public static final String BROCCOLI = "브로콜리";
	public static final String LETTUCE = "상추";		
	
	// 육류
	public static final String PORK_BELLY = "삼겹살";
	public static final String BEEF = "소고기";	
	public static final String CHICKEN = "닭고기";
	public static final String SAUSAGE = "소세지";
	public static final String SPAM = "햄";	
	public static final String LAMB = "양고기";
	
	
	// 전자제품
	public static final String COMPUTER = "컴퓨터";	
	public static final String TELEPHONE = "휴대전화";	
	public static final String TELEVISION = "텔레비전";	
	public static final String MONITER = "모니터";	
	public static final String CALCULATOR = "계산기";	
	public static final String RADIO = "라디오";	
	public static final String REFRIGERATOR = "냉장고";	
	public static final String AIR_CONDITIONER = "에어컨";
	
	// 견과류
	public static final String WALNUT = "호두";	
	public static final String PEANUT = "땅콩";	
	public static final String ALMOND = "아몬드";	
	public static final String CASHEW_NUTS = "캐슈넛";	
	public static final String PISTACHIO = "피스타치오";	
	public static final String HAZELNUT = "헤이즐넛";	
	
	// 가격 단위
	public static final int TEN_THOUSAND = 10000;
	public static final int FIVE_THOUSAND = 5000;
	public static final int	ONE_THOUSAND = 1000;
	public static final int FIVE_HUNDRED = 500;
	public static final int ONE_HUNDRED = 100;

	
	/**
	 * 
	 * 진열되어 있는 모든 상품들 불러오는 기능
	 * @return List
	 */
	List<Product> getProducts();
	
	
	/**
	 * 
	 * 카테고리 번호로 해당 카테고리의 상품 조회 기능
	 * @param no
	 * @return List
	 */
	List<Product> findByCategory(int no);
	
	
	/**
	 * 
	 * 상품들 중 하나만 탐색하는 기능
	 * @param no
	 * @return
	 */
	Product findBy(int no);
	
	/**
	 * 상품 생성
	 * 
	 */
	void initProducts();

	
	// 번호에 의해 오름차순 정렬
	void sort();

}

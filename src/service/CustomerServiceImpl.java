package service;

import static utils.CustomerUtils.nextInt;
import static utils.CustomerUtils.nextLine;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.imageio.spi.RegisterableService;

import utils.CustomerUtils;
import vo.Customer;
import java.util.Set;

/**
 * 
 * 
 * 
 * 
 * @author 이시화
 *
 */
public class CustomerServiceImpl implements CustomerService {
	/**
	 * 싱글턴
	 */
	private static CustomerService customerService = new CustomerServiceImpl();

	private CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public static CustomerService getInstance() {
		return customerService;
	}

	/**
	 * 필드
	 */
	private List<Customer> customers = new ArrayList<Customer>();// 회원리스트

	/**
	 * 로그인 상태값 및 생성자
	 */
	private Customer loginUser;// 현재 로그인된 사람

	public Customer getLoginUser() {
		return loginUser;
	}

	
	/**
	 * 
	 * findBy 메서드 구현 회원번호값과 아이디, 비밀번호값
	 * @author 이시화 
	 * 
	 */
	@SuppressWarnings("unused")
	private Customer findBy(int no) {// 회원번호
		Customer customer = null;
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getNo() == no) {
				customer = customers.get(i);
				break;
			}

		}
		return customer;
	}

	private Customer findBy(String id) {// 회원아이디
		Customer customer = null;
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId().equals(id)) {
				customer = customers.get(i);
				break;
			}

		}
		return customer;
	}

	private Customer findBy(String id, String password) {// 회원아이디와 비밀번호(로그인 유저값)
		Customer customer = null;
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getPassword().equals(password) && customers.get(i).getId().equals(id)) {
				customer = customers.get(i);
				break;
			}

		}
		return customer;
	}

	/**
	 * @author 이창용 회원등록위한 register 메서드 구현,
	 */
	/**
	 * @author 이시화 no값 생성 조건, 아이디 중복값 구현
	 */
	@Override
	public void register() {
		System.out.println("================= 회원 가입 =====================");
		String id = nextLine("아이디를 입력해 주세요 :"); // 아이디는 유일값으로 설정, 중복값 방지
		if (findBy(id) != null) {
			System.err.println("중복된 아이디가 존재합니다");
			return;
		}
		String password = nextLine("비밀번호를 입력해 주세요 :");
		String address = nextLine("주소를 입력해주세요 :");

		int no = 0;// 회원번호 부여 1부터 순차적으로 임의부여 가능하도록
		if (customers.isEmpty()) {
			no = 1;
		} else {
			no = customers.get(customers.size() - 1).getNo() + 1;
		}

		Customer customer = new Customer(no, id, password, address, 100000);
		customers.add(customer);// 회원리스트에 추가
		System.out.println("회원가입이 성공적으로 완료되었습니다.");

	}

	/**
	 * 
	 * @author 박연재 로그인유저에 대한 아이디,비밀번호,주소 수정
	 * 
	 * @author 이시화 ui흐름에 맞게 수정
	 */
	@Override
	public void modify() {
		if (loginUser == null) {// 로그인중인 유저 검증
			System.err.println("회원 수정을 하려면 로그인을 해주세요");
			return;
		}
		System.out.println("================= 회원 수정 =====================");
		String password1 = nextLine("현재 비밀번호를 입력해 주세요 : "); // 현재 비밀번호를 입력 받음
		if (findBy(loginUser.getId(), password1) == null) {
			System.err.println("비밀번호가 틀렸습니다");
			return;
		}

		String password = nextLine("변경할 비밀번호를 입력해 주세요 : "); // 바꿀 비밀번호를 입력 받음
		String address = nextLine("변경할 주소를 입력해 주세요 : "); // 주소를 입력 받음
		loginUser.setPassword(password);
		loginUser.setAddress(address);
		System.out.println("회원 수정을 완료하였습니다.");
	}

	/**
	 * @author 이시화 회원 탈퇴를 위한 delete 메서드 구현 로그인중인 유저가 탈퇴를 할 경우에만, 비밀번호를 한번 더 입력 받고 다시
	 *         한번 물어봐야함
	 */
	@Override
	public void delete() {
		if (loginUser == null) {// 로그인중인 유저 검증
			System.err.println("회원 탈퇴를 하려면 로그인을 해주세요");
			return;
		}
		System.out.println("================= 회원 탈퇴 =====================");
		String password1 = nextLine("비밀번호를 입력해 주세요 : "); // 현재 비밀번호를 입력 받음
		if (findBy(loginUser.getId(), password1) == null) {
			System.err.println("비밀번호가 틀렸습니다");
			return;
		}

		System.err.println("정말 탈퇴하시겠습니까?");// 한번 더 물어보기
		switch (nextInt("1. 예 2. 아니요")) {
		case 1:
			customers.remove(loginUser);
			System.out.println("회원 탈퇴가 완료되었습니다.");
			break;

		}

	}

	/**
	 * @이재원 관리자시점에서 회원 목록을 조회할 수 있는 기능
	 */
	@Override
	public void list() {
		sort();//우선 정렬
		System.out.println("================= 회원 조회 =====================");
		System.out.println("   NO     ID      PASSWORD        주소      자산");// 출력구분
		System.out.println("=================================================");// 구분선
		for (Customer c1 : customers) {// 향상for문으로 customers의 리스트 출력
			System.out.println(c1);

		}
	}

	/**
	 * @이시화 정렬 회원 번호순으로 정렬
	 */
	@Override
	public void sort() {//comparator 활용
		customers.sort(new Comparator<Customer>() {
			public int compare(Customer o1, Customer o2) {
				return o1.getNo() - o2.getNo();
			}

		});
	}

	/**
	 * @author 이시화 로그인 기능 구현 및 로그인 상태값 저장
	 */
	public Customer login() {//중복 아이디 값이 있을경우 비밀번호 입력받아 맞다면 로그인중인 유저에 넣기
		System.out.println("================= 회원 로그인 ====================");
		String id = nextLine("아이디를 입력해 주세요 :");
		if (findBy(id) != null) {
			String password = nextLine("비밀번호를 입력해 주세요 :");
			loginUser = findBy(id, password);
		}
		return loginUser;
	}
	
	/**
	 * 
	 * 
	 * 데이터 보존 및 세션 아웃 기능 구현
	 * @author 박연재
	 * 
	 * 
	 */
	public void logout() {
		
		for(Customer c : customers) {
			if(c.getNo() == loginUser.getNo()) {
				c = loginUser;
			}
		}
		System.out.println(loginUser.getId() + "님, 좋은 하루 보내세요.");
		loginUser = null;
	}

}

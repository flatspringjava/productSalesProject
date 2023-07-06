package service;

import java.util.List;

import vo.Customer;

/**
 * 
 * @author 
 * @author
 * @author
 */
public interface CustomerService {
	/**
	 * @author 이창용
	 */
	void register();// 회원정보 등록

	/**
	 * @author 박연재
	 */
	void modify();// 수정

	/**
	 * @author 이재원
	 */
	void list();

	/**
	 * @author 이시화
	 */
	void delete();// 삭제

	void sort();
	
	void logout();
	
	Customer login();

	Customer getLoginUser();

}

package utils;

public class NullException extends RuntimeException{

	
	public NullException() {}
	
	public NullException(Object object) {
		super("상품이 존재하지 않습니다.");
	}
}

package utils;




/**
 * 
 * 예외 범위 클래스
 * @author 박연재
 *
 */
public class MyRangeException extends RuntimeException{
	private int start;
	private int end;
	
	public MyRangeException() {}

	public MyRangeException(int start, int end) {
		
		super(start + "와 " + end + "사이의 값을 입력하세요.");
	}

}

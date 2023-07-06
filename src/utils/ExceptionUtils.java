package utils;

/**
 * 
 * 예외 처리 유틸
 * 
 * @author 박연재
 *
 */
public class ExceptionUtils {

	private static MyRangeException myRangeException = new MyRangeException();
	private static NullException nullException = new NullException();

	public static int checkRange(int num) {
		return checkRange(num, 0, 100);
	}

	public static int checkRange(int num, int start, int end) {
		if (num < start || num > end) {
			throw new MyRangeException(start, end);
		}
		return num;
	}

	public static Object checkNull(Object object) {

		if(object == null) {
			throw new NullException(object);
		}
		return object;
	}
}

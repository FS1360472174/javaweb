package nio;

public class IntegerTest {
	public static void main(String[] args) {
		testInt2String();
	}
	
	private static void testInt2String(){
		System.out.println(Integer.toString(new byte[]{ 10, 112, 0, 10 }[1] & 0xFF, 10));
	}
}

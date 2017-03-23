package monitor;

import org.github.jamm.MemoryMeter;

/**
 * https://github.com/jbellis/jamm jamm:java agent memory meter
 * 
 * @author cnStoneFang
 *
 */
public class Memory {

	public static void main(String[] args) {
		int a = 1;
		memTest(a);
		a = 2;
		memTest(a);
		int b = 2;
		memTest(b);
		String str = "123456";
		memTest(str);
	}

	private static void memTest(Object obj) {
		MemoryMeter meter = new MemoryMeter();
		System.out.println(meter.measure(obj));
		System.out.println(meter.measureDeep(obj));
		System.out.println(meter.countChildren(obj));
	}
}

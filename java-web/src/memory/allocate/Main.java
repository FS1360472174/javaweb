package memory.allocate;

import java.nio.ByteBuffer;

public class Main {
	public static void main(String[] args) {

	}

	private static void allocateWithJava() {
		ByteBuffer buf = MemoryUtil.allocate(1024 * 1024 * 100, true);
	}
}

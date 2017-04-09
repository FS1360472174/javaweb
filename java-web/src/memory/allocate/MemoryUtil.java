package memory.allocate;

import java.nio.ByteBuffer;

public class MemoryUtil {
	public static ByteBuffer allocate(int size, boolean onHeap) {
		return onHeap ? ByteBuffer.allocate(size) : ByteBuffer.allocateDirect(size);
	}
}

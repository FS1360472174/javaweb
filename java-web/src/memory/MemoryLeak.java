package memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MemoryLeak {
	public static void main(String[] args) {
		// memoryLeak();
		linkQueueTest();
	}

	private static void memoryLeak() {
		List<Object>  listObj = new ArrayList<Object>();
		long iterations = 0;
		try {
			while (true) {
				++iterations;
				Object obj = new Object();
				// listObj.add(obj);
				obj = null;
			}
		} catch(OutOfMemoryError e) {
			System.err.println("iterations: " + iterations);
			throw e;
		}
	}

	private static void linkQueueTest() {

		Queue<Object> queue = new ConcurrentLinkedQueue<Object>();
		// Cassandra org.apache.cassandra.utils.concurrent.Ref L261
		// Queue<Object> queue = new ConcurrentLinkedDeque<Object>();
		queue.offer(new Object());
		Object item = new Object();
	
		long iterations = 0;
		try {
			while (true) {
				++iterations;
				queue.offer(item);
				queue.remove(item);
			}
		} catch (OutOfMemoryError e) {
			queue = null;
			System.err.println("iterations: " + iterations);
			throw e;
		}
	}
}

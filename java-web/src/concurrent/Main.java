package concurrent;

public class Main {
	public static void main(String[] args) {
		for (int i =0;i<10;i++) {
			Thread run = new Thread(new TestRunnable("name"+i));
			run.start();
			Thread thread = new TestThread("name"+i);
			thread.start();
		}
	}
}

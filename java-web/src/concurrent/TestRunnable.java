package concurrent;

public class TestRunnable implements Runnable {

	private String name;
	TestRunnable(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		System.out.println("runnable" + this.name);	
	}

}

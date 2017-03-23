package concurrent;

public class TestThread extends Thread {
	private String name;
	TestThread(String name){
		this.name = name;
	}
	public void run() {
        System.out.println("thread"+name);
    }
}

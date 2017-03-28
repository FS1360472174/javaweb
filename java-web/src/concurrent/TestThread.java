package concurrent;

import java.util.HashMap;

public class TestThread extends Thread {
	private volatile String name;
	HashMap<String, String> hash = new HashMap<String,String>();
	TestThread(String name){
		this.name = name;
	}
	public void run() {
        System.out.println("thread"+name);
    }
}

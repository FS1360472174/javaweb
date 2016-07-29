package util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ArrayListTest {
	public static void main(String[] args) {
		Gson gson = new Gson();
		List<String> list1 = new ArrayList<String>() {
			{
				add("list1");
			}
		};
		list1.add("list1-1");
		List<String> list2 = new ArrayList<String>();

		Test test = new Test();
		test.add("test2");
		System.out.println("list1:" + gson.toJson(list1) + " Class:" + list1.getClass().isAnonymousClass());
		System.out.println("list2:" + gson.toJson(list2).toString() + " Class:" + list2.getClass());
		System.out.println("test:" + gson.toJson(test) + test.getClass());
	}

}

class Test extends ArrayList<String> {
	Test() {
		add("test");
	}
}

package util;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTest {
	public static void main(String[] args) {
		Gson gson = new Gson();
		LinkedList linkedList = new LinkedList<>();
		List<String> list3 = new ArrayList<>(3);
		
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
		
		addCap();
	}

	// À©ÈÝ
	private static void addCap(){
		List<String> list = new ArrayList<>(1);
		list.add("1");
		list.add("2");
	}
}



class Test extends ArrayList<String> {
	Test() {
		add("test");
	}
}

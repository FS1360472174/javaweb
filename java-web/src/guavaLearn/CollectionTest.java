package guavaLearn;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.HashSet;
import java.util.Set;

public class CollectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<String> setVal = new HashSet<String>();
		setVal.add("1");
		setVal.add("2");
		setVal.add("3");
		System.out.println(isEqual(setVal,"1"));
		System.out.println(isSetEqual(setVal,"1"));
	}
	
	
	private static boolean isEqual(Set<String> setVal,String val){
		return Iterables.any(setVal, new Predicate<String>() {
			@Override
			public boolean apply(String input) {
				return input.equals(val);
			}
			
		});
	}
	
	private static boolean isSetEqual(Set<String>setVal,String val){
		for(String input: setVal) {
			if(val.equals(input)) {
				return true;
			}
		}
		return false;
	}
}

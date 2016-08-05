package util;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonStringtoListBean {

	public static void main(String[] args) {
		testWithGson();
		testWithJackson();
	}

	private static void testWithGson() {
		Gson gson = new GsonBuilder().create();
		String str = "[{\"id\":\"10\",\"addr\":\"ss\"}]";
		TypeToken<List<Address>> list = new TypeToken<List<Address>>() {
		};
		List<Address> listAddress = gson.fromJson(str, list.getType());
		System.out.println(listAddress);
	}

	private static void testWithJackson() {
		String str = "[{\"id\":\"10\",\"addr\":\"ss\"}]";
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = getCollectionType(mapper, List.class, Address.class);
		List<Address> listAddress = null;
		try {
			listAddress = mapper.readValue(str, javaType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listAddress);
	}

	private static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}
}

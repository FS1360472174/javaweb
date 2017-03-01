package concurrent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * java 提供了Executor来实现多线程
 * @author stoneFang
 *
 */
public class TestExecutor {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<List<String>> callable;
		callable = new Callable<List<String>>(){

			@Override
			public List<String> call() throws Exception {
				return readFile("src/concurrent/test.txt");
			}
			
		};
		Future<List<String>> future = executor.submit(callable);
		try {
			List<String> lines = future.get(5, TimeUnit.SECONDS);
			for(String line: lines) {
				System.out.println(line);
			}
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static List<String> readFile(String path){
		List<String> listTxt = new ArrayList<String>();
		File fileName = new File(path);
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
			BufferedReader buf = new BufferedReader(reader);
			String line = "";
			while(line != null) {
				listTxt.add(line);
				line = buf.readLine();	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listTxt;
	}

}

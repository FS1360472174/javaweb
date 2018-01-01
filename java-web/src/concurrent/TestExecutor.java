package concurrent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * java �ṩ��Executor��ʵ�ֶ��߳�
 * @author stoneFang
 *
 */
public class TestExecutor {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorService fixExecutor = Executors.newFixedThreadPool(10);
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

package concurrent.guava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * java 提供了Executor来实现多线程
 * @author stoneFang
 *
 */
public class TestExecutor {

	public static void main(String[] args) {
		ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
		Callable<List<String>> callable;
		callable = new Callable<List<String>>(){

			@Override
			public List<String> call() throws Exception {
				return readFile("src/concurrent/test.txt");
			}
			
		};
		ListenableFuture<List<String>> future = executor.submit(callable);
		Futures.addCallback(future, new FutureCallback<List<String>>() {
			
			public void onFailure(Throwable thrown) {
				System.out.println("error");
			}

			@Override
			public void onSuccess(List<String> result) {
				for(String line: result) {
					System.out.println(line);
				}
				
			}
		});
		
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

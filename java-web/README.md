
1. https://github.com/FS1360472174/javaweb/blob/master/java-web/src/util/ArrayListTest.java

    解决Gson gson 将List<String> lz 转换为Json的问题

    问题:http://bbs.csdn.net/topics/391991733

2. https://github.com/FS1360472174/javaweb/blob/master/java-web/src/util/JsonStringtoListBean.java

    解决org.codehaus.jackson 将jsonstring转换为List<Bean> 的问题。

    问题:http://bbs.csdn.net/topics/391994638
    
3. https://github.com/FS1360472174/javaweb/blob/master/java-web/src/timertask/CrontriggerTask.java

    解决定时任务

    问题:http://bbs.csdn.net/topics/391995592
    
    使用quartz框架，http://www.quartz-scheduler.org/


	**java 多线程**
	
	java多线程框架Executor /java-web/src/concurrent/TestExecutor.java
	
	**注：**
	[blog详解](http://blog.csdn.net/fs1360472174/article/details/60467437)
	Executor将任务的提交与执行过程分开，直接使用Runnable表示任务。future获取返回值
	
		ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorService executor = Executors.newSingleThreadExecutor();
			Callable<List<String>> callable;
			callable = new Callable<List<String>>(){
	
				@Override
				public List<String> call() throws Exception {
					return readFile("src/concurrent/test.txt");
				}
				
			};
			Future<List<String>> future = executor.submit(callable);
			List<String> lines = future.get(5, TimeUnit.SECONDS);
	synchronize关键字
	
	**google Guava包**
	
	JDK中Future通过异步的方式计算返回结果，当并发操作时，在任务结束或者没结束的时候都会返回一个结果。Future是异步操作的一个引用句柄，确保在服务执行返回一个结果。
	
	ListenableFuture允许注册回调方法。可以一个小小的改进会支持更多的操作。
	对应JDK中的 ExecutorService.submit(Callable) 提交多线程异步运算的方式，Guava 提供了ListeningExecutorService 接口, 该接口返回 ListenableFuture 而相应的 ExecutorService 返回普通的 Future。将 ExecutorService 转为 ListeningExecutorService，可以使用MoreExecutors.listeningDecorator(ExecutorService)进行装饰。
	
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		ListenableFuture<Explosion> explosion = service.submit(new Callable<Explosion>() {
		  public Explosion call() {
		    return pushBigRedButton();
		  }
		});
		Futures.addCallback(explosion, new FutureCallback<Explosion>() {
		  // we want this handler to run immediately after we push the big red button!
		  public void onSuccess(Explosion explosion) {
		    walkAwayFrom(explosion);
		  }
		  public void onFailure(Throwable thrown) {
		    battleArchNemesis(); // escaped the explosion!
		  }
		});

4. java 内存泄漏

https://github.com/FS1360472174/javaweb/blob/master/java-web/src/memory/MemoryLeak.java

5. http request

http://square.github.io/okhttp/

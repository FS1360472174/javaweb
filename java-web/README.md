
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


4. java 多线程

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

指令重排序导致了线程的
线程之间如何通信
两种：

## 共享内存
隐式进行的，必须显式指定某个方法或某段代码需要在线程之间互斥执行

monitor翻译成管程

## 消息传递。

## 同步动作
lock, unlock, 读写volatile

显示执行的，

线程之间如何同步
同步是指程序 中用 于 控制 不同 线程 间 操作 发生 相对 顺序 的 机制。


方腾飞; 魏鹏; 程晓明. Java并发编程的艺术 (Java核心技术系列) (Kindle 位置 686-688). 机械工业出版社. Kindle 版本.

java的内存模型


- volatile


父子线程


程序是否正在同步了

- 多个线程访问共享变量，且至少有一个线程在写

- happens-before

必要而非充分的约束集，也就是说java 内存模型运行的happends-before也运行，但是反过来这不一样。
happens-before更加宽松

- 同一个线程的每个操作happens-before于该线程中的任意后续操作。
- 监视器锁规则对一个锁的解锁happens-before于随后对这个锁的加锁
-volatile变量规则： 对一个volatile 域 的 写， happens- before 于 任意 后续 对 这个 volatile 域 的 读。

happens-before 不一定是顺序的，但是是前一个操作对后一个操作是可见的


问题：

final 类 不可再构造了，但是还是可以修改字段，这个如何理解

当对象 o 的构造器中有写 final 字段 f 的操作，在
退出构造器后，不管是异常还是正常退出，对象 o 的 f 上都会发生冻结动作


常用并发工具类
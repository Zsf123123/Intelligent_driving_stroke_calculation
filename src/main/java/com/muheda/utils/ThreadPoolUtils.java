package com.muheda.utils;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * 线程池，可自动释放，自行扩容，最大扩容容量200线程，线程池的各个参数
 *  * 要根据任务数量，任务执行时间，任务持续时间，服务器状态设置参数，
 *  * 用法MyFixedThreadPool.getThreadPool().execute(Runnable);
 */
public class ThreadPoolUtils {
	/**
	 * 核心线程数量，未达到这个线程数的时候会创建线程，此处设置为1表示在没有任务执行的时候只有1个线程，
	 * 有任务直接进入任务队列执行线程，执行完毕线程等待超时直接关闭线程，防止多余的线程存在，浪费性能，
	 * 如果线程队列超过数量，则自动扩容，扩容上限为设置的最大线程数量，
	 */
	private static int CORE_POOL_SIZE = 10;
	
	/**
	 * 最大线程数量（包括线程队列里面的线程数量），达到这个线程数的时候会执行饱和策略
	 */
	private static int MAX_POOL_SIZE = 20;
	
	/**
	 * 线程空闲时间，超过这个时间，线程自动退出
	 */
	private static long KEEP_ALIVE_TIME = 30;

	/**
	 * 线程任务队列
	 * 1.ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
	 * 2.LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue
	 * 3.SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue
	 * 4.PriorityBlockingQueue：一个具有优先级的无限阻塞队列。
	 */
	private static ArrayBlockingQueue<Runnable> BLOCKING_QUEUE = new ArrayBlockingQueue<Runnable>(100);
	
	/**
	 * 线程池饱和策略
	 * 1.AbortPolicy：直接抛出异常。
	 * 2.CallerRunsPolicy：只用调用者所在线程来运行任务。
	 * 3.DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
	 * 4.DiscardPolicy：不处理，丢弃掉。
	 * 5.也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。
	 */
	private static RejectedExecutionHandler EXECUTION_HANDLER = new AbortPolicy();
	
	/**
	 * 新建线程池,此处参数为取中间情况参数
	 * @return 线程池对象
	 */
	private final static ExecutorService SELF_THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
			BLOCKING_QUEUE, new NamedThreadUtils("THREAD_WORKER"), EXECUTION_HANDLER);
	
	/**
	 * 新建单一线程的线程池，参数为本项目适用环境下的参数
	 * @return 线程池对象
	 */
	public final static ExecutorService getSingleThreadPool(String threadName) {
		return new ThreadPoolExecutor(1, 1, Long.MAX_VALUE, TimeUnit.HOURS,
				new LinkedBlockingQueue<Runnable>(), new NamedThreadUtils(threadName));
	}
	
	private static ExecutorService logThreadPool = null;

	/**
	 * 取得自定义核心线程数的线程池对象
	 * @return 线程池对象
	 */
	public static ExecutorService  getThreadPool(){
		return SELF_THREAD_POOL; 
	}
	
	/**
	 * 获取线程池中在使用的线程数量
	 * @return 线程池数量
	 */
	public static int  getActiveThreadCount(){
		return ((ThreadPoolExecutor)SELF_THREAD_POOL).getActiveCount(); 
	}
	
	/**
	 * 获取线程池中闲置线程数量
	 * @return 线程池数量
	 */
	public static int  getRestThreadCount(){
		return MAX_POOL_SIZE-((ThreadPoolExecutor)SELF_THREAD_POOL).getActiveCount(); 
	}

	/**
	 * 新建固定大小线程的线程池，初始化threadLenght个线程
	 * @return 线程池对象
	 */
	public final static ExecutorService getOwnThreadPool(int coreSize,int maxSize,String threadName) {
		return new ThreadPoolExecutor(coreSize,maxSize, Long.MAX_VALUE, TimeUnit.HOURS,
				BLOCKING_QUEUE, new NamedThreadUtils(threadName));
	}

}
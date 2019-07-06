package com.muheda.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 命名工厂
 */
public class NamedThreadUtils implements ThreadFactory {

	private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final ThreadGroup group;
	private final String namePrefix;
	private final boolean isDaemon;

	public NamedThreadUtils() {
		this("pool");
	}

	public NamedThreadUtils(String name) {
		this(name, false);
	}

	public NamedThreadUtils(String preffix, boolean daemon) {
		SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		namePrefix = preffix + "-" + POOL_NUMBER.getAndIncrement() + "-thread-";
		isDaemon = daemon;
	}

	/**
	 * 设置线程组和线程名称，堆栈大小，0表示无限制
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
		t.setDaemon(isDaemon);
		if (t.getPriority() != Thread.NORM_PRIORITY) {
			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}
}

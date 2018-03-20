package org.cc11001100.grab.base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.cc11001100.cleaner.WatchDog;
import org.cc11001100.entity.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import static org.cc11001100.utils.DateUtil.nowSecond;
import static org.cc11001100.utils.SleepUtil.sleepSeconds;

/**
 * @author CC11001100
 */
@Component
public class GrabWorkerManager {

	private static Logger logger = LogManager.getLogger(GrabWorkerManager.class);

	@Autowired
	private WatchDog watchDog;

	/**
	 * 当前注册的GrabWorker
	 */
	private PriorityBlockingQueue<Task> taskQueue = new PriorityBlockingQueue<>();

	/**
	 * 添加新的grab
	 *
	 * @param grabWorker
	 * @return
	 */
	public boolean register(GrabWorker grabWorker) {
		return taskQueue.offer(new Task(grabWorker));
	}

	public void run() {
		while (!taskQueue.isEmpty()) {

			Task t = taskQueue.peek();
			long needSleepSeconds = t.nextInvoke - nowSecond();
			if (needSleepSeconds > 0) {
				sleepSeconds(needSleepSeconds);
			}

			t = taskQueue.poll();
			t.nextInvoke = nowSecond() + t.grabWorker.getInvokeIntervalSeconds();
			taskQueue.offer(t);
			new Thread(t).start();

		}
	}

	public class Task implements Comparable<Task>, Runnable {

		String name;
		Long nextInvoke;
		GrabWorker grabWorker;

		public Task(GrabWorker g) {
			nextInvoke = nowSecond() + grabWorker.getInvokeIntervalSeconds();
			name = g.getName();
			grabWorker = g;
		}

		@Override
		public int compareTo(Task o) {
			return (int) (nextInvoke - o.nextInvoke);
		}

		@Override
		public void run() {
			List<Proxy> proxyList = grabWorker.grab();
			watchDog.eat(name, proxyList);
		}
	}

}

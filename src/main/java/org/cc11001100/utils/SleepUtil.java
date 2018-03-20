package org.cc11001100.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author CC11001100
 */
public class SleepUtil {

	public static void sleep(TimeUnit timeUnit, long howLong){
		try {
			timeUnit.sleep(howLong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleepSeconds(long howLong){
		sleep(TimeUnit.SECONDS, howLong);
	}

}

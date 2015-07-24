package com.landerer.hystrix.democlient;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class DemoClient {
	public static void main(String[] args) {
		final Timer timer = new Timer(true);
		TimerTask task = new UuidCommandTimerRask();
		timer.schedule(task , 0L, 1000L);
		while(true) {
			// just run
		}
	}
	
	private static final class UuidCommandTimerRask extends TimerTask {
		@Override
		public void run() {
			System.out.println(Instant.now() + ": " + new UuidCommand().execute());
		}
	}
}

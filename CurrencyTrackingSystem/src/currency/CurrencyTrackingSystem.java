package currency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CurrencyTrackingSystem {
	public static void main(String[] args) {
		// 4. You will periodically check the rates, consider using a schedular.
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(new CurrencyTrackingJob(), 0, 60,
				TimeUnit.SECONDS);
	}
}

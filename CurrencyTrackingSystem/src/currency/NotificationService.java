package currency;

import java.util.Date;

public class NotificationService {
	public static void onAlert(Date time, String symbol, double target, double current) {
		// 4. For notification, we will assume that there is a system that will
		// be called to notify users. You
		// only display it on the screen if target has been reached.

		System.out.println("------ " + time + " ------");
		System.out.println("SYMBOL : " + symbol);
		System.out.println("TARGET : " + target);
		System.out.println("CURRENT: " + current);

		// TODO notify all the consumers
	}
}

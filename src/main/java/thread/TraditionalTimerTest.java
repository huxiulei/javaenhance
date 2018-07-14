package thread;

import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("test~~~");
			}
		},1000);
	}

	
	
	Thread t = new Thread();
}

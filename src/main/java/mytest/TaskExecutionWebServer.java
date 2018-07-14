package mytest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutionWebServer {
	private static final int nThread = 10;
	private static final Executor exec = Executors.newFixedThreadPool(nThread);
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(1234);
		while(true){
			final Socket connection = ss.accept();
			Runnable task = new Runnable() {
				@Override
				public void run() {
					handleRequest(connection);
				}

				private void handleRequest(Socket connection){
					try {
						InputStream is = connection.getInputStream();
						byte[] by = new byte[1024];
						int len = 0;
						while(-1 != (len = is.read() )){
							
						}
						System.out.println("--> " + is.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(task);
		}
	}
}

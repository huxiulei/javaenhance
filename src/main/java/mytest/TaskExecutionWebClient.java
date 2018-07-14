package mytest;

import java.net.Socket;

public class TaskExecutionWebClient {

	public static void main(String[] args) throws Exception{
		Socket scoket = new Socket("127.0.0.1",1234);
		
		System.out.println("the connection is successful");
		scoket.close();
	}

}

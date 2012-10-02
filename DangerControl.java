/*
Ethan Eldridge
KD Tree / Listening Object Interface for the danger zone application.
Provides a wrapper for an interface to the all important Danger Zone K-d(2) Tree
*/
import java.io.*;
import java.net.*;
import java.util.Timer;

class DangerControl{
	
	public static void main(String argv[]) throws Exception
	{
		//5480 For Listening, 5481 to send back out
		//ServerSocket listen = new ServerSocket(5480);
		//Time out for the server while I test so I don't have to kill it
		//10 Seconds should be ok for now
		long timeout = System.currentTimeMillis() + 1000*10;
		//Socket to send back the data
		//Socket response = new Socket("127.0.0.1",5481);

		while(System.currentTimeMillis() < timeout){

		}

		System.out.print("done");


	}

}
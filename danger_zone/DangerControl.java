package danger_zone;
import java.io.*;
import java.net.*;
import java.util.Timer;


/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.1
*@since 2012-10-5
*
* KD Tree / Listening Object Interface for the danger zone application.
* Provides a wrapper for an interface to the all important Danger Zone K-d(2) Tree
*/
public class DangerControl{
	/**
	*Socket to accept incoming queries to the Danger Control interface, listens on port 5480
	*/
	ServerSocket listen = null;

	public static void main(String argv[]) throws Exception
	{
		//5480 For Listening, 5481 to send back out
		listen = new ServerSocket(5480);

		//Time out for the server while I test so I don't have to kill it
		//10 Seconds should be ok for now
		long timeout = System.currentTimeMillis() + 1000*5;

		//Socket to send back the data php must be listening to this or we will get an exception
		Socket response = null;
		
		Socket incoming = null;
		while(System.currentTimeMillis() < timeout){
			//Attempt to listen for a client
			try{
				incoming = listen.accept();
				System.out.println("Recieved");
			}catch(IOException e){
				System.out.println("Error");
				continue;
			}

			//Read incoming messages with autoflushing printwriter
			PrintWriter out = null;
			BufferedReader info = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			String msg;

			float[] geoCmd = null;
			while((msg = info.readLine()) != null){
				//Parse information from the message:
				geoCmd = CommandParser.parseGeoCommand(msg);
				if(geoCmd != null){
					//We have recieved the Coordinates
					System.out.println("OH HEY FUCKER I GOT THIS SHIT " + geoCmd[0] + " " + geoCmd[1]);
				}

			}
			//Close the incoming stream
			incoming.close();


		}
		listen.close();
		System.out.print("done");



		


	}

}
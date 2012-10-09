package danger_zone;
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.Stack;
import java.util.Map;
//http://code.google.com/p/json-simple/
import org.json.simple.JSONObject;



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
	ServerSocket clientListener = null;
	/**
	*Timeout for the DangerControl program's clientListener, this must be set in integer form (Seconds)
	*/
	int int_timeout = 5;
	/**
	*Timeout for the DangerControl program itself, this is used during debugging and will probably be removed in release implementations
	*/
	long long_timeout = System.currentTimeMillis() + 1000*int_timeout;
	/**
	*Socket that will hold the incoming traffic coming from the clientListener
	*/
	Socket incoming = null;
	/**
	*Data Structure to hold the dangerZones from the database. 
	*/
	DangerNode dangerZones = null;

	/**
	*The url that the output of the commands will be send to
	*/
	public static final String URL_TO_SEND_TO = "http://localhost/Server/Java/danger_zone/test.php";

	/**
	*Creates an instance of the DangerControl class.
	*/
	public DangerControl() throws Exception{
		//5480 For Listening, 5481 to send back out
		clientListener = new ServerSocket(5480);
		clientListener.setSoTimeout(int_timeout);
		//Construct the Tree to hold the danger zones (note this should be replaced by a tree building from sql function)
		this.createTree();


	}

	/**
	*Creates and constructs the tree stored in dangerZones from the database
	*/
	public void createTree(){
		dangerZones = new DangerNode(9,9,1);
		dangerZones.addNode(new DangerNode(7,2,4));
		dangerZones.addNode(new DangerNode(12,12,5));
		dangerZones.addNode(new DangerNode(15,13,6));
		this.dangerZones = DangerNode.reBalanceTree(dangerZones);
	}

	/**
	*Run this instance of DangerControl
	*/
	public void run() throws Exception{
		//Fun Fact, Java supports labels. I didn't know Java liked Spaghetti
		Running:
		while(System.currentTimeMillis() < long_timeout){
			//If we can't listen then just loop around
			if(!this.listen()){ continue Running; }
			System.out.println("I can read");
				this.read();
		}
		//Cleanup
		clientListener.close();
	}

	/**
	*Opens the ServerSocket clientListener to accept incoming data
	*@return Returns true if the socket is able to listen, false if otherwise.
	*/
	public boolean listen(){
		try{
			incoming = clientListener.accept();
			return true;
		}catch(IOException e){
			return false;
		}	
	}

	/**
	*Readings incoming messages and calls the dispatcher to send responses
	*/
	public void read() throws Exception{
		//Read incoming messages with autoflushing printwriter
		BufferedReader info = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
		DataOutputStream responseStream = new DataOutputStream(incoming.getOutputStream());
		String msg;

		
		while((msg = info.readLine()) != null){
			System.out.println("yay");
			System.out.println(msg);
			//We should use some type of switch or something to figure out what function to call from the command parser
			if(msg.indexOf(CommandParser.CMD_LON) != -1 && msg.indexOf(CommandParser.CMD_LAT) != -1){
				//Handle the command and respond to it
				this.dispatchResponse(this.handleGeoCommand(msg),responseStream);
				System.out.print("OUT !");
				incoming.shutdownOutput();
				
			}
			//We can extend right here to implement more commands
			System.out.println("out");
		}
		//Close the incoming stream
		System.out.println("ALL OUT");
		incoming.close();
		info.close();
	}

	/**
	*Dispatches a response back to the client of the nearest neighbors to the point they asked for.
	*@param neighbors The nearest zones returned by the search for the tree
	*/
	public void dispatchResponse(Stack<DangerNode> neighbors,DataOutputStream responseStream) throws Exception{
		//Lets send the response as a json array of the nodes
		JSONObject response = new JSONObject();
		response.put("neighbors", neighbors);
		System.out.println(response);
		responseStream.writeBytes(response.toString());
		// responseStream.writeBytes("+\n");
		responseStream.flush();
		//responseStream.close();

		System.out.println("Message Dispatched");
	
	}

	/**
	*Parses a command in the GEO COMMAND format, will return the results of searching the tree for the specified coordinate and number of near zones
	*@param geoCommand String command in the GEO COMMAND format;
	*@return returns the results of searching the tree for the coordinate.
	*/
	public Stack<DangerNode> handleGeoCommand(String geoCommand){
		float[] geoCmd = null;
		//Parse information from the message:
		geoCmd = CommandParser.parseGeoCommand(geoCommand);
		if(geoCmd != null){
			//We have recieved the Coordinates and should play with the tree
			//System.out.println("Searching tree for " + geoCmd[0] + " " + geoCmd[1]);
			if(dangerZones == null){
				System.out.println("No Tree Initailized");
				return null;
			}
			return dangerZones.nearestNeighbor(new float[]{geoCmd[0],geoCmd[1]},(int)geoCmd[2]);

		}
		return null;
}

	public static void main(String argv[]) throws Exception
	{
		DangerControl control = new DangerControl();		
		control.run();

	}

}
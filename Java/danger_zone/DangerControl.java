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
public abstract class DangerControl{
	/**
	*Debug variable, if specified as true, output messages will be displayed. 
	*/
	static boolean debugOn = true;
	/**
	*Socket to accept incoming queries to the Danger Control interface, listens on port 5480
	*/
	ServerSocket clientListener = null;
	/**
	*Timeout for the DangerControl program's clientListener, this must be set in integer form (Seconds)
	*/
	static int int_timeout = 5;
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
	*Port number to communicate to the client with
	*/
	static int port_number = 5480;
	/**
	*Classifer interface to allow for feed back to the classifier from incoming command messages.
	*/
	BayesTrainer classifier = new BayesTrainer();

	/**
	*Variable to control continous listening by the server instead of a time out.
	*/
	static boolean continous = false;

	public abstract void trainBayes(String password,boolean debugOn);
	public abstract void setRootNode(DangerNode dn);
	public abstract void createTestTree();
	public abstract void run() throws Exception;
	public abstract void run(boolean continous) throws Exception;
	public abstract void read(DatagramPacket request) throws Exception;
	public abstract void read() throws Exception;
	public abstract void handleLine(String line,DatagramPacket request);
	public abstract void dispatchResponse(Stack<DangerNode> neighbors,DatagramPacket responseStream) throws Exception;
	public abstract Stack<DangerNode> handleGeoCommand(String geoCommand);
	public abstract void dispatchResponse(Stack<DangerNode> neighbors,DataOutputStream responseStream) throws Exception;
	public abstract void handleLine(String line,DataOutputStream request);
}
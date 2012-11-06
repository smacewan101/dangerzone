package danger_zone;
import java.io.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-11-05
*
* The driver program for all the Java classes. This class will be ran once from the command line with some arguments and will
* handle all incoming requests to the Java side of the server.
* 
* 
*/
public class JavaDriver{

	final DangerControlUDP dcUDP;

	public JavaDriver(String password, String username){
		//Initializes the Naive Bayers Runner and UDP Danger Control. 
		//Let the danger control class run both the tree and the bayes trainer.
		//The danger control should take commands involving both geo neighbors and for 
		//bayes to be trained via command line.

		//Initalize Control
		dcUDP = new DangerControlUDP()
		//Create the tree
		ArrayList<DangerNode> nodes =  DangerNode.fetchDangers(username,password);
		dcUDP.setRootNode(DangerNode.makeTree(nodes));
		//


	}

	public void run(){

	}

	public static void main(String[] args) {
		//Arguments:
		// -p passwordbetweenqoutes
		// -n portnumber
		// Must have spaces between arguments 
		String password = "";
		String username = "";
		int portnumber = DangerControlUDP.port_number;

		//Parse out the arguments from the command line
		for(int i = 0; i < args.length; i++){
			if(args[i].trim().equals("-p")){
				//Get the password
				try{
					password = args[i+1];	
				}catch(java.lang.IndexOutOfBoundsException out){
					System.out.println("Password expected if -p specifier is given.");
					return;
				}
			}else if(args[i].trim().equals("-n")){
				//Get the port number
				try{
					portnumber = Integer.parseInt(args[i+1]);
				}catch(java.lang.IndexOutOfBoundsException out){
					System.out.println("Port number expected if -n specifier is given.");
					return;
				}catch(java.lang.NumberFormatException nfe){
					System.out.println("Must specify an integer value for the port number");
					return;
				}
			}else if(args[i].trim().equals("-u")){
				//Get the user for the database
				try{
					username = args[i+1];
				}catch(java.lang.IndexOutOfBoundsException out){
					System.out.println("Username expected if -u specifier is given");
					return;
				}
			}else if(args[i]).trim().equals("-help")){
				//Print out the help for this.
				System.out.println("usage: java JavaDriver -p password -u username [-n integer port number]");
			}
		}

		//Make sure we have the least amount of arguments we need:
		if(password.equals("") || username.equals("")){
			System.out.println("Required arguments user and password. Please see the help for this program.");
		}

		//Set the port number
		DangerControlUDP.port_number = portnumber;

		//Construct the Driver given the arguments and run the program
		JavaDriver jd = new JavaDriver(password,username);
		jd.run();
	}
}
package danger_zone;
import java.util.HashMap;


/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-7
*
* The CommandParser is a static class that contains the constants CMD_LON and CMD_LAT which correspond to the
*format of the geo command to be interpreted. 
*GEO FORMAT: LON XXX.XXXXXX LAT XXX.XXXXXXX where X's are numeric and to any precision.
*GEO FORMAT: LON XXX.XXXXXX LAT XXX.XXXXXXX NUM XXX where NUM specifies up to how many nodes you would like returned.
*KILL CODE: KILLSERVER0x0000
*CLASSIFY DET  TEXT   Classifier will classify the text and return which category it is in, D or S for danger or safe.
*TRAIN [D|S] TEXT classify given text as dangerous or safe, D will specify the text as Dangerouns and S as Safe.
*/
public class CommandParser{
	/**
	*Constant for the longitude specifier in a GEO command
	*/
	static final String CMD_LON = "LON";
	/**
	*Constant for the latitude specifier in a GEO command
	*/
	static final String CMD_LAT = "LAT";
	/**
	*Constant for the number of nodes a query would like returned
	*/
	static final String CMD_COUNT = "NUM";
	/**
	*Constant for the kill code to the server if the server is running in continous mode. This does not kill the server if the server is running on a time out.
	*/
	static final String KILL = "KILLSERVER0x0000";
	/**
	*Constant for the command to classify
	*/
	static final String CMD_CLASSIFY = "CLASSIFY";
	/**
	*Constant for the classify option to add the class of the text as specified
	*/
	static final String CMD_TRAIN = "TRAIN";
	/**
	*Constant for the classify option ADD to have texted be add as dangerous text
	*/
	static final String OPT_DANGER = "D";
	/**
	*Constant for the classify option ADD to have text be added as safe text.
	*/
	static final String OPT_SAFE = "S";
	/**
	*Constant for the classify option to add some text as a category
	*/
	static final String OPT_DET = "DET";
	/**
	*Constant for the determine class of text option in the CLASSIFY command.
	*/



	public static void main(String argv[]){
		String cmd = "LON 91.12 LAT 40.78";
		float[] result = CommandParser.parseGeoCommand(cmd);
		System.out.println(result[0] + "\n" + result[1]);
		//IF they are really floats then they'll add
		System.out.println(result[0] + result[1]);
	}


	

	/**
	*If the command string is of the form LON XXX.XXXX LAT XXX.XXXX then this function will remove the longitude and latitude from the string and return them in a float array. The returning array will have longitude first in the array, and latitude second. 
	*@param command The String command to be parsed for geo commands
	*@return A float array of size 2 with longitude and latitude stored in 0 and 1 indices. Note that if the command being parsed is not in the Geo command format then one or more of the resulting elements will be null. This should be checked by the calling program.
	*/
	public static float[] parseGeoCommand(String command){
		//split the command by its spaces and look at the parts to find longitude and latitude
		String[] parts;
		parts = command.split(" ");
		//Associativity is a nice thing
		HashMap<String,Float> cmds = new HashMap<String,Float>(parts.length);
		for(int i=0; i < parts.length; i++){
			if(parts[i].equals(CMD_LON)){
				//Bounds check on i+1 is not done because this function expects a well formed Geo Command
				cmds.put(CMD_LON,Float.parseFloat(parts[i+1]));
				continue;
			}else if(parts[i].equals(CMD_LAT)){
				//No bounds check because we should be sure of a well formed Geo Command here
				cmds.put(CMD_LAT,Float.parseFloat(parts[i+1]));
				continue;
			}else if(parts[i].equals(CMD_COUNT)){
				cmds.put(CMD_COUNT,Float.parseFloat(parts[i+1]));
				continue;
			}
		}
		//Now we 'hopefully' have what we'd like, in which case we should return it
		float [] lonlatTuple = new float[3];

		//Check for nulls
		if(!cmds.containsKey(CMD_LON) || !cmds.containsKey(CMD_LAT)){
			return null;
		}

		//cmds.get will return null if key does not exist
		lonlatTuple[0] = cmds.get(CMD_LON);
		lonlatTuple[1] = cmds.get(CMD_LAT);
		lonlatTuple[2] = cmds.get(CMD_COUNT);
		
		return lonlatTuple;
	}

	/**
	*Parses a classifying command and returns the text to be classified. Returns an empty string upon failing to read the text
	*@param command The command to be parsed as a clasifier command.
	*@return Returns the text to be classified on success or an empty string on failure.
	*/
	public static String parseClassifyCommand(String command){
		String [] parts;
		parts = command.split(" ");
		if(parts[0] == CMD_CLASSIFY){
			if(parts.length > 1){
				//Run the string together
				String text ="";
				for(int i = 1; i < parts.length; i++){
					text += parts[i];
				}
				return text;
			}
		}
		return "";
	}

	/**
	*Parse a training comand into the form of CLASS TEXT... and returns it in a string array with the class in the first element and the text in the other. If the class or text is invalid both elements will be empty strings.
	*@param command The command to be parsed as a training command
	*@return A 2 element array with class of the text in the first element and the text in the second. If invalid elements
	*/
	public static String[] parseTrainCommand(String command){
		String [] parts;
		String [] elements = new String[]{"",""};
		parts =command.split(" ");
		if(parts[0] == CMD_TRAIN){
			if(parts.length > 1){
				if(parts[1]==OPT_DANGER || parts[1]==OPT_SAFE){
					String build = "";
					for(int i = 2; i < parts.length; i++){
						build += parts[i];
					}
					elements[0] = parts[1];
					elements[1] = build;
				}
			}
		}
		return elements;
	}

}
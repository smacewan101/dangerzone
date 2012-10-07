package danger_zone;
import java.util.regex;
import java.util.HashMap;


public class CommandParser{
	//Test function
	static final String CMD_LON = "LON";
	static final String CMD_LAT = "LAT";

	public static void main(String argv[]){

	}

	public float[] parseCommand(String command){
		//split the command by its spaces and look at the parts to find longitude and latitude
		String[] parts;
		part = command.split(" ");
		//Associativity is a nice thing
		HashMap cmds = new HashMap(part.length);
		for(int i=0; i < parts.length; i++){
			if(part[i].equals(CMD_LON)){
				cmds.put(CMD_LON,part[i+1]);
				continue;
			}else if(part[i].equals(CMD_LAT)){
				cmds.put(CMD_LAT,part[i+1]);
				continue;
			}
		}
		//Now we 'hopefully' have what we'd like, in which case we should return it
		float [] lonlatTuple = new float[2];
		//cmds.get will return null if key does not exist
		lonlatTuple[0] = cmds.get(CMD_LON);
		lonlatTuple[1] = cmds.get(CMD_LAT);
		
		

	}
}
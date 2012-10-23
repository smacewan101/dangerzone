package danger_zone;
import java.io.*;
import java.sql.Timestamp;


/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-22
*
* Training tweet with a marked bit of dangerous or not. Used to train the Naive Bayes algorithm.
*/
public class Training_Tweet extends Tweet{
	private int category;

	public Training_Tweet(int id, String text, int cat){
		Super(id,text);
		category = cat;
	}

}
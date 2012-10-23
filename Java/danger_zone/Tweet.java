package danger_zone;
import java.io.*;
import java.sql.Timestamp;


/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-22
*
* Class providing an interface to the SQL database for training purposes. 
*/
public class Tweet{
	/**
	*Unique tweet id for this tweet.
	*/
	private int tweet_id = -1;
	
	/**
	*Id of the original tweet this treat may have been re-tweeted from
	*/
	private int parent_id = -1;

	/**
	*Latitude of the geo coordinate of this tweet
	*/
	private float latitiude = -1;

	/**
	*Longitude of the geo coordinate of this tweet
	*/
	private float longitude = -1;

	/**
	*Text message of the tweet itself.
	*/
	private String text = "";

	/**
	*The time this tweet was created
	*/
	private java.sql.Timestamp timestamp;

	/**
	*The search string used by the MacEwan Twitter API that found this tweet
	*/
	private String search_str = "";

	/**
	*Constructs a tweet
	*/
	public Tweet(){

	}


}

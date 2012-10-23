package danger_zone;
import java.io.*;
import java.sql.Timestamp;


/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-22
*
* Data class to hold and provide methods to access Tweets
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
	private float latitude = -1;

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
	*Constructs a tweet with the most basic of information and defaults the rest.
	*@param id Unique Tweet Id
	*@param text The text of the tweet itself
	*/
	public Tweet(int id,String text){
		this(id,text,-1,-1,-1,"",new java.sql.Timestamp(System.currentTimeMillis()));
	}

	/**
	*Constructs a tweet with default information besides the id, text, latitude and longitude
	*@param id Unique Tweet id
	*@param text The text of the tweet itself
	*@param latitude The latitude coordinate of the tweet
	*@param longitude The longitude coordinate of the tweet
	*/
	public Tweet(int id,String text, float latitude, float longitude ){
		this(id,text,latitude,longitude,-1,"",new java.sql.Timestamp(System.currentTimeMillis()));
	}

	/**
	*Constructs a basic geo-spacial time stamped tweet
	*@param id Unique Tweet id
	*@param text The text of the tweet itself
	*@param latitude The latitude coordinate of the tweet
	*@param longitude The longitude coordinate of the tweet
	*@param time Timestamp of the tweet.
	*/
	public Tweet(int id, String text, float latitude, float longitude, java.sql.Timestamp time){
		this(id,text,latitude,longitude,-1,"",time);
	}

	/**
	*Constructs a tweet with all the information available
	*@param id Unique Tweet id
	*@param text The text of the tweet itself
	*@param latitude The latitude coordinate of the tweet
	*@param longitude The longitude coordinate of the tweet
	*@param parent_id The id of the tweet this tweet was re-tweeted from
	*@param search The search string that resulted in finding this tweet
	*@param time Timestamp of the tweet.
	*/
	public Tweet(int id, String text, float latitude, float longitude, int parent_id, String search, java.sql.Timestamp time){
		this.tweet_id = id;
		this.text = text;
		this.latitude = latitude;
		this.longitude = longitude;
		this.parent_id = parent_id;
		this.search_str = search;
		this.timestamp = time;
	}

	public String getTweetText(){
		return this.text;
	}

}

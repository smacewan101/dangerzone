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

	/**
	*Constructs a tweet with the most basic of information and defaults the rest.
	*@param id Unique Tweet Id
	*@param text The text of the tweet itself
	*@param cat Category that this tweet belongs in
	*/
	public Training_Tweet(int id, String text, int cat){
		super(id,text);
		category = cat;
	}

	/**
	*Constructs a tweet with default information besides the id, text, latitude and longitude
	*@param id Unique Tweet id
	*@param text The text of the tweet itself
	*@param latitude The latitude coordinate of the tweet
	*@param longitude The longitude coordinate of the tweet
	*@param cat Category that this tweet belongs in
	*/
	public Training_Tweet(int id, String text, float latitude, float longtiude, int cat){
		super(id,text,latitude,longtiude);
		category = cat;
	}

	/**
	*Constructs a basic geo-spacial time stamped tweet
	*@param id Unique Tweet id
	*@param text The text of the tweet itself
	*@param latitude The latitude coordinate of the tweet
	*@param longitude The longitude coordinate of the tweet
	*@param time Timestamp of the tweet.
	*@param cat Category that this Tweet belongs in 
	*/
	public Training_Tweet(int id, String text, float latitude, float longitude, java.sql.Timestamp time, int cat){
		super(id,text,latitude,longitude,-1,"",time);
		category = cat;

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
	*@param cat Category that this Tweet belongs in 
	*/
	public Training_Tweet(int id, String text, float latitude, float longitude, int parent_id, String search, java.sql.Timestamp time, int cat){
		super(id,text,latitude,longitude,parent_id,search,time);
		category = cat;
	}




}
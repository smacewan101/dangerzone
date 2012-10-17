package danger_zone;
import java.util.HashMap;
import java.io.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-17
*
* Class to change tweets into Naive Bayes inputs. 
*/
public class Lemmatizer{
	/**
	*
	*/
	public String [] outputs = null;

	/**
	*
	*/
	private String [] stopWords = StopWords.strippers;
	


	public Lemmatizer(){}

	/**
	*Strips the tweet of all common words that are unimportant to the word.
	*/
	public final void stripTweet(String tweet){
		//Split the tweet
		String [] splitTweet = tweet.split(" ");
		for(String t : splitTweet){
			for(String w : stopWords){
				if(t.compareToIgnoreCase(w) == 0){
					int pos = tweet.indexOf(t);
					tweet = tweet.substring(0,pos).concat(tweet.substring(pos+t.length()));
				}
			}
		}
		this.outputs = tweet.split(" ");
	}

	public final void stem(){
		for(int i = 0; i < outputs.length; i++){
			this.outputs[i] = new Lemmatization(outputs[i]).toString();
		}
	}

	public String toString(){
		String s = "";
		for(String o : outputs){
			s = s.concat(o);
		}
		return s;
	}

	public static void main(String argv[]) throws Exception
	{
		Lemmatizer lem = new Lemmatizer();
		lem.stripTweet("Hey there is a fire near me! I'm running away from it!");
		lem.stem();
		System.out.println(lem);

	}



}
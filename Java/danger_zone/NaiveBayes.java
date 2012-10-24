package danger_zone;
import java.util.HashMap;
import java.io.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-17
*
* Naive Bayes classifier to classify a tweet as a danger or not. 
*/
public class NaiveBayes{
	/**
	*Constant for the category danger
	*/
	public static final int CAT_DANGER = 1;
	/**
	*Constant for the category safe
	*/
	public static final int CAT_SAFE = 0;
	/**
	*Lemmatizer instance to strip and parse tweets into a managable form.
	*/
	private Lemmatizer tweetStripper = new Lemmatizer();
	/**
	*Helper array to make the classifying function easier
	*/
	private static final int [] categories = {CAT_SAFE,CAT_DANGER};

	/**
	*Holds The two categories of the data being classified
	*/
	private HashMap<Integer, HashMap<String,Integer>> category_count = new HashMap<Integer, HashMap<String,Integer>>();

	public NaiveBayes(){
		HashMap<String,Integer> danger = new HashMap<String,Integer>();
		HashMap<String,Integer> safe = new HashMap<String,Integer>();
		category_count.put(CAT_DANGER, danger);
		category_count.put(CAT_SAFE, safe);
	}

	/**
	*Trains the algorithm on the dataset, each category is specified by a constant declared in this class, CAT_SAFE, CAT_DANGER and such.
	*@param category The category to place this tweet into, should be selected from the constants declared in this class.
	*@param tweet The text string of the tweet to be classified
	*/
	public void train(int category, String tweet){


		//Get the words out of the tweet
		String [] parsedTweet = tweetStripper.parseTweet(tweet).split(" ");

		//Associate these words with the category
		for(String pt : parsedTweet){
			if(!category_count.get(category).containsKey(pt)){
				category_count.get(category).put(pt,1);
			}
			int numW = category_count.get(category).get(pt);
			category_count.get(category).put(pt,numW+1);

		}
		//count of pt in cat divided by total count of pt in all categories = probability
	}

	static public int convertBoolToInt(boolean cat){
		if(cat){
			return CAT_DANGER;
		}else{
			return CAT_SAFE;
		}
	}

	public void calculateProbabilities(){
		//Remove all keys from each hash map and do some counting and then put in the scores for everything?
	}

	/**
	*Classifies a given tweet into a category class defined as a constant in this class.
	*@param tweet The text string of the sweet to be classified
	*/
	public int classify(String tweet){
		//Each word is classified indenpendtly, whichever one has the most wins. 
		String [] parsedTweet = tweetStripper.parseTweet(tweet).split(" ");


		//Initalize a way to keep track of the tweet
		HashMap<Integer,Integer> tweetClass = new HashMap<Integer,Integer>();
		for (int cat : categories) {
			tweetClass.put(cat,0);
		}

		//Classify each word
		for(String pt : parsedTweet){
			for(int cat : categories){
				if(category_count.get(cat).containsKey(pt)){
					//Increase count of the word
					category_count.get(cat).put(pt,category_count.get(cat).get(pt)+1);
				}else{
					category_count.get(cat).put(pt,1);
				}
			}
		}

		//get the counts of the strings
		for(String pt : parsedTweet){
			int total = 0;
			for(int cat : categories){
				total = total + category_count.get(cat).get(pt);
			}
			//Now apply this to the tweet class
			for(int cat : categories){
				//store the probabilities for each word
				category_count.get(cat).put(pt,category_count.get(cat).get(pt)/total);
			}
		}

		//Product each string's probability to determine the total probability for the tweet in each category
		for(String pt : parsedTweet){
			for(int cat : categories){
				tweetClass.put(cat,tweetClass.get(cat)*category_count.get(cat).get(pt));
			}
		}

		//Determine the best fitting category
		int bestFit = 0;
		for (int cat : categories) {
			if(tweetClass.get(cat) > bestFit){
				bestFit = cat;
			}
		}

		return bestFit;

	}

	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes();

		nb.train(NaiveBayes.CAT_SAFE,"I love to dance with Kittens");
		nb.train(NaiveBayes.CAT_DANGER,"There are Kittens on Fire and its terrible those poor Kittens");

		switch(nb.classify("Kittens on fire")){
			case NaiveBayes.CAT_DANGER:
				System.out.println("danger");
				break;
			case NaiveBayes.CAT_SAFE:
				System.out.println("safe");
				break;
		}

	}

}
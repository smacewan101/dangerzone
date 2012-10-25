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

	/**
	*Totals of all the counts during training.
	*/
	private HashMap<String, Integer> prior_totals = new HashMap<String, Integer>();

	/**
	*Total count of all things trained on
	*/
	private int total_training_size = 0;

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
			if(prior_totals.containsKey(pt)){
				prior_totals.put(pt, prior_totals.get(pt) + 1);
			}else{
				prior_totals.put(pt,1);
			}
			
			//Increment the total size of the training set.
			total_training_size++;
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
		HashMap<Integer,Float> tweetClass = new HashMap<Integer,Float>();
		for (int cat : categories) {
			tweetClass.put(cat,(float)0);
		}

		//Get how often each word appeared during training (in a category)
		HashMap<Integer, HashMap<String,Float>> priors = new HashMap<Integer, HashMap<String,Float>>();
		for(int cat : categories){
			priors.put(cat, new HashMap<String,Float>());
		}

		//Compute Prior Probabilities
		for(String pt : parsedTweet){
			for(int cat : categories){
				if(category_count.get(cat).containsKey(pt)){
					float prob = category_count.get(cat).get(pt)/((float)prior_totals.get(pt));
					priors.get(cat).put(pt,prob);
				}
			}
		}


		//No multiplication by 0
		float[] probs = new float[categories.length];
		for(int i=0; i < categories.length; i++){
			probs[i] = 1;
		}

		//Compute the probability of the word being in this class.
		for(String pt : parsedTweet){
			for(int cat : categories){
				probs[cat] *= (prior_totals.get(pt) / (float)category_count.get(cat).size());
			}
		}

		//Multiply by the priorer bit
		for(int p =0; p < categories.length; p++){
			 tweetClass.put(p,  probs[p]*category_count.get(p).size()/total_training_size);
		}

		//Determine the best fitting category
		int bestFit = 0;
		for (int cat : categories) {
			System.out.println(tweetClass.get(cat));
			if(tweetClass.get(cat) > bestFit){
				bestFit = cat;
			}
		}

		return bestFit;

	}

	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes();

		nb.train(NaiveBayes.CAT_SAFE,"Moving to Georgia");
		nb.train(NaiveBayes.CAT_SAFE,"Georgia is a lovely place to take a stroll");
		nb.train(NaiveBayes.CAT_DANGER,"Shooting in Georgia");
		nb.train(NaiveBayes.CAT_DANGER,"Three people shot in Georgia");

		switch(nb.classify("Shooting in Georgia")){
			case NaiveBayes.CAT_DANGER:
				System.out.println("danger");
				break;
			case NaiveBayes.CAT_SAFE:
				System.out.println("safe");
				break;
		}

	}

}
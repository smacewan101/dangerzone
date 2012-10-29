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
	*Holds The two categories of the data being classified, and number of times a given String appears
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

	/**
	*Weight on how much each word in a tweet affects the total probabiliy:
	*/
	private float weight = (float)1.00;

	/**
	*Assumed probability of the word being in any given category.
	*/
	private float assumed_prob = (float)0.1;

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

		//Compute the leading term of category probability:
		float[] catProb = new float[categories.length];
		int i = 0;
		for(int cat : categories){
			catProb[i] = category_count.get(cat).size()/(float)total_training_size;
			i++;
		}

		//Compute the number of times a word appears in all categories:
		HashMap<String, Integer> total_all_classes = new HashMap<String, Integer>();
		for(String pt : parsedTweet){
			for(int cat : categories){
				if(!total_all_classes.containsKey(pt)){
					if(category_count.get(cat).containsKey(pt)){
						total_all_classes.put(pt,category_count.get(cat).get(pt));
					}else{
						//What happens if we've never seen the word before?
					}
				}else{
					//If we've seen the word before:
					if(category_count.get(cat).containsKey(pt)){
						int additional = total_all_classes.get(pt) + category_count.get(cat).get(pt);
						total_all_classes.put(pt, additional);
					}else{
						//I'm skeptical if this will ever be execuated, but what happens if we've never seen the word before?
					}
				}
			}
		}

		//Create something to hold the probabilities in
		float[] class_probability = new float[categories.length];
		//Initalize:
		for(int c = 0; c < categories.length; c++){
			class_probability[c] = 1;
		}

		//Compute the prodcut of the toals with priors and weights and assumed prob.
		//product of ( #word appears in all class * (#word appears in class c/#words in class c) )+ weight*assumed probability all divided by weight + #words appeared in all classes
		for(int cat : categories){
			for(String pt : parsedTweet){
				if(category_count.get(cat).containsKey(pt) && total_all_classes.containsKey(pt)){ 
					//This is going to be a terribly long expression sadly.
					class_probability[cat] *= ((total_all_classes.get(pt)*(category_count.get(cat).get(pt)/(float)category_count.get(cat).size())) + weight*assumed_prob)/(float)(weight + total_all_classes.get(pt));
				}
			}
		}

		//Multiply the whole term by the leading term of catProb:
		for(int cat : categories){
			class_probability[cat]  = catProb[cat]*class_probability[cat];
		}

		//Figure out which class/category is the highest and return the best fitting one.
		int bestFit = CAT_SAFE; //If can't figure, return that its SAFE? Dunno if this is the best choice
		int bestProb = -1;
		for(int cat : categories){
			if(class_probability[cat] > bestProb){
				bestFit = cat;
			}
		}		



		return bestFit;

	}

	public static void main(String[] args) {
		NaiveBayes nb = new NaiveBayes();

		nb.train(NaiveBayes.CAT_DANGER,"Syria is under attack");
		nb.train(NaiveBayes.CAT_DANGER,"Bombs in Syria");
		nb.train(NaiveBayes.CAT_SAFE,"Syria officials attend a ball");
		nb.train(NaiveBayes.CAT_SAFE,"Peacetime in Syria");

		switch(nb.classify("Attack in Syria a hoax")){
			case NaiveBayes.CAT_DANGER:
				System.out.println("danger");
				break;
			case NaiveBayes.CAT_SAFE:
				System.out.println("safe");
				break;
		}

	}

}
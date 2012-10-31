package danger_zone;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-28
*
* Class to overview the training of the Naive Bayes Algorithm from the Dataset.
*/
public class BayesTrainer{
	/**
	*Instance of a classifier to use
	*/
	private	NaiveBayes bayes = new NaiveBayes();

	/**
	*The Dataset to use to train the NaiveBayes instance
	*/
	private DataSet data = new DataSet();

	public boolean initializeData(String password){
		try{
			//Open the connection to the database
			data.initialize(password);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return false;
		}
		return true;
	}

	public void trainBayes(){
		Training_Tweet tweet = (Training_Tweet)data.getNext();
		while(tweet != null){
			//Train based on the bit
			bayes.train(tweet.getCategory(),tweet.getTweetText());
			tweet = (Training_Tweet)data.getNext();
		}
	}

	public float validateOn(ArrayList<Training_Tweet> set){
		//Go through the set and see if the categorys of the tweets
		//match what the bayes returns. Return a percentage of correctness
		int total = set.size();
		int correct = 0;
		Training_Tweet t;
		for(int s = 0; s < total; s++){
			t = set.get(s);
			int c = t.getCategory();
			int guess = bayes.classify(t.getTweetText());
			if(c == guess){
				correct = correct + 1;
			}
		}
		
		return correct/(float)total;
	}

	public float validateOn(){
		//Create a validation set
		ArrayList<Training_Tweet> set = new ArrayList<Training_Tweet>();
		for(int i = 0; i < data.size()/5; i ++){
			set.add((Training_Tweet)data.getNext());
		}

		return validateOn(set);
	}

	public void crossValidation(){
		//K fold cross validation with k=10
		int size = data.size();
		int foldSize = size/10;
		//Break everything into sets
		ArrayList<List<Training_Tweet>> validationSets = new ArrayList<List<Training_Tweet>>();
		for(int k = 0; k < 10; k++){
			//Get the validation sets:
			List<Training_Tweet> set = new ArrayList<Training_Tweet>();
			for(int j = 0; j < foldSize; j++){
				Training_Tweet t = (Training_Tweet)data.getNext();
				if(t != null){	
					set.add(t);
				}
			}
			validationSets.add(set);
		}
		//Train with each set and validate?
		ArrayList<Training_Tweet> validSet;
		ArrayList<Training_Tweet> set;
		for(int k=0; k < 10; k++){
			validSet = (ArrayList<Training_Tweet>) validationSets.get(k);
			for(int j = 0; j < 10; j++){
				//Don't train on the validation set
				if(j!=k){
					set = (ArrayList<Training_Tweet>)validationSets.get(j);
					for(int h = 0; h < set.size(); h++){ 
						bayes.train(set.get(h).getCategory(),set.get(h).getTweetText());
					}
				}
			}
			//Validate:
			System.out.println(validateOn(validSet));
		}


	}

	public void run(String password){
		//Create the DataSet
		initializeData(password);
		//Begin Training the data on everything in the dataset
		crossValidation();
		//System.out.println(validateOn());


	}

	public static void main(String[] args) {
		//Command line parameter of password
		if(args.length < 1){
			System.out.println("Required parameter: Password");
			System.exit(1);
		}

		BayesTrainer bt = new BayesTrainer();
		bt.run(args[0]);
		
	}




}
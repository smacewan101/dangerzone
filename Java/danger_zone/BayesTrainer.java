package danger_zone;
import java.io.*;

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
	private Dataset data = new Dataset();

	public initializeData(String password){
		try{
			//Open the connection to the database
			data.initialize(password);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}



	public run(String password){
		//Create the DataSet
		initializeData(password);
		//Begin Training the data on everything in the dataset
		while((Training_Tweet tweet = data.getNext()) != null){
			//Train based on the bit
		}

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
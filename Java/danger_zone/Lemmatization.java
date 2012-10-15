package danger_zone;
import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.Stack;
import java.util.Map;
import java.io.*;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-11
*
*/
public class Lemmatization{
	/**
	*Variable to hold the initial word and once all operations have been applied, the lemma.
	*/
	String word;
	/**
	*List of common suffixes in the english language
	*/
	static final private String[] suffixes = {"ly","y","ing","ed","en","er","est","n't","s","ian","ize","ise","fy","ful","able","ible","hood","ness","less","ism","ment","ist","ment","al","ish","ify","acy","ance","ence","dom","or","ity","ty","ship","sion","tion","ate","esque","ic","ical","ious","ous","ive"};
	/**
	*List of common prefixes in the english language
	*/
	static final private String[] prefixes = {"an","a","ante","anti","auto","circum","co","com","con","contra","de","dis","en","ex","extra","hetero","homo","hyper","homo","hyper","il","im","in","ir","inter","intra","in","macro","micro","mono","non","omni","post","pre","pro","sub","syn","trans","tri","uni","un"};

	/**
	*@param word The word to be lemmatized
	*Constructs the Lemmatization object from the given word
	*/
	public Lemmatization(String word){
		this.word = word;
		this.removeSuffix();
		this.removePrefix();
	}

	/**
	*Removes any suffix from the word this instance is constructed from.
	*/
	public void removeSuffix(){
		for(String s : suffixes){
			if(this.word.endsWith(s)){
				this.word = this.word.substring(0,this.word.length() - s.length());
			}
		}
	}
	
	/**
	*Removes any prefix from the word this instance is constructed from.
	*/
	public void removePrefix(){
		for(String s : prefixes){
			if(this.word.startsWith(s)){
				this.word = this.word.substring(s.length(),this.word.length());
			}
		}
	}

	/**
	*Returns this instance's word.
	*/
	public String toString(){
		return this.word;
	}



	public static void main(String argv[]) throws Exception
	{
		Lemmatization s = new Lemmatization("any");
		System.out.println(s);

	}

}
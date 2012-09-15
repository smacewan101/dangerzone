<?php 

class TwitterFeed extends Model{
	//https://twitter.com/statuses/user_timeline/thedangerzone3.xml?count=5
	public $feedToParse = "TheDangerZone3";
	
	public function __construct(){}

	//For this function action is the feed to parse
	public function getData($action=null){
		if(!is_null($action)){
			//action in this case will be the twitter acount to get
			$this->feedToParse = $action;
		}
		
		//Construct and load the xml url
		$xml = simplexml_load_file('http://twitter.com/statuses/user_timeline/' . $this->feedToParse .'.xml?count=5');
		if(!$xml){
			echo 'Could Not Load Twitter';
			return;
		}
		//return the data
		return $xml;
	}

	static public function linkify_tweet($v)
	{
		//Cool function that makes Tweet links into links
	    $v = ' ' . $v;
	 
	    $v = preg_replace('/(^|\s)@(\w+)/', '\1@<a href="http://www.twitter.com/\2">\2</a>', $v);
	    $v = preg_replace('/(^|\s)#(\w+)/', '\1#<a href="http://search.twitter.com/search?q=%23\2">\2</a>', $v);
	 
	    $v = preg_replace("#(^|[\n ])([\w]+?://[\w]+[^ \"\n\r\t<]*)#ise", "'\\1<a href=\"\\2\" >\\2</a>'", $v);
	    $v = preg_replace("#(^|[\n ])((www|ftp)\.[^ \"\t\n\r<]*)#ise", "'\\1<a href=\"http://\\2\" >\\2</a>'", $v);
	    $v = preg_replace("#(^|[\n ])([a-z0-9&\-_\.]+?)@([\w\-]+\.([\w\-\.]+\.)*[\w]+)#i", "\\1<a href=\"mailto:\\2@\\3\">\\2@\\3</a>", $v);
	     
	    return trim($v);
	}

}


?>
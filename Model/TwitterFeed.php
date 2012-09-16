<?php 
class TwitterFeed{
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




}


?>
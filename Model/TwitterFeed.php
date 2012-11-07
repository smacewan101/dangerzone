<?php 

require_once('./Twitter/tmhOAuth/tmhOAuth.php');

class TwitterFeed{
	
	//twitter authentication library
	
	//include('./');

	//information needed to connect to twitter
	private $connection_data = array(
		'consumer_key' => '0ZpaqIc5dIdQEEtaUYf7wQ',
		'consumer_private_key' => 'R275pd4ps5zYJX4WAWTZcgDx65wxQ2UrTHREguzILAY',
		'access_token' => '825797034-jE7LzZOXrEo7VvsN9i0I6zzeBCNaxXbFhPGoY3Xt',
		'access_secret_token'=> 'VXugw7vMlMJM8EpchD4sl7XkdpaOF1bJFGlR7Ei60');

	//varibale to hold connection data
	private $authenticate;

	//User name and url to send request to through the twitter authentication library
	public $feedToParse = "thedangerzone3";
	private $url = '1/statuses/user_timeline';
	
	public function __construct(){
		$this->authenticate = new tmhOAuth($this->connection_data);
	}

	//For this function action is the feed to parse
	public function getData($user=null, $url=null){
		if(!is_null($user)){
			//action in this case will be the twitter acount to get
			$this->feedToParse = $user;
		}
		if(!is_null($url)){
			$this->url = $url;
		}
		$this->authenticate->request('GET', $this->authenticate->url($this->url), array('screen_name'=> $this->feedToParse));
		//Construct and load the xml url
		
		//return the data
		return json_decode($this->authenticate->response['response']);
	}




}


?>
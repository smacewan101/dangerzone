<?php
require_once('/tmhOAuth/tmhOAuth.php');

	class TwitterQuery{
		
		public $search;
		private $keywords;
		private $lang;
		private $rpp;
		private $geo;


		//twitter keys used to use a GET request with twitter
		private $connection_data = array(
		'consumer_key' => '0ZpaqIc5dIdQEEtaUYf7wQ',
		'consumer_private_key' => 'R275pd4ps5zYJX4WAWTZcgDx65wxQ2UrTHREguzILAY',
		'access_token' => '825797034-jE7LzZOXrEo7VvsN9i0I6zzeBCNaxXbFhPGoY3Xt',
		'access_secret_token'=> 'VXugw7vMlMJM8EpchD4sl7XkdpaOF1bJFGlR7Ei60');


		//set some default search parameters
		public function __construct(){
			$this ->lang = "lang=en";
			$this ->rpp = "rpp=5";
			$this ->keywords = "q=";
			$this ->geo = null;
			$this->search = null;
		}


		//set the results per page
		//input: integer > 0
		public function setRpp($rpp){
			if ($rpp >0){
				$this->rpp ="rpp=".$rpp;
			}
		}

		//set the keyowrds or hashtags to use in the search
		//input: array of strings
		public function setKeywords($keywords){
			$this->keywords = "q=".urlencode(implode(" ", $keywords));
		}


		//set the geocode to use in the search
		//input: array with lattitude, longitude and optional radius in mi or km 
		public function setGeo($geo){
			if(count($geo)==2 || count($geo)==3){
				$this->geo ="geocode=".implode(",",$geo);	
			}else{
				$this->geo = null;
			}			
		}

		//search twitter via the search api
		public function searchResults() {
			if ($this->search != null){
				$url = "http://search.twitter.com/search.json?".$this->search;
				$curl = curl_init();
				curl_setopt( $curl, CURLOPT_URL, $url );
				curl_setopt( $curl, CURLOPT_RETURNTRANSFER, 1 );
				$result = curl_exec( $curl );
				if(is_object(json_decode($result, false))){
					$search_results = json_decode($result, false);
					$return = $search_results->results;
				}else{
					$return = curl_errno($curl);
				}
				curl_close( $curl );
				return $return;
			}else{ 
				echo "<p> No Search Was Given.</p>";
				return '-1';
			}
		}


		//construct the search string to be used to query twiiter for tweets
		public function constructSearch(){
			$searchParams = array($this->keywords, $this->geo, $this->lang, $this->rpp);
			$this->search = implode('&',$searchParams);
		}

		//grabs all the recent tweets from a specific user by their username on twitter
		//INPUT:  user_name as a string
		//OUTPUT: array of all the tweets returned my twitter
		public function getUserTimelineByName($user_name){
			$authenticate = new tmhOAuth($this->connection_data);
			$url = '1/statuses/user_timeline';
			$authenticate->request('GET', $authenticate->url($url), array('screen_name'=>$user_name));
			$response = json_decode($authenticate->response['response'], false);
			if(array_key_exists('errors', $response)){
				return $response->errors[0]->code;
			}else{
				return $response;
			}			
		}

		public function getUserTimelineByID($user_id){
			$authenticate = new tmhOAuth($this->connection_data);
			$url = '1/statuses/user_timeline';
			$authenticate->request('GET', $authenticate->url($url), array('user_id'=>$user_id));
			$response = json_decode($authenticate->response['response'], false);
			if(array_key_exists('errors', $response)){
				return $response->errors[0]->code;
			}else{
				return $response;
			}			
		}

		/*
		
		PHP does not have overloading of this type, will look into this later

		//construct search with specific keywords
		//input: array of keywords
		public function constructSearch($keywords){
			$this->setKeywords($keywords);
			$this->constructSearch();
		}


		//construct search given keywords and geocode
		//input: two arrays, one of keywords and one of geocodes
		public function constructSearch($keywords, $geocode){
			$this ->setKeywords($keywords);
			$this ->setGeo($geocode);
			$this ->constructSearch();
		}

		public function constructSearch($keywords, $geocode, $rpp){
			$this ->setKeywords($keywords);
			$this ->setGeo($geocode);
			$this ->setRpp($rpp);
			$this ->constructSearch();
		}

		*/
	}
?>
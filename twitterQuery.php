<?php

	class TwitterQuery{

		public $search;
		private $keywords;
		private $lang;
		private $rpp;
		private $geo;


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
				curl_close( $curl );
				$return = json_decode($result, false);
				return $return;
			}
			else echo "<p> No Search Was Given.</p>";
		}



		//construct the search string to be used to query twiiter for tweets
		public function constructSearch(){
			$searchParams = array($this->keywords, $this->geo, $this->lang, $this->rpp);
			$this->search = implode('&',$searchParams);
			echo $this->search;
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
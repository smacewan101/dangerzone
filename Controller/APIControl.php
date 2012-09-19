<?php
//API Controller

class APIControl{
	public $page = null;

	public function _construct($requestURI){
		//Grab the url from the server
		$url = explode('/',$requestURI);

		//Grab it out
		$this->page = array_pop($url);
	}

	public function getPage(){
		return $this->page;
	}
}


?>



<?php
//API Controller

class APIControl{
	public $page = null;

	public function _construct(){
		//Grab the url from the server
		$url = explode('/',$_SERVER['REQUEST_URI']);
		//Grab it out
		$this->page = $url[count($url)-1];
		
	}

	public function getPage(){
		return $this->page;
	}
}


?>

<?php

class GitHubView{
	private $xml = null;

	public function __construct($data=null){
		$this->xml = $data;
		$this->render();
	}	

	public function render(){
		//Render nothing if we have no data to render
		if(is_null($this->xml)){return;}

		var_dump($this->xml);
	}

}

?>
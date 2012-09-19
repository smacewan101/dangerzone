<?php 

class APIPage{
	private $page = null;

	public function __construct($page){
		$this->page = $page;	
	}

	public function render(){
		if($page = "" || $page = null){
			include "../View/API/Home.php";
		}else{
			include "../View/API/".$page;
		}
	}

}
?>
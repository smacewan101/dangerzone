<?php 

class APIPage{
	private $page = null;

	public function __construct($page){
		$this->page = $page;	
		$this->render();
	}

	public function render(){
		if($this->page == "" || $this->page == null){
			include "../View/API/Home.php";
		}else{
			include "../View/API/".$this->page;
		}
	}

}
?>
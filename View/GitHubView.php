<!-- JAVASCRIPT-->

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

		//Grab the last update time and stuff
		echo  $this->xml->title . '<br />';
		echo 'Last Updated on: ' . substr($this->xml->updated, 0,10) .'<br />';
		
		//Echo out commits
		//We don't too many commits, imagine if there were 1000's! So we'll limit it to 5
		$stop = 0;
		foreach ($this->xml->entry as $entry) {
			if($stop > 4){break;}
			//Get the link to commit
			$href = $entry->link['href'];
			//Get who made it
			$author = $entry->author->name;
			$authorLink = $entry->author->uri;
			//Get the changes from the commit
			$changes = $entry->content;
			//Now get the message of the commit
			$msg = $entry->title;
			//Display who made it and when while linking the name to the uri
			echo '<a href="'.$href.'" id="GitMit" >Commit</a> by <a id="GitMit" href="' . $authorLink . '">' . $author .'</a><br />';
			//Use the message of the commit as a drop down for the content
			echo '<p class="GitCommit" onclick="dropClicked('."'".'content'.$stop."'".');">' .$msg .'</p>';
			echo '<span class ="GitContent" id="content'.$stop.'">' . $changes .'</span>';
			$stop=$stop+1;
			echo '<hr id="gitRule">';
		}
	}

}

?>
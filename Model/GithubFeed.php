<?php
// https://github.com/EJEHardenberg/API-Page/commits/master.atom
//Git hub RSS Feed Parser
class GithubFeed{
	private $username = 'EJEHardenberg';
	private $repoName = 'API-Page';
	private $branchName = 'master';

	public function __construct(){}

	//For this function action is the feed to parse
	public function getData($uName=null,$rName=null,$bName=null){
		$this->username = is_null($uName) ? $this->username : $uName;
		$this->repoName = is_null($rName) ? $this->repoName : $rName;
		$this->branchName=is_null($bName) ? $this->branchName : $bName;

		
		//Construct and load the xml url
		$xml = simplexml_load_file('https://github.com/'.$this->username.'/'.$this->repoName.'/commits/'.$this->branchName.'.atom');
		if(!$xml){
			echo 'Could Not Load GitHub';
			return;
		}
		//return the data
		return $xml;
	}

}

?>
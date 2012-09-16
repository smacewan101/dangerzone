<?php
//View for the twitter!

class TwitterView{
	private $xml = null;

	public function __construct($data=null){
		$this->xml = $data;
		$this->render();
	}	

	public function render(){
		//Render nothing if we have no data to render
		if(is_null($this->xml)){return;}

		foreach ($this->xml as $status) {
			//Format the Date
			$datArray = explode(' ',$status->created_at);
			echo 'Tweet at '. $datArray[3] . ' on ' . $datArray[1] . ' ' . $datArray[2] . ' ' . $datArray[5] . '<br />';
			echo $this->linkify_tweet($status->text) . '<br /><hr id="tweetrule">';
		
		}
	}

	public function linkify_tweet($v)
	{
		//Cool function that makes Tweet links into links
	    $v = ' ' . $v;
	 
	    $v = preg_replace('/(^|\s)@(\w+)/', '\1@<a href="http://www.twitter.com/\2">\2</a>', $v);
	    $v = preg_replace('/(^|\s)#(\w+)/', '\1#<a href="http://search.twitter.com/search?q=%23\2">\2</a>', $v);
	 
	    $v = preg_replace("#(^|[\n ])([\w]+?://[\w]+[^ \"\n\r\t<]*)#ise", "'\\1<a href=\"\\2\" >\\2</a>'", $v);
	    $v = preg_replace("#(^|[\n ])((www|ftp)\.[^ \"\t\n\r<]*)#ise", "'\\1<a href=\"http://\\2\" >\\2</a>'", $v);
	    $v = preg_replace("#(^|[\n ])([a-z0-9&\-_\.]+?)@([\w\-]+\.([\w\-\.]+\.)*[\w]+)#i", "\\1<a href=\"mailto:\\2@\\3\">\\2@\\3</a>", $v);
	     
	    return trim($v);
	}
}

?>
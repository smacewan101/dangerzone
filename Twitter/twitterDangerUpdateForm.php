<?php
require_once("twitterQuery.php");
require_once("twitterDB.php");
$twitterQuery = new TwitterQuery();
$db = new TwitterDB();

if(!array_key_exists('username', $_POST)){
	$keywords = explode(' ', $_POST['keywords']);
	$lat = $_POST['lat'];
	$long = $_POST['long'];
	$radius=$_POST['radius'];
	$units =$_POST['units'];
	$rpp =$_POST['rpp'];
	if($lat != '' && $radius != ''){
		$geo=array($lat,$long,implode('',array($radius,$units)));
		$twitterQuery->setGeo($geo);
	}
	$twitterQuery->setKeywords($keywords);
	$twitterQuery->setRpp($rpp);
	$twitterQuery->constructSearch();
	$tweets = $twitterQuery->searchResults();
}else{
	if($_POST['type'] == 'name'){
		$tweets = $twitterQuery->getUserTimelineByName($_POST['username']);
	}else{
		$tweets = $twitterQuery->getUserTimelineByID($_POST['username']);
	}
}

if(is_int($tweets)){
	echo $tweets;
}else{
	echo '<form action="twitterDangerUpdate.php" method="post">';
	$i=0;
	foreach($tweets as $tweet){
		//var_dump($tweet);
		$text = $tweet->text;
		$id_str = $tweet->id_str;		
		echo '<p>';
		echo $i;
		echo '<br />';		
		echo $text;
		echo '<input type="radio" name="danger'.$i.'" value="0!###!'.$id_str.'" checked="true">Not Dangerous</option>';
		echo '<input type="radio" name="danger'.$i.'" value="1!###!'.$id_str.'">Dangerous</opiton>';	
		echo '</p>';
		$i = $i +1;
	}
	$db->insertTweets($tweets,$twitterQuery->search);
	echo '<input type="submit"/>';
	echo '</form>';
}
?>
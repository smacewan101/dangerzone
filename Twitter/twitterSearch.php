<?php
require_once("twitterQuery.php");
require_once("twitterDB.php");
$twitterQuery = new TwitterQuery();

/*
//Test that grabbing tweets by user_id works
$tweets = $twitterQuery->getUserTimeline('apoeticshepard');
var_dump($tweets);
*/


//$geo = array('44.475','-73.212', '5mi');
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
	$db = new TwitterDB();
	foreach( $tweets as $result )
	{	
		echo '<p>';
		echo $result->id_str;
		echo '<br />';
		if(array_key_exists('from_user_id_str', $result)){
			echo $result->from_user_id_str;
		}else{
			echo $result->user->id_str;
		}
		echo '<br />';
		echo $result->text;
		echo '<br />';
		echo $db->tweetDate($result->created_at);	
		if($result->geo != null){
			echo '<br />';
			echo $result->geo->coordinates[0];
			echo '<br />';
			echo $result->geo->coordinates[1];
		}else{
			echo '<br />';
			echo 'null';
		}
		echo '</p>';
	}
	$db->insertTweets($tweets,$twitterQuery->search);	
}

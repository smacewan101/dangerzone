<?php
require_once("twitterQuery.php");
require_once("twitterDB.php");
$twitterQuery = new TwitterQuery();
$keywords = explode(' ', $_POST['keywords']);
//$geo = array('44.475','-73.212', '5mi');
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
if(is_int($tweets)){
	echo $tweets;
}else{
	$db = new TwitterDB();
	foreach( $tweets as $result )
	{
		echo '<p>';
		echo $result->id_str;
		echo '<br />';
		echo $result->from_user_id_str;
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
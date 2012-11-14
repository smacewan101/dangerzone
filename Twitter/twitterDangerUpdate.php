<?php
require_once('twitterDB.php');
$db = new TwitterDB();
foreach($_POST as $danger){
	$tweet_data = explode('!###!', $danger);
	$db->updateTweetDanger($tweet_data[1], $tweet_data[0]);
}
echo 'Thank you for grabbing some training info, it is much appreciated.'
?>
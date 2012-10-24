<?php
	require_once('twitterDB.php');
	$db = new TwitterDB();
	$id_str = "253667508828135424";
	$from_user_id="260872727";
	$tweets=$db->selectTweets();
	$id_tweet = $db->selectTweetByID($id_str);
	$user_tweets = $db->selectTweetByUserID($from_user_id);
	echo '<p>';
	if(count($tweets)!=0){
		foreach($tweets as $tweet){
			echo $tweet[4];
			echo '<br />';
		}
	}else{
		echo "No tweets found with selectTweets()";
	}
	echo "</p>";
	echo '<p>';
	if(count($id_tweet)!=0){
		foreach($id_tweet as $tweet){
			echo $tweet[4];
			echo '<br />';
		}	
	}else{
		echo "No tweets found with string id =".$id_str;
	}
	echo '<p>';
	echo "</p>";
	if(count($user_tweets)!=0){
		foreach($user_tweets as $tweet){
			echo $tweet[4];
			echo '<br />';
		}
	}else{
		echo "No tweets found with user id =".$from_user_id;
	}
	echo "</p>";
?>
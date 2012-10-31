<?php
require_once('twitterDB.php');
$db = new TwitterDB();
$tweets = $db->selectNewTweets();

echo '<form action="twitterDangerUpdate.php" method="post">';
$i=0;
foreach($tweets as $tweet){
	//var_dump($tweet);
	echo '<p>';
	echo $i.' '.$tweet['text'];
	echo '<br />';	
	echo '<input type="radio" name="danger'.$i.'" value="0,'.$tweet['id_str'].'" checked="true">Not Dangerous</option>';
	echo '<input type="radio" name="danger'.$i.'" value="1,'.$tweet['id_str'].'">Dangerous</opiton>';	
	echo '</p>';
	$i = $i +1;
}
echo '<input type="submit"/>';
echo '</form>';
?>
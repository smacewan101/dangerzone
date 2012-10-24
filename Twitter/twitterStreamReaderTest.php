<?php


	//require_once('twitterStreamReader.php');
	require_once('tmhoauth/tmhOAuth.php');
	require_once('tmhoauth/tmhUtilities.php');
/*
	$connection_data = array(
		'consumer_key' => '0ZpaqIc5dIdQEEtaUYf7wQ',
		'consumer_private_key' => 'R275pd4ps5zYJX4WAWTZcgDx65wxQ2UrTHREguzILAY',
		'access_token' => '825797034-jE7LzZOXrEo7VvsN9i0I6zzeBCNaxXbFhPGoY3Xt',
		'access_secret_token'=> 'VXugw7vMlMJM8EpchD4sl7XkdpaOF1bJFGlR7Ei60');
	$OAuth = new tmhOAuth($connection_data);
	$method = 'https://stream.twitter.com/1.1/statuses/filter.json';

	$params = array(
		'track' => 'meeting');

	Function my_streaming_callback($data, $length, $metrics) {
  	// Twitter sends keep alive's in their streaming API.
  	// when this happens $data will appear empty. 
  	// ref: https://dev.twitter.com/docs/streaming-apis/messages#Blank_lines
	 	 global $raw;
		 if ($raw) :
		   echo $data;
		 else :
		   $data = json_decode($data, true);

		   $date = strtotime($data['created_at']);
		   $data['text'] = str_replace(PHP_EOL, '', $data['text']);
		   $data['user']['screen_name'] = str_pad($data['user']['screen_name'], 15, ' ');
		   echo "{$data['id_str']}\t{$date}\t{$data['user']['screen_name']}\t\t{$data['text']}" . PHP_EOL;

		   global $count;
		   $count++;

		   global $first_id;
		   if ($first_id==0)
		     $first_id = $data['id_str'];
		 endif;

		 global $limit;
		 if ($count==$limit) :
		   return true;
		 endif;
		 return file_exists(dirname(__FILE__) . '/STOP');
    }
  	
  	$OAuth->streaming_request('POST', $method, $params, 'my_streaming_callback');	
  	tmhUtilities::pr($OAuth);

*/	
	//$tweet_text = 'meeting';
	$connection_data = array(
		'consumer_key' => '0ZpaqIc5dIdQEEtaUYf7wQ',
		'consumer_private_key' => 'R275pd4ps5zYJX4WAWTZcgDx65wxQ2UrTHREguzILAY',
		'access_token' => '825797034-jE7LzZOXrEo7VvsN9i0I6zzeBCNaxXbFhPGoY3Xt',
		'access_secret_token'=> 'VXugw7vMlMJM8EpchD4sl7XkdpaOF1bJFGlR7Ei60');
	$connection = new tmhOAuth($connection_data);
	$connection->request('GET', $connection->url('1/statuses/user_timeline'), array('screen_name'=>'TheDangerZone3'));
	
	$tweets = json_decode($connection->response['response']);
	foreach($tweets as $tweet){
		echo $tweet->text;
		echo '<br />';
	}

/*
	require_once('twitterStreamReader.php');
	$streamReader= new TwitterStreamReader();
	$streamReader->startStream();

	*/
 
?>
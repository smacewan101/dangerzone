<?php

class TwitterStreamReader{
	
	private $consumer_key = '0ZpaqIc5dIdQEEtaUYf7wQ';
	private $consumer_private_key = 'R275pd4ps5zYJX4WAWTZcgDx65wxQ2UrTHREguzILAY';
	private $access_token = '825797034-jE7LzZOXrEo7VvsN9i0I6zzeBCNaxXbFhPGoY3Xt';
	private $access_secret_token= 'VXugw7vMlMJM8EpchD4sl7XkdpaOF1bJFGlR7Ei60';

	private $method = 'POST';
	private $url = 'https://stream.twitter.com/1.1/statuses/filter.json';
	private $track = 'meeting';
	private $signature_method='HMAC-SHA1';
	private $oauth_verison='1.0';


	public function __construct(){
	}

	public function startStream(){
		$time_stamp = time();
		$nonce = md5(time());
		$authorization=array('oauth_consumer_key' =>$this->consumer_key,
					'oauth_nonce' => $nonce,
					'oauth_signature_method' => $this->signature_method,
					'oauth_timestamp' => $time_stamp, 
					'oauth_token' => $this->access_token, 
					'oauth_version' => $this->oauth_verison);
		$base_string =urlencode($this->method).'&'.urlencode($this->url);
		foreach($authorization as $key => $param){
			$base_string .= '&'.urlencode($key).''.urlencode('=').''.urlencode($param);
		}
		$secret = $this->consumer_private_key.'&'.$this->access_secret_token;
		$signature = base64_encode(hash_hmac('sha1', $base_string, $secret));
		$authorization['oauth_signature'] = $signature;
		ksort($authorization);
		$header_string = 'OAuth ';
		foreach($authorization as $key => $param){
			$header_string .= urlencode($key).'="'.$param.'", '; 
		}
 		$header_string = trim($header_string);
 		$header_string = trim($header_string,',');
 		$curl_request = curl_init();
 		curl_setopt_array($curl_request, array(
 			CURLOPT_URL => $this->url,
 			CURLOPT_USERAGENT => 'TheDangerZone3',
 			CURLOPT_CONNECTTIMEOUT => '30',
 			CURLOPT_TIMEOUT => '0',
 			CURLOPT_POST => true,
 			));

 		var_dump($curl_request);

	}
}
?>
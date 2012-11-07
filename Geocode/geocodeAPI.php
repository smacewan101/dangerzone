<?php

class GeocodeAPI{
	/* ITEMS NEED BY MAPQUEST API
	private $key = 'Fmjtd%7Cluuanu01ll%2Crg%3Do5-96b0d4';
	private $urlHeader = 'http://www.mapquestapi.com/geocoding/v1/';
	*/

	private $urlHeader ='http://maps.googleapis.com/maps/api/geocode/json?';

	public function __construct(){}


	//Converts a pair of latitude and longitude points into an address
	//INPUT: latitude, longitute
	//RETURN: formatted string address that is returned by google geocode api
	public function reverseGeo($lat,$long){


		//code for mapquest api
		/*
		$input_params = array('key' => $this->key,
							 'lat' => $lat,
							 'lng' =>$long,
							 'callback'=>'renderReverse');
		$input_string='';
		foreach ($input_params as $key => $value) {
			$input_string .= $key.'='.$value.'&';
		}
		$input_string= rtrim($input_string, '&');
		$url = $this ->urlHeader.'reverse?'.$input_string;
		*/

		$input_string ='latlng='.$lat.','.$long.'&sensor=false';
		$url = $this ->urlHeader.$input_string;
		$curl = curl_init();		
		curl_setopt( $curl, CURLOPT_URL, $url );
		curl_setopt( $curl, CURLOPT_RETURNTRANSFER, 1 );
		$result = json_decode(curl_exec($curl), false);
		if($result->status == 'OK'){
			return $result->results[0]->formatted_address;
		}
		else return 'No Address Found';
	}

	//Converts an address into latitude/longitude coordinates
	//INPUT: address array. Each entry should be a part of the address
	//	NOTE:
	//	1. example= array('12345 Some Road', 'Some City/Town', 'Some Country')
	//	2. NOT ALL OF THESE ENTRIES ARE NECESSARY, only one is needed for query to suceed
	//	   but more specificity the better
	//RETURN: an array($lat, $long)
	public function geocode($address_array){
		$input_string= urlencode(implode(',', $address_array));
		$url = $this->urlHeader.'address='.$input_string.'&sensor=false';
		$curl = curl_init();		
		curl_setopt( $curl, CURLOPT_URL, $url );
		curl_setopt( $curl, CURLOPT_RETURNTRANSFER, 1 );
		$result = json_decode(curl_exec($curl), false);
		if($result->status == 'OK'){
			return array($result->results[0]->geometry->location->lat, $result->results[0]->geometry->location->lng);
		}else{
			return 'No Geocode Found';
		}
	}
}

?>
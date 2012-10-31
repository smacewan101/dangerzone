<?php

require_once('../Geocode/geocodeAPI.php');

$geo = new GeocodeAPI();
$lat = '34.802075';
$long = '38.996815';
echo '<p>';
echo $geo->reverseGeo($lat, $long);
echo '</p>';
$location = array('M20', 'Syria');
echo '<p>';
$geocodes = $geo->geocode($location);
foreach($geocodes as $value){
	echo '<br />';
	echo $value;
}
echo '</p>';


?>
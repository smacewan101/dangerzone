<?php
//Danger Socket Test

//Send Text of "LON 91.12 LAT 40.78"
$host = "127.0.0.1";  
$hostport = 5480;  
$resultport = 5481;
$timeout = 30;  //timeout in seconds  
  
$socket =  fsockopen($host,$hostport,$errNum);

if($socket){
	fwrite($socket,"LON 13 LAT 9 NUM 3\n");
	sleep(1);
	//Then we hope to god that happiness occurs
	$response = $_POST['response'];
}

fclose($socket);
echo "Completed with: " . $errNum;

?>
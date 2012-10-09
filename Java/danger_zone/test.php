<?php
//Danger Socket Test

//Send Text of "LON 91.12 LAT 40.78"
$host = "127.0.0.1";  
$hostport = 5480;  

$sock = stream_socket_client($host . ':' . $hostport,$errno,$errstr,10);
if(!$sock){
	echo $errstr;
}else{
	fwrite($sock, "LON 13 LAT 9 NUM 3\r");
	while(!feof($sock)){
		echo fgets($sock,1024);
		fwrite($sock, "LON 91.12 LAT 40.78 NUM 3\r");
		sleep(1);
	}
	fclose($sock);
}


?>

<?php
//Danger Socket Test

$message = "LON 91.12 LAT 40.78 NUM 3\r";
$domain = (strtoupper(substr(PHP_OS, 0, 3)) == 'WIN' ? AF_INET : AF_INET);
$socketHandle = socket_create($domain, SOCK_DGRAM, SOL_UDP);
$serverIP = "127.0.0.1";
$serverSendPort = 5480;
$serverRecvPort = 5480;

socket_bind($socketHandle, $serverIP, $serverSendPort);

echo 'MESSAGE: ' . $message . '<br />';
socket_sendto($socketHandle, $message, strlen($message), MSG_EOF, $serverIP, $serverSendPort);

$response = "";
$from = "";
$port = 0;
socket_recvfrom($socketHandle, $response, 512, 0, $from, $port);
echo $response . '<br />';


$message = "CLASSIFY Gunshots in Syria";
echo 'MESSAGE: ' . $message . '<br />';
socket_sendto($socketHandle, $message, strlen($message), MSG_EOF, $serverIP, $serverSendPort);

$response = "";
$from = "";
$port = 0;
socket_recvfrom($socketHandle, $response, 512, 0, $from, $port);
echo $response . '<br />';

$message = 'KILLSERVER0x0000';
echo 'MESSAGE: ' . $message . '<br />';
socket_sendto($socketHandle, $message, strlen($message), MSG_EOF, $serverIP, $serverSendPort);


echo $response;


/*
//Send Text of "LON 91.12 LAT 40.78"
$host ="dangerzone.cems.uvm.edu";  
$hostport = 5480;  

$sock = stream_socket_client($host . ':' . $hostport,$errno,$errstr,10);
if(!$sock){
	echo $errstr;
}else{
	fwrite($sock, "LON 13 LAT 9 NUM 3\r");
	//while(!feof($sock)){
		$msg =  fgets($sock,1024);
	//	if($msg){
			echo $msg;
	//	}
	//	fclose($sock);
	//	$sock = stream_socket_client($host . ':' . $hostport,$errno,$errstr,10);
	//	fwrite($sock, "LON 91.12 LAT 40.78 NUM 3\r");
	//}
	
	fwrite($sock, "KILLSERVER0x0000");
	fclose($sock);
}
*/
?>

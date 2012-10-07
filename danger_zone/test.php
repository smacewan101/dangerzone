<?php
//Danger Socket Test

//Send Text of "LON 91.12 LAT 40.78"
$host = "127.0.0.1";  
$hostport = 5480;  
$resultport = 5481;
$timeout = 30;  //timeout in seconds  
  
$socket =  fsockopen($host,$hostport,$errNum);

if($socket){
	fwrite($socket,"LON 91.12 LAT 40.78");
	sleep(1);
	$readsocket = fsockopen($host,$resultport,$errNum2);
	if($){readsocket
		echo $fread($readsocket,8192);
		fclose($readsocket);
	}else{
		echo "Failed read with " . $errNum2 .'\n';
	}

}

fclose($socket);
echo "Completed with: " . $errNum;

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<?php
	//Grabbing some important configutaration info so transfering to server is easy
	require_once('config.php');
?>
<html>

	<head>
		<title>Danger Zone</title>
		<?php echo '<link rel="stylesheet" href= "'.DOCROOT.'Resources/style.css" type="text/css" />' ?>
	</head>

	<body>
		<!-- Get the Header and Navigation -->
		<!-- Top Bar Navigation will be good I think -->
		<div id="navigation">
			<?php
				include('/View/navigation.php');
			?>
		</div>

		<!--Get the Feeds and display them in the box -->
		<div id="leftArea">
			<?php


			?>
			hi
		</div>

		<!-- Figure out what page we're grabbing via the URL -->
		<div id="rightArea">
			<?php
			
			?>
			hi
		</div>

	</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<?php
	//Grabbing some important configutaration info so transfering to server is easy
	require_once('config.php');
	require_once(DOCROOT.'Model/TwitterFeed.php');
?>
<html>

	<head>
		<title>Danger Zone</title>
		<?php echo '<link rel="stylesheet" href= "'.DOCROOT.'Resources/style.css" type="text/css" />' ?>
	</head>

	<body>
		<!-- Get the Header and Navigation -->
		<div id="header">
			<div class="spacer"></div>
			<h1 id="archer">YOU'RE IN THE DANGER ZONE</h1>
			<!-- Top Bar Navigation will be good I think -->
			<div class="spacer"></div>
			<div id="navigation">
				<?php
					include('/View/navigation.php');
				?>
			</div>

		</div>

		<!--Get the Feeds and display them in the box -->
		<div id="leftArea">
			<?php


			?>
			asfljaksfjsdklgjsdg
		</div>

		<!-- Figure out what page we're grabbing via the URL -->
		<div id="rightArea">
			<?php
			
			?>
			hi
		</div>

		<!-- Footer Will Just be A little cute thing-->
		<div id="kenny">
			<h1>Ever wonder if Kenny Logins is around the corner? You best call him, because you're in: The Danger Zone</h1>
		</div>
	</body>
</html>
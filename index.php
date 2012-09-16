<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<?php
	//Grabbing some important configutaration info so transfering to server is easy
	require_once('config.php');
	require_once('..'.DOCROOT .'Model/TwitterFeed.php');
	require_once('..'.DOCROOT .'Model/GithubFeed.php');
	require_once('..'.DOCROOT.'View/TwitterView.php');
	require_once('..'.DOCROOT.'View/GitHubView.php');
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
			<h1 id="archer">WELCOME TO THE DANGER ZONE</h1>
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
			<div>
				<!--Twitter Feed -->
				<div id="twitter">
					<h3 id="twitHead"><a href="https://twitter.com/TheDangerZone3" id="TwitterLink">Danger Zone Twitter Feed</a></h3>
						<?php
							//Render the Twitter Feed First
							$twitter = new TwitterFeed();
							new TwitterView($twitter->getData());
						?>
				</div>
				<hr>
				<!-- GitHub RSS Feed-->
				<div id="github">
					<h3 id="gitHead">Danger Zone GitHub Feed</h3>
						<?php 
							//Render the GitHub Feed
							$hub = new  GithubFeed();
							new GitHubView($hub->getData());

						?>
				</div>
			</div>
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
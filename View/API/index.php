<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<?php
	//Index Page for the API folder
	//This guy is going to help control all the requests for the content.
	//Basic set up is the twitter/github stuff on the left like the original index page
	//Then the url will be analyzed, API/page, and we'll attempt to include that page
	//So this pages job is to provide the general structure of the page
	//Its Controller will handle parsing the url and figuring out what the user wants
	//the controller will pass out data (the page name) to the constructor of the view
	//And then poof we'll have the page


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
		<!-- Lets Not Forget Our Javascript -->
		<script type="text/Javascript">
			<!--
				function dropClicked(id){
					//This isn't very pretty but thats ok it works!
					if( document.getElementById(id).style.display == ""){
		 				document.getElementById(id).style.display = 'none';
					}
					if(document.getElementById(id).style.display == 'none' ){
						document.getElementById(id).style.display = 'inline';
					}else{
						document.getElementById(id).style.display = 'none';
					}
				}

			//-->
		</script>
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
					<p id="twitHead"><a href="https://twitter.com/TheDangerZone3" id="TwitterLink">Danger Zone Twitter Feed</a></p>
						<?php
							//Render the Twitter Feed First
							$twitter = new TwitterFeed();
							new TwitterView($twitter->getData());
						?>
				</div>
				<hr>
				<!-- GitHub RSS Feed-->
				<div id="github">
					<p id="gitHead"><a href="https://github.com/EJEHardenberg/API-Page" id="gitHead">Danger Zone GitHub Feed</a></p>
					<p id="gitInfo">Click a commit to see details</p>
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
				//Make the Controller for the API 

				//Render the view
			?>
			<p>
			</p>
		</div>

		<!-- Footer Will Just be A little cute thing-->
		<!--
		<div id="kenny">
			<h1>Ever wonder if Kenny Logins is around the corner? You best call him, because you're in: The Danger Zone</h1>
		</div>
		-->
	</body>
</html>
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
		<?php echo '<link rel="stylesheet" href= "..'.DOCROOT.'Resources/style.css" type="text/css" />' ?>
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
			
			?>
			<p>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. In in enim turpis. Sed porta fermentum ipsum, vel eleifend augue porta nec. Praesent et ligula sit amet augue venenatis ultricies. Phasellus cursus nulla et enim iaculis scelerisque. Sed luctus convallis eros et aliquet. Vivamus vitae erat ac enim adipiscing adipiscing sit amet sit amet purus. Nunc pellentesque molestie venenatis. Morbi vitae arcu nec magna rutrum pulvinar. Nullam quis porttitor nisi. Donec libero diam, fermentum et tempor molestie, laoreet a tortor. Cras sit amet tortor enim, consequat convallis dui. Donec erat ipsum, accumsan vulputate ornare ac, tristique eu purus. Praesent mauris lectus, posuere non rhoncus sed, interdum eu lectus.

			Nam a quam velit, vitae porta dolor. Maecenas mi libero, mattis et iaculis a, tempus rutrum enim. Pellentesque eu aliquam erat. Suspendisse non massa eget nibh consequat dignissim. Cras aliquet metus vel ligula faucibus vel volutpat magna consectetur. Fusce tempus quam sed leo auctor tempor. Aenean elit erat, viverra nec fermentum a, bibendum eget lacus. Nunc condimentum ornare ultrices. Nam est mauris, cursus eget placerat id, egestas id metus. Sed ac vulputate dolor. Aenean sollicitudin convallis pellentesque.

			Nulla facilisi. Duis egestas tincidunt varius. Nulla interdum tincidunt dolor, quis porttitor leo ornare eget. Nam vehicula dolor eu turpis tempus ut feugiat augue semper. Suspendisse non posuere dolor. Etiam adipiscing volutpat lorem sodales ullamcorper. Pellentesque massa libero, imperdiet facilisis viverra a, pulvinar in massa. Nullam faucibus lacus vel lorem venenatis aliquet. Proin varius, sem at gravida suscipit, leo dolor feugiat libero, quis mollis ante magna quis lectus. Sed nec arcu ante, in consectetur enim. Cras scelerisque dignissim tristique. Phasellus a massa nec dolor sodales pharetra et consectetur lacus. Ut semper auctor purus eu gravida. Nullam vehicula tellus nec nunc convallis luctus. Etiam turpis turpis, sodales eget feugiat vitae, tristique id libero. Aliquam purus nisi, blandit vitae viverra sed, ullamcorper sit amet tellus.

			Cras et ipsum leo, vitae gravida ipsum. Integer eget risus non nisl posuere vulputate. Nullam tincidunt velit ac nisl luctus tempus. Phasellus non volutpat lacus. Aliquam blandit ipsum lobortis dolor porttitor at rhoncus dui dapibus. Mauris in dolor leo. Duis sit amet libero eu odio commodo placerat non vitae eros. Nam fermentum enim eget nisi dictum laoreet. Proin pharetra ultricies magna, eget interdum tortor sollicitudin at. Sed eget nisl turpis, rhoncus bibendum mauris. Curabitur vulputate mauris vel augue volutpat non interdum massa feugiat. Suspendisse volutpat diam ac justo blandit sed venenatis sem vulputate. Vivamus eleifend turpis pulvinar arcu facilisis facilisis. Phasellus massa sem, facilisis et adipiscing ac, cursus id turpis. In a urna nunc. Praesent commodo libero ipsum, non luctus risus.

			Sed euismod metus quis leo pellentesque faucibus accumsan magna scelerisque. Vestibulum gravida mollis quam pretium dapibus. Nullam vel massa ante. Sed porttitor, dui vitae porta congue, ligula mi scelerisque neque, vel convallis elit enim pulvinar quam. In libero lectus, luctus quis consequat eget, malesuada sit amet justo. Donec justo dolor, aliquam ac malesuada et, vestibulum at sem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque dapibus, lacus eu varius placerat, neque tortor porttitor neque, ac mattis ligula risus non velit.
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
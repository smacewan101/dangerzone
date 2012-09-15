<!-- Navigation Bar for the Site-->
<?php
	//Grabbing some important configutaration info so transfering to server is easy
	require_once('config.php');
?>
<ul class="nav">
		<li>
			<?php echo '<a href="'.DOCROOT.'" class="nav">Home</a>'; ?>
		</li>
		<li>
			<?php echo '<a href="'.DOCROOT.'View/API/" class="nav">API Documentation</a>'; ?>
		</li>
		<li>
			<?php echo '<a href="'.DOCROOT.'View/Contact.php" class="nav">Contact Us</a>'; ?>
		</li>
		<li>
			<?php echo '<a href="'.DOCROOT.'View/Team.php" class="nav">The Team</a>'; ?>
		</li>
</ul>
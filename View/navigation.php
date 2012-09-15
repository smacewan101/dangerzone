<!-- Navigation Bar for the Site-->
<?php
	//Grabbing some important configutaration info so transfering to server is easy
	require_once('config.php');
?>
<ul class="nav">
		<li>
			<?php echo '<a href="'.DOCROOT.'" >Home</a>'; ?>
		</li>
		<li>
			<?php echo '<a href="'.DOCROOT.'/View/API/">API Documentation</a>'; ?>
		</li>
		<li>
			<?php echo '<a href="'.DOCROOT.'/View/Contact.php">Contact Us</a>'; ?>
		</li>
		<li>
			<?php echo '<a href="'.DOCROOT.'/View/Team.php">The Team</a>'; ?>
		</li>
</ul>
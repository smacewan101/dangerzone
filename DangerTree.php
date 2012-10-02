<?php

/*************************************************
KD Tree For Geocoordinates
(2 dimensional K-d Tree)
Ethan Eldridge 9/28
When you can code as I do, you have my permission to type.
*/

class DangerNode{
	//Geo Coordinates stored in an assosociative array
	public $coordinates = null;

	//Database Key for dangerzone
	private $id = null;

	//Timestamp for deletion on time outs
	private $timeStamp = null;

	//Leaves
	private $left = null;
	private $right = null;

	public function __construct($lon,$lat,$id,$tStamp = null, DangerNode $lChild = null, DangerNode $rChild = null){
		$this->id = $id;
		$this->timeStamp = is_null($tStamp) ? date('Y-m-d H:i:s') : $tStamp;
		$this->left  = is_null($lChild) ? null : $lChild;
		$this->right = is_null($rChild) ? null : $rChild;
		$this->coordinates = array('longitude' =>$lon, 'latitude' =>$lat);
	}

	public function setLeftChild(DangerNode $child) { $this->left  = $child; }
	public function setRightChild(DangerNode $child){ $this->right = $child; }
	public function getLeftChild() {return $this->left; }
	public function getRightChild(){return $this->right;}
	public function is_leaf(){ return is_null($this->left) && is_null($this->right);}

	//Search Point should be a tuple of dimensionality 2
	public function nearestNeighborSearch($node, $SearchPoint,$bests,$numOfBests=5,$depth=1){

	}

	//Rebuild function creates a heap of the nodes underneath this one, then reconstructs the tree
	//based on the heap to achieve a balanced heap.
	//Time to take all nodes underneath: (folding on each branch effectively) log?
	//Time to construct a heap: log I believe, so log time to rebalance the tree is pretty damn good
	public balance(){
		//Loop through each subtree to grab nodes
		$nodes = 
		while(!is_null($this->left)){

		}
	}
	
}


?>
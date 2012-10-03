/**
*Ethan Eldridge
*
*/
import java.sql.Timestamp;

class DangerNode{
	private int id;
	private Timestamp timestamp;
	private DangerNode left = null;
	private DangerNode right = null;
	private float[] coordinates;

	/**
	*Creates an instance of the DangerNode. Nulls may be passed for lChild and rChild if there are no children to this node
	*@param longitude The longitude coordinate of the danger zone associated with id
	*@param latitude The latitude coordinate of the danger zone associated with id
	*@param id The integer id for the database entry associated with the danger zone
	*@param lChild the left child of the DangerNode
	*@param rChild the right child of the DangerNode
	*@return the created object instance of DangerNode
	*/
	public DangerNode(float longitude,float latitude,int id,DangerNode lChild,DangerNode rChild){
		this.id = id;
		this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		this.coordinates[0] = longitude;
		this.coordinates[1] = latitude;
		this.left = lChild;
		this.right = rChild;
	}


	/**
	*Creates an instance of the DangerNode. Nulls may be passed for lChild and rChild if there are no children to this node
	*@param longitude The longitude coordinate of the danger zone associated with id
	*@param latitude The latitude coordinate of the danger zone associated with id
	*@param id The integer id for the database entry associated with the danger zone
	*@param lChild the left child of the DangerNode
	*@param rChild the right child of the DangerNode
	*@param tStamp Timestamp in SQL Format
	*@return the created object instance of DangerNode
	*/
	public DangerNode(float longitude,float latitude,int id,DangerNode lChild, DangerNode rChild, java.sql.Timestamp tStamp){
		this(longitude,latitude,id,lChild,rChild);
		this.timestamp = tStamp;
	}

	/**
	*Searches the tree for the searchTuple given, returns null if not found and a DangerNode if found. Note this search is note the nearest neighbor search
	*@param searchTuple The tuple of floats we're searching from.
	*@return null if not found, the DangerNode if found.
	*/
	public DangerNode search(float[] searchTuple){
		//Possibly thinking about having this return the id for the danger zone instead, will have to talk with group about this
		return this.innerSearch(searchTuple,1);
	}

	/**
	*Search used by search function to search along the correct axis given the depth of the tree
	*/
	private DangerNode innerSearch(float[] searchTuple, int depth){
		//To the left, to the left...
		if(coordinates[depth % coordinates.length] < searchTuple[depth % searchTuple.length]){
			if(this.left != null){
				return this.left.innerSearch(searchTuple,depth-1);
			}else{
				return null;
			}
		}else if(coordinates[depth % coordinates.length] > searchTuple[depth % searchTuple.length]){
			if(this.right != null){
				return this.right.innerSearch(searchTuple,depth-1);
			}
		}else{
			//Then we must be equal along the axis we're searching for and should check the other 
			//axis to see if we have an exact match.
			if(coordinates[depth +1% coordinates.length] == searchTuple[depth +1% searchTuple.length]){
				return this;
			}
		}
		return null;
	}

	
}
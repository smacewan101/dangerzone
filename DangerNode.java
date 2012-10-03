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
	private float[] coordinates = new float[2];

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
	*@param searchTuple The tuple of floats we're searching for.
	*@return null if not found, the DangerNode if found.
	*/
	public DangerNode search(float[] searchTuple){
		//Possibly thinking about having this return the id for the danger zone instead, will have to talk with group about this
		return this.innerSearch(searchTuple,1);
	}

	/**
	*Search used by search function to search along the correct axis given the depth of the tree
	*@param searchTuple the tuple of floats we're searching for.
	*@param depth The depth of the tree, required to compute the proper sorting axis of the tree
	*@return null if not found, the DangerNode if found.
	*/
	private DangerNode innerSearch(float[] searchTuple, int depth){
		//Check for match
		if(coordinates[(depth % coordinates.length)] == searchTuple[depth % searchTuple.length]){
			System.out.println("partial match on depth" + depth);
			if(coordinates[(depth +1)% coordinates.length] == searchTuple[(depth +1)% searchTuple.length]){
					System.out.println("Total match");
					return this;
			}
		}
		//To the left, to the left...
		if(coordinates[depth % coordinates.length] < searchTuple[depth % searchTuple.length]){
			System.out.println("Traversing left");
			if(this.left != null){
				return this.left.innerSearch(searchTuple,depth+1);
			}
		}else{
			System.out.println("Traversing right");
			if(this.right != null){
				return this.right.innerSearch(searchTuple,depth+1);
			}
		}
		return null;
	}

	/**
	*Gets the coordinate at the specified index. 
	*@param index Since coordinates are a 2-Tuple, should be either 0 or 1
	*@return The coordinate at that index.
	*/
	public float getCoordinate(int index){
		return coordinates[index];
	}

	public void addNode(DangerNode newNode){
		this.innerAddNode(newNode, 1);
	}

	public void innerAddNode(DangerNode newNode, int depth){
		if(coordinates[depth % coordinates.length] < newNode.getCoordinate(depth % coordinates.length)){
			if(this.left != null){
				this.left.innerAddNode(newNode,depth+1);
			}else{
				this.left = newNode;
			}
		}else{
			if(this.right != null){
				this.right.innerAddNode(newNode,depth+1);
			}else{
				this.right = newNode;
			}
		}
	}


	//Test function
	public static void main(String argv[]) throws Exception
	{
		DangerNode p = new DangerNode(1,1,1,null,null);
		p.addNode(new DangerNode(0,1,1,null,null));
		p.addNode(new DangerNode(3,3,3,null,null));
		float [] s = new float[2];
		s[0] = 1;
		s[1] = 1;
		System.out.println("Searching for 1,1");
		System.out.println(p.search(s));
		s[0] = 0;
		s[1] = 1;
		System.out.println("Searching for 0,1");
		System.out.println(p.search(s));
		s[0] = 3;
		s[1] = 3;
		System.out.println("Searching for 3,3");
		System.out.println(p.search(s));
		s[0] = 34;
		s[1] = -34;
		System.out.println("Searching for bogus value");
		System.out.println(p.search(s));
	}	

}
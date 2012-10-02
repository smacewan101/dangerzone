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
	
}
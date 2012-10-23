package danger_zone;
import java.util.HashMap;
import java.io.*;
import java.sql.*;
import java.sql.ResultSet;

/**
*@author Ethan Eldridge <ejayeldridge @ gmail.com>
*@version 0.0
*@since 2012-10-22
*
* Class providing an interface to the SQL database for training purposes. 
*/
public class DataSet{
	/**
	*
	*/
	Connection con = null;


	/**
	*Opens a connection to the database
	*/
	public Connection openConnection() throws Exception{
		java.util.Properties properties = new java.util.Properties();
		properties.put("user","cs276");
		properties.put("password","v3jNNrGn");
	  	properties.put("characterEncoding", "ISO-8859-1");
	  	properties.put("useUnicode", "true");
	  	String url = "jdbc:mysql://www.dangerzone.cems.uvm.edu/DangerZone";

	  	Class.forName("com.mysql.jdbc.Driver").newInstance();
	  	Connection c = DriverManager.getConnection(url, properties);
	  	return c;
	}

	/**
	*Returns the result set of the tweet table from the database
	*@return The result set of the tweet table
	*/
	public ResultSet getData() throws Exception{
		Statement query = con.createStatement();
		query.executeQuery("SELECT * FROM TBL_TWEET");
		return query.getResultSet();
	}

	public void constructDataSet(ResultSet results) throws Exception{
		try{
			//Read in the data
			while(results.next()){

			}
		}catch(java.sql.SQLException jSQL){
			//Do nothing
		}finally{
			//Cut off the results connection
			results.close();
		}
	}


	public boolean initalize() throws Exception{
		try{
			con = openConnection();
			ResultSet data = getData();
			constructDataSet(data);	
		}catch(Exception e){
			System.out.println(e.getStackTrace());
			return false;
		}
		return true;
	}


}
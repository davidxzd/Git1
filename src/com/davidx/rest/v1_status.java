package com.davidx.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import com.davidx.dao.*;

@Path("/v1/status")
public class v1_status {
	
	private static final String str="00:01";
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<h1><p>Java Restful Service!</p></h1>";
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/version")
	public String returnVersion() {
		return "<h1><p>Version : </p></h1>"+" "+str;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus(){
		PreparedStatement query=null;
		String myString=null;
		String returnString=null;
		Connection conn=null;
		
		try{
			conn=Gitdao.gitoracleConn().getConnection();
			query=conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME "+
						"from sys.dual");
			ResultSet rs=query.executeQuery();
			
			while(rs.next()){
				myString=rs.getString("DATETIME");
			}
			query.close();
			
			returnString="<p>Datebase Status</p> "+"<p>Datebase Date/Time RETURN: "+myString+"</p>";
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return returnString;
	}
	
	
	
	
	

}

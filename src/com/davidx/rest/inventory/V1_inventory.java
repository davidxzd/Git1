package com.davidx.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

import com.davidx.dao.Gitdao;
import com.davidx.util.ToJason;


@Path("/v1/inventory")
public class V1_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllEmp() throws Exception{
		
		PreparedStatement query=null;
		String returnString=null;
		Connection conn=null;
		
		try{
			conn=Gitdao.gitoracleConn().getConnection();
			query=conn.prepareStatement("select * "+ "from HR.EMPLOYEES");
			ResultSet rs=query.executeQuery();
			
			ToJason converter=new ToJason();
			JSONArray json=new JSONArray();
			json=converter.toJSONArray(rs);
			query.close();
			returnString=json.toString();
			
			 
			
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

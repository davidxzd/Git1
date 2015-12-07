package com.davidx.dao;

import javax.naming.*;
import javax.sql.*;


public class Gitdao {

	private static DataSource gitoracle=null;
	private static Context context=null;
	
	public static DataSource gitoracleConn() throws Exception{
		if(gitoracle!=null){
			return gitoracle;
		}
		try{
			if(context==null){
				context=new InitialContext();
			}
			
			gitoracle=(DataSource) context.lookup("gitoracle");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return gitoracle;
	}
}

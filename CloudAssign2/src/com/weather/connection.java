package com.weather;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;




public class connection {

	static String connectionURL = "jdbc:mysql://localhost:3306/weatherdb?verifyServerCertificate=false&useSSL=true";
	static Connection connection = null;
	
	//weatherVO weathervo = new weatherVO();
	
	public static ArrayList<weather> getAllWeathers() throws Exception {

	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(connectionURL,"root", "password");
try {
		
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM daily");
		ResultSet rs = ps.executeQuery();
		ArrayList<weather> weatherList = new ArrayList<weather>();
		
		while (rs.next()) {
			weather weather= new weather();
			
			SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMdd");
			String f = d1.format(rs.getDate(1));
			
			weather.DATE=f;
		weatherList.add(weather);
		
		

		}
		return weatherList;
		} 
		catch (Exception e) {
		throw e;
		}
	}
	
	public static Response getDate(int date) throws Exception
	{
		weatherVO weathervo = new weatherVO();
    	
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "password");
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM daily where date="+date);
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst())
			{
				return Response.status(Status.NOT_FOUND).entity("").build();
			} 
			else{				
				
			
					while (rs.next())
					{
						SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMdd");
						String f = d1.format(rs.getDate(1));
						
					
						weathervo.DATE=f ;
						weathervo.TMAX=rs.getDouble(2);
						weathervo.TMIN=rs.getDouble(3);
					

				}
				return Response.status(Status.OK).entity(weathervo).build();
			}	
			
			
		}
		
		catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
      
	}			
		
	
	public Response updateClimate(weatherVO w) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "password");
			
	    	//weatherVO weathervo = new weatherVO();
			SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
		    //Date d =  (Date) sm.parse(w.DATE);
			java.util.Date d=sm.parse(w.DATE);
			
	    	PreparedStatement ps = connection.prepareStatement("INSERT INTO daily(DATE,TMAX,TMIN) VALUES(?,?,?)");
	    	ps.setDate(1,new java.sql.Date(d.getTime()));
	    	ps.setDouble(2, w.TMAX);
	    	ps.setDouble(3, w.TMIN);
			 ps.executeUpdate();
			
		  weather we = new weather();
	    we.DATE=w.DATE;
	    
				System.out.println(we.DATE);
				return Response.status(Status.CREATED).entity(we).build();
					
			} 
			catch (Exception e) {
					e.printStackTrace();
					return Response.status(Status.CONFLICT).entity(e).build();
				}
	    
		
	}
	
	public Response deleteClimate(String DATE) {
	    
    	
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "password");
			
			SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");
			PreparedStatement preparedstatement= connection.prepareStatement("select * from daily where date= ?");
			System.out.println(DATE);
			java.util.Date d=sm.parse(DATE);
			preparedstatement.setDate(1,(new java.sql.Date(d.getTime())));
			ResultSet rs=preparedstatement.executeQuery();
			boolean flag=rs.last();
			int r=-1;
			if(flag)
				r=rs.getRow();
			if(r==1)
			{
				PreparedStatement ps = connection.prepareStatement("delete from daily where date=?");
			
			ps.setDate(1,(new java.sql.Date(d.getTime())));
			ps.executeUpdate();
			System.out.println(r);
			
			//System.out.println("Executed  "+a);
				return  Response.status(204).build();
			}
			else
				return Response.status(Response.Status.NOT_FOUND).build();
		} 
		catch (Exception e) {
				e.printStackTrace();
				return Response.status(Response.Status.CONFLICT).entity(e).build();
			}
    }
     
	
}


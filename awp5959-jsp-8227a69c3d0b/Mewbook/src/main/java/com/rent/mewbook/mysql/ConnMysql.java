package com.rent.mewbook.mysql;

import java.sql.*;

public class ConnMysql 
{
	private Connection conn      = null;
	private Statement  statement = null;
	private ResultSet  resultset = null;
    
	public void connection()
	{
		try 
	    {
	    	String url = "jdbc:mysql://163.17.136.212:3306/jsp?autoReconnect=true&useSSL=false";
	    	String acc = "rabbit0313";
	    	String pwd = "rabbitbomb";
            conn = DriverManager.getConnection(url, acc, pwd);
            System.out.println("SQL Connection to database established!");
        } 
	    catch (SQLException e) 
	    {
            System.out.println("Connection Failed! Check output console" + e);
            return;
        }
	}
	
	public ResultSet querySelect(String query)
	{
		try 
	    { 
	    	statement = conn.createStatement(); 
	    	resultset = statement.executeQuery(query);
	    	return resultset;
	    	/*while( resultset.next() ) 
	    	{ 
	    		System.out.println( resultset.getInt("MemberId")+"\t\t" + resultset.getString("MemberAccount")+"\t\t" + resultset.getString("MemberPassword") ); 
	    	} */
	    } 
	    catch(SQLException e) 
	    { 
	    	System.out.println("DropDB Exception :" + e.toString()); 
	    	return resultset;
	    }
	}
	
	public void queryUpdate(String query)
	{
		try 
	    { 
	    	statement = conn.createStatement(); 
	    	statement.executeUpdate(query);
	    } 
	    catch(SQLException e) 
	    { 
	    	System.out.println("DropDB Exception :" + e.toString()); 
	    }
	}

	public void close()
	{
		try
        {
            if(conn != null)
            	conn.close();
            System.out.println("Connection closed !!");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
	}
	
	public ConnMysql() 
	{
		System.out.println("-------- MySQL JDBC Connection Demo ------------");
	    try
	    {
	        Class.forName("com.mysql.jdbc.Driver");
	    } 
	    catch (ClassNotFoundException e) 
	    {
	        System.out.println("MySQL JDBC Driver not found !!");
	        return;
	    }
	    
	    System.out.println("MySQL JDBC Driver Registered!");
	}
}

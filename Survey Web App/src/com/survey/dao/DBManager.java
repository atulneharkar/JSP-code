package com.survey.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.log4j.Logger;


import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ import org.apache.log4j.Logger;
import oracle.ucp.jdbc.PoolDataSource;
public class DBManager {

	static final Logger logger = Logger.getLogger(DBManager.class);
	private  final String className = "DBHandler";
	private  String functionName = "";
	// Get the DB connection
	private  String strMethodName = null;
	public static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	
	
	
	public Connection getConnection() throws Exception {
		functionName = "getConnection";
		logger.info("In " + className + " - " + functionName + " ");
		strMethodName = "getConnection";
        
		Connection con = null;
		
	    String url , username ,  password = "";
	    System.out.println("Before try");   
		    try{
		    	
		    Class.forName(DRIVER);
		    String propFileName = "DBConnection.properties";
		    logger.info("==============Fetching data for DBHandler====================");
		    System.out.println("==============Fetching data for DBHandler====================");
		    Properties prop = new Properties();
		    prop.load(DBManager.class.getClassLoader().getResourceAsStream(propFileName));
		    
		    url = prop.getProperty("score.url");
		    username = prop.getProperty("score.username");
		    password = prop.getProperty("score.password");
		    System.out.println("URL "+url + " username "+username+ " password "+password);   
		    logger.info("URL "+url + " username "+username+ " password "+password);
		    con = DriverManager.getConnection(url, username, password);
		    logger.info("Connection object is null here");
		   } 
		   catch(IOException io)
		   {
		  	 io.printStackTrace();
		  	logger.error("In " + className + " - " + functionName + " "
					+ io.getMessage());
		   }
		   catch (SQLException e) {
			logger.error("In " + className + " - " + functionName + " "
					+ e.getMessage());
	       } 
		return con;
	}

	public  void closeConnection(Connection conn) throws Exception {
		functionName = "closeConnection";
		logger.info("In " + className + " - " + functionName + " ");
		strMethodName = "closeConnection";

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("In " + className + " - " + functionName + " "
					+ e.getMessage());
		}

	}

	public  void closeStatement(Statement stmt) throws Exception {
		functionName = "closeStatement";
		logger.info("In " + className + " - " + functionName + " ");
		strMethodName = "closeStatement";

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			logger.error("In " + className + " - " + functionName + " "
					+ e.getMessage());
		}

	}

}

package com.madridmobility.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.madridmobility.Entities.StreetsNumberEntity;
import com.madridmobility.beans.StreetsNumberBean;
import com.madridmobility.dao.IStreetsNumberDAOImpl;
@Component("StreetsNumberDAOImpl")
public class StreetsNumberDAOImpl implements IStreetsNumberDAOImpl {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<StreetsNumberEntity> findAll() {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT stn FROM StreetsNumberEntity stn ");

		Query q = em.createQuery(sb.toString());

		//q.setParameter("email", user.getEmail());
		
		List<StreetsNumberEntity> userFound = new ArrayList<StreetsNumberEntity>();

		try {
			 userFound = (List<StreetsNumberEntity>) q.getResultList();
			 
			 for(StreetsNumberEntity user:userFound) {
				 //saveStreetsNumber(user);
			 }
			 
		}catch(NoResultException nre){
		}
		return null;
}
	void saveStreetsNumber(StreetsNumberEntity streetsNumberEntity) {
		List<StreetsNumberBean> streetsNumberBeanList= new ArrayList<StreetsNumberBean>();
		  String JDBC_DRIVER = "org.postgresql.Driver";  
		   String DB_URL = "jdbc:postgresql://ec2-3-222-30-53.compute-1.amazonaws.com/dd9338c1e0p4ud";

		   //  Database credentials
		    String USER = "pxhufrkggazdad";
		   String PASS = "5952deaf86e1a95965c1d4648df312f16b30ab040a041e3ede885613ef3e6a3c";
		   
		   Connection conn = null;
		   
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      //stmt = conn.createStatement();
		      String sql;
		      sql = "INSERT INTO streets_number( street_name, street_number) " + 
		      		"VALUES" + 
		      		"	(?,?) ";
		      
		      PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.NO_GENERATED_KEYS);
		      
		      stmt.setString(1, streetsNumberEntity.getStreetName());
		      stmt.setString(2, streetsNumberEntity.getStreetNumber());
		      
		      ResultSet rs = stmt.executeQuery();

		      
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		      try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		      try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   }finally{
		      if(conn !=null) {
		    	  
		      }
		   }//end try
		   //return streetsNumberBeanList;
	}
}

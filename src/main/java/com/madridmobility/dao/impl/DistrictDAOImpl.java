package com.madridmobility.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.madridmobility.beans.DistrictBean;
import com.madridmobility.dao.IDistrictDAO;

@Component("DistrictDAOImpl")
public class DistrictDAOImpl implements IDistrictDAO {

	@Override
	public List<DistrictBean> getDistrictsWithTotalBikes() throws SQLException{ 
		 List<DistrictBean> districtBeanList = new ArrayList<>();
		 
		 //PreparedStatement pstBuscarCodigo = null;
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
		      sql = " SELECT distritos.id, distritos.nombre as disname, SUM(bike_stations.available_bikes) as sumb  " + 
		      		"FROM bike_stations, distritos  " + 
		      		"WHERE ST_Contains(geom, bike_stations.coordinates)  " +
		      		"GROUP BY distritos.nombre,distritos.id; ";
		      
		      
		      PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.NO_GENERATED_KEYS);
		      
		      
		      ResultSet rs = stmt.executeQuery();
		      
		     
		      
		      if(rs != null) {
		    	   while(rs.next()) {
		    		   
		    		   DistrictBean districtBean = new DistrictBean();
		    		 
		    		   districtBean.setTotalBikes(rs.getLong("sumb"));
		    		   districtBean.setName(rs.getString("disname"));
		    		   districtBeanList.add(districtBean);
		    	   }
		      }

		      
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
		    	  conn.close();
		      }
		   }//end try
		   return districtBeanList;
	}
}

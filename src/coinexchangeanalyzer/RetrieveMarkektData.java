/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*

  Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es/gc200271677", "gc200271677", "Y-xX3iij");
        Statement statement = con.createStatement();

*/
package coinexchangeanalyzer;
import java.util.ArrayList;
import  java.sql.ResultSet; 
/**
 *
 * @author Oleg
 */
// STEP 1: Import required packages
import java.sql.*;
import java.util.*;
/**
 *
 * @author Oleg
 */
public class RetrieveMarkektData {
  

// JDBC driver name and database URL
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
static final String DB_URL = "jdbc:mysql://localhost/";
// Database credentials
static final String USER = "root";
static final String PASS = "root";
public String[] result;


public String[]  Connect() {
    
   Connection conn = null;
    Statement stmt = null;
    Scanner scn = new Scanner(System.in);
    String course_code = null, course_desc = null, course_chair = null;

    try {
        // STEP 2: Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // STEP 3: Open a connection
        System.out.print("\nConnecting to database...");
        conn = DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es/gc200271677", "gc200271677", "Y-xX3iij");
        System.out.println(" SUCCESS!\n");

        // STEP 5: Excute query
        System.out.print("\nInserting records into table...");
        stmt = conn.createStatement();

        String sql = "select (candleHigh + candleLow)/2.0 as Mid from candleInfo order by unixTime desc  limit 1000";
        //candleHigh //candleLow
        ResultSet rs = stmt.executeQuery(sql);
        
        ArrayList<String> list= new ArrayList<String>();
       //=new double[1000]; 

        while (rs.next()) {
        
        list.add(rs.getString("Mid"));

            result = new String[list.size()];

            result = list.toArray(result);
            //this.result2 = new double[result.length];
            
            //System.out.println("DoIt" + result.length);
            for(int i =0; i<result.length; i++){
               ;//System.out.println(result[i]) ;//this.result2[i] = Double.parseDouble(result[i]);
            }   
        }   
        
    

        
        System.out.println(result.length + " SUCCESS!\n");
        //return result2;
        return result;
        
    } catch(SQLException se) {
        se.printStackTrace();
    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if(stmt != null)
                conn.close();
        } catch(SQLException se) {
        }
        try {
            if(conn != null)
                conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        }
    }
    System.out.println("Thank you for your patronage!");

    return result;
  }
}
    

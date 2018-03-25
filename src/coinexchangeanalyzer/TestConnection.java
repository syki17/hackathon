/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

class TestConnection {
// JDBC driver name and database URL
/*static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
static final String DB_URL = "jdbc:mysql://localhost/";

// Database credentials
static final String USER = "root";
static final String PASS = "root";
*/
public double targetPosition() {
    
    
   // double[] data = new double[1000];
   
    
   // for (int i=0;i<data.length;i++)
   //     {
   //           data[i] = i; 
   //     }
    
    //data[99]=100;
    TradingBasket trdBasket = new TradingBasket();
    //Trading_SMA strategy = new Trading_SMA(30,0.01); 
    //Trading_LinearRegression strategy1 = new Trading_LinearRegression(100,10,-10);
    
    Trading_RSI strategy1 = new Trading_RSI(100, 30, 70);
    Trading_SMA strategy2 = new Trading_SMA(100,.010);
    Trading_LinearRegression strategy3 = new Trading_LinearRegression(100,5,-5);
   //  strategy2.backTest(data);
   //  strategy1.backTest(data);
     
    trdBasket.addStrategy(strategy1);
    trdBasket.addStrategy(strategy2);
     trdBasket.addStrategy(strategy3);
    trdBasket.setBounds(1.5,-1.5);
    

    
    
    RetrieveMarkektData c = new RetrieveMarkektData();
     c.Connect();
    System.out.println("Result is" + (c.result).length);
     double[] data = new double[(c.result).length];
     
     for (int i = 0;i<data.length;i++)
     {
     data[data.length-i-1]= Double.parseDouble(c.result[i]);
     }
     
    return trdBasket.getTradingSignal(data);    

    //System.out.println(strategy.evaluate(data));
    
    
 /*   Connection conn = null;
    Statement stmt = null;
    Scanner scn = new Scanner(System.in);
    String course_code = null, course_desc = null, course_chair = null;

    try {
        // STEP 2: Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // STEP 3: Open a connection
        System.out.print("\nConnecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println(" SUCCESS!\n");

        // STEP 5: Excute query
        System.out.print("\nInserting records into table...");
        stmt = conn.createStatement();

        String sql = "select * from people.people_users";
        
        ResultSet rs = stmt.executeQuery(sql);
        
        ArrayList<String> list= new ArrayList<String>();
        
        while (rs.next()) {
            list.add(rs.getString("people_id"));

            String[] result = new String[list.size()];
            result = list.toArray(result);

            for(int i =0; i<result.length; i++){
                System.out.println(result[i]);
            }   
        }   
        System.out.println(" SUCCESS!\n");

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
*/

  }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coinexchangeanalyzer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Jakub
 */
public class CoinExchangeAnalyzer {

    /**
     * @param args the command line arguments
     */
  
     static long starttime11 = System.currentTimeMillis() / 1000L;
    public static void main(String[] args) throws UnirestException, InterruptedException, IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeyException {
        CoinExchangeAnalyzer getCoin = new CoinExchangeAnalyzer();
        getCoin.run();
       

    }

    public long getStartlongtime12() {
        return starttime11;
    }

    public void setStartlongtime12(long startlongtime12) {
        this.starttime11 = startlongtime12;
    }


    private void makeOrder(double f) throws NoSuchAlgorithmException, InvalidKeyException, UnirestException{
        String t;
        Charset asci;
        Mac sha256_HMAC;
        SecretKeySpec key;
        byte[] mac_data;
        int returnedValue;
        returnedValue = (int)f;

        switch (returnedValue){
                case 1 :
                    long unixTime = System.currentTimeMillis() / 1000L;

                    t = "symbol=BTCUSDT&side=BUY&type=MARKET&quantity=0.0001&" +"&recvWindow=5000&timestamp=" + unixTime;
                    asci  = Charset.forName("US-ASCII");
                    sha256_HMAC = Mac.getInstance("HmacSHA256");
                    key = new javax.crypto.spec.SecretKeySpec(asci.encode("77rLftGrqOVPplMr1SBZswdFqjaQLk7mpuADYQB0a894mZ5YZb92pVq5NWEIxl67").array(), "HmacSHA256");
                    sha256_HMAC.init(key);
                    mac_data = sha256_HMAC.doFinal(asci.encode(t).array());
                    String resultkey = "";
                    for ( byte element : mac_data)
                    {
                        resultkey += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
                    }
                    System.out.println("Result:[" + resultkey + "]");
                    System.out.println("+ Position");
                    break;
                case 0 : System.out.println("Nothing");
break;
                case -1 :
                    unixTime = System.currentTimeMillis();
                    t = "?symbol=BTCUSDT&side=SELL&type=LIMIT&timeInForce=GTC&quantity=0.0001&price=8700.00&recvWindow=15000&timestamp=" + unixTime;
                    asci  = Charset.forName("UTF-8");
                    sha256_HMAC = Mac.getInstance("HmacSHA256");
                    key = new javax.crypto.spec.SecretKeySpec(asci.encode("E7485SFfTgk5zV0aEH0apb7GtUawC22fkuINHeCqBq3LytXt4XrtE0VnIWVS0Ywy").array(), "HmacSHA256");
                    sha256_HMAC.init(key);
                    mac_data = sha256_HMAC.doFinal(asci.encode(t).array());
                    resultkey = "";
                    for ( byte element : mac_data)
                    {
                        resultkey += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
                    }
                    System.out.println("- Position");
                  //  System.out.println(t+"&signature="+resultkey);
                    
                    
                    break;
                    
                default:
                    break;
         }
    }
    

    
   private String putCoins(String resultkey1) throws UnirestException {
        Unirest.setTimeouts(240000, 600000);
        String urlpost = "https://api.binance.com/api/v3/order"+resultkey1;
        System.out.println(urlpost);
        HttpResponse<String> response = Unirest.post("https://api.binance.com/api/v3/order"+resultkey1)
                .header("X-MBX-APIKEY","OtGxpUB0TCIZ8bMnp6zy1IdV8HHskEMSnJpf9fQTB2IF0um0zWfmFuJERBxrCWwy")
            .header("cache-control", "no-cache")    
                .asString();
        return response.getBody();
    } 
   
    
    private String getCoins() throws UnirestException {
        Unirest.setTimeouts(240000, 600000);
        HttpResponse<String> response = Unirest.get("https://api.binance.com/api/v1/klines?symbol=BTCUSDT&interval=1m")
                .header("cache-control", "no-cache")
                .asString();
        return response.getBody();
    }
    int count = 0;

 
    private void run() throws UnirestException, InterruptedException, IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeyException {

        /*
        String t = "symbol=BTCUSDT&side=BUY&type=LIMIT&timeInForce=GTC&quantity=1&price=0.1&recvWindow=5000&timestamp=1521853789";     
        Charset asci  = Charset.forName("US-ASCII");
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(asci.encode("77rLftGrqOVPplMr1SBZswdFqjaQLk7mpuADYQB0a894mZ5YZb92pVq5NWEIxl67").array(), "HmacSHA256");
          sha256_HMAC.init(key);
        byte[] mac_data = sha256_HMAC.doFinal(asci.encode(t).array());
                String resultkey = "";
        for ( byte element : mac_data)
        {
           resultkey += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
        }
        System.out.println("Result:[" + resultkey + "]");
        
        
         */
        while (true){
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es/gc200271677", "gc200271677", "Y-xX3iij");
        Statement statement = con.createStatement();
        count = 0;
        String[] splitter = getCoins().split("\\],");
           String sqlDrop = "DELETE FROM candleInfo";
            statement.executeUpdate(sqlDrop);
         
        for (String g : splitter) {
            String g1 = g.replaceAll("\\[", "");
            String g2 = g1.replaceAll("\\]", "");
            String g3 = g2.replaceAll("\"", "");

          
            String[] g4 = g3.split(",");
            

           // System.out.println(g4[0]);

            double starttime = parseDouble(g4[0]);
            long startlong = (long) starttime;
            double closetime = parseDouble(g4[6]);
            long closelong = (long) closetime;
            
            
           String sql = "INSERT INTO candleInfo VALUES('" + startlong + "','" + parseDouble(g4[1]) + "','" + parseDouble(g4[2]) + "','" + parseDouble(g4[3]) + "','" + parseDouble(g4[4]) + "','" + parseDouble(g4[5]) + "','" + closelong + "','" + parseDouble(g4[7]) + "','" + parseDouble(g4[8]) + "','" + parseDouble(g4[9]) + "','" + parseDouble(g4[10]) + "','" + parseDouble(g4[11]) + "')";
           statement.executeUpdate(sql);
            
        }
         TestConnection newConnection = new TestConnection();
         makeOrder(newConnection.targetPosition());//newConnection.targetPosition());
         Thread.sleep(60000);
       // String finalDecision = a.targetPosition();
        
        // ResultSummary summary = gson.fromJson(getSummary(), ResultSummary.class);

        //    for (Summary coinSummary : summary.getSummary()) {
        //      if (coinSummary.getMarketID().equals(coin.getMarketID())) {
        //   String sql = "INSERT INTO coinsummary VALUES('" + coin.getMarketAssetName() + "','" + coin.getBaseCurrency() + "','" + coinSummary.getLastPrice() + "','" + coinSummary.getChange() + "','" + coinSummary.getHighPrice() + "','" + coinSummary.getLowPrice() + "','" + coinSummary.getVolume() + "','" + coinSummary.getBTCVolume() + "','" + coinSummary.getTradeCount() + "','" + coinSummary.getBidPrice() + "','" + coinSummary.getAskPrice() + "','" + coinSummary.getBuyOrderCount() + "','" + coinSummary.getSellOrderCount() + "','" + timeStamp + "')";
        //   statement.executeUpdate(sql);
    }
}
}

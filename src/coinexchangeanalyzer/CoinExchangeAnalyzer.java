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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Arrays;
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
    testreturn t = new testreturn();
    long  startlongtime12 = 1521804898;
    public static void main(String[] args) throws UnirestException, InterruptedException, IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException, InvalidKeyException {
        CoinExchangeAnalyzer getCoin = new CoinExchangeAnalyzer();
        getCoin.run();

    }

    public long getStartlongtime12() {
        return startlongtime12;
    }

    public void setStartlongtime12(long startlongtime12) {
        this.startlongtime12 = startlongtime12;
    }

    
    
    private String getCoins(long starttime11) throws UnirestException {
        Unirest.setTimeouts(240000, 600000);
        HttpResponse<String> response = Unirest.get("https://api.binance.com/api/v1/klines?symbol=BTCUSDT&interval=5m&startTime="+starttime11)
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
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coins", "root", "syk0k0t");
        Statement statement = con.createStatement();
         */
    //    while (true){
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://sql.computerstudi.es/gc200271677", "gc200271677", "Y-xX3iij");
        Statement statement = con.createStatement();
        count = 0;
        String[] splitter = getCoins(getStartlongtime12()).split("\\],");
           
         setStartlongtime12(startlongtime12 -= 299700000);
        for (String g : splitter) {
            String g1 = g.replaceAll("\\[", "");
            String g2 = g1.replaceAll("\\]", "");
            String g3 = g2.replaceAll("\"", "");

          
            String[] g4 = g3.split(",");
            

            System.out.println(g4[0]);

            double starttime = parseDouble(g4[0]);
            long startlong = (long) starttime;
            double closetime = parseDouble(g4[6]);
            long closelong = (long) closetime;

            String sql = "INSERT INTO candleInfo VALUES('" + startlong + "','" + parseDouble(g4[1]) + "','" + parseDouble(g4[2]) + "','" + parseDouble(g4[3]) + "','" + parseDouble(g4[4]) + "','" + parseDouble(g4[5]) + "','" + closelong + "','" + parseDouble(g4[7]) + "','" + parseDouble(g4[8]) + "','" + parseDouble(g4[9]) + "','" + parseDouble(g4[10]) + "','" + parseDouble(g4[11]) + "')";
            statement.executeUpdate(sql);

        }
        // ResultSummary summary = gson.fromJson(getSummary(), ResultSummary.class);

        //    for (Summary coinSummary : summary.getSummary()) {
        //      if (coinSummary.getMarketID().equals(coin.getMarketID())) {
        //   String sql = "INSERT INTO coinsummary VALUES('" + coin.getMarketAssetName() + "','" + coin.getBaseCurrency() + "','" + coinSummary.getLastPrice() + "','" + coinSummary.getChange() + "','" + coinSummary.getHighPrice() + "','" + coinSummary.getLowPrice() + "','" + coinSummary.getVolume() + "','" + coinSummary.getBTCVolume() + "','" + coinSummary.getTradeCount() + "','" + coinSummary.getBidPrice() + "','" + coinSummary.getAskPrice() + "','" + coinSummary.getBuyOrderCount() + "','" + coinSummary.getSellOrderCount() + "','" + timeStamp + "')";
        //   statement.executeUpdate(sql);
   // }
}
}

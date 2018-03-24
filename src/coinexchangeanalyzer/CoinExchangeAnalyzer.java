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
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Jakub
 */
public class CoinExchangeAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnirestException, InterruptedException, IOException, ClassNotFoundException, SQLException {
        CoinExchangeAnalyzer getCoin = new CoinExchangeAnalyzer();
        getCoin.run();
    }

    private String getCoins() throws UnirestException {
        Unirest.setTimeouts(240000, 600000);
        HttpResponse<String> response = Unirest.get("https://www.coinexchange.io/api/v1/getmarkets")
                
                .header("cache-control", "no-cache")
                .asString();
        return response.getBody();
    }

    private String getSummary() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://www.coinexchange.io/api/v1/getmarketsummaries")
                .header("cache-control", "no-cache")
                .asString();
        return response.getBody();
    }
    int count = 0;

    private void run() throws UnirestException, InterruptedException, IOException, ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coins", "root", "syk0k0t");

        Statement statement = con.createStatement();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = new Gson();
        gson = builder.serializeNulls().create();
        while(true){
            
        Result result = gson.fromJson(getCoins(), Result.class);
        Thread.sleep(1500);
        ResultSummary summary = gson.fromJson(getSummary(), ResultSummary.class);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        
        for (ResultCoins coin : result.getResult()) {
            for (Summary coinSummary : summary.getSummary()) {
                if (coinSummary.getMarketID().equals(coin.getMarketID())) {
                       System.out.println(coin.getMarketAssetName() );
                    String sql = "INSERT INTO coinsummary VALUES('" + coin.getMarketAssetName() + "','" + coin.getBaseCurrency() + "','" + coinSummary.getLastPrice() + "','" + coinSummary.getChange() + "','" + coinSummary.getHighPrice() + "','" + coinSummary.getLowPrice() + "','" + coinSummary.getVolume() + "','" + coinSummary.getBTCVolume() + "','" + coinSummary.getTradeCount() + "','" + coinSummary.getBidPrice() + "','" + coinSummary.getAskPrice() + "','" + coinSummary.getBuyOrderCount() + "','" + coinSummary.getSellOrderCount() + "','" + timeStamp + "')";
                    statement.executeUpdate(sql);
                   
                }
            }
        }
        
        }
    }

}

package coinexchangeanalyzer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/*
this creates a object from the getSummary() Method. Most of these values are in a 24h timeframe
*/
public class Summary {

@SerializedName("MarketID")
@Expose
private String marketID;
@SerializedName("LastPrice")
@Expose
private String lastPrice;
@SerializedName("Change")
@Expose
private String change;
@SerializedName("HighPrice")
@Expose
private String highPrice;
@SerializedName("LowPrice")
@Expose
private String lowPrice;
@SerializedName("Volume")
@Expose
private String volume;
@SerializedName("BTCVolume")
@Expose
private String bTCVolume;
@SerializedName("TradeCount")
@Expose
private String tradeCount;
@SerializedName("BidPrice")
@Expose
private String bidPrice;
@SerializedName("AskPrice")
@Expose
private String askPrice;
@SerializedName("BuyOrderCount")
@Expose
private String buyOrderCount;
@SerializedName("SellOrderCount")
@Expose
private String sellOrderCount;

/**
* No args constructor for use in serialization
* 
*/
public Summary() {
}

/**
* 
* @param bidPrice
* @param marketID
* @param tradeCount
* @param change
* @param volume
* @param buyOrderCount
* @param lastPrice
* @param bTCVolume
* @param sellOrderCount
* @param lowPrice
* @param highPrice
* @param askPrice
*/
public Summary(String marketID, String lastPrice, String change, String highPrice, String lowPrice, String volume, String bTCVolume, String tradeCount, String bidPrice, String askPrice, String buyOrderCount, String sellOrderCount) {
super();
this.marketID = marketID;
this.lastPrice = lastPrice;
this.change = change;
this.highPrice = highPrice;
this.lowPrice = lowPrice;
this.volume = volume;
this.bTCVolume = bTCVolume;
this.tradeCount = tradeCount;
this.bidPrice = bidPrice;
this.askPrice = askPrice;
this.buyOrderCount = buyOrderCount;
this.sellOrderCount = sellOrderCount;
}

public String getMarketID() {
return marketID;
}

public void setMarketID(String marketID) {
this.marketID = marketID;
}

public String getLastPrice() {
return lastPrice;
}

public void setLastPrice(String lastPrice) {
this.lastPrice = lastPrice;
}
/**
 * Returns 24h change in price as %
 * @return 
 */
public String getChange() {
return change;
}

public void setChange(String change) {
this.change = change;
}
/**
 * Return 24h high price
 * @return 
 */
public String getHighPrice() {
return highPrice;
}

public void setHighPrice(String highPrice) {
this.highPrice = highPrice;
}
/**
 * Returns 24h low price 
 * @return 
 */
public String getLowPrice() {
return lowPrice;
}

public void setLowPrice(String lowPrice) {
this.lowPrice = lowPrice;
}

public String getVolume() {
return volume;
}

public void setVolume(String volume) {
this.volume = volume;
}

public String getBTCVolume() {
return bTCVolume;
}

public void setBTCVolume(String bTCVolume) {
this.bTCVolume = bTCVolume;
}

public String getTradeCount() {
return tradeCount;
}

public void setTradeCount(String tradeCount) {
this.tradeCount = tradeCount;
}

public String getBidPrice() {
return bidPrice;
}

public void setBidPrice(String bidPrice) {
this.bidPrice = bidPrice;
}

public String getAskPrice() {
return askPrice;
}

public void setAskPrice(String askPrice) {
this.askPrice = askPrice;
}

public String getBuyOrderCount() {
return buyOrderCount;
}

public void setBuyOrderCount(String buyOrderCount) {
this.buyOrderCount = buyOrderCount;
}

public String getSellOrderCount() {
return sellOrderCount;
}

public void setSellOrderCount(String sellOrderCount) {
this.sellOrderCount = sellOrderCount;
}

}
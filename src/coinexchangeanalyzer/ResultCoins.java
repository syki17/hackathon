package coinexchangeanalyzer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/*
this creates an object from the getCoins() Method
*/
public class ResultCoins {

@SerializedName("MarketID")
@Expose
private String marketID;
@SerializedName("MarketAssetName")
@Expose
private String marketAssetName;
@SerializedName("MarketAssetCode")
@Expose
private String marketAssetCode;
@SerializedName("MarketAssetID")
@Expose
private String marketAssetID;
@SerializedName("MarketAssetType")
@Expose
private String marketAssetType;
@SerializedName("BaseCurrency")
@Expose
private String baseCurrency;
@SerializedName("BaseCurrencyCode")
@Expose
private String baseCurrencyCode;
@SerializedName("BaseCurrencyID")
@Expose
private String baseCurrencyID;
@SerializedName("Active")
@Expose
private Boolean active;

/**
* No args constructor for use in serialization
* 
*/
public ResultCoins() {
}

/**
* 
* @param marketAssetID
* @param marketID
* @param marketAssetName
* @param active
* @param baseCurrency
* @param marketAssetType
* @param baseCurrencyCode
* @param baseCurrencyID
* @param marketAssetCode
*/
public ResultCoins(String marketID, String marketAssetName, String marketAssetCode, String marketAssetID, String marketAssetType, String baseCurrency, String baseCurrencyCode, String baseCurrencyID, Boolean active) {
super();
this.marketID = marketID;
this.marketAssetName = marketAssetName;
this.marketAssetCode = marketAssetCode;
this.marketAssetID = marketAssetID;
this.marketAssetType = marketAssetType;
this.baseCurrency = baseCurrency;
this.baseCurrencyCode = baseCurrencyCode;
this.baseCurrencyID = baseCurrencyID;
this.active = active;
}

public String getMarketID() {
return marketID;
}

public void setMarketID(String marketID) {
this.marketID = marketID;
}

public String getMarketAssetName() {
return marketAssetName;
}

public void setMarketAssetName(String marketAssetName) {
this.marketAssetName = marketAssetName;
}

public String getMarketAssetCode() {
return marketAssetCode;
}

public void setMarketAssetCode(String marketAssetCode) {
this.marketAssetCode = marketAssetCode;
}

public String getMarketAssetID() {
return marketAssetID;
}

public void setMarketAssetID(String marketAssetID) {
this.marketAssetID = marketAssetID;
}

public String getMarketAssetType() {
return marketAssetType;
}

public void setMarketAssetType(String marketAssetType) {
this.marketAssetType = marketAssetType;
}

public String getBaseCurrency() {
return baseCurrency;
}

public void setBaseCurrency(String baseCurrency) {
this.baseCurrency = baseCurrency;
}

public String getBaseCurrencyCode() {
return baseCurrencyCode;
}

public void setBaseCurrencyCode(String baseCurrencyCode) {
this.baseCurrencyCode = baseCurrencyCode;
}

public String getBaseCurrencyID() {
return baseCurrencyID;
}

public void setBaseCurrencyID(String baseCurrencyID) {
this.baseCurrencyID = baseCurrencyID;
}

public Boolean getActive() {
return active;
}

public void setActive(Boolean active) {
this.active = active;
}

}

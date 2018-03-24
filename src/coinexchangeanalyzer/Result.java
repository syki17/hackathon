package coinexchangeanalyzer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("success")
@Expose
private String success;
@SerializedName("request")
@Expose
private String request;
@SerializedName("message")
@Expose
private String message;
@SerializedName("result")
@Expose
private List<ResultCoins> result = null;

/**
* No args constructor for use in serialization
* 
*/
public Result() {
}

/**
* 
* @param message
* @param result
* @param request
* @param success
*/
public Result(String success, String request, String message, List<ResultCoins> result) {
super();
this.success = success;
this.request = request;
this.message = message;
this.result = result;
}

public String getSuccess() {
return success;
}

public void setSuccess(String success) {
this.success = success;
}

public String getRequest() {
return request;
}

public void setRequest(String request) {
this.request = request;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public List<ResultCoins> getResult() {
return result;
}

public void setResult(List<ResultCoins> result) {
this.result = result;
}

}
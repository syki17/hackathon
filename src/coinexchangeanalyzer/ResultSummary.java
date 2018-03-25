package coinexchangeanalyzer;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultSummary {

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
private List<Summary> summary = null;
/**
* No args constructor for use in serialization
* 
*/
public ResultSummary() {
}
/**
* 
* @param message
* @param result
* @param request
* @param success
*/
public ResultSummary(String success, String request, String message, List<Summary> summary) {
super();
this.success = success;
this.request = request;
this.message = message;
this.summary = summary;
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

public List<Summary> getSummary() {
return summary;
}

public void setSummary(List<Summary> summary) {
this.summary = summary;
}

}
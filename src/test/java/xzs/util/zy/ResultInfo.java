package xzs.util.zy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)   
public class ResultInfo {
	String resultdata;
	RemarkInfo remark;
	String resultmsg;
	String resultcode;
	public String getResultdata() {
		return resultdata;
	}
	public void setResultdata(String resultdata) {
		this.resultdata = resultdata;
	}
	public RemarkInfo getRemark() {
		return remark;
	}
	public void setRemark(RemarkInfo remark) {
		this.remark = remark;
	}
	public String getResultmsg() {
		return resultmsg;
	}
	public void setResultmsg(String resultmsg) {
		this.resultmsg = resultmsg;
	}
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	
}

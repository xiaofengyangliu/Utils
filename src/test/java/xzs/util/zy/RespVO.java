package xzs.util.zy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)   
public class RespVO {
	String jobid;
	String bizno;
	String requestsn;
	String responsetime;
	String consumestate;
	String errcode;
	String errmsg;
	ResultInfo result;
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String getBizno() {
		return bizno;
	}
	public void setBizno(String bizno) {
		this.bizno = bizno;
	}
	public String getRequestsn() {
		return requestsn;
	}
	public void setRequestsn(String requestsn) {
		this.requestsn = requestsn;
	}
	public String getResponsetime() {
		return responsetime;
	}
	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}
	public String getConsumestate() {
		return consumestate;
	}
	public void setConsumestate(String consumestate) {
		this.consumestate = consumestate;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public ResultInfo getResult() {
		return result;
	}
	public void setResult(ResultInfo result) {
		this.result = result;
	}
}

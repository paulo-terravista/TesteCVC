package br.com.terravista.teste.cvc.exception;

import java.util.Calendar;
import java.util.Date;

public class ErrorDetail {
	
	  private int code;
	  private Date timestamp;
	  private String message;
	  private String details;
	  
	  public ErrorDetail() {
		  super();
	  }

	  public ErrorDetail(int code, String message, String details) {
	    super();
	    this.code = code;
	    this.timestamp = Calendar.getInstance().getTime();
	    this.message = message;
	    this.details = details;
	  }

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}

package com.netblizzard.httpclient;

import java.util.Date;

public class SearchResponse {
	private Date logTime;
	private String entity;
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
}

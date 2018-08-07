package com.niit.core.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DataResult implements Serializable {
	
	private String status;
	private Integer tip;
	private Map<String, Object> map;
	
	
	
	public DataResult() {
		super();
	}



	public DataResult(String status, Integer tip, Map<String, Object> map) {
		super();
		this.status = status;
		this.tip = tip;
		this.map = map;
	}
	
	

	public DataResult(String status, Integer tip) {
		super();
		this.status = status;
		this.tip = tip;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTip() {
		return tip;
	}
	
	public void setTip(Integer tip) {
		this.tip = tip;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
	
	
}

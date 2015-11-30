package com.aquest.emailmarketing.web.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cm_bounce_codes")
public class BounceCode {
	@Id 
    @GeneratedValue
    @Column(name = "id")
    private long id;
	private String code;
	private String type;
	private String explanation;
	
	public BounceCode() {
		
	}
	
	public BounceCode(long id, String code, String type, String explanation) {
		super();
		this.id = id;
		this.code = code;
		this.type = type;
		this.explanation = explanation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Override
	public String toString() {
		return "BounceCode [id=" + id + ", code=" + code + ", type=" + type + ", explanation=" + explanation + "]";
	}
}

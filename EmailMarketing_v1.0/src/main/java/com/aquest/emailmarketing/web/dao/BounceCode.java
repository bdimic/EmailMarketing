package com.aquest.emailmarketing.web.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class BounceCode.
 */
@Entity
@Table(name="cm_bounce_codes")
public class BounceCode {
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
    private long id;
	
	/** The code. */
	private String code;
	
	/** The type. */
	private String type;
	
	/** The explanation. */
	private String explanation;
	
	/**
	 * Instantiates a new bounce code.
	 */
	public BounceCode() {
		
	}
	
	/**
	 * Instantiates a new bounce code.
	 *
	 * @param id the id
	 * @param code the code
	 * @param type the type
	 * @param explanation the explanation
	 */
	public BounceCode(long id, String code, String type, String explanation) {
		super();
		this.id = id;
		this.code = code;
		this.type = type;
		this.explanation = explanation;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the explanation.
	 *
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * Sets the explanation.
	 *
	 * @param explanation the new explanation
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BounceCode [id=" + id + ", code=" + code + ", type=" + type + ", explanation=" + explanation + "]";
	}
}

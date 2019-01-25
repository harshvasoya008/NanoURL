package com.nanourl.models;

/*
 * Report Schema
 *
 * Author: Harsh Vasoya 
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nanourl.helpers.Constants;

@Entity
@Table(name = "reports", schema = Constants.DB_SCHEMA_NAME)
public class Report {

	/* Fields */
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonIgnore
	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "num_short_url_created")
	private long numShortUrlCreated;

	@Column(name = "num_long_url_added")
	private long numLongUrlAdded;

	@Column(name = "num_unique_clicks")
	private long numUniqueClicks;

	@Column(name = "num_total_clicks")
	private long numTotalClicks;

	/* Operation based time assignment */
	@PrePersist
	public void beforeCreation() {
		createdAt = new Date();
	}

	/* Getter and Setter Methods */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public long getNumShortUrlCreated() {
		return numShortUrlCreated;
	}

	public void setNumShortUrlCreated(long numShortUrlCreated) {
		this.numShortUrlCreated = numShortUrlCreated;
	}

	public long getNumLongUrlAdded() {
		return numLongUrlAdded;
	}

	public void setNumLongUrlAdded(long numLongUrlAdded) {
		this.numLongUrlAdded = numLongUrlAdded;
	}

	public long getNumUniqueClicks() {
		return numUniqueClicks;
	}

	public void setNumUniqueClicks(long numUniqueClicks) {
		this.numUniqueClicks = numUniqueClicks;
	}

	public long getNumTotalClicks() {
		return numTotalClicks;
	}

	public void setNumTotalClicks(long numTotalClicks) {
		this.numTotalClicks = numTotalClicks;
	}

}

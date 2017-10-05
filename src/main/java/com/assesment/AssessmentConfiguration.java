package com.assesment;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class AssessmentConfiguration extends Configuration{
	@JsonProperty("couchbase.nodes")
	private String[] couchbaseNodes;

	@JsonProperty("couchbase.bucket")
	private String couchbaseBucket;

	@JsonProperty("couchbase.password")
	private String couchbasePassword;
	
	@JsonProperty("thirdpart.uri")
	private String uri;

	public String[] getCouchbaseNodes() {
		return couchbaseNodes;
	}

	public String getCouchbaseBucket() {
		return couchbaseBucket;
	}

	public String getCouchbasePassword() {
		return couchbasePassword;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}

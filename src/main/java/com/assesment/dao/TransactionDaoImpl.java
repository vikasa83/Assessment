package com.assesment.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.assesment.AssessmentConfiguration;
import com.assesment.pojo.Transaction;
import com.assesment.pojo.TransactionData;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionDaoImpl implements TransactionDao {

	private AssessmentConfiguration configuration;
	private Bucket bucket;
	private CouchbaseCluster cluster;
	private ObjectMapper objectMapper;

	@Inject
	public TransactionDaoImpl(@Named("configuration") AssessmentConfiguration configuration,
			@Named("jsonmapper") ObjectMapper objectMapper) throws Exception {
		this.configuration = configuration;
		this.objectMapper = objectMapper;
		cluster = CouchbaseCluster.create(configuration.getCouchbaseNodes());
		bucket = cluster.openBucket(configuration.getCouchbaseBucket());
	}
	
	public TransactionDaoImpl() {
	}

	public AssessmentConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(AssessmentConfiguration configuration) {
		this.configuration = configuration;
	}

	public Bucket getBucket() {
		return bucket;
	}

	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setCluster(CouchbaseCluster cluster) {
		this.cluster = cluster;
	}

	@Override
	public List<Transaction> getTransaction(String accountNumber, String sortCode) throws Exception {
		String transactionKey = "TRANSACTION_" + accountNumber;
		JsonDocument transactionDocument = bucket.get(transactionKey);
		if (transactionDocument != null ) {
			JsonObject jsonObject = transactionDocument.content();
			String jsonString = jsonObject.toString();
			TransactionData transactionData = objectMapper.readValue(jsonString, TransactionData.class);
			return transactionData.getTransactions();
		}
		return null;
	}

}

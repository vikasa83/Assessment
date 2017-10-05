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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionDaoImpl implements TransactionDao {

	private AssessmentConfiguration configuration;
	private final Bucket bucket;
	private final CouchbaseCluster cluster;
	private final ObjectMapper objectMapper;

	@Inject
	public TransactionDaoImpl(@Named("configuration") AssessmentConfiguration configuration,
			@Named("jsonmapper") ObjectMapper objectMapper) throws Exception {
		this.configuration = configuration;
		this.objectMapper = objectMapper;
		cluster = CouchbaseCluster.create(configuration.getCouchbaseNodes());
		bucket = cluster.openBucket(configuration.getCouchbaseBucket());
	}

	@Override
	public List<Transaction> getTransaction(String accountNumber, String sortCode) throws Exception {
		String transactionKey = "TRANSACTION_" + accountNumber;
		JsonDocument transactionDocument = bucket.get(transactionKey);
		if (transactionDocument != null && !transactionDocument.content().isEmpty()) {
			JsonObject jsonObject = transactionDocument.content();
			String jsonString = jsonObject.toString();
			TransactionData transactionData = objectMapper.readValue(jsonString, TransactionData.class);
			return transactionData.getTransactions();
		}
		return null;
	}

}

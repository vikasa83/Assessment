package com.assesment.dao;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assesment.AssessmentConfiguration;
import com.assesment.pojo.Transaction;
import com.assesment.pojo.TransactionData;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionDaoImpl implements TransactionDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDaoImpl.class);
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

	public void setConfiguration(AssessmentConfiguration configuration) {
		this.configuration = configuration;
	}

	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setCluster(CouchbaseCluster cluster) {
		this.cluster = cluster;
	}

	@Override
	public List<Transaction> getTransaction(String accountNumber, String sortCode) throws Exception {
		String transactionKey = "TRANSACTION_" + accountNumber + "_" + sortCode;
		JsonDocument transactionDocument = bucket.get(transactionKey);
		if (transactionDocument != null) {
			JsonObject jsonObject = transactionDocument.content();
			String jsonString = jsonObject.toString();
			TransactionData transactionData = objectMapper.readValue(jsonString, TransactionData.class);
			return transactionData.getTransactions();
		}
		return null;
	}

	@Override
	public boolean createTransaction(TransactionData transactionData) {
		// TODO Auto-generated method stub
		boolean isCreated = false;
		String accNum = getRandomAccountNumber();
		String srtCode = getRandomSortCode();
		String transactionKey = "TRANSACTION_" + accNum + "_" + srtCode;
		JsonObject transationJsonObject;
		try {
			transationJsonObject = getProductFromJson(objectMapper.writeValueAsString(transactionData));
			JsonDocument document = bucket.insert(JsonDocument.create(transactionKey, transationJsonObject));
			if (document != null) {
				LOGGER.info("Transactions created with accountNumbe :: "+accNum +" and sortCode :: "+srtCode);
				isCreated = true;
			}
		} catch (JsonProcessingException e) {
			LOGGER.error("Error Occurred", e.getMessage());
			return false;
		}

		return isCreated;
	}

	@Override
	public boolean updateTransaction(String accountNumber, String sortCode, TransactionData transactionData) {
		boolean isUpdate = false;
		String transactionKey = "TRANSACTION_" + accountNumber + "_" + sortCode;

		JsonDocument transactionDocument = bucket.get(transactionKey);
		if (transactionDocument != null) {
			JsonObject jsonObject = transactionDocument.content();
			String jsonString = jsonObject.toString();
			try {
				TransactionData transactionDataFromDB = objectMapper.readValue(jsonString, TransactionData.class);
				List<Transaction> transactions = transactionDataFromDB.getTransactions();
				transactions.addAll(transactionData.getTransactions());

				transactionData.setTransactions(transactions);
				JsonObject transationJsonObject = getProductFromJson(objectMapper.writeValueAsString(transactionData));
				JsonDocument document = bucket.replace(JsonDocument.create(transactionKey, transationJsonObject));
				if (document != null) {
					isUpdate = true;
				}
			} catch (IOException e) {
				LOGGER.error("Error Occurred", e.getMessage());
			}
		}

		return isUpdate;
	}

	public static JsonObject getProductFromJson(String strJson) {
		return JsonObject.fromJson(strJson);
	}

	public static String getRandomAccountNumber() {
		Random rand = new Random();
		return String.valueOf(rand.nextInt(1000000));
	}

	public static String getRandomSortCode() {
		Random rand = new Random();
		return String.valueOf(rand.nextInt(10000));
	}
}

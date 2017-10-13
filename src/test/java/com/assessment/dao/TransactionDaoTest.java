package com.assessment.dao;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.assesment.AssessmentConfiguration;
import com.assesment.dao.TransactionDaoImpl;
import com.assesment.pojo.Transaction;
import com.assesment.pojo.TransactionData;
import com.assessment.resource.TestConfiguration;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.boolex.Matcher;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDaoTest {
	
	private AssessmentConfiguration testConfiguration;
	
	private Bucket bucket = Mockito.mock(Bucket.class);
	private final CouchbaseCluster cluster = Mockito.mock(CouchbaseCluster.class);
	private final ObjectMapper objectMapper = Jackson.newObjectMapper();
	
	private TransactionDaoImpl transactionDaoImpl;

	@Before
	public void setUp() throws Exception {
		testConfiguration = TestConfiguration.load();
		transactionDaoImpl = new TransactionDaoImpl();
		transactionDaoImpl.setBucket(bucket);
		transactionDaoImpl.setCluster(cluster);
		transactionDaoImpl.setConfiguration(testConfiguration);
		transactionDaoImpl.setObjectMapper(objectMapper);
	}
	
	@Test
	public void getTransactionTest() throws Exception{
		String accnum = "1234";
		String sortCode = "345";
		
		List<Transaction> expectedTransactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setTransactionId("3");
		transaction.setPermanentAccountNumber("222222");
		expectedTransactions.add(transaction);
		
		String expectedTransactionsStr = FixtureHelpers.fixture(
				"com/assessment/fixtures/couchBaseDoc.json");
		
		JsonObject jsonObject = JsonObject.fromJson(expectedTransactionsStr);
		JsonDocument jsonDoc = JsonDocument.create("TRANSACTION_"+accnum+"_"+sortCode, jsonObject);
		
		Mockito.when(bucket.get("TRANSACTION_"+accnum+"_"+sortCode)).thenReturn(jsonDoc);
		
		List<Transaction> transactions = transactionDaoImpl.getTransaction(accnum, sortCode);
		
		assertThat(transactions.size()).isEqualTo(expectedTransactions.size());
		
		assertThat(transactions.get(0).toString()).isEqualTo(expectedTransactions.get(0).toString());
		
	}
	
	@Test
	public void getTransactionJSONDocIsNullTest() throws Exception{
		String accnum = "1234";
		String sortCode = "345";
		
		Mockito.when(bucket.get("TRANSACTION_"+accnum)).thenReturn(null);
		
		List<Transaction> transactions = transactionDaoImpl.getTransaction(accnum, sortCode);
		
		assertThat(transactions).isNull();
		
	}
	
	@Test(expected=Exception.class)
	public void getTransactionExcetionTest() throws Exception{
		String accnum = "1234";
		String sortCode = "345";
		
		Mockito.when(bucket.get("TRANSACTION_"+accnum)).thenThrow(new Exception());
		
		List<Transaction> transactions = transactionDaoImpl.getTransaction(accnum, sortCode);
		
		assertThat(transactions).isNull();
	}
	
	@Test
	public void testCreateTransaction() throws JsonProcessingException {
		TransactionData transactionData = new TransactionData();
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setBankTransactionCode("bankTransactionCode");
		transactions.add(transaction);
		transactionData.setTransactions(transactions);
		
		JsonObject transationJsonObject = JsonObject.fromJson(objectMapper.writeValueAsString(transactionData));
		JsonDocument jsonDocument = JsonDocument.create("id", transationJsonObject);
		
		Mockito.when(bucket.insert(Mockito.anyObject())).thenReturn(jsonDocument);
		
		boolean isCreated = transactionDaoImpl.createTransaction(transactionData);
		assertThat(isCreated).isTrue();
	}
	
	@Test
	public void testCreateTransactionNotCreated() throws JsonProcessingException {
		TransactionData transactionData = new TransactionData();
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setBankTransactionCode("bankTransactionCode");
		transactions.add(transaction);
		transactionData.setTransactions(transactions);
		
		Mockito.when(bucket.insert(Mockito.anyObject())).thenReturn(null);
		
		boolean isCreated = transactionDaoImpl.createTransaction(transactionData);
		assertThat(isCreated).isFalse();
	}
	
	@Test
	public void testCreateTransactionException() throws JsonProcessingException {
		
		ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
		transactionDaoImpl.setObjectMapper(objectMapper);
		TransactionData transactionData = new TransactionData();
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setBankTransactionCode("bankTransactionCode");
		transactions.add(transaction);
		transactionData.setTransactions(transactions);
		
		Mockito.when(objectMapper.writeValueAsString(transactionData)).thenThrow(JsonProcessingException.class);
		
		boolean isCreated = transactionDaoImpl.createTransaction(transactionData);
		assertThat(isCreated).isFalse();
	}
	
	
	@Test
	public void testUpdateTransaction() throws JsonProcessingException {
		String accountNumber = "12345";
		String sortCode = "123";
		TransactionData transactionData = new TransactionData();
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setBankTransactionCode("bankTransactionCode");
		transactions.add(transaction);
		transactionData.setTransactions(transactions);
		
		JsonObject transationJsonObject = JsonObject.fromJson(objectMapper.writeValueAsString(transactionData));
		JsonDocument jsonDocument = JsonDocument.create("TRANSACTION_" + accountNumber + "_" + sortCode, transationJsonObject);
		
		Mockito.when(bucket.replace(Mockito.anyObject())).thenReturn(jsonDocument);
		Mockito.when(bucket.get("TRANSACTION_" + accountNumber + "_" + sortCode)).thenReturn(jsonDocument);
		
		boolean isUpdated = transactionDaoImpl.updateTransaction(accountNumber, sortCode, transactionData);
		assertThat(isUpdated).isTrue();
	}
	
	@Test
	public void testUpdateTransactionNotUpdated() throws JsonProcessingException {
		String accountNumber = "12345";
		String sortCode = "123";
		TransactionData transactionData = new TransactionData();
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setBankTransactionCode("bankTransactionCode");
		transactions.add(transaction);
		transactionData.setTransactions(transactions);
		
		JsonObject transationJsonObject = JsonObject.fromJson(objectMapper.writeValueAsString(transactionData));
		JsonDocument jsonDocument = JsonDocument.create("TRANSACTION_" + accountNumber + "_" + sortCode, transationJsonObject);
		
		Mockito.when(bucket.replace(Mockito.anyObject())).thenReturn(jsonDocument);
		Mockito.when(bucket.get("TRANSACTION_" + accountNumber + "_" + sortCode)).thenReturn(null);
		
		boolean isUpdated = transactionDaoImpl.updateTransaction(accountNumber, sortCode, transactionData);
		assertThat(isUpdated).isFalse();
	}
	
	@Test
	public void testUpdateTransactionNotUpdatedCase2() throws JsonProcessingException {
		String accountNumber = "12345";
		String sortCode = "123";
		TransactionData transactionData = new TransactionData();
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setBankTransactionCode("bankTransactionCode");
		transactions.add(transaction);
		transactionData.setTransactions(transactions);
		
		JsonObject transationJsonObject = JsonObject.fromJson(objectMapper.writeValueAsString(transactionData));
		JsonDocument jsonDocument = JsonDocument.create("TRANSACTION_" + accountNumber + "_" + sortCode, transationJsonObject);
		
		Mockito.when(bucket.replace(Mockito.anyObject())).thenReturn(null);
		Mockito.when(bucket.get("TRANSACTION_" + accountNumber + "_" + sortCode)).thenReturn(jsonDocument);
		
		boolean isUpdated = transactionDaoImpl.updateTransaction(accountNumber, sortCode, transactionData);
		assertThat(isUpdated).isFalse();
	}
	
}

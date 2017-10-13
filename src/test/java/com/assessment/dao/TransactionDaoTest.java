package com.assessment.dao;

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
import com.assessment.resource.TestConfiguration;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;

import static org.fest.assertions.api.Assertions.assertThat;

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
}

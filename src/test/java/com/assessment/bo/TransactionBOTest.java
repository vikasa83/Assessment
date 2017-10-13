package com.assessment.bo;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.assesment.AssessmentConfiguration;
import com.assesment.bo.TransactionBOImpl;
import com.assesment.dao.TransactionDao;
import com.assesment.pojo.ThirdpartyOutput;
import com.assesment.pojo.Transaction;
import com.assesment.pojo.TransactionData;
import com.assesment.pojo.TransactionResponse;
import com.assessment.resource.TestConfiguration;

import ch.qos.logback.core.boolex.Matcher;


@RunWith(MockitoJUnitRunner.class)
public class TransactionBOTest {

	private TransactionDao transactionDao = Mockito.mock(TransactionDao.class);
	private AssessmentConfiguration testConfiguration;
	
	private TransactionBOImpl transactionBOImpl;
	
	private Client client = Mockito.mock(Client.class);
	private WebTarget webTarget = Mockito.mock(WebTarget.class);
	
	@Before
	public void setUp() throws Exception {
		testConfiguration = TestConfiguration.load();
		transactionBOImpl = new TransactionBOImpl(transactionDao, testConfiguration);
		transactionBOImpl.setClient(client);
		transactionBOImpl.setWebTarget(webTarget);
		Mockito.when(client.target(Mockito.anyString())).thenReturn(webTarget);
	}
	
	@Test
	public void getTransactionTest() throws Exception {
		Response mockRespone = Mockito.mock(Response.class);
		
		Invocation.Builder restTargetBuilder = Mockito
				.mock(Invocation.Builder.class);
		
		String accountNumber = "12345";
		String sortCode = "43324";
		
		ThirdpartyOutput thirdpartyOutput = new ThirdpartyOutput();
		thirdpartyOutput.setSomeFields("SomeFields");
		thirdpartyOutput.setThrirdPartyData("ThrirdPartyData");
		
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setTransactionId("transactionId");
		transactions.add(transaction);
		
		TransactionResponse exTransactionResponse = new TransactionResponse();
		exTransactionResponse.setThirdpartyOutput(thirdpartyOutput);
		exTransactionResponse.setTransactions(transactions);
		
		Mockito.when(transactionDao.getTransaction(accountNumber, sortCode)).thenReturn(transactions);
		
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(restTargetBuilder);
		
		Mockito.when(restTargetBuilder.get()).thenReturn(mockRespone);
		
		Mockito.when(mockRespone.getStatus()).thenReturn(200);
		
		Mockito.when(mockRespone.readEntity(ThirdpartyOutput.class)).thenReturn(thirdpartyOutput);
		
		TransactionResponse transactionResponse = transactionBOImpl.getTransaction(accountNumber, sortCode);
		
		assertThat(transactionResponse.toString()).isEqualTo(exTransactionResponse.toString());
	}
	
	@Test
	public void getTransactionTestIOException() throws Exception {
		Response mockRespone = Mockito.mock(Response.class);
		
		Invocation.Builder restTargetBuilder = Mockito
				.mock(Invocation.Builder.class);
		
		String accountNumber = "12345";
		String sortCode = "43324";
		
		ThirdpartyOutput thirdpartyOutput = new ThirdpartyOutput();
		thirdpartyOutput.setSomeFields("SomeFields");
		thirdpartyOutput.setThrirdPartyData("ThrirdPartyData");
		
		List<Transaction> transactions = new ArrayList<>();
		
		TransactionResponse exTransactionResponse = new TransactionResponse();
		exTransactionResponse.setThirdpartyOutput(thirdpartyOutput);
		exTransactionResponse.setTransactions(transactions);
		
		Mockito.when(transactionDao.getTransaction(accountNumber, sortCode)).thenThrow(new IOException());
		
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(restTargetBuilder);
		
		Mockito.when(restTargetBuilder.get()).thenReturn(mockRespone);
		
		Mockito.when(mockRespone.readEntity(ThirdpartyOutput.class)).thenReturn(thirdpartyOutput);
		
		TransactionResponse transactionResponse = transactionBOImpl.getTransaction(accountNumber, sortCode);
		
		assertThat(transactionResponse).isNull();
	}
	
	
	@Test(expected = Exception.class)
	public void getTransactionTestThrowsException() throws Exception {
		Response mockRespone = Mockito.mock(Response.class);
		
		Invocation.Builder restTargetBuilder = Mockito
				.mock(Invocation.Builder.class);
		
		String accountNumber = "12345";
		String sortCode = "43324";
		
		ThirdpartyOutput thirdpartyOutput = new ThirdpartyOutput();
		thirdpartyOutput.setSomeFields("SomeFields");
		thirdpartyOutput.setThrirdPartyData("ThrirdPartyData");
		
		List<Transaction> transactions = new ArrayList<>();
		Transaction transaction = new Transaction();
		transaction.setTransactionId("transactionId");
		transactions.add(transaction);
		
		TransactionResponse exTransactionResponse = new TransactionResponse();
		exTransactionResponse.setThirdpartyOutput(thirdpartyOutput);
		exTransactionResponse.setTransactions(transactions);
		
		Mockito.when(transactionDao.getTransaction(accountNumber, sortCode)).thenReturn(transactions);
		
		Mockito.when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(restTargetBuilder);
		
		Mockito.when(restTargetBuilder.get()).thenReturn(mockRespone);
		
		Mockito.when(mockRespone.getStatus()).thenReturn(400);
		
		TransactionResponse transactionResponse = transactionBOImpl.getTransaction(accountNumber, sortCode);
	}
	
	@Test
	public void createTransactionTest() {
		TransactionData transactionData = new TransactionData();
		Mockito.when(transactionDao.createTransaction(transactionData)).thenReturn(true);
		boolean isCreated = transactionBOImpl.createTransaction(transactionData);
		assertThat(isCreated).isTrue();
	}
	
	@Test
	public void updateTransactionTest() {
		String accNum = "12345";
		String sortCode = "234";		
		TransactionData transactionData = new TransactionData();
		Mockito.when(transactionDao.updateTransaction(accNum,sortCode,transactionData)).thenReturn(true);
		boolean isCreated = transactionBOImpl.updateTransaction(accNum,sortCode,transactionData);
		assertThat(isCreated).isTrue();
	}
}

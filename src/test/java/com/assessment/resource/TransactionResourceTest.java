package com.assessment.resource;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.assesment.bo.TransactionBO;
import com.assesment.bo.TransactionBOImpl;
import com.assesment.pojo.TransactionData;
import com.assesment.pojo.TransactionResponse;
import com.assesment.resource.TransactionResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;
import io.dropwizard.testing.junit.ResourceTestRule;

@RunWith(MockitoJUnitRunner.class)
public class TransactionResourceTest {

	private static TransactionBO transactionBOImpl = Mockito.mock(TransactionBOImpl.class);

	private static ObjectMapper objectMapper = Jackson.newObjectMapper();

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new TransactionResource(objectMapper, transactionBOImpl)).build();

	@Before
	public void setUpResources() throws Exception {
		objectMapper.registerModule(new AfterburnerModule());
	}

	@Test
	public void testGetTransactions() throws Exception {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/43324");
		String expectedTransactions = FixtureHelpers.fixture("com/assessment/fixtures/expectedTransactions.json");
		TransactionResponse expectedTransactionResponse = objectMapper.readValue(expectedTransactions,
				TransactionResponse.class);

		Mockito.doReturn(expectedTransactionResponse).when(transactionBOImpl).getTransaction(Mockito.anyString(),
				Mockito.anyString());

		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + "Token").get();

		TransactionResponse responseText = response.readEntity(TransactionResponse.class);
		assertThat(responseText.toString()).isEqualTo(expectedTransactionResponse.toString());
		assertThat(response.getStatus()).isEqualTo(200);

	}

	@Test
	public void testGetTransactionsForNotFound() throws Exception {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/43324");
		Mockito.doReturn(null).when(transactionBOImpl).getTransaction(Mockito.anyString(), Mockito.anyString());
		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + "Token").get();
		assertThat(response.getStatus()).isEqualTo(404);

	}

	@Test
	public void testGetTransactionsForException() throws Exception {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/43324");
		Mockito.doThrow(new Exception()).when(transactionBOImpl).getTransaction(Mockito.anyString(),
				Mockito.anyString());
		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + "Token").get();
		assertThat(response.getStatus()).isEqualTo(406);

	}

	@Test
	public void testPostTransactions() throws IOException {
		WebTarget resourceTarget = resources.client().target("/v1/transactions");
		TransactionData transactionData = new TransactionData();
		Mockito.doReturn(true).when(transactionBOImpl).createTransaction(transactionData);
		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + "Token")
				.post(Entity.entity(transactionData, MediaType.APPLICATION_JSON));
		String responseText = response.readEntity(String.class);
		assertThat(response.getStatus()).isEqualTo(200);
		assertThat(responseText).isEqualTo("Transactions cretaed successfully");
	}

	@Test
	public void testPostTransactionsNotCreated() throws IOException {
		WebTarget resourceTarget = resources.client().target("/v1/transactions");
		TransactionData transactionData = new TransactionData();
		Mockito.doReturn(false).when(transactionBOImpl).createTransaction(transactionData);
		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + "Token")
				.post(Entity.entity(transactionData, MediaType.APPLICATION_JSON));
		String responseText = response.readEntity(String.class);
		assertThat(response.getStatus()).isEqualTo(406);
		assertThat(responseText).isEqualTo("Transactions creation is failed. Please contact administrator.");
	}

	@Test
	public void testPutTransactions() throws IOException {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/4324");
		TransactionData transactionData = new TransactionData();
		Mockito.doReturn(true).when(transactionBOImpl).updateTransaction("12345", "4324", transactionData);
		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + "Token")
				.put(Entity.entity(transactionData, MediaType.APPLICATION_JSON));
		String responseText = response.readEntity(String.class);
		assertThat(response.getStatus()).isEqualTo(200);
		assertThat(responseText).isEqualTo("Transactions updated successfully");
	}

	@Test
	public void testPutTransactionsFailed() throws IOException {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/4324");
		TransactionData transactionData = new TransactionData();
		Mockito.doReturn(false).when(transactionBOImpl).updateTransaction("12345", "4324", transactionData);
		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer " + "Token")
				.put(Entity.entity(transactionData, MediaType.APPLICATION_JSON));
		String responseText = response.readEntity(String.class);
		assertThat(response.getStatus()).isEqualTo(406);
		assertThat(responseText).isEqualTo("Transactions updation is failed. Please contact administrator.");
	}

	@Test
	public void testGetTransactionsAuthorizationError() throws Exception {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/43324");

		Response response = resourceTarget.request().get();

		String responseText = response.readEntity(String.class);
		assertThat(responseText).isEqualTo("Authorization required.");
		assertThat(response.getStatus()).isEqualTo(401);

	}

	@Test
	public void testGetTransactionsAuthorizationErrorInvalidToken() throws Exception {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/43324");

		Response response = resourceTarget.request().header(HttpHeaders.AUTHORIZATION, "Bearer").get();

		String responseText = response.readEntity(String.class);
		assertThat(responseText).isEqualTo("Authorization required.");
		assertThat(response.getStatus()).isEqualTo(401);

	}

	@Test
	public void testPutTransactionsAuthorizationError() throws IOException {
		WebTarget resourceTarget = resources.client().target("/v1/transactions/accountNumber/12345/sortCode/4324");
		TransactionData transactionData = new TransactionData();
		Response response = resourceTarget.request().put(Entity.entity(transactionData, MediaType.APPLICATION_JSON));
		String responseText = response.readEntity(String.class);
		assertThat(responseText).isEqualTo("Authorization required.");
		assertThat(response.getStatus()).isEqualTo(401);
	}

	@Test
	public void testPostTransactionsAuthorizationError() throws IOException {
		WebTarget resourceTarget = resources.client().target("/v1/transactions");
		TransactionData transactionData = new TransactionData();
		Response response = resourceTarget.request().post(Entity.entity(transactionData, MediaType.APPLICATION_JSON));
		String responseText = response.readEntity(String.class);
		assertThat(responseText).isEqualTo("Authorization required.");
		assertThat(response.getStatus()).isEqualTo(401);
	}
}

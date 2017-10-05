package com.assessment.resource;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.assesment.bo.TransactionBO;
import com.assesment.bo.TransactionBOImpl;
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
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(
			new TransactionResource(objectMapper, transactionBOImpl))
			.build();

	@Before
	public void setUpResources() throws Exception {
		objectMapper.registerModule(new AfterburnerModule());
	}

	
	@Test
	public void testGetTransactions() throws IOException {
		WebTarget resourceTarget = resources.client().target(
				"/v1/transactions/accountNumber/12345/sortCode/43324");
		String expectedTransactions = FixtureHelpers.fixture(
				"com/assessment/fixtures/expectedTransactions.json");
		TransactionResponse expectedTransactionResponse = objectMapper.readValue(expectedTransactions,
				TransactionResponse.class);

		Mockito.doReturn(expectedTransactionResponse).when(transactionBOImpl)
				.getTransaction(Mockito.anyString(), Mockito.anyString());

		Response response = resourceTarget.request().get();

		TransactionResponse responseText = response.readEntity(TransactionResponse.class);
		assertThat(responseText.toString()).isEqualTo(expectedTransactionResponse.toString());
		assertThat(response.getStatus()).isEqualTo(200);

	}
	
	
}

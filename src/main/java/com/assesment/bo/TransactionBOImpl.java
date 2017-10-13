package com.assesment.bo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assesment.AssessmentConfiguration;
import com.assesment.dao.TransactionDao;
import com.assesment.pojo.ThirdpartyOutput;
import com.assesment.pojo.Transaction;
import com.assesment.pojo.TransactionData;
import com.assesment.pojo.TransactionResponse;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;

public class TransactionBOImpl implements TransactionBO {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionBOImpl.class);
	private TransactionDao transactionDao;
	private AssessmentConfiguration configuration;
	private Client client;
	private WebTarget webTarget;

	@Inject
	public TransactionBOImpl(@Named("transactionDao") TransactionDao transactionDao,
			@Named("configuration") AssessmentConfiguration configuration) {
		this.transactionDao = transactionDao;
		this.configuration = configuration;
		client = ClientBuilder.newClient().register(JacksonFeatures.class);
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setWebTarget(WebTarget webTarget) {
		this.webTarget = webTarget;
	}

	public TransactionResponse getTransaction(String accountNumber, String sortCode) throws Exception{

		TransactionResponse transactionResponse = null;
		List<Transaction> transactions = null;
		try {
			transactions = transactionDao.getTransaction(accountNumber, sortCode);
		} catch (Exception e) {
			LOGGER.error("Exception occurred.. ", e.getMessage());
		}

		if (transactions != null) {
			transactionResponse = new TransactionResponse();
			transactionResponse.setTransactions(transactions);
			transactionResponse.setThirdpartyOutput(getThirdPartyOutput("pathVariable","queryParam"));
		}
		return transactionResponse;
	}

	private ThirdpartyOutput getThirdPartyOutput(String pasthVariable, String queryParam) throws Exception{

		webTarget = getWebTarget(pasthVariable, queryParam);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		int status = response.getStatus();
		if(status == 200) {
			ThirdpartyOutput thirdpartyOutput = response.readEntity(ThirdpartyOutput.class);
			return thirdpartyOutput;
		} else {
			throw new Exception("Error in fetching 3rd party data.");
		}
		
	}

	@Override
	public boolean createTransaction(TransactionData transactionData) {
		boolean isCreated = transactionDao.createTransaction(transactionData);
		return isCreated;
	}

	@Override
	public boolean updateTransaction(String accountNumber, String sortCode, TransactionData transactionData) {
		boolean isUpdated = transactionDao.updateTransaction(accountNumber, sortCode, transactionData);
		return isUpdated;
	}
	
	private WebTarget getWebTarget(String pathPara, String queryParam){
		return webTarget = client.target(configuration.getUri()+"/"+pathPara+"?"+queryParam);
	}

}

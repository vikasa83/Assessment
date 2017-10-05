package com.assesment.resource;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assesment.AssessmentConfiguration;
import com.assesment.bo.TransactionBO;
import com.assesment.bo.TransactionBOImpl;
import com.assesment.pojo.Transaction;
import com.assesment.pojo.TransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/v1/transactions")
@Api(value = "Endpoint to find transacion by AccountNumber and SortCode")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionResource.class);
	private ObjectMapper objectMapper;
	private TransactionBO transactionBO;
	private AssessmentConfiguration configuration;

	@Inject
	public TransactionResource(@Named("jsonmapper") ObjectMapper objectMapper,
			@Named("transactionBO") TransactionBO transactionBO,
			@Named("configuration") AssessmentConfiguration configuration) {
		this.objectMapper = objectMapper;
		this.transactionBO = transactionBO;
		this.configuration = configuration;
	}

	@GET
	@Path("/accountNumber/{accountNumber}/sortCode/{sortCode}")
	@ApiOperation(value = "Find transactions of accountNumber with sortCode")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 200, message = "Successful retrieval of transaction detail", response = TransactionResponse.class) })
	public Response getTransactions(
			@ApiParam(value = "Account Number  (e.g. 008315670)", required = true) @PathParam("accountNumber") String accountNumber,
			@ApiParam(value = "Sort Code (e.g. 12345)", required = true) @PathParam("sortCode") String sortCode)
					throws IOException {

		TransactionResponse transactionResponse = transactionBO.getTransaction(accountNumber, sortCode);
		return Response.ok(objectMapper.writeValueAsString(transactionResponse)).build();
	}

}

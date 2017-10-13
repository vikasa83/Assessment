package com.assesment.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assesment.bo.TransactionBO;
import com.assesment.pojo.TransactionData;
import com.assesment.pojo.TransactionResponse;
import com.couchbase.client.deps.io.netty.handler.codec.http.HttpResponseStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
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

	@Inject
	public TransactionResource(@Named("jsonmapper") ObjectMapper objectMapper,
			@Named("transactionBO") TransactionBO transactionBO) {
		this.objectMapper = objectMapper;
		this.transactionBO = transactionBO;
	}

	@GET
	@Path("/accountNumber/{accountNumber}/sortCode/{sortCode}")
	@ApiOperation(value = "Find transactions of accountNumber with sortCode")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 401, message = "Authorization required."),
			@ApiResponse(code = 200, message = "Successfully retrieval of transaction detail", response = TransactionResponse.class) })
	public Response getTransactions(
			@ApiParam(value = "Account Number  (e.g. 008315670)", required = true) @PathParam("accountNumber") String accountNumber,
			@ApiParam(value = "Sort Code (e.g. 12345)", required = true) @PathParam("sortCode") String sortCode,
			@HeaderParam(value = "Authorization") String authorization) throws JsonProcessingException {

		if (!oAuthValidation(authorization)) {
			LOGGER.error("Unautorized request.");
			return Response.status(Status.UNAUTHORIZED).entity("Authorization required.").build();
		}
		String responseText = "";
		TransactionResponse transactionResponse;
		try {
			transactionResponse = transactionBO.getTransaction(accountNumber, sortCode);
			if (transactionResponse != null) {
				responseText = objectMapper.writeValueAsString(transactionResponse);
				LOGGER.info("Returning response.");
			} else {
				return Response.status(Status.NOT_FOUND).entity("Transactions not found.").build();
			}

		} catch (Exception e) {
			LOGGER.error("Error occured..", e.getMessage());
			return Response.status(Status.NOT_ACCEPTABLE).entity("Error Occured in getting third party response.")
					.build();
		}

		return Response.ok(responseText).build();
	}

	@POST
	@ApiOperation(value = "Add transaction into accountNumber with sortCode")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Authorization required."),
			@ApiResponse(code = 200, message = "Successfully inserted transaction detail") })
	public Response postTransactions(
			@ApiParam(value = "Transaction data", required = true) TransactionData transactionData,
			@HeaderParam(value = "Authorization") String authorization) throws JsonProcessingException {

		if (!oAuthValidation(authorization)) {
			LOGGER.error("Unautorized request.");
			return Response.status(Status.UNAUTHORIZED).entity("Authorization required.").build();
		}

		boolean isCreated = transactionBO.createTransaction(transactionData);
		if (isCreated) {
			return Response.ok("Transactions cretaed successfully").build();
		}
		return Response.status(Status.NOT_ACCEPTABLE)
				.entity("Transactions creation is failed. Please contact administrator.").build();
	}

	@PUT
	@Path("/accountNumber/{accountNumber}/sortCode/{sortCode}")
	@ApiOperation(value = "Add transaction into accountNumber with sortCode")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Authorization required."),
			@ApiResponse(code = 200, message = "Successfully updated transaction detail") })
	public Response updateTransactions(
			@ApiParam(value = "Account Number  (e.g. 008315670)", required = true) @PathParam("accountNumber") String accountNumber,
			@ApiParam(value = "Sort Code (e.g. 12345)", required = true) @PathParam("sortCode") String sortCode,
			@ApiParam(value = "Transaction data", required = true) TransactionData transactionData,
			@HeaderParam(value = "Authorization") String authorization) {

		if (!oAuthValidation(authorization)) {
			return Response.status(Status.UNAUTHORIZED).entity("Authorization required.").build();
		}

		boolean isUpdate = transactionBO.updateTransaction(accountNumber, sortCode, transactionData);
		if (isUpdate) {
			return Response.ok("Transactions updated successfully").build();
		}

		return Response.status(Status.NOT_ACCEPTABLE)
				.entity("Transactions updation is failed. Please contact administrator.").build();
	}

	private boolean oAuthValidation(String authorization) {
		if (authorization == null || !isAuthorized(authorization)) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isAuthorized(String authHeader) {
		// here we need to validate token by calling identity service
		// currently not doing any validation
		String[] tokens = authHeader.split(" ");
		if (tokens.length < 2) {
			return false;
		}
		return true;
	}
}

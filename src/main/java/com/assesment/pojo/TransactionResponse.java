package com.assesment.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "TransactionsData")
public class TransactionResponse {

	@ApiModelProperty(value = "Transactions")
	@JsonProperty("transactions")
	private List<Transaction> transactions;
	
	@ApiModelProperty(value = "ThirdpartyOutput")
	@JsonProperty("thirdpartyOutput")
	private ThirdpartyOutput thirdpartyOutput;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public ThirdpartyOutput getThirdpartyOutput() {
		return thirdpartyOutput;
	}

	public void setThirdpartyOutput(ThirdpartyOutput thirdpartyOutput) {
		this.thirdpartyOutput = thirdpartyOutput;
	}

	@Override
	public String toString() {
		return "TransactionResponse [transactions=" + transactions + ", thirdpartyOutput=" + thirdpartyOutput + "]";
	}
	
	
}

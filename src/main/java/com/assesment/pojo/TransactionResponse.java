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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((thirdpartyOutput == null) ? 0 : thirdpartyOutput.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionResponse other = (TransactionResponse) obj;
		if (thirdpartyOutput == null) {
			if (other.thirdpartyOutput != null)
				return false;
		} else if (!thirdpartyOutput.equals(other.thirdpartyOutput))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}
	
	
}

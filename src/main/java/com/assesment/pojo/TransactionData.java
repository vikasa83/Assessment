package com.assesment.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionData {

	@JsonProperty("transactions")
	List<Transaction> transactions = new ArrayList<>();

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "TransactionData [transactions=" + transactions + "]";
	}

	
}

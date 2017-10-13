package com.assesment.bo;

import com.assesment.pojo.TransactionData;
import com.assesment.pojo.TransactionResponse;

public interface TransactionBO {

	public TransactionResponse getTransaction(String accountNumber, String sortCode) throws Exception;

	public boolean createTransaction(TransactionData transactionData);

	public boolean updateTransaction(String accountNumber, String sortCode,
			TransactionData transactionData);
}

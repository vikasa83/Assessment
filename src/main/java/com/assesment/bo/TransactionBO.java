package com.assesment.bo;

import com.assesment.pojo.TransactionResponse;

public interface TransactionBO {

	public TransactionResponse getTransaction(String accountNumber, String sortCode);
}

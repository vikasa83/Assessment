package com.assesment.dao;

import java.util.List;

import com.assesment.pojo.Transaction;
import com.assesment.pojo.TransactionData;

public interface TransactionDao {

	public List<Transaction> getTransaction(String accountNumber, String sortCode) throws Exception;

	public boolean createTransaction(TransactionData transactionData);

	public boolean updateTransaction(String accountNumber, String sortCode, TransactionData transactionData);
}

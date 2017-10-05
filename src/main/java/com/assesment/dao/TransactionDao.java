package com.assesment.dao;

import java.util.List;

import com.assesment.pojo.Transaction;

public interface TransactionDao {

	public List<Transaction> getTransaction(String accountNumber, String sortCode) throws Exception;
}

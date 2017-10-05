package com.assesment.pojo;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Transaction")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

	@ApiModelProperty(value = "Transaction Id")
	@JsonProperty("transactionId")
	private String transactionId;
	
	@ApiModelProperty(value = "Permanent Account Number")
	@JsonProperty("permanentAccountNumber")
	private String permanentAccountNumber;
	
	@ApiModelProperty(value = "Transaction Amount")
	@JsonProperty("transactionAmount")
	private String transactionAmount;
	
	@ApiModelProperty(value = "TransactionCurrencyCode")
	@JsonProperty("transactionCurrencyCode")
	private String transactionCurrencyCode;
	
	@ApiModelProperty(value = "CreditDebitIndicator")
	@JsonProperty("creditDebitIndicator")
	private String creditDebitIndicator;
	
	@ApiModelProperty(value = "TransactionStatus")
	@JsonProperty("transactionStatus")
	private String transactionStatus;
	
	@ApiModelProperty(value = "PostedDateTime")
	@JsonProperty("postedDateTime")
	private String postedDateTime;
	
	@ApiModelProperty(value = "BookedDateTime")
	@JsonProperty("BookedDateTime")
	private String bookedDateTime;
	
	@ApiModelProperty(value = "TransactionDescription")
	@JsonProperty("transactionDescription")
	private String transactionDescription;
	
	@ApiModelProperty(value = "Type")
	@JsonProperty("type")
	private String type;
	
	@ApiModelProperty(value = "InterimBookedBalanceAmount")
	@JsonProperty("interimBookedBalanceAmount")
	private String interimBookedBalanceAmount;
	
	@ApiModelProperty(value = "InterimBookedBalanceCurrencyCode")
	@JsonProperty("interimBookedBalanceCurrencyCode")
	private String InterimBookedBalanceCurrencyCode;
	
	@ApiModelProperty(value = "BankTransactionCode")
	@JsonProperty("bankTransactionCode")
	private String bankTransactionCode;
	
	@ApiModelProperty(value = "BankTransactionSubCode")
	@JsonProperty("bankTransactionSubCode")
	private String bankTransactionSubCode;
	
	@ApiModelProperty(value = "ProprietaryTransactionCode")
	@JsonProperty("proprietaryTransactionCode")
	private String proprietaryTransactionCode;
	
	@ApiModelProperty(value = "ProprietaryTransactionIssuer")
	@JsonProperty("proprietaryTransactionIssuer")
	private String proprietaryTransactionIssuer;
	
	@ApiModelProperty(value = "MerchantName")
	@JsonProperty("merchantName")
	private String merchantName;
	
	@ApiModelProperty(value = "MerchantCategoryCode")
	@JsonProperty("merchantCategoryCode")
	private String merchantCategoryCode;
	
	@ApiModelProperty(value = "InterimBookedCreditDebitIndicator")
	@JsonProperty("interimBookedCreditDebitIndicator")
	private String interimBookedCreditDebitIndicator;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPermanentAccountNumber() {
		return permanentAccountNumber;
	}

	public void setPermanentAccountNumber(String permanentAccountNumber) {
		this.permanentAccountNumber = permanentAccountNumber;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionCurrencyCode() {
		return transactionCurrencyCode;
	}

	public void setTransactionCurrencyCode(String transactionCurrencyCode) {
		this.transactionCurrencyCode = transactionCurrencyCode;
	}

	public String getCreditDebitIndicator() {
		return creditDebitIndicator;
	}

	public void setCreditDebitIndicator(String creditDebitIndicator) {
		this.creditDebitIndicator = creditDebitIndicator;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getPostedDateTime() {
		return postedDateTime;
	}

	public void setPostedDateTime(String postedDateTime) {
		this.postedDateTime = postedDateTime;
	}

	public String getBookedDateTime() {
		return bookedDateTime;
	}

	public void setBookedDateTime(String bookedDateTime) {
		this.bookedDateTime = bookedDateTime;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInterimBookedBalanceAmount() {
		return interimBookedBalanceAmount;
	}

	public void setInterimBookedBalanceAmount(String interimBookedBalanceAmount) {
		this.interimBookedBalanceAmount = interimBookedBalanceAmount;
	}

	public String getInterimBookedBalanceCurrencyCode() {
		return InterimBookedBalanceCurrencyCode;
	}

	public void setInterimBookedBalanceCurrencyCode(String interimBookedBalanceCurrencyCode) {
		InterimBookedBalanceCurrencyCode = interimBookedBalanceCurrencyCode;
	}

	public String getBankTransactionCode() {
		return bankTransactionCode;
	}

	public void setBankTransactionCode(String bankTransactionCode) {
		this.bankTransactionCode = bankTransactionCode;
	}

	public String getBankTransactionSubCode() {
		return bankTransactionSubCode;
	}

	public void setBankTransactionSubCode(String bankTransactionSubCode) {
		this.bankTransactionSubCode = bankTransactionSubCode;
	}

	public String getProprietaryTransactionCode() {
		return proprietaryTransactionCode;
	}

	public void setProprietaryTransactionCode(String proprietaryTransactionCode) {
		this.proprietaryTransactionCode = proprietaryTransactionCode;
	}

	public String getProprietaryTransactionIssuer() {
		return proprietaryTransactionIssuer;
	}

	public void setProprietaryTransactionIssuer(String proprietaryTransactionIssuer) {
		this.proprietaryTransactionIssuer = proprietaryTransactionIssuer;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantCategoryCode() {
		return merchantCategoryCode;
	}

	public void setMerchantCategoryCode(String merchantCategoryCode) {
		this.merchantCategoryCode = merchantCategoryCode;
	}

	public String getInterimBookedCreditDebitIndicator() {
		return interimBookedCreditDebitIndicator;
	}

	public void setInterimBookedCreditDebitIndicator(String interimBookedCreditDebitIndicator) {
		this.interimBookedCreditDebitIndicator = interimBookedCreditDebitIndicator;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", permanentAccountNumber=" + permanentAccountNumber
				+ ", transactionAmount=" + transactionAmount + ", transactionCurrencyCode=" + transactionCurrencyCode
				+ ", creditDebitIndicator=" + creditDebitIndicator + ", transactionStatus=" + transactionStatus
				+ ", postedDateTime=" + postedDateTime + ", bookedDateTime=" + bookedDateTime
				+ ", transactionDescription=" + transactionDescription + ", type=" + type
				+ ", interimBookedBalanceAmount=" + interimBookedBalanceAmount + ", InterimBookedBalanceCurrencyCode="
				+ InterimBookedBalanceCurrencyCode + ", bankTransactionCode=" + bankTransactionCode
				+ ", bankTransactionSubCode=" + bankTransactionSubCode + ", proprietaryTransactionCode="
				+ proprietaryTransactionCode + ", proprietaryTransactionIssuer=" + proprietaryTransactionIssuer
				+ ", merchantName=" + merchantName + ", merchantCategoryCode=" + merchantCategoryCode
				+ ", interimBookedCreditDebitIndicator=" + interimBookedCreditDebitIndicator + "]";
	}
	
	
}

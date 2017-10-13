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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((InterimBookedBalanceCurrencyCode == null) ? 0 : InterimBookedBalanceCurrencyCode.hashCode());
		result = prime * result + ((bankTransactionCode == null) ? 0 : bankTransactionCode.hashCode());
		result = prime * result + ((bankTransactionSubCode == null) ? 0 : bankTransactionSubCode.hashCode());
		result = prime * result + ((bookedDateTime == null) ? 0 : bookedDateTime.hashCode());
		result = prime * result + ((creditDebitIndicator == null) ? 0 : creditDebitIndicator.hashCode());
		result = prime * result + ((interimBookedBalanceAmount == null) ? 0 : interimBookedBalanceAmount.hashCode());
		result = prime * result
				+ ((interimBookedCreditDebitIndicator == null) ? 0 : interimBookedCreditDebitIndicator.hashCode());
		result = prime * result + ((merchantCategoryCode == null) ? 0 : merchantCategoryCode.hashCode());
		result = prime * result + ((merchantName == null) ? 0 : merchantName.hashCode());
		result = prime * result + ((permanentAccountNumber == null) ? 0 : permanentAccountNumber.hashCode());
		result = prime * result + ((postedDateTime == null) ? 0 : postedDateTime.hashCode());
		result = prime * result + ((proprietaryTransactionCode == null) ? 0 : proprietaryTransactionCode.hashCode());
		result = prime * result
				+ ((proprietaryTransactionIssuer == null) ? 0 : proprietaryTransactionIssuer.hashCode());
		result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
		result = prime * result + ((transactionCurrencyCode == null) ? 0 : transactionCurrencyCode.hashCode());
		result = prime * result + ((transactionDescription == null) ? 0 : transactionDescription.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionStatus == null) ? 0 : transactionStatus.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Transaction other = (Transaction) obj;
		if (InterimBookedBalanceCurrencyCode == null) {
			if (other.InterimBookedBalanceCurrencyCode != null)
				return false;
		} else if (!InterimBookedBalanceCurrencyCode.equals(other.InterimBookedBalanceCurrencyCode))
			return false;
		if (bankTransactionCode == null) {
			if (other.bankTransactionCode != null)
				return false;
		} else if (!bankTransactionCode.equals(other.bankTransactionCode))
			return false;
		if (bankTransactionSubCode == null) {
			if (other.bankTransactionSubCode != null)
				return false;
		} else if (!bankTransactionSubCode.equals(other.bankTransactionSubCode))
			return false;
		if (bookedDateTime == null) {
			if (other.bookedDateTime != null)
				return false;
		} else if (!bookedDateTime.equals(other.bookedDateTime))
			return false;
		if (creditDebitIndicator == null) {
			if (other.creditDebitIndicator != null)
				return false;
		} else if (!creditDebitIndicator.equals(other.creditDebitIndicator))
			return false;
		if (interimBookedBalanceAmount == null) {
			if (other.interimBookedBalanceAmount != null)
				return false;
		} else if (!interimBookedBalanceAmount.equals(other.interimBookedBalanceAmount))
			return false;
		if (interimBookedCreditDebitIndicator == null) {
			if (other.interimBookedCreditDebitIndicator != null)
				return false;
		} else if (!interimBookedCreditDebitIndicator.equals(other.interimBookedCreditDebitIndicator))
			return false;
		if (merchantCategoryCode == null) {
			if (other.merchantCategoryCode != null)
				return false;
		} else if (!merchantCategoryCode.equals(other.merchantCategoryCode))
			return false;
		if (merchantName == null) {
			if (other.merchantName != null)
				return false;
		} else if (!merchantName.equals(other.merchantName))
			return false;
		if (permanentAccountNumber == null) {
			if (other.permanentAccountNumber != null)
				return false;
		} else if (!permanentAccountNumber.equals(other.permanentAccountNumber))
			return false;
		if (postedDateTime == null) {
			if (other.postedDateTime != null)
				return false;
		} else if (!postedDateTime.equals(other.postedDateTime))
			return false;
		if (proprietaryTransactionCode == null) {
			if (other.proprietaryTransactionCode != null)
				return false;
		} else if (!proprietaryTransactionCode.equals(other.proprietaryTransactionCode))
			return false;
		if (proprietaryTransactionIssuer == null) {
			if (other.proprietaryTransactionIssuer != null)
				return false;
		} else if (!proprietaryTransactionIssuer.equals(other.proprietaryTransactionIssuer))
			return false;
		if (transactionAmount == null) {
			if (other.transactionAmount != null)
				return false;
		} else if (!transactionAmount.equals(other.transactionAmount))
			return false;
		if (transactionCurrencyCode == null) {
			if (other.transactionCurrencyCode != null)
				return false;
		} else if (!transactionCurrencyCode.equals(other.transactionCurrencyCode))
			return false;
		if (transactionDescription == null) {
			if (other.transactionDescription != null)
				return false;
		} else if (!transactionDescription.equals(other.transactionDescription))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionStatus == null) {
			if (other.transactionStatus != null)
				return false;
		} else if (!transactionStatus.equals(other.transactionStatus))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}

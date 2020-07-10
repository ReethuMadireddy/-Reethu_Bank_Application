package com.dxc.pojo;

public class Transaction {
	private int accountNumber;
	private String transactionType;
	private int balance;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Transaction(int accountNumber, String transactionType, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.transactionType = transactionType;
		this.balance = balance;
	}
	public Transaction() {
		super();
			}
	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", transactionType=" + transactionType + ", balance="
				+ balance + "]";
	}
	

}

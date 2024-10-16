package models;

public class TransactionHeader {
	String transactionheaderid;
	String userid;
	
	public TransactionHeader(String transactionheaderid, String userid) {
		super();
		this.transactionheaderid = transactionheaderid;
		this.userid = userid;
	}

	public String getTransactionheaderid() {
		return transactionheaderid;
	}

	public void setTransactionheaderid(String transactionheaderid) {
		this.transactionheaderid = transactionheaderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String toString() {
		return transactionheaderid;
	}
	

}

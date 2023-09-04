package com.smallworld.data;

public class Transaction {
    // Represent your transaction data here.

    private Long mtn;
    private Long amount;
    private String senderFullName;
    private Long senderAge;
    private String beneficiaryFullName;
    private Long beneficiaryAge;
    private Long issueId;
    private Boolean issueSolved;
    private String issueMessage;
    
	public Transaction() {
		super();
	}

	public Transaction(Long mtn, Long amount, String senderFullName, Long senderAge, String beneficiaryFullName,
			Long beneficiaryAge, Long issueId, Boolean issueSolved, String issueMessage) {
		super();
		this.mtn = mtn;
		this.amount = amount;
		this.senderFullName = senderFullName;
		this.senderAge = senderAge;
		this.beneficiaryFullName = beneficiaryFullName;
		this.beneficiaryAge = beneficiaryAge;
		this.issueId = issueId;
		this.issueSolved = issueSolved;
		this.issueMessage = issueMessage;
	}

	public Long getMtn() {
		return mtn;
	}

	public void setMtn(Long mtn) {
		this.mtn = mtn;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getSenderFullName() {
		return senderFullName;
	}

	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}

	public Long getSenderAge() {
		return senderAge;
	}

	public void setSenderAge(Long senderAge) {
		this.senderAge = senderAge;
	}

	public String getBeneficiaryFullName() {
		return beneficiaryFullName;
	}

	public void setBeneficiaryFullName(String beneficiaryFullName) {
		this.beneficiaryFullName = beneficiaryFullName;
	}

	public Long getBeneficiaryAge() {
		return beneficiaryAge;
	}

	public void setBeneficiaryAge(Long beneficiaryAge) {
		this.beneficiaryAge = beneficiaryAge;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public boolean getIssueSolved() {
		return issueSolved;
	}

	public void setIssueSolved(boolean issueSolved) {
		this.issueSolved = issueSolved;
	}

	public String getIssueMessage() {
		return issueMessage;
	}

	public void setIssueMessage(String issueMessage) {
		this.issueMessage = issueMessage;
	}
    	
}

package com.smallworld;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallworld.data.Transaction;

public class TransactionDataFetcher {
	 private List<Transaction> transactions;
    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() {
    	   try {
               String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
               ObjectMapper objectMapper = new ObjectMapper();
               transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
           } catch (IOException e) {
               throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
           }
    	   
    	   double totalAmount = 0.0;
    	    for (Transaction transaction : transactions) {
    	        totalAmount += transaction.getAmount();
    	    }
    	    return totalAmount;
    }
    
    

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        double totalAmount = 0.0;
        try {
	        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
	        ObjectMapper objectMapper = new ObjectMapper();
	        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
	    } catch (IOException e) {
	        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
	    }
        
        for (Transaction transaction : transactions) {
            if (senderFullName.equals(transaction.getSenderFullName())) {
                totalAmount += transaction.getAmount();
            }
        }
        return totalAmount;
    }

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount() {
    	  double maxAmount = 0.0;
    	  try {
  	        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
  	        ObjectMapper objectMapper = new ObjectMapper();
  	        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
  	    } catch (IOException e) {
  	        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
  	    }
    	    for (Transaction transaction : transactions) {
    	        double amount = transaction.getAmount();
    	        if (amount > maxAmount) {
    	            maxAmount = amount;
    	        }
    	    }
    	    return maxAmount;
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients() {
        Set<String> uniqueClients = new HashSet<>();
	  	  try {
		        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
		        ObjectMapper objectMapper = new ObjectMapper();
		        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
		    } catch (IOException e) {
		        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
		    }        
	  	  
	        for (Transaction transaction : transactions) {
	            uniqueClients.add(transaction.getSenderFullName());
	            uniqueClients.add(transaction.getBeneficiaryFullName());
	        }
	        
        return uniqueClients.size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName) {
	  	  try {
		        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
		        ObjectMapper objectMapper = new ObjectMapper();
		        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
		    } catch (IOException e) {
		        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
		    }             
    	
    	for (Transaction transaction : transactions) {
            if (transaction.getSenderFullName().equals(clientFullName) || 
                transaction.getBeneficiaryFullName().equals(clientFullName)) {
                if (!transaction.getIssueSolved()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, Transaction> getTransactionsByBeneficiaryName() {
    	Map<String, Transaction> transactionsByBeneficiary = new HashMap<>();
    	 try {
		        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
		        ObjectMapper objectMapper = new ObjectMapper();
		        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
		    } catch (IOException e) {
		        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
		    }    
    	 
        for (Transaction transaction : transactions) {
            String beneficiaryName = transaction.getBeneficiaryFullName();
            transactionsByBeneficiary.put(beneficiaryName, transaction);
        }
        return transactionsByBeneficiary;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds() {
    	 Set<Integer> unsolvedIssueIds = new HashSet<>();
    	 try {
		        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
		        ObjectMapper objectMapper = new ObjectMapper();
		        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
		    } catch (IOException e) {
		        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
		    } 
    	 
         for (Transaction transaction : transactions) {
             Long issueId = transaction.getIssueId();
             if (issueId != null && !transaction.getIssueSolved()) {
                 unsolvedIssueIds.add( issueId.intValue() );
             }
         }
         return unsolvedIssueIds;
    }

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages() {
        List<String> solvedIssueMessages = new ArrayList<>();
        try {
	        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
	        ObjectMapper objectMapper = new ObjectMapper();
	        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
	    } catch (IOException e) {
	        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
	    }    
        
        for (Transaction transaction : transactions) {
            if (transaction.getIssueSolved()) {
                String issueMessage = transaction.getIssueMessage();
                solvedIssueMessages.add(issueMessage);
            }
        }
        return solvedIssueMessages;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<Transaction> getTop3TransactionsByAmount() {

        try {
	        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
	        ObjectMapper objectMapper = new ObjectMapper();
	        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
	    } catch (IOException e) {
	        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
	    }       	
    	
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        int size = sortedTransactions.size();

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (sortedTransactions.get(j).getAmount() < sortedTransactions.get(j + 1).getAmount()) {
                    Transaction temp = sortedTransactions.get(j);
                    sortedTransactions.set(j, sortedTransactions.get(j + 1));
                    sortedTransactions.set(j + 1, temp);
                }
            }
        }

        return sortedTransactions.subList(0, Math.min(3, size));
  
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender() {
        String topSender = null;
        double maxTotalAmount = 0.0;
        Map<String, Double> senderTotalAmounts = new HashMap<>();
        try {
	        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
	        ObjectMapper objectMapper = new ObjectMapper();
	        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
	    } catch (IOException e) {
	        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
	    }    

        for (Transaction transaction : transactions) {
            String senderFullName = transaction.getSenderFullName();
            double transactionAmount = transaction.getAmount();
            
            double currentTotalAmount = senderTotalAmounts.getOrDefault(senderFullName, 0.0) + transactionAmount;
            
            if (currentTotalAmount > maxTotalAmount) {
                maxTotalAmount = currentTotalAmount;
                topSender = senderFullName;
            }
            
            senderTotalAmounts.put(senderFullName, currentTotalAmount);
        }

        return Optional.ofNullable(topSender);
    }

}

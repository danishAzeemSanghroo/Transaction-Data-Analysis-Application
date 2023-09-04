package com.smallworld.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import com.smallworld.TransactionDataFetcher;
import com.smallworld.data.Transaction;

public class UnitTest {
	
	TransactionDataFetcher transactionDataFetcher = new TransactionDataFetcher();
	Transaction transaction = new Transaction();
	private List<Transaction> transactions;
	
	@Test
	public void getTotalTransactionAmountTest() {
		double expectedTotalAmount = 4368.0;
		double actualTotalAmount = transactionDataFetcher.getTotalTransactionAmount();
        assertEquals(expectedTotalAmount, actualTotalAmount,0.01); 
	} 
	
	@Test
	public void getTotalTransactionAmountSentByTest() {
		double expectedTotalAmount = 827.0;
		double actualTotalAmount = transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
        assertEquals(expectedTotalAmount, actualTotalAmount,0.01); 		
	}
	
	@Test
	public void getMaxTransactionAmountTest() {
		double expectedTotalAmount = 985.0;
		double actualTotalAmount = transactionDataFetcher.getMaxTransactionAmount();
        assertEquals(expectedTotalAmount, actualTotalAmount,0.01); 	
	}
	
	@Test
	public void countUniqueClientsTest() {
		Long expectedTotalAmount = 14L;
		Long actualTotalAmount = transactionDataFetcher.countUniqueClients();
        assertEquals(expectedTotalAmount, actualTotalAmount,0.01); 	
	}	
	
	@Test
	public void hasOpenComplianceIssuesTest() {
		boolean hasOpenComplianceIssues = transactionDataFetcher.hasOpenComplianceIssues("Alfie Solomons");
		boolean expected = true;
		assertEquals(expected, hasOpenComplianceIssues);
	}
	
	@Test
	public void getTransactionsByBeneficiaryNameTest() {
		Map actualDataMap = transactionDataFetcher.getTransactionsByBeneficiaryName();
		Map<String, Transaction> expectedDataMap = new HashMap<>();
		expectedDataMap.put("Test",transaction);
		assertEquals(expectedDataMap, actualDataMap);
	}
	
	@Test
	public void getUnsolvedIssueIdsTest() {
		Set<Integer> actualUnsolvedIssueIds = transactionDataFetcher.getUnsolvedIssueIds();
		Set<Integer> expectedUnsolvedIssueIds = new HashSet<>();
		expectedUnsolvedIssueIds.add(11);
		assertEquals(expectedUnsolvedIssueIds, actualUnsolvedIssueIds);
	}
	
	@Test
	public void getAllSolvedIssueMessagesTest() {
		List<String> actualData = transactionDataFetcher.getAllSolvedIssueMessages();
		List<String> expectedData = new ArrayList<>();
		expectedData.add("Test");
		assertEquals(expectedData, actualData);
	}
	
	@Test
	public void getTop3TransactionsByAmountTest() {
		List<Transaction> actualSortedTransactions = transactionDataFetcher.getTop3TransactionsByAmount();
//        try {
//	        String json = new String(Files.readAllBytes(Paths.get("transactions.json")));
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        transactions = objectMapper.readValue(json, new TypeReference<List<Transaction>>() {});
//	    } catch (IOException e) {
//	        throw new RuntimeException("---->>>>>>>>-----Failed to load transaction data -->>>--", e);
//	    } 
		transactions.add(new Transaction(3L, 200L, "Sender1", 30L, "Beneficiary3", 50L, 1L, true, "Message1"));
		List<Transaction> expctedSortedTransactions = new ArrayList<>(transactions);
		assertEquals(expctedSortedTransactions, actualSortedTransactions);
	}
	
	@Test
	public void getTopSenderTest(){
	        Optional<String> topSender = transactionDataFetcher.getTopSender();
	        Optional<String> expectedTopSender = Optional.of("Sender1");
	        assertEquals(expectedTopSender, topSender);
	}

}

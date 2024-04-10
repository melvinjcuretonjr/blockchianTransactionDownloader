package org.example.fetcher;

import org.example.objects.TransactionData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MeowTransactionFetcher extends TransactionFetcher{

    private static final String MEOW_API_URL = "https://explorer.mewccrypto.com/address/";
    private BigDecimal defaultPrice = BigDecimal.ZERO;

    public MeowTransactionFetcher(String meowAddress) {
        super(meowAddress);
    }
    @Override
    public List<TransactionData> fetchTransactions() throws IOException {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = MEOW_API_URL + address;
        JSONObject json = new JSONObject(new URL(url).openStream().readAllBytes());
        JSONArray transactions = json.getJSONArray("transactions");
        for (int i = 0; i < transactions.length(); i++) {
//            JSONObject transaction = transactions.getJSONObject(i);
//            String transactionId = transaction.getString("txid");
//            Long timestamp = transaction.getLong("time");
//            Long amount = transaction.getLong("value");
//            // Do something with the transaction data
//            transactionDataList.add(new TransactionData("mewc",transactionId,timestamp,amount,defaultPrice));
        }
        return transactionDataList;
    }

}


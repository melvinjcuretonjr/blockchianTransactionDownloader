package org.example.fetcher;

import org.example.objects.TransactionData;
import org.example.util.JsonReaderUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RadiantTransactionFetcher extends TransactionFetcher{

    private static final String RADIANT_EXPLORER_URL = "https://radiantexplorer.com/ext/getaddresstxs/";
    private static final String ENDING = "/0/1000";

    public RadiantTransactionFetcher(String radiantAddress) {
        super(radiantAddress);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = RADIANT_EXPLORER_URL + address + ENDING;
        JSONArray transactions = JsonReaderUtil.readJsonArrayFromUrl(url);

        for (int i = 0; i < transactions.length(); i++) {
            JSONObject transaction = transactions.getJSONObject(i);
            BigDecimal amount = transaction.getBigDecimal("sent");
            String transactionId = transaction.getString("txid");
            long timestamp = transaction.getLong("timestamp");

           TransactionData transactionData = new TransactionData("rxd", transactionId, timestamp, amount, BigDecimal.ZERO);
           transactionDataList.add(transactionData);
        }
        return transactionDataList;
    }
}

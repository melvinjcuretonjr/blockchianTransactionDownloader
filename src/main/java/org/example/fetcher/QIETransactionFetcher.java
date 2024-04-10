package org.example.fetcher;

import org.example.objects.TransactionData;
import org.example.util.JsonReaderUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class QIETransactionFetcher extends TransactionFetcher{

    private static final String QIE_EXPLORER_URL = "https://mainnet.qiblockchain.online/api?module=account&action=txlist&address=";

    public QIETransactionFetcher(String qieAddress) {
        super(qieAddress);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = QIE_EXPLORER_URL + address;
        JSONObject jsonObject = JsonReaderUtil.readJsonObjectFromUrl(url);
        JSONArray transactions = jsonObject.getJSONArray("result");

        for (int i = 0; i < transactions.length(); i++) {
            JSONObject transaction = transactions.getJSONObject(i);
            String transactionId = transaction.getString("hash");
            long timestamp = transaction.getLong("timeStamp");

            BigDecimal formattedAmount = transaction.getBigDecimal("value");

            BigDecimal amount = formattedAmount.movePointLeft(18);

           TransactionData transactionData = new TransactionData("qie", transactionId, timestamp, amount, BigDecimal.ZERO);
           transactionDataList.add(transactionData);
        }
        return transactionDataList;
    }
}

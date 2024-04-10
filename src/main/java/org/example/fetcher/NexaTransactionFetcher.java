package org.example.fetcher;

import org.example.objects.TransactionData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NexaTransactionFetcher extends TransactionFetcher{

    private static final String NEXA_ADDRESS_EXPLORER_URL = "https://explorer.nexa.org/address/";
    private static final String NEXA_TRANSACTION_EXPLORER_URL = "https://explorer.nexa.org/tx/";

    public NexaTransactionFetcher(String address) {
        super(address);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();

        String transactionUrl = NEXA_ADDRESS_EXPLORER_URL + address;
        Document transactionDoc = Jsoup.connect(transactionUrl).get();
        Element transactionElement = transactionDoc.select("code").get(1);
        JSONObject transactionJsonObject = new JSONObject(transactionElement.toString().replace("<code class=\"json bg-light\">", "").replace("</code>",""));
        JSONArray transactions = transactionJsonObject.getJSONArray("txids");
        for (int i = 0; i < transactions.length(); i++) {
            String transactionId = transactions.get(i).toString();

            String detailsUrl = NEXA_TRANSACTION_EXPLORER_URL + transactionId;
            Document detailsDoc = Jsoup.connect(detailsUrl).get();
            Element detailsElement = detailsDoc.select("code").get(0);
            JSONObject detailsJsonObject = new JSONObject(detailsElement.toString().replace("<code class=\"json bg-light\" data-lang=\"json\">", "").replace("</code>",""));
            JSONArray transactionDetails = detailsJsonObject.getJSONArray("vout");

            for (int n = 0; n < transactionDetails.length(); n++) {
                JSONObject detail = transactionDetails.getJSONObject(n);
                JSONObject subDetail = detail.getJSONObject("scriptPubKey");
                JSONArray subDetailAddresses = subDetail.getJSONArray("addresses");
                if (subDetailAddresses.toList().contains(address)) {
                    long timestamp = detailsJsonObject.getLong("time");
                    BigDecimal amount = detail.getBigDecimal("value");
                    TransactionData transactionData = new TransactionData("nexa", transactionId, timestamp, amount, BigDecimal.ZERO);
                    transactionDataList.add(transactionData);
                    break;
                }
            }
        }
        return transactionDataList;
    }
}

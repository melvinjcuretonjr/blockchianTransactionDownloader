package org.example.fetcher;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.example.objects.TransactionData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PktTransactionFetcher extends TransactionFetcher{

    private static final String PKT_API_URL = "https://explorer.pkt.cash/api/v1/PKT/pkt/address/";
    private static final String PKT_API_MIDDLE = "/income/2021-11-01/";
    private static final String PKT_API_END = "?mining=included";
    private BigDecimal defaultPrice = BigDecimal.ZERO;

    public PktTransactionFetcher(String pktAddress) {
        super(pktAddress);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws IOException {
        String currentDateTime = DateTimeFormatter.ofPattern("YYYY-MM-dd", Locale.ENGLISH).format(java.time.LocalDateTime.now());
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = PKT_API_URL
                + address
                + PKT_API_MIDDLE
                + currentDateTime
                + PKT_API_END;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();

        JSONObject json = new JSONObject(jsonData);
        JSONArray transactions = json.getJSONArray("results");
        for (int i = 0; i < transactions.length(); i++) {
            JSONObject transaction = transactions.getJSONObject(i);
            String transactionId = null;

            ZonedDateTime zdt = java.time.ZonedDateTime.parse(transaction.getString("date"));

            Long timestamp = zdt.toEpochSecond();

            BigDecimal amount = BigDecimal.ZERO;
            BigDecimal formattedAmount = new BigDecimal(transaction.getLong("received"));
            formattedAmount = formattedAmount.movePointLeft(9);
            amount = formattedAmount;

            // Do something with the transaction data
            transactionDataList.add(new TransactionData("pkt",transactionId,timestamp,amount,defaultPrice));
        }
        return transactionDataList;
    }

}


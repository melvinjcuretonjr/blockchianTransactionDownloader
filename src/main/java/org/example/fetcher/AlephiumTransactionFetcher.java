package org.example.fetcher;

import org.example.objects.TransactionData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AlephiumTransactionFetcher extends TransactionFetcher{

    private static final String ALEPHIUM_EXPLORER_URL = "https://explorer.alephium.org/addresses/";

    public AlephiumTransactionFetcher(String address) {
        super(address);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = ALEPHIUM_EXPLORER_URL + address;

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject json = new JSONObject(response.toString());
            JSONArray transactions = json.getJSONArray("transactions");
            for (int i = 0; i < transactions.length(); i++) {
                JSONObject transaction = transactions.getJSONObject(i);
                String transactionId = transaction.getString("id");
                String blockHeight = transaction.getString("height");
                String timestamp = transaction.getString("time");
                String amount = transaction.getString("amount");
                String fee = transaction.getString("fee");
                // Do something with the transaction data
            }
        } else {
            System.out.println("Failed to fetch transactions from Alephium address: " + responseCode);
        }

//       TransactionData transactionData = new TransactionData("rxd", transactionId, timestamp, amount, BigDecimal.ZERO);
//       transactionDataList.add(transactionData);

        return transactionDataList;
    }
}

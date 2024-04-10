package org.example.fetcher;

import org.example.objects.TransactionData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MeowTransactionFetcher2 extends TransactionFetcher{

    private static final String MEOW_EXPLORER_URL = "https://explorer.mewccrypto.com/address/";

    public MeowTransactionFetcher2(String meowAddress) {
        super(meowAddress);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws IOException {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = MEOW_EXPLORER_URL + address;
        Document doc = Jsoup.connect(url).get();
//        Element element = doc.getElementById("address-txs");
        Elements rows = doc.select("table tbody tr");
//        for (Element row : rows) {
//            Elements cols = row.select("td");
//            if (cols.size() >= 4) {
//                String transactionId = cols.get(0).text();
//                String timestamp = cols.get(1).text();
//                String amount = cols.get(2).text();
//                // Do something with the transaction data
//            }
//        }

        return transactionDataList;
    }
}

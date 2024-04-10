package org.example.fetcher;

import org.example.objects.TransactionData;

import java.util.ArrayList;
import java.util.List;

public class PepewTransactionFetcher extends TransactionFetcher{

    private static final String PEPEW_ADDRESS_EXPLORER_URL = "https://explorer.pepecore.com/ext/getaddress/";
    private static final String PEPEW_TRANSACTION_EXPLORER_URL = "https://explorer.pepecore.com/ext/gettx/";

    public PepewTransactionFetcher(String address) {
        super(address);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
//
//        String transactionUrl = PEPEW_ADDRESS_EXPLORER_URL + address;
//        JSONObject transactions = JsonReaderUtil.readJsonObjectFromUrl(transactionUrl);
//        JSONArray transactionDetails = transactions.getJSONArray("last_txs");
//        for (int i = 0; i < transactionDetails.length(); i++) {
//            JSONObject entry = transactionDetails.getJSONObject(i);
//            String detailsUrl = PEPEW_TRANSACTION_EXPLORER_URL + entry.get("addresses");
//            JSONObject transactionDetail = JsonReaderUtil.readJsonObjectFromUrl(detailsUrl);
//            JSONObject transactionDetail2 = transactionDetail.getJSONObject("tx");
//            JSONArray transactionVIn = transactionDetail2.getJSONArray("vin");
//            JSONArray transactionVOut = transactionDetail2.getJSONArray("vout");
//            for (int n = 0; n < transactionVIn.length(); n++) {
//                JSONObject object = transactionVIn.getJSONObject(n);
//                if (this.address.equalsIgnoreCase(object.getString("addresses"))) {
//                    long timestamp = transactionDetail2.getLong("timestamp");
//                    BigDecimal amount = object.getBigDecimal("amount");
//                    amount = amount.movePointLeft(8);
//                    amount = amount.negate();
//                    TransactionData transactionData = new TransactionData("pepew", entry.getString("addresses"), timestamp, amount, BigDecimal.ZERO);
//                    transactionDataList.add(transactionData);
//                }
//            }
//            for (int m = 0; m < transactionVOut.length(); m++) {
//                JSONObject object = transactionVOut.getJSONObject(m);
//                if (this.address.equalsIgnoreCase(object.getString("addresses"))) {
//                    long timestamp = transactionDetail2.getLong("timestamp");
//                    BigDecimal amount = object.getBigDecimal("amount");
//                    amount = amount.movePointLeft(8);
//                    TransactionData transactionData = new TransactionData("pepew", entry.getString("addresses"), timestamp, amount, BigDecimal.ZERO);
//                    transactionDataList.add(transactionData);
//                }
//            }
//        }
        return transactionDataList;
    }
}

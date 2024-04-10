package org.example.fetcher;

import org.example.objects.TransactionData;
import org.example.util.JsonReaderUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TONTransactionFetcher extends TransactionFetcher{
    private static final String TON_EXPLORER_URL = "https://tonapi.io/v1/blockchain/getTransactions?account=";
    private static final String ENDING = "=25758317000002&minLt=0&limit=1000";

    public TONTransactionFetcher(String address) {
        super(address);
    }

    @Override
    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = TON_EXPLORER_URL + address + ENDING;
        JSONObject jsonObject = JsonReaderUtil.readJsonObjectFromUrl(url);
        JSONArray transactions = (JSONArray) jsonObject.get("transactions");


        for (int i = 0; i < transactions.length(); i++) {
            BigDecimal amount = BigDecimal.ZERO;
            JSONObject transaction = transactions.getJSONObject(i);
            String transactionId = transaction.getString("hash");
            long timestamp = transaction.getLong("utime");
//            JSONArray jsonArray = (JSONArray) transaction.get("in_msg");
//           for (int n = 0; n < jsonArray.length(); n++) {
               JSONObject output = (JSONObject) transaction.get("in_msg");
//               if (StringUtils.equalsIgnoreCase(tonAddress, output.getString("script_public_key_address"))){
                   BigDecimal formattedAmount = new BigDecimal(output.getLong("value"));
                   formattedAmount = formattedAmount.movePointLeft(9);
                   amount = formattedAmount;
//               }
//           }

           TransactionData transactionData = new TransactionData("ton", transactionId, timestamp, amount, BigDecimal.ZERO);
           transactionDataList.add(transactionData);
        }
        return transactionDataList;
    }
}

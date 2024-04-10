package org.example.fetcher;

import org.apache.commons.lang3.StringUtils;
import org.example.objects.TransactionData;
import org.example.util.JsonReaderUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ErgoTransactionFetcher extends TransactionFetcher{

    private static final String ERGO_EXPLORER_URL = "https://api.ergoplatform.com//api/v1/addresses/";
    private static final String ENDING = "/transactions";

    public ErgoTransactionFetcher(String address) {
        super(address);
    }

    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String url = ERGO_EXPLORER_URL + address + ENDING;
        JSONObject jsonObject = JsonReaderUtil.readJsonObjectFromUrl(url);
        JSONArray transactions = jsonObject.getJSONArray("items");

        for (int i = 0; i < transactions.length(); i++) {
            BigDecimal amount = BigDecimal.ZERO;
            String coin = "";
            JSONObject transaction = transactions.getJSONObject(i);
            String transactionId = transaction.getString("id");
            long timestamp = transaction.getLong("timestamp");
            JSONArray outputs = (JSONArray) transaction.get("outputs");
            for (int n = 0; n < outputs.length(); n++) {
                JSONObject output = outputs.getJSONObject(n);
                if (StringUtils.equalsIgnoreCase(address, output.getString("address"))) {
                    if (!output.getJSONArray("assets").isEmpty()) {
                        JSONArray assets = output.getJSONArray("assets");
                        for (int m = 0; m < assets.length(); m++) {
                            JSONObject asset = assets.getJSONObject(m);
                            BigDecimal formattedAmount = new BigDecimal(asset.getLong("amount"));
                            formattedAmount = formattedAmount.movePointLeft(asset.getInt("decimals"));
                            amount = formattedAmount;
                            coin = assets.getJSONObject(0).getString("name");
                            TransactionData transactionData = new TransactionData(coin, transactionId, timestamp, amount, BigDecimal.ZERO);
                            transactionDataList.add(transactionData);
                        }
                    }
                    BigDecimal formattedAmount = output.getBigDecimal("value");
                    int moveLeft = 9;
                    if (output.has("decimals")) {
                        moveLeft = output.getInt("decimals");
                    }
                    formattedAmount = formattedAmount.movePointLeft(moveLeft);
                    amount = formattedAmount;
                    coin = "erg";
                    TransactionData transactionData = new TransactionData(coin, transactionId, timestamp, amount, BigDecimal.ZERO);
                    transactionDataList.add(transactionData);

                }
            }
        }
//        System.out.println("Task scheduled to execute after 10 seconds at : " + java.time.ZonedDateTime.now());
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//
//        int taskNumber = 0;
//        int initDelay = 0;
//        int delay = 10;
//
//        for (TransactionData transactionData : transactionDataList) {
//            APIWrapper apiWrapper = new APIWrapper("App-Task-" + taskNumber ,transactionData);
//            taskNumber++;
//            ScheduledFuture<?> result = executor.schedule(apiWrapper, delay, TimeUnit.SECONDS);
//            delay = delay + 10;
//            if (taskNumber==2){
//                break;
//            }
//        }
//        System.out.println("Shutdown and await requested at : " + java.time.ZonedDateTime.now());
//        shutdownAndAwaitTermination(executor);
        return transactionDataList;
    }

//    static void shutdownAndAwaitTermination(ExecutorService executorService) {
//        executorService.shutdown();
//        try {
//            if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
//                executorService.shutdownNow();
//            }
//        } catch (InterruptedException ie) {
//            executorService.shutdownNow();
//            Thread.currentThread().interrupt();
//        }
//    }

}

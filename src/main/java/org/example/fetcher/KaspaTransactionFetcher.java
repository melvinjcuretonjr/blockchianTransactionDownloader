package org.example.fetcher;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.objects.TransactionData;
import org.example.util.JsonReaderUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class KaspaTransactionFetcher extends TransactionFetcher {
    private static final String KASPA_EXPLORER_URL = "https://api.kaspa.org/addresses/";
    private static final String ENDING = "/full-transactions?limit=500&offset=0&resolve_previous_outpoints=no";
    private static final String KASPA_EXPLORER_INPUT_URL_START = "https://api.kaspa.org/transactions/";
    private static final String KASPA_EXPLORER_INPUT_URL_END = "?inputs=true&outputs=false&resolve_previous_outpoints=full";
    private static final String KASPA_EXPLORER_OUTPUT_URL_START = "https://api.kaspa.org/transactions/";
    private static final String KASPA_EXPLORER_OUTPUT_URL_END = "?inputs=false&outputs=true&resolve_previous_outpoints=full";

    public KaspaTransactionFetcher(String kaspaAddress) {
        super(kaspaAddress);
    }

    public List<TransactionData> fetchTransactions() throws Exception {
        List<TransactionData> transactionDataList = new ArrayList<>();
        String ExplorerUrl = KASPA_EXPLORER_URL + address + ENDING;
        JSONArray transactions = JsonReaderUtil.readJsonArrayFromUrl(ExplorerUrl);

        for (int i = 0; i < transactions.length(); i++) {
            BigDecimal amount = BigDecimal.ZERO;
            JSONObject transaction = transactions.getJSONObject(i);
            String transactionId = transaction.getString("transaction_id");

            if (BooleanUtils.toBoolean(transaction.get("is_accepted").toString()) == Boolean.FALSE){
                break;
            }

            //Build transaction url to get outputs
            String outputUrl = KASPA_EXPLORER_OUTPUT_URL_START + transactionId + KASPA_EXPLORER_OUTPUT_URL_END;
            JSONObject outputTransactionObject = JsonReaderUtil.readJsonObjectFromUrl(outputUrl);

            long timestamp = transaction.getLong("block_time");

            if(!outputTransactionObject.isEmpty() && outputTransactionObject.get("outputs")!=null) {
                JSONArray jsonArrayOutputs = (JSONArray) outputTransactionObject.get("outputs");
                for (int n = 0; n < jsonArrayOutputs.length(); n++) {
                    JSONObject output = jsonArrayOutputs.getJSONObject(n);
                    if (StringUtils.equalsIgnoreCase(address, output.getString("script_public_key_address"))) {
                        BigDecimal formattedAmount = new BigDecimal(output.getLong("amount"));
                        formattedAmount = formattedAmount.movePointLeft(8);
                        amount = formattedAmount;

                        if (!BigDecimal.ZERO.equals(amount)) {
                            TransactionData transactionData = new TransactionData("kas", transactionId, timestamp, amount, BigDecimal.ZERO);
                            transactionDataList.add(transactionData);
                        }
                    }
                }
            }

            //Build transaction url to get inputs
            String inputUrl = KASPA_EXPLORER_INPUT_URL_START + transactionId + KASPA_EXPLORER_INPUT_URL_END;
            JSONObject inputTransactionObjet = JsonReaderUtil.readJsonObjectFromUrl(inputUrl);

            if (!inputTransactionObjet.isEmpty() && inputTransactionObjet.get("inputs")!=null) {
                JSONArray jsonArrayInputs = (JSONArray) inputTransactionObjet.get("inputs");
                for (int n = 0; n < jsonArrayInputs.length(); n++) {
                    JSONObject input = jsonArrayInputs.getJSONObject(n);
                    if (StringUtils.equalsIgnoreCase(address, input.getString("previous_outpoint_address"))) {
                        BigDecimal formattedAmount = new BigDecimal(input.getLong("previous_outpoint_amount"));
                        formattedAmount = formattedAmount.movePointLeft(8);
                        amount = formattedAmount.multiply(new BigDecimal("-1"));

                        if (!BigDecimal.ZERO.equals(amount)) {
                            TransactionData transactionData = new TransactionData("kas", transactionId, timestamp, amount, BigDecimal.ZERO);
                            transactionDataList.add(transactionData);
                        }
                    }
                }
            }
        }

        return transactionDataList;
    }

}

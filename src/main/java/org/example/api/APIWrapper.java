package org.example.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.objects.TransactionData;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class APIWrapper implements Runnable {
    private static Logger logger = LogManager.getLogger(APIWrapper.class);
    private static final String PRICE_API_URL = "https://rest.coinapi.io/v1/exchangerate/KAS/USD";
    private static final String apiKey = "BAB4CB18-D2B9-4B06-94B7-99A27C8D0DFE";
    private final TransactionData transactionData;
    private final String name;

    public APIWrapper(String name, TransactionData transactionData){
        this.transactionData = transactionData;
        this.name = name;
    }

    @Override
    public void run() {
        logger.info("Task " + name + " starting API call at " + java.time.ZonedDateTime.now());
        try {
            LocalDateTime pointInTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(transactionData.getTimestamp()),
                    TimeZone.getDefault().toZoneId());
//            String pointInTimeString = DateTimeFormatter.ofPattern("dd-MM-YYYY", Locale.ENGLISH).format(pointInTime);
//            String urlString = PRICE_API_URL + "?date=" + pointInTime;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(PRICE_API_URL)
                    .addHeader("X-CoinAPI-Key", apiKey)
                    .build();

            Response response = client.newCall(request).execute();

            String jsonData = response.body().string();

            JSONObject json = new JSONObject(jsonData);
            transactionData.setPrice((BigDecimal) json.get("rate"));
        } catch (Exception ex){
            logger.error("An error occurred while fetching price data",ex);
        }
        logger.info("Task " + name + " ending API call at " + java.time.ZonedDateTime.now());
    }
}

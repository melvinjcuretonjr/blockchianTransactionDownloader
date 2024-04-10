package org.example.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.APIWrapper;
import org.example.objects.TransactionData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class FileDataCreator {
    private static final Logger logger = LogManager.getLogger(APIWrapper.class);
    public static List<String> createKoinlyFileData(List <TransactionData> transactionDataList) {
        List<String> dataToReturn = new ArrayList<>();

        logger.info("Writing file data started: " + java.time.ZonedDateTime.now());

        // Write the headers to the CSV file
        String header = "Koinly Date" +
                "," +
                "Amount" +
                "," +
                "Currency" +
                "," +
                "Label" +
                "," +
                "TxHash" +
                ",";

        //Write new line
        dataToReturn.add(header);

        StringBuilder body = new StringBuilder();

        // Write the values to the CSV file
        for (TransactionData transactionData : transactionDataList) {
            body = new StringBuilder();

            switch (transactionData.getCoin()) {
                case "erg":
                case "eVOTE2":
                case "ergopad":
                case "COMET":
                case "NETA":
                case "kas":
                    body.append(LocalDateTime.ofInstant(Instant.ofEpochMilli(transactionData.getTimestamp()), TimeZone.getDefault().toZoneId()));
                    break;
                default:
                    body.append(LocalDateTime.ofInstant(Instant.ofEpochSecond(transactionData.getTimestamp()), TimeZone.getDefault().toZoneId()));
                    break;
            }
            body.append(",");
            body.append(transactionData.getAmount());
            body.append(",");
            body.append(transactionData.getCoin());
            body.append(",");
            body.append("Mining");
            body.append(",");
            if(transactionData.getTransactionId()!=null && !StringUtils.equalsIgnoreCase("null",transactionData.getTransactionId())){
                body.append(transactionData.getTransactionId());
            }
            body.append(",");
            dataToReturn.add(body.toString());
        }

        logger.info("Writing file data ended: " + java.time.ZonedDateTime.now());

        return dataToReturn;
    }
}

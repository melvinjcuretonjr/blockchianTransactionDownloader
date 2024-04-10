package org.example.fetcher;

import org.example.objects.TransactionData;

import java.util.List;

public abstract class TransactionFetcher {
    public static final String BLOCKCHAIN_API_URL = "";
    public String address;

    public TransactionFetcher(String address) {
        this.address = address;
    }

    public abstract List<TransactionData> fetchTransactions() throws Exception;
}


package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.fetcher.KaspaTransactionFetcher;
import org.example.objects.TransactionData;
import org.example.util.FileDataCreator;
import org.example.util.FileWriterUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    
    public static void main(String[] args) throws Exception{
        logger.info("-------------Transaction downloading has begun----------------------");

        List<TransactionData> transactionDataList = new ArrayList<>();
        String fileNameBasePath = "C:\\Users\\melvi\\OneDrive\\Desktop\\BlockchainTransactionDownloader";

        logger.info("-------------Staring on Kaspa----------------------");
        KaspaTransactionFetcher kaspaTransactionFetcher = new KaspaTransactionFetcher("kaspa:qqg2rhpl72u2cwrmke2tnz3jm5eccgrnk9rx4kfc69wsgntfrzeejc5eyqnzp");
        transactionDataList.addAll(kaspaTransactionFetcher.fetchTransactions());

        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"kas",FileDataCreator.createKoinlyFileData(transactionDataList));

        kaspaTransactionFetcher = new KaspaTransactionFetcher("kaspa:qqsdn9ufka7gvp87tqgzlrqxdt65hrdv6fcp6vjpwf5wkxrp8ukj2pzdcpyvk ");
        transactionDataList.addAll(kaspaTransactionFetcher.fetchTransactions());

        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"kas",FileDataCreator.createKoinlyFileData(transactionDataList));

        kaspaTransactionFetcher = new KaspaTransactionFetcher("kaspa:qp0jy05h0fkwcet2nlvmvg70pz82l9pcg0qhfea2922wkk2lljfzz0a50kd2s");
        transactionDataList.addAll(kaspaTransactionFetcher.fetchTransactions());

        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"kas",FileDataCreator.createKoinlyFileData(transactionDataList));

        kaspaTransactionFetcher = new KaspaTransactionFetcher("kaspa:qqh29udvpfj23quvsg0gqux7e0kvgqy98fhm4vax97dy7zxw3kmsc80y9rp5y");
        transactionDataList.addAll(kaspaTransactionFetcher.fetchTransactions());

        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"kas",FileDataCreator.createKoinlyFileData(transactionDataList));
        logger.info("-------------Finished Kaspa----------------------");
//
//        logger.info("-------------Staring on Ton----------------------");
//        TONTransactionFetcher tonTransactionFetcher = new TONTransactionFetcher("EQBWyg2zWuHxbxrp9wF9JP3hqX0pddzc7er2RvLlp7SvXQaE&maxLt");
//        transactionDataList.clear();
//        transactionDataList.addAll(tonTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"ton", FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished Ton----------------------");
//
//        logger.info("-------------Staring on PKT----------------------");
//        PktTransactionFetcher pktTransactionFetcher = new PktTransactionFetcher("pkt1qmes2daht33evkw7d40gqdxs9h4rxl4xrfdpy0l");
//        transactionDataList.clear();
//        transactionDataList.addAll(pktTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"pkt", FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished PKT----------------------");
//
//        logger.info("-------------Staring on Neox----------------------");
//        NEOXTransactionFetcher neoxTransactionFetcher = new NEOXTransactionFetcher("GMgUMSbaTShVzcqYYABwTCpF2M2HNKBXhf");
//        transactionDataList.clear();
//        transactionDataList.addAll(neoxTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"neox",FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished Neox----------------------");
//
//        logger.info("-------------Staring on QIE----------------------");
//        QIETransactionFetcher qieTransactionFetcher = new QIETransactionFetcher("0xbD2D4e1B41677c1F0a287Fc4f606d4320b688613");
//        transactionDataList.clear();
//        transactionDataList.addAll(qieTransactionFetcher.fetchTransactions());
//        qieTransactionFetcher = new QIETransactionFetcher("0x1e9a9a147Fad27d9f702eB7445092F19639F132F");
//        transactionDataList.addAll(qieTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"qie",FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished QIE----------------------");
//
//        logger.info("-------------Staring on Radiant----------------------");
//        RadiantTransactionFetcher radiantTransactionFetcher = new RadiantTransactionFetcher("17tSMvHYkZy3GM3PPXtg3Jww9knUVTZAT1");
//        transactionDataList.clear();
//        transactionDataList.addAll(radiantTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"rxd",FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished Radiant----------------------");
//
//        logger.info("-------------Staring on Ergo----------------------");
//        ErgoTransactionFetcher ergoTransactionFetcher = new ErgoTransactionFetcher("9evUAbjMeSpv94uXznFTaU6YBr5DEyYp6qfU2tBJVyKTcPnXccV");
//        transactionDataList.clear();
//        transactionDataList.addAll(ergoTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"erg",FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished Ergo----------------------");
//
//        logger.info("-------------Staring on Nexa----------------------");
//        NexaTransactionFetcher nexaTransactionFetcher = new NexaTransactionFetcher("nexa:nqtsq5g5p5gdyg9g2mxzqsset362m5f0548qwx7qltxfatq3");
//        transactionDataList.clear();
//        transactionDataList.addAll(nexaTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"nexa", FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished Nexa----------------------");
//-------------- Do not use ------------------
//        logger.info("-------------Staring on Pepew----------------------");
//        PepewTransactionFetcher pepewTransactionFetcher = new PepewTransactionFetcher("P9PypEeKHqULh6hEbcLcxEcZcRxsJ7Y9VC");
//        transactionDataList.clear();
//        transactionDataList.addAll(pepewTransactionFetcher.fetchTransactions());
//
//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"Pepew", FileDataCreator.createKoinlyFileData(transactionDataList));
//        logger.info("-------------Finished Pepew----------------------");


//        logger.info("-------------Staring on Alephium----------------------");
//        AlephiumTransactionFetcher alephiumTransactionFetcher = new AlephiumTransactionFetcher("1AFZMabBb6Eq9uzrSnsCArAw1Uae13PrqU4BSNCBJMM8v");
//        transactionDataList.clear();
//        transactionDataList.addAll(alephiumTransactionFetcher.fetchTransactions());

//        FileWriterUtil.writeDataAtOnce(fileNameBasePath,"alph",FileDataCreator.createKoinlyFileData(transactionDataList));
        logger.info("-------------Finished Alephium----------------------");

        logger.info("-------------Transaction downloading has finished----------------------");
    }
}
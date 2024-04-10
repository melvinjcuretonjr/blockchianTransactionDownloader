package org.example.util;

import com.opencsv.CSVWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileWriterUtil {
    private static final Logger logger = LogManager.getLogger(FileWriterUtil.class);

    public static void writeDataAtOnce(String filePathBase, String coin, List<String> dataToWrite)
    {
        logger.info("-------------File writing has begun for " + coin + "---------------------");
        String timeStamp = java.time.ZonedDateTime.now().format(DateTimeFormatter.ofPattern("MMddyyyy_HH_mm_ss"));
        String fileNameStart = String.format("transactions_%s_",coin);
        String fileNameExt = ".csv";
        String fileName = fileNameStart + timeStamp + fileNameExt;
        // first create file object for file placed at location
        // specified by filepath
        File file = Paths.get(filePathBase,fileName).toFile();

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            for(String line : dataToWrite){
                writer.writeNext(StringUtils.split(line,","));
            }

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.info("-------------File writing has completed for " + coin + "---------------------");
    }
}

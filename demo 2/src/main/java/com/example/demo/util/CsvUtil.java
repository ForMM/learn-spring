package com.example.demo.util;

import com.example.demo.controller.AccountController;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvUtil {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);
    public static  <T> List<T> getCsvData(InputStream inputStream, Class<T> clazz) {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(inputStream);
        } catch (Exception e) {
            logger.error("",e);
        }

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);

        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(in)
                .withSeparator(',')
                .withQuoteChar('\'')
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }

}

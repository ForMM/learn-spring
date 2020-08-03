package com.example.demo;

import com.example.demo.controller.DocDealController;
import com.example.demo.util.csvpojo.DataAndTypeCsv;
import com.example.demo.util.CsvUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class TestCsvToTxy {
    private static Logger logger = LoggerFactory.getLogger(DocDealController.class);
    @Test
    public void testHashMap(){


        FileWriter out = null;
        File file = new File("C:\\Users\\PF0W8JF8\\Desktop\\上汽\\存证编号_1.csv");
        try {
            InputStream is = new FileInputStream(file);
            List<DataAndTypeCsv> csvData = CsvUtil.getCsvData(is, DataAndTypeCsv.class);
            logger.info("总数据：",csvData.size());

            String line = System.getProperty("line.separator");
            StringBuilder sb = new StringBuilder();
            for (int i = 0;i<csvData.size();i++){
                DataAndTypeCsv dataAndTypeCsv = csvData.get(i);
                logger.info("contractId:{}",dataAndTypeCsv.getContract_id());
                logger.info("reqTime:{}",dataAndTypeCsv.getReq_time());
                logger.info("witnessInfo:{}",dataAndTypeCsv.getWitness_info());

                String witness_info = dataAndTypeCsv.getWitness_info();
                JsonObject jsonObject = new Gson().fromJson(witness_info, JsonObject.class);
                JsonObject fileInfo = jsonObject.get("fileInfo").getAsJsonObject();
                String caseNum = fileInfo.get("caseNum").getAsString();
                logger.info("caseNum:{}",caseNum);

                String result = dataAndTypeCsv.getReq_time() +"  "+dataAndTypeCsv.getContract_id()+"  "+caseNum;
                sb.append(result).append("\r\n");
            }

            File csvfile = new File("C:\\Users\\PF0W8JF8\\Desktop\\上汽\\存证编号11.txt");
            out = new FileWriter(csvfile);
            if(!csvfile.exists()){
                csvfile.createNewFile();
            }
            out.write(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

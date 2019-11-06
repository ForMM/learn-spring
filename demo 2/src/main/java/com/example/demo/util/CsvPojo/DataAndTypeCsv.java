package com.example.demo.util.CsvPojo;

import com.opencsv.bean.CsvBindByName;

public class DataAndTypeCsv {
    @CsvBindByName(column = "req_time")
    private String req_time;

    @CsvBindByName(column = "contract_id")
    private String contract_id;

    @CsvBindByName(column = "witness_info")
    private String witness_info;

    public String getReq_time() {
        return req_time;
    }

    public void setReq_time(String req_time) {
        this.req_time = req_time;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public String getWitness_info() {
        return witness_info;
    }

    public void setWitness_info(String witness_info) {
        this.witness_info = witness_info;
    }

    public DataAndTypeCsv() {
    }

}

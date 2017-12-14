package com.lance.demo.springboot.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoanReq {
    private String transNo;
    private String transMoney;
    private String toAccName;
    private String toAccNo;
    private String toBankName;
    private String toProName;
    private String toCityName;
    private String toAccDept;
    private String transCardId;
    private String transMobile;
    private String transSummary;

}

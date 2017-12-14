package com.lance.demo.springboot.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.lance.demo.springboot.util.EncryptUtil;
import com.lance.demo.springboot.util.JsonUtil;
import lombok.Data;
import lombok.Getter;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayReq {
    private String memberId;

    private String terminalId;

    private String dataType;

    private String txnType;

    private String txnSubType;

    private String dataContent;

    private String version;

    public PayReq(){
        this(new Builder());
    }

    public PayReq(Builder builder) {
        this.memberId = builder.memberId;
        this.terminalId = builder.terminalId;
        this.dataType = builder.dataType;
        this.txnType = builder.txnType;
        this.txnSubType = builder.txnSubType;
        this.version = builder.version;
        this.dataContent = builder.dataContent;
    }


    public static final class Builder<T> {
        private String memberId;

        private String terminalId;

        private String dataType;

        private String txnType;

        private String txnSubType;

        private String version;

        private String dataContent;

        private PublicKey publicKey;

        public Builder() {
            this.dataType = "json";
            this.version = "4.0.0";
        }

        public Builder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder txnType(String txnType) {
            this.txnType = txnType;
            return this;
        }

        public Builder txnSubType(String txnSubType) {
            this.txnSubType = txnSubType;
            return this;
        }

        public Builder publicKey(PublicKey publicKey) {
            this.publicKey = publicKey;
            return this;
        }

        public Builder content(T t) throws Exception {
            if (publicKey == null) {
                throw new Exception("public key not provide");
            }
            TransReqData<T> transReqData = new TransReqData<>();
            transReqData.setTransContent(t);
            TransContent transContent = new TransContent();
            List<TransReqData<T>> transReqDatas = new ArrayList<>();
            transReqDatas.add(transReqData);
            transContent.setTransReqDatas(transReqDatas);

            String json = JsonUtil.getJson(transContent);

            this.dataContent = EncryptUtil.encrypt(EncryptUtil.base64Encode(json),publicKey);
            return this;
        }

        public String buildJson() throws JsonProcessingException {
            PayReq payReq = new PayReq(this);
            return JsonUtil.getJson(payReq);
        }

        public PayReq build() {
            return new PayReq(this);
        }
    }

    @Data
    static class TransContent<T>{
        private List<TransReqData<T>> transReqDatas;
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    @Data
    static class TransReqData<T>{
        private T transContent;
    }
}

package com.lance.demo.springboot.controller;

import com.lance.demo.springboot.util.EncryptUtil;
import com.lance.demo.springboot.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    private RestTemplate restTemplate;
    private String url = "http://paytest.baofoo.com/baofoo-fopay/pay/BF0040001.do";

    @PostMapping("/pay")
    public String pay(@RequestBody LoanReq loanReq) throws Exception{
        PayReq payReq = new PayReq.Builder<LoanReq>()
                .memberId("100000276")
                .terminalId("100000990")
                .publicKey(EncryptUtil.loadPublicKey("certificate/bfkey_100000276@@100000990.cer"))
                .content(loanReq)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = JsonUtil.getMap(payReq);
        log.info("map:{}",map);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> payRspResponseEntity = restTemplate.postForEntity(url, request, String.class);
        log.info(payRspResponseEntity.toString());
        return "success";
    }
}

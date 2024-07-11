package com.chowe.depotmanagementsystem.api;

import com.chowe.depotmanagementsystem.bulksms.client.SmsClient;
import com.chowe.depotmanagementsystem.bulksms.data.SmsRequest;
import com.chowe.depotmanagementsystem.bulksms.data.SmsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bulksms-v1")
public class ClickatelSmsApi {
    private final SmsClient client;

    @PostMapping("/send-single-sms")
    public ResponseEntity<SmsResponse> sendSingleSms(@RequestBody SmsRequest smsRequest){
        SmsResponse smsResponse = client.sendSms(smsRequest);
        return new ResponseEntity<>(smsResponse, HttpStatus.OK);
    }
}

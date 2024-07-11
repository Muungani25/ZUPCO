package com.chowe.depotmanagementsystem.bulksms.client;


import com.chowe.depotmanagementsystem.bulksms.data.SmsRequest;
import com.chowe.depotmanagementsystem.bulksms.data.SmsResponse;
import com.chowe.depotmanagementsystem.service.util.MobileNumberFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsClientImpl implements SmsClient {

    private final RestTemplate restTemplate;

    @Value("${clickatel.http.api.key}")
    private String HTTP_API_KEY;
    @Value("${clickatel.http.send.url}")
    private String SINGLE_SMS_URL;


    @Override
    public SmsResponse sendSms(SmsRequest smsRequest) {

        ResponseEntity<SmsResponse> responseEntity = restTemplate.getForEntity(
                SINGLE_SMS_URL,
                SmsResponse.class,
                HTTP_API_KEY,
                MobileNumberFormatter.formatNumberToInternational(smsRequest.getTo()),
                smsRequest.getContent());
        log.info("sms response: {}",responseEntity.getBody());
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        throw new RuntimeException("Failed to send sms message, error from server");
    }
}

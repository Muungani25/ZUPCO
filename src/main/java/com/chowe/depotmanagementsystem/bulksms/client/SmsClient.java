package com.chowe.depotmanagementsystem.bulksms.client;


import com.chowe.depotmanagementsystem.bulksms.data.SmsRequest;
import com.chowe.depotmanagementsystem.bulksms.data.SmsResponse;

public interface SmsClient {
    SmsResponse sendSms(SmsRequest smsRequest);
}

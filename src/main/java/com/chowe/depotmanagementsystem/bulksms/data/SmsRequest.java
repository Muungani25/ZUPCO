package com.chowe.depotmanagementsystem.bulksms.data;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SmsRequest {
    private String to;
    private String content;
}

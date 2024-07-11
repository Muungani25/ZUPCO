package com.chowe.depotmanagementsystem.espclient;

import java.math.BigDecimal;

public interface EspClient {
    Object sendDispenseFuelSignalToEsp(BigDecimal fuelLitres);
}

package com.chowe.depotmanagementsystem.service.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class DriverIdGenerator {
    private static final String PREFIX = "ZUP";
    private static final int MAX_SEQUENCE = 999; // Maximum three-digit number
    private static final int MAX_RETRIES = 10; // Maximum retries in case of collision

    private static final AtomicInteger sequence = new AtomicInteger(0);

    public static String generateDriverId() {
        int uniqueNumber = getNextUniqueNumber();
        return PREFIX + String.format("%03d", uniqueNumber);
    }

    private static int getNextUniqueNumber() {
        for (int i = 0; i < MAX_RETRIES; i++) {
            int currentSequence = sequence.get();
            int nextSequence = (currentSequence + 1) % (MAX_SEQUENCE + 1);
            if (sequence.compareAndSet(currentSequence, nextSequence)) {
                return nextSequence;
            }
        }
        throw new RuntimeException("Failed to generate a unique number.");
    }
}

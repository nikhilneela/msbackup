package org.lockermanagementsystem.models;

import org.lockermanagementsystem.utils.AlphaNumericCodeGenerator;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class OneTimeCode {
    private String code;
    private LocalDateTime validUpto;

    public OneTimeCode() {
        this.code = AlphaNumericCodeGenerator.generateAlphaNumericCode(6);//can be configured
        LocalDateTime now = LocalDateTime.now();
        this.validUpto = now.plusHours(6); // can be configured
    }
}

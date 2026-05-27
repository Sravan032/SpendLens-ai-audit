package com.sravan.spendlens.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShareIdService {

    public String generateShareId() {

        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10);
    }
}
package com.ps.eca.user.service.impl;

import com.ps.eca.user.service.CommunicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsCommunicationService implements CommunicationService {
    @Override
    public void sendMessage(String recipient, String message) {
        /**
         * Send email to use via Communication Micro Service
         * Communication service will listen over Kafka, so we have to implement kafka producer here in this code
         */
        log.info("Sending Email to user: {}, message : {}", recipient, message);
    }
}

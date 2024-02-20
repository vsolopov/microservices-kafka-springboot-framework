package com.solopov.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionMessage {

    private Long transactionId;
    private Event event;
    private Double amount;
    private Status status;

    public enum Event {
        WITHDRAW, DEPOSIT
    }

    public enum Status {
        SUBMITTED, STARTED, PENDING, FINISHED, TERMINATED
    }
}

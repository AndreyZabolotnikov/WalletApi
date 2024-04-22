package ru.duxa.walletapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WalletErrorResponse {
    private String message;
    private long timestamp;
}
